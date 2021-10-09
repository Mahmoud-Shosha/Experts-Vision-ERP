package com.expertsvision.erp.masterdata.chartofaccounts.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the accounts_priv database table.
 * 
 */
@Entity
@Table(name="accounts_priv")
@IdClass(AccountsPrivPK.class)
@NamedQuery(name="AccountsPriv.findAll", query="SELECT a FROM AccountsPriv a")
public class AccountsPriv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	@JsonProperty("user_id")
	private Integer userId;

	@Id
	@Column(name="acc_no")
	@JsonProperty("acc_no")
	private Integer accNo;

	@Id
	@Column(name="acc_curr")
	@JsonProperty("acc_cur")
	private String accCurr;

	@Column(name="add_date")
	@JsonProperty("add_date")
	private Timestamp addDate;

	@Column(name="add_priv")
	@JsonProperty("add_priv")
	private Boolean addPriv;

	@Column(name="add_user")
	@JsonProperty("add_user")
	private Integer addUser;

	@Column(name="modify_date")
	@JsonProperty("modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	@JsonProperty("modify_user")
	private Integer modifyUser;

	@Column(name="view_priv")
	@JsonProperty("view_priv")
	private Boolean viewPriv;

	public AccountsPriv() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getAccNo() {
		return accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public String getAccCurr() {
		return accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
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

	public Boolean getViewPriv() {
		return this.viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

	@Override
	public String toString() {
		return "AccountsPriv [userId=" + userId + ", accNo=" + accNo + ", accCurr=" + accCurr + ", addDate=" + addDate
				+ ", addPriv=" + addPriv + ", addUser=" + addUser + ", modifyDate=" + modifyDate + ", modifyUser="
				+ modifyUser + ", viewPriv=" + viewPriv + "]";
	}

}