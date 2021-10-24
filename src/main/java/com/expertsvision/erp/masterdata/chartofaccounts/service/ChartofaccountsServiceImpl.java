package com.expertsvision.erp.masterdata.chartofaccounts.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
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
import com.expertsvision.erp.masterdata.accpara.entity.AccPara;
import com.expertsvision.erp.masterdata.accpara.service.AccParaService;
import com.expertsvision.erp.masterdata.chartofaccounts.dao.ChartofaccountsDAO;
import com.expertsvision.erp.masterdata.chartofaccounts.dto.ChartOfAccountsViewFilter;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrency;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyView;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPriv;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccount;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;

@Service
public class ChartofaccountsServiceImpl implements ChartofaccountsService {

	@Autowired
	private ChartofaccountsDAO chartofaccountsDAO;

	@Autowired
	private AccParaService accParaService;

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
	
	private final List<String> CASH_FLOW_ANALYSIS = Arrays.asList("1", "2", "3", "4", "5", "6", "7");
	
	private final List<String> LIST_CASH_FLOW = Arrays.asList("1", "2");
	
	private final List<String> LIST_CHART_OF_ACC = Arrays.asList("1", "2", "3", "4", "5");
	
	private final List<String> CC_POST = Arrays.asList("1", "2", "3", "4");

