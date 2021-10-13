package com.expertsvision.erp.masterdata.accpara.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.masterdata.accpara.dao.AccParaDAO;
import com.expertsvision.erp.masterdata.accpara.entity.AccPara;

@Service
public class AccParaServiceImpl implements AccParaService {

	@Autowired
	private AccParaDAO accParaDAO;

//	@Autowired
//	private GeneralDAO generalDAO;
//
//	@Autowired
//	private CoreValidationService coreValidationService;


//	@Override
//	@Transactional
//	public List<AccPara> getAccParaList(UsersView loginUsersView) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//		}
//		// Return requested data
//		List<AccPara> accPara = accountsGroupDAO.getAllAccParaList();
//		return accPara;
//	}

	// Used by the system
	@Override
	public AccPara getAccPara() {
		AccPara accPara = accParaDAO.getAccPara();
		if (accPara == null) {
			throw new ValidationException("not_exist", "acc_para");
		}
		return accPara;
	}
	
	
//	@Override
//	@Transactional
//	public AccPara getAccPara(UsersView loginUsersView) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//		}
//		// Return requested data
//		AccPara accPara = accountsGroupDAO.getAccPara(accountsGroupNo);
//		if (accPara == null) {
//			throw new ValidationException("not_exist", "group_no");
//		}
//		return accPara;
//	}
//
//	@Override
//	@Transactional
//	public SinglePage<AccPara> getAccParaSinglePage(UsersView loginUsersView, long pageNo) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//		}
//		// Return requested data
//		SinglePage<AccPara> singlePage = accountsGroupDAO.getAccParaSinglePage(pageNo);
//		return singlePage;
//	}
//
//	@Override
//	@Transactional
//	public SinglePage<AccPara> getAccParaLastPage(UsersView loginUsersView) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//		}
//		// Return requested data
//		SinglePage<AccPara> singlePage = accountsGroupDAO.getAccParaLastPage();
//		return singlePage;
//	}
//
//	@Override
//	@Transactional
//	public Long getAccParaSinglePageNo(UsersView loginUsersView, Integer accountsGroupNo) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//		}
//		// Return requested data
//		Long singlePageNo = accountsGroupDAO.getUserViewSinglePageNo(accountsGroupNo);
//		if (singlePageNo == null) {
//			throw new ValidationException("not_exist", "group_no");
//		}
//		return singlePageNo;
//	}
//
//	@Override
//	@Transactional
//	public MultiplePages<AccPara> getAccParaMultiplePages(UsersView loginUsersView, long pageNo) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//		}
//		// Return requested data
//		MultiplePages<AccPara> multiplePages = accountsGroupDAO.getAccParaMultiplePages(pageNo);
//		return multiplePages;
//	}
//
//	@Override
//	@Transactional
//	public MultiplePages<AccPara> getAccParaFilteredMultiplePages(UsersView loginUsersView, long pageNo,
//			AccParaFilter accParaFilter) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//		}
//		// Return requested data
//		MultiplePages<AccPara> multiplePages = accountsGroupDAO.getAccParaFilteredMultiplePages(pageNo,
//				accParaFilter);
//		return multiplePages;
//	}
//
//	@Override
//	@Transactional
//	public void addAccountsGroup(UsersView loginUsersView, AccPara accPara) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.ADD);
//		}
//		// Non-database validation
//		coreValidationService.notNull(accPara.getGroupNo(), "group_no");
//		coreValidationService.greaterThanOrEqualZero(accPara.getGroupNo(), "group_no");
//		coreValidationService.notNull(accPara.getGroupDName(), "name");
//		coreValidationService.notBlank(accPara.getGroupDName(), "name");
//		if ((accPara.getGroupFName() != null) && accPara.getGroupFName().isBlank())
//			accPara.setGroupFName(null);
//		// Database validation
//		AccountsGroup accountsGroup = getAccountsGroupFromAccPara(accPara);
//		Map<String, Object> conditions = new HashMap<>();
//		conditions.put("group_no", accountsGroup.getGroupNo());
//		if (generalDAO.isEntityExist("accounts_group", conditions))
//			throw new ValidationException("already_exist", "group_no");
//		conditions.clear();
//		conditions.put("group_d_name", accountsGroup.getGroupDName());
//		if (generalDAO.isEntityExist("accounts_group", conditions))
//			throw new ValidationException("already_exist", "name");
//		conditions.clear();
//		conditions.put("group_f_name", accountsGroup.getGroupFName());
//		if (accountsGroup.getGroupFName() != null && generalDAO.isEntityExist("accounts_group", conditions))
//			throw new ValidationException("already_exist", "foreign_name");
//		conditions.clear();		
//		// Add the accountsGroup
//		Timestamp add_date = new Timestamp(new Date().getTime());
//		accountsGroup.setAddDate(add_date);
//		accountsGroup.setAddUser(loginUsersView.getUserId());
//		accountsGroup.setModifyDate(null);
//		accountsGroup.setModifyUser(null);
//		accountsGroupDAO.addAccountsGroup(accountsGroup);
//	}
//
//	@Override
//	@Transactional
//	public void updateAccountsGroup(UsersView loginUsersView, AccPara accPara) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.MODIFY);
//		}
//		// Non-database validation
//		coreValidationService.notNull(accPara.getGroupNo(), "group_no");
//		coreValidationService.greaterThanOrEqualZero(accPara.getGroupNo(), "group_no");
//		coreValidationService.notNull(accPara.getGroupDName(), "name");
//		coreValidationService.notBlank(accPara.getGroupDName(), "name");
//		if ((accPara.getGroupFName() != null) && accPara.getGroupFName().isBlank())
//			accPara.setGroupFName(null);
//		// Database validation
//		AccountsGroup accountsGroup = getAccountsGroupFromAccPara(accPara);
//		Map<String, Object> conditions = new HashMap<>();
//		conditions.put("group_no", accountsGroup.getGroupNo());
//		if (!generalDAO.isEntityExist("accounts_group", conditions))
//			throw new ValidationException("not_exist", "group_no");
//		conditions.clear();
//		conditions.put("group_d_name", accountsGroup.getGroupDName());
//		String exceptionCondition = null;
//		exceptionCondition = " and group_no != " + accountsGroup.getGroupNo();
//		if (generalDAO.isEntityExist("accounts_group", conditions, exceptionCondition))
//			throw new ValidationException("already_exist", "name");
//		conditions.clear();
//		conditions.put("group_f_name", accountsGroup.getGroupFName());
//		if (accountsGroup.getGroupFName() != null && generalDAO.isEntityExist("accounts_group", conditions, exceptionCondition))
//			throw new ValidationException("already_exist", "foreign_name");
//		conditions.clear();
//		// Update the user
//		Timestamp update_date = new Timestamp(new Date().getTime());
//		accountsGroup.setModifyDate(update_date);
//		accountsGroup.setModifyUser(loginUsersView.getUserId());
//		accountsGroupDAO.updateAccountsGroup(accountsGroup);
//	}
//
//	@Override
//	@Transactional
//	public void deleteAccountsGroup(UsersView loginUsersView, Integer accountsGroupNo) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.DELETE);
//		}
//		// Non-database validation
//		coreValidationService.notNull(accountsGroupNo, "group_no");
//		coreValidationService.greaterThanOrEqualZero(accountsGroupNo, "group_no");
//		// Database validation
//		Map<String, Object> conditions = new HashMap<>();
//		conditions.put("group_no", accountsGroupNo);
//		if (!generalDAO.isEntityExist("accounts_group", conditions))
//			throw new ValidationException("not_exist", "group_no");
//		// delete the usersaccountsGroup
//		try {
//			accountsGroupDAO.deleteAccountsGroup(accountsGroupNo);
//		} catch (Exception e) {
//			throw new ValidationException("used_somewhere", "group_no");
//		}
//	}
//	
//	@Override
//	@Transactional
//	public PreData preAdd(UsersView loginUsersView) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.MODIFY);
//		}
//		Set<String> readOnly = new HashSet<>();
//		Map<String, Object> defaultValues = new HashMap<>();
//		Map<String, Object> info = new HashMap<>();
//		PreData preData = new PreData(readOnly, defaultValues, info);
//		// Fill preData object
//		defaultValues.put("nextPK", accountsGroupDAO.getNextPK());
//		// return the data
//		return preData;
//	}
//	
//	@Override
//	@Transactional
//	public PreData preModify(UsersView loginUsersView) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
//				coreValidationService.activeFlagDetail(FlagDetails.ACCOUNTS_GROUP);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.VIEW);
//			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ACCOUNTS_GROUP, FlagsActions.MODIFY);
//		}
//		Set<String> readOnly = new HashSet<>();
//		Map<String, Object> defaultValues = new HashMap<>();
//		Map<String, Object> info = new HashMap<>();
//		PreData preData = new PreData(readOnly, defaultValues, info);
//		// Fill preData object
//		defaultValues.put("nextPK", accountsGroupDAO.getNextPK());
//		// return the data
//		return preData;
//	}
//
//	public AccountsGroup getAccountsGroupFromAccPara(AccPara accPara) {
//		AccountsGroup accountsGroup = new AccountsGroup();
//		try {
//			accountsGroup.setAddDate(accPara.getAddDate());
//			accountsGroup.setAddUser(accPara.getAddUser());
//			accountsGroup.setGroupDName(Utils.escapeLiteral(null, accPara.getGroupDName(), true).toString());
//			accountsGroup.setGroupFName(accPara.getGroupFName());
//			if (accPara.getGroupFName() == null)
//				accountsGroup.setGroupFName(accPara.getGroupFName());
//			else
//				accountsGroup.setGroupFName(Utils.escapeLiteral(null, accPara.getGroupFName(), true).toString());
//			accountsGroup.setGroupNo(accPara.getGroupNo());
//			accountsGroup.setModifyDate(accPara.getModifyDate());
//			accountsGroup.setModifyUser(accPara.getModifyUser());
//		} catch (SQLException e) {
//			throw new UnauthorizedException("resource");
//		}
//		return accountsGroup;
//	}

}
