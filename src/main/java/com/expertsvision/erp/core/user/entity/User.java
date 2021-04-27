package com.expertsvision.erp.core.user.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private Integer userId;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="admin_user")
	private Boolean adminUser;

	@Column(name="direct_mang")
	private Integer directMang;

	@Column(name="group_no")
	private Integer groupNo;

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

	@Column(name="password")
	private String password;

	@Column(name="super_admin")
	private Boolean superAdmin;

	@Column(name="user_d_name")
	private String userDName;

	@Column(name="user_f_name")
	private String userFName;

	public User() {
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Boolean getAdminUser() {
		return this.adminUser;
	}

	public void setAdminUser(Boolean adminUser) {
		this.adminUser = adminUser;
	}

	public Integer getDirectMang() {
		return this.directMang;
	}

	public void setDirectMang(Integer directMang) {
		this.directMang = directMang;
	}

	public Integer getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
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

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getSuperAdmin() {
		return this.superAdmin;
	}

	public void setSuperAdmin(Boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public String getUserDName() {
		return this.userDName;
	}

	public void setUserDName(String userDName) {
		this.userDName = userDName;
	}

	public String getUserFName() {
		return this.userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", addDate=" + addDate + ", addUser=" + addUser + ", adminUser=" + adminUser
				+ ", directMang=" + directMang + ", groupNo=" + groupNo + ", inactive=" + inactive + ", inactiveDate="
				+ inactiveDate + ", inactiveReason=" + inactiveReason + ", inactiveUser=" + inactiveUser
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", password=" + password
				+ ", superAdmin=" + superAdmin + ", userDName=" + userDName + ", userFName=" + userFName + "]";
	}

}