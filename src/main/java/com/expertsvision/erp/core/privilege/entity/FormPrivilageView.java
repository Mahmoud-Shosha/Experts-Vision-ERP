package com.expertsvision.erp.core.privilege.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the form_privilage_view database table.
 * 
 */
@Entity
@Table(name="form_privilage_view")
@IdClass(FormPrivilagePK.class)
@NamedQuery(name="FormPrivilageView.findAll", query="SELECT f FROM FormPrivilageView f")
public class FormPrivilageView implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonProperty("add_date")
	@Column(name="add_date")
	private Timestamp addDate;

	@JsonProperty("add_priv")
	@Column(name="add_priv")
	private Boolean addPriv;

	@JsonProperty("add_user")
	@Column(name="add_user")
	private Integer addUser;

	@JsonProperty("add_user_d_name")
	@Column(name="add_user_d_name")
	private String addUserDName;

	@JsonProperty("add_user_f_name")
	@Column(name="add_user_f_name")
	private String addUserFName;

	@JsonProperty("audit_priv")
	@Column(name="audit_priv")
	private Boolean auditPriv;

	@JsonProperty("delete_priv")
	@Column(name="delete_priv")
	private Boolean deletePriv;

	@JsonProperty("form_d_name")
	@Column(name="form_d_name")
	private String formDName;

	@JsonProperty("form_f_name")
	@Column(name="form_f_name")
	private String formFName;

	@Id
	@JsonProperty("form_no")
	@Column(name="form_no")
	private Integer formNo;

	@JsonProperty("include_priv")
	@Column(name="include_priv")
	private Boolean includePriv;

	@JsonProperty("modify_date")
	@Column(name="modify_date")
	private Timestamp modifyDate;

	@JsonProperty("modify_priv")
	@Column(name="modify_priv")
	private Boolean modifyPriv;

	@JsonProperty("modify_user")
	@Column(name="modify_user")
	private Integer modifyUser;

	@JsonProperty("modify_user_d_name")
	@Column(name="modify_user_d_name")
	private String modifyUserDName;

	@JsonProperty("modify_user_f_name")
	@Column(name="modify_user_f_name")
	private String modifyUserFName;

	@JsonProperty("post_priv")
	@Column(name="post_priv")
	private Boolean postPriv;

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

	public FormPrivilageView() {
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

	public String getFormDName() {
		return this.formDName;
	}

	public void setFormDName(String formDName) {
		this.formDName = formDName;
	}

	public String getFormFName() {
		return this.formFName;
	}

	public void setFormFName(String formFName) {
		this.formFName = formFName;
	}

	public Integer getFormNo() {
		return this.formNo;
	}

	public void setFormNo(Integer formNo) {
		this.formNo = formNo;
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

	@Override
	public String toString() {
		return "FormPrivilageView [addDate=" + addDate + ", addPriv=" + addPriv + ", addUser=" + addUser
				+ ", addUserDName=" + addUserDName + ", addUserFName=" + addUserFName + ", auditPriv=" + auditPriv
				+ ", deletePriv=" + deletePriv + ", formDName=" + formDName + ", formFName=" + formFName + ", formNo="
				+ formNo + ", includePriv=" + includePriv + ", modifyDate=" + modifyDate + ", modifyPriv=" + modifyPriv
				+ ", modifyUser=" + modifyUser + ", modifyUserDName=" + modifyUserDName + ", modifyUserFName="
				+ modifyUserFName + ", postPriv=" + postPriv + ", printPriv=" + printPriv + ", userId=" + userId
				+ ", viewPriv=" + viewPriv + "]";
	}

}