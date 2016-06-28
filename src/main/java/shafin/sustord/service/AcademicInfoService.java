/*
 */
package shafin.sustord.service;

/**
 *
 * @author SHAFIN
 */
public class AcademicInfoService extends StudentServicea{

    public AcademicInfoService(String registrationNo) throws Exception{
         super(registrationNo);
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
