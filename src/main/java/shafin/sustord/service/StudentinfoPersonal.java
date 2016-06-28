package shafin.sustord.service;

import shafin.sustord.dao.PersonalInfoDao;
import shafin.sustord.dto.StudentPersonalinfoDTO;
import shafin.sustord.entity.PersonalInfo;

public class StudentinfoPersonal extends StudentServicea {

	private final PersonalInfoDao dao;

	public StudentinfoPersonal(String registrationNo) {
		super(registrationNo);
		this.dao = new PersonalInfoDao();
	}

	/* Personal Information */
	public String getStudentName() {
		String studentName = dao.findOne(this.studentInfo.getStudentInfoId()).getName();
		return studentName != null ? studentName : "";
	}

	public StudentPersonalinfoDTO getStudentPersonalInfo() {
		PersonalInfo info = dao.findOne(this.studentInfo.getStudentInfoId());
		return wrapEntityToDTO(info);
	}
	
	public boolean saveStudentPersonalInfo(StudentPersonalinfoDTO dto){
		PersonalInfo personalInfo = wrapDTOToEntity(dto);
		personalInfo.setStudentInfoIdFk(this.studentInfo);
		return dao.updateOne(personalInfo);
	}

	private static StudentPersonalinfoDTO wrapEntityToDTO(PersonalInfo info) {
		StudentPersonalinfoDTO dto = new StudentPersonalinfoDTO();
		dto.setName(info.getName());
		dto.setFathersName(dto.getFathersName());
		dto.setMothersName(info.getMothersName());
		dto.setBloodGroup(info.getBloodGroup());
		dto.setContact(dto.getContact());
		dto.setDateOfBirth(dto.getDateOfBirth());
		dto.setEmail(info.getEmail());
		dto.setIsApproved(info.getIsApproved());
		dto.setMaritalStatus(info.getMaritalStatus());
		dto.setNationality(info.getNationality());
		dto.setPermanentAddress(info.getPermanentAddress());
		dto.setPhotoUrl(info.getPhotoUrl());
		dto.setPresentAddress(info.getPresentAddress());
		dto.setReligion(info.getReligion());
		dto.setSex(info.getSex());
		return dto;
	}
	
	private static PersonalInfo wrapDTOToEntity(StudentPersonalinfoDTO dto) {
		PersonalInfo info = new PersonalInfo();
		info.setName(dto.getName());
		info.setFathersName(dto.getFathersName());
		info.setMothersName(dto.getMothersName());
		info.setBloodGroup(dto.getBloodGroup());
		info.setContact(dto.getContact());
		info.setDateOfBirth(dto.getDateOfBirth());
		info.setEmail(dto.getEmail());
		info.setIsApproved(dto.getIsApproved());
		info.setMaritalStatus(dto.getMaritalStatus());
		info.setNationality(dto.getNationality());
		info.setPermanentAddress(dto.getPermanentAddress());
		info.setPhotoUrl(dto.getPhotoUrl());
		info.setPresentAddress(dto.getPresentAddress());
		info.setReligion(dto.getReligion());
		info.setSex(dto.getSex());
		return info;
	}
}
