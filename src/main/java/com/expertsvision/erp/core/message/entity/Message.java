package com.expertsvision.erp.core.message.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the messages database table.
 * 
 */
@Entity
@Table(name="messages")
@IdClass(MessagesPK.class)
@NamedQuery(name="Message.findAll", query="SELECT m FROM Message m")
public class Message implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="lang_no")
	private Integer langNo;

	@Id
	@Column(name="message_code")
	private String messageCode;
	
	@Column(name="add_date")
	private Timestamp addDate;

	@Column(name="add_user")
	private Integer addUser;

	@Column(name="message_desc")
	private String messageDesc;

	@Column(name="modify_date")
	private Timestamp modifyDate;

	@Column(name="modify_user")
	private Integer modifyUser;

	public Message() {
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

	public String getMessageDesc() {
		return this.messageDesc;
	}

	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
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

	public Integer getLangNo() {
		return langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	@Override
	public String toString() {
		return "Message [langNo=" + langNo + ", messageCode=" + messageCode + ", addDate=" + addDate + ", addUser="
				+ addUser + ", messageDesc=" + messageDesc + ", modifyDate=" + modifyDate + ", modifyUser=" + modifyUser
				+ "]";
	}

}