package com.expertsvision.erp.masterdata.cash.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * The persistent class for the cash_in_hand_priv database table.
 * 
 */
@Entity
@Table(name = "cash_in_hand_priv")
@IdClass(CashInHandPrivPK.class)
public class CashInHandPriv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "user_id")
	private Integer userId;

	@Id
	@Column(name = "acc_curr")
	private String accCurr;

	@Column(name = "add_date")
	private Timestamp addDate;

	@Column(name = "add_priv")
	private Boolean addPriv;

	@Column(name = "add_user")
	private Integer addUser;

	@Id
	@Column(name = "cash_no")
	private Integer cashNo;

	@Column(name = "modify_date")
	private Timestamp modifyDate;

	@Column(name = "modify_user")
	private Integer modifyUser;

	@Column(name = "view_priv")
	private Boolean viewPriv;

	public CashInHandPriv() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
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

	public Integer getCashNo() {
		return this.cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
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
		return "CashInHandPriv [userId=" + userId + ", accCurr=" + accCurr + ", addDate=" + addDate + ", addPriv="
				+ addPriv + ", addUser=" + addUser + ", cashNo=" + cashNo + ", modifyDate=" + modifyDate
				+ ", modifyUser=" + modifyUser + ", viewPriv=" + viewPriv + "]";
	}

}