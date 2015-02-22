/*
 */
package me.shafin.sustord.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author SHAFIN
 */
public class SemesterStatPOJO {
    
    private Map semesterGradeDistributionMap;
    private List<SyllabusPOJO> courseStat;

    /**
     * @return the semesterGradeDistributionMap
     */
    public Map getSemesterGradeDistributionMap() {
        return semesterGradeDistributionMap;
    }

    /**
     * @param Ap
     * @param A
     * @param Am
     * @param Bp
     * @param B
     * @param Bm
     * @param Cp
     * @param C
     * @param Cm
     * @param F
     */
    public void setSemesterGradeDistributionMap(int Ap, int A, int Am,
            int Bp, int B, int Bm,
            int Cp, int C, int Cm, int F) {
        
        Map map = new HashMap();
        
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

    /**
     * @return the courseStat
     */
    public List<SyllabusPOJO> getCourseStat() {
        return courseStat;
    }

    /**
     * @param courseStat the courseStat to set
     */
    public void setCourseStat(List<SyllabusPOJO> courseStat) {
        this.courseStat = courseStat;
    }
}
