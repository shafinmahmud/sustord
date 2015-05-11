package me.shafin.sustord.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import me.shafin.sustord.pojo.LoginMessage;
import me.shafin.sustord.utility.ServiceDispatcher;
import org.hibernate.HibernateException;

/**
 *
 * @author SHAFIN
 */
public class StudentLoginService extends StudentIdentityService{

    private static final String NULL_RESPONSE = " - ";
    private static final String ERROR_RESPONSE = ":/";
    
     public StudentLoginService(String registrationNo) throws Exception {
        super(StudentIdentityService.forSingletonIdentityService());
        //super(StudentIdentityService.forProtypeIdentityService(registrationNo));
    }
    
    public LoginMessage authencateStudent(String registrationNo, String password) {
        LoginMessage message = new LoginMessage();
        try {
            if (studentInfo != null) {
                message.setRequestedIdValid(true);     
                
                if (password.equals(studentInfo.getPassword())) {
                    message.setRequestedPasswordValid(true);
                    message.setMessageTitle("Verified");
                    message.setMessageBody("Login information is correct. Verification successful.");
                    message.setRequestedId(registrationNo);
                    
                    //setting Student ID to Dispatcher for available in Service method
                    ServiceDispatcher.setLoggedInStudentID(registrationNo);
                } else {
                    message.setRequestedPasswordValid(false);
                    message.setMessageTitle("Access denied");
                    message.setMessageBody("Provided password is wrong.");
                }
            } else {
                message.setRequestedIdValid(false);
                message.setMessageTitle("Unknown ID");
                message.setMessageBody("Provided Registration No is invalid.");
            }

        } catch (HibernateException ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String errorString = sw.toString(); // stack trace as a string  

            message.setMessageTitle("Hibernate Exception");
            message.setMessageBody(errorString);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String errorString = sw.toString(); // stack trace as a string  

            message.setMessageTitle("Server Exception");
            message.setMessageBody(errorString);
        }

        return message;
    }

   
    
    public StudentService temporarySupport() throws SQLException {

        StudentService service = new StudentService();
        return service;

    }
}
