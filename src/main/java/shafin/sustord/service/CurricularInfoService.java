/*
 */
package shafin.sustord.service;


/**
 *
 * @author SHAFIN
 */
public class CurricularInfoService extends StudentService{
    
    public CurricularInfoService(String registrationNo) throws Exception{
        super(registrationNo);
    }
 
    
     //public List<SyllabusCoursePojo> getAcademicCoursesOfSemester(int semester) {
       /* try {
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
        }*/
    	// return null;
   // }
    
   // public List<OptionalCoursePojo> getOptionalCoursesOfSyllabus(){
       /* try {
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
        }*/
    //	return null;
   // }
}
