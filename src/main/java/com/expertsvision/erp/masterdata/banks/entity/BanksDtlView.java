package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the banks_dtl_view database table.
 * 
 */
@Entity
@Table(name = "banks_dtl_view")
@IdClass(BanksDtlPK.class)
@NamedQuery(name = "BanksDtlView.findAll", query = "SELECT b FROM BanksDtlView b")
public class BanksDtlView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("acc_curr")
	@Column(name = "acc_curr")
	private String accCurr;

	@JsonProperty("acc_d_name")
	@Column(name = "acc_d_name")
	private String accDName;

	@JsonProperty("acc_f_name")
	@Column(name = "acc_f_name")
	private String accFName;

	@Id
	@JsonProperty("acc_no")
	@Column(name = "acc_no")
	private Integer accNo;

	@JsonProperty("add_date")
	@Column(name = "add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	@Column(name = "add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	@Column(name = "add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	@Column(name = "add_user_f_name")
	private String addUserFName;

	@Id
	@JsonProperty("bank_no")
	@Column(name = "bank_no")
	private Integer bankNo;

	@JsonProperty("currency_d_name")
	@Column(name = "currency_d_name")
	private String currencyDName;

	@JsonProperty("currency_f_name")
	@Column(name = "currency_f_name")
	private String currencyFName;

	@JsonProperty("inactive")
	@Column(name = "inactive")
	private Boolean inactive;

	@JsonProperty("inactive_reason")
	@Column(name = "inactive_reason")
	private String inactiveReason;

	@JsonProperty("inactive_user")
	@Column(name = "inactive_user")
	private Integer inactiveUser;

	@JsonProperty("inactive_user_d_name")
	@Column(name = "inactive_user_d_name")
	private String inactiveUserDName;

	@JsonProperty("inactive_user_f_name")
	@Column(name = "inactive_user_f_name")
	private String inactiveUserFName;

	@JsonProperty("modify_date")
	@Column(name = "modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	@Column(name = "modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	@Column(name = "modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	@Column(name = "modify_user_f_name")
	private String modifyUserFName;

	@Transient
	@JsonProperty("action")
	@JsonInclude(Include.NON_NULL)
	private String action;

	public BanksDtlView() {
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
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

	@Override
	public String toString() {
		return "BanksDtlView [accCurr=" + accCurr + ", accDName=" + accDName + ", accFName=" + accFName + ", accNo="
				+ accNo + ", addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", bankNo=" + bankNo + ", currencyDName=" + currencyDName
				+ ", currencyFName=" + currencyFName + ", inactive=" + inactive + ", inactiveReason=" + inactiveReason
				+ ", inactiveUser=" + inactiveUser + ", inactiveUserDName=" + inactiveUserDName + ", inactiveUserFName="
				+ inactiveUserFName + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName="
				+ modifyUserDName + ", modifyUserFName=" + modifyUserFName + "]";
	}

}