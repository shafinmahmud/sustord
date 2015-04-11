package me.shafin.sustord.service;

import java.sql.SQLException;
import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.StudentInfo;
import me.shafin.sustord.utility.ServiceDispatcher;
import org.hibernate.HibernateException;

/**
 *
 * @author SHAFIN
 */
public class LoginService {

    private final StudentInfo studentInfo;
    private static final String NULL_RESPONSE = " - ";
    private static final String ERROR_RESPONSE = ":/";

    /* Constructor thats is private and get accesses through static helper method  */
    private LoginService(StudentInfo studentInfo) throws HibernateException {
        this.studentInfo = studentInfo;
    }

    /* Constructor helper for singleton LoginService  */
    public static LoginService forSingletonLoginService(String registrationNo) throws HibernateException, SQLException, Exception {
        return new LoginService(ServiceDispatcher.getSingletonStudentInfo(registrationNo));
    }

    /* Constructor helper for prototype LoginService  */
    public static LoginService forProtypeLoginService(String registrationNo) throws  HibernateException,SQLException{
        return new LoginService(StudentInfoDao.getStudentInfoObject(registrationNo));
    }

    public boolean verifyRegistrationNo() throws Exception {
        return studentInfo != null;
    }

    public boolean verifyPassword(String password) throws Exception {
        String existingPassword = studentInfo.getPassword();
        return password.equals(existingPassword);
    }
}
