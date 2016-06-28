
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "department")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d"),
		@NamedQuery(name = "Department.findByDeptId", query = "SELECT d FROM Department d WHERE d.deptId = :deptId"),
		@NamedQuery(name = "Department.findByDeptCode", query = "SELECT d FROM Department d WHERE d.deptCode = :deptCode"),
		@NamedQuery(name = "Department.findByDeptName", query = "SELECT d FROM Department d WHERE d.deptName = :deptName") })
public class Department implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "dept_id")
	private Integer deptId;

	@Basic(optional = false)
	@Column(name = "dept_code")
	private String deptCode;

	@Basic(optional = false)
	@Column(name = "dept_name")
	private String deptName;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "offeringDeptIdFk")
	private Collection<Syllabus> offeringSyllabusCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "acceptingDeptIdFk")
	private Collection<Syllabus> acceptingSyllabusCollection;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deptIdFk")
	private Collection<DegreeOffered> degreeOfferedCollection;

	@JoinColumn(name = "school_id_fk", referencedColumnName = "school_id")
	@ManyToOne(optional = false)
	private School schoolIdFk;

	public Department() {
	}

	public Department(Integer deptId) {
		this.deptId = deptId;
	}

	public Department(Integer deptId, String deptCode, String deptName) {
		this.deptId = deptId;
		this.deptCode = deptCode;
		this.deptName = deptName;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	@XmlTransient
	public Collection<Syllabus> getOfferingSyllabusCollection() {
		return offeringSyllabusCollection;
	}

	public void setOfferingSyllabusCollection(Collection<Syllabus> syllabusCollection) {
		this.offeringSyllabusCollection = syllabusCollection;
	}

	@XmlTransient
	public Collection<Syllabus> getAcceptingSyllabusCollection1() {
		return acceptingSyllabusCollection;
	}

	public void setAcceptingSyllabusCollection(Collection<Syllabus> syllabusCollection) {
		this.acceptingSyllabusCollection = syllabusCollection;
	}

	@XmlTransient
	public Collection<DegreeOffered> getDegreeOfferedCollection() {
		return degreeOfferedCollection;
	}

	public void setDegreeOfferedCollection(Collection<DegreeOffered> degreeOfferedCollection) {
		this.degreeOfferedCollection = degreeOfferedCollection;
	}

	public School getSchoolIdFk() {
		return schoolIdFk;
	}

	public void setSchoolIdFk(School schoolIdFk) {
		this.schoolIdFk = schoolIdFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (deptId != null ? deptId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof Department)) {
			return false;
		}
		Department other = (Department) object;
		if ((this.deptId == null && other.deptId != null)
				|| (this.deptId != null && !this.deptId.equals(other.deptId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "shafin.sustord.Department[ deptId=" + deptId + " ]";
	}

}
