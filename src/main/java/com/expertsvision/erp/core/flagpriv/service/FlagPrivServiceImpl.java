package com.expertsvision.erp.core.flagpriv.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailPK;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.flagdetail.service.FlagDetailService;
import com.expertsvision.erp.core.flagdetail.service.InMemoryFlagDetailService;
import com.expertsvision.erp.core.flagpriv.dao.FlagPrivDAO;
import com.expertsvision.erp.core.flagpriv.entity.FlagPriv;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivPK;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivView;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.form.service.FormsService;
import com.expertsvision.erp.core.form.service.InMemoryFormsService;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.user.service.UsersService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class FlagPrivServiceImpl implements FlagPrivService {

	@Autowired
	private FlagPrivDAO flagPrivViewDAO;

	@Autowired
	private FlagDetailService flagDetailService;

	@Autowired
	private UsersService usersService;
	
	@Autowired
	private FormsService formsService;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	@Lazy
	private InMemoryFlagPrivService inMemoryFlagPrivService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Autowired
	@Lazy
	private InMemoryFormsService inMemoryFormsService;

	@Autowired
	@Lazy
	private InMemoryFlagDetailService inMemoryFlagDetailService;

	// Donot forget to update cache when add, upd, del

//	@Autowired
//	private CoreValidationService coreValidationService;

	@Override
	@Transactional
	public List<FlagPrivView> getFlagPrivViewList() {
		List<FlagPrivView> flagPrivViewList = null;
		try {
			flagPrivViewList = flagPrivViewDAO.getFlagPrivViewList();
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return flagPrivViewList;
	}

	@Override
	@Transactional
	public List<FlagPrivView> getFlagPrivViewList(UsersView loginUser, Integer userId) {
		if (!usersService.isUserSubordinate(loginUser, userId))
			throw new UnauthorizedException("user");
		List<FlagPrivView> flagPrivViewList = null;
		UsersView userIdUsersView = inMemoryUsersService.getUsersView(userId);
		if (userIdUsersView.getSuperAdmin() || userIdUsersView.getAdminUser()) {
			List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
			flagPrivViewList = new ArrayList<>();
			FlagPrivView newFlagPrivView;
			for (FlagDetailView FlagDetailView : flagDetailViewList) {
				newFlagPrivView = new FlagPrivView();
//				newFlagPrivView.setAddDate(null);
				newFlagPrivView.setAddPriv(true);
//				newFlagPrivView.setAddUser(null);
//				newFlagPrivView.setAddUserDName(null);
//				newFlagPrivView.setAddUserFName(null);
				newFlagPrivView.setDeletePriv(true);
				newFlagPrivView.setFlagCode(FlagDetailView.getFlagCode());
				newFlagPrivView.setFlagValue(FlagDetailView.getFlagValue());		
//				newFlagPrivView.setModifyDate(null);
				newFlagPrivView.setModifyPriv(true);
//				newFlagPrivView.setModifyUser(null);
//				newFlagPrivView.setModifyUserDName(null);
//				newFlagPrivView.setModifyUserFName(null);
				newFlagPrivView.setPrintPriv(true);
//				newFlagPrivView.setUserDName(null);
//				newFlagPrivView.setUserFName(null);
				newFlagPrivView.setUserId(userId);
				newFlagPrivView.setViewPriv(true);
				flagPrivViewList.add(newFlagPrivView);
			}
		} else {
			try {
				flagPrivViewList = flagPrivViewDAO.getFlagPrivViewList(userId);
			} catch (Exception e) {
				throw new UnauthorizedException("resource");
			}
		}
		return flagPrivViewList;
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
	public void generateFlagPrivsForUser(UsersView loginUser, Integer userId, Timestamp addDate) {
		addDate = (addDate != null) ? addDate : new Timestamp(new Date().getTime());
		// Get all flagDetails
		List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
		// Generating the privileges for the user for each screen
		List<FlagPriv> prvsList = new ArrayList<>();
		FlagPriv prv = null;
		for (FlagDetailView flagDetail : flagDetailViewList) {
			if (flagDetail.getActive()) {
				prv = new FlagPriv();
				prv.setAddDate(addDate);
				prv.setAddPriv(false);
				prv.setAddUser(loginUser.getUserId());
				prv.setDeletePriv(false);
				prv.setModifyDate(null);
				prv.setModifyPriv(false);
				prv.setModifyUser(null);
				prv.setPrintPriv(false);
				prv.setViewPriv(false);
				prv.setUserId(userId);
				prv.setFlagCode(flagDetail.getFlagCode());
				prv.setFlagValue(flagDetail.getFlagValue());
				prvsList.add(prv);
			}
		}
		flagPrivViewDAO.addBulkFlagPriv(prvsList);
		inMemoryFlagPrivService.updateFlagPrivView();
	}

	@Override
	public void generateFlagPrivsForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId,
			Timestamp addDate) {
		addDate = (addDate != null) ? addDate : new Timestamp(new Date().getTime());
		List<FlagPriv> toUserPrvsList = new ArrayList<>();
		FlagPriv prv = null;
		// If the fromUserId is superadmin or admin user
		UsersView fromUsersView = inMemoryUsersService.getUsersView(fromUserId);
		if (fromUsersView.getSuperAdmin() || fromUsersView.getAdminUser()) {
			// Get all forms
			List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
			// Generating the privileges for the user for each screen
			for (FlagDetailView flagDetail : flagDetailViewList) {
				if (flagDetail.getActive()) {
					prv = new FlagPriv();
					prv.setAddDate(addDate);
					prv.setAddPriv(true);
					prv.setAddUser(loginUser.getUserId());
					prv.setDeletePriv(true);
					prv.setModifyDate(null);
					prv.setModifyPriv(true);
					prv.setModifyUser(null);
					prv.setPrintPriv(true);
					prv.setUserId(toUserId);
					prv.setViewPriv(true);
					prv.setFlagCode(flagDetail.getFlagCode());
					prv.setFlagValue(flagDetail.getFlagValue());
					toUserPrvsList.add(prv);
				}
			}
		} else {
			// Get the fromUser prvs
			List<FlagPrivView> fromUserPrvsList = flagPrivViewDAO.getFlagPrivViewList(fromUserId);
			// Generating the privileges for the toUser for each fromUser prv
			for (FlagPrivView prvView : fromUserPrvsList) {
				prv = new FlagPriv();
				prv.setAddDate(addDate);
				prv.setAddPriv(prvView.getAddPriv());
				prv.setAddUser(loginUser.getUserId());
				prv.setDeletePriv(prvView.getDeletePriv());
				prv.setModifyDate(null);
				prv.setModifyPriv(prvView.getModifyPriv());
				prv.setModifyUser(null);
				prv.setPrintPriv(prvView.getPrintPriv());
				prv.setUserId(toUserId);
				prv.setViewPriv(prvView.getViewPriv());
				prv.setFlagCode(prvView.getFlagCode());
				prv.setFlagValue(prvView.getFlagValue());
				toUserPrvsList.add(prv);
			}
		}
		flagPrivViewDAO.addBulkFlagPriv(toUserPrvsList);
		inMemoryFlagPrivService.updateFlagPrivView();
	}

	@Override
	public void updateFlagPrivsForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId,
			Timestamp modifyDate) {
		modifyDate = (modifyDate != null) ? modifyDate : new Timestamp(new Date().getTime());
		List<FlagPriv> toUserPrvsList = new ArrayList<>();
		FlagPriv prv = null;
		// If the fromUserId is superadmin or admin
		UsersView fromUsersView = inMemoryUsersService.getUsersView(fromUserId);
		if (fromUsersView.getSuperAdmin() || fromUsersView.getAdminUser()) {
			// Get all flagDetails
			List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
			// Generating the privileges for the user for each screen
			for (FlagDetailView flagDetail : flagDetailViewList) {
				if (flagDetail.getActive()) {
					prv = new FlagPriv();
					prv.setAddPriv(true);
					prv.setDeletePriv(true);
					prv.setModifyDate(modifyDate);
					prv.setModifyPriv(true);
					prv.setModifyUser(loginUser.getUserId());
					prv.setPrintPriv(true);
					prv.setUserId(toUserId);
					prv.setViewPriv(true);
					prv.setFlagCode(flagDetail.getFlagCode());
					prv.setFlagValue(flagDetail.getFlagValue());
					toUserPrvsList.add(prv);
				}
			}
		} else {
			// Get the fromUser prvs
			List<FlagPrivView> fromUserPrvsList = flagPrivViewDAO.getFlagPrivViewList(fromUserId);
			// Updating the privileges for the toUser for each fromUser prv
			for (FlagPrivView prvView : fromUserPrvsList) {
				prv = getFlagPrivFromFlagPrivView(prvView);
				prv.setModifyDate(modifyDate);
				prv.setModifyUser(loginUser.getUserId());
				prv.setUserId(toUserId);
				toUserPrvsList.add(prv);
			}
		}
		flagPrivViewDAO.updateBulkFlagPriv(toUserPrvsList);
		inMemoryFlagPrivService.updateFlagPrivView();
	}

	@Override
	public void updateGroupUsersFlagPrivs(UsersView loginUser, Integer fromUserId, Integer groupNo,
			Timestamp modifyDate) {
		List<User> usersList = usersService.getUsersListByGroupNo(groupNo);
		if (usersList.isEmpty())
			return;
		for (User user : usersList) {
			updateFlagPrivsForUserFromAnotherUser(loginUser, fromUserId, user.getUserId(), modifyDate);
		}
	}

	@Override
	@Transactional
	public void updateFlagPriv(UsersView loginUser, FlagPrivView flagPrivView) {
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
		coreValidationService.notNull(flagPrivView.getUserId(), "user_no");
		coreValidationService.greaterThanOrEqualZero(flagPrivView.getUserId(), "user_no");
		coreValidationService.notNull(flagPrivView.getFlagCode(), "flag_code");
		coreValidationService.notBlank(flagPrivView.getFlagCode(), "flag_code");
		coreValidationService.notNull(flagPrivView.getFlagValue(), "flag_value");
		coreValidationService.greaterThanZero(flagPrivView.getFlagValue(), "flag_value");
		coreValidationService.notNull(flagPrivView.getAddPriv(), "add_prv");
		coreValidationService.notNull(flagPrivView.getModifyPriv(), "modify_prv");
		coreValidationService.notNull(flagPrivView.getViewPriv(), "view_prv");
		coreValidationService.notNull(flagPrivView.getPrintPriv(), "print_prv");
		coreValidationService.notNull(flagPrivView.getDeletePriv(), "delete_prv");
		UsersView flagPrivViewUserView = inMemoryUsersService.getUsersView(flagPrivView.getUserId());
		if (flagPrivViewUserView.getSuperAdmin() || flagPrivViewUserView.getAdminUser())
			throw new UnauthorizedException("user");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("user_id", flagPrivView.getUserId());
		if (!generalDAO.isEntityExist("users", conditions))
			throw new ValidationException("not_exist", "user_no");
		conditions.clear();
		conditions.put("flag_code", flagPrivView.getFlagCode());
		conditions.put("flag_value", flagPrivView.getFlagValue());
		if (!generalDAO.isEntityExist("flag_detail", conditions))
			throw new ValidationException("not_exist", "flag_value");
		conditions.clear();
		FlagPriv newFlagPriv = getFlagPrivFromFlagPrivView(flagPrivView);
		FlagPriv oldFlagPriv = flagPrivViewDAO
				.getFlagPriv(new FlagPrivPK(newFlagPriv.getUserId(), newFlagPriv.getFlagCode(), newFlagPriv.getFlagValue()));
		FlagPriv loginFlagPriv = flagPrivViewDAO
				.getFlagPriv(new FlagPrivPK(loginUser.getUserId(), newFlagPriv.getFlagCode(), newFlagPriv.getFlagValue()));
		if (!loginUser.getAdminUser() && !loginUser.getSuperAdmin()) {
			if (!newFlagPriv.getAddPriv().equals(oldFlagPriv.getAddPriv())) {
				if (!loginFlagPriv.getAddPriv())
					throw new UnauthorizedException("flag_value");
			}
			if (!newFlagPriv.getModifyPriv().equals(oldFlagPriv.getModifyPriv())) {
				if (!loginFlagPriv.getModifyPriv())
					throw new UnauthorizedException("flag_value");
			}
			if (!newFlagPriv.getDeletePriv().equals(oldFlagPriv.getDeletePriv())) {
				if (!loginFlagPriv.getDeletePriv())
					throw new UnauthorizedException("flag_value");
			}
			if (!newFlagPriv.getViewPriv().equals(oldFlagPriv.getViewPriv())) {
				if (!loginFlagPriv.getViewPriv())
					throw new UnauthorizedException("flag_value");
			}
			if (!newFlagPriv.getPrintPriv().equals(oldFlagPriv.getPrintPriv())) {
				if (!loginFlagPriv.getPrintPriv())
					throw new UnauthorizedException("flag_value");
			}
		}
		if (!(loginUser.getSuperAdmin() || loginUser.getAdminUser())) {
			if (!inMemoryFlagPrivService.getFlagPrivView(new FlagPrivPK(loginUser.getUserId(), flagPrivView.getFlagCode(), flagPrivView.getFlagValue())).getViewPriv())
				throw new UnauthorizedException("flag_value");
		}
		// Continue non-database validation
		// getFormView
		Set<Entry<Integer, FormsView>> formsViewSet = inMemoryFormsService.ggetFormsViewMap().entrySet();
		for (Entry<Integer, FormsView> entry : formsViewSet) {
			if (entry.getValue().getFlagCode()!=null && entry.getValue().getFlagCode().equals(flagPrivView.getFlagCode())) {
				if (!formsService.IsFormsViewInMainTree(loginUser.getUserId(), entry.getValue().getFormNo()))
					throw new UnauthorizedException("form");
				break;
			}
		}
		// getFlagDetailView
		Set<Entry<FlagDetailPK, FlagDetailView>> flagDetailsSet = inMemoryFlagDetailService.getFlagDetailViewMap().entrySet();
		for (Entry<FlagDetailPK, FlagDetailView> entry : flagDetailsSet) {
			if (entry.getValue().getFlagCode()!=null && entry.getValue().getFlagCode().equals(flagPrivView.getFlagCode())) {
				if (!entry.getValue().getActive())
					throw new UnauthorizedException("flag_value");
				break;
			}
		}
		if (loginUser.getUserId().equals(flagPrivView.getUserId()))
			throw new UnauthorizedException("user");
		
		// Update the privilege
		Timestamp updateDate = new Timestamp(new Date().getTime());
		newFlagPriv.setModifyDate(updateDate);
		newFlagPriv.setModifyUser(loginUser.getUserId());
		flagPrivViewDAO.updateFlagPriv(newFlagPriv);
		inMemoryFlagPrivService.updateFlagPrivView();
	}

	@Override
	public void deleteBulkFlagPriv(Integer userId) {
		flagPrivViewDAO.deleteBulkFlagPriv(userId);
		inMemoryFlagPrivService.updateFlagPrivView();
	}

	public FlagPriv getFlagPrivFromFlagPrivView(FlagPrivView prvView) {
		FlagPriv prv = new FlagPriv();
		prv.setAddDate(prvView.getAddDate());
		prv.setAddPriv(prvView.getAddPriv());
		prv.setAddUser(prvView.getAddUser());
		prv.setDeletePriv(prvView.getDeletePriv());
		prv.setFlagCode(prvView.getFlagCode());
		prv.setFlagValue(prvView.getFlagValue());
		prv.setModifyDate(prvView.getModifyDate());
		prv.setModifyPriv(prvView.getModifyPriv());
		prv.setModifyUser(prvView.getModifyUser());
		prv.setPrintPriv(prvView.getPrintPriv());
		prv.setUserId(prvView.getUserId());
		prv.setViewPriv(prvView.getViewPriv());
		return prv;
	}
}
