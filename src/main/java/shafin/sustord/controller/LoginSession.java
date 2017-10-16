package shafin.sustord.controller;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class LoginSession {
	
	private String role;
	private String id;
	
	public LoginSession() {
	}
		
	public LoginSession(String role, String id) {
		super();
		this.role = role;
		this.id = id;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}	
}
