package com.expertsvision.erp.core.validation;

import java.util.List;

import com.expertsvision.erp.core.utils.Forms;

public interface CoreValidationService {
	
	void notNull(Object field, String labelCode);
	
	void notBlank(String field, String labelCode);
	
	void greaterThanOrEqualZero(Integer field, String labelCode);
	
	void greaterThanZero(Integer field, String labelCode);
	
	void inValues(Integer field, List<Integer> values, String labelCode);
	
	void runDatabaseValidation(List<String> validation);
	
	void activeModuleAndForm(Forms form);
			
}
