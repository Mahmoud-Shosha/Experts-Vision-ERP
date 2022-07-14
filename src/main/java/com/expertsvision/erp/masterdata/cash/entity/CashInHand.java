package com.expertsvision.erp.masterdata.cash.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * The persistent class for the cash_in_hand database table.
 * 
 */
@Entity
@Table(name = "cash_in_hand")
public class CashInHand implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cash_no")
	private Integer cashNo;

	@Column(name = "acc_no")
	private Integer accNo;

	@Column(name = "add_date")
	private Timestamp addDate;

	@Column(name = "add_user")
	private Integer addUser;

	@Column(name = "branch_no")
	private Integer branchNo;

	@Column(name = "cash_d_name")
	private String cashDName;

	@Column(name = "cash_f_name")
	private String cashFName;

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

	@Column(name = "pos")
	private Boolean pos;

	public CashInHand() {
	}

	public Integer getCashNo() {
		return this.cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
	}

	public Integer getAccNo() {
		return this.accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
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

	public Integer getBranchNo() {
		return this.branchNo;
	}

	public void setBranchNo(Integer branchNo) {
		this.branchNo = branchNo;
	}

	public String getCashDName() {
		return this.cashDName;
	}

	public void setCashDName(String cashDName) {
		this.cashDName = cashDName;
	}

	public String getCashFName() {
		return this.cashFName;
	}

	public void setCashFName(String cashFName) {
		this.cashFName = cashFName;
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

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Boolean getPos() {
		return this.pos;
	}

	public void setPos(Boolean pos) {
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "CashInHand [cashNo=" + cashNo + ", accNo=" + accNo + ", addDate=" + addDate + ", addUser=" + addUser
				+ ", branchNo=" + branchNo + ", cashDName=" + cashDName + ", cashFName=" + cashFName + ", inactive="
				+ inactive + ", inactiveReason=" + inactiveReason + ", inactiveUser=" + inactiveUser + ", modifyDate="
				+ modifyDate + ", modifyUser=" + modifyUser + ", pos=" + pos + "]";
	}

}