
package shafin.sustord.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import shafin.sustord.dto.ClassRoutinePOJO;
import shafin.sustord.dto.StudentPOJO;
import shafin.sustord.dto.SyllabusPOJO;
import shafin.sustord.model.ClassRoutine;
import shafin.sustord.model.Course;
import shafin.sustord.model.CourseRegistration;
import shafin.sustord.model.PersonalInfo;
import shafin.sustord.model.Prerequisite;
import shafin.sustord.model.StudentInfo;
import shafin.sustord.model.Syllabus;
import shafin.sustord.model.TimeSlot;
import shafin.sustord.pojo.StatSemesterPojo;
import shafin.sustord.util.CgpaCalculation;
import shafin.sustord.util.FormatService;
import shafin.sustord.util.HibernateUtil;
import shafin.sustord.util.JsonConvertion;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author SHAFIN
 */
public class StudentService {

	private final SessionFactory sessionFactory;

	public StudentService() throws ExceptionInInitializerError, SQLException {
		this.sessionFactory = HibernateUtil.getSessionFactory();
	}

	/**
	 * *************************************************************************
	 * **************************** *************** AUTHENTICATION
	 * ******************************
	 * *************************************************************************
	 * ****************************
	 */
	/**
	 *
	 * @param registration
	 * @param passWord
	 * @return
	 */
	/**
	 * *************************************************************************
	 * **************************** *************** GET COURSES FROM SYLLABUS
	 * AND REGISTRATION TABLE
	 * *************************************************************************
	 * ****************************
	 * 
	 * @param regNo
	 * @return
	 */
	public boolean isStudentSyllabusHasOptionalCourses(String regNo) {
		List<SyllabusPOJO> studentSyllabusList = getStudentSyllabusAsEntity(regNo, 0);

		return !studentSyllabusList.isEmpty();
	}

	/**
	 *
	 * @param regNo
	 * @param semester
	 * @return
	 */
	public String getStudentSyllabus(String regNo, int semester) {

		List<SyllabusPOJO> courseList;
		courseList = getStudentSyllabusAsEntity(regNo, semester);
		String jsoListString = JsonConvertion.objectListToJsonString(courseList);

		return jsoListString;
	}

	/**
	 *
	 * @param regNo
	 * @param semester
	 * @return
	 */
	public List<SyllabusPOJO> getStudentSyllabusAsEntity(String regNo, int semester) {
		List<SyllabusPOJO> courseList = new ArrayList<>();
		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {

			Integer batch = std.getStudentBatchIdFk().getStudentBatchId();

			String hql = "FROM Syllabus WHERE studentBatchIdFk = :batch and semester = :semester";
			Query query = session.createQuery(hql);
			query.setInteger("batch", batch);
			query.setParameter("semester", semester);

			@SuppressWarnings("unchecked")
			List<Syllabus> syllabusList = (List<Syllabus>) query.list();
			session.getTransaction().commit();

			List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(regNo,
					getStudentTotalSemester(regNo));

			for (Syllabus s : syllabusList) {

				SyllabusPOJO syllabusPojo = new SyllabusPOJO();

				// getting the prerequisite courses
				String prerequisiteString = "";
				List<Prerequisite> prerequisites = (List<Prerequisite>) s.getPrerequisiteCollection();
				if (!prerequisites.isEmpty()) {
					for (Prerequisite p : prerequisites) {
						prerequisiteString += p.getCourseIdFk().getCourseCode() + " ";
					}
				}
				// getting the grade value from registration
				String gradeString = "";
				String gradePoint = "";
				String passedOn = "";
				boolean foundGrade = false;
				for (SyllabusPOJO all : allCourseRegistrations) {
					if (all.getCourseCode().equals(s.getCourseIdFk().getCourseCode())) {
						String grade = all.getGrade();
						gradeString += " " + grade;
						foundGrade = true;

						gradePoint = CgpaCalculation.getGradePointFromGradeLetter(grade);

						if (grade.equals("F")) {
							passedOn = "dropped";
						} else if (grade.equals("N/A")) {
							passedOn = "";
						} else {
							passedOn = FormatService.formatSemesterNameShort(all.getTakenSemester());
						}
					}
				}
				if (!foundGrade) {
					syllabusPojo.setGrade("N/A");
				} else {
					syllabusPojo.setGrade(gradeString);
				}
				syllabusPojo.setPoint(gradePoint);
				syllabusPojo.setPassedOn(passedOn);
				syllabusPojo.setStatus("N/A");

				syllabusPojo.setSyllabusId(s.getSyllabusId());
				syllabusPojo.setCourseCode(s.getCourseIdFk().getCourseCode());
				syllabusPojo.setAcceptingDept(s.getAcceptingDeptIdFk().getDeptCode());
				syllabusPojo.setOfferringDept(s.getOfferingDeptIdFk().getDeptCode());
				syllabusPojo.setTitle(s.getCourseIdFk().getTitle());
				syllabusPojo.setCredit(s.getCourseIdFk().getCredit());
				syllabusPojo.setPrerequisite(prerequisiteString);
				if (s.getCourseIdFk().getTheoryOrLab() == 1) {
					syllabusPojo.setTheoryOrLab(true);
				} else {
					syllabusPojo.setTheoryOrLab(false);
				}
				syllabusPojo.setHrsWeek(s.getHrsWeek());

				courseList.add(syllabusPojo);
			}

		} catch (Exception e) {

		} finally {
			session.close();
		}

		return courseList;

	}

