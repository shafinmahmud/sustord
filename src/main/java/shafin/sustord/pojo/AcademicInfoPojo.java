/*
 */
package shafin.sustord.pojo;

/**
 *
 * @author SHAFIN
 */
public class AcademicInfoPojo{
    
    private String registrationNo;
	private double cgpa;
	private double creditsCompleted;
	private double totalCredit;
	private String session;
	private String program;
	private String department;
	private String school;	
	
	public AcademicInfoPojo() {
		
	}

	public String getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(String registrationNo) {
		this.registrationNo = registrationNo;
	}

	public double getCgpa() {
		return cgpa;
	}

	public void setCgpa(double cgpa) {
		this.cgpa = cgpa;
	}

	public double getCreditsCompleted() {
		return creditsCompleted;
	}

	public void setCreditsCompleted(double creditsCompleted) {
		this.creditsCompleted = creditsCompleted;
	}

	public double getTotalCredit() {
		return totalCredit;
	}

	public void setTotalCredit(double totalCredit) {
		this.totalCredit = totalCredit;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}
	
	
}
