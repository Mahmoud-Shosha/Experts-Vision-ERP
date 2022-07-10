package com.expertsvision.erp.masterdata.banks.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the banks_view database table.
 * 
 */
@Entity
@Table(name = "banks_view")
public class BanksView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("acc_d_name")
	@Column(name = "acc_d_name")
	private String accDName;

	@JsonProperty("acc_f_name")
	@Column(name = "acc_f_name")
	private String accFName;

	@JsonProperty("account_no")
	@Column(name = "account_no")
	private Integer accountNo;

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

	@JsonProperty("bank_d_name")
	@Column(name = "bank_d_name")
	private String bankDName;

	@JsonProperty("bank_f_name")
	@Column(name = "bank_f_name")
	private String bankFName;

	@Id
	@JsonProperty("bank_no")
	@Column(name = "bank_no")
	private Integer bankNo;

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

	@JsonProperty("bnk_dtl_list")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<BanksDtlView> bankDtlList;

	public BanksView() {
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

	public Integer getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(Integer bankNo) {
		this.bankNo = bankNo;
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

	public List<BanksDtlView> getBankDtlList() {
		return bankDtlList;
	}

	public void setBankDtlList(List<BanksDtlView> bankDtlList) {
		this.bankDtlList = bankDtlList;
	}

	@Override
	public String toString() {
		return "BanksView [accDName=" + accDName + ", accFName=" + accFName + ", accountNo=" + accountNo + ", addDate="
				+ addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName + ", addUserFName=" + addUserFName
				+ ", bankDName=" + bankDName + ", bankFName=" + bankFName + ", bankNo=" + bankNo + ", inactive="
				+ inactive + ", inactiveReason=" + inactiveReason + ", inactiveUser=" + inactiveUser
				+ ", inactiveUserDName=" + inactiveUserDName + ", inactiveUserFName=" + inactiveUserFName
				+ ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName
				+ ", modifyUserFName=" + modifyUserFName + "]";
	}

}