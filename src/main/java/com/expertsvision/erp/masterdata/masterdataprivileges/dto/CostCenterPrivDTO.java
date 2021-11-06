package com.expertsvision.erp.masterdata.masterdataprivileges.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CostCenterPrivDTO {

	@JsonProperty("user_id")
	private Integer userId;

	@JsonProperty("admin_group")
	private Boolean adminGroup;

	@JsonProperty("cost_center")
	private Integer costCenter;

	@JsonProperty("add_priv")
	private Boolean addPriv;

	@JsonProperty("view_priv")
	private Boolean viewPriv;

	@JsonProperty("add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	private Integer addUser;

	@JsonProperty("modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	private Integer modifyUser;

	@JsonProperty("can_change_add_priv")
	private Boolean canChangeAddPriv;

	@JsonProperty("can_change_view_priv")
	private Boolean canChangeViewPriv;

	@JsonProperty("user_d_name")
	private String userDName;

	@JsonProperty("user_f_name")
	private String userFName;

	@JsonProperty("add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	private String addUserFName;

	@JsonProperty("modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	private String modifyUserFName;

	@JsonProperty("cc_d_name")
	private String ccDName;

	@JsonProperty("cc_f_name")
	private String ccFName;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getAdminGroup() {
		return adminGroup;
	}

	public void setAdminGroup(Boolean adminGroup) {
		this.adminGroup = adminGroup;
	}

	public Integer getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Integer costCenter) {
		this.costCenter = costCenter;
	}

	public Boolean getAddPriv() {
		return addPriv;
	}

	public void setAddPriv(Boolean addPriv) {
		this.addPriv = addPriv;
	}

	public Boolean getViewPriv() {
		return viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

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

	public Boolean getCanChangeAddPriv() {
		return canChangeAddPriv;
	}

	public void setCanChangeAddPriv(Boolean canChangeAddPriv) {
		this.canChangeAddPriv = canChangeAddPriv;
	}

	public Boolean getCanChangeViewPriv() {
		return canChangeViewPriv;
	}

	public void setCanChangeViewPriv(Boolean canChangeViewPriv) {
		this.canChangeViewPriv = canChangeViewPriv;
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

	public String getCcDName() {
		return ccDName;
	}

	public void setCcDName(String ccDName) {
		this.ccDName = ccDName;
	}

	public String getCcFName() {
		return ccFName;
	}

	public void setCcFName(String ccFName) {
		this.ccFName = ccFName;
	}

}
