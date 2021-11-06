package com.expertsvision.erp.masterdata.costcenters.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The persistent class for the cost_center_priv database table.
 * 
 */
@Entity
@Table(name = "cost_center_priv")
@IdClass(CostCenterPrivPK.class)
@NamedQuery(name = "CostCenterPriv.findAll", query = "SELECT c FROM CostCenterPriv c")
public class CostCenterPriv implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("user_id")
	@Column(name = "user_id")
	private Integer userId;

	@Id
	@JsonProperty("cost_center")
	@Column(name = "cost_center")
	private Integer costCenter;

	@JsonProperty("add_date")
	@Column(name = "add_date")
	private Timestamp addDate;

	@JsonProperty("add_priv")
	@Column(name = "add_priv")
	private Boolean addPriv;

	@JsonProperty("add_user")
	@Column(name = "add_user")
	private Integer addUser;

	@JsonProperty("modify_date")
	@Column(name = "modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_user")
	@Column(name = "modify_user")
	private Integer modifyUser;

	@JsonProperty("view_priv")
	@Column(name = "view_priv")
	private Boolean viewPriv;

	public CostCenterPriv() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getCostCenter() {
		return costCenter;
	}

	public void setCostCenter(Integer costCenter) {
		this.costCenter = costCenter;
	}

	public Timestamp getAddDate() {
		return this.addDate;
	}

	public void setAddDate(Timestamp addDate) {
		this.addDate = addDate;
	}

	public Boolean getAddPriv() {
		return this.addPriv;
	}

	public void setAddPriv(Boolean addPriv) {
		this.addPriv = addPriv;
	}

	public Integer getAddUser() {
		return this.addUser;
	}

	public void setAddUser(Integer addUser) {
		this.addUser = addUser;
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

	public Boolean getViewPriv() {
		return this.viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

}