package com.expertsvision.erp.core.utils;

public enum Forms {
	
	LANGUAGE(99),
	LABELS(23),
	MESSAGES(99),
	MODULES(99),
	FORMS(99),
	USERS(1102),
	USERS_GROUPS(1101),
	FORM_PRIVILEGES(1110),
	GEOGRAPHICAL_CODING(1301);
	
	private Integer formNo;
	
	Forms(Integer formNo) {
		this.formNo = formNo;
	}

	public Integer getFormNo() {
		return formNo;
	}

}
