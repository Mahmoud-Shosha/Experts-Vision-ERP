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
	GEOGRAPHICAL_CODING(1301),
	COMPANIES_AND_BRANCHES(1302),
	MASTER_DATA_PRIVILEGES(1109),
	CURRENCY(1304),
	FINANCIAL_CODING(2102),
	CHART_OF_ACCOUNTS(2101),
	COST_CENTERS(2104),
	EMPLOYEE_INFORMATION(1305);
	
	private Integer formNo;
	
	Forms(Integer formNo) {
		this.formNo = formNo;
	}

	public Integer getFormNo() {
		return formNo;
	}

}
