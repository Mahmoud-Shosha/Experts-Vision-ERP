package com.expertsvision.erp.masterdata.chartofaccounts.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.sql.Timestamp;


/**
 * The persistent class for the accounts_currency_view database table.
 * 
 */
@Entity
@IdClass(AccountsCurrencyPK.class)
@Table(name="accounts_currency_view")
@NamedQuery(name="AccountsCurrencyView.findAll", query="SELECT a FROM AccountsCurrencyView a")
public class AccountsCurrencyView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("acc_no")
	@Column(name="acc_no")
	private Integer accNo;

	@JsonProperty("active")
	@Column(name="active")
	private Boolean active;

	@JsonProperty("add_date")
	@Column(name="add_date")
	private Timestamp addDate;

	@JsonProperty("add_user")
	@Column(name="add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	@Column(name="add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	@Column(name="add_user_f_name")
	private String addUserFName;

	@Id
	@JsonProperty("cur_code")
	@Column(name="cur_code")
	private String curCode;

	@JsonProperty("currency_d_name")
	@Column(name="currency_d_name")
	private String currencyDName;

	@JsonProperty("currency_f_name")
	@Column(name="currency_f_name")
	private String currencyFName;

	@JsonProperty("modify_date")
	@Column(name="modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	@Column(name="modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	@Column(name="modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	@Column(name="modify_user_f_name")
	private String modifyUserFName;

	@JsonProperty("used")
	@Column(name="used")
	private Boolean used;
	
	@Transient
	@JsonProperty("action")
	@JsonInclude(Include.NON_NULL)
	private String action;

	
	public AccountsCurrencyView() {
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

	public String getCurCode() {
		return this.curCode;
	}

	public void setCurCode(String curCode) {
		this.curCode = curCode;
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

	public Boolean getUsed() {
		return this.used;
	}

	public void setUsed(Boolean used) {
		this.used = used;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	@Override
	public String toString() {
		return "AccountsCurrencyView [accNo=" + accNo + ", active=" + active + ", addDate=" + addDate + ", addUser="
				+ addUser + ", addUserDName=" + addUserDName + ", addUserFName=" + addUserFName + ", curCode=" + curCode
				+ ", currencyDName=" + currencyDName + ", currencyFName=" + currencyFName + ", modifyDate=" + modifyDate
				+ ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName + ", modifyUserFName="
				+ modifyUserFName + ", used=" + used + ", action=" + action + "]";
	}

}