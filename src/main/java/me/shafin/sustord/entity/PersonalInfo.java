/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "personal_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonalInfo.findAll", query = "SELECT p FROM PersonalInfo p"),
    @NamedQuery(name = "PersonalInfo.findByPersonalInfoId", query = "SELECT p FROM PersonalInfo p WHERE p.personalInfoId = :personalInfoId"),
    @NamedQuery(name = "PersonalInfo.findByName", query = "SELECT p FROM PersonalInfo p WHERE p.name = :name"),
    @NamedQuery(name = "PersonalInfo.findByFathersName", query = "SELECT p FROM PersonalInfo p WHERE p.fathersName = :fathersName"),
    @NamedQuery(name = "PersonalInfo.findByMothersName", query = "SELECT p FROM PersonalInfo p WHERE p.mothersName = :mothersName"),
    @NamedQuery(name = "PersonalInfo.findBySex", query = "SELECT p FROM PersonalInfo p WHERE p.sex = :sex"),
    @NamedQuery(name = "PersonalInfo.findByReligion", query = "SELECT p FROM PersonalInfo p WHERE p.religion = :religion"),
    @NamedQuery(name = "PersonalInfo.findByNationality", query = "SELECT p FROM PersonalInfo p WHERE p.nationality = :nationality"),
    @NamedQuery(name = "PersonalInfo.findByMaritalStatus", query = "SELECT p FROM PersonalInfo p WHERE p.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "PersonalInfo.findByDateOfBirth", query = "SELECT p FROM PersonalInfo p WHERE p.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "PersonalInfo.findByBloodGroup", query = "SELECT p FROM PersonalInfo p WHERE p.bloodGroup = :bloodGroup"),
    @NamedQuery(name = "PersonalInfo.findByPresentAddress", query = "SELECT p FROM PersonalInfo p WHERE p.presentAddress = :presentAddress"),
    @NamedQuery(name = "PersonalInfo.findByPermanentAddress", query = "SELECT p FROM PersonalInfo p WHERE p.permanentAddress = :permanentAddress"),
    @NamedQuery(name = "PersonalInfo.findByEmail", query = "SELECT p FROM PersonalInfo p WHERE p.email = :email"),
    @NamedQuery(name = "PersonalInfo.findByContact", query = "SELECT p FROM PersonalInfo p WHERE p.contact = :contact")})
public class PersonalInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "personal_info_id")
    private Integer personalInfoId;
    @Size(max = 250)
    @Column(name = "name")
    private String name;
    @Size(max = 250)
    @Column(name = "fathers_name")
    private String fathersName;
    @Size(max = 250)
    @Column(name = "mothers_name")
    private String mothersName;
    @Size(max = 10)
    @Column(name = "sex")
    private String sex;
    @Size(max = 20)
    @Column(name = "religion")
    private String religion;
    @Size(max = 20)
    @Column(name = "nationality")
    private String nationality;
    @Size(max = 15)
    @Column(name = "marital_status")
    private String maritalStatus;
    @Size(max = 15)
    @Column(name = "date_of_birth")
    private String dateOfBirth;
    @Size(max = 10)
    @Column(name = "blood_group")
    private String bloodGroup;
    @Size(max = 400)
    @Column(name = "present_address")
    private String presentAddress;
    @Size(max = 400)
    @Column(name = "permanent_address")
    private String permanentAddress;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "contact")
    private String contact;
    @Size(max = 45)
    @Column(name = "photo_url")
    private String photoUrl;
    @JoinColumn(name = "student_info_id_fk", referencedColumnName = "student_info_id")
    @OneToOne(optional = false)
    private StudentInfo studentInfoIdFk;

    public PersonalInfo() {
    }

    public PersonalInfo(Integer personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public Integer getPersonalInfoId() {
        return personalInfoId;
    }

    public void setPersonalInfoId(Integer personalInfoId) {
        this.personalInfoId = personalInfoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFathersName() {
        return fathersName;
    }

    public void setFathersName(String fathersName) {
        this.fathersName = fathersName;
    }

    public String getMothersName() {
        return mothersName;
    }

    public void setMothersName(String mothersName) {
        this.mothersName = mothersName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    public void setBloodGroup(String bloodGroup) {
        this.bloodGroup = bloodGroup;
    }

    public String getPresentAddress() {
        return presentAddress;
    }

    public void setPresentAddress(String presentAddress) {
        this.presentAddress = presentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public StudentInfo getStudentInfoIdFk() {
        return studentInfoIdFk;
    }

    public void setStudentInfoIdFk(StudentInfo studentInfoIdFk) {
        this.studentInfoIdFk = studentInfoIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personalInfoId != null ? personalInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonalInfo)) {
            return false;
        }
        PersonalInfo other = (PersonalInfo) object;
        if ((this.personalInfoId == null && other.personalInfoId != null) || (this.personalInfoId != null && !this.personalInfoId.equals(other.personalInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.PersonalInfo[ personalInfoId=" + personalInfoId + " ]";
    }
    
}
