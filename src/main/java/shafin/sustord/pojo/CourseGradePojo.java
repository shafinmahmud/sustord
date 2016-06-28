/*
 */
package shafin.sustord.pojo;

/**
 *
 * @author SHAFIN
 */
public class CourseGradePojo extends CourseBasicPojo{
    private String gradeLetter;
    private double gradePoint;

    /**
     * @return the gradeLetter
     */
    public String getGradeLetter() {
        return gradeLetter;
    }

    /**
     * @param gradeLetter the gradeLetter to set
     */
    public void setGradeLetter(String gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    /**
     * @return the gradePoint
     */
    public double getGradePoint() {
        return gradePoint;
    }

    /**
     * @param gradePoint the gradePoint to set
     */
    public void setGradePoint(double gradePoint) {
        this.gradePoint = gradePoint;
    }
    
}
