package me.shafin.sustord.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import me.shafin.sustord.service.LoginService;

/**
 *
 * @author SHAFIN
 */
public class LoginController {

    public static String authencateStudent(String registrationNo, String password) {
        String authenticationMessage;
        try {
            LoginService loginService = LoginService.forSingletonLoginService(registrationNo);
            if(loginService.verifyRegistrationNo()){
                if(loginService.verifyPassword(password)){
                    authenticationMessage = "verified#" + registrationNo;
                }else{
                    authenticationMessage = "passError#";
                }
            }else{
                authenticationMessage = "regError#";
            }

        } catch (Exception ex) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String errorString = sw.toString(); // stack trace as a string  
            authenticationMessage = errorString;
        }

        return authenticationMessage;
    }
}
