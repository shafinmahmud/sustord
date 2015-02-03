/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import me.shafin.sustord.bean.ClassRoutinePOJO;
import me.shafin.sustord.bean.SyllabusPOJO;
import me.shafin.sustord.entity.ClassRoutine;
import me.shafin.sustord.entity.Course;
import me.shafin.sustord.entity.CourseRegistration;
import me.shafin.sustord.entity.PersonalInfo;
import me.shafin.sustord.entity.Prerequisite;
import me.shafin.sustord.entity.StudentInfo;
import me.shafin.sustord.entity.Syllabus;
import me.shafin.sustord.entity.TimeSlot;
import me.shafin.sustord.utility.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author SHAFIN
 */
public class StudentService {

    private SessionFactory sessionFactory;
    private StudentInfo studentInfo = null;

    /**
     * *****************************************************************************************************
     * *************** AUTHENTICATION ******************************
     * *****************************************************************************************************
     */
    /**
     *
     * @param registration
     * @param passWord
     * @return
     */
    public String verifyLogin(String registration, String passWord) {
        String connectionStatus = "denied";
        try {
            sessionFactory = HibernateUtil.getSessionFactory();

            Session session = sessionFactory.openSession();
            session.beginTransaction();
            String hql = "FROM StudentInfo WHERE registrationNo = :reg and password = :pass";
            Query query = session.createQuery(hql);
            query.setParameter("reg", registration);
            query.setParameter("pass", passWord);
            List<StudentInfo> infos = (List<StudentInfo>) query.list();

            session.getTransaction().commit();
            session.close();

            if (!infos.isEmpty()) {
                setStudentInfo(infos.get(0));
                connectionStatus = "verified";
            }
        } catch (ExceptionInInitializerError ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String errorString = sw.toString(); // stack trace as a string  
            connectionStatus = errorString;
        }

        return connectionStatus;

    }

