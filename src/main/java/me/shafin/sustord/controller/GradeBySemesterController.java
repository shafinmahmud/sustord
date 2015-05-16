/*
 */
package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.service.AcademicInfoService;
import me.shafin.sustord.service.PersonalInfoService;

/**
 *
 * @author SHAFIN
 */
public class GradeBySemesterController {

    private PersonalInfoService personalInfoService;
    private AcademicInfoService academicInfoService;

    public GradeBySemesterController(String registrationNo) {
        try {
            this.personalInfoService = new PersonalInfoService(registrationNo);
            this.academicInfoService = new AcademicInfoService(registrationNo);
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getStudentName() {
        return personalInfoService.getStudentName();
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
