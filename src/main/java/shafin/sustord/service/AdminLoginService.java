/*
 */
package shafin.sustord.service;

import shafin.sustord.exeptions.InvalidRegistrationException;
import shafin.sustord.exeptions.UnmatchedPasswordException;


public class AdminLoginService extends AdminService{
    
    
	public static final String ID_ERROR = " is an Invalid Admin ID."
			+ "Make sure you typed the valid Registration No. It usually looks like YYYYDEPXXX. eg. 2011331ABC ";

	public static final String PASS_ERROR = "Wrong password. Have you changed your password? "
			+ "If not, your default password is 123456";

	public AdminLoginService(String registrationNo) {
		super(registrationNo);
	}

	private boolean isExistsRegistrationNo() {
		return adminInfo != null;
	}

	private boolean isMatchPassword(String password) {
		String existingPassword = adminInfo.getPassword();
		return password.equals(existingPassword);
	}

	public boolean authenticateLogin(String password) {
		if (isExistsRegistrationNo()) {
			if(isMatchPassword(password))
				return true;
			else
				throw new UnmatchedPasswordException(PASS_ERROR);
		} else {
			throw new InvalidRegistrationException(this.adminID + ID_ERROR);
		}
	}
    
}
