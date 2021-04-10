package com.expertsvision.erp.core.privilege.entity;

import java.io.Serializable;
import javax.persistence.*;


import java.sql.Timestamp;


/**
 * The persistent class for the form_privilage database table.
 * 
 */
@Entity
@Table(name="form_privilage")
@IdClass(FormPrivilagePK.class)
@NamedQuery(name="FormPrivilage.findAll", query="SELECT f FROM FormPrivilage f")
public class FormPrivilage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="user_id")
	private Integer userId;

	@Id
	@Column(name="form_no")
	private Integer formNo;
	
	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_priv")
	private Boolean addPriv;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="audit_priv")
	private Boolean auditPriv;

	@Column(name="delete_priv")
	private Boolean deletePriv;

	@Column(name="include_priv")
	private Boolean includePriv;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_priv")
	private Boolean modifyPriv;

	@Column(name="modify_user")
	private Integer modifyUser;

	@Column(name="post_priv")
	private Boolean postPriv;

	@Column(name="print_priv")
	private Boolean printPriv;

	@Column(name="view_priv")
	private Boolean viewPriv;

	public FormPrivilage() {
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFormNo() {
		return formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
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

	public Boolean getAuditPriv() {
		return this.auditPriv;
	}

	public void setAuditPriv(Boolean auditPriv) {
		this.auditPriv = auditPriv;
	}

	public Boolean getDeletePriv() {
		return this.deletePriv;
	}

	public void setDeletePriv(Boolean deletePriv) {
		this.deletePriv = deletePriv;
	}

	public Boolean getIncludePriv() {
		return this.includePriv;
	}

	public void setIncludePriv(Boolean includePriv) {
		this.includePriv = includePriv;
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

	public Boolean getPostPriv() {
		return this.postPriv;
	}

	public void setPostPriv(Boolean postPriv) {
		this.postPriv = postPriv;
	}

	public Boolean getPrintPriv() {
		return this.printPriv;
	}

	public void setPrintPriv(Boolean printPriv) {
		this.printPriv = printPriv;
	}

	public Boolean getViewPriv() {
		return this.viewPriv;
	}

	public void setViewPriv(Boolean viewPriv) {
		this.viewPriv = viewPriv;
	}

	
	@Override
	public String toString() {
		return "FormPrivilage [userId=" + userId + ", formNo=" + formNo + ", addDate=" + addDate + ", addPriv="
				+ addPriv + ", addUser=" + addUser + ", auditPriv=" + auditPriv + ", deletePriv=" + deletePriv
				+ ", includePriv=" + includePriv + ", modifyDate=" + modifyDate + ", modifyPriv=" + modifyPriv
				+ ", modifyUser=" + modifyUser + ", postPriv=" + postPriv + ", printPriv=" + printPriv + ", viewPriv="
				+ viewPriv + "]";
	}

}