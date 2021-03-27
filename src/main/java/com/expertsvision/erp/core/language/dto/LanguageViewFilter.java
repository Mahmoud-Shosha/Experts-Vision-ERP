package com.expertsvision.erp.core.language.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LanguageViewFilter {
	
	@JsonProperty("lang_no")
	private Integer langNo;
	
	@JsonProperty("lang_name")
	private String langName;
	
	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("lang_dfl")
	private Boolean langDfl;
	
	

	public Integer getLangNo() {
		return langNo;
	}

	public void setLangNo(Integer langNo) {
		this.langNo = langNo;
	}

	public String getLangName() {
		return langName;
	}

	public void setLangName(String langName) {
		this.langName = langName;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getLangDfl() {
		return langDfl;
	}

	public void setLangDfl(Boolean langDfl) {
		this.langDfl = langDfl;
	}

	
	@Override
	public String toString() {
		return "LanguageViewFilter [langNo=" + langNo + ", langName=" + langName + ", active=" + active + ", langDfl="
				+ langDfl + "]";
	}

}
