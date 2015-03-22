
package me.shafin.sustord.service;

import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.StudentInfo;

/**
 *
 * @author SHAFIN
 */
public class LoginService {
    
    
    public static boolean verifyRegistrationNo(String registrationNo) throws Exception{ 
       StudentInfo studentInfo = StudentInfoDao.getStudentInfoObject(registrationNo);
        return studentInfo != null;
    }
    
    public static boolean verifyPassword(String registrationNo, String password) throws Exception{ 
       StudentInfo studentInfo = StudentInfoDao.getStudentInfoObject(registrationNo);
       String existingPassword = studentInfo.getPassword();
       return password.equals(existingPassword);
    }
}
