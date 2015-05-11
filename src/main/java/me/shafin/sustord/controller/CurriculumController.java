/*
 */
package me.shafin.sustord.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.bean.OptionalCoursePojo;
import me.shafin.sustord.bean.SyllabusCoursePojo;
import me.shafin.sustord.service.AcademicInfoService;
import me.shafin.sustord.service.CurricularInfoService;

/**
 *
 * @author SHAFIN
 */
public class CurriculumController {

    private AcademicInfoService academicInfoService;
    private CurricularInfoService curricularInfoService;

    public CurriculumController(String registrationNo) {
        try {
            this.academicInfoService = new AcademicInfoService(registrationNo);
            this.curricularInfoService = new CurricularInfoService(registrationNo);
        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public int getTotalAcademicSemester(){
        return academicInfoService.getTotalAcadmicSemester();
    }
    
    public List<SyllabusCoursePojo> getAcademicSyllabus(int semester) {
        return curricularInfoService.getAcademicCoursesOfSemester(semester);
    }
    
    public List<OptionalCoursePojo> getOptionalCourses(){
        return curricularInfoService.getOptionalCoursesOfSyllabus();
    }
}
