package shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import shafin.sustord.service.PersonalInfoService;

/**
 *
 * @author SHAFIN
 */
public class UserHomeController {

    private PersonalInfoService informationService;

    public UserHomeController(String registrationNo) {
        try {
            this.informationService = new PersonalInfoService(registrationNo);
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
