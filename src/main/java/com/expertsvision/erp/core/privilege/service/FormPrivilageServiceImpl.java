package com.expertsvision.erp.core.privilege.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.form.service.FormsService;
import com.expertsvision.erp.core.privilege.dao.FormPrivilageDAO;
import com.expertsvision.erp.core.privilege.entity.FormPrivilage;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.user.service.UsersService;

@Service
public class FormPrivilageServiceImpl implements FormPrivilageService {
	
	@Autowired
	private FormPrivilageDAO formPrivilageViewDAO;
	
	@Autowired
	private FormsService formsService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	@Lazy
	private InMemoryFormPrivilageService inMemoryFormPrivilageService;
	
	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;
	
	
	
	// Donot forget to update cache when add, upd, del
	
//	@Autowired
//	private CoreValidationService coreValidationService;

	
	@Override
	@Transactional
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
	
	@Override
	public void generateFormPrivilegesForUser(UsersView loginUser, Integer userId, Timestamp addDate) {
		addDate = (addDate!=null)? addDate : new Timestamp(new Date().getTime());
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
	public void generateFormPrivilegesForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId, Timestamp addDate) {
		addDate = (addDate!=null)? addDate : new Timestamp(new Date().getTime());
		List<FormPrivilage> toUserPrvsList = new ArrayList<>();
		FormPrivilage prv = null;
		// If the fromUserId is 
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
	public void updateFormPrivilegesForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId, Timestamp modifyDate) {
		modifyDate = (modifyDate!=null)? modifyDate : new Timestamp(new Date().getTime());
		List<FormPrivilage> toUserPrvsList = new ArrayList<>();
		FormPrivilage prv = null;
		// If the fromUserId is 
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
	public void updateGroupUsersPrivileges(UsersView loginUser, Integer fromUserId, Integer groupNo, Timestamp modifyDate) {
		List<User> usersList = usersService.getUsersListByGroupNo(groupNo);
		if (usersList.isEmpty()) return;
		for (User user : usersList) {
			updateFormPrivilegesForUserFromAnotherUser(loginUser, fromUserId, user.getUserId(), modifyDate);
		}
	}
	
	public FormPrivilage getFormPrivilageFromFormPrivilageView(FormPrivilageView prvView)  {
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
	
	@Override
	public void deleteBulkFormPrivilage(Integer userId) {
		formPrivilageViewDAO.deleteBulkFormPrivilage(userId);
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
	}
}
