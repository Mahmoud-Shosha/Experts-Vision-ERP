package com.expertsvision.erp.core.privilege.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.privilege.dao.FormPrivilageDAO;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.user.entity.UsersView;

@Service
public class FormPrivilageServiceImpl implements FormPrivilageService {
	
	@Autowired
	private FormPrivilageDAO formPrivilageViewDAO;
	
//	@Autowired
//	private CoreValidationService coreValidationService;

	
	@Override
	public List<FormPrivilageView> getFormPrivilageViewList(UsersView loginUser) {
//		coreValidationService.activeModuleAndForm(Forms.MODULES);
		// check that the user has privilege
		List<FormPrivilageView> formPrivilageViewList = null;
		try {
			formPrivilageViewList = formPrivilageViewDAO.getFormPrivilageViewList();
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return formPrivilageViewList;
	}
	
//	
//	@Override
//	public ModulesView getModulesView(UsersView loginUser, Integer moduleNo) {
////		coreValidationService.activeModuleAndForm(Forms.MODULES);
//		ModulesView modulesView = null;
//		try {
//			modulesView = modulesViewDAO.getModulesView(loginUser, moduleNo);
//		} catch (Exception e) {
//			throw new UnauthorizedException("resource");
//		}
//		if (modulesView == null) {
//			throw new ValidationException("not_exist", "module");
//		}
//		return modulesView;
//	}
//	
//	
//	@Override
//	public SinglePage<ModulesView> getModulesViewSinglePage(UsersView loginUser, long pageNo) {
//		coreValidationService.activeModuleAndForm(Forms.MODULES);
//		SinglePage<ModulesView> singlePage = null;
//		try {
//			singlePage = modulesViewDAO.getModulesViewSinglePage(loginUser, pageNo);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new UnauthorizedException("resource");
//		}
//		return singlePage;
//	}
//	
//	@Override
//	public SinglePage<ModulesView> getModulesViewLastPage(UsersView loginUser) {
//		coreValidationService.activeModuleAndForm(Forms.MODULES);
//		SinglePage<ModulesView> singlePage = null;
//		try {
//			singlePage = modulesViewDAO.getModulesViewLastPage(loginUser);
//		} catch (Exception e) {
//			throw new UnauthorizedException("resource");
//		}
//		return singlePage;
//	}
//	
//	@Override
//	public MultiplePages<ModulesView> getModulesViewMultiplePages(UsersView loginUser, long pageNo) {
//		coreValidationService.activeModuleAndForm(Forms.MODULES);
//		MultiplePages<ModulesView> multiplePages = null;
//		try {
//			multiplePages = modulesViewDAO.getModulesViewMultiplePages(loginUser, pageNo);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new UnauthorizedException("resource");
//		}
//		return multiplePages;
//	}
//	
//	@Override
//	public MultiplePages<ModulesView> getModulesViewFilteredMultiplePages(UsersView loginUser, long pageNo, ModulesViewFilter ModuleViewFilter) {
//		coreValidationService.activeModuleAndForm(Forms.MODULES);
//		MultiplePages<ModulesView> multiplePages = null;
//		try {
//			multiplePages = modulesViewDAO.getModulesViewFilteredMultiplePages(loginUser,pageNo, ModuleViewFilter);
//		} catch (Exception e) {
//			throw new UnauthorizedException("resource");
//		}
//		return multiplePages;
//	}
//	
//	@Override
//	public void updateModulesView(UsersView loginUser, ModulesView modulesView) {
//		coreValidationService.activeModuleAndForm(Forms.MODULES);
//		coreValidationService.notNull(modulesView.getModuleNo(), "module_no");
//		coreValidationService.greaterThanZero(modulesView.getModuleNo(), "module_no");
//		coreValidationService.notNull(modulesView.getModuleDName(), "name");
//		coreValidationService.notBlank(modulesView.getModuleDName(), "name");
//		if (modulesView.getModuleFName().isBlank()) modulesView.setModuleFName(null);
//		coreValidationService.notNull(modulesView.getShortcut(), "shortcut");
//		coreValidationService.notBlank(modulesView.getShortcut(), "shortcut");
//		coreValidationService.notNull(modulesView.getActive(), "active");
//		coreValidationService.notNull(modulesView.getOrderNo(), "order_no");
//		coreValidationService.greaterThanZero(modulesView.getOrderNo(), "order_no");
//		coreValidationService.runDatabaseValidation(modulesViewDAO.updateModulesView(loginUser, modulesView));	
//	}
}
