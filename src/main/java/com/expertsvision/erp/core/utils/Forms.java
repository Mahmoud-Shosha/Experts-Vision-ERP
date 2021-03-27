package com.expertsvision.erp.core.utils;

public enum Forms {
	
	LANGUAGE(99),
	LABELS(99),
	MESSAGES(99),
	MODULES(99),
	FORMS(99),
	USERS(1102),
	USERS_GROUPS(1101);
	
	private Integer formNo;
	
	Forms(Integer formNo) {
		this.formNo = formNo;
	}

	public Integer getFormNo() {
		return formNo;
	}

}
