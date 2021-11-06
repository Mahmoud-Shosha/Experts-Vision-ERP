package com.expertsvision.erp.masterdata.costcenters.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the cost_center database table.
 * 
 */
@Entity
@Table(name="cost_center")
@NamedQuery(name="CostCenter.findAll", query="SELECT c FROM CostCenter c")
public class CostCenter implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cc_no")
	private Integer ccNo;

	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="cc_d_name")
	private String ccDName;

	@Column(name="cc_f_name")
	private String ccFName;

	@Column(name="cc_group")
	private Integer ccGroup;

	@Column(name="inactive")
	private Boolean inactive;

	@Column(name="inactive_date")
	private Timestamp inactiveDate;

	@Column(name="inactive_reason")
	private String inactiveReason;

	@Column(name="inactive_user")
	private Integer inactiveUser;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="parent_cc")
	private Integer parentCc;

	@Column(name="sub")
	private Boolean sub;

	public CostCenter() {
	}

	public Integer getCcNo() {
		return this.ccNo;
	}

	public void setCcNo(Integer ccNo) {
		this.ccNo = ccNo;
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

	public Integer getParentCc() {
		return this.parentCc;
	}

	public void setParentCc(Integer parentCc) {
		this.parentCc = parentCc;
	}

	public Boolean getSub() {
		return this.sub;
	}

	public void setSub(Boolean sub) {
		this.sub = sub;
	}

}