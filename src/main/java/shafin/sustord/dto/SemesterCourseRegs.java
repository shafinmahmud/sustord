package shafin.sustord.dto;

import java.util.List;

public class SemesterCourseRegs {

	private String semesterName;
	private List<Course> courses;
	private double totalCredits;
	private double totalHoursWeek;

	public SemesterCourseRegs() {
	}

	public String getSemesterName() {
		return semesterName;
	}

	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	public double getTotalCredits() {
		float total = 0.0f;
		for(Course course:courses){
			total += course.getCredit();
		}
		totalCredits = total;
		return totalCredits;
	}

	public void setTotalCredits(double totalCredits) {
		this.totalCredits = totalCredits;
	}

	public double getTotalHoursWeek() {
		float total = 0.0f;
		for(Course course:courses){
			total += course.getHoursWeek();
		}
		totalHoursWeek = total;
		return totalHoursWeek;
	}

	public void setTotalHoursWeek(double totalHoursWeek) {
		this.totalHoursWeek = totalHoursWeek;
	}

}
