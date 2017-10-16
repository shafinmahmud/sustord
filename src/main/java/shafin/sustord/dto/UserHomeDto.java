package shafin.sustord.dto;

import java.util.List;

public class UserHomeDto {

	private String studentName;
	private String registrationNo;
	private String ppURL;
	private List<ClassRoutine> routines;
	private SemesterCourseRegs currentSemesterCourseRegistrations;

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public String getPpURL() {
		return ppURL;
	}

	public void setPpURL(String ppURL) {
		this.ppURL = ppURL;
	}

	public List<ClassRoutine> getRoutines() {
		return routines;
	}

	public void setRoutines(List<ClassRoutine> routines) {
		this.routines = routines;
	}

	public SemesterCourseRegs getCurrentSemesterCourseRegistrations() {
		return currentSemesterCourseRegistrations;
	}

	public void setCurrentSemesterCourseRegistrations(SemesterCourseRegs currentSemesterCourseRegistrations) {
		this.currentSemesterCourseRegistrations = currentSemesterCourseRegistrations;
	}

}
