package me.shafin.sustord.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import me.shafin.sustord.bean.LoginMessage;
import me.shafin.sustord.service.LoginService;
import me.shafin.sustord.service.StudentService;
import org.hibernate.HibernateException;

/**
 *
 * @author SHAFIN
 */
public class LoginController {

    public static LoginMessage authencateStudent(String registrationNo, String password) {
        LoginMessage message = new LoginMessage();
        try {
            LoginService loginService = LoginService.forSingletonLoginService(registrationNo);
            if (loginService.verifyRegistrationNo()) {
                message.setREGISTRATION_NO_VALID(true);
                if (loginService.verifyPassword(password)) {
                    message.setPASSWORD_VALID(true);
                    message.setMessageHeader("Verified");
                    message.setMessageBody("Login information is correct. Verification successful.");
                    message.setRequestedRegistrationNo(registrationNo);
                } else {
                    message.setPASSWORD_VALID(false);
                    message.setMessageHeader("Access denied");
                    message.setMessageBody("Provided password is wrong.");
                }
            } else {
                message.setREGISTRATION_NO_VALID(false);
                message.setMessageHeader("Unknown ID");
                message.setMessageBody("Provided Registration No is invalid.");
            }

        } catch (HibernateException ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String errorString = sw.toString(); // stack trace as a string  

            message.setMessageHeader("Hibernate Exception");
            message.setMessageBody(errorString);
        } catch (Exception e) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String errorString = sw.toString(); // stack trace as a string  

            message.setMessageHeader("Server Exception");
            message.setMessageBody(errorString);
        }

        return message;
    }

    public static StudentService temporarySupport() throws SQLException {

        StudentService service = new StudentService();
        return service;

    }
}
