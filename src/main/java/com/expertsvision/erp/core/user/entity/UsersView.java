package com.expertsvision.erp.core.user.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the users_view database view.
 * 
 */
@Entity
@Table(name="users_view")
@NamedQuery(name="UsersView.findAll", query="SELECT u FROM UsersView u")
public class UsersView implements Serializable {
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

	@JsonProperty("admin_user")
	@Column(name="admin_user")
	private Boolean adminUser;

	@JsonProperty("direct_mang")
	@Column(name="direct_mang")
	private Integer directMang;

	@JsonProperty("direct_mang_d_name")
	@Column(name="direct_mang_d_name")
	private String directMangDName;

	@JsonProperty("direct_mang_f_name")
	@Column(name="direct_mang_f_name")
	private String directMangFName;

	@JsonProperty("group_no")
	@Column(name="group_no")
	private Integer groupNo;

	@JsonProperty("group_no_d_name")
	@Column(name="group_no_d_name")
	private String groupNoDName;

	@JsonProperty("group_no_f_name")
	@Column(name="group_no_f_name")
	private String groupNoFName;

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

	@JsonProperty("password")
	@Column(name="password")
	private String password;

	@JsonProperty("super_admin")
	@Column(name="super_admin")
	private Boolean superAdmin;

	@JsonProperty("user_d_name")
	@Column(name="user_d_name")
	private String userDName;

	@JsonProperty("user_f_name")
	@Column(name="user_f_name")
	private String userFName;

	@Id
	@JsonProperty("user_id")
	@Column(name="user_id")
	private Integer userId;
	

	public UsersView() {
	}
	
	public UsersView(Integer userId) {
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

	public String getDirectMangDName() {
		return this.directMangDName;
	}

	public void setDirectMangDName(String directMangDName) {
		this.directMangDName = directMangDName;
	}

	public String getDirectMangFName() {
		return this.directMangFName;
	}

	public void setDirectMangFName(String directMangFName) {
		this.directMangFName = directMangFName;
	}

	public Integer getGroupNo() {
		return this.groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupNoDName() {
		return this.groupNoDName;
	}

	public void setGroupNoDName(String groupNoDName) {
		this.groupNoDName = groupNoDName;
	}

	public String getGroupNoFName() {
		return this.groupNoFName;
	}

	public void setGroupNoFName(String groupNoFName) {
		this.groupNoFName = groupNoFName;
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UsersView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", adminUser=" + adminUser + ", directMang=" + directMang
				+ ", directMangDName=" + directMangDName + ", directMangFName=" + directMangFName + ", groupNo="
				+ groupNo + ", groupNoDName=" + groupNoDName + ", groupNoFName=" + groupNoFName + ", inactive="
				+ inactive + ", inactiveDate=" + inactiveDate + ", inactiveReason=" + inactiveReason + ", inactiveUser="
				+ inactiveUser + ", inactiveUserDName=" + inactiveUserDName + ", inactiveUserFName=" + inactiveUserFName
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName
				+ ", modifyUserFName=" + modifyUserFName + ", password=" + password + ", superAdmin=" + superAdmin
				+ ", userDName=" + userDName + ", userFName=" + userFName + ", userId=" + userId + "]";
	}
	

}