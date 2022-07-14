package com.expertsvision.erp.masterdata.cash.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.DetailValidationException;
import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.branches.entity.BranchesView;
import com.expertsvision.erp.masterdata.branches.service.BranchesService;
import com.expertsvision.erp.masterdata.cash.dao.CashDAO;
import com.expertsvision.erp.masterdata.cash.dto.CashInHandViewFilter;
import com.expertsvision.erp.masterdata.cash.entity.CashInHand;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandDtl;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandDtlView;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandPriv;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandView;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyView;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;
import com.expertsvision.erp.masterdata.chartofaccounts.service.ChartofaccountsService;

@Service
public class CashServiceImpl implements CashService {

	@Autowired
	private ChartofaccountsService chartofaccountsService;
	
	@Autowired
	private BranchesService branchesService;
	
	@Autowired
	private CashDAO cashDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Autowired
	@Lazy
	private InMemoryUsersGroupsService inMemoryUsersGroupsService;

	@Override
	@Transactional
	public List<CashInHandView> getCashInHandViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		List<CashInHandView> cashInHandView = cashDAO.getAllCashInHandViewList(loginUsersView);
		return cashInHandView;
	}

	@Override
	@Transactional
	public CashInHandView getCashInHandView(UsersView loginUsersView, Integer cashInHandNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		CashInHandView cashInHandView = cashDAO.getCashInHandView(loginUsersView, cashInHandNo);
		if (cashInHandView == null) {
			throw new ValidationException("not_exist", "cash_no");
		}
		cashInHandView.setCashDtlList(cashDAO.getCashInHandDtlViewList(cashInHandNo));
		return cashInHandView;
	}

	@Override
	@Transactional
	public SinglePage<CashInHandView> getCashInHandViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<CashInHandView> singlePage = cashDAO.getCashInHandViewSinglePage(loginUsersView, pageNo);
		if (singlePage.getPage() != null) {
			singlePage.getPage().setCashDtlList(cashDAO.getCashInHandDtlViewList(singlePage.getPage().getCashNo()));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CashInHandView> getCashInHandViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<CashInHandView> singlePage = cashDAO.getCashInHandViewLastPage(loginUsersView);
		if (singlePage.getPage() != null) {
			singlePage.getPage().setCashDtlList(cashDAO.getCashInHandDtlViewList(singlePage.getPage().getCashNo()));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCashInHandViewSinglePageNo(UsersView loginUsersView, Integer cashInHandNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		Long singlePageNo = cashDAO.getCashInHandViewSinglePageNo(loginUsersView, cashInHandNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "cash_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CashInHandView> getCashInHandViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<CashInHandView> multiplePages = cashDAO.getCashInHandViewMultiplePages(loginUsersView, pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CashInHandView> getCashInHandViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CashInHandViewFilter cashInHandViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<CashInHandView> multiplePages = cashDAO.getCashInHandViewFilteredMultiplePages(loginUsersView, pageNo, cashInHandViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public PreData preAdd(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.ADD);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		defaultValues.put("cash_no", cashDAO.getNextPK());
		// return the data
		return preData;
	}

	@Override
	@Transactional
	public void addCashInHand(UsersView loginUsersView, CashInHandView cashInHandView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.ADD);
		}
		Timestamp add_date = new Timestamp(new Date().getTime());
		List<CashInHandDtl> cashInHandDtlForAddList = new ArrayList<>();
		// Non-database validation
		coreValidationService.notNull(cashInHandView.getCashNo(), "cash_no");
		coreValidationService.greaterThanOrEqualZero(cashInHandView.getCashNo(), "cash_no");
		coreValidationService.notNull(cashInHandView.getBranchNo(), "branch_no");
		coreValidationService.greaterThanOrEqualZero(cashInHandView.getBranchNo(), "branch_no");
		coreValidationService.notNull(cashInHandView.getCashDName(), "name");
		coreValidationService.notBlank(cashInHandView.getCashDName(), "name");
		if ((cashInHandView.getCashFName() != null) && cashInHandView.getCashFName().isBlank())
			cashInHandView.setCashFName(null);
		coreValidationService.notNull(cashInHandView.getAccNo(), "acc_no");
		coreValidationService.greaterThanOrEqualZero(cashInHandView.getAccNo(), "acc_no");
		coreValidationService.notNull(cashInHandView.getInactive(), "inactive");
		coreValidationService.notNull(cashInHandView.getPos(), "pos");
		// Non-database validation for details
		if (cashInHandView.getCashDtlList() == null || cashInHandView.getCashDtlList().isEmpty()) {
			throw new ValidationException("exactly_one_record", "cash_on_hand");
		}
		int count = 0;
		for (CashInHandDtlView obj : cashInHandView.getCashDtlList()) {
			obj.setCashNo(cashInHandView.getCashNo());
			coreValidationService.notNull(obj.getAccCurr(), "currency_code");
			coreValidationService.notBlank(obj.getAccCurr(), "currency_code");
			coreValidationService.notNull(obj.getAccNo(), "acc_no");
			coreValidationService.greaterThanOrEqualZero(obj.getAccNo(), "acc_no");
			coreValidationService.notNull(obj.getInactive(), "inactive");
			if (!obj.getAccNo().equals(cashInHandView.getAccNo()))
				throw new ValidationException("invalid", "acc_no");
			switch (obj.getAction()) {
			case "add":
				count++;
				break;
			default:
				throw new DetailValidationException("invalid_detail", "action", obj.getAction(), "currency_code",
						obj.getAccCurr());
			}
		}
		if (count != 1) {
			throw new ValidationException("exactly_one_record", "cash_on_hand");
		}
		// Database validation
		CashInHand cash = getCashInHandFromCashInHandView(cashInHandView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("cash_no", cash.getCashNo());
		if (generalDAO.isEntityExist("cash_in_hand", conditions))
			throw new ValidationException("already_exist", "cash_no");
		conditions.clear();
		conditions.put("cash_d_name", cash.getCashDName());
		if (generalDAO.isEntityExist("cash_in_hand", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("cash_f_name", cash.getCashFName());
		if (cash.getCashFName() != null && generalDAO.isEntityExist("cash_in_hand", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		ChartOfAccountsView chartOfAccountsView = chartofaccountsService
				.getChartOfAccountsViewWithoutScrPriv(loginUsersView, cash.getAccNo());
		if (chartOfAccountsView == null)
			throw new ValidationException("not_exist", "acc_no");
		if (chartOfAccountsView.getSub() == null || !chartOfAccountsView.getSub()
				|| chartOfAccountsView.getAccType() == null || !chartOfAccountsView.getAccType().equals("2"))
			throw new ValidationException("cash_acc");
		if (chartOfAccountsView.getInactive())
			throw new ValidationException("is_inactive", "acc_no", chartOfAccountsView.getAccNo());
		BranchesView branchesView = branchesService.getBranchesViewWithoutScrPriv(loginUsersView, cash.getBranchNo());
		if (branchesView == null)
			throw new ValidationException("not_exist", "branch_no");
		// Database validation for details
		Set<String> accountCurrencySet = new HashSet<>();
		for (AccountsCurrencyView accountsCurrencyView : chartOfAccountsView.getAccountCurrencyList()) {
			accountCurrencySet.add(accountsCurrencyView.getCurCode());
			if (cashInHandView.getCashDtlList().get(0).getAccCurr().equals(accountsCurrencyView.getCurCode())) {
				if (!accountsCurrencyView.getUsed()) {
					throw new ValidationException("is_inactive", "currency_code", accountsCurrencyView.getUsed());
				}
			}
		}
		Set<String> valuesSetForAdd = new HashSet<>();
		Set<String> DBValuesSetForAdd;
		Map<String, Object> parameters = new HashMap<>();
		CashInHandDtl cashInHandDtl;
		parameters.put("cashNo", cashInHandView.getCashNo());
		for (CashInHandDtlView obj : cashInHandView.getCashDtlList()) {
			cashInHandDtl = getCashInHandDtlFromCashInHandDtlView(obj);
			cashInHandDtl.setAddDate(add_date);
			cashInHandDtl.setAddUser(loginUsersView.getUserId());
			cashInHandDtl.setModifyDate(null);
			cashInHandDtl.setModifyUser(null);
			if (cashInHandDtl.getInactive()) {
				cashInHandDtl.setInactiveUser(loginUsersView.getUserId());
			} else {
				cashInHandDtl.setInactiveUser(null);
				cashInHandDtl.setInactiveReason(null);
			}
			cashInHandDtlForAddList.add(cashInHandDtl);
			if (valuesSetForAdd.contains(cashInHandDtl.getAccCurr())) {
				throw new DetailValidationException("already_exist_detail", "currency", obj.getAccCurr(), "cash_no",
						cashInHandDtl.getCashNo());
			} else {
				valuesSetForAdd.add(cashInHandDtl.getAccCurr());
			}
			if (!accountCurrencySet.contains(cashInHandDtl.getAccCurr()))
				throw new ValidationException("invalid", "currency");
		}

		DBValuesSetForAdd = generalDAO.getThemIfExist("cash_in_hand_dtl", "cash_no = :cashNo", parameters, "acc_curr",
				valuesSetForAdd);
		if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
			throw new DetailValidationException("already_exist_detail", "currency", DBValuesSetForAdd.toArray()[0],
					"cash_no", cashInHandView.getCashNo());
		// Add the cashInHandl
		cash.setAddDate(add_date);
		cash.setAddUser(loginUsersView.getUserId());
		cash.setModifyDate(null);
		cash.setModifyUser(null);
		if (cash.getInactive()) {
			cash.setInactiveUser(loginUsersView.getUserId());
		} else {
			cash.setInactiveUser(null);
			cash.setInactiveReason(null);
		}
		cashDAO.addCashInHand(cash, cashInHandDtlForAddList);
		generateCashInHandPrivsForAllUsers(loginUsersView, cash.getCashNo(), cashInHandDtlForAddList.get(0).getAccCurr(), add_date);
	}

	@Override
	@Transactional
	public void updateCashInHand(UsersView loginUsersView, CashInHandView cashInHandView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.MODIFY);
		}
		Timestamp add_date = new Timestamp(new Date().getTime());
		List<CashInHandDtl> cashInHandDtlForAddList = new ArrayList<>();
		List<CashInHandDtl> cashInHandDtlForUpdateList = new ArrayList<>();
		List<CashInHandDtl> cashInHandDtlForDeleteList = new ArrayList<>();
		// Non-database validation
		coreValidationService.notNull(cashInHandView.getCashNo(), "cash_no");
		coreValidationService.greaterThanOrEqualZero(cashInHandView.getCashNo(), "cash_no");
		coreValidationService.notNull(cashInHandView.getBranchNo(), "branch_no");
		coreValidationService.greaterThanOrEqualZero(cashInHandView.getBranchNo(), "branch_no");
		coreValidationService.notNull(cashInHandView.getCashDName(), "name");
		coreValidationService.notBlank(cashInHandView.getCashDName(), "name");
		if ((cashInHandView.getCashFName() != null) && cashInHandView.getCashFName().isBlank())
			cashInHandView.setCashFName(null);
		coreValidationService.notNull(cashInHandView.getAccNo(), "acc_no");
		coreValidationService.greaterThanOrEqualZero(cashInHandView.getAccNo(), "acc_no");
		coreValidationService.notNull(cashInHandView.getInactive(), "inactive");
		coreValidationService.notNull(cashInHandView.getPos(), "pos");
		// Non-database validation for details
		if (cashInHandView.getCashDtlList() != null) {
			for (CashInHandDtlView obj : cashInHandView.getCashDtlList()) {
				obj.setCashNo(cashInHandView.getCashNo());
				coreValidationService.notNull(obj.getAccCurr(), "currency_code");
				coreValidationService.notBlank(obj.getAccCurr(), "currency_code");
				coreValidationService.notNull(obj.getAccNo(), "acc_no");
				coreValidationService.greaterThanOrEqualZero(obj.getAccNo(), "acc_no");
				coreValidationService.notNull(obj.getInactive(), "inactive");
				if (!obj.getAction().equals("delete") && !obj.getAccNo().equals(cashInHandView.getAccNo()))
					throw new ValidationException("invalid", "acc_no");
				switch (obj.getAction()) {
				case "add":
				case "update":
				case "delete":
					break;
				default:
					throw new DetailValidationException("invalid_detail", "action", obj.getAction(), "currency_code",
							obj.getAccCurr());
				}
			}
		}
		// Database validation
		CashInHand cash = getCashInHandFromCashInHandView(cashInHandView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("cash_no", cash.getCashNo());
		if (!generalDAO.isEntityExist("cash_in_hand", conditions))
			throw new ValidationException("not_exist", "cash_no");
		conditions.clear();
		conditions.put("cash_d_name", cash.getCashDName());
		String exceptionCondition = null;
		exceptionCondition = " and cash_no != " + cash.getCashNo();
		if (generalDAO.isEntityExist("cash_in_hand", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("cash_f_name", cash.getCashFName());
		if (cash.getCashFName() != null && generalDAO.isEntityExist("cash_in_hand", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		ChartOfAccountsView chartOfAccountsView = chartofaccountsService
				.getChartOfAccountsViewWithoutScrPriv(loginUsersView, cash.getAccNo());
		if (chartOfAccountsView == null)
			throw new ValidationException("not_exist", "acc_no");
		if (chartOfAccountsView.getSub() == null || !chartOfAccountsView.getSub()
				|| chartOfAccountsView.getAccType() == null || !chartOfAccountsView.getAccType().equals("2"))
			throw new ValidationException("cash_acc");
		if (chartOfAccountsView.getInactive())
			throw new ValidationException("is_inactive", "acc_no", chartOfAccountsView.getAccNo());
		BranchesView branchesView = branchesService.getBranchesViewWithoutScrPriv(loginUsersView, cash.getBranchNo());
		if (branchesView == null)
			throw new ValidationException("not_exist", "branch_no");
		// Database validation for details
		Set<String> accountCurrencySet = new HashSet<>();
		for (AccountsCurrencyView accountsCurrencyView : chartOfAccountsView.getAccountCurrencyList()) {
			accountCurrencySet.add(accountsCurrencyView.getCurCode());
			if (cashInHandView.getCashDtlList() != null && !cashInHandView.getCashDtlList().isEmpty()) {
				for (CashInHandDtlView cashDtlView : cashInHandView.getCashDtlList()) {
					if (!cashDtlView.getAction().equals("delete")
							&& cashDtlView.getAccCurr().equals(accountsCurrencyView.getCurCode())
							&& !accountsCurrencyView.getUsed()) {
						throw new ValidationException("is_inactive", "currency_code", accountsCurrencyView.getUsed());
					}
				}
			}
		}
		if (chartOfAccountsView.getInactive() && !cash.getInactive()) {
			throw new ValidationException("cash_account_inactive");
		}
		if (cashInHandView.getCashDtlList() != null) {
			Set<String> valuesSetForAdd = new HashSet<>();
			Set<String> DBValuesSetForAdd;
			Set<String> valuesSetForModifyOrDelete = new HashSet<>();
			Set<String> DBValuesSetForModifyOrDelete;
			Map<String, Object> parameters = new HashMap<>();
			CashInHandDtl cashInHandDtl;
			parameters.put("cashNo", cashInHandView.getCashNo());
			parameters.put("accNo", cashInHandView.getAccNo());
			for (CashInHandDtlView obj : cashInHandView.getCashDtlList()) {
				cashInHandDtl = getCashInHandDtlFromCashInHandDtlView(obj);
				switch (obj.getAction()) {
				case "add":
					cashInHandDtl.setAddDate(add_date);
					cashInHandDtl.setAddUser(loginUsersView.getUserId());
					cashInHandDtl.setModifyDate(null);
					cashInHandDtl.setModifyUser(null);
					if (cashInHandDtl.getInactive()) {
						cashInHandDtl.setInactiveUser(loginUsersView.getUserId());
					} else {
						cashInHandDtl.setInactiveUser(null);
						cashInHandDtl.setInactiveReason(null);
					}
					cashInHandDtlForAddList.add(cashInHandDtl);
					if (valuesSetForAdd.contains(obj.getAccCurr())) {
						throw new DetailValidationException("already_exist_detail", "currency", obj.getAccCurr(),
								"cash_no", cashInHandDtl.getCashNo());
					} else {
						valuesSetForAdd.add(cashInHandDtl.getAccCurr());
					}
					if (!accountCurrencySet.contains(cashInHandDtl.getAccCurr()))
						throw new ValidationException("invalid", "currency");
					break;
				case "update":
					cashInHandDtl.setModifyDate(add_date);
					cashInHandDtl.setModifyUser(loginUsersView.getUserId());
					CashInHandDtlView DBCashInHandDtlView = cashDAO.getCashInHandDtlViewList(cash.getCashNo()).get(0);
					if (DBCashInHandDtlView.getInactive() && !cashInHandDtl.getInactive()) {
						cashInHandDtl.setInactiveUser(null);
						cashInHandDtl.setInactiveReason(null);

					} else if (!DBCashInHandDtlView.getInactive() && cashInHandDtl.getInactive()) {
						cashInHandDtl.setInactiveUser(loginUsersView.getUserId());
					} else {
						cashInHandDtl.setInactiveUser(DBCashInHandDtlView.getInactiveUser());
						cashInHandDtl.setInactiveReason(DBCashInHandDtlView.getInactiveReason());
					}
					cashInHandDtlForUpdateList.add(cashInHandDtl);
					if (valuesSetForAdd.contains(obj.getAccCurr()))
						throw new DetailValidationException("already_exist_detail", "currency", obj.getAccCurr(),
								"bank_no", cashInHandDtl.getCashNo());
					valuesSetForModifyOrDelete.add(cashInHandDtl.getAccCurr());
					if (!accountCurrencySet.contains(cashInHandDtl.getAccCurr()))
						throw new ValidationException("invalid", "currency");
					break;
				case "delete":
					cashInHandDtlForDeleteList.add(cashInHandDtl);
					valuesSetForModifyOrDelete.add(cashInHandDtl.getAccCurr());
					break;
				}
			}
			if (!valuesSetForAdd.isEmpty()) {
				DBValuesSetForAdd = generalDAO.getThemIfExist("cash_in_hand_dtl",
						"cash_no = :cashNo and acc_no = :accNo", parameters, "acc_curr", valuesSetForAdd);
				if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
					throw new DetailValidationException("already_exist_detail", "currency",
							DBValuesSetForAdd.toArray()[0], "cash_no", cashInHandView.getCashNo());
			}
			parameters.remove("accNo");
			if (!valuesSetForModifyOrDelete.isEmpty()) {
				DBValuesSetForModifyOrDelete = generalDAO.getThemIfExist("cash_in_hand_dtl", "cash_no = :cashNo",
						parameters, "acc_curr", valuesSetForModifyOrDelete);
				if (DBValuesSetForModifyOrDelete != null) {
					for (String curCode : valuesSetForModifyOrDelete) {
						if (!DBValuesSetForModifyOrDelete.contains(curCode))
							throw new DetailValidationException("not_exist_detail", "currency", curCode, "cash_no",
									cashInHandView.getCashNo());
					}
				}
			}

		}
		// Update the bank
		CashInHandView DBCashInHandView = cashDAO.getCashInHandView(loginUsersView, cash.getCashNo());
		if (DBCashInHandView.getInactive() && !cash.getInactive()) {
			cash.setInactiveUser(null);
			cash.setInactiveReason(null);

		} else if (!DBCashInHandView.getInactive() && cash.getInactive()) {
			cash.setInactiveUser(loginUsersView.getUserId());
		} else {
			cash.setInactiveUser(DBCashInHandView.getInactiveUser());
			cash.setInactiveReason(DBCashInHandView.getInactiveReason());
		}
		cash.setModifyDate(add_date);
		cash.setModifyUser(loginUsersView.getUserId());
		cashDAO.updateCashInHand(cash, cashInHandDtlForAddList, cashInHandDtlForDeleteList, cashInHandDtlForUpdateList);
		// validate exactly one detail
		conditions.clear();
		conditions.put("cash_no", cash.getCashNo());
		if (generalDAO.getCount("cash_in_hand_dtl", conditions, null) != 1)
			throw new ValidationException("exactly_one_record", "cash");
	}

	@Override
	@Transactional
	public void deleteCashInHand(UsersView loginUsersView, Integer cashInHandNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CASH_IN_HAND);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CASH_IN_HAND);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CASH_IN_HAND, FormsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(cashInHandNo, "cash_no");
		coreValidationService.greaterThanOrEqualZero(cashInHandNo, "cash_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("cash_no", cashInHandNo);
		if (!generalDAO.isEntityExist("cash_in_hand", conditions))
			throw new ValidationException("not_exist", "cash_no");
		// delete the bank
		try {
			cashDAO.deleteCashInHand(cashInHandNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "cash_no");
		}
	}

	@Override
	public void generateCashInHandPrivsForAllUsers(UsersView loginUsersView, Integer cashInHandNo, String AccCurr,
			Timestamp currentDate) {
		/*
		 * $$$$$$$$$$$$___Do not forget to add in
		 * MasterDataPrivilegesService___$$$$$$$$$$$$
		 */
		// PREPARE VARAIBLES
		List<CashInHandPriv> cashInHandlPrivList = new ArrayList<>();
		CashInHandPriv cashInHandlPriv;
		boolean viewPriv = true;
		boolean addPriv = true;
		// LOOP OVER ALL USERS
		for (UsersView usersView : inMemoryUsersService.getAllUsersView()) {
			// Do not generate privs for admin and superuser
			if (!(usersView.getSuperAdmin() || usersView.getAdminUser())) {
				if (usersView.getGroupNo() != null
						&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
					viewPriv = true;
					addPriv = true;
				} else {
					viewPriv = false;
					addPriv = false;
				}
				if (loginUsersView != null && loginUsersView.getUserId().equals(usersView.getUserId())) {
					viewPriv = true;
					addPriv = true;
				}
				cashInHandlPriv = new CashInHandPriv();
				cashInHandlPriv.setAccCurr(AccCurr);
				cashInHandlPriv.setCashNo(cashInHandNo);
				cashInHandlPriv.setUserId(usersView.getUserId());
				cashInHandlPriv.setAddDate(currentDate);
				cashInHandlPriv.setAddUser(1);
				cashInHandlPriv.setModifyDate(null);
				cashInHandlPriv.setModifyUser(null);
				cashInHandlPriv.setAddPriv(addPriv);
				cashInHandlPriv.setViewPriv(viewPriv);
				cashInHandlPrivList.add(cashInHandlPriv);
			}
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (CashInHandPriv priv : cashInHandlPrivList) {
			cashDAO.addCashInHandsPriv(priv);
		}
	}

	public CashInHand getCashInHandFromCashInHandView(CashInHandView cashInHandView) {
		CashInHand cash = new CashInHand();
		try {
			cash.setAccNo(cashInHandView.getAccNo());
			cash.setAddDate(cashInHandView.getAddDate());
			cash.setAddUser(cashInHandView.getAddUser());
			cash.setBranchNo(cashInHandView.getBranchNo());
			cash.setCashDName(Utils.escapeLiteral(null, cashInHandView.getCashDName(), true).toString());
			if (cashInHandView.getCashFName() == null)
				cash.setCashFName(cashInHandView.getCashFName());
			else
				cash.setCashFName(Utils.escapeLiteral(null, cashInHandView.getCashFName(), true).toString());
			cash.setCashNo(cashInHandView.getCashNo());
			cash.setInactive(cashInHandView.getInactive());
			if (cashInHandView.getInactiveReason() == null)
				cash.setInactiveReason(cashInHandView.getInactiveReason());
			else
				cash.setInactiveReason(Utils.escapeLiteral(null, cashInHandView.getInactiveReason(), true).toString());
			cash.setInactiveUser(cashInHandView.getInactiveUser());
			cash.setModifyDate(cashInHandView.getModifyDate());
			cash.setModifyUser(cashInHandView.getModifyUser());
			cash.setPos(cashInHandView.getPos());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return cash;
	}

	public CashInHandDtl getCashInHandDtlFromCashInHandDtlView(CashInHandDtlView cashInHandDtlView) {
		CashInHandDtl cashInHandDtl = new CashInHandDtl();
		try {
			cashInHandDtl.setAccCurr(Utils.escapeLiteral(null, cashInHandDtlView.getAccCurr(), true).toString());
			cashInHandDtl.setAccNo(cashInHandDtlView.getAccNo());
			cashInHandDtl.setAddDate(cashInHandDtlView.getAddDate());
			cashInHandDtl.setAddUser(cashInHandDtlView.getAddUser());
			cashInHandDtl.setCashNo(cashInHandDtlView.getCashNo());
			cashInHandDtl.setInactive(cashInHandDtlView.getInactive());
			if (cashInHandDtlView.getInactiveReason() == null)
				cashInHandDtl.setInactiveReason(cashInHandDtlView.getInactiveReason());
			else
				cashInHandDtl.setInactiveReason(
						Utils.escapeLiteral(null, cashInHandDtlView.getInactiveReason(), true).toString());
			cashInHandDtl.setInactiveUser(cashInHandDtlView.getInactiveUser());
			cashInHandDtl.setModifyDate(cashInHandDtlView.getModifyDate());
			cashInHandDtl.setModifyUser(cashInHandDtlView.getModifyUser());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return cashInHandDtl;
	}

}
