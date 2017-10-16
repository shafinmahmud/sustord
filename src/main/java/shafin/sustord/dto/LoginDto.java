package shafin.sustord.dto;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginDto{
	
	private String userRole;
	
	@Size(min=10, max=10, message="Your ID must be 10 Character long!")
	private String userName;
	
	@NotEmpty( message="Your password must not be Empty!")
	private String password;
	
	public LoginDto() {
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	
}
