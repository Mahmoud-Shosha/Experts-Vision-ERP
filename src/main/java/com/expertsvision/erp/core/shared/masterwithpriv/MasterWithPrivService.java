package com.expertsvision.erp.core.shared.masterwithpriv;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.validation.CoreValidationService;

public class MasterWithPrivService {

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	@Lazy
	private InMemoryUsersGroupsService inMemoryUsersGroupsService;

	protected Forms form;

	public void checkScreenViewPriv() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(form);
			} else {
				coreValidationService.activeModuleAndForm(form);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.VIEW);
		}
	}

	public void checkScreenAddPriv() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(form);
			} else {
				coreValidationService.activeModuleAndForm(form);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.ADD);
		}
	}

	public void checkScreenModifyPriv() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(form);
			} else {
				coreValidationService.activeModuleAndForm(form);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.MODIFY);
		}
	}

	public void checkScreenDeletePriv() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(form);
			} else {
				coreValidationService.activeModuleAndForm(form);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, form, FormsActions.DELETE);
		}
	}

	public void checkMasterEntityViewExist(Object object, String notExistMessageLabel) {
		if (object == null) {
			throw new ValidationException("not_exist", notExistMessageLabel);
		}
	}

	public String getNullIfBlank(String string) {
		if ((string != null) && string.isBlank())
			return null;
		else
			return string;
	}

	public String escapeMandatoryString(String string) {
		try {
			return Utils.escapeLiteral(null, string, true).toString();
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
	}

	public String escapeOptionalString(String string) {
		try {
			if (string == null)
				return null;
			else
				return Utils.escapeLiteral(null, string, true).toString();
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
	}

	public void checkNotExist(String tableName, String columnName, Object columnValue,
			String alreadyExistMessageLabel) {
		if (columnValue != null) {
			Map<String, Object> conditions = new HashMap<>();
			conditions.put(columnName, columnValue);
			if (generalDAO.isEntityExist(tableName, conditions))
				throw new ValidationException("already_exist", alreadyExistMessageLabel);
		}
	}

	public void checkNotExist(String tableName, String columnName, Object columnValue, String alreadyExistMessageLabel,
			String where) {
		if (columnValue != null) {
			Map<String, Object> conditions = new HashMap<>();
			conditions.put(columnName, columnValue);
			if (generalDAO.isEntityExist(tableName, conditions, where))
				throw new ValidationException("already_exist", alreadyExistMessageLabel);
		}
	}

	public void checkExist(String tableName, String columnName, Object columnValue, String notExistMessageLabel) {
		if (columnValue != null) {
			Map<String, Object> conditions = new HashMap<>();
			conditions.put(columnName, columnValue);
			if (!generalDAO.isEntityExist(tableName, conditions))
				throw new ValidationException("not_exist", notExistMessageLabel);
		}
	}

	public boolean getViewPriv(UsersView usersView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Boolean viewPriv;
		if (usersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
			viewPriv = true;
		} else {
			viewPriv = false;
		}
		if (loginUser.getUserId().equals(usersView.getUserId())) {
			viewPriv = true;
		}
		return viewPriv;
	}

	public boolean getAddPriv(UsersView usersView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Boolean addPriv;
		if (usersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
			addPriv = true;
		} else {
			addPriv = false;
		}
		if (loginUser.getUserId().equals(usersView.getUserId())) {
			addPriv = true;
		}
		return addPriv;
	}

}
