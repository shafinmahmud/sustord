package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.service.StudentInfoService;

/**
 *
 * @author SHAFIN
 */
public class UserHomeController {

    private StudentInfoService informationService;

    public UserHomeController(String registrationNo) {
        try {
            this.informationService = StudentInfoService.forSingletonStudentInfoService(registrationNo);
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getStudentName() {
        return informationService.getStudentName();
    }

    public String getStudentPhotoUrl() {
        return informationService.getStudentPhotoUrl();
    }
}
