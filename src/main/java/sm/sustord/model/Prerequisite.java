/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sm.sustord.model;

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
@Table(name = "prerequisite")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prerequisite.findAll", query = "SELECT p FROM Prerequisite p"),
    @NamedQuery(name = "Prerequisite.findByPrerequisiteId", query = "SELECT p FROM Prerequisite p WHERE p.prerequisiteId = :prerequisiteId")})
public class Prerequisite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prerequisite_id")
    private Integer prerequisiteId;
    @JoinColumn(name = "syllabus_id_fk", referencedColumnName = "syllabus_id")
    @ManyToOne(optional = false)
    private Syllabus syllabusIdFk;
    @JoinColumn(name = "course_id_fk", referencedColumnName = "course_id")
    @ManyToOne(optional = false)
    private Course courseIdFk;

    public Prerequisite() {
    }

    public Prerequisite(Integer prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public Integer getPrerequisiteId() {
        return prerequisiteId;
    }

    public void setPrerequisiteId(Integer prerequisiteId) {
        this.prerequisiteId = prerequisiteId;
    }

    public Syllabus getSyllabusIdFk() {
        return syllabusIdFk;
    }

    public void setSyllabusIdFk(Syllabus syllabusIdFk) {
        this.syllabusIdFk = syllabusIdFk;
    }

    public Course getCourseIdFk() {
        return courseIdFk;
    }

    public void setCourseIdFk(Course courseIdFk) {
        this.courseIdFk = courseIdFk;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prerequisiteId != null ? prerequisiteId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prerequisite)) {
            return false;
        }
        Prerequisite other = (Prerequisite) object;
        if ((this.prerequisiteId == null && other.prerequisiteId != null) || (this.prerequisiteId != null && !this.prerequisiteId.equals(other.prerequisiteId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.Prerequisite[ prerequisiteId=" + prerequisiteId + " ]";
    }
    
}
