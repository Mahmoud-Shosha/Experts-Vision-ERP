package com.expertsvision.erp.core.validation;

import java.math.BigDecimal;
import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.FlagDetails;
import com.expertsvision.erp.core.utils.FlagsActions;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;

public interface CoreValidationService {
	
	void notNull(Object field, String labelCode);
	
	void notNull(Object field, String labelCodeFirst, String labelCodeSecond, Object valueSecond);
	
	void notBlank(String field, String labelCode);
	
	void greaterThanOrEqualZero(Integer field, String labelCode);
	
	void greaterThanZero(Integer field, String labelCode);
	
	void greaterThanZero(BigDecimal field, String labelCode);
	
	void greaterThan(BigDecimal field1, BigDecimal field2, String labelCodeFirst, Object valueFirst,
			         String labelCodeSecond, Object valueSecond);
	
	void greaterThanOrEqual(BigDecimal field1, BigDecimal field2, String labelCodeFirst, Object valueFirst,
	                        String labelCodeSecond, Object valueSecond);
	
	void lessThan(BigDecimal field1, BigDecimal field2, String labelCodeFirst, Object valueFirst,
	         String labelCodeSecond, Object valueSecond);

	void lessThanOrEqual(BigDecimal field1, BigDecimal field2, String labelCodeFirst, Object valueFirst,
	                   String labelCodeSecond, Object valueSecond);
	
	void inValues(Integer field, List<Integer> values, String labelCode);
	
	void runDatabaseValidation(List<String> validation);
	
	void activeModuleAndForm(Forms form);
	
	void activeModule(Forms form);
	
	void activeFlagDetail(FlagDetails flagDetails);
	
	void validateHasFormPrivilege(UsersView loginUser, Forms form, FormsActions formAction);
	
	void validateHasFlagDetailPrivilege(UsersView loginUser, FlagDetails flagDetails,
			FlagsActions flagsAction) ;
			
}
