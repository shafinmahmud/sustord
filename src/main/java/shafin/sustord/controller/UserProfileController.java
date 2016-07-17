package shafin.sustord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.sustord.dto.UserProfileDto;
import shafin.sustord.pojo.AcademicInfoPojo;
import shafin.sustord.pojo.PersonalInfoPojo;
import shafin.sustord.service.StudentinfoServiceAcademic;
import shafin.sustord.service.StudentinfoServicePersonal;

@Controller
@Scope("request")
public class UserProfileController {

	@Autowired
	LoginSession loginSession;
	
	@RequestMapping(value = "/profile/user", method = RequestMethod.GET)
	public String profile(Model model){
		if (loginSession.getId() == null || !loginSession.getRole().equals("student"))
			return "redirect:/login/user";
		
		model.addAttribute("profile", getProfileDTO());
		return "profile";
	}
	
	@RequestMapping(value = "/profile/user/edit", method = RequestMethod.GET)
	public String profileEdit(Model model){
		if (loginSession.getId() == null || !loginSession.getRole().equals("student"))
			return "redirect:/login/user";
		
		model.addAttribute("profile", getProfileDTO());
		return "profile";
	}
	
	private UserProfileDto getProfileDTO(){
		StudentinfoServicePersonal personalService = new StudentinfoServicePersonal(loginSession.getId());
		StudentinfoServiceAcademic academicService = new StudentinfoServiceAcademic(loginSession.getId());
		
		AcademicInfoPojo academicPojo = academicService.getStudentAcademicInfo();
		PersonalInfoPojo personalPojo = personalService.getStudentPersonalInfo();
		return wrapPojoIntoUserProfileDto(academicPojo,personalPojo);
	}
	
	private static UserProfileDto wrapPojoIntoUserProfileDto(AcademicInfoPojo academicPojo, PersonalInfoPojo personalPojo){
		UserProfileDto dto = new UserProfileDto();
		
		dto.setRegistrationNo(academicPojo.getRegistrationNo());
		dto.setSchool(academicPojo.getSchool());
		dto.setDepartment(academicPojo.getDepartment());
		dto.setProgram(academicPojo.getProgram());
		dto.setSession(academicPojo.getSession());
		dto.setCreditsCompleted(academicPojo.getCreditsCompleted());
		dto.setTotalCredit(academicPojo.getTotalCredit());
		dto.setCgpa(academicPojo.getCgpa());
		
		dto.setName(personalPojo.getName());
		dto.setFathersName(personalPojo.getFathersName());
		dto.setMothersName(personalPojo.getMothersName());
		dto.setBloodGroup(personalPojo.getBloodGroup());
		dto.setPhone(personalPojo.getPhone());
		dto.setDateOfBirth(personalPojo.getDateOfBirth());
		dto.setEmail(personalPojo.getEmail());
		dto.setMaritalStatus(personalPojo.getMaritalStatus());
		dto.setNationality(personalPojo.getNationality());
		dto.setPermanentAddress(personalPojo.getPermanentAddress());
		dto.setPhotoUrl(personalPojo.getPhotoUrl());
		dto.setPresentAddress(personalPojo.getPresentAddress());
		dto.setReligion(personalPojo.getReligion());
		dto.setSex(personalPojo.getSex());
		return dto;
	}
	
}
