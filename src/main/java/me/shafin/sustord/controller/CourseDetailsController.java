/*
 */
package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.service.AcademicInfoService;

/**
 *
 * @author SHAFIN
 */
public class CourseDetailsController {

    private AcademicInfoService academicService;

    public CourseDetailsController() {
        try {
            this.academicService = new AcademicInfoService();
        } catch (Exception ex) {
            Logger.getLogger(CurriculumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTotalAcademicSemester() {
        return academicService.getTotalAcadmicSemester();
    }
}
