/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.shafin.sustord.entity;

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
@Table(name = "degree")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Degree.findAll", query = "SELECT d FROM Degree d"),
    @NamedQuery(name = "Degree.findByDegreeId", query = "SELECT d FROM Degree d WHERE d.degreeId = :degreeId"),
    @NamedQuery(name = "Degree.findByDegreeType", query = "SELECT d FROM Degree d WHERE d.degreeType = :degreeType"),
    @NamedQuery(name = "Degree.findByDegreeTypeName", query = "SELECT d FROM Degree d WHERE d.degreeTypeName = :degreeTypeName"),
    @NamedQuery(name = "Degree.findByDegreeCategory", query = "SELECT d FROM Degree d WHERE d.degreeCategory = :degreeCategory"),
    @NamedQuery(name = "Degree.findByTotalSemester", query = "SELECT d FROM Degree d WHERE d.totalSemester = :totalSemester")})
public class Degree implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "degree_id")
    private Integer degreeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "degree_type")
    private String degreeType;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 90)
    @Column(name = "degree_type_name")
    private String degreeTypeName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "degree_category")
    private String degreeCategory;
    @Basic(optional = false)
    @NotNull
    @Column(name = "total_semester")
    private int totalSemester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "degreeIdFk")
    private Collection<DegreeOffered> degreeOfferedCollection;

    public Degree() {
    }

    public Degree(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public Degree(Integer degreeId, String degreeType, String degreeTypeName, String degreeCategory, int totalSemester) {
        this.degreeId = degreeId;
        this.degreeType = degreeType;
        this.degreeTypeName = degreeTypeName;
        this.degreeCategory = degreeCategory;
        this.totalSemester = totalSemester;
    }

    public Integer getDegreeId() {
        return degreeId;
    }

    public void setDegreeId(Integer degreeId) {
        this.degreeId = degreeId;
    }

    public String getDegreeType() {
        return degreeType;
    }

    public void setDegreeType(String degreeType) {
        this.degreeType = degreeType;
    }

    public String getDegreeTypeName() {
        return degreeTypeName;
    }

    public void setDegreeTypeName(String degreeTypeName) {
        this.degreeTypeName = degreeTypeName;
    }

    public String getDegreeCategory() {
        return degreeCategory;
    }

    public void setDegreeCategory(String degreeCategory) {
        this.degreeCategory = degreeCategory;
    }

    public int getTotalSemester() {
        return totalSemester;
    }

    public void setTotalSemester(int totalSemester) {
        this.totalSemester = totalSemester;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<DegreeOffered> getDegreeOfferedCollection() {
        return degreeOfferedCollection;
    }

    public void setDegreeOfferedCollection(Collection<DegreeOffered> degreeOfferedCollection) {
        this.degreeOfferedCollection = degreeOfferedCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (degreeId != null ? degreeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Degree)) {
            return false;
        }
        Degree other = (Degree) object;
        if ((this.degreeId == null && other.degreeId != null) || (this.degreeId != null && !this.degreeId.equals(other.degreeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "me.shafin.sustord.entity.Degree[ degreeId=" + degreeId + " ]";
    }
    
}