	/**
	 *
	 * @param regNo
	 * @param uptoSemester
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SyllabusPOJO> getStudentSyllabusAll(String regNo, int uptoSemester) {

		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);
		List<SyllabusPOJO> courseList = new ArrayList<SyllabusPOJO>();

		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			Integer batch = std.getStudentBatchIdFk().getStudentBatchId();

			String hql = "FROM Syllabus WHERE studentBatchIdFk = :batch and semester <= :semester and semester > 0";
			Query query = session.createQuery(hql);
			query.setInteger("batch", batch);
			query.setParameter("semester", uptoSemester);

			List<Syllabus> syllabusList = (List<Syllabus>) query.list();
			session.getTransaction().commit();

			List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(regNo,
					getStudentCurrentSemester(regNo));

			for (Syllabus s : syllabusList) {
				SyllabusPOJO syllabusPojo = new SyllabusPOJO();

				// getting the prerequisite courses
				String prerequisiteString = "";
				List<Prerequisite> prerequisites = (List<Prerequisite>) s.getPrerequisiteCollection();
				if (!prerequisites.isEmpty()) {
					for (Prerequisite p : prerequisites) {
						prerequisiteString += p.getCourseIdFk().getCourseCode() + " ";
					}
				}

				// getting the grade value from registration
				String gradeString = "";
				String gradePoint = "";
				String passedOn = "";
				boolean foundGrade = false;
				for (SyllabusPOJO all : allCourseRegistrations) {
					if (all.getCourseCode().equals(s.getCourseIdFk().getCourseCode())) {
						String grade = all.getGrade();
						gradeString += " " + grade;
						foundGrade = true;

						gradePoint = CgpaCalculation.getGradePointFromGradeLetter(grade);

						if (grade.equals("F")) {
							passedOn = "dropped";
						} else if (grade.equals("N/A")) {
							passedOn = "";
						} else {
							passedOn = FormatService.formatSemesterNameShort(all.getTakenSemester());
						}
					}
				}
				if (!foundGrade) {
					syllabusPojo.setGrade("N/A");
				} else {
					syllabusPojo.setGrade(gradeString);
				}
				syllabusPojo.setPoint(gradePoint);
				syllabusPojo.setPassedOn(passedOn);
				syllabusPojo.setStatus("N/A");

				syllabusPojo.setSyllabusId(s.getSyllabusId());
				syllabusPojo.setCourseCode(s.getCourseIdFk().getCourseCode());
				syllabusPojo.setOfferingSemester(s.getSemester());
				syllabusPojo.setAcceptingDept(s.getAcceptingDeptIdFk().getDeptCode());
				syllabusPojo.setOfferringDept(s.getOfferingDeptIdFk().getDeptCode());
				syllabusPojo.setTitle(s.getCourseIdFk().getTitle());
				syllabusPojo.setCredit(s.getCourseIdFk().getCredit());
				syllabusPojo.setPrerequisite(prerequisiteString);
				if (s.getCourseIdFk().getTheoryOrLab() == 1) {
					syllabusPojo.setTheoryOrLab(true);
				} else {
					syllabusPojo.setTheoryOrLab(false);
				}
				syllabusPojo.setHrsWeek(s.getHrsWeek());

				courseList.add(syllabusPojo);
			}
		} catch (Exception e) {

		} finally {
			session.close();
		}

		return courseList;
	}

	/**
	 *
	 * @param courseCode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getStudentCourseDetails(String courseCode) {
		String details = "";
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			String hql = "FROM Course WHERE courseCode = :code";
			Query query = session.createQuery(hql);
			query.setString("code", courseCode);

			List<Course> list = (List<Course>) query.list();
			session.getTransaction().commit();

			if (list.get(0).getContent() != null) {
				details = list.get(0).getContent();
			}
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return details;
	}

	/**
	 *
	 * @param regNo
	 * @param semester
	 * @return
	 */
	public String getStudentRegisteredCourses(String regNo, int semester) {

		List<SyllabusPOJO> takenCourses = getStudentRegisteredCoursesAsEntity(regNo, semester);

		String jsoListString = JsonConvertion.objectListToJsonString(takenCourses);

		return jsoListString;
	}

