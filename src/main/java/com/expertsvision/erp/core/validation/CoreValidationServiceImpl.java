package com.expertsvision.erp.core.validation;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.ConfirmException;
import com.expertsvision.erp.core.exception.DetailValidationException;
import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailPK;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.flagdetail.service.InMemoryFlagDetailService;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivPK;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivView;
import com.expertsvision.erp.core.flagpriv.service.InMemoryFlagPrivService;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.form.service.InMemoryFormsService;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.module.service.InMemoryModulesService;
import com.expertsvision.erp.core.privilege.entity.FormPrivilagePK;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.privilege.service.InMemoryFormPrivilageService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.FlagDetails;
import com.expertsvision.erp.core.utils.FlagsActions;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;

@Service
public class CoreValidationServiceImpl implements CoreValidationService {

	@Autowired
	private InMemoryModulesService inMemoryModulesViewService;

	@Autowired
	private InMemoryFormsService inMemoryFormsViewService;

	@Autowired
	private InMemoryFormPrivilageService inMemoryFormPrivilageService;

	@Autowired
	private InMemoryFlagDetailService inMemoryFlagDetailService;

	@Autowired
	private InMemoryFlagPrivService inMemoryFlagPrivService;

	@Override
	public void notNull(Object field, String labelCode) {
		if (!(field != null)) {
			throw new ValidationException("you_must_enter", labelCode);
		}
	}

	@Override
	public void notNull(Object field, String labelCodeFirst, String labelCodeSecond, Object valueSecond) {
		if (!(field != null)) {
			throw new DetailValidationException("you_must_enter_detail", labelCodeFirst, null, labelCodeSecond, valueSecond);
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
	public void greaterThanZero(BigDecimal field, String labelCode) {
		if (field != null && !(field.compareTo(BigDecimal.ZERO) > 0)) {
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
		while (true) {
			formsView = inMemoryFormsViewService.getFormsView(formsView.getParentForm());
			if (!formsView.getActive()) {
				throw new UnauthorizedException("resource");
			} else if (formsView.getParentForm().equals(0)) {
				break;
			}
		}
	}

	@Override
	public void activeModule(Forms form) {
		Integer formNo = form.getFormNo();
		FormsView formsView = inMemoryFormsViewService.getFormsView(formNo);
		Integer moduleNo = formsView.getModuleNo();
		ModulesView modulesView = inMemoryModulesViewService.getModulesView(moduleNo);
		if (!modulesView.getActive()) {
			throw new UnauthorizedException("resource");
		}
	}

	@Override
	public void activeFlagDetail(FlagDetails flagDetails) {
		if (!inMemoryFlagDetailService
				.getFlagDetailView(new FlagDetailPK(flagDetails.getFlagCode(), flagDetails.getFlagValue()))
				.getActive()) {
			throw new UnauthorizedException("resource");
		}
	}

	@Override
	public void validateHasFormPrivilege(UsersView loginUser, Forms form, FormsActions formAction) {
		if (loginUser.getSuperAdmin() || loginUser.getAdminUser())
			return;
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
			hasPriv = formPrivilageView.getPrintPriv();
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
		FormsView formsView = inMemoryFormsViewService.getFormsView(form.getFormNo());
		while (true) {
			formsView = inMemoryFormsViewService.getFormsView(formsView.getParentForm());
			formPrivilageView = inMemoryFormPrivilageService
					.getFormPrivilageView(new FormPrivilagePK(loginUser.getUserId(), formsView.getFormNo()));
			if (!formPrivilageView.getIncludePriv()) {
				throw new UnauthorizedException("resource");
			} else if (formsView.getParentForm().equals(0)) {
				break;
			}
		}
	}

	@Override
	public void validateHasFlagDetailPrivilege(UsersView loginUser, FlagDetails flagDetails, FlagsActions flagsAction) {
		if (loginUser.getSuperAdmin() || loginUser.getAdminUser())
			return;
		FlagPrivView flagPrivView = inMemoryFlagPrivService.getFlagPrivView(
				new FlagPrivPK(loginUser.getUserId(), flagDetails.getFlagCode(), flagDetails.getFlagValue()));
		FlagDetailView flagDetailView = inMemoryFlagDetailService
				.getFlagDetailView(new FlagDetailPK(flagDetails.getFlagCode(), flagDetails.getFlagValue()));
		if (!flagDetailView.getFlagPriv())
			return;
		boolean hasPriv;
		switch (flagsAction) {
		case ADD:
			hasPriv = flagPrivView.getAddPriv();
			break;
		case DELETE:
			hasPriv = flagPrivView.getDeletePriv();
			break;
		case MODIFY:
			hasPriv = flagPrivView.getModifyPriv();
			break;
		case PRINT:
			hasPriv = flagPrivView.getPrintPriv();
			break;
		case VIEW:
			hasPriv = flagPrivView.getViewPriv();
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
