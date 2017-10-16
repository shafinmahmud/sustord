package shafin.sustord.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import shafin.sustord.dao.SyllabusDao;
import shafin.sustord.entity.Syllabus;
import shafin.sustord.pojo.AcademicInfoPojo;

public class StudentinfoServiceAcademic extends StudentService {

	private SyllabusDao syllabusDao;

	public StudentinfoServiceAcademic(String registrationNo) {
		super(registrationNo);
		this.syllabusDao = new SyllabusDao();
	}

	public String getStudentRegistrationNo() {
		return studentInfo.getRegistrationNo();
	}

	public String getStudentSchoolName() {
		return studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDeptIdFk().getSchoolIdFk().getSchoolName();
	}

	public String getStudentDepartmentName() {
		return studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDeptIdFk().getDeptName();
	}

	public String getStudentDepartmentCode() {
		return studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDeptIdFk().getDeptCode();
	}

	public String getStudentProgramName() {
		return studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getDegreeTypeName() + "("
				+ studentInfo.getStudentBatchIdFk().getDegreeOfferedIdFk().getDegreeIdFk().getDegreeCategory() + ")";
	}

	public String getStudentSession() {
		return studentInfo.getStudentBatchIdFk().getSession();
	}

	public double getStudentTotalCredit() {
		List<Syllabus> batchSyllabus = syllabusDao
				.findSyllabusByBatchId(studentInfo.getStudentBatchIdFk().getStudentBatchId());
		List<Syllabus> optionalSyllabus = new ArrayList<>();

		double totalCredit = 0.0f;
		for (Syllabus s : batchSyllabus) {
			if (s.getType() != null && s.getType().startsWith("OPT")) {
				optionalSyllabus.add(s);
			} else {
				totalCredit += s.getCourseIdFk().getCredit();
			}
		}

		String temp = "";
		Collections.sort(optionalSyllabus, Syllabus.getCompByType());
		for (Syllabus s : optionalSyllabus) {
			if (!temp.equals(s.getType()))
				totalCredit += s.getCourseIdFk().getCredit();
			temp = s.getType();
		}
		return totalCredit;
	}

	public AcademicInfoPojo getStudentAcademicInfo() {
		AcademicInfoPojo pojo = new AcademicInfoPojo();
		pojo.setRegistrationNo(getStudentRegistrationNo());
		pojo.setSchool(getStudentSchoolName());
		pojo.setDepartment(getStudentDepartmentName());
		pojo.setProgram(getStudentProgramName());
		pojo.setSession(getStudentSession());
		pojo.setTotalCredit(getStudentTotalCredit());
		return pojo;
	}

}
