package com.expertsvision.erp.masterdata.cash.entity;

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
 * The persistent class for the cash_in_hand_view database table.
 * 
 */
@Entity
@Table(name = "cash_in_hand_view")
public class CashInHandView implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@JsonProperty("branch_d_name")
	@Column(name = "branch_d_name")
	private String branchDName;

	@JsonProperty("branch_f_name")
	@Column(name = "branch_f_name")
	private String branchFName;

	@JsonProperty("branch_no")
	@Column(name = "branch_no")
	private Integer branchNo;

	@JsonProperty("cash_d_name")
	@Column(name = "cash_d_name")
	private String cashDName;

	@JsonProperty("cash_f_name")
	@Column(name = "cash_f_no")
	private String cashFNo;

	@JsonProperty("cash_no")
	@Column(name = "cash_no")
	private Integer cashNo;

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

	@JsonProperty("pos")
	@Column(name = "pos")
	private Boolean pos;

	@JsonProperty("cash_dtl_list")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<CashInHandDtlView> cashDtlList;

	public CashInHandView() {
	}

	public List<CashInHandDtlView> getCashDtlList() {
		return cashDtlList;
	}

	public void setCashDtlList(List<CashInHandDtlView> cashDtlList) {
		this.cashDtlList = cashDtlList;
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

	public String getBranchDName() {
		return this.branchDName;
	}

	public void setBranchDName(String branchDName) {
		this.branchDName = branchDName;
	}

	public String getBranchFName() {
		return this.branchFName;
	}

	public void setBranchFName(String branchFName) {
		this.branchFName = branchFName;
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

	public String getCashFNo() {
		return this.cashFNo;
	}

	public void setCashFNo(String cashFNo) {
		this.cashFNo = cashFNo;
	}

	public Integer getCashNo() {
		return this.cashNo;
	}

	public void setCashNo(Integer cashNo) {
		this.cashNo = cashNo;
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

	public Boolean getPos() {
		return this.pos;
	}

	public void setPos(Boolean pos) {
		this.pos = pos;
	}

	@Override
	public String toString() {
		return "CashInHandView [accDName=" + accDName + ", accFName=" + accFName + ", accNo=" + accNo + ", addDate="
				+ addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName + ", addUserFName=" + addUserFName
				+ ", branchDName=" + branchDName + ", branchFName=" + branchFName + ", branchNo=" + branchNo
				+ ", cashDName=" + cashDName + ", cashFNo=" + cashFNo + ", cashNo=" + cashNo + ", inactive=" + inactive
				+ ", inactiveReason=" + inactiveReason + ", inactiveUser=" + inactiveUser + ", inactiveUserDName="
				+ inactiveUserDName + ", inactiveUserFName=" + inactiveUserFName + ", modifyDate=" + modifyDate
				+ ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName + ", modifyUserFName="
				+ modifyUserFName + ", pos=" + pos + "]";
	}

}