package shafin.sustord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.sustord.dto.UserProfileDto;
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
		
		getProfileDTO();
		return "profile";
	}
	
	private UserProfileDto getProfileDTO(){
		StudentinfoPersonal personalService = new StudentinfoPersonal(loginSession.getId());
		return null;
	}
}
