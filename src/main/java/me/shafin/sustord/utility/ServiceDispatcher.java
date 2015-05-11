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
    private static  String loggedInStudentID = null;
    private static String loggedAdminID = null;

    public static StudentInfo getSingletonStudentInfo(String registrationNo) throws Exception {

        if (!singletonStudentInfoList.isEmpty()) {
            for (StudentInfo studentInfo : singletonStudentInfoList) {
                if (studentInfo.getRegistrationNo().equals(registrationNo)) {
                    System.out.println("found studentinfo: " + studentInfo.getRegistrationNo());
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
                setLoggedInStudentID(null);
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
                setLoggedAdminID(null);
                System.out.println("set NULL to adminInfo: "+adminNo);
                System.out.println("current size of singletonAdminInfoList: "+singletonAdminInfoList.size());
                break;
            }
        }
    }

    /**
     * @return the loggedInStudentID
     */
    public static String getLoggedInStudentID() {
        return loggedInStudentID;
    }

    /**
     * @param aLoggedInStudentID the loggedInStudentID to set
     */
    public static void setLoggedInStudentID(String aLoggedInStudentID) {
        loggedInStudentID = aLoggedInStudentID;
    }

    /**
     * @return the loggedAdminID
     */
    public static String getLoggedAdminID() {
        return loggedAdminID;
    }

    /**
     * @param aLoggedAdminID the loggedAdminID to set
     */
    public static void setLoggedAdminID(String aLoggedAdminID) {
        loggedAdminID = aLoggedAdminID;
    }
}
