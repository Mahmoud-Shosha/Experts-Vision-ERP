package com.expertsvision.erp.masterdata.inventory.maingroups.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.expertsvision.erp.core.utils.Addable;
import com.expertsvision.erp.core.utils.Modifyable;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;

/**
 * The persistent class for the main_group_priv database table.
 * 
 */
@Entity
@Table(name = "main_group_priv")
@IdClass(MainGroupPrivPK.class)
public class MainGroupPriv implements Serializable, Addable, Modifyable  {
	private static final long serialVersionUID = 1L;

	@Id
	@JsonProperty("user_id")
	@Column(name = "user_id")
	private Integer userId;

	@Id
	@JsonProperty("group_code")
	@Column(name = "group_code")
	private String groupCode;

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

	public MainGroupPriv() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getGroupCode() {
		return groupCode;
	}

	public void setGroupCode(String groupCode) {
		this.groupCode = groupCode;
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

	@Override
	public String toString() {
		return "MainGroupPriv [userId=" + userId + ", groupCode=" + groupCode + "]";
	}

}