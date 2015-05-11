/*
 */
package me.shafin.sustord.service;

import java.io.PrintWriter;
import java.io.StringWriter;
import me.shafin.sustord.pojo.LoginMessage;
import me.shafin.sustord.utility.ServiceDispatcher;
import org.hibernate.HibernateException;

/**
 *
 * @author SHAFIN
 */
public class AdminLoginService extends AdminIdentityService{
    private static final String NULL_RESPONSE = " - ";
    private static final String ERROR_RESPONSE = ":/";
    
     public AdminLoginService(String adminNo) throws Exception {
        super(AdminIdentityService.forSingletonIdentityService(adminNo));
    }
    
     public LoginMessage authencateBatchAdmin(String adminId, String password) {
        LoginMessage message = new LoginMessage();
        try {
            
            if (adminInfo != null) {
                message.setRequestedIdValid(true);
                if (password.equals(adminInfo.getPassword())) {
                    message.setRequestedPasswordValid(true);
                    message.setMessageTitle("Verified");
                    message.setMessageBody("Login information is correct. Verification successful.");
                    message.setRequestedId(adminId);
                    
                    //setting Admin ID to Dispatcher for available in Service method
                    ServiceDispatcher.setLoggedAdminID(adminId);
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
    
}
