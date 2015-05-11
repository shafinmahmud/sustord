package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.service.AcademicInfoService;
import me.shafin.sustord.service.PersonalInfoService;

/**
 *
 * @author SHAFIN
 */
public class UserProfileController {

    private PersonalInfoService personalInfoService;
    private AcademicInfoService academicInfoService;

    public UserProfileController() {
        try {
            this.personalInfoService = new PersonalInfoService();
            this.academicInfoService = new AcademicInfoService();
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getStudentName() {
        return personalInfoService.getStudentName();
    }

    public String getFatherName() {
        return personalInfoService.getFatherName();
    }

    public String getMotherName() {
        return personalInfoService.getMotherName();
    }

    public String getPresentAddress() {
        return personalInfoService.getPresentAddress();
    }

    public String getPermanentAddress() {
        return personalInfoService.getPermanentAddress();
    }

    public String getPhone() {
        return personalInfoService.getPhone();
    }

    public String getEmail() {
        return personalInfoService.getEmail();
    }

    public String getDob() {
        return personalInfoService.getDob();
    }

    public String getSex() {
        return personalInfoService.getSex();
    }

    public String getReligion() {
        return personalInfoService.getReligion();
    }

    public String getNationality() {
        return personalInfoService.getNationality();
    }

    public String getMaritalStatus() {
        return personalInfoService.getMaritalStatus();
    }

    public String getBloodGroup() {
        return personalInfoService.getBloodGroup();
    }

    public String getStudentPhotoUrl() {
        return personalInfoService.getStudentPhotoUrl();
    }

    /* Academic Information  */
    public String getStudentProgramName() {
        return academicInfoService.getStudentProgramName();
    }

    public String getStudentDepartmentName() {
        return academicInfoService.getStudentDepartmentName();
    }

    public String getStudentSchoolName() {
        return academicInfoService.getStudentSchoolName();
    }

    public String getStudentAcademicSession() {
        return academicInfoService.getStudentAcademicSession();
    }
}
