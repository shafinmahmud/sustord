/*
 */
package sm.sustord.service;

import sm.sustord.dao.StudentInfoDao;
import sm.sustord.model.StudentInfo;
import sm.sustord.utility.ServiceDispatcher;

/**
 *
 * @author SHAFIN
 */
public class StudentIdentityService {
    public final StudentInfo studentInfo;

    /* Constructor thats is private and get accesses through static helper method  */
    public StudentIdentityService(StudentInfo studentInfo) throws Exception {
        this.studentInfo = studentInfo;
    }

    /* Constructor helper for singleton AcademicInfoService  */
    public static StudentInfo forSingletonIdentityService(String registrationNo) throws Exception {
        return ServiceDispatcher.getSingletonStudentInfo(registrationNo);
    }

    /* Constructor helper for prototype AcademicInfoService  */
    public static StudentInfo forProtypeIdentityService(String registrationNo) throws Exception {
        return StudentInfoDao.getStudentInfoObject(registrationNo);
    }
    
}
