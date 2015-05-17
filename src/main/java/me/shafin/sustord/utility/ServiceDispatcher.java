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

    private static final List<StudentInfo> singletonStudentInfoList = new ArrayList<StudentInfo>();
    private static final List<AdminInfo> singletonAdminInfoList = new ArrayList<AdminInfo>();

    public static StudentInfo getSingletonStudentInfo(String registrationNo) throws Exception {

        if (!singletonStudentInfoList.isEmpty()) {
            for (StudentInfo studentInfo : singletonStudentInfoList) {
                if (studentInfo.getRegistrationNo().equals(registrationNo)) {
                    System.out.println("CONTINOUS: TIME: "++" Reg:" + studentInfo.getRegistrationNo()
                                        +" Name: "+studentInfo.getPersonalInfo().getName());
                    return studentInfo;
                }
            }
        }

        StudentInfo newStudentInfo = StudentInfoDao.getStudentInfoObject(registrationNo);
        if(newStudentInfo != null){
            singletonStudentInfoList.add(newStudentInfo);
            System.out.println("created studentinfo: " + newStudentInfo.getRegistrationNo());
        }    
        return newStudentInfo;
    }
    
    public static AdminInfo getSingletonAdminInfo(String adminNo) throws Exception {

        if (!singletonAdminInfoList.isEmpty()) {
            for (AdminInfo adminInfo : singletonAdminInfoList) {
                if (adminInfo.getAdminNo().equals(adminNo)) {
                    System.out.println("found adminInfo: " + adminInfo.getAdminNo());
                    return adminInfo;
                }
            }
        }

        AdminInfo newAdminInfo = AdminInfoDao.getAdminInfoObject(adminNo);
        if(newAdminInfo != null){
            singletonAdminInfoList.add(newAdminInfo);
            System.out.println("created adminInfo: " + newAdminInfo.getAdminNo());
        }    
        return newAdminInfo;
    }

    public static void nullifyStudentServices(String registrationNo) {

        for (StudentInfo exitingStudenInfo : singletonStudentInfoList) {
            if (exitingStudenInfo.getRegistrationNo().equals(registrationNo)) {
                singletonStudentInfoList.remove(exitingStudenInfo);
                System.out.println("set NULL to studentInfo: "+registrationNo);
                 System.out.println("current size of singletonStudentInfoList: "+singletonStudentInfoList.size());
                break;
            }
        }
    }
    
    public static void nullifyAdminServices(String adminNo) {

        for (AdminInfo exitingAdminInfo : singletonAdminInfoList) {
            if (exitingAdminInfo.getAdminNo().equals(adminNo)) {
                singletonAdminInfoList.remove(exitingAdminInfo);
                System.out.println("set NULL to adminInfo: "+adminNo);
                System.out.println("current size of singletonAdminInfoList: "+singletonAdminInfoList.size());
                break;
            }
        }
    }
}
