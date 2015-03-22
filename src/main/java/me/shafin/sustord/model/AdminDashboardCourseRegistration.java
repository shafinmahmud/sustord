/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "admin_dashboard_course_registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AdminDashboardCourseRegistration.findAll", query = "SELECT a FROM AdminDashboardCourseRegistration a"),
    @NamedQuery(name = "AdminDashboardCourseRegistration.findByAdminDashboardCourseRegistrationId", query = "SELECT a FROM AdminDashboardCourseRegistration a WHERE a.adminDashboardCourseRegistrationId = :adminDashboardCourseRegistrationId"),
    @NamedQuery(name = "AdminDashboardCourseRegistration.findByIntakeSemester", query = "SELECT a FROM AdminDashboardCourseRegistration a WHERE a.intakeSemester = :intakeSemester"),
    @NamedQuery(name = "AdminDashboardCourseRegistration.findByGrade", query = "SELECT a FROM AdminDashboardCourseRegistration a WHERE a.grade = :grade"),
    @NamedQuery(name = "AdminDashboardCourseRegistration.findByApproval", query = "SELECT a FROM AdminDashboardCourseRegistration a WHERE a.approval = :approval")})
public class AdminDashboardCourseRegistration implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "admin_dashboard_course_registration_id")
    private Integer adminDashboardCourseRegistrationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "intake_semester")
    private int intakeSemester;
    @Size(max = 5)
    @Column(name = "grade")
    private String grade;
    @Column(name = "approval")
    private Short approval;
    @JoinColumn(name = "student_info_id_fk", referencedColumnName = "student_info_id")
    @ManyToOne(optional = false)
    private StudentInfo studentInfoIdFk;
    @JoinColumn(name = "student_batch_id_fk", referencedColumnName = "student_batch_id")
    @ManyToOne(optional = false)
    private StudentBatch studentBatchIdFk;
    @JoinColumn(name = "syllabus_id_fk", referencedColumnName = "syllabus_id")
    @ManyToOne(optional = false)
    private Syllabus syllabusIdFk;

    public AdminDashboardCourseRegistration() {
    }

    public AdminDashboardCourseRegistration(Integer adminDashboardCourseRegistrationId) {
        this.adminDashboardCourseRegistrationId = adminDashboardCourseRegistrationId;
    }

    public AdminDashboardCourseRegistration(Integer adminDashboardCourseRegistrationId, int intakeSemester) {
        this.adminDashboardCourseRegistrationId = adminDashboardCourseRegistrationId;
        this.intakeSemester = intakeSemester;
    }

    public Integer getAdminDashboardCourseRegistrationId() {
        return adminDashboardCourseRegistrationId;
    }

    public void setAdminDashboardCourseRegistrationId(Integer adminDashboardCourseRegistrationId) {
        this.adminDashboardCourseRegistrationId = adminDashboardCourseRegistrationId;
    }

    public int getIntakeSemester() {
        return intakeSemester;
    }

    public void setIntakeSemester(int intakeSemester) {
        this.intakeSemester = intakeSemester;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Short getApproval() {
        return approval;
    }

    public void setApproval(Short approval) {
        this.approval = approval;
    }

    public StudentInfo getStudentInfoIdFk() {
        return studentInfoIdFk;
    }

    public void setStudentInfoIdFk(StudentInfo studentInfoIdFk) {
        this.studentInfoIdFk = studentInfoIdFk;
    }

    public StudentBatch getStudentBatchIdFk() {
        return studentBatchIdFk;
    }

    public void setStudentBatchIdFk(StudentBatch studentBatchIdFk) {
        this.studentBatchIdFk = studentBatchIdFk;
    }

    public Syllabus getSyllabusIdFk() {
        return syllabusIdFk;
    }

    public void setSyllabusIdFk(Syllabus syllabusIdFk) {
        this.syllabusIdFk = syllabusIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminDashboardCourseRegistrationId != null ? adminDashboardCourseRegistrationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminDashboardCourseRegistration)) {
            return false;
        }
        AdminDashboardCourseRegistration other = (AdminDashboardCourseRegistration) object;
        if ((this.adminDashboardCourseRegistrationId == null && other.adminDashboardCourseRegistrationId != null) || (this.adminDashboardCourseRegistrationId != null && !this.adminDashboardCourseRegistrationId.equals(other.adminDashboardCourseRegistrationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.AdminDashboardCourseRegistration[ adminDashboardCourseRegistrationId=" + adminDashboardCourseRegistrationId + " ]";
    }
    
}
