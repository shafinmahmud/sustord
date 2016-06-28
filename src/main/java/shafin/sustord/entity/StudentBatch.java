
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
@Table(name = "student_batch")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "StudentBatch.findAll", query = "SELECT s FROM StudentBatch s"),
		@NamedQuery(name = "StudentBatch.findByStudentBatchId", query = "SELECT s FROM StudentBatch s WHERE s.studentBatchId = :studentBatchId"),
		@NamedQuery(name = "StudentBatch.findBySession", query = "SELECT s FROM StudentBatch s WHERE s.session = :session"),
		@NamedQuery(name = "StudentBatch.findByMaxSemester", query = "SELECT s FROM StudentBatch s WHERE s.maxSemester = :maxSemester"),
		@NamedQuery(name = "StudentBatch.findByBatchTag", query = "SELECT s FROM StudentBatch s WHERE s.batchTag = :batchTag") })
public class StudentBatch implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "student_batch_id")
	private Integer studentBatchId;
	
	@Basic(optional = false)
	@Column(name = "session")
	private String session;
	
	@Column(name = "max_semester")
	private Integer maxSemester;
	
	@Column(name = "batch_tag")
	private String batchTag;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "studentBatchIdFk")
	private Collection<AdminInfo> adminInfoCollection;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "studentBatchIdFk")
	private Collection<Syllabus> syllabusCollection;
	
	@JoinColumn(name = "degree_offered_id_fk", referencedColumnName = "degree_offered_id")
	@ManyToOne(optional = false)
	private DegreeOffered degreeOfferedIdFk;
	
	@OneToMany(mappedBy = "studentBatchIdFk")
	private Collection<StudentInfo> studentInfoCollection;

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

	public String getBatchTag() {
		return batchTag;
	}

	public void setBatchTag(String batchTag) {
		this.batchTag = batchTag;
	}

	@XmlTransient
	public Collection<AdminInfo> getAdminInfoCollection() {
		return adminInfoCollection;
	}

	public void setAdminInfoCollection(Collection<AdminInfo> adminInfoCollection) {
		this.adminInfoCollection = adminInfoCollection;
	}

	@XmlTransient
	public Collection<Syllabus> getSyllabusCollection() {
		return syllabusCollection;
	}

	public void setSyllabusCollection(Collection<Syllabus> syllabusCollection) {
		this.syllabusCollection = syllabusCollection;
	}

	public DegreeOffered getDegreeOfferedIdFk() {
		return degreeOfferedIdFk;
	}

	public void setDegreeOfferedIdFk(DegreeOffered degreeOfferedIdFk) {
		this.degreeOfferedIdFk = degreeOfferedIdFk;
	}

	@XmlTransient
	public Collection<StudentInfo> getStudentInfoCollection() {
		return studentInfoCollection;
	}

	public void setStudentInfoCollection(Collection<StudentInfo> studentInfoCollection) {
		this.studentInfoCollection = studentInfoCollection;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (studentBatchId != null ? studentBatchId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof StudentBatch)) {
			return false;
		}
		StudentBatch other = (StudentBatch) object;
		if ((this.studentBatchId == null && other.studentBatchId != null)
				|| (this.studentBatchId != null && !this.studentBatchId.equals(other.studentBatchId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "shafin.sustord.StudentBatch[ studentBatchId=" + studentBatchId + " ]";
	}

}
