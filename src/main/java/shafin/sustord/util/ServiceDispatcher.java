/*
 */
package shafin.sustord.util;

import java.util.ArrayList;
import java.util.List;

import shafin.sustord.dao.AdminInfoDao;
import shafin.sustord.dao.StudentInfoDao;
import shafin.sustord.model.AdminInfo;
import shafin.sustord.model.StudentInfo;

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
                    
                    System.out.println("CONTINOUS: "+DateTime.getLocalTimeStamp()+" ID:" + studentInfo.getRegistrationNo()
                                        +" Name: "+studentInfo.getPersonalInfo().getName());
                    return studentInfo;
                }
            }
        }

        StudentInfo newStudentInfo = StudentInfoDao.getStudentInfoObject(registrationNo);
        if(newStudentInfo != null){
            singletonStudentInfoList.add(newStudentInfo);
            System.out.println("STACK-SIZE: "+singletonStudentInfoList.size());
            System.err.println("NEW INSTANCE: "+DateTime.getLocalTimeStamp()+" ID: " + newStudentInfo.getRegistrationNo()
                                        +" Name: "+newStudentInfo.getPersonalInfo().getName());
        }    
        return newStudentInfo;
    }
    
    public static AdminInfo getSingletonAdminInfo(String adminNo) throws Exception {

        if (!singletonAdminInfoList.isEmpty()) {
            for (AdminInfo adminInfo : singletonAdminInfoList) {
                if (adminInfo.getAdminNo().equals(adminNo)) {
                    System.out.println("CONTINOUS: "+DateTime.getLocalTimeStamp()+" ID: " + adminInfo.getAdminNo());
                    return adminInfo;
                }
            }
        }

        AdminInfo newAdminInfo = AdminInfoDao.getAdminInfoObject(adminNo);
        if(newAdminInfo != null){
            singletonAdminInfoList.add(newAdminInfo);
            System.out.println("CONTINOUS: "+DateTime.getLocalTimeStamp()+" ID: " + newAdminInfo.getAdminNo());
                    
        }    
        return newAdminInfo;
    }

    public static void nullifyStudentServices(String registrationNo) {

        for (StudentInfo exitingStudenInfo : singletonStudentInfoList) {
            if (exitingStudenInfo.getRegistrationNo().equals(registrationNo)) {
                singletonStudentInfoList.remove(exitingStudenInfo);
                System.err.println("LOGOUT-SET_NULL: "+DateTime.getLocalTimeStamp()+" ID: " + exitingStudenInfo.getRegistrationNo());
                System.out.println("STACK-SIZE: "+singletonStudentInfoList.size());
                break;
            }
        }
    }
    
    public static void nullifyAdminServices(String adminNo) {

        for (AdminInfo exitingAdminInfo : singletonAdminInfoList) {
            if (exitingAdminInfo.getAdminNo().equals(adminNo)) {
                singletonAdminInfoList.remove(exitingAdminInfo);
                System.out.println("LOGOUT-SET_NULL: "+adminNo);
                
                break;
            }
        }
    }
}
