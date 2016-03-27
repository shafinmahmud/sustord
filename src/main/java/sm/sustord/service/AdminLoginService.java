/*
 */
package sm.sustord.service;

/**
 *
 * @author SHAFIN
 */
public class AdminLoginService extends AdminIdentityService{
    
    
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
