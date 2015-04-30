/*
 */
package me.shafin.sustord.utility;

import java.util.ArrayList;
import java.util.List;
import me.shafin.sustord.dao.AdminInfoDao;
import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.AdminInfo;
import me.shafin.sustord.model.StudentInfo;

/**
 *
 * @author SHAFIN
 */
public class ServiceDispatcher {

    private static List<StudentInfo> singletonStudentInfoList = new ArrayList<StudentInfo>();
    private static List<AdminInfo> singletonAdminInfoList = new ArrayList<AdminInfo>();

    public static StudentInfo getSingletonStudentInfo(String registrationNo) throws Exception {

        if (!singletonStudentInfoList.isEmpty()) {
            for (StudentInfo studentInfo : singletonStudentInfoList) {
                if (studentInfo.getRegistrationNo().equals(registrationNo)) {
                    //System.out.println("found studentinfo: " + studentInfo.getRegistrationNo());
                    return studentInfo;
                }
            }
        }

        StudentInfo newStudentInfo = StudentInfoDao.getStudentInfoObject(registrationNo);
        if(newStudentInfo != null){
            singletonStudentInfoList.add(newStudentInfo);
        }    
        return newStudentInfo;
    }
    
    public static AdminInfo getSingletonAdminInfo(String adminNo) throws Exception {

        if (!singletonAdminInfoList.isEmpty()) {
            for (AdminInfo adminInfo : singletonAdminInfoList) {
                if (adminInfo.getAdminNo().equals(adminNo)) {
                    return adminInfo;
                }
            }
        }

        AdminInfo newAdminInfo = AdminInfoDao.getAdminInfoObject(adminNo);
        if(newAdminInfo != null){
            singletonAdminInfoList.add(newAdminInfo);
        }    
        return newAdminInfo;
    }

    public static void nullifyStudentServices(String registrationNo) {

        for (StudentInfo exitingStudenInfo : singletonStudentInfoList) {
            if (exitingStudenInfo.getRegistrationNo().equals(registrationNo)) {
                singletonStudentInfoList.remove(exitingStudenInfo);
                break;
            }
        }
    }
    
    public static void nullifyAdminServices(String adminNo) {

        for (AdminInfo exitingAdminInfo : singletonAdminInfoList) {
            if (exitingAdminInfo.getAdminNo().equals(adminNo)) {
                singletonAdminInfoList.remove(exitingAdminInfo);
                break;
            }
        }
    }
}
