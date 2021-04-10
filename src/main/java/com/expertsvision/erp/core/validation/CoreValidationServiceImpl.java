package com.expertsvision.erp.core.validation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.ConfirmException;
import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.form.service.InMemoryFormsViewService;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.module.service.InMemoryModulesViewService;
import com.expertsvision.erp.core.privilege.entity.FormPrivilagePK;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.privilege.service.InMemoryFormPrivilageService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;

@Service
public class CoreValidationServiceImpl implements CoreValidationService {

	@Autowired
	private InMemoryModulesViewService inMemoryModulesViewService;

	@Autowired
	private InMemoryFormsViewService inMemoryFormsViewService;

	@Autowired
	private InMemoryFormPrivilageService inMemoryFormPrivilageService;

	@Override
	public void notNull(Object field, String labelCode) {
		if (!(field != null)) {
			throw new ValidationException("you_must_enter", labelCode);
		}
	}

	@Override
	public void notBlank(String field, String labelCode) {
		if (field != null && field.isBlank()) {
			throw new ValidationException("you_must_enter", labelCode);
		}
	}

	@Override
	public void greaterThanOrEqualZero(Integer field, String labelCode) {
		if (field != null && !(field >= 0)) {
			throw new ValidationException("must_greater_equal_zero", labelCode);
		}
	}

	@Override
	public void greaterThanZero(Integer field, String labelCode) {
		if (field != null && !(field > 0)) {
			throw new ValidationException("must_greater_zero", labelCode);
		}
	}

	@Override
	public void inValues(Integer field, List<Integer> values, String labelCode) {
		if (field != null && !(values.stream().anyMatch(i -> i.equals(field)))) {
			throw new ValidationException("must_in_values", labelCode, values);
		}
	}

	@Override
	public void runDatabaseValidation(List<String> validation) {
		Map<String, String> statusMap = new HashMap<String, String>();
		statusMap.put("status",
				validation.stream().filter(t -> t.startsWith("status:")).findFirst().get().replace("status:", ""));
		statusMap.put("messageCode", validation.stream().filter(t -> t.startsWith("message_code:")).findFirst().get()
				.replace("message_code:", ""));
		statusMap.put("labelCode", validation.stream().filter(t -> t.startsWith("label_code:")).findFirst().get()
				.replace("label_code:", ""));
		statusMap.put("value",
				validation.stream().filter(t -> t.startsWith("value:")).findFirst().get().replace("value:", ""));
		if (!statusMap.get("status").equals("true")) {
			if (statusMap.get("value").equals("null")) {
				throw new ValidationException(statusMap.get("messageCode"), statusMap.get("labelCode"));
			} else if (statusMap.get("value").equals("unauthorized")) {
				throw new UnauthorizedException(statusMap.get("labelCode"));
			} else if (statusMap.get("value").equals("confirm")) {
				throw new ConfirmException(statusMap.get("messageCode"));
			} else {
				throw new ValidationException(statusMap.get("messageCode"), statusMap.get("labelCode"),
						statusMap.get("value"));
			}
		}
	}

	@Override
	public void activeModuleAndForm(Forms form) {
		Integer formNo = form.getFormNo();
		FormsView formsView = inMemoryFormsViewService.getFormsView(formNo);
		Integer moduleNo = formsView.getModuleNo();
		ModulesView modulesView = inMemoryModulesViewService.getModulesView(moduleNo);
		if (!formsView.getActive() || !modulesView.getActive()) {
			throw new UnauthorizedException("resource");
		}
	}

	public void validateHasFormPrivilege(UsersView loginUser, Forms form, FormsActions formAction) {
		if (loginUser.getSuperAdmin() || loginUser.getAdminUser()) return;
		FormPrivilageView formPrivilageView = inMemoryFormPrivilageService
				.getFormPrivilageView(new FormPrivilagePK(loginUser.getUserId(), form.getFormNo()));
		boolean hasPriv;
		switch (formAction) {
			case INCLUDE:
				hasPriv = formPrivilageView.getIncludePriv();
				break;
			case ADD:
				hasPriv = formPrivilageView.getAddPriv();
				break;
			case AUDIT:
				hasPriv = formPrivilageView.getAuditPriv();
				break;
			case DELETE:
				hasPriv = formPrivilageView.getDeletePriv();
				break;
			case MODIFY:
				hasPriv = formPrivilageView.getModifyPriv();
				break;
			case POST:
				hasPriv = formPrivilageView.getPostPriv();
				break;
			case PRINT:
				hasPriv = formPrivilageView.getPostPriv();
				break;
			case VIEW:
				hasPriv = formPrivilageView.getViewPriv();
				break;
			default:
				hasPriv = false;
				break;
		}
		if (!hasPriv) {
			throw new UnauthorizedException("resource");
		}
	}

}
