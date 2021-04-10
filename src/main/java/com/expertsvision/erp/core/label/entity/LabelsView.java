package com.expertsvision.erp.core.label.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Timestamp;


/**
 * The persistent class for the labels_view database view.
 * 
 */
@Entity
@Table(name="labels_view")
@IdClass(LabelsPK.class)
@NamedQuery(name="LabelsView.findAll", query="SELECT l FROM LabelsView l")
public class LabelsView implements Serializable {
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

	@Id
	@JsonProperty("label_code")
	@Column(name="label_code")
	private String labelCode;

	@JsonProperty("label_desc")
	@Column(name="label_desc")
	private String labelDesc;

	@Id
	@JsonProperty("lang_no")
	@Column(name="lang_no")
	private Integer langNo;

	@JsonProperty("lang_no_name")
	@Column(name="lang_no_name")
	private String langNoName;

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

	public LabelsView() {
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

	public String getLabelCode() {
		return this.labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	public String getLabelDesc() {
		return this.labelDesc;
	}

	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}

	public Integer getLangNo() {
		return this.langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}

	public String getLangNoName() {
		return this.langNoName;
	}

	public void setLangNoName(String langNoName) {
		this.langNoName = langNoName;
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

	@Override
	public String toString() {
		return "LabelsView [addDate=" + addDate + ", addUser=" + addUser + ", addUserDName=" + addUserDName
				+ ", addUserFName=" + addUserFName + ", labelCode=" + labelCode + ", labelDesc=" + labelDesc
				+ ", langNo=" + langNo + ", langNoName=" + langNoName + ", modifyDate=" + modifyDate + ", modifyUser="
				+ modifyUser + ", modifyUserDName=" + modifyUserDName + ", modifyUserFName=" + modifyUserFName + "]";
	}

	
}