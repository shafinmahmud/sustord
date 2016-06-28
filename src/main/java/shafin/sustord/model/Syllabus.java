/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shafin.sustord.model;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "syllabus")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Syllabus.findAll", query = "SELECT s FROM Syllabus s"),
    @NamedQuery(name = "Syllabus.findBySyllabusId", query = "SELECT s FROM Syllabus s WHERE s.syllabusId = :syllabusId"),
    @NamedQuery(name = "Syllabus.findBySemester", query = "SELECT s FROM Syllabus s WHERE s.semester = :semester"),
    @NamedQuery(name = "Syllabus.findByHrsWeek", query = "SELECT s FROM Syllabus s WHERE s.hrsWeek = :hrsWeek"),
    @NamedQuery(name = "Syllabus.findByRemarks", query = "SELECT s FROM Syllabus s WHERE s.remarks = :remarks")})
public class Syllabus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "syllabus_id")
    private Integer syllabusId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester")
    private int semester;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "hrs_week")
    private Double hrsWeek;
    @Size(max = 50)
    @Column(name = "remarks")
    private String remarks;
    @JoinColumn(name = "offering_dept_id_fk", referencedColumnName = "dept_id")
    @ManyToOne(optional = false)
    private Department offeringDeptIdFk;
    @JoinColumn(name = "accepting_dept_id_fk", referencedColumnName = "dept_id")
    @ManyToOne(optional = false)
    private Department acceptingDeptIdFk;
    @JoinColumn(name = "course_id_fk", referencedColumnName = "course_id")
    @ManyToOne(optional = false)
    private Course courseIdFk;
    @JoinColumn(name = "student_batch_id_fk", referencedColumnName = "student_batch_id")
    @ManyToOne(optional = false)
    private StudentBatch studentBatchIdFk;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "syllabusIdFk")
    private Collection<AdminDashboardCourseRegistration> adminDashboardCourseRegistrationCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "syllabusIdFk")
    private Collection<Prerequisite> prerequisiteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "syllabusIdFk")
    private Collection<CourseRegistration> courseRegistrationCollection;
    @OneToMany(mappedBy = "syllabusIdFk")
    private Collection<ClassRoutine> classRoutineCollection;

    public Syllabus() {
    }

    public Syllabus(Integer syllabusId) {
        this.syllabusId = syllabusId;
    }

    public Syllabus(Integer syllabusId, int semester) {
        this.syllabusId = syllabusId;
        this.semester = semester;
    }

    public Integer getSyllabusId() {
        return syllabusId;
    }

    public void setSyllabusId(Integer syllabusId) {
        this.syllabusId = syllabusId;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public Double getHrsWeek() {
        return hrsWeek;
    }

    public void setHrsWeek(Double hrsWeek) {
        this.hrsWeek = hrsWeek;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public Course getCourseIdFk() {
        return courseIdFk;
    }

    public void setCourseIdFk(Course courseIdFk) {
        this.courseIdFk = courseIdFk;
    }

    public StudentBatch getStudentBatchIdFk() {
        return studentBatchIdFk;
    }

    public void setStudentBatchIdFk(StudentBatch studentBatchIdFk) {
        this.studentBatchIdFk = studentBatchIdFk;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<AdminDashboardCourseRegistration> getAdminDashboardCourseRegistrationCollection() {
        return adminDashboardCourseRegistrationCollection;
    }

    public void setAdminDashboardCourseRegistrationCollection(Collection<AdminDashboardCourseRegistration> adminDashboardCourseRegistrationCollection) {
        this.adminDashboardCourseRegistrationCollection = adminDashboardCourseRegistrationCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Prerequisite> getPrerequisiteCollection() {
        return prerequisiteCollection;
    }

    public void setPrerequisiteCollection(Collection<Prerequisite> prerequisiteCollection) {
        this.prerequisiteCollection = prerequisiteCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<CourseRegistration> getCourseRegistrationCollection() {
        return courseRegistrationCollection;
    }

    public void setCourseRegistrationCollection(Collection<CourseRegistration> courseRegistrationCollection) {
        this.courseRegistrationCollection = courseRegistrationCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<ClassRoutine> getClassRoutineCollection() {
        return classRoutineCollection;
    }

    public void setClassRoutineCollection(Collection<ClassRoutine> classRoutineCollection) {
        this.classRoutineCollection = classRoutineCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (syllabusId != null ? syllabusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Syllabus)) {
            return false;
        }
        Syllabus other = (Syllabus) object;
        if ((this.syllabusId == null && other.syllabusId != null) || (this.syllabusId != null && !this.syllabusId.equals(other.syllabusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.Syllabus[ syllabusId=" + syllabusId + " ]";
    }
    
}
