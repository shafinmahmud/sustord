package sm.sustord.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;

import sm.sustord.pojo.LoginMessage;
import sm.sustord.service.AdminLoginService;
import sm.sustord.service.StudentLoginService;
import sm.sustord.service.StudentService;

import org.hibernate.HibernateException;

/**
 *
 * @author SHAFIN
 */
public class LoginController {

    public static LoginMessage authencateStudent(String registrationNo, String password) {
        LoginMessage message = new LoginMessage();
        try {
            StudentLoginService loginService = new StudentLoginService(registrationNo);
            if (loginService.verifyRegistrationNo()) {
                message.setRequestedIdValid(true);
                if (loginService.verifyPassword(password)) {
                    message.setRequestedPasswordValid(true);
                    message.setMessageTitle("Verified");
                    message.setMessageBody("Login information is correct. Verification successful.");
                    message.setRequestedId(registrationNo);
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

    public static LoginMessage authencateBatchAdmin(String adminId, String password) {
        LoginMessage message = new LoginMessage();
        try {
            AdminLoginService loginService = new AdminLoginService(adminId);
            if (loginService.verifyRegistrationNo()) {
                message.setRequestedIdValid(true);
                if (loginService.verifyPassword(password)) {
                    message.setRequestedPasswordValid(true);
                    message.setMessageTitle("Verified");
                    message.setMessageBody("Login information is correct. Verification successful.");
                    message.setRequestedId(adminId);
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
    
    public static StudentService temporarySupport() throws SQLException {

        StudentService service = new StudentService();
        return service;

    }
}
