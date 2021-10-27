package com.expertsvision.erp.masterdata.employees.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the emp_info database table.
 * 
 */
@Entity
@Table(name="emp_info")
@NamedQuery(name="EmpInfo.findAll", query="SELECT e FROM EmpInfo e")
public class EmpInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="emp_no")
	private Integer empNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="emp_d_name")
	private String empDName;

	@Column(name="emp_f_name")
	private String empFName;

	@Column(name="inactive")
	private Boolean inactive;

	@Column(name="inactive_date")
	private Timestamp inactiveDate;

	@Column(name="inactive_reason")
	private String inactiveReason;

	@Column(name="inactive_user")
	private Integer inactiveUser;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	public EmpInfo() {
	}

	public Integer getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Integer getAddUser() {
		return this.addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public String getEmpDName() {
		return this.empDName;
	}

	public void setEmpDName(String empDName) {
		this.empDName = empDName;
	}

	public String getEmpFName() {
		return this.empFName;
	}

	public void setEmpFName(String empFName) {
		this.empFName = empFName;
	}

	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public Timestamp getInactiveDate() {
		return this.inactiveDate;
	}

	public void setInactiveDate(Timestamp inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public String getInactiveReason() {
		return this.inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public Integer getInactiveUser() {
		return this.inactiveUser;
	}

	public void setInactiveUser(Integer inactiveUser) {
		this.inactiveUser = inactiveUser;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}
	
	@Override
	public String toString() {
		return "EmpInfo [empNo=" + empNo + ", addDate=" + addDate + ", addUser=" + addUser + ", empDName=" + empDName
				+ ", empFName=" + empFName + ", inactive=" + inactive + ", inactiveDate=" + inactiveDate
				+ ", inactiveReason=" + inactiveReason + ", inactiveUser=" + inactiveUser + ", modifyDate=" + modifyDate
				+ ", modifyUser=" + modifyUser + "]";
	}

}