package com.expertsvision.erp.masterdata.cash.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the cash_in_hand_dtl database table.
 * 
 */
@Entity
@Table(name = "cash_in_hand_dtl")
@IdClass(CashInHandDtlPK.class)
@NamedQuery(name = "CashInHandDtl.findAll", query = "SELECT c FROM CashInHandDtl c")
public class CashInHandDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cash_no")
	private Integer cashNo;

	@Id
	@Column(name = "acc_curr")
	private String accCurr;

	@Id
	@Column(name = "acc_no")
	private Integer accNo;

	@Column(name = "active")
	private Boolean active;

	@Column(name = "add_date")
	private Timestamp addDate;

	@Column(name = "add_user")
	private Integer addUser;

	@Column(name = "inactive")
	private Boolean inactive;

	@Column(name = "inactive_reason")
	private String inactiveReason;

	@Column(name = "inactive_user")
	private Integer inactiveUser;

	@Column(name = "modify_date")
	private Timestamp modifyDate;

	@Column(name = "modify_user")
	private Integer modifyUser;

	public CashInHandDtl() {
	}

	public Integer getAccNo() {
		return this.accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public Boolean getActive() {
		return this.active;
	}

	public void setActive(Boolean active) {
		this.active = active;
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

	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
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

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
	public String toString() {
		return "CashInHandDtl [cashNo=" + cashNo + ", accCurr=" + accCurr + ", accNo=" + accNo + ", active=" + active
				+ ", addDate=" + addDate + ", addUser=" + addUser + ", inactive=" + inactive + ", inactiveReason="
				+ inactiveReason + ", inactiveUser=" + inactiveUser + ", modifyDate=" + modifyDate + ", modifyUser="
				+ modifyUser + "]";
	}

}