/*
 */
package me.shafin.sustord.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.shafin.sustord.bean.SyllabusPOJO;

/**
 *
 * @author SHAFIN
 */
public class StatSemesterPojo {
    
    private Map<String, Integer> semesterGradeDistributionMap;
    private List<SyllabusPOJO> courseStat;
    private List<StudentGradeRankedPojo> studentRankedList;


    public Map<String, Integer> getSemesterGradeDistributionMap() {
        return semesterGradeDistributionMap;
    }

   
    public void setSemesterGradeDistributionMap(int Ap, int A, int Am,
            int Bp, int B, int Bm,
            int Cp, int C, int Cm, int F) {
        
        Map<String, Integer> map = new HashMap<String, Integer>();
        
        map.put("Ap", Ap);
        map.put("A", A);
        map.put("Am", Am);
        map.put("Bp", Bp);
        map.put("B", B);
        map.put("Bm", Bm);
        map.put("Cp", Cp);
        map.put("C", C);
        map.put("Cm", Cm);
        map.put("F", F);
        this.semesterGradeDistributionMap = map;
    }


    public List<SyllabusPOJO> getCourseStat() {
        return courseStat;
    }

  
    public void setCourseStat(List<SyllabusPOJO> courseStat) {
        this.courseStat = courseStat;
    }

 
    public List<StudentGradeRankedPojo> getStudentRankedList() {
        return studentRankedList;
    }

    
    public void setStudentRankedList(List<StudentGradeRankedPojo> studentRankedList) {
        this.studentRankedList = studentRankedList;
    }
}
