package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the banks database table.
 * 
 */
@Entity
@Table(name = "banks")
@NamedQuery(name = "Bank.findAll", query = "SELECT b FROM Bank b")
public class Bank implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "bank_no")
	private Integer bankNo;

	@Column(name = "account_no")
	private Integer accountNo;

	@Column(name = "add_date")
	private Timestamp addDate;

	@Column(name = "add_user")
	private Integer addUser;

	@Column(name = "bank_d_name")
	private String bankDName;

	@Column(name = "bank_f_name")
	private String bankFName;

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

	public Bank() {
	}

	public Integer getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
	}

	public Integer getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
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

	public String getBankDName() {
		return this.bankDName;
	}

	public void setBankDName(String bankDName) {
		this.bankDName = bankDName;
	}

	public String getBankFName() {
		return this.bankFName;
	}

	public void setBankFName(String bankFName) {
		this.bankFName = bankFName;
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
		return "Bank [bankNo=" + bankNo + ", accountNo=" + accountNo + ", addDate=" + addDate + ", addUser=" + addUser
				+ ", bankDName=" + bankDName + ", bankFName=" + bankFName + ", inactive=" + inactive
				+ ", inactiveReason=" + inactiveReason + ", inactiveUser=" + inactiveUser + ", modifyDate=" + modifyDate
				+ ", modifyUser=" + modifyUser + "]";
	}

}