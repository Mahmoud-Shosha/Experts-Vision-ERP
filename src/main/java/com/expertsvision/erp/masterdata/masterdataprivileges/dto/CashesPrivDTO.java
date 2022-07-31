package com.expertsvision.erp.masterdata.masterdataprivileges.dto;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CashesPrivDTO {

	@JsonProperty("user_id")
	private Integer userId;

	@JsonProperty("admin_group")
	private Boolean adminGroup;

	@JsonProperty("cash_no")
	private Integer cashNo;

	@JsonProperty("acc_curr")
	private String accCurr;

	@JsonProperty("add_priv")
	private Boolean addPriv;

	@JsonProperty("view_priv")
	private Boolean viewPriv;

	@JsonProperty("add_user")
	private Integer addUser;

	@JsonProperty("add_date")
	private Timestamp addDate;

	@JsonProperty("modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_date")
	private Timestamp modifyDate;

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

	@JsonProperty("cash_d_name")
	private String cashDName;

	@JsonProperty("cash_f_name")
	private String cashFName;

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

	public Integer getCashNo() {
		return cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
	}

	public String getAccCurr() {
		return accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
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

	public Integer getAddUser() {
		return addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
	}

	public Timestamp getAddDate() {
		return addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Integer getModifyUser() {
		return modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
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

	public String getCashDName() {
		return cashDName;
	}

	public void setCashDName(String cashDName) {
		this.cashDName = cashDName;
	}

	public String getCashFName() {
		return cashFName;
	}

	public void setCashFName(String cashFName) {
		this.cashFName = cashFName;
	}

}
