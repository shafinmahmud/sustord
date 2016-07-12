package shafin.sustord.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.sustord.dto.LoginDto;
import shafin.sustord.dto.LoginModel;
import shafin.sustord.exeptions.InvalidRegistrationException;
import shafin.sustord.exeptions.UnmatchedPasswordException;
import shafin.sustord.service.AdminLoginService;
import shafin.sustord.service.StudentLoginService;

@Controller
@Scope("request")
public class LoginController {
	
	@Autowired
    LoginSession loginSession;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "redirect:/login/user";
	}

	
	@RequestMapping(value = "/login/user", method = RequestMethod.GET)
	public String user(Model model) {
		LoginModel login = getUserLoginModel();
		LoginDto dto = new LoginDto();

		model.addAttribute("dto", dto);
		model.addAttribute("login", login);
		return "login";
	}

	@RequestMapping(value = "/login/admin", method = RequestMethod.GET)
	public String admin(Model model) {
		LoginModel login = getAdminLoginModel();
		LoginDto dto = new LoginDto();

		model.addAttribute("dto", dto);
		model.addAttribute("login", login);
		return "login";
	}

	@RequestMapping(value = "/login/user", method = RequestMethod.POST)
	public String loginUser(@Valid @ModelAttribute("dto") LoginDto dto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			LoginModel login = getUserLoginModel();
			model.addAttribute("login", login);
			return "login";
		}

		String auth = userLoginAuth(dto.getUserName(), dto.getPassword());
		if (auth.equals("success")) {
			// set session for specific user
			this.loginSession.setId(dto.getUserName());
			this.loginSession.setRole(dto.getUserRole());
			return "redirect:/home/user";
		} else {
			LoginModel login = getUserLoginModel();
			login.setValidationMessage(auth);
			model.addAttribute("login", login);
			return "login";
		}
	}
	
	@RequestMapping(value = "/login/admin", method = RequestMethod.POST)
	public String loginAdmin(@Valid @ModelAttribute("dto") LoginDto dto, BindingResult result, Model model) {

		if (result.hasErrors()) {
			LoginModel login = getAdminLoginModel();
			model.addAttribute("login", login);
			return "login";
		}

		String auth = adminLoginAuth(dto.getUserName(), dto.getPassword());
		if (auth.equals("success")) {
			// set session for specific user
			this.loginSession.setId(dto.getUserName());
			this.loginSession.setRole(dto.getUserRole());
			return "redirect:/home/user";
		} else {
			LoginModel login = getAdminLoginModel();
			login.setValidationMessage(auth);
			model.addAttribute("login", login);
			return "login";
		}
	}

	private String userLoginAuth(String id, String pass) {	
		try {
			StudentLoginService service = new StudentLoginService(id);
			service.authenticateLogin(pass);
		} catch (InvalidRegistrationException e) {
			return e.getMessage();
		} catch (UnmatchedPasswordException e) {
			return e.getMessage();
		}
		return "success";
	}

	private String adminLoginAuth(String id, String pass) {
		try {
			AdminLoginService service = new AdminLoginService(id);
			service.authenticateLogin(pass);
		} catch (InvalidRegistrationException e) {
			return e.getMessage();
		} catch (UnmatchedPasswordException e) {
			return e.getMessage();
		}
		return "success";
	}

	private LoginModel getUserLoginModel() {
		LoginModel login = new LoginModel();
		login.setUserRole("student");
		login.setFormHeaderText("Sign in with your Registration Number.");
		login.setLeftPanelImage("/resources/images/collage.jpg");
		login.setUserNameText("Registration No");
		login.setUserNamePlaceHolder("eg. 2011331001");
		login.setAlterUserText("I'm an Admin");
		login.setAlterUserURL("/login/admin");
		login.setFormSubmitURL("/login/user");
		login.setLoginHelpURL("");
		return login;
	}

	private LoginModel getAdminLoginModel() {
		LoginModel login = new LoginModel();
		login.setUserRole("student");
		login.setFormHeaderText("Sign in with your Admin ID.");
		login.setLeftPanelImage("/resources/images/image14.jpg");
		login.setUserNameText("Admin ID");
		login.setUserNamePlaceHolder("eg. 2011331AB");
		login.setAlterUserText("I'm a Student");
		login.setAlterUserURL("/login/user");
		login.setFormSubmitURL("/login/admin");
		login.setLoginHelpURL("");
		return login;
	}

}
