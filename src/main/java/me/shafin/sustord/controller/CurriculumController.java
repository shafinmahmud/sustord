/*
 */
package me.shafin.sustord.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.bean.OptionalCoursePojo;
import me.shafin.sustord.bean.SyllabusCoursePojo;
import me.shafin.sustord.service.AcademicInfoService;
import me.shafin.sustord.service.StudentInfoService;

/**
 *
 * @author SHAFIN
 */
public class CurriculumController {

    private StudentInfoService informationService;
    private AcademicInfoService academicService;

    public CurriculumController(String registrationNo) {
        try {
            this.informationService = StudentInfoService.forSingletonStudentInfoService(registrationNo);
            this.academicService = AcademicInfoService.forSingletonAcademicInfoService(registrationNo);
        } catch (Exception ex) {
            Logger.getLogger(CurriculumController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

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

    public int getTotalAcademicSemester(){
        return academicService.getTotalAcadmicSemester();
    }
    
    public List<SyllabusCoursePojo> getAcademicSyllabus(int semester) {
        return academicService.getAcademicCoursesOfSemester(semester);
    }
    
    public List<OptionalCoursePojo> getOptionalCourses(){
        return academicService.getOptionalCoursesOfSyllabus();
    }
}
