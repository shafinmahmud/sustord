/*
 */
package shafin.sustord.pojo;

/**
 *
 * @author SHAFIN
 */
public class StudentGradePojo extends StudentPojo{
    
    private double completedCredits;
    private double gradePoint;

    /**
     * @return the completedCredits
     */
    public double getCompletedCredits() {
        return completedCredits;
    }

    /**
     * @param CompletedCredits the completedCredits to set
     */
    public void setCompletedCredits(double CompletedCredits) {
        this.completedCredits = CompletedCredits;
    }

    /**
     * @return the gradePoint
     */
    public double getGradePoint() {
        return gradePoint;
    }

    /**
     * @param GradePoint the gradePoint to set
     */
    public void setGradePoint(double GradePoint) {
        this.gradePoint = GradePoint;
    }
}
