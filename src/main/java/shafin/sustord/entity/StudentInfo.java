/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shafin.sustord.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "student_info")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentInfo.findAll", query = "SELECT s FROM StudentInfo s"),
    @NamedQuery(name = "StudentInfo.findByStudentInfoId", query = "SELECT s FROM StudentInfo s WHERE s.studentInfoId = :studentInfoId"),
    @NamedQuery(name = "StudentInfo.findByRegistrationNo", query = "SELECT s FROM StudentInfo s WHERE s.registrationNo = :registrationNo"),
    @NamedQuery(name = "StudentInfo.findByPassword", query = "SELECT s FROM StudentInfo s WHERE s.password = :password")})
public class StudentInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_info_id")
    private Integer studentInfoId;
    @Size(max = 20)
    @Column(name = "registration_no")
    private String registrationNo;
    @Size(max = 30)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentInfoIdFk")
    private Collection<CourseRegistration> courseRegistrationCollection;
    @JoinColumn(name = "student_batch_id_fk", referencedColumnName = "student_batch_id")
    @ManyToOne
    private StudentBatch studentBatchIdFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentInfoIdFk")
    private Collection<PersonalInfo> personalInfoCollection;

    public StudentInfo() {
    }

    public StudentInfo(Integer studentInfoId) {
        this.studentInfoId = studentInfoId;
    }

    public Integer getStudentInfoId() {
        return studentInfoId;
    }

    public void setStudentInfoId(Integer studentInfoId) {
        this.studentInfoId = studentInfoId;
    }

    public String getRegistrationNo() {
        return registrationNo;
    }

    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<CourseRegistration> getCourseRegistrationCollection() {
        return courseRegistrationCollection;
    }

    public void setCourseRegistrationCollection(Collection<CourseRegistration> courseRegistrationCollection) {
        this.courseRegistrationCollection = courseRegistrationCollection;
    }

    public StudentBatch getStudentBatchIdFk() {
        return studentBatchIdFk;
    }

    public void setStudentBatchIdFk(StudentBatch studentBatchIdFk) {
        this.studentBatchIdFk = studentBatchIdFk;
    }

    @XmlTransient
    public Collection<PersonalInfo> getPersonalInfoCollection() {
        return personalInfoCollection;
    }

    public void setPersonalInfoCollection(Collection<PersonalInfo> personalInfoCollection) {
        this.personalInfoCollection = personalInfoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentInfoId != null ? studentInfoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentInfo)) {
            return false;
        }
        StudentInfo other = (StudentInfo) object;
        if ((this.studentInfoId == null && other.studentInfoId != null) || (this.studentInfoId != null && !this.studentInfoId.equals(other.studentInfoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shafin.mavenproject1.StudentInfo[ studentInfoId=" + studentInfoId + " ]";
    }
    
}
