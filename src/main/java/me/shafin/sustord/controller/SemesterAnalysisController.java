/*
 */
package me.shafin.sustord.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.pojo.StudentGradeRankedPojo;
import me.shafin.sustord.service.AcademicInfoService;
import me.shafin.sustord.service.GradeService;

/**
 *
 * @author SHAFIN
 */
public class SemesterAnalysisController{

    private AcademicInfoService academicService;
    private GradeService gradeService;

    public SemesterAnalysisController() {
        try {          
            this.academicService = new AcademicInfoService();
            this.gradeService = new GradeService();
        } catch (Exception ex) {
            Logger.getLogger(CurriculumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getTotalAcademicSemester() {
        return academicService.getTotalAcadmicSemester();
    }
    
    public List<StudentGradeRankedPojo> getSemesterRankList(int semester){
        return gradeService.getSemesterRankList(semester);
    }
}
