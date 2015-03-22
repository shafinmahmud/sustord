package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.service.StudentInfoService;

/**
 *
 * @author SHAFIN
 */
public class UserProfileController {
    
    private StudentInfoService informationService;
    
    public UserProfileController(String registrationNo) {
        try {
            this.informationService = StudentInfoService.forSingletonStudentInfoService(registrationNo);
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public String getStudentName() {
             return informationService.getStudentName();   
    }

    public String getFatherName() {      
            return informationService.getFatherName();
    }

    public String getMotherName() {       
            return informationService.getMotherName();
    }

    public String getPresentAddress() {
            return informationService.getPresentAddress();   
    }

    public String getPermanentAddress() {
            return informationService.getPermanentAddress();
    }

    public String getPhone() {
            return informationService.getPhone();
    }

    public String getEmail() {
            return informationService.getEmail();
    }

    public String getDob() {
            return informationService.getDob();
    }

    public String getSex() {
            return informationService.getSex();
    }

    public String getReligion() {
            return informationService.getReligion();
    }

    public String getNationality() {
            return informationService.getNationality();
    }

    public String getMaritalStatus() {
            return informationService.getMaritalStatus();
    }

    public String getBloodGroup() {
            return informationService.getBloodGroup();
    }

    public String getStudentPhotoUrl() {
            return informationService.getStudentPhotoUrl();
    }
    
   /* Academic Information  */
    public String getStudentProgramName() {
        return informationService.getStudentProgramName();
    }
    
    public String getStudentDepartmentName() {
        return informationService.getStudentDepartmentName();
    }

    public String getStudentSchoolName() {
        return informationService.getStudentSchoolName();
    }

    public String getStudentAcademicSession() {
        return informationService.getStudentAcademicSession();
    }
}
