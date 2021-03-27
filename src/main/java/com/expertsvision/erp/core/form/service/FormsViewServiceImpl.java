package com.expertsvision.erp.core.form.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.form.dao.FormsViewDAO;
import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class FormsViewServiceImpl implements FormsViewService {
	
	@Autowired
	private FormsViewDAO formsViewDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;

	
	@Override
	public List<FormsView> getFormsViewList(UsersView loginUser) {
//		coreValidationService.activeModuleAndForm(Forms.FORMS);
		List<FormsView> formsViewList = null;
		try {
			formsViewList = formsViewDAO.getFormsViewList(loginUser);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return formsViewList;
	}
	
	
	@Override
	public FormsView getFormsView(UsersView loginUser, Integer formNo) {
//		coreValidationService.activeModuleAndForm(Forms.FORMS);
		FormsView formsView = null;
		try {
			formsView = formsViewDAO.getFormsView(loginUser, formNo);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		if (formsView == null) {
			throw new ValidationException("not_exist", "form");
		}
		return formsView;
	}
	
	@Override
	public SinglePage<FormsView> getFormsViewSinglePage(UsersView loginUser, long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.FORMS);
		SinglePage<FormsView> singlePage = null;
		try {
			singlePage = formsViewDAO.getFormsViewSinglePage(loginUser, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public SinglePage<FormsView> getFormsViewLastPage(UsersView loginUser) {
		coreValidationService.activeModuleAndForm(Forms.FORMS);
		SinglePage<FormsView> singlePage = null;
		try {
			singlePage = formsViewDAO.getFormsViewLastPage(loginUser);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public MultiplePages<FormsView> getFormsViewMultiplePages(UsersView loginUser, long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.FORMS);
		MultiplePages<FormsView> multiplePages = null;
		try {
			multiplePages = formsViewDAO.getFormsViewMultiplePages(loginUser, pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	@Override
	public MultiplePages<FormsView> getFormsViewFilteredMultiplePages(UsersView loginUser, long pageNo, FormsViewFilter FormViewFilter) {
		coreValidationService.activeModuleAndForm(Forms.FORMS);
		MultiplePages<FormsView> multiplePages = null;
		try {
			multiplePages = formsViewDAO.getFormsViewFilteredMultiplePages(loginUser,pageNo, FormViewFilter);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	@Override
	public List<FormsView> getFormsViewMainTree(UsersView loginUser) {
		List<FormsView> formsViewMainTree = null;
		try {
			formsViewMainTree = formsViewDAO.getFormsViewMainTree(loginUser);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return formsViewMainTree;
	}
	
	
	@Override
	public void updateFormsView(UsersView loginUser, FormsView formsView) {
		coreValidationService.activeModuleAndForm(Forms.FORMS);
		coreValidationService.notNull(formsView.getFormNo(), "form_no");
		coreValidationService.greaterThanZero(formsView.getFormNo(), "form_no");
		coreValidationService.notNull(formsView.getModuleNo(), "module_no");
		coreValidationService.greaterThanZero(formsView.getModuleNo(), "module_no");
		coreValidationService.notNull(formsView.getFormDName(), "name");
		coreValidationService.notBlank(formsView.getFormDName(), "name");
		if (formsView.getFormFName().isBlank()) formsView.setFormFName(null);
		coreValidationService.notNull(formsView.getParentForm(), "parent_form");
		coreValidationService.greaterThanZero(formsView.getParentForm(), "parent_form");
		coreValidationService.notNull(formsView.getActive(), "active");
		coreValidationService.notNull(formsView.getFormOrder(), "order_no");
		coreValidationService.greaterThanZero(formsView.getFormOrder(), "order_no");
		coreValidationService.notNull(formsView.getMain(), "main");
		coreValidationService.runDatabaseValidation(formsViewDAO.updateFormsView(loginUser, formsView));
	}


}
