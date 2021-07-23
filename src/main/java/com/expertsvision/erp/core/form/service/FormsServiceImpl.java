package com.expertsvision.erp.core.form.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.form.dao.FormsDAO;
import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.Form;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.module.service.InMemoryModulesService;
import com.expertsvision.erp.core.privilege.entity.FormPrivilagePK;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.privilege.service.InMemoryFormPrivilageService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.utils.GeneralConstants;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class FormsServiceImpl implements FormsService {

	@Autowired
	private FormsDAO formsDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	@Lazy
	private InMemoryFormsService inMemoryFormsService;

	@Autowired
	@Lazy
	private InMemoryFormPrivilageService inMemoryFormPrivilageService;

	@Autowired
	@Lazy
	private InMemoryModulesService inMemoryModulesService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Override
	public List<FormsView> getFormsViewList() {
		// Used only by the system
		// Return requested data
		List<FormsView> formsViewList = formsDAO.getFormsViewList();
		return formsViewList;
	}
	
	@Override
	@Transactional
	public List<FormsView> getFormsViewList(UsersView loginUser) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		List<FormsView> formsViewList = formsDAO.getFormsViewList();
		return formsViewList;
	}

	@Override
	@Transactional
	public FormsView getFormsView(UsersView loginUser, Integer formNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		FormsView formsView = formsDAO.getFormsView(formNo);
		if (formsView == null) {
			throw new ValidationException("not_exist", "form");
		}
		return formsView;
	}

	@Override
	@Transactional
	public SinglePage<FormsView> getFormsViewSinglePage(UsersView loginUser, long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<FormsView> singlePage = formsDAO.getFormsViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<FormsView> getFormsViewLastPage(UsersView loginUser) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<FormsView> singlePage = formsDAO.getFormsViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getFormsViewSinglePageNo(UsersView loginUser, Integer formNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		Long singlePageNo = formsDAO.getFormsViewSinglePageNo(formNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "form");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<FormsView> getFormsViewMultiplePages(UsersView loginUser, long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<FormsView> multiplePages = formsDAO.getFormsViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<FormsView> getFormsViewFilteredMultiplePages(UsersView loginUser, long pageNo,
			FormsViewFilter FormViewFilter) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<FormsView> multiplePages = formsDAO.getFormsViewFilteredMultiplePages(pageNo, FormViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public List<FormsView> getFormsViewMainTree(UsersView loginUser) {
		// Return requested data
		List<FormsView> formsViewMainTree = formsDAO.getFormsViewMainTree(loginUser);
		return formsViewMainTree;
	}

	@Override
	public Boolean IsFormsViewInMainTree(Integer userId, Integer formNo) {
		UsersView usersView = inMemoryUsersService.getUsersView(userId);
		FormsView formsView = inMemoryFormsService.getFormsView(formNo);
		ModulesView modulesView = inMemoryModulesService.getModulesView(formsView.getModuleNo());
		FormPrivilageView formPrivilageView = inMemoryFormPrivilageService
				.getFormPrivilageView(new FormPrivilagePK(userId, formNo));
		if (usersView.getSuperAdmin()) {
			return true;
		} else if (usersView.getAdminUser()) {
			if (modulesView.getModuleNo().equals(GeneralConstants.INTERNAL_SETTING_MODULE_ID)
					|| !modulesView.getActive())
				return false;
			else
				return true;
		} else {
			if (modulesView.getModuleNo().equals(GeneralConstants.INTERNAL_SETTING_MODULE_ID)
					|| !modulesView.getActive() || !formsView.getActive() || !formPrivilageView.getIncludePriv())
				return false;
			else
				return true;
		}
	}

	@Override
	@Transactional
	public void updateFormsView(UsersView loginUser, FormsView formsView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(formsView.getFormNo(), "form_no");
		coreValidationService.greaterThanZero(formsView.getFormNo(), "form_no");
		coreValidationService.notNull(formsView.getModuleNo(), "module_no");
		coreValidationService.greaterThanZero(formsView.getModuleNo(), "module_no");
		coreValidationService.notNull(formsView.getFormDName(), "name");
		coreValidationService.notBlank(formsView.getFormDName(), "name");
		if ((formsView.getFormFName() != null) && formsView.getFormFName().isBlank())
			formsView.setFormFName(null);
		coreValidationService.notNull(formsView.getParentForm(), "parent_form");
		coreValidationService.greaterThanZero(formsView.getParentForm(), "parent_form");
		coreValidationService.notNull(formsView.getActive(), "active");
		coreValidationService.notNull(formsView.getFormOrder(), "order_no");
		coreValidationService.greaterThanZero(formsView.getFormOrder(), "order_no");
		coreValidationService.notNull(formsView.getMain(), "main");
		// Database validation
		Form form = getFormFromFormsView(formsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("form_no", form.getFormNo());
		if (!generalDAO.isEntityExist("forms", conditions))
			throw new ValidationException("not_exist", "form_no");
		conditions.clear();
		conditions.put("module_no", form.getModuleNo());
		if (!generalDAO.isEntityExist("modules", conditions))
			throw new ValidationException("not_exist", "module_no");
		conditions.clear();
		conditions.put("form_no", form.getParentForm());
		if (!generalDAO.isEntityExist("forms", conditions))
			throw new ValidationException("not_exist", "parent_form");
		conditions.clear();
		conditions.put("form_d_name", form.getFormDName());
		String exceptionCondition = null;
		exceptionCondition = " and form_no != " + form.getFormNo();
		if (generalDAO.isEntityExist("forms", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		if (form.getFormFName() != null) {
			conditions.clear();
			conditions.put("form_f_name", form.getFormFName());
			if (generalDAO.isEntityExist("forms", conditions, exceptionCondition))
				throw new ValidationException("already_exist", "foreign_name");
		}
		// Update the from
		formsDAO.updateForms(form);
		inMemoryFormsService.updateFormsView();
	}

	public Form getFormFromFormsView(FormsView formsView) {
		Form form = new Form();
		try {
			form.setActive(formsView.getActive());
			form.setFormDName(Utils.escapeLiteral(null, formsView.getFormDName(), true).toString());
			if (formsView.getFormFName() == null)
				form.setFormFName(formsView.getFormFName());
			else
				form.setFormFName(Utils.escapeLiteral(null, formsView.getFormFName(), true).toString());
			form.setFormNo(formsView.getFormNo());
			form.setFormOrder(formsView.getFormOrder());
			form.setMain(formsView.getMain());
			form.setModuleNo(formsView.getModuleNo());
			form.setParentForm(formsView.getParentForm());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return form;
	}

}
