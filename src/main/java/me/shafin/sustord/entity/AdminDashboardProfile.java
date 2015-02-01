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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "admin_dashboard_profile")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminDashboardProfile.findAll", query = "SELECT a FROM AdminDashboardProfile a"),
    @NamedQuery(name = "AdminDashboardProfile.findByAdminDashboardProfileId", query = "SELECT a FROM AdminDashboardProfile a WHERE a.adminDashboardProfileId = :adminDashboardProfileId"),
    @NamedQuery(name = "AdminDashboardProfile.findByName", query = "SELECT a FROM AdminDashboardProfile a WHERE a.name = :name"),
    @NamedQuery(name = "AdminDashboardProfile.findByFathersName", query = "SELECT a FROM AdminDashboardProfile a WHERE a.fathersName = :fathersName"),
    @NamedQuery(name = "AdminDashboardProfile.findByMothersName", query = "SELECT a FROM AdminDashboardProfile a WHERE a.mothersName = :mothersName"),
    @NamedQuery(name = "AdminDashboardProfile.findBySex", query = "SELECT a FROM AdminDashboardProfile a WHERE a.sex = :sex"),
    @NamedQuery(name = "AdminDashboardProfile.findByReligion", query = "SELECT a FROM AdminDashboardProfile a WHERE a.religion = :religion"),
    @NamedQuery(name = "AdminDashboardProfile.findByNationality", query = "SELECT a FROM AdminDashboardProfile a WHERE a.nationality = :nationality"),
    @NamedQuery(name = "AdminDashboardProfile.findByMaritalStatus", query = "SELECT a FROM AdminDashboardProfile a WHERE a.maritalStatus = :maritalStatus"),
    @NamedQuery(name = "AdminDashboardProfile.findByDateOfBirth", query = "SELECT a FROM AdminDashboardProfile a WHERE a.dateOfBirth = :dateOfBirth"),
    @NamedQuery(name = "AdminDashboardProfile.findByBloodGroup", query = "SELECT a FROM AdminDashboardProfile a WHERE a.bloodGroup = :bloodGroup"),
    @NamedQuery(name = "AdminDashboardProfile.findByPresentAddress", query = "SELECT a FROM AdminDashboardProfile a WHERE a.presentAddress = :presentAddress"),
    @NamedQuery(name = "AdminDashboardProfile.findByPermanentAddress", query = "SELECT a FROM AdminDashboardProfile a WHERE a.permanentAddress = :permanentAddress"),
    @NamedQuery(name = "AdminDashboardProfile.findByEmail", query = "SELECT a FROM AdminDashboardProfile a WHERE a.email = :email"),
    @NamedQuery(name = "AdminDashboardProfile.findByContact", query = "SELECT a FROM AdminDashboardProfile a WHERE a.contact = :contact"),
    @NamedQuery(name = "AdminDashboardProfile.findByApproval", query = "SELECT a FROM AdminDashboardProfile a WHERE a.approval = :approval")})
public class AdminDashboardProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "admin_dashboard_profile_id")
    private Integer adminDashboardProfileId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "name")
    private String name;
    @Size(max = 100)
    @Column(name = "fathers_name")
    private String fathersName;
    @Size(max = 100)
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
    @Size(max = 300)
    @Column(name = "present_address")
    private String presentAddress;
    @Size(max = 300)
    @Column(name = "permanent_address")
    private String permanentAddress;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Size(max = 45)
    @Column(name = "contact")
    private String contact;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "approval")
    private Short approval;
    @JoinColumn(name = "student_batch_id_fk", referencedColumnName = "student_batch_id")
    @ManyToOne(optional = false)
    private StudentBatch studentBatchIdFk;
    @JoinColumn(name = "student_info_id_fk", referencedColumnName = "student_info_id")
    @ManyToOne(optional = false)
    private StudentInfo studentInfoIdFk;

    public AdminDashboardProfile() {
    }

    public AdminDashboardProfile(Integer adminDashboardProfileId) {
        this.adminDashboardProfileId = adminDashboardProfileId;
    }

    public AdminDashboardProfile(Integer adminDashboardProfileId, String name) {
        this.adminDashboardProfileId = adminDashboardProfileId;
        this.name = name;
    }

    public Integer getAdminDashboardProfileId() {
        return adminDashboardProfileId;
    }

    public void setAdminDashboardProfileId(Integer adminDashboardProfileId) {
        this.adminDashboardProfileId = adminDashboardProfileId;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Short getApproval() {
        return approval;
    }

    public void setApproval(Short approval) {
        this.approval = approval;
    }

    public StudentBatch getStudentBatchIdFk() {
        return studentBatchIdFk;
    }

    public void setStudentBatchIdFk(StudentBatch studentBatchIdFk) {
        this.studentBatchIdFk = studentBatchIdFk;
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
        hash += (adminDashboardProfileId != null ? adminDashboardProfileId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminDashboardProfile)) {
            return false;
        }
        AdminDashboardProfile other = (AdminDashboardProfile) object;
        if ((this.adminDashboardProfileId == null && other.adminDashboardProfileId != null) || (this.adminDashboardProfileId != null && !this.adminDashboardProfileId.equals(other.adminDashboardProfileId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.AdminDashboardProfile[ adminDashboardProfileId=" + adminDashboardProfileId + " ]";
    }
    
}
