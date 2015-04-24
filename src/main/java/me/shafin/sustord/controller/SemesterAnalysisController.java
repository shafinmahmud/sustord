/*
 */
package me.shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import me.shafin.sustord.service.AcademicInfoService;

/**
 *
 * @author SHAFIN
 */
public class SemesterAnalysisController extends HttpServlet{

    private AcademicInfoService academicService;

    public SemesterAnalysisController(String registrationNo) {
        try {
            
            this.academicService = AcademicInfoService.forSingletonAcademicInfoService(registrationNo);
        } catch (Exception ex) {
            Logger.getLogger(CurriculumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTotalAcademicSemester() {
        return academicService.getTotalAcadmicSemester();
    }
}
