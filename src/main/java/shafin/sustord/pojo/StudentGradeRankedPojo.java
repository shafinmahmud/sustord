/*
 */
package shafin.sustord.pojo;

import java.util.Comparator;

/**
 *
 * @author SHAFIN
 */
public class StudentGradeRankedPojo extends StudentGradePojo {

    private int gradeWiseRank;

    public StudentGradeRankedPojo(StudentGradePojo s) {
        this.setRegistrationNo(s.getRegistrationNo());
        this.setName(s.getName());
        this.setCompletedCredits(s.getCompletedCredits());
        this.setGradePoint(s.getGradePoint());
    }

    /**
     * @return the gradeWiseRank
     */
    public int getGradeWiseRank() {
        return gradeWiseRank;
    }

    /**
     * @param gradeWiseRank the gradeWiseRank to set
     */
    public void setGradeWiseRank(int gradeWiseRank) {
        this.gradeWiseRank = gradeWiseRank;
    }
    
    public static Comparator<StudentGradeRankedPojo> GpaMultipliedCreditComparator = new Comparator<StudentGradeRankedPojo>() {

                @Override
                public int compare(StudentGradeRankedPojo std1, StudentGradeRankedPojo std2) {

                    double valStd1 = std1.getGradePoint()*std1.getCompletedCredits();
                    double valStd2 = std2.getGradePoint()*std2.getCompletedCredits();
                    
                    int comp = 0;
                    //ascending order
                    if(valStd1 < valStd2){
                        comp = 1;
                    }else if(valStd1 > valStd2){
                        comp = -1;
                    }
                    return comp;
                }
            };

    @Override
    public String toString() {
        return "StudentGradeRankedPojo{" + "gradeWiseRank=" + gradeWiseRank + '}';
    }
    
    
}
