package com.expertsvision.erp.masterdata.cash.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cash_in_hand_view database table.
 * 
 */
@Entity
@Table(name="cash_in_hand_view")
@NamedQuery(name="CashInHandView.findAll", query="SELECT c FROM CashInHandView c")
public class CashInHandView implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@Column(name="branch_d_name")
	private String branchDName;

	@Column(name="branch_f_name")
	private String branchFName;

	@Column(name="branch_no")
	private Integer branchNo;

	@Column(name="cash_d_name")
	private String cashDName;

	@Column(name="cash_f_no")
	private String cashFNo;

	@Column(name="cash_no")
	private Integer cashNo;

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

	private Boolean pos;

	public CashInHandView() {
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

}