    /**
     * *****************************************************************************************************
     * *************** GET COURSES FROM SYLLABUS AND REGISTRATION TABLE
     * *****************************************************************************************************
     */
    public boolean isStudentSyllabusHasOptionalCourses() {
        List<SyllabusPOJO> studentSyllabusList = getStudentSyllabusAsEntity(0);

        if (!studentSyllabusList.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param semester
     * @return
     */
    public String getStudentSyllabus(int semester) {

        List<SyllabusPOJO> courseList;
        courseList = getStudentSyllabusAsEntity(semester);

        String jsoListString = JsonConvertion.objectListToJsonString(courseList);

        return jsoListString;

    }

    /**
     *
     * @param semester
     * @return
     */
    public List<SyllabusPOJO> getStudentSyllabusAsEntity(int semester) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer batch = studentInfo.getStudentBatchIdFk().getStudentBatchId();

        String hql = "FROM Syllabus WHERE studentBatchIdFk = :batch and semester = :semester";
        Query query = session.createQuery(hql);
        query.setInteger("batch", batch);
        query.setParameter("semester", semester);

        List<Syllabus> syllabusList = (List<Syllabus>) query.list();
        session.getTransaction().commit();

        List<SyllabusPOJO> courseList = new ArrayList<SyllabusPOJO>();
        List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(getStudentTotalSemester());

        for (Syllabus s : syllabusList) {
            SyllabusPOJO syllabusPojo = new SyllabusPOJO();

            //getting the prerequsite courses
            String prerequisiteString = "";
            List<Prerequisite> prerequisites = (List<Prerequisite>) s.getPrerequisiteCollection();
            if (!prerequisites.isEmpty()) {
                for (Prerequisite p : prerequisites) {
                    prerequisiteString += p.getCourseIdFk().getCourseCode() + " ";
                }
            }

            //getting the grade value from registration
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
        session.close();
        return courseList;

    }

    /**
     *
     * @param uptoSemester
     * @return
     */
    public List<SyllabusPOJO> getStudentSyllabusAll(int uptoSemester) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Integer batch = studentInfo.getStudentBatchIdFk().getStudentBatchId();

        String hql = "FROM Syllabus WHERE studentBatchIdFk = :batch and semester <= :semester and semester > 0";
        Query query = session.createQuery(hql);
        query.setInteger("batch", batch);
        query.setParameter("semester", uptoSemester);

        List<Syllabus> syllabusList = (List<Syllabus>) query.list();
        session.getTransaction().commit();

        List<SyllabusPOJO> courseList = new ArrayList<SyllabusPOJO>();
        List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(getStudentCurrentSemester());

        for (Syllabus s : syllabusList) {
            SyllabusPOJO syllabusPojo = new SyllabusPOJO();

            //getting the prerequsite courses
            String prerequisiteString = "";
            List<Prerequisite> prerequisites = (List<Prerequisite>) s.getPrerequisiteCollection();
            if (!prerequisites.isEmpty()) {
                for (Prerequisite p : prerequisites) {
                    prerequisiteString += p.getCourseIdFk().getCourseCode() + " ";
                }
            }

            //getting the grade value from registration
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
        session.close();
        return courseList;
    }

    public String getStudentCourseDetails(String courseCode) {
        String details = "";
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM Course WHERE courseCode = :code";
        Query query = session.createQuery(hql);
        query.setString("code", courseCode);

        List<Course> list = (List<Course>) query.list();
        session.getTransaction().commit();

        if (list.get(0).getContent() != null) {
            details = list.get(0).getContent();
        }
        session.close();
        return details;
    }

    /**
     *
     * @param semester
     * @return
     */
    public String getStudentRegisteredCourses(int semester) {

        List<SyllabusPOJO> takenCourses = getStudentRegisteredCoursesAsEntity(semester);

        String jsoListString = JsonConvertion.objectListToJsonString(takenCourses);

        return jsoListString;
    }

    /**
     *
     * @param semester
     * @return
     */
    public List<SyllabusPOJO> getStudentRegisteredCoursesAsEntity(int semester) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM CourseRegistration WHERE studentInfoIdFk = :id and attendSemester = :semester";
        Query query = session.createQuery(hql);
        query.setInteger("id", studentInfo.getStudentInfoId());
        query.setParameter("semester", semester);

        List<CourseRegistration> allRegisteredCourses = (List<CourseRegistration>) query.list();

        session.getTransaction().commit();
        session.close();

        List<SyllabusPOJO> takenCourses = new ArrayList<SyllabusPOJO>();
        SyllabusPOJO syllabusPojo;

        for (CourseRegistration c : allRegisteredCourses) {
            if (c.getAttendSemester() == semester) {

                syllabusPojo = new SyllabusPOJO();

                syllabusPojo.setCourseRegistrationId(c.getCourseRegistrationId());
                syllabusPojo.setSyllabusId(c.getSyllabusIdFk().getSyllabusId());
                syllabusPojo.setOfferingSemester(c.getSyllabusIdFk().getSemester());
                syllabusPojo.setCourseCode(c.getSyllabusIdFk().getCourseIdFk().getCourseCode());
                syllabusPojo.setTitle(c.getSyllabusIdFk().getCourseIdFk().getTitle());
                syllabusPojo.setCredit(c.getSyllabusIdFk().getCourseIdFk().getCredit());
                syllabusPojo.setAttendYear(c.getAttendYear());

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

                if (c.getSyllabusIdFk().getCourseIdFk().getTheoryOrLab() == 1) {
                    syllabusPojo.setTheoryOrLab(true);
                } else {
                    syllabusPojo.setTheoryOrLab(false);
                }
                syllabusPojo.setHrsWeek(c.getSyllabusIdFk().getHrsWeek());

                takenCourses.add(syllabusPojo);
            }
        }

        return takenCourses;
    }

    /**
     *
     * @param uptoSemester
     * @return
     */
    public List<SyllabusPOJO> getStudentRegisteredCoursesAll(int uptoSemester) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String hql = "FROM CourseRegistration WHERE studentInfoIdFk = :id and attendSemester <= :uptoSem";
        Query query = session.createQuery(hql);
        query.setInteger("id", studentInfo.getStudentInfoId());
        query.setInteger("uptoSem", uptoSemester);

        List<CourseRegistration> allTakenCourses = (List<CourseRegistration>) query.list();

        session.getTransaction().commit();
        session.close();

        List<SyllabusPOJO> allTakenCoursesPojo = new ArrayList<SyllabusPOJO>();
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
        return allTakenCoursesPojo;
    }

