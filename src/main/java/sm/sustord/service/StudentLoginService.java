package sm.sustord.service;

/**
 *
 * @author SHAFIN
 */
public class StudentLoginService extends StudentIdentityService{
    
     public StudentLoginService(String registrationNo) throws Exception {
        super(StudentIdentityService.forSingletonIdentityService(registrationNo));
        //super(StudentIdentityService.forProtypeIdentityService(registrationNo));
    }

    public boolean verifyRegistrationNo() throws Exception {
        return studentInfo != null;
    }

    public boolean verifyPassword(String password) throws Exception {
        String existingPassword = studentInfo.getPassword();
        return password.equals(existingPassword);
    }
}
