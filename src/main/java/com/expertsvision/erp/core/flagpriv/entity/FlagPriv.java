package com.expertsvision.erp.core.flagpriv.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.sql.Timestamp;


/**
 * The persistent class for the flag_priv database table.
 * 
 */
@Entity
@Table(name="flag_priv")
@IdClass(FlagPrivPK.class)
@NamedQuery(name="FlagPriv.findAll", query="SELECT f FROM FlagPriv f")
public class FlagPriv implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="user_id")
	private Integer userId;

	@Id
	@Column(name="flag_code")
	private String flagCode;

	@Id
	@Column(name="flag_value")
	private Integer flagValue;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_priv")
	private Boolean addPriv;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="delete_priv")
	private Boolean deletePriv;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_priv")
	private Boolean modifyPriv;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="print_priv")
	private Boolean printPriv;

	@Column(name="view_priv")
	private Boolean viewPriv;

	public FlagPriv() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

	public Integer getFlagValue() {
		return flagValue;
	}


	public void setFlagValue(Integer flagValue) {
		this.flagValue = flagValue;
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

	public Boolean getDeletePriv() {
		return this.deletePriv;
	}

	public void setDeletePriv(Boolean deletePriv) {
		this.deletePriv = deletePriv;
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

	public Boolean getPrintPriv() {
		return this.printPriv;
	}

	public void setPrintPriv(Boolean printPriv) {
		this.printPriv = printPriv;
	}

	public Boolean getViewPriv() {
		return this.viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

	@Override
	public String toString() {
		return "FlagPriv [userId=" + userId + ", flagCode=" + flagCode + ", flagValue=" + flagValue + ", addDate="
				+ addDate + ", addPriv=" + addPriv + ", addUser=" + addUser + ", deletePriv=" + deletePriv
				+ ", modifyDate=" + modifyDate + ", modifyPriv=" + modifyPriv + ", modifyUser=" + modifyUser
				+ ", printPriv=" + printPriv + ", viewPriv=" + viewPriv + "]";
	}

}