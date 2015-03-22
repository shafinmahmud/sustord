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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "class_routine")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassRoutine.findAll", query = "SELECT c FROM ClassRoutine c"),
    @NamedQuery(name = "ClassRoutine.findByClassRoutineId", query = "SELECT c FROM ClassRoutine c WHERE c.classRoutineId = :classRoutineId"),
    @NamedQuery(name = "ClassRoutine.findByYear", query = "SELECT c FROM ClassRoutine c WHERE c.year = :year"),
    @NamedQuery(name = "ClassRoutine.findBySemester", query = "SELECT c FROM ClassRoutine c WHERE c.semester = :semester")})
public class ClassRoutine implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "class_routine_id")
    private Integer classRoutineId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "year")
    private int year;
    @Basic(optional = false)
    @NotNull
    @Column(name = "semester")
    private int semester;
    @JoinColumn(name = "time_slot_id_fk", referencedColumnName = "time_slot_id")
    @ManyToOne(optional = false)
    private TimeSlot timeSlotIdFk;
    @JoinColumn(name = "syllabus_id_fk", referencedColumnName = "syllabus_id")
    @ManyToOne
    private Syllabus syllabusIdFk;

    public ClassRoutine() {
    }

    public ClassRoutine(Integer classRoutineId) {
        this.classRoutineId = classRoutineId;
    }

    public ClassRoutine(Integer classRoutineId, int year, int semester) {
        this.classRoutineId = classRoutineId;
        this.year = year;
        this.semester = semester;
    }

    public Integer getClassRoutineId() {
        return classRoutineId;
    }

    public void setClassRoutineId(Integer classRoutineId) {
        this.classRoutineId = classRoutineId;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public TimeSlot getTimeSlotIdFk() {
        return timeSlotIdFk;
    }

    public void setTimeSlotIdFk(TimeSlot timeSlotIdFk) {
        this.timeSlotIdFk = timeSlotIdFk;
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
        hash += (classRoutineId != null ? classRoutineId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ClassRoutine)) {
            return false;
        }
        ClassRoutine other = (ClassRoutine) object;
        if ((this.classRoutineId == null && other.classRoutineId != null) || (this.classRoutineId != null && !this.classRoutineId.equals(other.classRoutineId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.ClassRoutine[ classRoutineId=" + classRoutineId + " ]";
    }
    
}
