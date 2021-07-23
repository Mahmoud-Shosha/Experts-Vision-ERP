package com.expertsvision.erp.core.flagdetail.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the flag_detail_main_tree database view.
 * 
 */
@Entity
@Table(name="flag_detail_main_tree")
@IdClass(FlagDetailMainTreePK.class)
@NamedQuery(name="FlagDetailMainTree.findAll", query="SELECT f FROM FlagDetailMainTree f")
public class FlagDetailMainTree implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("active")
	@Column(name="active")
	private Boolean active;

	@JsonProperty("add_date")
	@Column(name="add_date")
	private Timestamp addDate;

	@JsonProperty("add_priv")
	@Column(name="add_priv")
	private Boolean addPriv;

	@JsonProperty("add_user")
	@Column(name="add_user")
	private Integer addUser;

	@JsonProperty("delete_priv")
	@Column(name="delete_priv")
	private Boolean deletePriv;

	@Id
	@JsonProperty("flag_code")
	@Column(name="flag_code")
	private String flagCode;

	@JsonProperty("flag_priv")
	@Column(name="flag_priv")
	private Boolean flagPriv;

	@JsonProperty("flag_sr")
	@Column(name="flag_sr")
	private Integer flagSr;

	@Id
	@JsonProperty("flag_value")
	@Column(name="flag_value")
	private Integer flagValue;

	@JsonProperty("label_code")
	@Column(name="label_code")
	private String labelCode;

	@JsonProperty("modify_date")
	@Column(name="modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_priv")
	@Column(name="modify_priv")
	private Boolean modifyPriv;

	@JsonProperty("modify_user")
	@Column(name="modify_user")
	private Integer modifyUser;

	@JsonProperty("print_priv")
	@Column(name="print_priv")
	private Boolean printPriv;

	@Id
	@JsonProperty("user_id")
	@Column(name="user_id")
	private Integer userId;

	@JsonProperty("view_priv")
	@Column(name="view_priv")
	private Boolean viewPriv;

	public FlagDetailMainTree() {
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

	public Boolean getDeletePriv() {
		return this.deletePriv;
	}

	public void setDeletePriv(Boolean deletePriv) {
		this.deletePriv = deletePriv;
	}

	public String getFlagCode() {
		return this.flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

	public Boolean getFlagPriv() {
		return this.flagPriv;
	}

	public void setFlagPriv(Boolean flagPriv) {
		this.flagPriv = flagPriv;
	}

	public Integer getFlagSr() {
		return this.flagSr;
	}

	public void setFlagSr(Integer flagSr) {
		this.flagSr = flagSr;
	}

	public Integer getFlagValue() {
		return this.flagValue;
	}

	public void setFlagValue(Integer flagValue) {
		this.flagValue = flagValue;
	}

	public String getLabelCode() {
		return this.labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	public Timestamp getModifyDate() {
		return this.modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Boolean getModifyPriv() {
		return this.modifyPriv;
	}

	public void setModifyPriv(Boolean modifyPriv) {
		this.modifyPriv = modifyPriv;
	}

	public Integer getModifyUser() {
		return this.modifyUser;
	}

	public void setModifyUser(Integer modifyUser) {
		this.modifyUser = modifyUser;
	}

	public Boolean getPrintPriv() {
		return this.printPriv;
	}

	public void setPrintPriv(Boolean printPriv) {
		this.printPriv = printPriv;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Boolean getViewPriv() {
		return this.viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

}