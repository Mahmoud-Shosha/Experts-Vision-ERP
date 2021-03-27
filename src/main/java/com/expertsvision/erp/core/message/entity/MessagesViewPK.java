package com.expertsvision.erp.core.message.entity;

import java.io.Serializable;

/**
 * The primary key class for the messages_view database view.
 * 
 */
public class MessagesViewPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	private Integer langNo;

	private String messageCode;
	

	public MessagesViewPK() {
	}


	public MessagesViewPK(Integer langNo, String messageCode) {
		super();
		this.langNo = langNo;
		this.messageCode = messageCode;
	}


	public Integer getLangNo() {
		return this.langNo;
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
		return "MessagesViewPK [langNo=" + langNo + ", messageCode=" + messageCode + "]";
	}


	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof MessagesViewPK)) {
			return false;
		}
		MessagesViewPK castOther = (MessagesViewPK)other;
		return 
			this.langNo.equals(castOther.langNo)
			&& this.messageCode.equals(castOther.messageCode);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.langNo.hashCode();
		hash = hash * prime + this.messageCode.hashCode();
		
		return hash;
	}
	
}