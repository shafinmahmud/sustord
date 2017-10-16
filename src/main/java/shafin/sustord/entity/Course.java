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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "course")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Course.findAll", query = "SELECT c FROM Course c"),
    @NamedQuery(name = "Course.findByCourseId", query = "SELECT c FROM Course c WHERE c.courseId = :courseId"),
    @NamedQuery(name = "Course.findByCourseCode", query = "SELECT c FROM Course c WHERE c.courseCode = :courseCode"),
    @NamedQuery(name = "Course.findByTitle", query = "SELECT c FROM Course c WHERE c.title = :title"),
    @NamedQuery(name = "Course.findByCredit", query = "SELECT c FROM Course c WHERE c.credit = :credit"),
    @NamedQuery(name = "Course.findByTheoryOrLab", query = "SELECT c FROM Course c WHERE c.theoryOrLab = :theoryOrLab"),
    @NamedQuery(name = "Course.findByReferences", query = "SELECT c FROM Course c WHERE c.references = :references")})
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "course_id")
    private Integer courseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "course_code")
    private String courseCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Column(name = "credit")
    private double credit;
    @Column(name = "theory_or_lab")
    private Integer theoryOrLab;
    @Lob
    @Size(max = 65535)
    @Column(name = "content")
    private String content;
    @Size(max = 500)
    @Column(name = "references")
    private String references;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseIdFk")
    private Collection<Syllabus> syllabusCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseIdFk")
    private Collection<OptionalCourse> optionalCourseCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "courseIdFk")
    private Collection<Prerequisite> prerequisiteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requiredCourseIdFk")
    private Collection<Prerequisite> prerequisiteCollection1;

    public Course() {
    }

    public Course(Integer courseId) {
        this.courseId = courseId;
    }

    public Course(Integer courseId, String courseCode, String title, double credit) {
        this.courseId = courseId;
        this.courseCode = courseCode;
        this.title = title;
        this.credit = credit;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public Integer getTheoryOrLab() {
        return theoryOrLab;
    }

    public void setTheoryOrLab(Integer theoryOrLab) {
        this.theoryOrLab = theoryOrLab;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReferences() {
        return references;
    }

    public void setReferences(String references) {
        this.references = references;
    }

    @XmlTransient
    public Collection<Syllabus> getSyllabusCollection() {
        return syllabusCollection;
    }

    public void setSyllabusCollection(Collection<Syllabus> syllabusCollection) {
        this.syllabusCollection = syllabusCollection;
    }

    @XmlTransient
    public Collection<OptionalCourse> getOptionalCourseCollection() {
        return optionalCourseCollection;
    }

    public void setOptionalCourseCollection(Collection<OptionalCourse> optionalCourseCollection) {
        this.optionalCourseCollection = optionalCourseCollection;
    }

    @XmlTransient
    public Collection<Prerequisite> getPrerequisiteCollection() {
        return prerequisiteCollection;
    }

    public void setPrerequisiteCollection(Collection<Prerequisite> prerequisiteCollection) {
        this.prerequisiteCollection = prerequisiteCollection;
    }

    @XmlTransient
    public Collection<Prerequisite> getPrerequisiteCollection1() {
        return prerequisiteCollection1;
    }

    public void setPrerequisiteCollection1(Collection<Prerequisite> prerequisiteCollection1) {
        this.prerequisiteCollection1 = prerequisiteCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (courseId != null ? courseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Course)) {
            return false;
        }
        Course other = (Course) object;
        if ((this.courseId == null && other.courseId != null) || (this.courseId != null && !this.courseId.equals(other.courseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "shafin.mavenproject1.Course[ courseId=" + courseId + " ]";
    }
    
}
