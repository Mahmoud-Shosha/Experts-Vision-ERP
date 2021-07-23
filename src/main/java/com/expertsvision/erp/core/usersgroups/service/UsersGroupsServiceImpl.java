package com.expertsvision.erp.core.usersgroups.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
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
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.usersgroups.dao.UsersGroupsDAO;
import com.expertsvision.erp.core.usersgroups.dto.UsersGroupsViewFilter;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroup;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroupsView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class UsersGroupsServiceImpl implements UsersGroupsService {

	@Autowired
	private UsersGroupsDAO usersGroupsDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	@Lazy
	private InMemoryUsersGroupsService inMemoryUsersGroupsService;

	@Override
	@Transactional
	public List<UsersGroupsView> getAllUsersGroupsViewList() {
		// Only used by system functions, like inmemory DB
		List<UsersGroupsView> usersGroupsView = usersGroupsDAO.getAllUsersGroupsViewList();
		return usersGroupsView;
	}

	@Override
	@Transactional
	public List<UsersGroupsView> getUsersGroupsViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
		}
		// Return requested data
		List<UsersGroupsView> usersGroupsView = usersGroupsDAO.getAllUsersGroupsViewList();
		return usersGroupsView;
	}

	@Override
	@Transactional
	public UsersGroupsView getUsersGroupsView(UsersView loginUsersView, Integer groupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
		}
		// Return requested data
		UsersGroupsView usersGroupsView = usersGroupsDAO.getUsersGroupsView(groupNo);
		if (usersGroupsView == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return usersGroupsView;
	}

	@Override
	@Transactional
	public SinglePage<UsersGroupsView> getUsersGroupsViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
		}
		// Return requested data
		SinglePage<UsersGroupsView> singlePage = usersGroupsDAO.getUsersGroupsViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<UsersGroupsView> getUsersGroupsViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
		}
		// Return requested data
		SinglePage<UsersGroupsView> singlePage = usersGroupsDAO.getUsersGroupsViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getUsersGroupsViewSinglePageNo(UsersView loginUsersView, Integer groupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = usersGroupsDAO.getUserViewSinglePageNo(groupNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<UsersGroupsView> getUsersGroupsViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<UsersGroupsView> multiplePages = usersGroupsDAO.getUsersGroupsViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<UsersGroupsView> getUsersGroupsViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			UsersGroupsViewFilter usersGroupsViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<UsersGroupsView> multiplePages = usersGroupsDAO.getUsersGroupsViewFilteredMultiplePages(pageNo,
				usersGroupsViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addUsersGroups(UsersView loginUsersView, UsersGroupsView usersGroupsView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(usersGroupsView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(usersGroupsView.getGroupNo(), "group_no");
		coreValidationService.notNull(usersGroupsView.getGroupDName(), "name");
		coreValidationService.notBlank(usersGroupsView.getGroupDName(), "name");
		if ((usersGroupsView.getGroupFName() != null) && usersGroupsView.getGroupFName().isBlank())
			usersGroupsView.setGroupFName(null);
		coreValidationService.notNull(usersGroupsView.getAdminGroup(), "admin_group");
		// Database validation
		UsersGroup usersGroup = getUsersGroupFromUsersGroupsView(usersGroupsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", usersGroup.getGroupNo());
		if (generalDAO.isEntityExist("users_groups", conditions))
			throw new ValidationException("already_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", usersGroup.getGroupDName());
		if (generalDAO.isEntityExist("users_groups", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", usersGroup.getGroupFName());
		if (usersGroup.getGroupFName() != null && generalDAO.isEntityExist("users_groups", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		// Add the user
		Timestamp add_date = new Timestamp(new Date().getTime());
		usersGroup.setAddDate(add_date);
		usersGroup.setAddUser(loginUsersView.getUserId());
		usersGroup.setModifyDate(null);
		usersGroup.setModifyUser(null);
		usersGroupsDAO.addUsersGroup(usersGroup);
		inMemoryUsersGroupsService.updateUsersGroupsView();
	}

	@Override
	@Transactional
	public void updateUsersGroups(UsersView loginUsersView, UsersGroupsView usersGroupsView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(usersGroupsView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(usersGroupsView.getGroupNo(), "group_no");
		coreValidationService.notNull(usersGroupsView.getGroupDName(), "name");
		coreValidationService.notBlank(usersGroupsView.getGroupDName(), "name");
		if ((usersGroupsView.getGroupFName() != null) && usersGroupsView.getGroupFName().isBlank())
			usersGroupsView.setGroupFName(null);
		coreValidationService.notNull(usersGroupsView.getAdminGroup(), "admin_group");
		// Database validation
		UsersGroup usersGroup = getUsersGroupFromUsersGroupsView(usersGroupsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", usersGroup.getGroupNo());
		if (!generalDAO.isEntityExist("users_groups", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", usersGroup.getGroupDName());
		String exceptionCondition = null;
		exceptionCondition = " and group_no != " + usersGroup.getGroupNo();
		if (generalDAO.isEntityExist("users_groups", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", usersGroup.getGroupFName());
		if (usersGroup.getGroupFName() != null
				&& generalDAO.isEntityExist("users_groups", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		usersGroup.setModifyDate(update_date);
		usersGroup.setModifyUser(loginUsersView.getUserId());
		usersGroupsDAO.updateUsersGroup(usersGroup);
		inMemoryUsersGroupsService.updateUsersGroupsView();
	}

	@Override
	@Transactional
	public void deleteUsersGroups(UsersView loginUsersView, Integer groupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.USERS_GROUPS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.USERS_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.USERS_GROUPS, FormsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(groupNo, "group_no");
		coreValidationService.greaterThanOrEqualZero(groupNo, "group_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", groupNo);
		if (!generalDAO.isEntityExist("users_groups", conditions))
			throw new ValidationException("not_exist", "group_no");
		// delete the usersgroup
		try {
			usersGroupsDAO.deleteUsersGroup(groupNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "group");
		}
		inMemoryUsersGroupsService.updateUsersGroupsView();
	}

	public UsersGroup getUsersGroupFromUsersGroupsView(UsersGroupsView usersGroupsView) {
		UsersGroup usersGroup = new UsersGroup();
		try {
			usersGroup.setAddDate(usersGroupsView.getAddDate());
			usersGroup.setAddUser(usersGroupsView.getAddUser());
			usersGroup.setAdminGroup(usersGroupsView.getAdminGroup());
			usersGroup.setGroupDName(Utils.escapeLiteral(null, usersGroupsView.getGroupDName(), true).toString());
			if (usersGroupsView.getGroupFName() == null)
				usersGroup.setGroupFName(usersGroupsView.getGroupFName());
			else
				usersGroup.setGroupFName(Utils.escapeLiteral(null, usersGroupsView.getGroupFName(), true).toString());
			usersGroup.setGroupNo(usersGroupsView.getGroupNo());
			usersGroup.setModifyDate(usersGroupsView.getModifyDate());
			usersGroup.setModifyUser(usersGroupsView.getModifyUser());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return usersGroup;
	}

}
