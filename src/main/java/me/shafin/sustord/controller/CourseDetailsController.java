/*
 */
package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.service.AcademicInfoService;
import me.shafin.sustord.service.StudentInfoService;

/**
 *
 * @author SHAFIN
 */
public class CourseDetailsController {
     private StudentInfoService informationService;
    private AcademicInfoService academicService;

    public CourseDetailsController(String registrationNo) {
        try {
            this.informationService = StudentInfoService.forSingletonStudentInfoService(registrationNo);
            this.academicService = AcademicInfoService.forSingletonAcademicInfoService(registrationNo);
        } catch (Exception ex) {
            Logger.getLogger(CurriculumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int getTotalAcademicSemester(){
        return academicService.getTotalAcadmicSemester();
    }
}
