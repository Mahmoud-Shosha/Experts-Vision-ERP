package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the banks_dtl database table.
 * 
 */
@Entity
@Table(name = "banks_dtl")
@IdClass(BanksDtlPK.class)
@NamedQuery(name = "BanksDtl.findAll", query = "SELECT b FROM BanksDtl b")
public class BanksDtl implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bank_no")
	private Integer bankNo;

	@Id
	@Column(name = "acc_no")
	private Integer accNo;

	@Id
	@Column(name = "acc_curr")
	private String accCurr;

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

	public BanksDtl() {
	}

	public Integer getBankNo() {
		return bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
	}

	public Integer getAccNo() {
		return accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public String getAccCurr() {
		return this.accCurr;
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

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	@Override
	public String toString() {
		return "BanksDtl [bankNo=" + bankNo + ", accNo=" + accNo + ", accCurr=" + accCurr + ", addDate=" + addDate
				+ ", addUser=" + addUser + ", inactive=" + inactive + ", inactiveReason=" + inactiveReason
				+ ", inactiveUser=" + inactiveUser + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + "]";
	}

}