package shafin.sustord.service;

import shafin.sustord.exeptions.InvalidRegistrationException;

/**
 *
 * @author SHAFIN
 */
public class StudentLoginService extends StudentServicea {
	
	public StudentLoginService(String registrationNo){
		super(registrationNo);
	}

	private boolean isExistsRegistrationNo(){
		return studentInfo != null;
	}

	private boolean isMatchPassword(String password){
		String existingPassword = studentInfo.getPassword();
		return password.equals(existingPassword);
	}
	
	public boolean authenticateLogin(String password){
		if(isExistsRegistrationNo()){
			return isMatchPassword(password);
		}else{
			throw new InvalidRegistrationException(this.registrationNo+" is an Invalid Registration No.");
		}
	}
}
