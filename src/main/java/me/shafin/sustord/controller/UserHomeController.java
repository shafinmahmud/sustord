package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.service.PersonalInfoService;

/**
 *
 * @author SHAFIN
 */
public class UserHomeController {

    private PersonalInfoService informationService;

    public UserHomeController() {
        try {
            this.informationService = new PersonalInfoService();
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
