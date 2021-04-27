package com.expertsvision.erp.core.user.dto;

import java.sql.Timestamp;


import com.fasterxml.jackson.annotation.JsonProperty;

public class UsersDTO {

	@JsonProperty("add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	private String addUserFName;

	@JsonProperty("admin_user")
	private Boolean adminUser;

	@JsonProperty("direct_mang")
	private Integer directMang;

	@JsonProperty("direct_mang_d_name")
	private String directMangDName;

	@JsonProperty("direct_mang_f_name")
	private String directMangFName;

	@JsonProperty("group_no")
	private Integer groupNo;

	@JsonProperty("group_no_d_name")
	private String groupNoDName;

	@JsonProperty("group_no_f_name")
	private String groupNoFName;

	@JsonProperty("inactive")
	private Boolean inactive;

	@JsonProperty("inactive_date")
	private Timestamp inactiveDate;

	@JsonProperty("inactive_reason")
	private String inactiveReason;

	@JsonProperty("inactive_user")
	private Integer inactiveUser;

	@JsonProperty("inactive_user_d_name")
	private String inactiveUserDName;

	@JsonProperty("inactive_user_f_name")
	private String inactiveUserFName;

	@JsonProperty("modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	private String modifyUserFName;

	@JsonProperty("password")
	private String password;

	@JsonProperty("super_admin")
	private Boolean superAdmin;

	@JsonProperty("user_d_name")
	private String userDName;

	@JsonProperty("user_f_name")
	private String userFName;

	@JsonProperty("user_id")
	private Integer userId;

	public Timestamp getAddDate() {
		return addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Integer getAddUser() {
		return addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public String getAddUserDName() {
		return addUserDName;
	}

	public void setAddUserDName(String addUserDName) {
		this.addUserDName = addUserDName;
	}

	public String getAddUserFName() {
		return addUserFName;
	}

	public void setAddUserFName(String addUserFName) {
		this.addUserFName = addUserFName;
	}

	public Boolean getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(Boolean adminUser) {
		this.adminUser = adminUser;
	}

	public Integer getDirectMang() {
		return directMang;
	}

	public void setDirectMang(Integer directMang) {
		this.directMang = directMang;
	}

	public String getDirectMangDName() {
		return directMangDName;
	}

	public void setDirectMangDName(String directMangDName) {
		this.directMangDName = directMangDName;
	}

	public String getDirectMangFName() {
		return directMangFName;
	}

	public void setDirectMangFName(String directMangFName) {
		this.directMangFName = directMangFName;
	}

	public Integer getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(Integer groupNo) {
		this.groupNo = groupNo;
	}

	public String getGroupNoDName() {
		return groupNoDName;
	}

	public void setGroupNoDName(String groupNoDName) {
		this.groupNoDName = groupNoDName;
	}

	public String getGroupNoFName() {
		return groupNoFName;
	}

	public void setGroupNoFName(String groupNoFName) {
		this.groupNoFName = groupNoFName;
	}

	public Boolean getInactive() {
		return inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public Timestamp getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(Timestamp inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public String getInactiveReason() {
		return inactiveReason;
	}

	public void setInactiveReason(String inactiveReason) {
		this.inactiveReason = inactiveReason;
	}

	public Integer getInactiveUser() {
		return inactiveUser;
	}

	public void setInactiveUser(Integer inactiveUser) {
		this.inactiveUser = inactiveUser;
	}

	public String getInactiveUserDName() {
		return inactiveUserDName;
	}

	public void setInactiveUserDName(String inactiveUserDName) {
		this.inactiveUserDName = inactiveUserDName;
	}

	public String getInactiveUserFName() {
		return inactiveUserFName;
	}

	public void setInactiveUserFName(String inactiveUserFName) {
		this.inactiveUserFName = inactiveUserFName;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Integer getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public String getModifyUserDName() {
		return modifyUserDName;
	}

	public void setModifyUserDName(String modifyUserDName) {
		this.modifyUserDName = modifyUserDName;
	}

	public String getModifyUserFName() {
		return modifyUserFName;
	}

	public void setModifyUserFName(String modifyUserFName) {
		this.modifyUserFName = modifyUserFName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getSuperAdmin() {
		return superAdmin;
	}

	public void setSuperAdmin(Boolean superAdmin) {
		this.superAdmin = superAdmin;
	}

	public String getUserDName() {
		return userDName;
	}

	public void setUserDName(String userDName) {
		this.userDName = userDName;
	}

	public String getUserFName() {
		return userFName;
	}

	public void setUserFName(String userFName) {
		this.userFName = userFName;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "UsersDTO [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
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
