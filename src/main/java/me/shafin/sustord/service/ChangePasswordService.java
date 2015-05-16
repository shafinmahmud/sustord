/*
 */
package me.shafin.sustord.service;

import me.shafin.sustord.dao.StudentInfoDao;

/**
 *
 * @author SHAFIN
 */
public class ChangePasswordService extends StudentIdentityService {

    private static final String NULL_RESPONSE = " - ";
    private static final String ERROR_RESPONSE = ":/";

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
