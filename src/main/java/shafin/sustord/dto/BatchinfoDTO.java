package shafin.sustord.dto;

public class BatchinfoDTO {

	private String batchTag;
	private String session;
	private String degreeType;
	private String degreeTypeName;
	private String degreeCategory;
	private int totalSemester;
	private int maximumSemester;
	private String deptCode;
	private String deptName;
	private String school;

	public BatchinfoDTO() {
	}

	public String getBatchTag() {
		return batchTag;
	}

	public void setBatchTag(String batchTag) {
		this.batchTag = batchTag;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getDegreeType() {
		return degreeType;
	}

	public void setDegreeType(String degreeType) {
		this.degreeType = degreeType;
	}

	public String getDegreeTypeName() {
		return degreeTypeName;
	}

	public void setDegreeTypeName(String degreeTypeName) {
		this.degreeTypeName = degreeTypeName;
	}

	public String getDegreeCategory() {
		return degreeCategory;
	}

	public void setDegreeCategory(String degreeCategory) {
		this.degreeCategory = degreeCategory;
	}

	public int getTotalSemester() {
		return totalSemester;
	}

	public void setTotalSemester(int totalSemester) {
		this.totalSemester = totalSemester;
	}

	public int getMaximumSemester() {
		return maximumSemester;
	}

	public void setMaximumSemester(int maximumSemester) {
		this.maximumSemester = maximumSemester;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

}
