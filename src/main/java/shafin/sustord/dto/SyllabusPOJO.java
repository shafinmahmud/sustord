/*
 */
package shafin.sustord.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author SHAFIN
 */
public class SyllabusPOJO {

    private int syllabusId;
    private int courseRegistrationId;
    private String courseCode;
    private String offerringDept;
    private String acceptingDept;
    private String courseRegistrationStatus;
    private String title;
    private double credit;
    private String prerequisite;
    private String courseDetails;
    private String grade;
    private String point;
    private int offeringSemester;
    private int takenSemester;
    private int attendYear;
    private String passedOn;
    private String status;
    private Double hrsWeek;
    private boolean theoryOrLab;

    //for statistics
    private Map<String, Integer> courseGradeDistributionMap;
    private int noOfAttendedStudent;

    /**
     * @return the courseCodeString
     */
    public String getCourseCode() {
        return courseCode;
    }

    /**
     * @param courseCodeString the courseCodeString to set
     */
    public void setCourseCode(String courseCodeString) {
        this.courseCode = courseCodeString;
    }

    /**
     * @return the offerringDept
     */
    public String getOfferringDept() {
        return offerringDept;
    }

    /**
     * @param offerringDept the offerringDept to set
     */
    public void setOfferringDept(String offerringDept) {
        this.offerringDept = offerringDept;
    }

    /**
     * @return the acceptingDept
     */
    public String getAcceptingDept() {
        return acceptingDept;
    }

    /**
     * @param acceptingDept the acceptingDept to set
     */
    public void setAcceptingDept(String acceptingDept) {
        this.acceptingDept = acceptingDept;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the prerequisite
     */
    public String getPrerequisite() {
        return prerequisite;
    }

    /**
     * @param prerequisite the prerequisite to set
     */
    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    /**
     * @return the syllabusId
     */
    public int getSyllabusId() {
        return syllabusId;
    }

    /**
     * @param syllabusId the syllabusId to set
     */
    public void setSyllabusId(int syllabusId) {
        this.syllabusId = syllabusId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the courseRegistrationId
     */
    public int getCourseRegistrationId() {
        return courseRegistrationId;
    }

    /**
     * @param courseRegistrationId the courseRegistrationId to set
     */
    public void setCourseRegistrationId(int courseRegistrationId) {
        this.courseRegistrationId = courseRegistrationId;
    }

    /**
     * @return the grade
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade the grade to set
     */
    public void setGrade(String grade) {
        this.grade = grade;

    }

    /**
     * @return the hrsWeek
     */
    public Double getHrsWeek() {
        return hrsWeek;
    }

    /**
     * @param hrsWeek the hrsWeek to set
     */
    public void setHrsWeek(Double hrsWeek) {
        this.hrsWeek = hrsWeek;
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

    /**
     * @return the point
     */
    public String getPoint() {
        return point;
    }

    /**
     * @param point the point to set
     */
    public void setPoint(String point) {
        this.point = point;
    }

    /**
     * @return the passedOn
     */
    public String getPassedOn() {
        return passedOn;
    }

    /**
     * @param passedOn the passedOn to set
     */
    public void setPassedOn(String passedOn) {
        this.passedOn = passedOn;
    }

    /**
     * @return the takenSemester
     */
    public int getTakenSemester() {
        return takenSemester;
    }

    /**
     * @param takenSemester the takenSemester to set
     */
    public void setTakenSemester(int takenSemester) {
        this.takenSemester = takenSemester;
    }

    /**
     * @return the offeringSemester
     */
    public int getOfferingSemester() {
        return offeringSemester;
    }

    /**
     * @param offeringSemester the offeringSemester to set
     */
    public void setOfferingSemester(int offeringSemester) {
        this.offeringSemester = offeringSemester;
    }

    /**
     * @return the attendYear
     */
    public int getAttendYear() {
        return attendYear;
    }

    /**
     * @param attendYear the attendYear to set
     */
    public void setAttendYear(int attendYear) {
        this.attendYear = attendYear;
    }

    /**
     * @return the courseDetails
     */
    public String getCourseDetails() {
        return courseDetails;
    }

    /**
     * @param courseDetails the courseDetails to set
     */
    public void setCourseDetails(String courseDetails) {
        this.courseDetails = courseDetails;
    }

    /**
     * @return the gradeDistributionMap
     */
    public Map<String, Integer> getCourseGradeDistributionMap() {
        return courseGradeDistributionMap;
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
    public void setCourseGradeDistributionMap(int Ap, int A, int Am,
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

        this.courseGradeDistributionMap = map;
    }

    /**
     * @return the noOfAttendedStudent
     */
    public int getNoOfAttendedStudent() {
        return noOfAttendedStudent;
    }

    /**
     * @param noOfAttendedStudent the noOfAttendedStudent to set
     */
    public void setNoOfAttendedStudent(int noOfAttendedStudent) {
        this.noOfAttendedStudent = noOfAttendedStudent;
    }

    /**
     * @return the courseRegistrationStatus
     */
    public String getCourseRegistrationStatus() {
        return courseRegistrationStatus;
    }

    /**
     * @param courseRegistrationStatus the courseRegistrationStatus to set
     */
    public void setCourseRegistrationStatus(String courseRegistrationStatus) {
        this.courseRegistrationStatus = courseRegistrationStatus;
    }

}
