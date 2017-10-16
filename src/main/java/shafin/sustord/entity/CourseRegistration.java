/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shafin.sustord.entity;

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
@Table(name = "course_registration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CourseRegistration.findAll", query = "SELECT c FROM CourseRegistration c"),
    @NamedQuery(name = "CourseRegistration.findByCourseRegistrationId", query = "SELECT c FROM CourseRegistration c WHERE c.courseRegistrationId = :courseRegistrationId"),
    @NamedQuery(name = "CourseRegistration.findByAttendYear", query = "SELECT c FROM CourseRegistration c WHERE c.attendYear = :attendYear"),
    @NamedQuery(name = "CourseRegistration.findByAttendSemester", query = "SELECT c FROM CourseRegistration c WHERE c.attendSemester = :attendSemester"),
    @NamedQuery(name = "CourseRegistration.findByIsApproved", query = "SELECT c FROM CourseRegistration c WHERE c.isApproved = :isApproved"),
    @NamedQuery(name = "CourseRegistration.findByGrade", query = "SELECT c FROM CourseRegistration c WHERE c.grade = :grade")})
public class CourseRegistration implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "course_registration_id")
    private Integer courseRegistrationId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attend_year")
    private int attendYear;
    @Basic(optional = false)
    @NotNull
    @Column(name = "attend_semester")
    private int attendSemester;
    @Column(name = "is_approved")
    private Integer isApproved;
    @Size(max = 5)
    @Column(name = "grade")
    private String grade;
    @JoinColumn(name = "student_info_id_fk", referencedColumnName = "student_info_id")
    @ManyToOne(optional = false)
    private StudentInfo studentInfoIdFk;
    @JoinColumn(name = "syllabus_id_fk", referencedColumnName = "syllabus_id")
    @ManyToOne(optional = false)
    private Syllabus syllabusIdFk;

    public CourseRegistration() {
    }

    public CourseRegistration(Integer courseRegistrationId) {
        this.courseRegistrationId = courseRegistrationId;
    }

    public CourseRegistration(Integer courseRegistrationId, int attendYear, int attendSemester) {
        this.courseRegistrationId = courseRegistrationId;
        this.attendYear = attendYear;
        this.attendSemester = attendSemester;
    }

    public Integer getCourseRegistrationId() {
        return courseRegistrationId;
    }

    public void setCourseRegistrationId(Integer courseRegistrationId) {
        this.courseRegistrationId = courseRegistrationId;
    }

    public int getAttendYear() {
        return attendYear;
    }

    public void setAttendYear(int attendYear) {
        this.attendYear = attendYear;
    }

    public int getAttendSemester() {
        return attendSemester;
    }

    public void setAttendSemester(int attendSemester) {
        this.attendSemester = attendSemester;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public StudentInfo getStudentInfoIdFk() {
        return studentInfoIdFk;
    }

    public void setStudentInfoIdFk(StudentInfo studentInfoIdFk) {
        this.studentInfoIdFk = studentInfoIdFk;
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
        hash += (courseRegistrationId != null ? courseRegistrationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CourseRegistration)) {
            return false;
        }
        CourseRegistration other = (CourseRegistration) object;
        if ((this.courseRegistrationId == null && other.courseRegistrationId != null) || (this.courseRegistrationId != null && !this.courseRegistrationId.equals(other.courseRegistrationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shafin.mavenproject1.CourseRegistration[ courseRegistrationId=" + courseRegistrationId + " ]";
    }
    
}
