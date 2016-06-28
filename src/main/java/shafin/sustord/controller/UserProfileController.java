package shafin.sustord.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

import shafin.sustord.service.AcademicInfoService;
import shafin.sustord.service.PersonalInfoService;

/**
 *
 * @author SHAFIN
 */
public class UserProfileController {

    private PersonalInfoService personalInfoService;
    private AcademicInfoService academicInfoService;

    String presentStreet;
    String presentArea;
    String presentThana;
    String presentDistrict;
    String presentCountry;

    String permanentStreet;
    String permanentArea;
    String permanentThana;
    String permanentDistrict;
    String permanentCountry;

    public UserProfileController(String registrationNo) {
        try {
            this.personalInfoService = new PersonalInfoService(registrationNo);
            this.academicInfoService = new AcademicInfoService(registrationNo);

            String presentAddress = getPresentAddress();

            if (!presentAddress.isEmpty()) {

                String[] parts = presentAddress.split(",");

                this.presentStreet = parts[0];
                this.presentArea = parts[1];
                this.presentThana = parts[2];
                this.presentDistrict = parts[3];
                this.presentCountry = parts[4];

            } else {
                this.presentStreet = "";
                this.presentArea = "";
                this.presentThana = "";
                this.presentDistrict = "";
                this.presentCountry = "";
            }

            String permanentAddress = getPermanentAddress();

            if (!permanentAddress.isEmpty()) {

                String[] parts = permanentAddress.split(",");

                this.permanentStreet = parts[0];
                this.permanentArea = parts[1];
                this.permanentThana = parts[2];
                this.permanentDistrict = parts[3];
                this.permanentCountry = parts[4];

            } else {
                this.permanentStreet = "";
                this.permanentArea = "";
                this.permanentThana = "";
                this.permanentDistrict = "";
                this.permanentCountry = "";
            }

        } catch (Exception ex) {
            Logger.getLogger(UserHomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getStudentName() {
        return getPersonalInfoService().getStudentName();
    }

    public String getFatherName() {
        return getPersonalInfoService().getFatherName();
    }

    public String getMotherName() {
        return getPersonalInfoService().getMotherName();
    }

    public final String getPresentAddress() {
        return getPersonalInfoService().getPresentAddress();
    }

    public final String getPermanentAddress() {
        return getPersonalInfoService().getPermanentAddress();
    }

    public String getPhone() {
        return getPersonalInfoService().getPhone();
    }

    public String getEmail() {
        return getPersonalInfoService().getEmail();
    }

    public String getDob() {
        return getPersonalInfoService().getDob();
    }

    public String getSex() {
        return getPersonalInfoService().getSex();
    }

    public String getReligion() {
        return getPersonalInfoService().getReligion();
    }

    public String getNationality() {
        return getPersonalInfoService().getNationality();
    }

    public String getMaritalStatus() {
        return getPersonalInfoService().getMaritalStatus();
    }

    public String getBloodGroup() {
        return getPersonalInfoService().getBloodGroup();
    }

    public String getStudentPhotoUrl() {
        return getPersonalInfoService().getStudentPhotoUrl();
    }

    /* Academic Information  */
    public String getStudentProgramName() {
        return getAcademicInfoService().getStudentProgramName();
    }

    public String getStudentDepartmentName() {
        return getAcademicInfoService().getStudentDepartmentName();
    }

    public String getStudentSchoolName() {
        return getAcademicInfoService().getStudentSchoolName();
    }

    public String getStudentAcademicSession() {
        return getAcademicInfoService().getStudentAcademicSession();
    }

    /**
     * @return the personalInfoService
     */
    public PersonalInfoService getPersonalInfoService() {
        return personalInfoService;
    }

    /**
     * @return the academicInfoService
     */
    public AcademicInfoService getAcademicInfoService() {
        return academicInfoService;
    }

    /**
     * @return the presentStreet
     */
    public String getPresentStreet() {
        return presentStreet;
    }

    /**
     * @return the presentArea
     */
    public String getPresentArea() {
        return presentArea;
    }

    /**
     * @return the presentThana
     */
    public String getPresentThana() {
        return presentThana;
    }

    /**
     * @return the presentDistrict
     */
    public String getPresentDistrict() {
        return presentDistrict;
    }

    /**
     * @return the presentCountry
     */
    public String getPresentCountry() {
        return presentCountry;
    }

    /**
     * @return the permanentStreet
     */
    public String getPermanentStreet() {
        return permanentStreet;
    }

    /**
     * @return the permanentArea
     */
    public String getPermanentArea() {
        return permanentArea;
    }

    /**
     * @return the permanentThana
     */
    public String getPermanentThana() {
        return permanentThana;
    }

    /**
     * @return the permanentDistrict
     */
    public String getPermanentDistrict() {
        return permanentDistrict;
    }

    /**
     * @return the permanentCountry
     */
    public String getPermanentCountry() {
        return permanentCountry;
    }
}
