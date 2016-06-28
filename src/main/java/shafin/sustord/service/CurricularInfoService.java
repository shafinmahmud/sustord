/*
 */
package shafin.sustord.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import shafin.sustord.bean.OptionalCoursePojo;
import shafin.sustord.bean.SyllabusCoursePojo;
import shafin.sustord.dao.PrerequisiteDao;
import shafin.sustord.dao.SyllabusDao;
import shafin.sustord.model.Prerequisite;
import shafin.sustord.model.Syllabus;

/**
 *
 * @author SHAFIN
 */
public class CurricularInfoService extends StudentIdentityService{
    
    public CurricularInfoService(String registrationNo) throws Exception{
        super(StudentIdentityService.forSingletonIdentityService(registrationNo));
    }
 
    
     public List<SyllabusCoursePojo> getAcademicCoursesOfSemester(int semester) {
        try {
            List<SyllabusCoursePojo> syllabusCoursePojoList = new ArrayList<SyllabusCoursePojo>();
            List<Syllabus> syllabusList = SyllabusDao.getSyllabusObjectsOfSemester(studentInfo.getStudentBatchIdFk()
                    .getStudentBatchId(), semester);

            for (Syllabus syllabus : syllabusList) {
                SyllabusCoursePojo coursePojo = new SyllabusCoursePojo();
                coursePojo.setCourseCode(syllabus.getCourseIdFk().getCourseCode());
                coursePojo.setCourseTitle(syllabus.getCourseIdFk().getTitle());
                coursePojo.setOfferingDeptCode(syllabus.getOfferingDeptIdFk().getDeptCode());
                coursePojo.setAcceptingDeptCode(syllabus.getAcceptingDeptIdFk().getDeptCode());
                coursePojo.setCredit(syllabus.getCourseIdFk().getCredit());
                coursePojo.setCourseHoursPerWeek(syllabus.getHrsWeek());

                //deciding is it a theory or lab course
                if (syllabus.getCourseIdFk().getTheoryOrLab() == 1) {
                    coursePojo.setTheoryOrLab(true);
                } else {
                    coursePojo.setTheoryOrLab(false);
                }

                //getting the prerequsite courses
                String prerequisiteString = "";
                List<Prerequisite> prerequisites = PrerequisiteDao.getPrerequisiteObjectsList(syllabus.getSyllabusId());
                if (!prerequisites.isEmpty()) {
                    for (Prerequisite p : prerequisites) {
                        prerequisiteString += p.getCourseIdFk().getCourseCode() + " ";
                    }
                }
                
                coursePojo.setPrerequisite(prerequisiteString);
                syllabusCoursePojoList.add(coursePojo);
            }

            return syllabusCoursePojoList;

        } catch (Exception ex) {
            Logger.getLogger(AcademicInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public List<OptionalCoursePojo> getOptionalCoursesOfSyllabus(){
        try {
            List<OptionalCoursePojo> optionalPojoList = new ArrayList<OptionalCoursePojo>();
            List<Syllabus> syllabusList = SyllabusDao.getSyllabusObjectsOfSemester(studentInfo.getStudentBatchIdFk()
                    .getStudentBatchId(), 0);//CAUSE optional courses are stored in the syllabus table with semester value 0
                                             //THIS is a MAJOR Database Design failure NEED to be addressed

            for (Syllabus syllabus : syllabusList) {
                OptionalCoursePojo optionalPojo = new OptionalCoursePojo();
                optionalPojo.setCourseCode(syllabus.getCourseIdFk().getCourseCode());
                optionalPojo.setCourseTitle(syllabus.getCourseIdFk().getTitle());
                optionalPojo.setOfferingDeptCode(syllabus.getOfferingDeptIdFk().getDeptCode());
                optionalPojo.setAcceptingDeptCode(syllabus.getAcceptingDeptIdFk().getDeptCode());
                optionalPojo.setCredit(syllabus.getCourseIdFk().getCredit());

                //deciding is it a theory or lab course
                if (syllabus.getCourseIdFk().getTheoryOrLab() == 1) {
                    optionalPojo.setTheoryOrLab(true);
                } else {
                    optionalPojo.setTheoryOrLab(false);
                }

                //getting the prerequsite courses
                String prerequisiteString = "";
                List<Prerequisite> prerequisites = PrerequisiteDao.getPrerequisiteObjectsList(syllabus.getSyllabusId());
                if (!prerequisites.isEmpty()) {
                    for (Prerequisite p : prerequisites) {
                        prerequisiteString += p.getCourseIdFk().getCourseCode() + " ";
                    }
                }
                
                optionalPojo.setPrerequisite(prerequisiteString);
                optionalPojoList.add(optionalPojo);
            }

            return optionalPojoList;

        } catch (Exception ex) {
            Logger.getLogger(AcademicInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return new ArrayList<OptionalCoursePojo>();
        }
    }
}
