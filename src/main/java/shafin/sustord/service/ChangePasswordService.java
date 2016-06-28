/*
 */
package shafin.sustord.service;

import shafin.sustord.dao.StudentInfoDao;
import shafin.sustord.exeptions.UnmatchedPasswordException;

/**
 *
 * @author SHAFIN
 */
public class ChangePasswordService extends StudentServicea {

    public ChangePasswordService(String registrationNo) throws Exception {
        super(registrationNo);
    }

    private boolean matchCurrentPassword(String oldPassword) throws Exception {
        return studentInfo.getPassword().equals(oldPassword);
    }

    public boolean saveNewPassword(String oldPassword, String newPassword) throws Exception {
    	if(matchCurrentPassword(oldPassword)){
    		StudentInfoDao dao = new StudentInfoDao();
    		this.studentInfo.setPassword(newPassword);
    		return dao.updateOne(studentInfo);
    	}else{
    		throw new UnmatchedPasswordException("Old password doesnt match!");
    	}
    }
}
