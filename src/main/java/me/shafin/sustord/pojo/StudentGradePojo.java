/*
 */
package me.shafin.sustord.pojo;

/**
 *
 * @author SHAFIN
 */
public class StudentGradePojo extends StudentPojo{
    
    private double CompletedCredits;
    private double GradePoint;

    /**
     * @return the CompletedCredits
     */
    public double getCompletedCredits() {
        return CompletedCredits;
    }

    /**
     * @param CompletedCredits the CompletedCredits to set
     */
    public void setCompletedCredits(double CompletedCredits) {
        this.CompletedCredits = CompletedCredits;
    }

    /**
     * @return the GradePoint
     */
    public double getGradePoint() {
        return GradePoint;
    }

    /**
     * @param GradePoint the GradePoint to set
     */
    public void setGradePoint(double GradePoint) {
        this.GradePoint = GradePoint;
    }
}
