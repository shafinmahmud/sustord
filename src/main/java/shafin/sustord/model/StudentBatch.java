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
@Table(name = "student_batch")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentBatch.findAll", query = "SELECT s FROM StudentBatch s"),
    @NamedQuery(name = "StudentBatch.findByStudentBatchId", query = "SELECT s FROM StudentBatch s WHERE s.studentBatchId = :studentBatchId"),
    @NamedQuery(name = "StudentBatch.findBySession", query = "SELECT s FROM StudentBatch s WHERE s.session = :session"),
    @NamedQuery(name = "StudentBatch.findByMaxSemester", query = "SELECT s FROM StudentBatch s WHERE s.maxSemester = :maxSemester")})
public class StudentBatch implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_batch_id")
    private Integer studentBatchId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "session")
    private String session;
    @Column(name = "max_semester")
    private Integer maxSemester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentBatchIdFk")
    private Collection<AdminInfo> adminInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentBatchIdFk")
    private Collection<Syllabus> syllabusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentBatchIdFk")
    private Collection<AdminDashboardCourseRegistration> adminDashboardCourseRegistrationCollection;
    @JoinColumn(name = "degree_offered_id_fk", referencedColumnName = "degree_offered_id")
    @ManyToOne(optional = false)
    private DegreeOffered degreeOfferedIdFk;
    @OneToMany(mappedBy = "studentBatchIdFk")
    private Collection<StudentInfo> studentInfoCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "studentBatchIdFk")
    private Collection<AdminDashboardProfile> adminDashboardProfileCollection;

    public StudentBatch() {
    }

    public StudentBatch(Integer studentBatchId) {
        this.studentBatchId = studentBatchId;
    }

    public StudentBatch(Integer studentBatchId, String session) {
        this.studentBatchId = studentBatchId;
        this.session = session;
    }

    public Integer getStudentBatchId() {
        return studentBatchId;
    }

    public void setStudentBatchId(Integer studentBatchId) {
        this.studentBatchId = studentBatchId;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public Integer getMaxSemester() {
        return maxSemester;
    }

    public void setMaxSemester(Integer maxSemester) {
        this.maxSemester = maxSemester;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<AdminInfo> getAdminInfoCollection() {
        return adminInfoCollection;
    }

    public void setAdminInfoCollection(Collection<AdminInfo> adminInfoCollection) {
        this.adminInfoCollection = adminInfoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Syllabus> getSyllabusCollection() {
        return syllabusCollection;
    }

    public void setSyllabusCollection(Collection<Syllabus> syllabusCollection) {
        this.syllabusCollection = syllabusCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<AdminDashboardCourseRegistration> getAdminDashboardCourseRegistrationCollection() {
        return adminDashboardCourseRegistrationCollection;
    }

    public void setAdminDashboardCourseRegistrationCollection(Collection<AdminDashboardCourseRegistration> adminDashboardCourseRegistrationCollection) {
        this.adminDashboardCourseRegistrationCollection = adminDashboardCourseRegistrationCollection;
    }

    public DegreeOffered getDegreeOfferedIdFk() {
        return degreeOfferedIdFk;
    }

    public void setDegreeOfferedIdFk(DegreeOffered degreeOfferedIdFk) {
        this.degreeOfferedIdFk = degreeOfferedIdFk;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<StudentInfo> getStudentInfoCollection() {
        return studentInfoCollection;
    }

    public void setStudentInfoCollection(Collection<StudentInfo> studentInfoCollection) {
        this.studentInfoCollection = studentInfoCollection;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<AdminDashboardProfile> getAdminDashboardProfileCollection() {
        return adminDashboardProfileCollection;
    }

    public void setAdminDashboardProfileCollection(Collection<AdminDashboardProfile> adminDashboardProfileCollection) {
        this.adminDashboardProfileCollection = adminDashboardProfileCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentBatchId != null ? studentBatchId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentBatch)) {
            return false;
        }
        StudentBatch other = (StudentBatch) object;
        if ((this.studentBatchId == null && other.studentBatchId != null) || (this.studentBatchId != null && !this.studentBatchId.equals(other.studentBatchId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.StudentBatch[ studentBatchId=" + studentBatchId + " ]";
    }
    
}
