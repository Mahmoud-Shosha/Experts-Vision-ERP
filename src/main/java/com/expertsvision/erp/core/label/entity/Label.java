package com.expertsvision.erp.core.label.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the labels database table.
 * 
 */
@Entity
@Table(name="labels")
@IdClass(LabelsPK.class)
@NamedQuery(name="Label.findAll", query="SELECT l FROM Label l")
public class Label implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lang_no")
	private Integer langNo;

	@Id
	@Column(name="label_code")
	private String labelCode;
	
	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="label_desc")
	private String labelDesc;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	public Label() {
	}

	public Integer getLangNo() {
		return langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
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

	public String getLabelDesc() {
		return this.labelDesc;
	}

	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
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

	@Override
	public String toString() {
		return "Label [langNo=" + langNo + ", labelCode=" + labelCode + ", addDate=" + addDate + ", addUser=" + addUser
				+ ", labelDesc=" + labelDesc + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser + "]";
	}

}