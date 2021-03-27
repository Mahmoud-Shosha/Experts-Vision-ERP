package com.expertsvision.erp.core.label.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public class LabelsViewFilter {
	
	@JsonProperty("label_code")
	private String labelCode;

	@JsonProperty("label_desc")
	private String labelDesc;

	@JsonProperty("lang_no")
	private Integer langNo;
	

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	public String getLabelDesc() {
		return labelDesc;
	}

	public void setLabelDesc(String labelDesc) {
		this.labelDesc = labelDesc;
	}

	public Integer getLangNo() {
		return langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}
	

	@Override
	public String toString() {
		return "LabelsViewFilter [labelCode=" + labelCode + ", labelDesc=" + labelDesc + ", langNo=" + langNo + "]";
	}

}