	/**
	 *
	 * @param regNo
	 * @param semester
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SyllabusPOJO> getStudentRegisteredCoursesAsEntity(String regNo, int semester) {

		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);
		List<SyllabusPOJO> takenCourses = new ArrayList<SyllabusPOJO>();

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			String hql = "FROM CourseRegistration WHERE studentInfoIdFk = :id and attendSemester = :semester";
			Query query = session.createQuery(hql);
			query.setInteger("id", std.getStudentInfoId());
			query.setParameter("semester", semester);

			List<CourseRegistration> allRegisteredCourses = (List<CourseRegistration>) query.list();

			session.getTransaction().commit();

			SyllabusPOJO syllabusPojo;

			for (CourseRegistration c : allRegisteredCourses) {
				if (c.getAttendSemester() == semester) {

					syllabusPojo = new SyllabusPOJO();

					syllabusPojo.setCourseRegistrationId(c.getCourseRegistrationId());
					syllabusPojo.setSyllabusId(c.getSyllabusIdFk().getSyllabusId());
					syllabusPojo.setOfferingSemester(c.getSyllabusIdFk().getSemester());
					syllabusPojo.setTakenSemester(semester);
					syllabusPojo.setCourseCode(c.getSyllabusIdFk().getCourseIdFk().getCourseCode());
					syllabusPojo.setTitle(c.getSyllabusIdFk().getCourseIdFk().getTitle());
					syllabusPojo.setCredit(c.getSyllabusIdFk().getCourseIdFk().getCredit());
					syllabusPojo.setAttendYear(c.getAttendYear());

					String grade;
					if (c.getGrade() == null || c.getGrade().equals("")) {
						grade = "N/A";
					} else if (c.getGrade().equals("F")) {
						grade = "F";
					} else {
						grade = c.getGrade();
					}

					syllabusPojo.setGrade(grade);
					syllabusPojo.setPoint(CgpaCalculation.getGradePointFromGradeLetter(grade));

					if (c.getSyllabusIdFk().getCourseIdFk().getTheoryOrLab() == 1) {
						syllabusPojo.setTheoryOrLab(true);
					} else {
						syllabusPojo.setTheoryOrLab(false);
					}
					syllabusPojo.setHrsWeek(c.getSyllabusIdFk().getHrsWeek());

					takenCourses.add(syllabusPojo);
				}
			}
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return takenCourses;
	}

	/**
	 *
	 * @param regNo
	 * @param uptoSemester
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SyllabusPOJO> getStudentRegisteredCoursesAll(String regNo, int uptoSemester) {

		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);
		List<SyllabusPOJO> allTakenCoursesPojo = new ArrayList<SyllabusPOJO>();

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			String hql = "FROM CourseRegistration WHERE studentInfoIdFk = :id and attendSemester <= :uptoSem";
			Query query = session.createQuery(hql);
			query.setInteger("id", std.getStudentInfoId());
			query.setInteger("uptoSem", uptoSemester);

			List<CourseRegistration> allTakenCourses = (List<CourseRegistration>) query.list();

			session.getTransaction().commit();

			SyllabusPOJO syllabusPojo;

			for (CourseRegistration c : allTakenCourses) {

				syllabusPojo = new SyllabusPOJO();
				syllabusPojo.setCourseRegistrationId(c.getCourseRegistrationId());
				syllabusPojo.setSyllabusId(c.getSyllabusIdFk().getSyllabusId());
				syllabusPojo.setCourseCode(c.getSyllabusIdFk().getCourseIdFk().getCourseCode());
				syllabusPojo.setTitle(c.getSyllabusIdFk().getCourseIdFk().getTitle());
				syllabusPojo.setCredit(c.getSyllabusIdFk().getCourseIdFk().getCredit());
				syllabusPojo.setTakenSemester(c.getAttendSemester());
				syllabusPojo.setAttendYear(c.getAttendYear());

				// hours/week
				if (c.getSyllabusIdFk().getCourseIdFk().getTheoryOrLab() == 1) {
					syllabusPojo.setTheoryOrLab(true);
				} else {
					syllabusPojo.setTheoryOrLab(false);
				}
				syllabusPojo.setHrsWeek(c.getSyllabusIdFk().getHrsWeek());

				// grade
				String grade;
				if (c.getGrade() == null || c.getGrade().equals("")) {
					grade = "N/A";

					syllabusPojo.setStatus("N/A");
				} else if (c.getGrade().equals("F")) {
					grade = "F";
					syllabusPojo.setStatus("DROPPED");
				} else {
					grade = c.getGrade();
					syllabusPojo.setStatus("PASSED");
				}
				syllabusPojo.setGrade(grade);
				syllabusPojo.setPoint(CgpaCalculation.getGradePointFromGradeLetter(grade));

				allTakenCoursesPojo.add(syllabusPojo);
			}
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return allTakenCoursesPojo;
	}

	/**
	 *
	 * @param regNo
	 * @param uptoSemester
	 * @return
	 */
	public List<SyllabusPOJO> getStudentDroppedCoursesAsEntity(String regNo, int uptoSemester) {

		List<SyllabusPOJO> finalFilter = new ArrayList<SyllabusPOJO>();

		if (uptoSemester > 2) {
			// filtering if he/she has passed the course any when
			// ***************************************************************
			List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(regNo,
					getStudentTotalSemester(regNo));
			List<SyllabusPOJO> passedCourses = new ArrayList<SyllabusPOJO>();
			List<SyllabusPOJO> tempFilter = new ArrayList<SyllabusPOJO>();

			// removing greater semester courses and passed coourses
			for (SyllabusPOJO spojo : allCourseRegistrations) {

				if (((spojo.getTakenSemester() < uptoSemester) && (spojo.getTakenSemester() % 2 == uptoSemester % 2)
						&& (spojo.getGrade().equals("N/A") || spojo.getGrade().equals("F")))) {

					tempFilter.add(spojo);
				}
				if (((spojo.getTakenSemester() < uptoSemester) && (spojo.getTakenSemester() % 2 == uptoSemester % 2)
						&& !(spojo.getGrade().equals("N/A") || spojo.getGrade().equals("F")))) {

					passedCourses.add(spojo);
				}
			}

			// finding if the course have been passed
			for (SyllabusPOJO filtered : tempFilter) {

				boolean found = false;
				for (SyllabusPOJO passed : passedCourses) {
					if (filtered.getCourseCode().equals(passed.getCourseCode())) {
						found = true;
					}
				}
				if (!found) {
					// checking if the item already added
					boolean added = false;
					for (SyllabusPOJO f : finalFilter) {
						if (filtered.getCourseCode().equals(f.getCourseCode())) {
							added = true;
						}
					}
					if (!added) {
						finalFilter.add(filtered);
					}
				}
			}
			// ****************************************************************
		}
		return finalFilter;
	}

