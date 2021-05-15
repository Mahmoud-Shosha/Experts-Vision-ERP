package com.expertsvision.erp.core.flagdetail.dto;



import com.fasterxml.jackson.annotation.JsonProperty;

public class FlagDetailViewFilter {

	@JsonProperty("active")
	private Boolean active;

	@JsonProperty("flag_code")
	private String flagCode;

	@JsonProperty("flag_priv")
	private Boolean flagPriv;

	@JsonProperty("flag_value")
	private Integer flagValue;

	@JsonProperty("label_code")
	private String labelCode;

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

	public Boolean getFlagPriv() {
		return flagPriv;
	}

	public void setFlagPriv(Boolean flagPriv) {
		this.flagPriv = flagPriv;
	}

	public Integer getFlagValue() {
		return flagValue;
	}

	public void setFlagValue(Integer flagValue) {
		this.flagValue = flagValue;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	@Override
	public String toString() {
		return "FlagMasterViewFilter [active=" + active + ", flagCode=" + flagCode + ", flagPriv=" + flagPriv
				+ ", flagValue=" + flagValue + ", labelCode=" + labelCode + "]";
	}

}
