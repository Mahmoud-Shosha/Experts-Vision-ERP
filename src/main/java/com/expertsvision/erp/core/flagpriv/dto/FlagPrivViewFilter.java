package com.expertsvision.erp.core.flagpriv.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlagPrivViewFilter {

	@JsonProperty("flag_code")
	private String flagCode;

	@JsonProperty("label_code")
	private String labelCode;

	public String getFlagCode() {
		return flagCode;
	}

	public void setFlagCode(String flagCode) {
		this.flagCode = flagCode;
	}

	public String getLabelCode() {
		return labelCode;
	}

	public void setLabelCode(String labelCode) {
		this.labelCode = labelCode;
	}

	@Override
	public String toString() {
		return "FlagMasterViewFilter [flagCode=" + flagCode + ", labelCode=" + labelCode + "]";
	}

}