    /**
     *
     * @param uptoSemester
     * @return
     */
    public List<SyllabusPOJO> getStudentDroppedCoursesAsEntity(int uptoSemester) {

        List<SyllabusPOJO> finalFilter = new ArrayList<SyllabusPOJO>();

        if (uptoSemester > 2) {
            //filerting if he/she has passed the course any when
            //***************************************************************
            List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(getStudentTotalSemester());
            List<SyllabusPOJO> passedCourses = new ArrayList<SyllabusPOJO>();
            List<SyllabusPOJO> tempFilter = new ArrayList<SyllabusPOJO>();

            //removing greater semester courses and passed coourses
            for (SyllabusPOJO spojo : allCourseRegistrations) {

                if (((spojo.getTakenSemester() < uptoSemester)
                        && (spojo.getTakenSemester() % 2 == uptoSemester % 2)
                        && (spojo.getGrade().equals("N/A") || spojo.getGrade().equals("F")))) {

                    tempFilter.add(spojo);
                }
                if (((spojo.getTakenSemester() < uptoSemester)
                        && (spojo.getTakenSemester() % 2 == uptoSemester % 2)
                        && !(spojo.getGrade().equals("N/A") || spojo.getGrade().equals("F")))) {

                    passedCourses.add(spojo);
                }
            }

            //finding if the course have been passed
            for (SyllabusPOJO filtered : tempFilter) {

                boolean found = false;
                for (SyllabusPOJO passed : passedCourses) {
                    if (filtered.getCourseCode().equals(passed.getCourseCode())) {
                        found = true;
                    }
                }
                if (!found) {
                    //checking if the item already added
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
            //****************************************************************
        }
        return finalFilter;
    }

    /**
     *
     * @param uptoSemester
     * @return
     */
    public String getStudentDroppedCourses(int uptoSemester) {
        List<SyllabusPOJO> courses = getStudentDroppedCoursesAsEntity(uptoSemester);

        String jsonListString = JsonConvertion.objectListToJsonString(courses);

        return jsonListString;
    }

    public List<SyllabusPOJO> getStudentPendingCoursesAsEntity(int uptoSemester) {
        List<SyllabusPOJO> pendingCourses = new ArrayList<SyllabusPOJO>();

        if (uptoSemester > 2) {

            //getting all courses before uptoSemester from syllabus
            //here (uptoSemester-2) because we need to search from 2 semester backward
            List<SyllabusPOJO> allSyllabusCourses = getStudentSyllabusAll(uptoSemester - 2);

            //getting all registered courses before uptoSemester from courseRegistration
            List<SyllabusPOJO> allCourseRegistrations = getStudentRegisteredCoursesAll(uptoSemester - 2);

            //searching for the pending courses comparing allSyllabusCoursesModFilter and allCourseRegistrationsModFilter
            for (SyllabusPOJO syl : allSyllabusCourses) {
                if (syl.getOfferingSemester() % 2 == uptoSemester % 2) {
                    //System.out.println("syllabus: "+syl.getCourseCode());
                    boolean found = false;
                    for (SyllabusPOJO reg : allCourseRegistrations) {
                        if (reg.getTakenSemester() % 2 == uptoSemester % 2) {
                            // System.out.println("registration: "+reg.getCourseCode());
                            if (syl.getCourseCode().equals(reg.getCourseCode())) {
                                found = true;
                            }
                        }
                    }
                    if (!found) {
                        //System.out.println("pending");
                        //System.out.println(syl.getCourseCode());
                        pendingCourses.add(syl);
                    }
                }
            }
            //****************************************************************
        }
        return pendingCourses;
    }

    /**
     *
     * @param day
     * @return
     */
    public List<ClassRoutinePOJO> getStudentRoutine(String day) {

        List<ClassRoutinePOJO> myRoutines = new ArrayList<ClassRoutinePOJO>();
        //getting the registered courses for the current semester
        List<SyllabusPOJO> registrations = getStudentRegisteredCoursesAsEntity(getStudentCurrentSemester());
        if (!registrations.isEmpty()) {
            int year = registrations.get(0).getAttendYear();
            int semester;
            if (registrations.get(0).getTakenSemester() % 2 == 1) {
                semester = 1;
            } else {
                semester = 2;
            }

            Session session = sessionFactory.openSession();
            session.beginTransaction();

            //getting all timeSlots of the day
            String hql = "FROM TimeSlot WHERE day= :day";
            Query query = session.createQuery(hql);
            query.setString("day", day);

            List<TimeSlot> timeSlots = (List<TimeSlot>) query.list();

            //getting all classRoutines of every timeSlot with Department year and semester match
            for (TimeSlot t : timeSlots) {

                List<ClassRoutine> routines = (List<ClassRoutine>) t.getClassRoutineCollection();

                if (!routines.isEmpty()) {

                    //System.out.println("slot: " + t.getDay() + " " + t.getStart() + "-" + t.getEnd());
                    for (ClassRoutine c : routines) {
                        if (c.getYear() == year && c.getSemester() == semester) {
                            //System.out.println("class: " + c.getSyllabusIdFk().getCourseIdFk().getCourseCode());

                            for (SyllabusPOJO reg : registrations) {

                                String routineCourseCode = c.getSyllabusIdFk().getCourseIdFk().getCourseCode();
                                if (reg.getCourseCode().equals(routineCourseCode)) {
                                    //then push data to ClassRoutinePOJO
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
            session.close();

        }

        //finally return the POJO collection
        return myRoutines;
    }

    /**
     *
     * @return
     */
    public double getCGPA() {
        List<SyllabusPOJO> cummilativeCourses = getStudentRegisteredCoursesAll(getStudentTotalSemester());

        double cgpa = CgpaCalculation.getGradePointOfSemester(cummilativeCourses);

        return cgpa;
    }

    /**
     *
     * @return
     */
    public double getTotalCredits() {
        double totalCredit;
        totalCredit = CgpaCalculation.getTotalCreditOfSemester(getStudentSyllabusAll(getStudentTotalSemester()));
        return totalCredit;
    }

    public double getCreditsCompleted() {
        double completedCredits;
        completedCredits = CgpaCalculation.getPassedCreditOfSemester(getStudentRegisteredCoursesAll(getStudentCurrentSemester()));
        return completedCredits;
    }

    public int getTotalCourses() {
        int totalcourses;
        totalcourses = getStudentSyllabusAll(getStudentTotalSemester()).size();
        return totalcourses;
    }

    public int getCompletedCourses() {
        int completedcourses = 0;
        List<SyllabusPOJO> all = getStudentSyllabusAll(getStudentTotalSemester());
        for (SyllabusPOJO spojo : all) {
            if (!spojo.getGrade().equals("F") && !spojo.getGrade().equals("N/A")) {
                completedcourses += 1;
            }
        }
        return completedcourses;
    }

    /**
     * *****************************************************************************************************
     * *************** SAVE AND MODIFICATION OPERATIONS
     * ************************
     * *****************************************************************************************************
     */
    /**
     *
     * @param personalInfo
     * @return
     */
    public boolean savePersonalInfo(PersonalInfo personalInfo) {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            session.saveOrUpdate(personalInfo);
            session.getTransaction().commit();
            session.close();

        } catch (Exception e) {
            return false;
        }

        return true;
    }

    /**
     *
     * @param jsonString
     * @param semester
     * @return
     */
    public boolean doStudentCourseRegistration(String jsonString, int semester) {

        // System.out.println("input wish: " + jsonString);
        JsonParser parser = new JsonParser(); // declaring jsonparser
        JsonElement jsonElement = parser.parse(jsonString); // getting the parsed jsonString as jsonElement
        JsonArray wishList = jsonElement.getAsJsonArray(); // converting the element as jsonArray

        //System.out.println("converted wish: " + wishList);
        //getting the currently registeres courses befor commit change
        String currentCoursesJson = getStudentRegisteredCourses(semester);

        //System.out.println("input target: " + currentCoursesJson);
        JsonParser parser1 = new JsonParser();
        JsonElement jsonElement1 = parser1.parse(currentCoursesJson);
        JsonArray targetList = jsonElement1.getAsJsonArray();

        //System.out.println("converted target: " + targetList);
        //generating the course taken year value from the semester
        String batchSession = getStudentSessiontName();
        int batchYear = Integer.parseInt(batchSession.substring(0, 4));
        String formatSemester = FormatService.formatSemesterNumber(semester);
        int academicYear = Integer.parseInt(formatSemester.substring(0, 1));
        int attendedYear = batchYear + academicYear;

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //---------------------------------------------------------------------------------------
        Gson gson = new Gson();
        CourseRegistration courseRegistration;

        if (wishList.isJsonNull() && targetList.isJsonNull()) {
            //System.out.println("testcase 1");
            return true;
        } else if (!wishList.isJsonNull() && targetList.isJsonNull()) {
            for (int i = 0; i < wishList.size(); i++) {
                String jsonObjectString = wishList.get(i).toString();
                SyllabusPOJO addSyllabusPojo = gson.fromJson(jsonObjectString, SyllabusPOJO.class);

                courseRegistration = new CourseRegistration();
                courseRegistration.setStudentInfoIdFk(studentInfo);
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
                    courseRegistration.setStudentInfoIdFk(studentInfo);
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

        //---------------------------------------------------------------------------------------
        session.getTransaction().commit();
        session.close();

        return true;
    }

    /**
     *
     * @param jsonString
     * @param semester
     * @return
     */
    public boolean doStudentResultUpdate(String jsonString, int semester) {

        boolean status = false;
        //System.out.println("method accesses ! \n" + jsonString);
        JsonParser parser = new JsonParser(); // declaring jsonparser
        JsonElement jsonElement = parser.parse(jsonString); // getting the parsed jsonString as jsonElement
        JsonArray gradeArray = jsonElement.getAsJsonArray();

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            //----------------------------------------------------------------------
            Gson gson = new Gson();
            for (int i = 0; i < gradeArray.size(); i++) {

                String syllabusPojoString = gradeArray.get(i).toString();
                SyllabusPOJO syllabusPojo = gson.fromJson(syllabusPojoString, SyllabusPOJO.class);

                CourseRegistration registration = (CourseRegistration) session.get(CourseRegistration.class,
                        syllabusPojo.getCourseRegistrationId());
                registration.setGrade(syllabusPojo.getGrade());

                //verifying that this grade update doesnt contradict with other semesters' intake of the same course
                List<SyllabusPOJO> allRegistered = getStudentRegisteredCoursesAll(getStudentCurrentSemester());
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
            //----------------------------------------------------------------------
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            status = false;
        } finally {
            return status;
        }

    }

    /**
     *
     * @return
     */
    public int getStudentCurrentSemester() {

        try {
            Session session = sessionFactory.openSession();
            session.beginTransaction();

            String hql = "select max(attendSemester) FROM CourseRegistration WHERE studentInfoIdFk = :id";
            Query query = session.createQuery(hql);
            query.setInteger("id", studentInfo.getStudentInfoId());
            List maxReg = query.list();

            session.getTransaction().commit();
            session.close();

            int maxVal; // assignging 1 so that current semester is 1 by default
            if (!maxReg.isEmpty()) {
                maxVal = (Integer) maxReg.get(0);
            } else {
                maxVal = 1;
                // System.out.println("this is it");
            }
            return maxVal;

        } catch (Exception e) {
            return 1;
        }

    }

    /**
     *
     * @return
     */
    public String getStudentProgramName() {
        String programName = studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getDegreeTypeName()
                + " (" + studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getDegreeCategory() + ")";
        return programName;
    }

    public String getStudentDepartmentName() {
        String departmentName = studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDeptIdFk().getDeptName();
        return departmentName;
    }

    public String getStudentSchoolName() {
        String schoolName = studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk()
                .getDeptIdFk().getSchoolIdFk().getSchoolName();
        return schoolName;
    }

    public int getStudentTotalSemester() {
        int totalSemester = studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getTotalSemester();

        return totalSemester;
    }

    public String getStudentSessiontName() {
        String sessionName = studentInfo.getStudentBatchIdFk().getSession();
        return sessionName;
    }

    /**
     * *****************************************************************************************************
     * *************** SETTER & GETTER ************************
     * *****************************************************************************************************
     */
    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    /**
     * @return the studentInfo
     */
    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    /**
     * @param studentInfo the studentInfo to set
     */
    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

}
 //THIS IS AN UNUSED METHOD-- UNCOMPLETE
//    public int getMaxSemesterValueOfCourseRegistration() {
//
////        List<CourseRegistration> courseRegistrations = getStudentRegisteredCoursesAsEntity(1);
////        //We need to use Comparator interface when we want to compare
////        //objects on different attributes,and this comaparator object
////        //will be later passed to Collection.max() method to get
////        //the maximum value among the Objects
////        Comparator<CourseRegistration> cmp = new Comparator<CourseRegistration>() {
////            @Override
////            public int compare(CourseRegistration o1, CourseRegistration o2) {
////                int i = o1.getAttendSemester();
////                int j = o2.getAttendSemester();
////                return i - j;
////            }
//        };
//Here first parameter is whose maximum element is to be determined
//and second parameter is the Comparator object we created earlier.
//This method returns the Syllabus object which contains the maximum
//semester value
//        CourseRegistration maxSemesterCourseRegistration = Collections.max(courseRegistrations, cmp);
//        int maxSem = maxSemesterCourseRegistration.getAttendSemester();
//
//        return maxSem;
//    }
