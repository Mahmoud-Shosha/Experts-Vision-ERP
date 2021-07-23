package com.expertsvision.erp.core.flagpriv.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the flag_priv_view database view.
 * 
 */
@Entity
@Table(name="flag_priv_view")
@IdClass(FlagPrivPK.class)
@NamedQuery(name="FlagPrivView.findAll", query="SELECT f FROM FlagPrivView f")
public class FlagPrivView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("add_date")
	@Column(name="add_date")
	private Timestamp addDate;

	@JsonProperty("add_priv")
	@Column(name="add_priv")
	private Boolean addPriv;

	@JsonProperty("add_user")
	@Column(name="add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	@Column(name="add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	@Column(name="add_user_f_name")
	private String addUserFName;

	@JsonProperty("delete_priv")
	@Column(name="delete_priv")
	private Boolean deletePriv;

	@Id
	@JsonProperty("flag_code")
	@Column(name="flag_code")
	private String flagCode;

	@Id
	@JsonProperty("flag_value")
	@Column(name="flag_value")
	private Integer flagValue;

	@JsonProperty("modify_date")
	@Column(name="modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_priv")
	@Column(name="modify_priv")
	private Boolean modifyPriv;

	@JsonProperty("modify_user")
	@Column(name="modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	@Column(name="modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	@Column(name="modify_user_f_name")
	private String modifyUserFName;

	@JsonProperty("print_priv")
	@Column(name="print_priv")
	private Boolean printPriv;

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

	@JsonProperty("view_priv")
	@Column(name="view_priv")
	private Boolean viewPriv;

	public FlagPrivView() {
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Boolean getAddPriv() {
		return this.addPriv;
	}

	public void setAddPriv(Boolean addPriv) {
		this.addPriv = addPriv;
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

	public Boolean getDeletePriv() {
		return this.deletePriv;
	}

	public void setDeletePriv(Boolean deletePriv) {
		this.deletePriv = deletePriv;
	}

	public String getFlagCode() {
		return this.flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

	public Integer getFlagValue() {
		return this.flagValue;
	}

	public void setFlagValue(Integer flagValue) {
		this.flagValue = flagValue;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getModifyPriv() {
		return this.modifyPriv;
	}

	public void setModifyPriv(Boolean modifyPriv) {
		this.modifyPriv = modifyPriv;
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

	public Boolean getPrintPriv() {
		return this.printPriv;
	}

	public void setPrintPriv(Boolean printPriv) {
		this.printPriv = printPriv;
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

	public Boolean getViewPriv() {
		return this.viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

	@Override
	public String toString() {
		return "FlagPrivView [addDate=" + addDate + ", addPriv=" + addPriv + ", addUser=" + addUser + ", addUserDName="
				+ addUserDName + ", addUserFName=" + addUserFName + ", deletePriv=" + deletePriv + ", flagCode="
				+ flagCode + ", flagValue=" + flagValue + ", modifyDate=" + modifyDate + ", modifyPriv=" + modifyPriv
				+ ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName + ", modifyUserFName="
				+ modifyUserFName + ", printPriv=" + printPriv + ", userDName=" + userDName + ", userFName=" + userFName
				+ ", userId=" + userId + ", viewPriv=" + viewPriv + "]";
	}

}