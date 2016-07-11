package shafin.sustord.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope("request")
public class UserHomeController {

	@Autowired
	LoginSession loginSession;

	@RequestMapping(value = "/home/user", method = RequestMethod.GET)
	public String userHome(Model model) {
		if (loginSession.getId() == null || !loginSession.getRole().equals("student"))
			return "redirect:/login/user";

		System.out.println(loginSession.getId() + " : " + loginSession.getRole());
		return "home";
	}
	
	@RequestMapping(value = "/home/admin", method = RequestMethod.GET)
	public String adminHome(Model model) {
		if (loginSession.getId() == null || !loginSession.getRole().equals("admin"))
			return "redirect:/login/admin";

		return "home";
	}

}