	@Override
	@Transactional
	public List<ChartOfAccountsView> getChartOfAccountsViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		// Return requested data
		List<ChartOfAccountsView> chartOfAccountsView = chartofaccountsDAO.getAllChartOfAccountsViewList();
		return chartOfAccountsView;
	}

	@Override
	@Transactional
	public ChartOfAccountsView getChartOfAccountsView(UsersView loginUsersView, Integer accNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		// Return requested data
		ChartOfAccountsView chartOfAccountsView = chartofaccountsDAO.getChartOfAccountsView(accNo);
		if (chartOfAccountsView == null)
			throw new ValidationException("not_exist", "acc_no");
		if (chartOfAccountsView.getSub()) {
			if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
				loginUsersView = null;
			chartOfAccountsView.setAccountCurrencyList(chartofaccountsDAO.getAccountsCurrencyViewList(loginUsersView, accNo));
		}
		return chartOfAccountsView;
	}

	@Override
	@Transactional
	public SinglePage<ChartOfAccountsView> getChartOfAccountsViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		// Return requested data
		SinglePage<ChartOfAccountsView> singlePage = chartofaccountsDAO.getChartOfAccountsViewSinglePage(pageNo);
		ChartOfAccountsView coav = singlePage.getPage();
		if (coav != null && coav.getSub()) {
			if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
				loginUsersView = null;
			coav.setAccountCurrencyList(chartofaccountsDAO.getAccountsCurrencyViewList(loginUsersView, coav.getAccNo()));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<ChartOfAccountsView> getChartOfAccountsViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		SinglePage<ChartOfAccountsView> singlePage = chartofaccountsDAO.getChartOfAccountsViewLastPage();
		ChartOfAccountsView coav = singlePage.getPage();
		if (coav != null && coav.getSub()) {
			if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
				loginUsersView = null;
			coav.setAccountCurrencyList(chartofaccountsDAO.getAccountsCurrencyViewList(loginUsersView, coav.getAccNo()));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public Long getChartOfAccountsViewSinglePageNo(UsersView loginUsersView, Integer accNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = chartofaccountsDAO.getUserViewSinglePageNo(accNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "acc_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<ChartOfAccountsView> getChartOfAccountsViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<ChartOfAccountsView> multiplePages = chartofaccountsDAO.getChartOfAccountsViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<ChartOfAccountsView> getChartOfAccountsViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			ChartOfAccountsViewFilter chartOfAccountsViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<ChartOfAccountsView> multiplePages = chartofaccountsDAO.getChartOfAccountsViewFilteredMultiplePages(pageNo,
				chartOfAccountsViewFilter);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public Object getNextPK(UsersView loginUsersView, Integer parentAcc) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
		}
		// Return requested data
		if (parentAcc.equals(0))
			return null;
		Integer PK = (Integer)chartofaccountsDAO.getNextPK(parentAcc);
		if (PK == null) return null;
		String childDigitsString = PK.toString().substring(parentAcc.toString().length());
		if ((Math.pow(10, childDigitsString.length()) - 1) > Integer.parseInt(childDigitsString))
			return ++PK;
		else
			return null;
	}

	@Override
	@Transactional
	public void addChartOfAccount(UsersView loginUsersView, ChartOfAccountsView chartOfAccountsView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.ADD);
		}
		Timestamp add_date = new Timestamp(new Date().getTime());
		List<AccountsCurrency> AccountsCurrencyForAddList = new ArrayList<>();
		// Non-database validation
		coreValidationService.notNull(chartOfAccountsView.getAccNo(), "acc_no");
		coreValidationService.greaterThanZero(chartOfAccountsView.getAccNo(), "acc_no");
		coreValidationService.notNull(chartOfAccountsView.getAccDName(), "name");
		coreValidationService.notBlank(chartOfAccountsView.getAccDName(), "name");
		if ((chartOfAccountsView.getAccFName() != null) && chartOfAccountsView.getAccFName().isBlank())
			chartOfAccountsView.setAccFName(null);
		coreValidationService.notNull(chartOfAccountsView.getSub(), "type");
		coreValidationService.notNull(chartOfAccountsView.getLevel(), "acc_level");
		coreValidationService.greaterThanZero(chartOfAccountsView.getLevel(), "acc_level");
		coreValidationService.notNull(chartOfAccountsView.getParentAcc(), "parent_acc");
		coreValidationService.greaterThanZero(chartOfAccountsView.getParentAcc(), "parent_acc");
		if (chartOfAccountsView.getAccGroup() != null) 
			coreValidationService.greaterThanOrEqualZero(chartOfAccountsView.getAccGroup(), "group");
		coreValidationService.notNull(chartOfAccountsView.getDr(), "acc_nature");
		coreValidationService.notNull(chartOfAccountsView.getBs(), "report_type");
		coreValidationService.notNull(chartOfAccountsView.getCcPost(), "cc_post");
		coreValidationService.notNull(chartOfAccountsView.getInactive(), "inactive");
		if (!chartOfAccountsView.getSub()) {
			chartOfAccountsView.setAccType(null);
			chartOfAccountsView.setCashFlowType(null);
			chartOfAccountsView.setAccountCurrencyList(null);
		}
		if (!CC_POST.contains(chartOfAccountsView.getCcPost()))
			throw new ValidationException("invalid", "cc_post");
		if (chartOfAccountsView.getAccDtl() != null) {
			if (chartOfAccountsView.getAccDtl().isBlank())
				chartOfAccountsView.setAccDtl(null);
			else if (!CASH_FLOW_ANALYSIS.contains(chartOfAccountsView.getAccDtl()))
				throw new ValidationException("invalid", "acc_dtl");
		}
		if (chartOfAccountsView.getCashFlowType() != null) {
			if (chartOfAccountsView.getCashFlowType().isBlank())
				chartOfAccountsView.setCashFlowType(null);
			else if (!LIST_CASH_FLOW.contains(chartOfAccountsView.getCashFlowType()))
				throw new ValidationException("invalid", "cash_flow_type");
		}
		if (chartOfAccountsView.getAccType() != null) {
			if (chartOfAccountsView.getAccType().isBlank())
				chartOfAccountsView.setAccType(null);
			else if (!LIST_CHART_OF_ACC.contains(chartOfAccountsView.getAccType()))
				throw new ValidationException("invalid", "acc_type");
		}
		// Non-database validation for details
		if (chartOfAccountsView.getAccountCurrencyList() != null) {
			for (AccountsCurrencyView obj : chartOfAccountsView.getAccountCurrencyList()) {
				obj.setAccNo(chartOfAccountsView.getAccNo());
				coreValidationService.notNull(obj.getCurCode(), "currency_code");
				coreValidationService.notBlank(obj.getCurCode(), "currency_code");
				coreValidationService.notNull(obj.getActive(), "active");
				coreValidationService.notNull(obj.getUsed(), "used");
				switch (obj.getAction()) {
				case "add":
					break;
				default:
					throw new DetailValidationException("invalid_detail", "action", obj.getAction(), "cur_code", obj.getCurCode());
				}
			}
		}
		// Database validation
		ChartOfAccount chartOfAccount = getChartOfAccountFromChartOfAccountsView(chartOfAccountsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("acc_no", chartOfAccount.getAccNo());
		if (generalDAO.isEntityExist("chart_of_accounts", conditions))
			throw new ValidationException("already_exist", "acc_no");
		conditions.clear();
		conditions.put("acc_d_name", chartOfAccount.getAccDName());
		if (generalDAO.isEntityExist("chart_of_accounts", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("acc_f_name", chartOfAccount.getAccFName());
		if (chartOfAccount.getAccFName() != null && generalDAO.isEntityExist("chart_of_accounts", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("group_no", chartOfAccount.getAccGroup());
		if (chartOfAccount.getAccGroup() != null && !generalDAO.isEntityExist("accounts_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		AccPara accPara = accParaService.getAccPara();
		ChartOfAccountsView parentAcc = chartofaccountsDAO.getChartOfAccountsView(chartOfAccount.getParentAcc());
		if (chartOfAccount.getLevel().equals(1)) {
			if (!chartOfAccount.getParentAcc().equals(0))
				throw new ValidationException("invalid", "parent_acc");
		} else {
			if (parentAcc == null)
				throw new ValidationException("not_exist", "parent_acc");
			if (!chartOfAccount.getLevel().equals(parentAcc.getLevel()+1))
				throw new ValidationException("invalid", "acc_level");
		}
		if (chartOfAccountsView.getSub()) {
			if (!chartOfAccount.getLevel().equals(accPara.getSubAccLvl()))
				throw new ValidationException("invalid", "acc_level");
		}
		if (!chartOfAccountsView.getParentAcc().equals(0) && !chartOfAccountsView.getBs().equals(parentAcc.getBs()))
			throw new ValidationException("invalid", "report_type");	
		switch (accPara.getCcPost()) {
			case "1":
				if (!chartOfAccountsView.getCcPost().equals("1"))
					throw new ValidationException("invalid", "cc_post");	
				break;
			case "2":
				if (!chartOfAccountsView.getBs())
					throw new ValidationException("invalid", "cc_post");	
				break;
		}
		// Database validation for details
		if (chartOfAccountsView.getAccountCurrencyList() != null) {
			Set<String> valuesSetForAdd = new HashSet<>();
			Set<String> DBValuesSetForAdd;
			Map<String, Object> parameters = new HashMap<>();
			AccountsCurrency accountsCurrency;
			parameters.put("accNo", chartOfAccountsView.getAccNo());
			for (AccountsCurrencyView obj : chartOfAccountsView.getAccountCurrencyList()) {
				accountsCurrency = getAccountsCurrencyViewFromAccountsCurrencyView(obj);
				accountsCurrency.setAddDate(add_date);
				accountsCurrency.setAddUser(loginUsersView.getUserId());
				accountsCurrency.setModifyDate(null);
				accountsCurrency.setModifyUser(null);
				AccountsCurrencyForAddList.add(accountsCurrency);
				if (valuesSetForAdd.contains(obj.getCurCode())) {
					throw new DetailValidationException("already_exist_detail", "currency",
							obj.getCurCode(), "acc_no", chartOfAccountsView.getAccNo());
				} else {
					valuesSetForAdd.add(obj.getCurCode());
				}
			}
			DBValuesSetForAdd = generalDAO.getThemIfExist("accounts_currency",
					"acc_no = :accNo", parameters, "cur_code", valuesSetForAdd);
			if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
				throw new DetailValidationException("already_exist_detail", "currency",
						DBValuesSetForAdd.toArray()[0], "acc_no", chartOfAccountsView.getAccNo());
		}
		// Add the chartOfAccountes
		chartOfAccount.setAddDate(add_date);
		chartOfAccount.setAddUser(loginUsersView.getUserId());
		chartOfAccount.setModifyDate(null);
		chartOfAccount.setModifyUser(null);
		if (chartOfAccount.getInactive()) {
			chartOfAccount.setInactiveDate(add_date);
			chartOfAccount.setInactiveUser(loginUsersView.getUserId());
		} else {
			chartOfAccount.setInactiveDate(null);
			chartOfAccount.setInactiveUser(null);
			chartOfAccount.setInactiveReason(null);
		}
		chartofaccountsDAO.addChartOfAccount(chartOfAccount, AccountsCurrencyForAddList); 
		for (AccountsCurrency obj : AccountsCurrencyForAddList) {
			generateChartOfAccountesPrivsForAllUsers(obj.getAccNo(), obj.getCurCode(), add_date);
		}
	}

	@Override
	@Transactional
	public void updateChartOfAccount(UsersView loginUsersView, ChartOfAccountsView chartOfAccountsView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.MODIFY);
		}
		Timestamp update_date = new Timestamp(new Date().getTime());
		List<AccountsCurrency> AccountsCurrencyForAddList = new ArrayList<>();
		List<AccountsCurrency> AccountsCurrencyForUpdateList = new ArrayList<>();
		List<AccountsCurrency> AccountsCurrencyForDeleteList = new ArrayList<>();
		// Non-database validation
		coreValidationService.notNull(chartOfAccountsView.getAccNo(), "acc_no");
		coreValidationService.greaterThanZero(chartOfAccountsView.getAccNo(), "acc_no");
		coreValidationService.notNull(chartOfAccountsView.getAccDName(), "name");
		coreValidationService.notBlank(chartOfAccountsView.getAccDName(), "name");
		if ((chartOfAccountsView.getAccFName() != null) && chartOfAccountsView.getAccFName().isBlank())
			chartOfAccountsView.setAccFName(null);
		coreValidationService.notNull(chartOfAccountsView.getSub(), "type");
		coreValidationService.notNull(chartOfAccountsView.getLevel(), "acc_level");
		coreValidationService.greaterThanZero(chartOfAccountsView.getLevel(), "acc_level");
		coreValidationService.notNull(chartOfAccountsView.getParentAcc(), "parent_acc");
		coreValidationService.greaterThanZero(chartOfAccountsView.getParentAcc(), "parent_acc");
		if (chartOfAccountsView.getAccGroup() != null) 
			coreValidationService.greaterThanOrEqualZero(chartOfAccountsView.getAccGroup(), "group");
		coreValidationService.notNull(chartOfAccountsView.getDr(), "acc_nature");
		coreValidationService.notNull(chartOfAccountsView.getBs(), "report_type");
		coreValidationService.notNull(chartOfAccountsView.getCcPost(), "cc_post");
		coreValidationService.notNull(chartOfAccountsView.getInactive(), "inactive");
		if (!chartOfAccountsView.getSub()) {
			chartOfAccountsView.setAccType(null);
			chartOfAccountsView.setCashFlowType(null);
			chartOfAccountsView.setAccountCurrencyList(null);
		}
		if (!CC_POST.contains(chartOfAccountsView.getCcPost()))
			throw new ValidationException("invalid", "cc_post");
		if (chartOfAccountsView.getAccDtl() != null) {
			if (chartOfAccountsView.getAccDtl().isBlank())
				chartOfAccountsView.setAccDtl(null);
			else if (!CASH_FLOW_ANALYSIS.contains(chartOfAccountsView.getAccDtl()))
				throw new ValidationException("invalid", "acc_dtl");
		}
		if (chartOfAccountsView.getCashFlowType() != null) {
			if (chartOfAccountsView.getCashFlowType().isBlank())
				chartOfAccountsView.setCashFlowType(null);
			else if (!LIST_CASH_FLOW.contains(chartOfAccountsView.getCashFlowType()))
				throw new ValidationException("invalid", "cash_flow_type");
		}
		if (chartOfAccountsView.getAccType() != null) {
			if (chartOfAccountsView.getAccType().isBlank())
				chartOfAccountsView.setAccType(null);
			else if (!LIST_CHART_OF_ACC.contains(chartOfAccountsView.getAccType()))
				throw new ValidationException("invalid", "acc_type");
		}
		// Non-database validation for details
		if (chartOfAccountsView.getAccountCurrencyList() != null) {
			for (AccountsCurrencyView obj : chartOfAccountsView.getAccountCurrencyList()) {
				obj.setAccNo(chartOfAccountsView.getAccNo());
				coreValidationService.notNull(obj.getCurCode(), "currency_code");
				coreValidationService.notBlank(obj.getCurCode(), "currency_code");
				coreValidationService.notNull(obj.getActive(), "active");
				coreValidationService.notNull(obj.getUsed(), "used");
				switch (obj.getAction()) {
				case "add":
				case "update":
				case "delete":
					break;
				default:
					throw new DetailValidationException("invalid_detail", "action", obj.getAction(), "cur_code", obj.getCurCode());
				}
			}
		}
		// Database validation
		ChartOfAccount chartOfAccount = getChartOfAccountFromChartOfAccountsView(chartOfAccountsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("acc_no", chartOfAccount.getAccNo());
		if (!generalDAO.isEntityExist("chart_of_accounts", conditions))
			throw new ValidationException("not_exist", "acc_no");
		conditions.clear();
		conditions.put("acc_d_name", chartOfAccount.getAccDName());
		String exceptionCondition = null;
		exceptionCondition = " and acc_no != " + chartOfAccount.getAccNo();
		if (generalDAO.isEntityExist("chart_of_accounts", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("acc_f_name", chartOfAccount.getAccFName());
		if (chartOfAccount.getAccFName() != null && generalDAO.isEntityExist("chart_of_accounts", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("group_no", chartOfAccount.getAccGroup());
		if (chartOfAccount.getAccGroup() != null && !generalDAO.isEntityExist("accounts_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		AccPara accPara = accParaService.getAccPara();
		ChartOfAccountsView parentAcc = chartofaccountsDAO.getChartOfAccountsView(chartOfAccount.getParentAcc());
		if (chartOfAccount.getLevel().equals(1)) {
			if (!chartOfAccount.getParentAcc().equals(0))
				throw new ValidationException("invalid", "parent_acc");
		} else {
			if (parentAcc == null)
				throw new ValidationException("not_exist", "parent_acc");
			if (!chartOfAccount.getLevel().equals(parentAcc.getLevel()+1))
				throw new ValidationException("invalid", "acc_level");
		}
		if (chartOfAccountsView.getSub()) {
			if (!chartOfAccount.getLevel().equals(accPara.getSubAccLvl()))
				throw new ValidationException("invalid", "acc_level");
		}
		if (!chartOfAccountsView.getParentAcc().equals(0) && !chartOfAccountsView.getBs().equals(parentAcc.getBs()))
			throw new ValidationException("invalid", "report_type");
		switch (accPara.getCcPost()) {
			case "1":
				if (!chartOfAccountsView.getCcPost().equals("1"))
					throw new ValidationException("invalid", "cc_post");	
				break;
			case "2":
				if (!chartOfAccountsView.getBs())
					throw new ValidationException("invalid", "cc_post");	
				break;
		}
		// Database validation for details
		if (chartOfAccountsView.getAccountCurrencyList() != null) {
			Set<String> valuesSetForAdd = new HashSet<>();
			Set<String> DBValuesSetForAdd;
			Set<String> valuesSetForModifyOrDelete = new HashSet<>();
			Set<String> DBValuesSetForModifyOrDelete;
			Map<String, Object> parameters = new HashMap<>();
			AccountsCurrency accountsCurrency;
			parameters.put("accNo", chartOfAccountsView.getAccNo());
			for (AccountsCurrencyView obj : chartOfAccountsView.getAccountCurrencyList()) {
				accountsCurrency = getAccountsCurrencyViewFromAccountsCurrencyView(obj);
				switch (obj.getAction()) {
				case "add":
					accountsCurrency = getAccountsCurrencyViewFromAccountsCurrencyView(obj);
					accountsCurrency.setAddDate(update_date);
					accountsCurrency.setAddUser(loginUsersView.getUserId());
					accountsCurrency.setModifyDate(null);
					accountsCurrency.setModifyUser(null);
					AccountsCurrencyForAddList.add(accountsCurrency);
					if (valuesSetForAdd.contains(obj.getCurCode())) {
						throw new DetailValidationException("already_exist_detail", "currency",
								obj.getCurCode(), "acc_no", chartOfAccountsView.getAccNo());
					} else {
						valuesSetForAdd.add(obj.getCurCode());
					}
					break;
				case "update":
					accountsCurrency = getAccountsCurrencyViewFromAccountsCurrencyView(obj);
					accountsCurrency.setModifyDate(update_date);
					accountsCurrency.setModifyUser(loginUsersView.getUserId());
					AccountsCurrencyForUpdateList.add(accountsCurrency);
					if (valuesSetForAdd.contains(obj.getCurCode()))
						throw new DetailValidationException("already_exist_detail", "currency",
								obj.getCurCode(), "acc_no", chartOfAccountsView.getAccNo());
					valuesSetForModifyOrDelete.add(accountsCurrency.getCurCode());
					break;
				case "delete":
					AccountsCurrencyForDeleteList.add(accountsCurrency);
					valuesSetForModifyOrDelete.add(accountsCurrency.getCurCode());
					break;
				}
			}
			if (!valuesSetForAdd.isEmpty()) {
				DBValuesSetForAdd = generalDAO.getThemIfExist("accounts_currency",
						"acc_no = :accNo", parameters, "cur_code", valuesSetForAdd);
				if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
					throw new DetailValidationException("already_exist_detail", "currency",
							DBValuesSetForAdd.toArray()[0], "acc_no", chartOfAccountsView.getAccNo());
			}
			if (!valuesSetForModifyOrDelete.isEmpty()) {
				DBValuesSetForModifyOrDelete = generalDAO.getThemIfExist("accounts_currency",
						"acc_no = :accNo", parameters, "cur_code", valuesSetForModifyOrDelete);
				if (DBValuesSetForModifyOrDelete != null) {
					for (String i : valuesSetForModifyOrDelete) {
						if (!DBValuesSetForModifyOrDelete.contains(i))
							throw new DetailValidationException("not_exist_detail", "currency",
									i, "acc_no", chartOfAccountsView.getAccNo());
					}
				}
			}
			
		}
		// Update the chartOfAccount
		ChartOfAccountsView DBChartOfAccountsView = chartofaccountsDAO.getChartOfAccountsView(chartOfAccountsView.getAccNo());
		if (DBChartOfAccountsView.getInactive() && !chartOfAccountsView.getInactive()) {
			chartOfAccount.setInactiveDate(null);
			chartOfAccount.setInactiveUser(null);
			chartOfAccount.setInactiveReason(null);

		} else if (!DBChartOfAccountsView.getInactive() && chartOfAccountsView.getInactive()) {
			chartOfAccount.setInactiveDate(update_date);
			chartOfAccount.setInactiveUser(loginUsersView.getUserId());
		} else {
			chartOfAccount.setInactiveDate(DBChartOfAccountsView.getInactiveDate());
			chartOfAccount.setInactiveUser(DBChartOfAccountsView.getInactiveUser());
			chartOfAccount.setInactiveReason(DBChartOfAccountsView.getInactiveReason());
		}
		chartOfAccount.setModifyDate(update_date);
		chartOfAccount.setModifyUser(loginUsersView.getUserId());
		chartofaccountsDAO.updateChartOfAccount(chartOfAccount, AccountsCurrencyForAddList,
				AccountsCurrencyForDeleteList, AccountsCurrencyForUpdateList);
		for (AccountsCurrency obj : AccountsCurrencyForAddList) {
			generateChartOfAccountesPrivsForAllUsers(obj.getAccNo(), obj.getCurCode(), update_date);
		}
	}

	@Override
	@Transactional
	public void deleteChartOfAccount(UsersView loginUsersView, Integer accNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(accNo, "acc_no");
		coreValidationService.greaterThanOrEqualZero(accNo, "acc_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("acc_no", accNo);
		if (!generalDAO.isEntityExist("chart_of_accounts", conditions))
			throw new ValidationException("not_exist", "acc_no");
		if (hasSubAccounts(accNo))
			throw new ValidationException("has_sub_accounts");
		// delete the userschartOfAccountes
		try {
			chartofaccountsDAO.deleteChartOfAccount(accNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "acc_no");
		}
	}
	
	@Override
	@Transactional
	public PreData preAdd(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.ADD);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		AccPara accPara = accParaService.getAccPara();
		info.put("sub_level", accPara.getSubAccLvl());
		info.put("cc_post", accPara.getCcPost());
		// return the data
		return preData;
	}
	
	@Override
	@Transactional
	public PreData preModify(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CHART_OF_ACCOUNTS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CHART_OF_ACCOUNTS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CHART_OF_ACCOUNTS, FormsActions.MODIFY);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		AccPara accPara = accParaService.getAccPara();
		info.put("sub_level", accPara.getSubAccLvl());
		info.put("cc_post", accPara.getCcPost());
		// return the data
		return preData;
	}

	@Override
	public void generateChartOfAccountesPrivsForAllUsers(Integer accNo, String curCode, Timestamp currentDate) {
		/*
		 * $$$$$$$$$$$$___Do not forget to add in
		 * MasterDataPrivilegesService___$$$$$$$$$$$$
		 */
		// PREPARE VARAIBLES
		List<AccountsPriv> accountPrivList = new ArrayList<>();
		AccountsPriv accountPriv;
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
				accountPriv = new AccountsPriv();
				accountPriv.setAccCurr(curCode);
				accountPriv.setAccNo(accNo);
				accountPriv.setAddDate(currentDate);
				accountPriv.setAddPriv(addPriv);
				accountPriv.setAddUser(1);
				accountPriv.setModifyDate(null);
				accountPriv.setModifyUser(null);
				accountPriv.setUserId(usersView.getUserId());
				accountPriv.setViewPriv(viewPriv);
				accountPrivList.add(accountPriv);
			}
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (AccountsPriv priv : accountPrivList) {
			chartofaccountsDAO.addAccountsPriv(priv);
		}
	}
	
	@Override
	public boolean hasSubAccounts(Integer accNo) {
		return chartofaccountsDAO.hasSubAccounts(accNo);
	}

	public ChartOfAccount getChartOfAccountFromChartOfAccountsView(ChartOfAccountsView chartOfAccountsView) {
		ChartOfAccount chartOfAccount = new ChartOfAccount();
		try {
			chartOfAccount.setAccDName(Utils.escapeLiteral(null, chartOfAccountsView.getAccDName(), true).toString());
			chartOfAccount.setCcPost(Utils.escapeLiteral(null, chartOfAccountsView.getCcPost(), true).toString());
			if (chartOfAccountsView.getAccDtl() == null)
				chartOfAccount.setAccDtl(chartOfAccountsView.getAccDtl());
			else
				chartOfAccount.setAccDtl(Utils.escapeLiteral(null, chartOfAccountsView.getAccDtl(), true).toString());
			if (chartOfAccountsView.getAccFName() == null)
				chartOfAccount.setAccFName(chartOfAccountsView.getAccFName());
			else
				chartOfAccount.setAccFName(Utils.escapeLiteral(null, chartOfAccountsView.getAccFName(), true).toString());
			chartOfAccount.setAccGroup(chartOfAccountsView.getAccGroup());
			chartOfAccount.setAccNo(chartOfAccountsView.getAccNo());
			if (chartOfAccountsView.getAccType() == null)
				chartOfAccount.setAccType(chartOfAccountsView.getAccType());
			else
				chartOfAccount.setAccType(Utils.escapeLiteral(null, chartOfAccountsView.getAccType(), true).toString());
			chartOfAccount.setAddDate(chartOfAccountsView.getAddDate());
			chartOfAccount.setAddUser(chartOfAccountsView.getAddUser());
			chartOfAccount.setBs(chartOfAccountsView.getBs());
			if (chartOfAccountsView.getCashFlowType() == null)
				chartOfAccount.setCashFlowType(chartOfAccountsView.getCashFlowType());
			else
				chartOfAccount.setCashFlowType(Utils.escapeLiteral(null, chartOfAccountsView.getCashFlowType(), true).toString());
			chartOfAccount.setDr(chartOfAccountsView.getDr());
			chartOfAccount.setInactive(chartOfAccountsView.getInactive());
			chartOfAccount.setInactiveDate(chartOfAccountsView.getInactiveDate());
			if (chartOfAccountsView.getInactiveReason() == null)
				chartOfAccount.setInactiveReason(chartOfAccountsView.getInactiveReason());
			else
				chartOfAccount.setInactiveReason(Utils.escapeLiteral(null, chartOfAccountsView.getInactiveReason(), true).toString());
			chartOfAccount.setInactiveUser(chartOfAccountsView.getInactiveUser());
			chartOfAccount.setLevel(chartOfAccountsView.getLevel());
			chartOfAccount.setModifyDate(chartOfAccountsView.getModifyDate());
			chartOfAccount.setModifyUser(chartOfAccountsView.getModifyUser());
			chartOfAccount.setParentAcc(chartOfAccountsView.getParentAcc());
			chartOfAccount.setSub(chartOfAccountsView.getSub());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return chartOfAccount;
	}
	
	public AccountsCurrency  getAccountsCurrencyViewFromAccountsCurrencyView(AccountsCurrencyView accountsCurrencyView) {
		AccountsCurrency accountsCurrency = new AccountsCurrency();
		try {
			accountsCurrency.setAccNo(accountsCurrencyView.getAccNo());
			accountsCurrency.setActive(accountsCurrencyView.getActive());
			accountsCurrency.setAddDate(accountsCurrencyView.getAddDate());
			accountsCurrency.setAddUser(accountsCurrencyView.getAddUser());
			accountsCurrency.setCurCode(Utils.escapeLiteral(null, accountsCurrencyView.getCurCode(), true).toString());
			accountsCurrency.setModifyDate(accountsCurrencyView.getModifyDate());
			accountsCurrency.setModifyUser(accountsCurrencyView.getModifyUser());
			accountsCurrency.setUsed(accountsCurrencyView.getUsed());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return accountsCurrency;
	}

}
