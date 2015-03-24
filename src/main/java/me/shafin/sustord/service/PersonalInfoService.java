/*
 */
package me.shafin.sustord.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import me.shafin.sustord.dao.PersonalInfoDao;
import me.shafin.sustord.dao.StudentInfoDao;
import me.shafin.sustord.model.StudentInfo;
import me.shafin.sustord.utility.ServiceDispatcher;

/**
 *
 * @author SHAFIN
 */
public class PersonalInfoService {

    private final StudentInfo studentInfo;
    private static final String NULL_RESPONSE = " - ";
    private static final String ERROR_RESPONSE = ":/";

    /* Constructor thats is private and get accesses through static helper method  */
    private PersonalInfoService(StudentInfo studentInfo) throws Exception {
        this.studentInfo = studentInfo;
    }

    /* Constructor helper for singleton PersonalInfoService  */
    public static PersonalInfoService forSingletonStudentInfoService(String registrationNo) throws Exception {
        return new PersonalInfoService(ServiceDispatcher.getSingletonStudentInfo(registrationNo));
    }

    /* Constructor helper for prototype PersonalInfoService  */
    public static PersonalInfoService forProtypeStudentInfoService(String registrationNo) throws Exception {
        return new PersonalInfoService(StudentInfoDao.getStudentInfoObject(registrationNo));
    }

    /* Personal Information  */
    public String getStudentName() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getName();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getFatherName() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getFathersName();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getMotherName() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getMothersName();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getPresentAddress() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getPresentAddress();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getPermanentAddress() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getPermanentAddress();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getPhone() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getContact();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getEmail() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getEmail();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getDob() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getDateOfBirth();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getSex() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getSex();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getReligion() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getReligion();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getNationality() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getNationality();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getMaritalStatus() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getMaritalStatus();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getBloodGroup() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getBloodGroup();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

    public String getStudentPhotoUrl() {
        try {
            String string = PersonalInfoDao.getPersonalInfoObject(studentInfo.getStudentInfoId()).getPhotoUrl();
            if (string == null) {
                return NULL_RESPONSE;
            } else {
                return string;
            }
        } catch (Exception ex) {
            Logger.getLogger(PersonalInfoService.class.getName()).log(Level.SEVERE, null, ex);
            return ERROR_RESPONSE;
        }
    }

}