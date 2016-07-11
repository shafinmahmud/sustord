package shafin.sustord.dto;

public class Course {
	
	private String courseCode;
    private String courseTitle;
    private String offeringDeptCode;
    private String acceptingDeptCode;
    private String offeringSemester;
    private String courseType;
    private double credit;
    private double hoursWeek;
    private boolean theoryOrLab;
    private String remark;
    
    public Course() {
		
	}

	public String getCourseCode() {
		return courseCode;
	}

	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	public String getCourseTitle() {
		return courseTitle;
	}

	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public String getOfferingDeptCode() {
		return offeringDeptCode;
	}

	public void setOfferingDeptCode(String offeringDeptCode) {
		this.offeringDeptCode = offeringDeptCode;
	}

	public String getAcceptingDeptCode() {
		return acceptingDeptCode;
	}

	public void setAcceptingDeptCode(String acceptingDeptCode) {
		this.acceptingDeptCode = acceptingDeptCode;
	}

	public String getOfferingSemester() {
		return offeringSemester;
	}

	public void setOfferingSemester(String offeringSemester) {
		this.offeringSemester = offeringSemester;
	}

	public String getCourseType() {
		return courseType;
	}

	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public double getHoursWeek() {
		return hoursWeek;
	}

	public void setHoursWeek(double hoursWeek) {
		this.hoursWeek = hoursWeek;
	}

	public boolean isTheoryOrLab() {
		return theoryOrLab;
	}

	public void setTheoryOrLab(boolean theoryOrLab) {
		this.theoryOrLab = theoryOrLab;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
    
    
}
