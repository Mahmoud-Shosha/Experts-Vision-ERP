package com.expertsvision.erp.core.utils;

public enum FlagDetails {
	
	LANGUAGE("internal_coding", 2),
	LABELS("internal_coding", 1),
	MESSAGES("internal_coding", 3),
	MODULES("internal_coding", 5),
	FORMS("internal_coding", 4),
	Flag("internal_coding", 6);
	
	private Integer flagValue;
	private String flagCode;
	
	
	private FlagDetails(String flagCode, Integer flagValue) {
		this.flagValue = flagValue;
		this.flagCode = flagCode;
	}

	public Integer getFlagValue() {
		return flagValue;
	}

	public String getFlagCode() {
		return flagCode;
	}

}
