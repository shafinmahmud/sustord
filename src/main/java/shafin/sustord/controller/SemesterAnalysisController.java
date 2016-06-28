/*
 */
package shafin.sustord.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;

import shafin.sustord.pojo.StudentGradeRankedPojo;
import shafin.sustord.service.AcademicInfoService;
import shafin.sustord.service.GradeService;

/**
 *
 * @author SHAFIN
 */
public class SemesterAnalysisController extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private AcademicInfoService academicService;
    private GradeService gradeService;

    public SemesterAnalysisController(String registrationNo) {
        try {          
            this.academicService = new AcademicInfoService(registrationNo);
            this.gradeService = new GradeService(registrationNo);
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
