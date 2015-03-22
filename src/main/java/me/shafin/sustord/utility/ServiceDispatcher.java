/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.utility;

import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.StudentInfo;

/**
 *
 * @author SHAFIN
 */
public class ServiceDispatcher {

    private static StudentInfo singletonStudentInfo;

    public static StudentInfo getSingletonStudentInfo(String registrationNo) throws Exception {
        if (singletonStudentInfo == null) {
            singletonStudentInfo = StudentInfoDao.getStudentInfoObject(registrationNo);
        }
        return singletonStudentInfo;
    }

    public static void nullifyServiceDispatchers() {
        singletonStudentInfo = null;
    }
}