	/**
	 *
	 * @param regNo
	 * @param uptoSemester
	 * @return
	 */
	public String getStudentDroppedCourses(String regNo, int uptoSemester) {
		List<SyllabusPOJO> courses = getStudentDroppedCoursesAsEntity(regNo, uptoSemester);

		String jsonListString = JsonConvertion.objectListToJsonString(courses);

		return jsonListString;
	}

	public List<SyllabusPOJO> getStudentPendingCoursesAsEntity(String regNo, int uptoSemester) {
		List<SyllabusPOJO> pendingCourses = new ArrayList<SyllabusPOJO>();

		if (uptoSemester > 2) {

			// getting all courses before uptoSemester from syllabus
			// here (uptoSemester-2) because we need to search from 2 semester
			// backward
			List<SyllabusPOJO> allSyllabusCourses = getStudentSyllabusAll(regNo, uptoSemester - 2);

			// getting all registered courses before uptoSemester from
			// courseRegistration
			List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(regNo, uptoSemester - 2);

			// searching for the pending courses comparing
			// allSyllabusCoursesModFilter and allCourseRegistrationsModFilter
			for (SyllabusPOJO syl : allSyllabusCourses) {
				if (syl.getOfferingSemester() % 2 == uptoSemester % 2) {
					// System.out.println("syllabus: "+syl.getCourseCode());
					boolean found = false;
					for (SyllabusPOJO reg : allCourseRegistrations) {
						if (reg.getTakenSemester() % 2 == uptoSemester % 2) {
							// System.out.println("registration:
							// "+reg.getCourseCode());
							if (syl.getCourseCode().equals(reg.getCourseCode())) {
								found = true;
							}
						}
					}
					if (!found) {
						// System.out.println("pending");
						// System.out.println(syl.getCourseCode());
						pendingCourses.add(syl);
					}
				}
			}
			// ****************************************************************
		}
		return pendingCourses;
	}

