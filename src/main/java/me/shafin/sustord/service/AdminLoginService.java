/*
 */
package me.shafin.sustord.service;

/**
 *
 * @author SHAFIN
 */
public class AdminLoginService extends AdminIdentityService{
    private static final String NULL_RESPONSE = " - ";
    private static final String ERROR_RESPONSE = ":/";
    
     public AdminLoginService(String adminNo) throws Exception {
        super(AdminIdentityService.forSingletonIdentityService(adminNo));
    }

    public boolean verifyRegistrationNo() throws Exception {
        return adminInfo != null;
    }

    public boolean verifyPassword(String password) throws Exception {
        String existingPassword = adminInfo.getPassword();
        return password.equals(existingPassword);
    }
    
}
