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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "optional_course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OptionalCourse.findAll", query = "SELECT o FROM OptionalCourse o"),
    @NamedQuery(name = "OptionalCourse.findByOptionalCourseId", query = "SELECT o FROM OptionalCourse o WHERE o.optionalCourseId = :optionalCourseId")})
public class OptionalCourse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "optional_course_id")
    private Integer optionalCourseId;
    @JoinColumn(name = "course_id_fk", referencedColumnName = "course_id")
    @ManyToOne(optional = false)
    private Course courseIdFk;
    @JoinColumn(name = "offering_dept_id_fk", referencedColumnName = "dept_id")
    @ManyToOne(optional = false)
    private Department offeringDeptIdFk;
    @JoinColumn(name = "accepting_dept_id_fk", referencedColumnName = "dept_id")
    @ManyToOne(optional = false)
    private Department acceptingDeptIdFk;
    @JoinColumn(name = "student_batch_id_fk", referencedColumnName = "student_batch_id")
    @ManyToOne(optional = false)
    private StudentBatch studentBatchIdFk;

    public OptionalCourse() {
    }

    public OptionalCourse(Integer optionalCourseId) {
        this.optionalCourseId = optionalCourseId;
    }

    public Integer getOptionalCourseId() {
        return optionalCourseId;
    }

    public void setOptionalCourseId(Integer optionalCourseId) {
        this.optionalCourseId = optionalCourseId;
    }

    public Course getCourseIdFk() {
        return courseIdFk;
    }

    public void setCourseIdFk(Course courseIdFk) {
        this.courseIdFk = courseIdFk;
    }

    public Department getOfferingDeptIdFk() {
        return offeringDeptIdFk;
    }

    public void setOfferingDeptIdFk(Department offeringDeptIdFk) {
        this.offeringDeptIdFk = offeringDeptIdFk;
    }

    public Department getAcceptingDeptIdFk() {
        return acceptingDeptIdFk;
    }

    public void setAcceptingDeptIdFk(Department acceptingDeptIdFk) {
        this.acceptingDeptIdFk = acceptingDeptIdFk;
    }

    public StudentBatch getStudentBatchIdFk() {
        return studentBatchIdFk;
    }

    public void setStudentBatchIdFk(StudentBatch studentBatchIdFk) {
        this.studentBatchIdFk = studentBatchIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (optionalCourseId != null ? optionalCourseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OptionalCourse)) {
            return false;
        }
        OptionalCourse other = (OptionalCourse) object;
        if ((this.optionalCourseId == null && other.optionalCourseId != null) || (this.optionalCourseId != null && !this.optionalCourseId.equals(other.optionalCourseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shafin.mavenproject1.OptionalCourse[ optionalCourseId=" + optionalCourseId + " ]";
    }
    
}
