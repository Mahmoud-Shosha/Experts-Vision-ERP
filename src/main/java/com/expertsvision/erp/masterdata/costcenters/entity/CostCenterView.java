package com.expertsvision.erp.masterdata.costcenters.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the cost_center_view database table.
 * 
 */
@Entity
@Table(name="cost_center_view")
@NamedQuery(name="CostCenterView.findAll", query="SELECT c FROM CostCenterView c")
public class CostCenterView implements Serializable {
	private static final long serialVersionUID = 1L;

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

	@JsonProperty("cc_d_name")
	@Column(name="cc_d_name")
	private String ccDName;

	@JsonProperty("cc_f_name")
	@Column(name="cc_f_name")
	private String ccFName;

	@JsonProperty("cc_group")
	@Column(name="cc_group")
	private Integer ccGroup;

	@Id
	@JsonProperty("cc_no")
	@Column(name="cc_no")
	private Integer ccNo;

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

	@JsonProperty("parent_cc")
	@Column(name="parent_cc")
	private Integer parentCc;

	@JsonProperty("parent_cc_d_name")
	@Column(name="parent_cc_d_name")
	private String parentCcDName;

	@JsonProperty("parent_cc_f_name")
	@Column(name="parent_cc_f_name")
	private String parentCcFName;

	@JsonProperty("sub")
	@Column(name="sub")
	private Boolean sub;

	public CostCenterView() {
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

	public String getCcDName() {
		return this.ccDName;
	}

	public void setCcDName(String ccDName) {
		this.ccDName = ccDName;
	}

	public String getCcFName() {
		return this.ccFName;
	}

	public void setCcFName(String ccFName) {
		this.ccFName = ccFName;
	}

	public Integer getCcGroup() {
		return this.ccGroup;
	}

	public void setCcGroup(Integer ccGroup) {
		this.ccGroup = ccGroup;
	}

	public Integer getCcNo() {
		return this.ccNo;
	}

	public void setCcNo(Integer ccNo) {
		this.ccNo = ccNo;
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

	public Integer getParentCc() {
		return this.parentCc;
	}

	public void setParentCc(Integer parentCc) {
		this.parentCc = parentCc;
	}

	public String getParentCcDName() {
		return this.parentCcDName;
	}

	public void setParentCcDName(String parentCcDName) {
		this.parentCcDName = parentCcDName;
	}

	public String getParentCcFName() {
		return this.parentCcFName;
	}

	public void setParentCcFName(String parentCcFName) {
		this.parentCcFName = parentCcFName;
	}

	public Boolean getSub() {
		return this.sub;
	}

	public void setSub(Boolean sub) {
		this.sub = sub;
	}

}