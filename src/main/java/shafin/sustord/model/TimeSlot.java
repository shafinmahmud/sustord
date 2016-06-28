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
@Table(name = "time_slot")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TimeSlot.findAll", query = "SELECT t FROM TimeSlot t"),
    @NamedQuery(name = "TimeSlot.findByTimeSlotId", query = "SELECT t FROM TimeSlot t WHERE t.timeSlotId = :timeSlotId"),
    @NamedQuery(name = "TimeSlot.findByDay", query = "SELECT t FROM TimeSlot t WHERE t.day = :day"),
    @NamedQuery(name = "TimeSlot.findByStart", query = "SELECT t FROM TimeSlot t WHERE t.start = :start"),
    @NamedQuery(name = "TimeSlot.findByEnd", query = "SELECT t FROM TimeSlot t WHERE t.end = :end")})
public class TimeSlot implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "time_slot_id")
    private Integer timeSlotId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "day")
    private String day;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "start")
    private String start;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "end")
    private String end;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "timeSlotIdFk")
    private Collection<ClassRoutine> classRoutineCollection;

    public TimeSlot() {
    }

    public TimeSlot(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public TimeSlot(Integer timeSlotId, String day, String start, String end) {
        this.timeSlotId = timeSlotId;
        this.day = day;
        this.start = start;
        this.end = end;
    }

    public Integer getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(Integer timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
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
        hash += (timeSlotId != null ? timeSlotId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TimeSlot)) {
            return false;
        }
        TimeSlot other = (TimeSlot) object;
        if ((this.timeSlotId == null && other.timeSlotId != null) || (this.timeSlotId != null && !this.timeSlotId.equals(other.timeSlotId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.TimeSlot[ timeSlotId=" + timeSlotId + " ]";
    }
    
}
