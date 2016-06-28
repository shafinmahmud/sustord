/*
 */
package shafin.sustord.service;

import shafin.sustord.dao.StudentInfoDao;

/**
 *
 * @author SHAFIN
 */
public class ChangePasswordService extends StudentIdentityService {


    public ChangePasswordService(String registrationNo) throws Exception {
        super(StudentIdentityService.forSingletonIdentityService(registrationNo));
        //super(StudentIdentityService.forProtypeIdentityService(registrationNo));
    }

    public boolean verifyCurrentPassword(String oldPassword) throws Exception {
        return studentInfo.getPassword().equals(oldPassword);
    }

    public boolean saveNewPassword(String newPassword) throws Exception {
        return StudentInfoDao.setStudentPassword(studentInfo, newPassword);
    }
}