	/**
	 *
	 * @param regNo
	 * @param day
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<ClassRoutinePOJO> getStudentRoutine(String regNo, String day) {

		List<ClassRoutinePOJO> myRoutines = new ArrayList<ClassRoutinePOJO>();
		// getting the registered courses for the current semester
		List<SyllabusPOJO> registrations = getStudentRegisteredCoursesAsEntity(regNo, getStudentCurrentSemester(regNo));
		if (!registrations.isEmpty()) {
			int year = registrations.get(0).getAttendYear();
			int semester;
			if (registrations.get(0).getTakenSemester() % 2 == 1) {
				semester = 1;
			} else {
				semester = 2;
			}

			Session session = sessionFactory.openSession();

			try {
				session.beginTransaction();
				// getting all timeSlots of the day
				String hql = "FROM TimeSlot WHERE day= :day";
				Query query = session.createQuery(hql);
				query.setString("day", day);

				List<TimeSlot> timeSlots = (List<TimeSlot>) query.list();

				// getting all classRoutines of every timeSlot with Department
				// year and semester match
				for (TimeSlot t : timeSlots) {

					List<ClassRoutine> routines = (List<ClassRoutine>) t.getClassRoutineCollection();

					if (!routines.isEmpty()) {

						// System.out.println("slot: " + t.getDay() + " " +
						// t.getStart() + "-" + t.getEnd());
						for (ClassRoutine c : routines) {
							if (c.getYear() == year && c.getSemester() == semester) {
								// System.out.println("class: " +
								// c.getSyllabusIdFk().getCourseIdFk().getCourseCode());

								for (SyllabusPOJO reg : registrations) {

									String routineCourseCode = c.getSyllabusIdFk().getCourseIdFk().getCourseCode();
									if (reg.getCourseCode().equals(routineCourseCode)) {
										// then push data to ClassRoutinePOJO
										ClassRoutinePOJO r = new ClassRoutinePOJO();
										r.setCourseCode(routineCourseCode);
										r.setTitle(reg.getTitle());
										r.setDay(day);
										r.setStart(t.getStart());
										r.setEnd(t.getEnd());

										myRoutines.add(r);
									}
								}

							}
						}

					}

				}
				session.getTransaction().commit();

			} catch (Exception e) {
			} finally {
				session.close();
			}

		}

		// finally return the POJO collection
		return myRoutines;
	}

	/**
	 *
	 * @param regNo
	 * @return
	 */
	public double getCGPA(String regNo) {
		List<SyllabusPOJO> cummilativeCourses = getStudentRegisteredCoursesAll(regNo, getStudentTotalSemester(regNo));

		double cgpa = CgpaCalculation.getGradePointOfSemester(cummilativeCourses);

		return cgpa;
	}

	/**
	 *
	 * @param regNo
	 * @return
	 */
	public double getTotalCredits(String regNo) {
		double totalCredit;
		totalCredit = CgpaCalculation
				.getTotalCreditOfSemester(getStudentSyllabusAll(regNo, getStudentTotalSemester(regNo)));
		return totalCredit;
	}

	public double getCreditsCompleted(String regNo) {
		double completedCredits;
		completedCredits = CgpaCalculation
				.getPassedCreditOfSemester(getStudentRegisteredCoursesAll(regNo, getStudentCurrentSemester(regNo)));
		return completedCredits;
	}

	public int getTotalCourses(String regNo) {
		int totalcourses;
		totalcourses = getStudentSyllabusAll(regNo, getStudentTotalSemester(regNo)).size();
		return totalcourses;
	}

	public int getCompletedCourses(String regNo) {
		int completedcourses = 0;
		List<SyllabusPOJO> all = getStudentRegisteredCoursesAll(regNo, getStudentCurrentSemester(regNo));
		for (SyllabusPOJO spojo : all) {
			if (spojo.getGrade().equals("A+") || spojo.getGrade().equals("A") || spojo.getGrade().equals("A-")
					|| spojo.getGrade().equals("B+") || spojo.getGrade().equals("B") || spojo.getGrade().equals("B-")
					|| spojo.getGrade().equals("C+") || spojo.getGrade().equals("C") || spojo.getGrade().equals("C-")) {
				completedcourses += 1;
			}
		}
		return completedcourses;
	}

	@SuppressWarnings("unchecked")
	public List<StudentPOJO> getStudentRankList(String regNo) {

		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);

		List<StudentPOJO> students = new ArrayList<StudentPOJO>();

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			// getting all student of that batch in list
			String hql = "FROM StudentInfo WHERE studentBatchIdFk= :batchId";
			Query query = session.createQuery(hql);
			query.setInteger("batchId", std.getStudentBatchIdFk().getStudentBatchId());

			List<StudentInfo> studentInfos = (List<StudentInfo>) query.list();

			for (StudentInfo s : studentInfos) {
				String reg = s.getRegistrationNo();

				double cgpa = getCGPA(s.getRegistrationNo());
				double cCredit = getCreditsCompleted(s.getRegistrationNo());
				if (cgpa > 0) {
					StudentPOJO guy = new StudentPOJO();
					guy.setRegistrationNo(reg);
					guy.setName(s.getPersonalInfo().getName());
					guy.setCgpa(cgpa);
					guy.setCompletedCredits(cCredit);

					students.add(guy);
				}

			}
			Collections.sort(students, StudentPOJO.CgpaMultiplyCreditComparator);

