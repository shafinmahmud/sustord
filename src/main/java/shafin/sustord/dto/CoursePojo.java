/*
 */
package shafin.sustord.dto;

/**
 *
 * @author SHAFIN
 */
public class CoursePojo {
    
    private String courseCode;
    private String courseTitle;
    private String offeringDeptCode;
    private String acceptingDeptCode;
    private double credit;
    private boolean theoryOrLab;

    /**
     * @return the courseCode
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCode the courseCode to set
     */
    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * @return the courseTitle
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     * @param courseTitle the courseTitle to set
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * @return the offeringDeptCode
     */
    public String getOfferingDeptCode() {
        return offeringDeptCode;
    }

    /**
     * @param offeringDeptCode the offeringDeptCode to set
     */
    public void setOfferingDeptCode(String offeringDeptCode) {
        this.offeringDeptCode = offeringDeptCode;
    }

    /**
     * @return the acceptingDeptCode
     */
    public String getAcceptingDeptCode() {
        return acceptingDeptCode;
    }

    /**
     * @param acceptingDeptCode the acceptingDeptCode to set
     */
    public void setAcceptingDeptCode(String acceptingDeptCode) {
        this.acceptingDeptCode = acceptingDeptCode;
    }

    /**
     * @return the credit
     */
    public double getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * @return the theoryOrLab
     */
    public boolean isTheoryOrLab() {
        return theoryOrLab;
    }

    /**
     * @param theoryOrLab the theoryOrLab to set
     */
    public void setTheoryOrLab(boolean theoryOrLab) {
        this.theoryOrLab = theoryOrLab;
    }

    @Override
    public String toString() {
        return "CoursePojo{" + "courseCode=" + courseCode + ", courseTitle=" + courseTitle + ", offeringDeptCode=" + offeringDeptCode + ", acceptingDeptCode=" + acceptingDeptCode + ", credit=" + credit + ", theoryOrLab=" + theoryOrLab + '}';
    }
    
    
}
