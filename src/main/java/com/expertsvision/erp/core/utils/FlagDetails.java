package com.expertsvision.erp.core.utils;

public enum FlagDetails {
	
	LANGUAGE("internal_coding", 2),
	LABELS("internal_coding", 1),
	MESSAGES("internal_coding", 3),
	MODULES("internal_coding", 5),
	FORMS("internal_coding", 4),
	FLAG("internal_coding", 6),
	REGION("geographical_coding", 1),
	COUNTRY("geographical_coding", 2),
	PROVINCE("geographical_coding", 3),
	CITY("geographical_coding", 4),
	ZONE("geographical_coding", 5),
	COMPANY_GROUPS("companies_and_branches", 1),
	COMPANIES("companies_and_branches", 2),
	BRANCHES("companies_and_branches", 3),
	ACCOUNTS_GROUP("financial_coding", 1),
	COST_CENTER_GROUP("financial_coding", 2);
	
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
