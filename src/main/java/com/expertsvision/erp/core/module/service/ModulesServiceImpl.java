package com.expertsvision.erp.core.module.service;

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
import com.expertsvision.erp.core.module.dao.ModulesDAO;
import com.expertsvision.erp.core.module.dto.ModulesViewFilter;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.module.entity.Module;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class ModulesServiceImpl implements ModulesService {
	
	@Autowired
	private ModulesDAO modulesDAO;
	
	@Autowired
	private GeneralDAO generalDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;
	
	@Autowired
	@Lazy
	private InMemoryModulesService inMemoryModulesService;

	
	@Override
	@Transactional
	public List<ModulesView> getModulesViewList(UsersView loginUser) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Return requested data
		List<ModulesView> modulesViewList = modulesDAO.getModulesViewList();
		return modulesViewList;
	}
	
	
	@Override
	@Transactional
	public ModulesView getModulesView(UsersView loginUser, Integer moduleNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Return requested data
		ModulesView modulesView = modulesDAO.getModulesView(moduleNo);
		if (modulesView == null) {
			throw new ValidationException("not_exist", "module");
		}
		return modulesView;
	}
	
	
	@Override
	@Transactional
	public SinglePage<ModulesView> getModulesViewSinglePage(UsersView loginUser, long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<ModulesView> singlePage = modulesDAO.getModulesViewSinglePage(pageNo);
		return singlePage;
	}
	
	@Override
	@Transactional
	public SinglePage<ModulesView> getModulesViewLastPage(UsersView loginUser) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<ModulesView> singlePage = modulesDAO.getModulesViewLastPage();
		return singlePage;
	}
	
	@Override
	@Transactional
	public Long getModulesViewSinglePageNo(UsersView loginUser, Integer moduleNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Return requested data
		 Long singlePageNo = modulesDAO.getModulesViewSinglePageNo(moduleNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "message");
		}
		return singlePageNo;
	}
	
	@Override
	@Transactional
	public MultiplePages<ModulesView> getModulesViewMultiplePages(UsersView loginUser, long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<ModulesView> multiplePages = modulesDAO.getModulesViewMultiplePages(pageNo);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public MultiplePages<ModulesView> getModulesViewFilteredMultiplePages(UsersView loginUser, long pageNo, ModulesViewFilter ModuleViewFilter) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<ModulesView> multiplePages = modulesDAO.getModulesViewFilteredMultiplePages(pageNo, ModuleViewFilter);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public void updateModules(UsersView loginUser, ModulesView modulesView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(modulesView.getModuleNo(), "module_no");
		coreValidationService.greaterThanZero(modulesView.getModuleNo(), "module_no");
		coreValidationService.notNull(modulesView.getModuleDName(), "name");
		coreValidationService.notBlank(modulesView.getModuleDName(), "name");
		if ((modulesView.getModuleFName() != null) && modulesView.getModuleFName().isBlank()) modulesView.setModuleFName(null);
		coreValidationService.notNull(modulesView.getShortcut(), "shortcut");
		coreValidationService.notBlank(modulesView.getShortcut(), "shortcut");
		coreValidationService.notNull(modulesView.getActive(), "active");
		coreValidationService.notNull(modulesView.getOrderNo(), "order_no");
		coreValidationService.greaterThanZero(modulesView.getOrderNo(), "order_no");
		// Database validation
		Module module = getModuleFromModulesView(modulesView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("module_no", module.getModuleNo());
		if (!generalDAO.isEntityExist("modules", conditions)) throw new ValidationException("not_exist", "module");
		conditions.clear();
		conditions.put("module_d_name", module.getModuleDName());
		String exceptionCondition = null;
		exceptionCondition = " and module_no != " + module.getModuleNo();
		if (generalDAO.isEntityExist("modules", conditions, exceptionCondition)) throw new ValidationException("already_exist", "name");
		if (module.getModuleFName() != null) {
			conditions.clear();
			conditions.put("module_f_name", module.getModuleFName());
			if (generalDAO.isEntityExist("modules", conditions, exceptionCondition)) throw new ValidationException("already_exist", "foreign_name");
		}
		conditions.clear();
		conditions.put("shortcut", module.getShortcut());
		if (generalDAO.isEntityExist("modules", conditions, exceptionCondition)) throw new ValidationException("already_exist", "shortcut");
		// Update the message
		modulesDAO.updateModules(module);
		inMemoryModulesService.updateModulesViewMap();
	}
	
	public Module getModuleFromModulesView(ModulesView modulesView)  {
		Module module = new Module();
		try {
			module.setActive(modulesView.getActive());
			module.setModuleDName(Utils.escapeLiteral(null, modulesView.getModuleDName(), true).toString());
			if (modulesView.getModuleFName() == null)
				module.setModuleFName(modulesView.getModuleFName());
			else
				module.setModuleFName(Utils.escapeLiteral(null, modulesView.getModuleFName(), true).toString());
			module.setModuleNo(modulesView.getModuleNo());
			module.setOrderNo(modulesView.getOrderNo());
			module.setShortcut(Utils.escapeLiteral(null, modulesView.getShortcut(), true).toString());
		} catch (SQLException e) {
			 throw new UnauthorizedException("resource");
		}
		return module;
	}
}
