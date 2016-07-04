package shafin.sustord.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import shafin.sustord.dto.LoginDto;
import shafin.sustord.dto.LoginModel;

@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home() {
		return "redirect:/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "redirect:/login/user";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginAuth(@Valid @ModelAttribute("dto") LoginDto dto, BindingResult result, Model model) {
			
		System.out.println(dto.getUserRole()+" : "+dto.getUserName().length() + " : " + dto.getPassword().length());
		
		if (result.hasErrors()) {
			System.out.println("yes error has!");
	        return "redirect:/login/user";
	    }
	
		return "login";
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
		model.addAttribute("login", login);
		return "login";
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
		login.setLoginHelpURL("");
		return login;
	}

	/*
	 * public static LoginMessage authencateStudent(String registrationNo,
	 * String password) { LoginMessage message = new LoginMessage(); try {
	 * StudentLoginService loginService = new
	 * StudentLoginService(registrationNo); if
	 * (loginService.verifyRegistrationNo()) {
	 * message.setRequestedIdValid(true); if
	 * (loginService.verifyPassword(password)) {
	 * message.setRequestedPasswordValid(true);
	 * message.setMessageTitle("Verified"); message.setMessageBody(
	 * "Login information is correct. Verification successful.");
	 * message.setRequestedId(registrationNo); } else {
	 * message.setRequestedPasswordValid(false);
	 * message.setMessageTitle("Access denied");
	 * message.setMessageBody("Provided password is wrong."); } } else {
	 * message.setRequestedIdValid(false);
	 * message.setMessageTitle("Unknown ID");
	 * message.setMessageBody("Provided Registration No is invalid."); }
	 * 
	 * } catch (HibernateException ex) { StringWriter sw = new StringWriter();
	 * PrintWriter pw = new PrintWriter(sw); ex.printStackTrace(pw); String
	 * errorString = sw.toString(); // stack trace as a string
	 * 
	 * message.setMessageTitle("Hibernate Exception");
	 * message.setMessageBody(errorString); } catch (Exception e) { StringWriter
	 * sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw);
	 * e.printStackTrace(pw); String errorString = sw.toString(); // stack trace
	 * as a string
	 * 
	 * message.setMessageTitle("Server Exception");
	 * message.setMessageBody(errorString); }
	 * 
	 * return message; }
	 * 
	 * public static LoginMessage authencateBatchAdmin(String adminId, String
	 * password) { LoginMessage message = new LoginMessage(); try {
	 * AdminLoginService loginService = new AdminLoginService(adminId); if
	 * (loginService.verifyRegistrationNo()) {
	 * message.setRequestedIdValid(true); if
	 * (loginService.verifyPassword(password)) {
	 * message.setRequestedPasswordValid(true);
	 * message.setMessageTitle("Verified"); message.setMessageBody(
	 * "Login information is correct. Verification successful.");
	 * message.setRequestedId(adminId); } else {
	 * message.setRequestedPasswordValid(false);
	 * message.setMessageTitle("Access denied");
	 * message.setMessageBody("Provided password is wrong."); } } else {
	 * message.setRequestedIdValid(false);
	 * message.setMessageTitle("Unknown ID");
	 * message.setMessageBody("Provided Registration No is invalid."); }
	 * 
	 * } catch (HibernateException ex) { StringWriter sw = new StringWriter();
	 * PrintWriter pw = new PrintWriter(sw); ex.printStackTrace(pw); String
	 * errorString = sw.toString(); // stack trace as a string
	 * 
	 * message.setMessageTitle("Hibernate Exception");
	 * message.setMessageBody(errorString); } catch (Exception e) { StringWriter
	 * sw = new StringWriter(); PrintWriter pw = new PrintWriter(sw);
	 * e.printStackTrace(pw); String errorString = sw.toString(); // stack trace
	 * as a string
	 * 
	 * message.setMessageTitle("Server Exception");
	 * message.setMessageBody(errorString); }
	 * 
	 * return message; }
	 * 
	 * public static StudentService temporarySupport() throws SQLException {
	 * 
	 * StudentService service = new StudentService(); return service;
	 * 
	 * }
	 */
}
