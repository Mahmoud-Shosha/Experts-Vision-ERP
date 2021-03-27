package com.expertsvision.erp.core.message.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MessagesViewFilter {

	@JsonProperty("message_code")
	private String messageCode;

	@JsonProperty("message_desc")
	private String messageDesc;

	@JsonProperty("lang_no")
	private Integer langNo;
	

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageDesc() {
		return messageDesc;
	}

	public void setMessageDesc(String messageDesc) {
		this.messageDesc = messageDesc;
	}

	public Integer getLangNo() {
		return langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}

	
	@Override
	public String toString() {
		return "MessagesViewFilter [messageCode=" + messageCode + ", messageDesc=" + messageDesc + ", langNo=" + langNo
				+ "]";
	}
	
}
