package com.expertsvision.erp.masterdata.employees.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the emp_info_view database table.
 * 
 */
@Entity
@Table(name="emp_info_view")
@NamedQuery(name="EmpInfoView.findAll", query="SELECT e FROM EmpInfoView e")
public class EmpInfoView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("add_date")
	@Column(name="add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	@Column(name="add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	@Column(name="add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	@Column(name="add_user_f_name")
	private String addUserFName;

	@JsonProperty("emp_d_name")
	@Column(name="emp_d_name")
	private String empDName;

	@JsonProperty("emp_f_name")
	@Column(name="emp_f_name")
	private String empFName;

	@Id
	@JsonProperty("emp_no")
	@Column(name="emp_no")
	private Integer empNo;

	@JsonProperty("inactive")
	@Column(name="inactive")
	private Boolean inactive;

	@JsonProperty("inactive_date")
	@Column(name="inactive_date")
	private Timestamp inactiveDate;

	@JsonProperty("inactive_reason")
	@Column(name="inactive_reason")
	private String inactiveReason;

	@JsonProperty("inactive_user")
	@Column(name="inactive_user")
	private Integer inactiveUser;

	@JsonProperty("inactive_user_d_name")
	@Column(name="inactive_user_d_name")
	private String inactiveUserDName;

	@JsonProperty("inactive_user_f_name")
	@Column(name="inactive_user_f_name")
	private String inactiveUserFName;

	@JsonProperty("modify_date")
	@Column(name="modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	@Column(name="modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	@Column(name="modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	@Column(name="modify_user_f_name")
	private String modifyUserFName;

	public EmpInfoView() {
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

	public String getAddUserDName() {
		return this.addUserDName;
	}

	public void setAddUserDName(String addUserDName) {
		this.addUserDName = addUserDName;
	}

	public String getAddUserFName() {
		return this.addUserFName;
	}

	public void setAddUserFName(String addUserFName) {
		this.addUserFName = addUserFName;
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

	public Integer getEmpNo() {
		return this.empNo;
	}

	public void setEmpNo(Integer empNo) {
		this.empNo = empNo;
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

	public String getInactiveUserDName() {
		return this.inactiveUserDName;
	}

	public void setInactiveUserDName(String inactiveUserDName) {
		this.inactiveUserDName = inactiveUserDName;
	}

	public String getInactiveUserFName() {
		return this.inactiveUserFName;
	}

	public void setInactiveUserFName(String inactiveUserFName) {
		this.inactiveUserFName = inactiveUserFName;
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

	public String getModifyUserDName() {
		return this.modifyUserDName;
	}

	public void setModifyUserDName(String modifyUserDName) {
		this.modifyUserDName = modifyUserDName;
	}

	public String getModifyUserFName() {
		return this.modifyUserFName;
	}

	public void setModifyUserFName(String modifyUserFName) {
		this.modifyUserFName = modifyUserFName;
	}

	@Override
	public String toString() {
		return "EmpInfoView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", empDName=" + empDName + ", empFName=" + empFName + ", empNo="
				+ empNo + ", inactive=" + inactive + ", inactiveDate=" + inactiveDate + ", inactiveReason="
				+ inactiveReason + ", inactiveUser=" + inactiveUser + ", inactiveUserDName=" + inactiveUserDName
				+ ", inactiveUserFName=" + inactiveUserFName + ", modifyDate=" + modifyDate + ", modifyUser="
				+ modifyUser + ", modifyUserDName=" + modifyUserDName + ", modifyUserFName=" + modifyUserFName + "]";
	}

}