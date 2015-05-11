/*
 */
package me.shafin.sustord.service;

/**
 *
 * @author SHAFIN
 */
public class AcademicInfoService extends StudentIdentityService{

    public AcademicInfoService() throws Exception{
         super(StudentIdentityService.forSingletonIdentityService());
    }

    /* Academic Information  */
    public String getStudentProgramName() {
         String programName = studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getDegreeTypeName()
                + " (" + studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getDegreeCategory() + ")";
        return programName;
    }
    
    public String getStudentDepartmentName() {
        return studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDeptIdFk().getDeptName();
    }

    public String getStudentSchoolName() {
        return studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDeptIdFk().getSchoolIdFk().getSchoolName();
    }

    public String getStudentAcademicSession() {
        return studentInfo.getStudentBatchIdFk().getSession();
    }
    
    public int getTotalAcadmicSemester(){
        return studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getTotalSemester();
    }

}
