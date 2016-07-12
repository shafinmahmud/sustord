/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shafin.sustord.entity;

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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SHAFIN
 */
@Entity
@Table(name = "admin_info")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "AdminInfo.findAll", query = "SELECT a FROM AdminInfo a"),
		@NamedQuery(name = "AdminInfo.findByAdminInfoId", query = "SELECT a FROM AdminInfo a WHERE a.adminInfoId = :adminInfoId"),
		@NamedQuery(name = "AdminInfo.findByAdminNo", query = "SELECT a FROM AdminInfo a WHERE a.adminNo = :adminNo"),
		@NamedQuery(name = "AdminInfo.findByPassword", query = "SELECT a FROM AdminInfo a WHERE a.password = :password") })
public class AdminInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "admin_info_id")
	private Integer adminInfoId;
	@Basic(optional = false)
	@NotNull
	@Size(min = 1, max = 20)
	@Column(name = "admin_no")
	private String adminNo;
	@Size(max = 30)
	@Column(name = "password")
	private String password;
	@JoinColumn(name = "student_batch_id_fk", referencedColumnName = "student_batch_id")
	@ManyToOne(optional = false)
	private StudentBatch studentBatchIdFk;

	public AdminInfo() {
	}

	public AdminInfo(Integer adminInfoId) {
		this.adminInfoId = adminInfoId;
	}

	public AdminInfo(Integer adminInfoId, String adminNo) {
		this.adminInfoId = adminInfoId;
		this.adminNo = adminNo;
	}

	public Integer getAdminInfoId() {
		return adminInfoId;
	}

	public void setAdminInfoId(Integer adminInfoId) {
		this.adminInfoId = adminInfoId;
	}

	public String getAdminNo() {
		return adminNo;
	}

	public void setAdminNo(String adminNo) {
		this.adminNo = adminNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public StudentBatch getStudentBatchIdFk() {
		return studentBatchIdFk;
	}

	public void setStudentBatchIdFk(StudentBatch studentBatchIdFk) {
		this.studentBatchIdFk = studentBatchIdFk;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (adminInfoId != null ? adminInfoId.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		// TODO: Warning - this method won't work in the case the id fields are
		// not set
		if (!(object instanceof AdminInfo)) {
			return false;
		}
		AdminInfo other = (AdminInfo) object;
		if ((this.adminInfoId == null && other.adminInfoId != null)
				|| (this.adminInfoId != null && !this.adminInfoId.equals(other.adminInfoId))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "shafin.mavenproject1.AdminInfo[ adminInfoId=" + adminInfoId + " ]";
	}

}
