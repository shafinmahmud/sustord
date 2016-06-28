/*
 */
package shafin.sustord.service;

import shafin.sustord.exeptions.InvalidRegistrationException;

/**
 *
 * @author SHAFIN
 */
public class AdminLoginService extends AdminService{
    
    
     public AdminLoginService(String adminNo) throws Exception {
        super(adminNo);
    }

     private boolean isExistsRegistrationNo(){
 		return adminInfo != null;
 	}

 	private boolean isMatchPassword(String password){
 		String existingPassword = adminInfo.getPassword();
 		return password.equals(existingPassword);
 	}
 	
 	public boolean authenticateLogin(String password){
 		if(isExistsRegistrationNo()){
 			return isMatchPassword(password);
 		}else{
 			throw new InvalidRegistrationException(this.adminNo+" is an Invalid Admin ID.");
 		}
 	}
    
}
