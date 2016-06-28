
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
@Table(name = "degree_offered")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "DegreeOffered.findAll", query = "SELECT d FROM DegreeOffered d"),
		@NamedQuery(name = "DegreeOffered.findByDegreeOfferedId", query = "SELECT d FROM DegreeOffered d WHERE d.degreeOfferedId = :degreeOfferedId") })
public class DegreeOffered implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "degree_offered_id")
	private Integer degreeOfferedId;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "degreeOfferedIdFk")
	private Collection<StudentBatch> studentBatchCollection;
	
	@JoinColumn(name = "dept_id_fk", referencedColumnName = "dept_id")
	@ManyToOne(optional = false)
	private Department deptIdFk;
	
	@JoinColumn(name = "degree_id_fk", referencedColumnName = "degree_id")
	@ManyToOne(optional = false)
	private Degree degreeIdFk;

	public DegreeOffered() {
	}

	public DegreeOffered(Integer degreeOfferedId) {
		this.degreeOfferedId = degreeOfferedId;
	}

	public Integer getDegreeOfferedId() {
		return degreeOfferedId;
	}

	public void setDegreeOfferedId(Integer degreeOfferedId) {
		this.degreeOfferedId = degreeOfferedId;
	}

	@XmlTransient
	public Collection<StudentBatch> getStudentBatchCollection() {
		return studentBatchCollection;
	}

	public void setStudentBatchCollection(Collection<StudentBatch> studentBatchCollection) {
		this.studentBatchCollection = studentBatchCollection;
	}

	public Department getDeptIdFk() {
		return deptIdFk;
	}

	public void setDeptIdFk(Department deptIdFk) {
		this.deptIdFk = deptIdFk;
	}

	public Degree getDegreeIdFk() {
		return degreeIdFk;
	}

	public void setDegreeIdFk(Degree degreeIdFk) {
		this.degreeIdFk = degreeIdFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (degreeOfferedId != null ? degreeOfferedId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof DegreeOffered)) {
			return false;
		}
		DegreeOffered other = (DegreeOffered) object;
		if ((this.degreeOfferedId == null && other.degreeOfferedId != null)
				|| (this.degreeOfferedId != null && !this.degreeOfferedId.equals(other.degreeOfferedId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "shafin.sustord.DegreeOffered[ degreeOfferedId=" + degreeOfferedId + " ]";
	}

}
