package com.expertsvision.erp.core.privilege.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.form.service.FormsService;
import com.expertsvision.erp.core.form.service.InMemoryFormsService;
import com.expertsvision.erp.core.privilege.dao.FormPrivilageDAO;
import com.expertsvision.erp.core.privilege.entity.FormPrivilage;
import com.expertsvision.erp.core.privilege.entity.FormPrivilagePK;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.user.service.UsersService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class FormPrivilageServiceImpl implements FormPrivilageService {

	@Autowired
	private FormPrivilageDAO formPrivilageViewDAO;

	@Autowired
	private FormsService formsService;

	@Autowired
	private UsersService usersService;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	@Lazy
	private InMemoryFormPrivilageService inMemoryFormPrivilageService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Autowired
	@Lazy
	private InMemoryFormsService inMemoryFormsService;

	// Donot forget to update cache when add, upd, del

//	@Autowired
//	private CoreValidationService coreValidationService;

	@Override
	@Transactional
	public List<FormPrivilageView> getFormPrivilageViewList(UsersView loginUser) {
		List<FormPrivilageView> formPrivilageViewList = null;
		try {
			formPrivilageViewList = formPrivilageViewDAO.getFormPrivilageViewList();
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return formPrivilageViewList;
	}
	
	@Override
	@Transactional
	public List<FormPrivilageView> getFormPrivilageViewList(UsersView loginUser, Integer userId) {
		if (!usersService.isUserSubordinate(loginUser, userId))
			throw new UnauthorizedException("user");
		List<FormPrivilageView> formPrivilageViewList = null;
		UsersView userIdUsersView = inMemoryUsersService.getUsersView(userId);
		if (userIdUsersView.getSuperAdmin() || userIdUsersView.getAdminUser()) {
			List<FormsView> formsViewList = formsService.getFormsViewList();
			formPrivilageViewList = new ArrayList<>();
			FormPrivilageView newFormPrivilageView;
			for (FormsView formsView : formsViewList) {
				newFormPrivilageView = new FormPrivilageView();
//				newFormPrivilageView.setAddDate(null);
				newFormPrivilageView.setAddPriv(true);
//				newFormPrivilageView.setAddUser(null);
//				newFormPrivilageView.setAddUserDName(null);
//				newFormPrivilageView.setAddUserFName(null);
				newFormPrivilageView.setAuditPriv(true);
				newFormPrivilageView.setDeletePriv(true);
				newFormPrivilageView.setFormDName(formsView.getFormDName());
				newFormPrivilageView.setFormFName(formsView.getFormFName());
				newFormPrivilageView.setFormNo(formsView.getFormNo());
				newFormPrivilageView.setIncludePriv(true);
//				newFormPrivilageView.setModifyDate(null);
				newFormPrivilageView.setModifyPriv(true);
//				newFormPrivilageView.setModifyUser(null);
//				newFormPrivilageView.setModifyUserDName(null);
//				newFormPrivilageView.setModifyUserFName(null);
				newFormPrivilageView.setPostPriv(true);
				newFormPrivilageView.setPrintPriv(true);
				newFormPrivilageView.setUserId(userId);
				newFormPrivilageView.setViewPriv(true);
				formPrivilageViewList.add(newFormPrivilageView);
			}
		} else {
			try {
				formPrivilageViewList = formPrivilageViewDAO.getFormPrivilageViewList(userId);
			} catch (Exception e) {
				throw new UnauthorizedException("resource");
			}
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

	@Override
	public void generateFormPrivilegesForUser(UsersView loginUser, Integer userId, Timestamp addDate) {
		addDate = (addDate != null) ? addDate : new Timestamp(new Date().getTime());
		// Get all forms
		UsersView superAdmin = new UsersView();
		superAdmin.setSuperAdmin(true);
		List<FormsView> formsViewList = formsService.getFormsViewList(superAdmin);
		// Generating the privileges for the user for each screen
		List<FormPrivilage> prvsList = new ArrayList<>();
		FormPrivilage prv = null;
		for (FormsView form : formsViewList) {
			if (form.getActive()) {
				prv = new FormPrivilage();
				prv.setAddDate(addDate);
				prv.setAddPriv(false);
				prv.setAddUser(loginUser.getUserId());
				prv.setAuditPriv(false);
				prv.setDeletePriv(false);
				prv.setFormNo(form.getFormNo());
				prv.setIncludePriv(false);
				prv.setModifyDate(null);
				prv.setModifyPriv(false);
				prv.setModifyUser(null);
				prv.setPostPriv(false);
				prv.setPrintPriv(false);
				prv.setUserId(userId);
				prv.setViewPriv(false);
				prvsList.add(prv);
			}
		}
		formPrivilageViewDAO.addBulkFormPrivilage(prvsList);
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
	}

	@Override
	public void generateFormPrivilegesForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId,
			Timestamp addDate) {
		addDate = (addDate != null) ? addDate : new Timestamp(new Date().getTime());
		List<FormPrivilage> toUserPrvsList = new ArrayList<>();
		FormPrivilage prv = null;
		// If the fromUserId is superadmin or admin user
		UsersView fromUsersView = inMemoryUsersService.getUsersView(fromUserId);
		if (fromUsersView.getSuperAdmin() || fromUsersView.getAdminUser()) {
			// Get all forms
			UsersView superAdmin = new UsersView();
			superAdmin.setSuperAdmin(true);
			List<FormsView> formsViewList = formsService.getFormsViewList(superAdmin);
			// Generating the privileges for the user for each screen
			for (FormsView form : formsViewList) {
				if (form.getActive()) {
					prv = new FormPrivilage();
					prv.setAddDate(addDate);
					prv.setAddPriv(true);
					prv.setAddUser(loginUser.getUserId());
					prv.setAuditPriv(true);
					prv.setDeletePriv(true);
					prv.setFormNo(form.getFormNo());
					prv.setIncludePriv(true);
					prv.setModifyDate(null);
					prv.setModifyPriv(true);
					prv.setModifyUser(null);
					prv.setPostPriv(true);
					prv.setPrintPriv(true);
					prv.setUserId(toUserId);
					prv.setViewPriv(true);
					toUserPrvsList.add(prv);
				}
			}
		} else {
			// Get the fromUser prvs
			List<FormPrivilageView> fromUserPrvsList = formPrivilageViewDAO.getFormPrivilageViewList(fromUserId);
			// Generating the privileges for the toUser for each fromUser prv
			for (FormPrivilageView prvView : fromUserPrvsList) {
				prv = new FormPrivilage();
				prv.setAddDate(addDate);
				prv.setAddPriv(prvView.getAddPriv());
				prv.setAddUser(loginUser.getUserId());
				prv.setAuditPriv(prvView.getAuditPriv());
				prv.setDeletePriv(prvView.getDeletePriv());
				prv.setFormNo(prvView.getFormNo());
				prv.setIncludePriv(prvView.getIncludePriv());
				prv.setModifyDate(null);
				prv.setModifyPriv(prvView.getModifyPriv());
				prv.setModifyUser(null);
				prv.setPostPriv(prvView.getPostPriv());
				prv.setPrintPriv(prvView.getPrintPriv());
				prv.setUserId(toUserId);
				prv.setViewPriv(prvView.getViewPriv());
				toUserPrvsList.add(prv);
			}
		}
		formPrivilageViewDAO.addBulkFormPrivilage(toUserPrvsList);
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
	}

	@Override
	public void updateFormPrivilegesForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId,
			Timestamp modifyDate) {
		modifyDate = (modifyDate != null) ? modifyDate : new Timestamp(new Date().getTime());
		List<FormPrivilage> toUserPrvsList = new ArrayList<>();
		FormPrivilage prv = null;
		// If the fromUserId is superadmin or admin
		UsersView fromUsersView = inMemoryUsersService.getUsersView(fromUserId);
		if (fromUsersView.getSuperAdmin() || fromUsersView.getAdminUser()) {
			// Get all forms
			UsersView superAdmin = new UsersView();
			superAdmin.setSuperAdmin(true);
			List<FormsView> formsViewList = formsService.getFormsViewList(superAdmin);
			// Generating the privileges for the user for each screen
			for (FormsView form : formsViewList) {
				if (form.getActive()) {
					prv = new FormPrivilage();
					prv.setAddPriv(true);
					prv.setAuditPriv(true);
					prv.setDeletePriv(true);
					prv.setFormNo(form.getFormNo());
					prv.setIncludePriv(true);
					prv.setModifyDate(modifyDate);
					prv.setModifyPriv(true);
					prv.setModifyUser(loginUser.getUserId());
					prv.setPostPriv(true);
					prv.setPrintPriv(true);
					prv.setUserId(toUserId);
					prv.setViewPriv(true);
					toUserPrvsList.add(prv);
				}
			}
		} else {
			// Get the fromUser prvs
			List<FormPrivilageView> fromUserPrvsList = formPrivilageViewDAO.getFormPrivilageViewList(fromUserId);
			// Generating the privileges for the toUser for each fromUser prv
			for (FormPrivilageView prvView : fromUserPrvsList) {
				prv = getFormPrivilageFromFormPrivilageView(prvView);
				prv.setModifyDate(modifyDate);
				prv.setModifyUser(loginUser.getUserId());
				prv.setUserId(toUserId);
				toUserPrvsList.add(prv);
			}
		}
		formPrivilageViewDAO.updateBulkFormPrivilage(toUserPrvsList);
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
	}

	@Override
	public void updateGroupUsersPrivileges(UsersView loginUser, Integer fromUserId, Integer groupNo,
			Timestamp modifyDate) {
		List<User> usersList = usersService.getUsersListByGroupNo(groupNo);
		if (usersList.isEmpty())
			return;
		for (User user : usersList) {
			updateFormPrivilegesForUserFromAnotherUser(loginUser, fromUserId, user.getUserId(), modifyDate);
		}
	}

	@Override
	@Transactional
	public void updateFormPrivilage(UsersView loginUser, FormPrivilageView formPrivilageView) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.FORM_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FORM_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.FORM_PRIVILEGES, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.FORM_PRIVILEGES, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.FORM_PRIVILEGES, FormsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(formPrivilageView.getUserId(), "user_no");
		coreValidationService.greaterThanOrEqualZero(formPrivilageView.getUserId(), "user_no");
		coreValidationService.notNull(formPrivilageView.getFormNo(), "form_no");
		coreValidationService.greaterThanZero(formPrivilageView.getFormNo(), "form_no");
		coreValidationService.notNull(formPrivilageView.getAddPriv(), "add_prv");
		coreValidationService.notNull(formPrivilageView.getAuditPriv(), "audit_prv");
		coreValidationService.notNull(formPrivilageView.getDeletePriv(), "delete_prv");
		coreValidationService.notNull(formPrivilageView.getIncludePriv(), "include_prv");
		coreValidationService.notNull(formPrivilageView.getModifyPriv(), "modify_prv");
		coreValidationService.notNull(formPrivilageView.getPostPriv(), "post_prv");
		coreValidationService.notNull(formPrivilageView.getPrintPriv(), "print_prv");
		coreValidationService.notNull(formPrivilageView.getViewPriv(), "view_prv");
		UsersView formPrivilageViewUserView = inMemoryUsersService.getUsersView(formPrivilageView.getUserId());
		if (formPrivilageViewUserView.getSuperAdmin() || formPrivilageViewUserView.getAdminUser())
			throw new UnauthorizedException("user");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("user_id", formPrivilageView.getUserId());
		if (!generalDAO.isEntityExist("users", conditions))
			throw new ValidationException("not_exist", "user_no");
		conditions.clear();
		conditions.put("form_no", formPrivilageView.getFormNo());
		if (!generalDAO.isEntityExist("forms", conditions))
			throw new ValidationException("not_exist", "form_no");
		conditions.clear();
		FormPrivilage newFormPrivilage = getFormPrivilageFromFormPrivilageView(formPrivilageView);
		FormPrivilage oldFormPrivilage = formPrivilageViewDAO
				.getFormPrivilage(new FormPrivilagePK(newFormPrivilage.getUserId(), newFormPrivilage.getFormNo()));
		FormPrivilage loginFormPrivilaeg = formPrivilageViewDAO
				.getFormPrivilage(new FormPrivilagePK(loginUser.getUserId(), newFormPrivilage.getFormNo()));
		if (!loginUser.getAdminUser() && !loginUser.getSuperAdmin()) {
			if (!newFormPrivilage.getIncludePriv().equals(oldFormPrivilage.getIncludePriv())) {
				if (!loginFormPrivilaeg.getIncludePriv())
					throw new UnauthorizedException("form");
			}
			if (!newFormPrivilage.getAddPriv().equals(oldFormPrivilage.getAddPriv())) {
				if (!loginFormPrivilaeg.getAddPriv())
					throw new UnauthorizedException("form");
			}
			if (!newFormPrivilage.getModifyPriv().equals(oldFormPrivilage.getModifyPriv())) {
				if (!loginFormPrivilaeg.getModifyPriv())
					throw new UnauthorizedException("form");
			}
			if (!newFormPrivilage.getDeletePriv().equals(oldFormPrivilage.getDeletePriv())) {
				if (!loginFormPrivilaeg.getDeletePriv())
					throw new UnauthorizedException("form");
			}
			if (!newFormPrivilage.getViewPriv().equals(oldFormPrivilage.getViewPriv())) {
				if (!loginFormPrivilaeg.getViewPriv())
					throw new UnauthorizedException("form");
			}
			if (!newFormPrivilage.getPrintPriv().equals(oldFormPrivilage.getPrintPriv())) {
				if (!loginFormPrivilaeg.getPrintPriv())
					throw new UnauthorizedException("form");
			}
			if (!newFormPrivilage.getAuditPriv().equals(oldFormPrivilage.getAuditPriv())) {
				if (!loginFormPrivilaeg.getAuditPriv())
					throw new UnauthorizedException("form");
			}
			if (!newFormPrivilage.getPostPriv().equals(oldFormPrivilage.getPostPriv())) {
				if (!loginFormPrivilaeg.getPostPriv())
					throw new UnauthorizedException("form");
			}
		}
		if (!usersService.isUserSubordinate(loginUser, newFormPrivilage.getUserId()))
			throw new UnauthorizedException("user");
		// Continue non-database validation
		if (!formsService.IsFormsViewInMainTree(loginUser.getUserId(), formPrivilageView.getFormNo()))
			throw new UnauthorizedException("form");
		if (loginUser.getUserId().equals(formPrivilageView.getUserId()))
			throw new UnauthorizedException("user");
		
		// Update the privilege
		Timestamp updateDate = new Timestamp(new Date().getTime());
		newFormPrivilage.setModifyDate(updateDate);
		newFormPrivilage.setModifyUser(loginUser.getUserId());
		FormsView FormsView =  inMemoryFormsService.getFormsView(newFormPrivilage.getFormNo());
		if ((FormsView.getMain()) || (FormsView.getFlagCode() != null)) {
			oldFormPrivilage.setIncludePriv(newFormPrivilage.getIncludePriv());
			newFormPrivilage = oldFormPrivilage;
		}
		formPrivilageViewDAO.updateFormPrivilage(newFormPrivilage);
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
	}

	@Override
	public void deleteBulkFormPrivilage(Integer userId) {
		formPrivilageViewDAO.deleteBulkFormPrivilage(userId);
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
	}

	public FormPrivilage getFormPrivilageFromFormPrivilageView(FormPrivilageView prvView) {
		FormPrivilage prv = new FormPrivilage();
		prv.setAddDate(prvView.getAddDate());
		prv.setAddPriv(prvView.getAddPriv());
		prv.setAddUser(prvView.getAddUser());
		prv.setAuditPriv(prvView.getAuditPriv());
		prv.setDeletePriv(prvView.getDeletePriv());
		prv.setFormNo(prvView.getFormNo());
		prv.setIncludePriv(prvView.getIncludePriv());
		prv.setModifyDate(prvView.getModifyDate());
		prv.setModifyPriv(prvView.getModifyPriv());
		prv.setModifyUser(prvView.getModifyUser());
		prv.setPostPriv(prvView.getPostPriv());
		prv.setPrintPriv(prvView.getPrintPriv());
		prv.setUserId(prvView.getUserId());
		prv.setViewPriv(prvView.getViewPriv());
		return prv;
	}
}