			session.getTransaction().commit();

		} catch (Exception e) {
		} finally {
			session.close();
		}

		return students;
	}

	/**
	 * *************************************************************************
	 * **************************** *************** STATISTICS METHODS
	 * *************************************************************************
	 * ****************************
	 * 
	 * @param regNo
	 * @param semester
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public StatSemesterPojo getSemesterStatistics(String regNo, int semester) {

		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);
		int Ap, A, Am, Bp, B, Bm, Cp, C, Cm, F;

		StatSemesterPojo stat = new StatSemesterPojo();

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			// getting all student of that batch in list
			String hql = "FROM StudentInfo WHERE studentBatchIdFk= :batchId";
			Query query = session.createQuery(hql);
			query.setInteger("batchId", std.getStudentBatchIdFk().getStudentBatchId());
			List<StudentInfo> studentInfos = (List<StudentInfo>) query.list();

			Ap = A = Am = Bp = B = Bm = Cp = C = Cm = F = 0;
			for (StudentInfo s : studentInfos) {

				List<SyllabusPOJO> registeredCourses = getStudentRegisteredCoursesAsEntity(s.getRegistrationNo(),
						semester);

				double gradePoint = CgpaCalculation.getGradePointOfSemester(registeredCourses);
				String gradeLetter = CgpaCalculation.getGradeLetterFromGradePoint(gradePoint);

				if (gradeLetter.equals("A+")) {
					Ap++;
				} else if (gradeLetter.equals("A")) {
					A++;
				} else if (gradeLetter.equals("A-")) {
					Am++;
				} else if (gradeLetter.equals("B+")) {
					Bp++;
				} else if (gradeLetter.equals("B")) {
					B++;
				} else if (gradeLetter.equals("B-")) {
					Bm++;
				} else if (gradeLetter.equals("C+")) {
					Cp++;
				} else if (gradeLetter.equals("C")) {
					C++;
				} else if (gradeLetter.equals("C-")) {
					Cm++;
				} else if (gradeLetter.equals("F")) {
					// F++;
				} else {
					System.out.println("nothing to do with this gradeletter");
				}
			}

			stat.setSemesterGradeDistributionMap(Ap, A, Am, Bp, B, Bm, Cp, C, Cm, F);

			// getting the syllabus courses
			List<SyllabusPOJO> syllabusCourses = getStudentSyllabusAsEntity(regNo, semester);

			for (SyllabusPOJO s : syllabusCourses) {
				Ap = A = Am = Bp = B = Bm = Cp = C = Cm = F = 0;
				s.setNoOfAttendedStudent(
						getStatOfTotalCourseAttend(s, std.getStudentBatchIdFk().getStudentBatchId(), semester));
				Ap = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "A+");
				A = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "A");
				Am = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "A-");
				Bp = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "B+");
				B = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "B");
				Bm = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "B-");
				Cp = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "C+");
				C = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "C");
				Cm = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "C-");
				F = getStatOfCourseGrade(s, std.getStudentBatchIdFk().getStudentBatchId(), semester, "F");

				s.setCourseGradeDistributionMap(Ap, A, Am, Bp, B, Bm, Cp, C, Cm, F);
			}

			stat.setCourseStat(syllabusCourses);

			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return stat;
	}

	/**
	 * ************************************************************************
	 * STATISTICS HELPER METHODS
	 * ************************************************************************
	 * 
	 * @param s
	 * @param batchId
	 * @param semester
	 * @return
	 */
	public int getStatOfTotalCourseAttend(SyllabusPOJO s, int batchId, int semester) {

		Long attendedStudent = new Long("0");
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			// getting the course attend count from hql
			String hql = "select count(*) from CourseRegistration "
					+ "where syllabusIdFk = :syllabusId and approval = 1 and attendSemester = :semester "
					+ "and studentInfoIdFk in (select studentInfoId from StudentInfo "
					+ "where studentBatchIdFk = :batchId)";
			Query query = session.createQuery(hql);
			query.setInteger("syllabusId", s.getSyllabusId());
			query.setInteger("semester", semester);
			query.setInteger("batchId", batchId);

			attendedStudent = (Long) query.uniqueResult();

			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return attendedStudent.intValue();
	}

	public int getStatOfCourseGrade(SyllabusPOJO s, int batchId, int semester, String gradeLetter) {

		Long gradeCount = new Long("0");
		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			// getting the course attend count from hql
			String hql = "select count(*) from CourseRegistration "
					+ "where syllabusIdFk = :syllabusId and approval = 1 and attendSemester = :semester and grade = :gradeLetter "
					+ "and studentInfoIdFk in (select studentInfoId from StudentInfo "
					+ "where studentBatchIdFk = :batchId)";
			Query query = session.createQuery(hql);
			query.setInteger("syllabusId", s.getSyllabusId());
			query.setInteger("semester", semester);
			query.setString("gradeLetter", gradeLetter);
			query.setInteger("batchId", batchId);

			gradeCount = (Long) query.uniqueResult();

			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return gradeCount.intValue();
	}

	/**
	 * *************************************************************************
	 * **************************** *************** SAVE AND MODIFICATION
	 * OPERATIONS ************************
	 * *************************************************************************
	 * ****************************
	 */
	/**
	 *
	 * @param personalInfo
	 * @return
	 */
	public boolean savePersonalInfo(PersonalInfo personalInfo) {

		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(personalInfo);
			session.getTransaction().commit();

		} catch (Exception e) {
			return false;
		} finally {
			session.close();
		}

		return true;
	}

	/**
	 * @param regNo
	 *
	 * @param jsonString
	 * @param semester
	 * @return
	 */
	public boolean doStudentCourseRegistration(String regNo, String jsonString, int semester) {

		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);

		// System.out.println("input wish: " + jsonString);
		JsonParser parser = new JsonParser(); // declaring jsonparser
		JsonElement jsonElement = parser.parse(jsonString); // getting the
															// parsed jsonString
															// as jsonElement
		JsonArray wishList = jsonElement.getAsJsonArray(); // converting the
															// element as
															// jsonArray

		// System.out.println("converted wish: " + wishList);
		// getting the currently registeres courses befor commit change
		String currentCoursesJson = getStudentRegisteredCourses(regNo, semester);

		// System.out.println("input target: " + currentCoursesJson);
		JsonParser parser1 = new JsonParser();
		JsonElement jsonElement1 = parser1.parse(currentCoursesJson);
		JsonArray targetList = jsonElement1.getAsJsonArray();

		// System.out.println("converted target: " + targetList);
		// generating the course taken year value from the semester
		String batchSession = getStudentSessiontName(regNo);
		int batchYear = Integer.parseInt(batchSession.substring(0, 4));
		String formatSemester = FormatService.formatSemesterNumber(semester);
		int academicYear = Integer.parseInt(formatSemester.substring(0, 1));
		int attendedYear = batchYear + academicYear;

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			// ---------------------------------------------------------------------------------------
			Gson gson = new Gson();
			CourseRegistration courseRegistration;

			if (wishList.isJsonNull() && targetList.isJsonNull()) {
				// System.out.println("testcase 1");
				return true;
			} else if (!wishList.isJsonNull() && targetList.isJsonNull()) {
				for (int i = 0; i < wishList.size(); i++) {
					String jsonObjectString = wishList.get(i).toString();
					SyllabusPOJO addSyllabusPojo = gson.fromJson(jsonObjectString, SyllabusPOJO.class);

					courseRegistration = new CourseRegistration();
					courseRegistration.setStudentInfoIdFk(std);
					courseRegistration.setAttendYear(attendedYear);
					courseRegistration.setAttendSemester(semester);

					Syllabus syllabus = (Syllabus) session.get(Syllabus.class, addSyllabusPojo.getSyllabusId());
					courseRegistration.setSyllabusIdFk(syllabus);

					courseRegistration.setApproval(1);

					session.save(courseRegistration);
				}
			} else if (wishList.isJsonNull() && !targetList.isJsonNull()) {
				for (int i = 0; i < targetList.size(); i++) {
					String jsonObjectString = targetList.get(i).toString();
					SyllabusPOJO delSyllabusPojo = gson.fromJson(jsonObjectString, SyllabusPOJO.class);

					courseRegistration = (CourseRegistration) session.get(CourseRegistration.class,
							delSyllabusPojo.getCourseRegistrationId());

					session.delete(courseRegistration);

				}
			} else {
				for (int i = 0; i < wishList.size(); i++) {
					String wishJsonString = wishList.get(i).toString();
					SyllabusPOJO wishSyllabusPojo = gson.fromJson(wishJsonString, SyllabusPOJO.class);
					boolean alreadyExists = false;

					for (int j = 0; j < targetList.size(); j++) {
						String targetJsonString = targetList.get(j).toString();
						SyllabusPOJO targetSyllabusPojo = gson.fromJson(targetJsonString, SyllabusPOJO.class);

						if (wishSyllabusPojo.getSyllabusId() == targetSyllabusPojo.getSyllabusId()) {
							alreadyExists = true;
						}
					}

					if (!alreadyExists) {
						courseRegistration = new CourseRegistration();
						courseRegistration.setStudentInfoIdFk(std);
						courseRegistration.setAttendYear(attendedYear);
						courseRegistration.setAttendSemester(semester);

						Syllabus syllabus = (Syllabus) session.get(Syllabus.class, wishSyllabusPojo.getSyllabusId());
						courseRegistration.setSyllabusIdFk(syllabus);

						courseRegistration.setApproval(1);

						session.save(courseRegistration);
					}
				}

				for (int i = 0; i < targetList.size(); i++) {

					String dTargetJsonString = targetList.get(i).toString();
					SyllabusPOJO delTargetSyllabusPojo = gson.fromJson(dTargetJsonString, SyllabusPOJO.class);
					boolean existsWishList = false;

					for (int j = 0; j < wishList.size(); j++) {
						String dWishJsonString = wishList.get(j).toString();
						SyllabusPOJO dWishSyllabusPojo = gson.fromJson(dWishJsonString, SyllabusPOJO.class);

						if (dWishSyllabusPojo.getSyllabusId() == delTargetSyllabusPojo.getSyllabusId()) {
							existsWishList = true;
						}
					}

					if (!existsWishList) {

						courseRegistration = (CourseRegistration) session.get(CourseRegistration.class,
								delTargetSyllabusPojo.getCourseRegistrationId());
						session.delete(courseRegistration);
					}
				}
			}

			// ---------------------------------------------------------------------------------------
			session.getTransaction().commit();
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return true;
	}

	/**
	 *
	 * @param regNo
	 * @param jsonString
	 * @param semester
	 * @return
	 */
	public boolean doStudentResultUpdate(String regNo, String jsonString, int semester) {

		boolean status = false;
		// System.out.println("method accesses ! \n" + jsonString);
		JsonParser parser = new JsonParser(); // declaring jsonparser
		JsonElement jsonElement = parser.parse(jsonString); // getting the
															// parsed jsonString
															// as jsonElement
		JsonArray gradeArray = jsonElement.getAsJsonArray();

		Session session = sessionFactory.openSession();

		try {

			session.beginTransaction();
			// ----------------------------------------------------------------------
			Gson gson = new Gson();
			for (int i = 0; i < gradeArray.size(); i++) {

				String syllabusPojoString = gradeArray.get(i).toString();
				SyllabusPOJO syllabusPojo = gson.fromJson(syllabusPojoString, SyllabusPOJO.class);

				CourseRegistration registration = (CourseRegistration) session.get(CourseRegistration.class,
						syllabusPojo.getCourseRegistrationId());
				registration.setGrade(syllabusPojo.getGrade());

				// verifying that this grade update doesnt contradict with other
				// semesters' intake of the same course
				List<SyllabusPOJO> allRegistered = getStudentRegisteredCoursesAll(regNo,
						getStudentCurrentSemester(regNo));
				for (SyllabusPOJO s : allRegistered) {
					if (s.getCourseCode().equals(syllabusPojo.getCourseCode()) && s.getTakenSemester() > semester) {

						// DELETE THE COURSE REGISTRATION HERE
						CourseRegistration invalidReg = (CourseRegistration) session.get(CourseRegistration.class,
								s.getCourseRegistrationId());

						session.delete(invalidReg);
					}
				}
			}
			status = true;
			// ----------------------------------------------------------------------
			session.getTransaction().commit();

		} catch (HibernateException e) {
			status = false;
		} finally {
			session.close();
		}
		return status;

	}

	/**
	 *
	 * @param regNo
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int getStudentCurrentSemester(String regNo) {

		Session session = sessionFactory.openSession();

		try {
			StudentInfo std = getStudentInfoObjectFromRegNo(regNo);

			session.beginTransaction();

			String hql = "select max(attendSemester) FROM CourseRegistration WHERE studentInfoIdFk = :id";
			Query query = session.createQuery(hql);
			query.setInteger("id", std.getStudentInfoId());
			List<Integer> maxReg = query.list();

			session.getTransaction().commit();

			int maxVal; // assigning 1 so that current semester is 1 by default
			if (!maxReg.isEmpty()) {
				maxVal = (Integer) maxReg.get(0);
			} else {
				maxVal = 1;
				// System.out.println("this is it");
			}
			return maxVal;

		} catch (Exception e) {
			return 1;
		} finally {
			session.close();
		}

	}

	public int getStudentTotalSemester(String regNo) {
		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);
		int totalSemester = std.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getTotalSemester();

		return totalSemester;
	}

	public String getStudentSessiontName(String regNo) {
		StudentInfo std = getStudentInfoObjectFromRegNo(regNo);
		String sessionName = std.getStudentBatchIdFk().getSession();
		return sessionName;
	}

	@SuppressWarnings("unchecked")
	public StudentInfo getStudentInfoObjectFromRegNo(String regNo) {

		StudentInfo studentInfoObject = null;

		Session session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			String hql = "FROM StudentInfo WHERE registrationNo = :reg";
			Query query = session.createQuery(hql);
			query.setParameter("reg", regNo);

			List<StudentInfo> infos = (List<StudentInfo>) query.list();

			session.getTransaction().commit();

			if (!infos.isEmpty()) {
				studentInfoObject = infos.get(0);
			}
		} catch (Exception e) {
		} finally {
			session.close();
		}

		return studentInfoObject;
	}

	/**
	 * *************************************************************************
	 * **************************** *************** SETTER & GETTER
	 * ************************
	 * *************************************************************************
	 * ****************************
	 */
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

}
