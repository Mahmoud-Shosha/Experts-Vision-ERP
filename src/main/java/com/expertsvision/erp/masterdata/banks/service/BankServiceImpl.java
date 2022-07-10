package com.expertsvision.erp.masterdata.banks.service;

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
import com.expertsvision.erp.masterdata.banks.dao.BankDAO;
import com.expertsvision.erp.masterdata.banks.dto.BanksViewFilter;
import com.expertsvision.erp.masterdata.banks.entity.Bank;
import com.expertsvision.erp.masterdata.banks.entity.BanksDtl;
import com.expertsvision.erp.masterdata.banks.entity.BanksDtlView;
import com.expertsvision.erp.masterdata.banks.entity.BanksPriv;
import com.expertsvision.erp.masterdata.banks.entity.BanksView;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyView;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;
import com.expertsvision.erp.masterdata.chartofaccounts.service.ChartofaccountsService;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private ChartofaccountsService chartofaccountsService;

	@Autowired
	private BankDAO bankDAO;

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
	public List<BanksView> getBanksViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		List<BanksView> banksView = bankDAO.getAllBankViewList(loginUsersView);
		return banksView;
	}

	@Override
	@Transactional
	public BanksView getBanksView(UsersView loginUsersView, Integer bankNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		BanksView banksView = bankDAO.getBankView(loginUsersView, bankNo);
		if (banksView == null) {
			throw new ValidationException("not_exist", "bank_no");
		}
		banksView.setBankDtlList(bankDAO.getBanksDtlViewList(bankNo));
		return banksView;
	}

	@Override
	@Transactional
	public SinglePage<BanksView> getBanksViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<BanksView> singlePage = bankDAO.getBankViewSinglePage(loginUsersView, pageNo);
		if (singlePage.getPage() != null) {
			singlePage.getPage().setBankDtlList(bankDAO.getBanksDtlViewList(singlePage.getPage().getBankNo()));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<BanksView> getBanksViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<BanksView> singlePage = bankDAO.getBankViewLastPage(loginUsersView);
		if (singlePage.getPage() != null) {
			singlePage.getPage().setBankDtlList(bankDAO.getBanksDtlViewList(singlePage.getPage().getBankNo()));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public Long getBanksViewSinglePageNo(UsersView loginUsersView, Integer bankNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		Long singlePageNo = bankDAO.getBankViewSinglePageNo(loginUsersView, bankNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "bank_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<BanksView> getBanksViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<BanksView> multiplePages = bankDAO.getBankViewMultiplePages(loginUsersView, pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<BanksView> getBanksViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			BanksViewFilter banksViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<BanksView> multiplePages = bankDAO.getBankViewFilteredMultiplePages(loginUsersView, pageNo,
				banksViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public PreData preAdd(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.ADD);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		defaultValues.put("bank_no", bankDAO.getNextPK());
		// return the data
		return preData;
	}

	@Override
	@Transactional
	public void addBank(UsersView loginUsersView, BanksView banksView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.ADD);
		}
		Timestamp add_date = new Timestamp(new Date().getTime());
		List<BanksDtl> bankDtlForAddList = new ArrayList<>();
		// Non-database validation
		coreValidationService.notNull(banksView.getBankNo(), "bank_no");
		coreValidationService.greaterThanOrEqualZero(banksView.getBankNo(), "bank_no");
		coreValidationService.notNull(banksView.getBankDName(), "name");
		coreValidationService.notBlank(banksView.getBankDName(), "name");
		if ((banksView.getAccFName() != null) && banksView.getAccFName().isBlank())
			banksView.setAccFName(null);
		coreValidationService.notNull(banksView.getAccountNo(), "acc_no");
		coreValidationService.greaterThanOrEqualZero(banksView.getAccountNo(), "acc_no");
		coreValidationService.notNull(banksView.getInactive(), "inactive");
		// Non-database validation for details
		if (banksView.getBankDtlList() == null || banksView.getBankDtlList().isEmpty()) {
			throw new ValidationException("exactly_one_record", "bank");
		}
		int count = 0;
		for (BanksDtlView obj : banksView.getBankDtlList()) {
			obj.setBankNo(banksView.getBankNo());
			coreValidationService.notNull(obj.getAccCurr(), "currency_code");
			coreValidationService.notBlank(obj.getAccCurr(), "currency_code");
			coreValidationService.notNull(obj.getAccNo(), "acc_no");
			coreValidationService.greaterThanOrEqualZero(obj.getAccNo(), "acc_no");
			coreValidationService.notNull(obj.getInactive(), "inactive");
			if (!obj.getAccNo().equals(banksView.getAccountNo()))
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
			throw new ValidationException("exactly_one_record", "bank");
		}
		// Database validation
		Bank bank = getBankFromBanksView(banksView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("bank_no", bank.getBankNo());
		if (generalDAO.isEntityExist("banks", conditions))
			throw new ValidationException("already_exist", "bank_no");
		conditions.clear();
		conditions.put("bank_d_name", bank.getBankDName());
		if (generalDAO.isEntityExist("banks", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("bank_f_name", bank.getBankFName());
		if (bank.getBankFName() != null && generalDAO.isEntityExist("banks", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		ChartOfAccountsView chartOfAccountsView = chartofaccountsService
				.getChartOfAccountsViewWithoutScrPriv(loginUsersView, bank.getAccountNo());
		if (chartOfAccountsView == null)
			throw new ValidationException("not_exist", "acc_no");
		if (chartOfAccountsView.getSub() == null || !chartOfAccountsView.getSub() || chartOfAccountsView.getAccType() == null || !chartOfAccountsView.getAccType().equals("3"))
			throw new ValidationException("bnk_acc");
		if (chartOfAccountsView.getInactive())
			throw new ValidationException("is_inactive", "acc_no", chartOfAccountsView.getAccNo());
		// Database validation for details
		Set<String> accountCurrencySet = new HashSet<>();
		for (AccountsCurrencyView accountsCurrencyView : chartOfAccountsView.getAccountCurrencyList()) {
			accountCurrencySet.add(accountsCurrencyView.getCurCode());
			if (banksView.getBankDtlList().get(0).getAccCurr().equals(accountsCurrencyView.getCurCode())) {
				if (!accountsCurrencyView.getUsed()) {
					throw new ValidationException("is_inactive", "currency_code", accountsCurrencyView.getUsed());
				}
			}
		}
		Set<String> valuesSetForAdd = new HashSet<>();
		Set<String> DBValuesSetForAdd;
		Map<String, Object> parameters = new HashMap<>();
		BanksDtl banksDtl;
		parameters.put("bankNo", banksView.getBankNo());
		for (BanksDtlView obj : banksView.getBankDtlList()) {
			banksDtl = getBanksDtlFromBanksDtlView(obj);
			banksDtl.setAddDate(add_date);
			banksDtl.setAddUser(loginUsersView.getUserId());
			banksDtl.setModifyDate(null);
			banksDtl.setModifyUser(null);
			if (banksDtl.getInactive()) {
				banksDtl.setInactiveUser(loginUsersView.getUserId());
			} else {
				banksDtl.setInactiveUser(null);
				banksDtl.setInactiveReason(null);
			}
			bankDtlForAddList.add(banksDtl);
			if (valuesSetForAdd.contains(banksDtl.getAccCurr())) {
				throw new DetailValidationException("already_exist_detail", "currency", obj.getAccCurr(), "bank_no",
						banksDtl.getBankNo());
			} else {
				valuesSetForAdd.add(banksDtl.getAccCurr());
			}
			if (!accountCurrencySet.contains(banksDtl.getAccCurr()))
				throw new ValidationException("invalid", "currency");
		}

		DBValuesSetForAdd = generalDAO.getThemIfExist("banks_dtl", "bank_no = :bankNo", parameters, "acc_curr", valuesSetForAdd);
		if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
			throw new DetailValidationException("already_exist_detail", "currency", DBValuesSetForAdd.toArray()[0],
					"bank_no", banksView.getBankNo());
		// Add the banks
		bank.setAddDate(add_date);
		bank.setAddUser(loginUsersView.getUserId());
		bank.setModifyDate(null);
		bank.setModifyUser(null);
		if (bank.getInactive()) {
			bank.setInactiveUser(loginUsersView.getUserId());
		} else {
			bank.setInactiveUser(null);
			bank.setInactiveReason(null);
		}
		bankDAO.addBank(bank, bankDtlForAddList);
		generateBankPrivsForAllUsers(loginUsersView, bank.getBankNo(), bankDtlForAddList.get(0).getAccCurr(), add_date);
	}

	@Override
	@Transactional
	public void updateBank(UsersView loginUsersView, BanksView banksView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.MODIFY);
		}
		Timestamp add_date = new Timestamp(new Date().getTime());
		List<BanksDtl> bankDtlForAddList = new ArrayList<>();
		List<BanksDtl> bankDtlForUpdateList = new ArrayList<>();
		List<BanksDtl> bankDtlForDeleteList = new ArrayList<>();
		// Non-database validation
		coreValidationService.notNull(banksView.getBankNo(), "bank_no");
		coreValidationService.greaterThanOrEqualZero(banksView.getBankNo(), "bank_no");
		coreValidationService.notNull(banksView.getBankDName(), "name");
		coreValidationService.notBlank(banksView.getBankDName(), "name");
		if ((banksView.getAccFName() != null) && banksView.getAccFName().isBlank())
			banksView.setAccFName(null);
		coreValidationService.notNull(banksView.getAccountNo(), "acc_no");
		coreValidationService.greaterThanOrEqualZero(banksView.getAccountNo(), "acc_no");
		coreValidationService.notNull(banksView.getInactive(), "inactive");
		// Non-database validation for details
		if (banksView.getBankDtlList() != null) {
			for (BanksDtlView obj : banksView.getBankDtlList()) {
				obj.setBankNo(banksView.getBankNo());
				coreValidationService.notNull(obj.getAccCurr(), "currency_code");
				coreValidationService.notBlank(obj.getAccCurr(), "currency_code");
				coreValidationService.notNull(obj.getAccNo(), "acc_no");
				coreValidationService.greaterThanOrEqualZero(obj.getAccNo(), "acc_no");
				coreValidationService.notNull(obj.getInactive(), "inactive");
				if (!obj.getAction().equals("delete") && !obj.getAccNo().equals(banksView.getAccountNo()))
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
		Bank bank = getBankFromBanksView(banksView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("bank_no", bank.getBankNo());
		if (!generalDAO.isEntityExist("banks", conditions))
			throw new ValidationException("not_exist", "bank_no");
		conditions.clear();
		conditions.put("bank_d_name", bank.getBankDName());
		String exceptionCondition = null;
		exceptionCondition = " and bank_no != " + bank.getBankNo();
		if (generalDAO.isEntityExist("banks", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("bank_f_name", bank.getBankFName());
		if (bank.getBankFName() != null && generalDAO.isEntityExist("banks", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		ChartOfAccountsView chartOfAccountsView = chartofaccountsService
				.getChartOfAccountsViewWithoutScrPriv(loginUsersView, bank.getAccountNo());
		if (chartOfAccountsView == null)
			throw new ValidationException("not_exist", "acc_no");
		if (chartOfAccountsView.getSub() == null || !chartOfAccountsView.getSub() ||
				chartOfAccountsView.getAccType() == null|| !chartOfAccountsView.getAccType().equals("3"))
			throw new ValidationException("bnk_acc");
		if (chartOfAccountsView.getInactive())
			throw new ValidationException("is_inactive", "acc_no", chartOfAccountsView.getAccNo());
		// Database validation for details
		Set<String> accountCurrencySet = new HashSet<>();
		for (AccountsCurrencyView accountsCurrencyView : chartOfAccountsView.getAccountCurrencyList()) {
			accountCurrencySet.add(accountsCurrencyView.getCurCode());
			if (banksView.getBankDtlList() != null && !banksView.getBankDtlList().isEmpty()) {
				for (BanksDtlView bankDtlView : banksView.getBankDtlList()) {
					if (!bankDtlView.getAction().equals("delete") && bankDtlView.getAccCurr().equals(accountsCurrencyView.getCurCode()) && !accountsCurrencyView.getUsed()) {
						throw new ValidationException("is_inactive", "currency_code", accountsCurrencyView.getUsed());
					}
				}
			}
		}
		if (chartOfAccountsView.getInactive() && !bank.getInactive()) {
			throw new ValidationException("bank_account_inactive");
		}
		if (banksView.getBankDtlList() != null) {
			Set<String> valuesSetForAdd = new HashSet<>();
			Set<String> DBValuesSetForAdd;
			Set<String> valuesSetForModifyOrDelete = new HashSet<>();
			Set<String> DBValuesSetForModifyOrDelete;
			Map<String, Object> parameters = new HashMap<>();
			BanksDtl banksDtl;
			parameters.put("bankNo", banksView.getBankNo());
			parameters.put("accNo", banksView.getAccountNo());
			for (BanksDtlView obj : banksView.getBankDtlList()) {
				banksDtl = getBanksDtlFromBanksDtlView(obj);
				switch (obj.getAction()) {
				case "add":
					banksDtl.setAddDate(add_date);
					banksDtl.setAddUser(loginUsersView.getUserId());
					banksDtl.setModifyDate(null);
					banksDtl.setModifyUser(null);
					if (banksDtl.getInactive()) {
						banksDtl.setInactiveUser(loginUsersView.getUserId());
					} else {
						banksDtl.setInactiveUser(null);
						banksDtl.setInactiveReason(null);
					}
					bankDtlForAddList.add(banksDtl);
					if (valuesSetForAdd.contains(obj.getAccCurr())) {
						throw new DetailValidationException("already_exist_detail", "currency", obj.getAccCurr(),
								"bank_no", banksDtl.getBankNo());
					} else {
						valuesSetForAdd
								.add(banksDtl.getAccCurr());
					}
					if (!accountCurrencySet.contains(banksDtl.getAccCurr()))
						throw new ValidationException("invalid", "currency");
					break;
				case "update":
					banksDtl.setModifyDate(add_date);
					banksDtl.setModifyUser(loginUsersView.getUserId());
					BanksDtlView DBBanksDtlView = bankDAO.getBanksDtlViewList(bank.getBankNo()).get(0);
					if (DBBanksDtlView.getInactive() && !banksDtl.getInactive()) {
						banksDtl.setInactiveUser(null);
						banksDtl.setInactiveReason(null);

					} else if (!DBBanksDtlView.getInactive() && banksDtl.getInactive()) {
						banksDtl.setInactiveUser(loginUsersView.getUserId());
					} else {
						banksDtl.setInactiveUser(DBBanksDtlView.getInactiveUser());
						banksDtl.setInactiveReason(DBBanksDtlView.getInactiveReason());
					}
					bankDtlForUpdateList.add(banksDtl);
					if (valuesSetForAdd.contains(obj.getAccCurr()))
						throw new DetailValidationException("already_exist_detail", "currency", obj.getAccCurr(),
								"bank_no", banksDtl.getBankNo());
					valuesSetForModifyOrDelete.add(banksDtl.getAccCurr());
					if (!accountCurrencySet.contains(banksDtl.getAccCurr()))
						throw new ValidationException("invalid", "currency");
					break;
				case "delete":
					bankDtlForDeleteList.add(banksDtl);
					valuesSetForModifyOrDelete.add(banksDtl.getAccCurr());
					break;
				}
			}
			if (!valuesSetForAdd.isEmpty()) {
				DBValuesSetForAdd = generalDAO.getThemIfExist("banks_dtl", "bank_no = :bankNo and acc_no = :accNo", parameters,
						"acc_curr", valuesSetForAdd);
				if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
					throw new DetailValidationException("already_exist_detail", "currency",
							DBValuesSetForAdd.toArray()[0], "bank_no", banksView.getBankNo());
			}
			parameters.remove("accNo");
			if (!valuesSetForModifyOrDelete.isEmpty()) {
				DBValuesSetForModifyOrDelete = generalDAO.getThemIfExist("banks_dtl", "bank_no = :bankNo", parameters,
						"acc_curr", valuesSetForModifyOrDelete);
				if (DBValuesSetForModifyOrDelete != null) {
					for (String curCode : valuesSetForModifyOrDelete) {
						if (!DBValuesSetForModifyOrDelete.contains(curCode))
							throw new DetailValidationException("not_exist_detail", "currency", curCode,
									"bank_no", banksView.getBankNo());
					}
				}
			}

		}
		// Update the bank
		BanksView DBBanksView = bankDAO.getBankView(loginUsersView, bank.getBankNo());
		if (DBBanksView.getInactive() && !bank.getInactive()) {
			bank.setInactiveUser(null);
			bank.setInactiveReason(null);

		} else if (!DBBanksView.getInactive() && bank.getInactive()) {
			bank.setInactiveUser(loginUsersView.getUserId());
		} else {
			bank.setInactiveUser(DBBanksView.getInactiveUser());
			bank.setInactiveReason(DBBanksView.getInactiveReason());
		}
		bank.setModifyDate(add_date);
		bank.setModifyUser(loginUsersView.getUserId());
		bankDAO.updateBank(bank, bankDtlForAddList, bankDtlForDeleteList, bankDtlForUpdateList);
		// validate exactly one detail
		conditions.clear();
		conditions.put("bank_no", bank.getBankNo());
		if (generalDAO.getCount("banks_dtl", conditions, null) != 1)
			throw new ValidationException("exactly_one_record", "bank");
	}

	@Override
	@Transactional
	public void deleteBank(UsersView loginUsersView, Integer bankNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.BANKS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.BANKS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.BANKS, FormsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(bankNo, "bank_no");
		coreValidationService.greaterThanOrEqualZero(bankNo, "bank_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("bank_no", bankNo);
		if (!generalDAO.isEntityExist("banks", conditions))
			throw new ValidationException("not_exist", "bank_no");
		// delete the bank
		try {
			bankDAO.deleteBank(bankNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "bank_no");
		}
	}

	@Override
	public void generateBankPrivsForAllUsers(UsersView loginUsersView, Integer bankNo, String AccCurr, Timestamp currentDate) {
		/*
		 * $$$$$$$$$$$$___Do not forget to add in
		 * MasterDataPrivilegesService___$$$$$$$$$$$$
		 */
		// PREPARE VARAIBLES
		List<BanksPriv> banksPrivList = new ArrayList<>();
		BanksPriv banksPriv;
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
				banksPriv = new BanksPriv();
				banksPriv.setAccCurr(AccCurr);
				banksPriv.setBankNo(bankNo);
				banksPriv.setUserId(usersView.getUserId());
				banksPriv.setAddDate(currentDate);
				banksPriv.setAddUser(1);
				banksPriv.setModifyDate(null);
				banksPriv.setModifyUser(null);
				banksPriv.setAddPriv(addPriv);
				banksPriv.setViewPriv(viewPriv);
				banksPrivList.add(banksPriv);
			}
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (BanksPriv priv : banksPrivList) {
			bankDAO.addBanksPriv(priv);
		}
	}

	public Bank getBankFromBanksView(BanksView banksView) {
		Bank bank = new Bank();
		try {
			bank.setAccountNo(banksView.getAccountNo());
			bank.setAddDate(banksView.getAddDate());
			bank.setAddUser(banksView.getAddUser());
			bank.setBankDName(Utils.escapeLiteral(null, banksView.getBankDName(), true).toString());
			if (banksView.getBankFName() == null)
				bank.setBankFName(banksView.getBankFName());
			else
				bank.setBankFName(Utils.escapeLiteral(null, banksView.getBankFName(), true).toString());
			bank.setBankNo(banksView.getBankNo());
			bank.setInactive(banksView.getInactive());
			if (banksView.getInactiveReason() == null)
				bank.setInactiveReason(banksView.getInactiveReason());
			else
				bank.setInactiveReason(Utils.escapeLiteral(null, banksView.getInactiveReason(), true).toString());
			bank.setInactiveUser(banksView.getInactiveUser());
			bank.setModifyDate(banksView.getModifyDate());
			bank.setModifyUser(banksView.getModifyUser());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return bank;
	}

	public BanksDtl getBanksDtlFromBanksDtlView(BanksDtlView banksDtlView) {
		BanksDtl banksDtl = new BanksDtl();
		try {
			banksDtl.setAccCurr(Utils.escapeLiteral(null, banksDtlView.getAccCurr(), true).toString());
			banksDtl.setAccNo(banksDtlView.getAccNo());
			banksDtl.setAddDate(banksDtlView.getAddDate());
			banksDtl.setAddUser(banksDtlView.getAddUser());
			banksDtl.setBankNo(banksDtlView.getBankNo());
			banksDtl.setInactive(banksDtlView.getInactive());
			if (banksDtlView.getInactiveReason() == null)
				banksDtl.setInactiveReason(banksDtlView.getInactiveReason());
			else
				banksDtl.setInactiveReason(
						Utils.escapeLiteral(null, banksDtlView.getInactiveReason(), true).toString());
			banksDtl.setInactiveUser(banksDtlView.getInactiveUser());
			banksDtl.setModifyDate(banksDtlView.getModifyDate());
			banksDtl.setModifyUser(banksDtlView.getModifyUser());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return banksDtl;
	}

}
