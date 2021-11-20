package com.expertsvision.erp.masterdata.chartofaccounts.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

/**
 * The persistent class for the accounts_currency database table.
 * 
 */
@Entity
@Table(name = "accounts_currency")
@IdClass(AccountsCurrencyPK.class)
@NamedQuery(name = "AccountsCurrency.findAll", query = "SELECT a FROM AccountsCurrency a")
public class AccountsCurrency implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "acc_no")
	private Integer accNo;

	@Id
	@Column(name = "cur_code")
	private String curCode;

	@Column(name = "add_date")
	private Timestamp addDate;

	@Column(name = "add_user")
	private Integer addUser;

	@Column(name = "modify_date")
	private Timestamp modifyDate;

	@Column(name = "modify_user")
	private Integer modifyUser;

	@Column(name = "used")
	private Boolean used;

	public AccountsCurrency() {
	}

	public Integer getAccNo() {
		return accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public String getCurCode() {
		return curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
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

	public Boolean getUsed() {
		return this.used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	@Override
	public String toString() {
		return "AccountsCurrency [accNo=" + accNo + ", curCode=" + curCode + ", addDate=" + addDate + ", addUser="
				+ addUser + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", used=" + used + "]";
	}

}