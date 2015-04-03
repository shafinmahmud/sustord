/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.utility;

import java.util.ArrayList;
import java.util.List;
import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.StudentInfo;

/**
 *
 * @author SHAFIN
 */
public class ServiceDispatcher {

    private static List<StudentInfo> singletonStudentInfoList = new ArrayList<StudentInfo>();

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

    public static void nullifyServiceDispatchers(String registrationNo) {

        for (StudentInfo exitingStudenInfo : singletonStudentInfoList) {
            if (exitingStudenInfo.getRegistrationNo().equals(registrationNo)) {
                singletonStudentInfoList.remove(exitingStudenInfo);
                break;
            }
        }
    }
}
