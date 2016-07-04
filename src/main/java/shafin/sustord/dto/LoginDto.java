package shafin.sustord.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class LoginDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String userRole;
	
	@NotNull(message="Your ID must not be Empty!")
	@Size(min=10, max=10, message="Your ID should be 10 Char long!")
	private String userName;
	
	@Size(min=1,  message="Your password must not be Empty!")
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
