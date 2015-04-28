/*
 */
package me.shafin.sustord.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.dao.CourseRegistrationDao;
import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.CourseRegistration;
import me.shafin.sustord.model.StudentInfo;
import me.shafin.sustord.pojo.CourseGradePojo;
import me.shafin.sustord.pojo.StudentGradePojo;
import me.shafin.sustord.pojo.StudentGradeRankedPojo;
import me.shafin.sustord.utility.GpaCalculator;
import me.shafin.sustord.utility.GradeConvertion;
import org.hibernate.HibernateException;

/**
 *
 * @author SHAFIN
 */
public class GradeService extends IdentityService {

    public GradeService(String registrationNo) throws Exception {
        super(IdentityService.forSingletonIdentityService(registrationNo));
        //super(IdentityService.forProtypeIdentityService(registrationNo));
    }

    public StudentGradePojo getStudentGradeOfSemester(Integer studentInfoId, int semester) {
        StudentGradePojo studentGrade = new StudentGradePojo();
        try {
            //get the attended courses of that semester
            List<CourseRegistration> registeredCourses;
            List<CourseGradePojo> coursePojos = new ArrayList<CourseGradePojo>();

            registeredCourses = CourseRegistrationDao.getRegisteredCourseListOfSemester(studentInfoId, semester);

            if (registeredCourses != null) {
                for (CourseRegistration c : registeredCourses) {
                    CourseGradePojo coursePojo = new CourseGradePojo();
                    coursePojo.setCourseCode(c.getSyllabusIdFk().getCourseIdFk().getCourseCode());
                    coursePojo.setCredit(c.getSyllabusIdFk().getCourseIdFk().getCredit());

                    String grade = c.getGrade();
                    if (grade != null && !grade.equals("")) {
                        coursePojo.setGradeLetter(grade);
                        coursePojo.setGradePoint(GradeConvertion.getGradePointFromGradeLetter(grade));
                    } else {
                        coursePojo.setGradeLetter("N/A");
                        coursePojo.setGradePoint(-1);
                    }
                    studentGrade.setGradePoint(semester);
                    coursePojos.add(coursePojo);
                }
            }

            //calculate the gpa
            studentGrade = GpaCalculator.getGradePoint(coursePojos);

        } catch (HibernateException ex) {
            Logger.getLogger(GradeService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GradeService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return studentGrade;
    }

    public List<StudentGradeRankedPojo> getSemesterRankList(int semester) {

        List<StudentGradeRankedPojo> rankedList = new ArrayList<StudentGradeRankedPojo>();
        try {
            //get the student list of the batch
            List<StudentInfo> studentInfos;
            studentInfos = StudentInfoDao.getStudentInfoObjects(studentInfo.getStudentBatchIdFk().getStudentBatchId());
            for (StudentInfo student : studentInfos) {
                StudentGradePojo studentGrade = getStudentGradeOfSemester(student.getStudentInfoId(), semester);
                studentGrade.setRegistrationNo(student.getRegistrationNo());
                studentGrade.setName(student.getPersonalInfo().getName());

                rankedList.add(new StudentGradeRankedPojo(studentGrade));
            }
            //sort out the students with their gpa and completed credits
            Collections.sort(rankedList, StudentGradeRankedPojo.GpaMultipliedCreditComparator);

            //assigning rank value
            for (int i = 0; i < rankedList.size(); i++) {
                rankedList.get(i).setGradeWiseRank(i + 1);
            }

        } catch (HibernateException ex) {
            Logger.getLogger(GradeService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(GradeService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rankedList;
    }

}
