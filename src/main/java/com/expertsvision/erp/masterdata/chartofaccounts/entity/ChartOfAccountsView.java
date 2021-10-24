package com.expertsvision.erp.masterdata.chartofaccounts.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the chart_of_accounts_view database table.
 * 
 */
@Entity
@Table(name="chart_of_accounts_view")
@NamedQuery(name="ChartOfAccountsView.findAll", query="SELECT c FROM ChartOfAccountsView c")
public class ChartOfAccountsView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("acc_d_name")
	@Column(name="acc_d_name")
	private String accDName;

	@JsonProperty("acc_dtl")
	@Column(name="acc_dtl")
	private String accDtl;

	@JsonProperty("acc_f_name")
	@Column(name="acc_f_name")
	private String accFName;

	@JsonProperty("acc_group")
	@Column(name="acc_group")
	private Integer accGroup;

	@Id
	@JsonProperty("acc_no")
	@Column(name="acc_no")
	private Integer accNo;

	@JsonProperty("acc_type")
	@Column(name="acc_type")
	private String accType;

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

	@JsonProperty("cash_flow_type")
	@Column(name="cash_flow_type")
	private String cashFlowType;

	@JsonProperty("dr")
	@Column(name="dr")
	private Boolean dr;

	@JsonProperty("group_d_name")
	@Column(name="group_d_name")
	private String groupDName;

	@JsonProperty("group_f_name")
	@Column(name="group_f_name")
	private String groupFName;

	@JsonProperty("inactive")
	@Column(name="inactive")
	private Boolean inactive;

	@JsonProperty("inactive_date")
	@Column(name="inactive_date")
	private Timestamp inactiveDate;

	@JsonProperty("inactive_reason")
	@Column(name="inactive_reason")
	private String inactiveReason;

	@JsonProperty("inactive_user")
	@Column(name="inactive_user")
	private Integer inactiveUser;

	@JsonProperty("inactive_user_d_name")
	@Column(name="inactive_user_d_name")
	private String inactiveUserDName;

	@JsonProperty("inactive_user_f_name")
	@Column(name="inactive_user_f_name")
	private String inactiveUserFName;

	@JsonProperty("level")
	@Column(name="level")
	private Integer level;

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

	@JsonProperty("parent_acc")
	@Column(name="parent_acc")
	private Integer parentAcc;

	@JsonProperty("parent_acc_d_name")
	@Column(name="parent_acc_d_name")
	private String parentAccDName;

	@JsonProperty("parent_acc_f_name")
	@Column(name="parent_acc_f_name")
	private String parentAccFName;

	@JsonProperty("sub")
	@Column(name="sub")
	private Boolean sub;

	@JsonProperty("bs")
	@Column(name="bs")
	private Boolean bs;
	
	@JsonProperty("cc_post")
	@Column(name="cc_post")
	private String ccPost;
	
	@JsonProperty("account_currency_list")
	@Transient
	@JsonInclude(Include.NON_NULL)
	private List<AccountsCurrencyView> accountCurrencyList;

	
	public ChartOfAccountsView() {
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

	public Integer getAccNo() {
		return this.accNo;
	}

	public void setAccNo(Integer accNo) {
		this.accNo = accNo;
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

	public String getGroupDName() {
		return this.groupDName;
	}

	public void setGroupDName(String groupDName) {
		this.groupDName = groupDName;
	}

	public String getGroupFName() {
		return this.groupFName;
	}

	public void setGroupFName(String groupFName) {
		this.groupFName = groupFName;
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

	public Integer getParentAcc() {
		return this.parentAcc;
	}

	public void setParentAcc(Integer parentAcc) {
		this.parentAcc = parentAcc;
	}

	public String getParentAccDName() {
		return this.parentAccDName;
	}

	public void setParentAccDName(String parentAccDName) {
		this.parentAccDName = parentAccDName;
	}

	public String getParentAccFName() {
		return this.parentAccFName;
	}

	public void setParentAccFName(String parentAccFName) {
		this.parentAccFName = parentAccFName;
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

	public List<AccountsCurrencyView> getAccountCurrencyList() {
		return accountCurrencyList;
	}

	public void setAccountCurrencyList(List<AccountsCurrencyView> accountCurrencyList) {
		this.accountCurrencyList = accountCurrencyList;
	}

	public String getCcPost() {
		return ccPost;
	}

	public void setCcPost(String ccPost) {
		this.ccPost = ccPost;
	}

	@Override
	public String toString() {
		return "ChartOfAccountsView [accDName=" + accDName + ", accDtl=" + accDtl + ", accFName=" + accFName
				+ ", accGroup=" + accGroup + ", accNo=" + accNo + ", accType=" + accType + ", addDate=" + addDate
				+ ", addUser=" + addUser + ", addUserDName=" + addUserDName + ", addUserFName=" + addUserFName
				+ ", cashFlowType=" + cashFlowType + ", dr=" + dr + ", groupDName=" + groupDName + ", groupFName="
				+ groupFName + ", inactive=" + inactive + ", inactiveDate=" + inactiveDate + ", inactiveReason="
				+ inactiveReason + ", inactiveUser=" + inactiveUser + ", inactiveUserDName=" + inactiveUserDName
				+ ", inactiveUserFName=" + inactiveUserFName + ", level=" + level + ", modifyDate=" + modifyDate
				+ ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName + ", modifyUserFName="
				+ modifyUserFName + ", parentAcc=" + parentAcc + ", parentAccDName=" + parentAccDName
				+ ", parentAccFName=" + parentAccFName + ", sub=" + sub + ", bs=" + bs + "]";
	}

}