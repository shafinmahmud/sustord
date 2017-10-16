package shafin.sustord.service;

import shafin.sustord.dao.PersonalInfoDao;
import shafin.sustord.entity.PersonalInfo;
import shafin.sustord.pojo.PersonalInfoPojo;

public class StudentinfoServicePersonal extends StudentService {

	private final PersonalInfoDao dao;

	public StudentinfoServicePersonal(String registrationNo) {
		super(registrationNo);
		this.dao = new PersonalInfoDao();
	}

	/* Personal Information */
	public String getStudentName() {
		String studentName = dao.findOne(this.studentInfo.getStudentInfoId()).getName();
		return studentName != null ? studentName : "";
	}
	
	public String getStudentPhotoURL(){
		String url = dao.findOne(this.studentInfo.getStudentInfoId()).getPhotoUrl();
		return url != null ? url : "";
	}

	public PersonalInfoPojo getStudentPersonalInfo() {
		PersonalInfo info = dao.findOne(this.studentInfo.getStudentInfoId());
		return wrapEntityToPojo(info);
	}
	
	public boolean saveStudentPersonalInfo(PersonalInfoPojo pojo){
		PersonalInfo personalInfo = wrapPojoToEntity(pojo);
		personalInfo.setStudentInfoIdFk(this.studentInfo);
		return dao.updateOne(personalInfo);
	}

	private static PersonalInfoPojo wrapEntityToPojo(PersonalInfo info) {
		PersonalInfoPojo pojo = new PersonalInfoPojo();
		pojo.setName(info.getName());
		pojo.setFathersName(info.getFathersName());
		pojo.setMothersName(info.getMothersName());
		pojo.setBloodGroup(info.getBloodGroup());
		pojo.setPhone(info.getContact());
		pojo.setDateOfBirth(info.getDateOfBirth());
		pojo.setEmail(info.getEmail());
		pojo.setMaritalStatus(info.getMaritalStatus());
		pojo.setNationality(info.getNationality());
		pojo.setPermanentAddress(info.getPermanentAddress());
		pojo.setPhotoUrl(info.getPhotoUrl());
		pojo.setPresentAddress(info.getPresentAddress());
		pojo.setReligion(info.getReligion());
		pojo.setSex(info.getSex());
		return pojo;
	}
	
	private static PersonalInfo wrapPojoToEntity(PersonalInfoPojo pojo) {
		PersonalInfo info = new PersonalInfo();
		info.setName(pojo.getName());
		info.setFathersName(pojo.getFathersName());
		info.setMothersName(pojo.getMothersName());
		info.setBloodGroup(pojo.getBloodGroup());
		info.setContact(pojo.getPhone());
		info.setDateOfBirth(pojo.getDateOfBirth());
		info.setEmail(pojo.getEmail());
		info.setMaritalStatus(pojo.getMaritalStatus());
		info.setNationality(pojo.getNationality());
		info.setPermanentAddress(pojo.getPermanentAddress());
		info.setPhotoUrl(pojo.getPhotoUrl());
		info.setPresentAddress(pojo.getPresentAddress());
		info.setReligion(pojo.getReligion());
		info.setSex(pojo.getSex());
		return info;
	}
}
