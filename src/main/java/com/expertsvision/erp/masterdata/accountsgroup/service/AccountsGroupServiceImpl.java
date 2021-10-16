package com.expertsvision.erp.masterdata.accountsgroup.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.FlagDetails;
import com.expertsvision.erp.core.utils.FlagsActions;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.accountsgroup.dao.AccountsGroupDAO;
import com.expertsvision.erp.masterdata.accountsgroup.dto.AccountsGroupViewFilter;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroup;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroupView;

@Service
public class AccountsGroupServiceImpl implements AccountsGroupService {

	@Autowired
	private AccountsGroupDAO accountsGroupDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<AccountsGroupView> getAccountsGroupViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		List<AccountsGroupView> accountsGroupView = accountsGroupDAO.getAllAccountsGroupViewList();
		return accountsGroupView;
	}

	@Override
	@Transactional
	public AccountsGroupView getAccountsGroupView(UsersView loginUsersView, Integer accountsGroupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		AccountsGroupView accountsGroupView = accountsGroupDAO.getAccountsGroupView(accountsGroupNo);
		if (accountsGroupView == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return accountsGroupView;
	}

	@Override
	@Transactional
	public SinglePage<AccountsGroupView> getAccountsGroupViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<AccountsGroupView> singlePage = accountsGroupDAO.getAccountsGroupViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<AccountsGroupView> getAccountsGroupViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<AccountsGroupView> singlePage = accountsGroupDAO.getAccountsGroupViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getAccountsGroupViewSinglePageNo(UsersView loginUsersView, Integer accountsGroupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = accountsGroupDAO.getUserViewSinglePageNo(accountsGroupNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<AccountsGroupView> getAccountsGroupViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<AccountsGroupView> multiplePages = accountsGroupDAO.getAccountsGroupViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<AccountsGroupView> getAccountsGroupViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			AccountsGroupViewFilter accountsGroupViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<AccountsGroupView> multiplePages = accountsGroupDAO.getAccountsGroupViewFilteredMultiplePages(pageNo,
				accountsGroupViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addAccountsGroup(UsersView loginUsersView, AccountsGroupView accountsGroupView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(accountsGroupView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(accountsGroupView.getGroupNo(), "group_no");
		coreValidationService.notNull(accountsGroupView.getGroupDName(), "name");
		coreValidationService.notBlank(accountsGroupView.getGroupDName(), "name");
		if ((accountsGroupView.getGroupFName() != null) && accountsGroupView.getGroupFName().isBlank())
			accountsGroupView.setGroupFName(null);
		// Database validation
		AccountsGroup accountsGroup = getAccountsGroupFromAccountsGroupView(accountsGroupView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", accountsGroup.getGroupNo());
		if (generalDAO.isEntityExist("accounts_group", conditions))
			throw new ValidationException("already_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", accountsGroup.getGroupDName());
		if (generalDAO.isEntityExist("accounts_group", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", accountsGroup.getGroupFName());
		if (accountsGroup.getGroupFName() != null && generalDAO.isEntityExist("accounts_group", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();		
		// Add the accountsGroup
		Timestamp add_date = new Timestamp(new Date().getTime());
		accountsGroup.setAddDate(add_date);
		accountsGroup.setAddUser(loginUsersView.getUserId());
		accountsGroup.setModifyDate(null);
		accountsGroup.setModifyUser(null);
		accountsGroupDAO.addAccountsGroup(accountsGroup);
	}

	@Override
	@Transactional
	public void updateAccountsGroup(UsersView loginUsersView, AccountsGroupView accountsGroupView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(accountsGroupView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(accountsGroupView.getGroupNo(), "group_no");
		coreValidationService.notNull(accountsGroupView.getGroupDName(), "name");
		coreValidationService.notBlank(accountsGroupView.getGroupDName(), "name");
		if ((accountsGroupView.getGroupFName() != null) && accountsGroupView.getGroupFName().isBlank())
			accountsGroupView.setGroupFName(null);
		// Database validation
		AccountsGroup accountsGroup = getAccountsGroupFromAccountsGroupView(accountsGroupView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", accountsGroup.getGroupNo());
		if (!generalDAO.isEntityExist("accounts_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", accountsGroup.getGroupDName());
		String exceptionCondition = null;
		exceptionCondition = " and group_no != " + accountsGroup.getGroupNo();
		if (generalDAO.isEntityExist("accounts_group", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", accountsGroup.getGroupFName());
		if (accountsGroup.getGroupFName() != null && generalDAO.isEntityExist("accounts_group", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		accountsGroup.setModifyDate(update_date);
		accountsGroup.setModifyUser(loginUsersView.getUserId());
		accountsGroupDAO.updateAccountsGroup(accountsGroup);
	}

	@Override
	@Transactional
	public void deleteAccountsGroup(UsersView loginUsersView, Integer accountsGroupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(accountsGroupNo, "group_no");
		coreValidationService.greaterThanOrEqualZero(accountsGroupNo, "group_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", accountsGroupNo);
		if (!generalDAO.isEntityExist("accounts_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		// delete the usersaccountsGroup
		try {
			accountsGroupDAO.deleteAccountsGroup(accountsGroupNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "group_no");
		}
	}
	
	@Override
	@Transactional
	public PreData preAdd(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.MODIFY);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		defaultValues.put("group_no", accountsGroupDAO.getNextPK());
		// return the data
		return preData;
	}
	
	@Override
	@Transactional
	public PreData preModify(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.MODIFY);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// No preData
		// return the data
		return preData;
	}

	public AccountsGroup getAccountsGroupFromAccountsGroupView(AccountsGroupView accountsGroupView) {
		AccountsGroup accountsGroup = new AccountsGroup();
		try {
			accountsGroup.setAddDate(accountsGroupView.getAddDate());
			accountsGroup.setAddUser(accountsGroupView.getAddUser());
			accountsGroup.setGroupDName(Utils.escapeLiteral(null, accountsGroupView.getGroupDName(), true).toString());
			accountsGroup.setGroupFName(accountsGroupView.getGroupFName());
			if (accountsGroupView.getGroupFName() == null)
				accountsGroup.setGroupFName(accountsGroupView.getGroupFName());
			else
				accountsGroup.setGroupFName(Utils.escapeLiteral(null, accountsGroupView.getGroupFName(), true).toString());
			accountsGroup.setGroupNo(accountsGroupView.getGroupNo());
			accountsGroup.setModifyDate(accountsGroupView.getModifyDate());
			accountsGroup.setModifyUser(accountsGroupView.getModifyUser());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return accountsGroup;
	}

}
