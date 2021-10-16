package com.expertsvision.erp.masterdata.chartofaccounts.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the chart_of_accounts database table.
 * 
 */
@Entity
@Table(name="chart_of_accounts")
@NamedQuery(name="ChartOfAccount.findAll", query="SELECT c FROM ChartOfAccount c")
public class ChartOfAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="acc_no")
	private Integer accNo;

	@Column(name="acc_d_name")
	private String accDName;

	@Column(name="acc_dtl")
	private String accDtl;

	@Column(name="acc_f_name")
	private String accFName;

	@Column(name="acc_group")
	private Integer accGroup;

	@Column(name="acc_type")
	private String accType;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="cash_flow_type")
	private String cashFlowType;
	
	@Column(name="cc_post")
	private String ccPost;

	@Column(name="dr")
	private Boolean dr;

	@Column(name="inactive")
	private Boolean inactive;

	@Column(name="inactive_date")
	private Timestamp inactiveDate;

	@Column(name="inactive_reason")
	private String inactiveReason;

	@Column(name="inactive_user")
	private Integer inactiveUser;

	@Column(name="level")
	private Integer level;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="parent_acc")
	private Integer parentAcc;

	@Column(name="sub")
	private Boolean sub;

	@Column(name="bs")
	private Boolean bs;

	public ChartOfAccount() {
	}

	public Integer getAccNo() {
		return this.accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
	}

	public String getAccDName() {
		return this.accDName;
	}

	public void setAccDName(String accDName) {
		this.accDName = accDName;
	}

	public String getAccDtl() {
		return this.accDtl;
	}

	public void setAccDtl(String accDtl) {
		this.accDtl = accDtl;
	}

	public String getAccFName() {
		return this.accFName;
	}

	public void setAccFName(String accFName) {
		this.accFName = accFName;
	}

	public Integer getAccGroup() {
		return this.accGroup;
	}

	public void setAccGroup(Integer accGroup) {
		this.accGroup = accGroup;
	}

	public String getAccType() {
		return this.accType;
	}

	public void setAccType(String accType) {
		this.accType = accType;
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

	public String getCashFlowType() {
		return this.cashFlowType;
	}

	public void setCashFlowType(String cashFlowType) {
		this.cashFlowType = cashFlowType;
	}

	public Boolean getDr() {
		return this.dr;
	}

	public void setDr(Boolean dr) {
		this.dr = dr;
	}

	public Boolean getInactive() {
		return this.inactive;
	}

	public void setInactive(Boolean inactive) {
		this.inactive = inactive;
	}

	public Timestamp getInactiveDate() {
		return this.inactiveDate;
	}

	public void setInactiveDate(Timestamp inactiveDate) {
		this.inactiveDate = inactiveDate;
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

	public Integer getLevel() {
		return this.level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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

	public Integer getParentAcc() {
		return this.parentAcc;
	}

	public void setParentAcc(Integer parentAcc) {
		this.parentAcc = parentAcc;
	}

	public Boolean getSub() {
		return this.sub;
	}

	public void setSub(Boolean sub) {
		this.sub = sub;
	}

	public Boolean getBs() {
		return this.bs;
	}

	public void setBs(Boolean bs) {
		this.bs = bs;
	}

	public String getCcPost() {
		return ccPost;
	}

	public void setCcPost(String ccPost) {
		this.ccPost = ccPost;
	}

	@Override
	public String toString() {
		return "ChartOfAccount [accNo=" + accNo + ", accDName=" + accDName + ", accDtl=" + accDtl + ", accFName="
				+ accFName + ", accGroup=" + accGroup + ", accType=" + accType + ", addDate=" + addDate + ", addUser="
				+ addUser + ", cashFlowType=" + cashFlowType + ", dr=" + dr + ", inactive=" + inactive
				+ ", inactiveDate=" + inactiveDate + ", inactiveReason=" + inactiveReason + ", inactiveUser="
				+ inactiveUser + ", level=" + level + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ ", parentAcc=" + parentAcc + ", sub=" + sub + ", bs=" + bs + "]";
	}

}