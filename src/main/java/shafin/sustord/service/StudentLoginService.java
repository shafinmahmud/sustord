package shafin.sustord.service;

import shafin.sustord.exeptions.InvalidRegistrationException;

/**
 *
 * @author SHAFIN
 */
public class StudentLoginService extends StudentServicea {

	public static final String ID_ERROR = "Invalid Registration No."
			+ "Make sure you typed the valid Registration No. It usually looks like YYYYDEPXXX. eg. 2011331001 ";

	public static final String PASS_ERROR = "Wrong password. Have you changed your password? "
			+ "If not, your default password is 123456";

	public StudentLoginService(String registrationNo) {
		super(registrationNo);
	}

	private boolean isExistsRegistrationNo() {
		return studentInfo != null;
	}

	private boolean isMatchPassword(String password) {
		String existingPassword = studentInfo.getPassword();
		return password.equals(existingPassword);
	}

	public boolean authenticateLogin(String password) {
		if (isExistsRegistrationNo()) {
			return isMatchPassword(password);
		} else {
			throw new InvalidRegistrationException(this.registrationNo + " is an Invalid Registration No.");
		}
	}
}
