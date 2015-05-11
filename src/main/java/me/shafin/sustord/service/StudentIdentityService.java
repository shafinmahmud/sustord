/*
 */
package me.shafin.sustord.service;

import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.StudentInfo;
import me.shafin.sustord.utility.ServiceDispatcher;

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
    public static StudentInfo forSingletonIdentityService() throws Exception {
        return ServiceDispatcher.getSingletonStudentInfo(ServiceDispatcher.getLoggedInStudentID());
    }

    /* Constructor helper for prototype AcademicInfoService  */
    public static StudentInfo forProtypeIdentityService() throws Exception {
        return StudentInfoDao.getStudentInfoObject(ServiceDispatcher.getLoggedInStudentID());
    }
    
}
