package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the banks_dtl_view database table.
 * 
 */
@Entity
@Table(name="banks_dtl_view")
@NamedQuery(name="BanksDtlView.findAll", query="SELECT b FROM BanksDtlView b")
public class BanksDtlView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="acc_curr")
	private String accCurr;

	@Column(name="acc_d_name")
	private String accDName;

	@Column(name="acc_f_name")
	private String accFName;

	@Id
	@Column(name="acc_no")
	private Integer accNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="add_user_d_name")
	private String addUserDName;

	@Column(name="add_user_f_name")
	private String addUserFName;

	@Column(name="bank_no")
	private Integer bankNo;

	@Column(name="currency_d_name")
	private String currencyDName;

	@Column(name="currency_f_name")
	private String currencyFName;

	private Boolean inactive;

	@Column(name="inactive_reason")
	private String inactiveReason;

	@Column(name="inactive_user")
	private Integer inactiveUser;

	@Column(name="inactive_user_d_name")
	private String inactiveUserDName;

	@Column(name="inactive_user_f_name")
	private String inactiveUserFName;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="modify_user_d_name")
	private String modifyUserDName;

	@Column(name="modify_user_f_name")
	private String modifyUserFName;

	public BanksDtlView() {
	}

	public String getAccCurr() {
		return this.accCurr;
	}

	public void setAccCurr(String accCurr) {
		this.accCurr = accCurr;
	}

	public String getAccDName() {
		return this.accDName;
	}

	public void setAccDName(String accDName) {
		this.accDName = accDName;
	}

	public String getAccFName() {
		return this.accFName;
	}

	public void setAccFName(String accFName) {
		this.accFName = accFName;
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

	public Integer getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
	}

	public String getCurrencyDName() {
		return this.currencyDName;
	}

	public void setCurrencyDName(String currencyDName) {
		this.currencyDName = currencyDName;
	}

	public String getCurrencyFName() {
		return this.currencyFName;
	}

	public void setCurrencyFName(String currencyFName) {
		this.currencyFName = currencyFName;
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

	public String getInactiveUserDName() {
		return this.inactiveUserDName;
	}

	public void setInactiveUserDName(String inactiveUserDName) {
		this.inactiveUserDName = inactiveUserDName;
	}

	public String getInactiveUserFName() {
		return this.inactiveUserFName;
	}

	public void setInactiveUserFName(String inactiveUserFName) {
		this.inactiveUserFName = inactiveUserFName;
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

}