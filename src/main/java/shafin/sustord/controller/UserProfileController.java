package shafin.sustord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.sustord.dto.UserProfileDto;
import shafin.sustord.pojo.PersonalInfoPojo;
import shafin.sustord.service.StudentinfoPersonal;

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
	
	private UserProfileDto getProfileDTO(){
		StudentinfoPersonal personalService = new StudentinfoPersonal(loginSession.getId());
		PersonalInfoPojo pojo = personalService.getStudentPersonalInfo();
		return wrapPersonalInfoPojoIntoUserProfileDto(pojo);
	}
	
	public static UserProfileDto wrapPersonalInfoPojoIntoUserProfileDto(PersonalInfoPojo pojo){
		UserProfileDto dto = new UserProfileDto();
		dto.setName(pojo.getName());
		dto.setFathersName(pojo.getFathersName());
		dto.setMothersName(pojo.getMothersName());
		dto.setBloodGroup(pojo.getBloodGroup());
		dto.setPhone(pojo.getPhone());
		dto.setDateOfBirth(pojo.getDateOfBirth());
		dto.setEmail(pojo.getEmail());
		dto.setMaritalStatus(pojo.getMaritalStatus());
		dto.setNationality(pojo.getNationality());
		dto.setPermanentAddress(pojo.getPermanentAddress());
		dto.setPhotoUrl(pojo.getPhotoUrl());
		dto.setPresentAddress(pojo.getPresentAddress());
		dto.setReligion(pojo.getReligion());
		dto.setSex(pojo.getSex());
		return dto;
	}
	
}
