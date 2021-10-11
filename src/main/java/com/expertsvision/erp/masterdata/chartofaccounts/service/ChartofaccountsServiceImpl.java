package com.expertsvision.erp.masterdata.chartofaccounts.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
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
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;
import com.expertsvision.erp.core.utils.FlagDetails;
import com.expertsvision.erp.core.utils.FlagsActions;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.branches.dao.BranchDAO;
import com.expertsvision.erp.masterdata.branches.entity.Branch;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.chartofaccounts.dao.ChartofaccountsDAO;
import com.expertsvision.erp.masterdata.chartofaccounts.dto.ChartOfAccountsViewFilter;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccount;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyView;

@Service
public class ChartofaccountsServiceImpl implements ChartofaccountsService {

	@Autowired
	private ChartofaccountsDAO chartofaccountsDAO;

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
		Double childDigits = Double.parseDouble(PK.toString().substring(parentAcc.toString().length()));
		if ((Math.pow(10, childDigits) - 1) > childDigits)
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
		// Non-database validation
		coreValidationService.notNull(branchView.getBranchNo(), "branch_no");
		coreValidationService.greaterThanOrEqualZero(branchView.getBranchNo(), "branch_no");
		coreValidationService.notNull(branchView.getBranchDName(), "name");
		coreValidationService.notBlank(branchView.getBranchDName(), "name");
		if ((branchView.getBranchFName() != null) && branchView.getBranchFName().isBlank())
			branchView.setBranchFName(null);
		coreValidationService.notNull(branchView.getShortcutD(), "shortcut_name");
		coreValidationService.notBlank(branchView.getShortcutD(), "shortcut_name");
		if ((branchView.getShortcutF() != null) && branchView.getShortcutF().isBlank())
			branchView.setShortcutF(null);
		coreValidationService.notNull(branchView.getCompanyNo(), "company_no");
		coreValidationService.greaterThanOrEqualZero(branchView.getCompanyNo(), "company_no");
		if ((branchView.getBranchDAddress() != null) && branchView.getBranchDAddress().isBlank())
			branchView.setBranchDAddress(null);
		if ((branchView.getBranchFAddress() != null) && branchView.getBranchFAddress().isBlank())
			branchView.setBranchFAddress(null);
		if ((branchView.getReportDHeader1() != null) && branchView.getReportDHeader1().isBlank())
			branchView.setReportDHeader1(null);
		if ((branchView.getReportFHeader1() != null) && branchView.getReportFHeader1().isBlank())
			branchView.setReportFHeader1(null);
		if ((branchView.getReportDHeader2() != null) && branchView.getReportDHeader2().isBlank())
			branchView.setReportDHeader2(null);
		if ((branchView.getReportFHeader2() != null) && branchView.getReportFHeader2().isBlank())
			branchView.setReportFHeader2(null);
		if ((branchView.getReportDHeader3() != null) && branchView.getReportDHeader3().isBlank())
			branchView.setReportDHeader3(null);
		if ((branchView.getReportFHeader3() != null) && branchView.getReportFHeader3().isBlank())
			branchView.setReportFHeader3(null);
		if ((branchView.getTelephoneNo() != null) && branchView.getTelephoneNo().isBlank())
			branchView.setTelephoneNo(null);
		if ((branchView.getLogo() != null) && branchView.getLogo().isBlank())
			branchView.setLogo(null);
		if (branchView.getCityNo() != null) {
			coreValidationService.notNull(branchView.getProvinceNo(), "province_no");
			coreValidationService.greaterThanOrEqualZero(branchView.getProvinceNo(), "province_no");
			coreValidationService.notNull(branchView.getCountryNo(), "country_no");
			coreValidationService.greaterThanOrEqualZero(branchView.getCountryNo(), "country_no");
		} else if (branchView.getProvinceNo() != null) {
			coreValidationService.notNull(branchView.getCountryNo(), "country_no");
			coreValidationService.greaterThanOrEqualZero(branchView.getCountryNo(), "country_no");
		}
		// Database validation
		Branch branch = getBranchFromChartOfAccountsView(branchView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("branch_no", branch.getBranchNo());
		if (generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("already_exist", "branch_no");
		conditions.clear();
		conditions.put("branch_d_name", branch.getBranchDName());
		if (generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("branch_f_name", branch.getBranchFName());
		if (branch.getBranchFName() != null && generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut_d", branch.getShortcutD());
		if (generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("shortcut_f", branch.getShortcutF());
		if (branch.getShortcutF() != null && generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("already_exist", "shortcut_F_name");
		conditions.clear();
		conditions.put("branch_d_address", branch.getBranchDAddress());
		if (branch.getBranchDAddress() != null && generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("already_exist", "address");
		conditions.clear();
		conditions.put("branch_f_address", branch.getBranchFAddress());
		if (branch.getBranchFAddress() != null && generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("already_exist", "address_f");
		conditions.clear();
		conditions.put("city_no", branch.getCityNo());
		if (branch.getCityNo() != null && !generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("not_exist", "city_no");
		conditions.clear();
		conditions.put("province_no", branch.getProvinceNo());
		if (branch.getProvinceNo() != null && !generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("not_exist", "province_no");
		conditions.clear();
		conditions.put("country_no", branch.getCountryNo());
		if (branch.getCountryNo() != null && !generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");
		conditions.clear();
		conditions.put("company_no", branch.getCompanyNo());
		if (!generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("not_exist", "company_no");
		conditions.clear();
		if (branchView.getCityNo() != null) {
			conditions.put("city_no", branch.getCityNo());
			if (!generalDAO.isEntityExist("city", conditions))
				throw new ValidationException("not_exist", "city_no");
			conditions.clear();
			conditions.put("city_no", branch.getCityNo());
			conditions.put("province_no", branch.getProvinceNo());
			if (!generalDAO.isEntityExist("city", conditions))
				throw new ValidationException("not_belong_to", "city_no", "province_no");
			conditions.clear();
			conditions.put("province_no", branch.getProvinceNo());
			conditions.put("country_no", branch.getCountryNo());
			if (!generalDAO.isEntityExist("province", conditions))
				throw new ValidationException("not_belong_to", "province_no", "country_no");
			conditions.clear();
		} else if (branchView.getProvinceNo() != null) {
			conditions.put("province_no", branch.getProvinceNo());
			if (!generalDAO.isEntityExist("province", conditions))
				throw new ValidationException("not_exist", "province_no");
			conditions.clear();
			conditions.put("province_no", branch.getProvinceNo());
			conditions.put("country_no", branch.getCountryNo());
			if (!generalDAO.isEntityExist("province", conditions))
				throw new ValidationException("not_belong_to", "province_no", "country_no");
			conditions.clear();
		} else if (branchView.getCountryNo() != null) {
			conditions.put("country_no", branch.getCountryNo());
			if (!generalDAO.isEntityExist("country", conditions))
				throw new ValidationException("not_exist", "country_no");
		}
		// Add the branches
		Timestamp add_date = new Timestamp(new Date().getTime());
		branch.setAddDate(add_date);
		branch.setAddUser(loginUsersView.getUserId());
		branch.setModifyDate(null);
		branch.setModifyUser(null);
		branchDAO.addBranch(branch);
		generateBranchesPrivsForAllUsers(branch.getBranchNo(), add_date);
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
		// Non-database validation
		coreValidationService.notNull(branchView.getBranchNo(), "branch_no");
		coreValidationService.greaterThanOrEqualZero(branchView.getBranchNo(), "branch_no");
		coreValidationService.notNull(branchView.getBranchDName(), "name");
		coreValidationService.notBlank(branchView.getBranchDName(), "name");
		if ((branchView.getBranchFName() != null) && branchView.getBranchFName().isBlank())
			branchView.setBranchFName(null);
		coreValidationService.notNull(branchView.getShortcutD(), "shortcut_name");
		coreValidationService.notBlank(branchView.getShortcutD(), "shortcut_name");
		if ((branchView.getShortcutF() != null) && branchView.getShortcutF().isBlank())
			branchView.setShortcutF(null);
		coreValidationService.notNull(branchView.getCompanyNo(), "company_no");
		coreValidationService.greaterThanOrEqualZero(branchView.getCompanyNo(), "company_no");
		if ((branchView.getBranchDAddress() != null) && branchView.getBranchDAddress().isBlank())
			branchView.setBranchDAddress(null);
		if ((branchView.getBranchFAddress() != null) && branchView.getBranchFAddress().isBlank())
			branchView.setBranchFAddress(null);
		if ((branchView.getReportDHeader1() != null) && branchView.getReportDHeader1().isBlank())
			branchView.setReportDHeader1(null);
		if ((branchView.getReportFHeader1() != null) && branchView.getReportFHeader1().isBlank())
			branchView.setReportFHeader1(null);
		if ((branchView.getReportDHeader2() != null) && branchView.getReportDHeader2().isBlank())
			branchView.setReportDHeader2(null);
		if ((branchView.getReportFHeader2() != null) && branchView.getReportFHeader2().isBlank())
			branchView.setReportFHeader2(null);
		if ((branchView.getReportDHeader3() != null) && branchView.getReportDHeader3().isBlank())
			branchView.setReportDHeader3(null);
		if ((branchView.getReportFHeader3() != null) && branchView.getReportFHeader3().isBlank())
			branchView.setReportFHeader3(null);
		if ((branchView.getTelephoneNo() != null) && branchView.getTelephoneNo().isBlank())
			branchView.setTelephoneNo(null);
		if ((branchView.getLogo() != null) && branchView.getLogo().isBlank())
			branchView.setLogo(null);
		if (branchView.getCityNo() != null) {
			coreValidationService.notNull(branchView.getProvinceNo(), "province_no");
			coreValidationService.greaterThanOrEqualZero(branchView.getProvinceNo(), "province_no");
			coreValidationService.notNull(branchView.getCountryNo(), "country_no");
			coreValidationService.greaterThanOrEqualZero(branchView.getCountryNo(), "country_no");
		} else if (branchView.getProvinceNo() != null) {
			coreValidationService.notNull(branchView.getCountryNo(), "country_no");
			coreValidationService.greaterThanOrEqualZero(branchView.getCountryNo(), "country_no");
		}
		// Database validation
		Branch branch = getBranchFromChartOfAccountsView(branchView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("branch_no", branch.getBranchNo());
		if (!generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("not_exist", "branch_no");
		conditions.clear();
		conditions.put("branch_d_name", branch.getBranchDName());
		String exceptionCondition = null;
		exceptionCondition = " and branch_no != " + branch.getBranchNo();
		if (generalDAO.isEntityExist("branches", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("branch_f_name", branch.getBranchFName());
		if (branch.getBranchFName() != null && generalDAO.isEntityExist("branches", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut_d", branch.getShortcutD());
		if (generalDAO.isEntityExist("branches", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("shortcut_f", branch.getShortcutF());
		if (branch.getShortcutF() != null && generalDAO.isEntityExist("branches", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "shortcut_F_name");
		conditions.clear();
		conditions.put("branch_d_address", branch.getBranchDAddress());
		if (branch.getBranchDAddress() != null && generalDAO.isEntityExist("branches", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "address");
		conditions.clear();
		conditions.put("branch_f_address", branch.getBranchFAddress());
		if (branch.getBranchFAddress() != null && generalDAO.isEntityExist("branches", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "address_f");
		conditions.clear();
		conditions.put("city_no", branch.getCityNo());
		if (branch.getCityNo() != null && !generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("not_exist", "city_no");
		conditions.clear();
		conditions.put("province_no", branch.getProvinceNo());
		if (branch.getProvinceNo() != null && !generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("not_exist", "province_no");
		conditions.clear();
		conditions.put("country_no", branch.getCountryNo());
		if (branch.getCountryNo() != null && !generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");
		conditions.clear();
		conditions.put("company_no", branch.getCompanyNo());
		if (!generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("not_exist", "company_no");
		conditions.clear();
		if (branchView.getCityNo() != null) {
			conditions.put("city_no", branch.getCityNo());
			if (!generalDAO.isEntityExist("city", conditions))
				throw new ValidationException("not_exist", "city_no");
			conditions.clear();
			conditions.put("city_no", branch.getCityNo());
			conditions.put("province_no", branch.getProvinceNo());
			if (!generalDAO.isEntityExist("city", conditions))
				throw new ValidationException("not_belong_to", "city_no", "province_no");
			conditions.clear();
			conditions.put("province_no", branch.getProvinceNo());
			conditions.put("country_no", branch.getCountryNo());
			if (!generalDAO.isEntityExist("province", conditions))
				throw new ValidationException("not_belong_to", "province_no", "country_no");
			conditions.clear();
		} else if (branchView.getProvinceNo() != null) {
			conditions.put("province_no", branch.getProvinceNo());
			if (!generalDAO.isEntityExist("province", conditions))
				throw new ValidationException("not_exist", "province_no");
			conditions.clear();
			conditions.put("province_no", branch.getProvinceNo());
			conditions.put("country_no", branch.getCountryNo());
			if (!generalDAO.isEntityExist("province", conditions))
				throw new ValidationException("not_belong_to", "province_no", "country_no");
			conditions.clear();
		} else if (branchView.getCountryNo() != null) {
			conditions.put("country_no", branch.getCountryNo());
			if (!generalDAO.isEntityExist("country", conditions))
				throw new ValidationException("not_exist", "country_no");
		}
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		branch.setModifyDate(update_date);
		branch.setModifyUser(loginUsersView.getUserId());
		branchDAO.updateBranch(branch);
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
		coreValidationService.notNull(branchesNo, "branch_no");
		coreValidationService.greaterThanOrEqualZero(branchesNo, "branch_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("branch_no", branchesNo);
		if (!generalDAO.isEntityExist("branches", conditions))
			throw new ValidationException("not_exist", "branch_no");
		// delete the usersbranches
		try {
			branchDAO.deleteBranch(branchesNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "branch_no");
		}
	}

	@Override
	public void generateBranchesPrivsForAllUsers(Integer branchesNo, Timestamp currentDate) {
		/*
		 * $$$$$$$$$$$$___Do not forget to add in
		 * MasterDataPrivilegesService___$$$$$$$$$$$$
		 */
		// PREPARE VARAIBLES
		List<BranchesPriv> branchesPrivList = new ArrayList<>();
		BranchesPriv branchesPriv;
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
				branchesPriv = new BranchesPriv();
				branchesPriv.setAddDate(currentDate);
				branchesPriv.setAddPriv(addPriv);
				branchesPriv.setAddUser(1);
				branchesPriv.setBranchNo(branchesNo);
				branchesPriv.setModifyDate(null);
				branchesPriv.setModifyUser(null);
				branchesPriv.setUserId(usersView.getUserId());
				branchesPriv.setViewPriv(viewPriv);
				branchesPrivList.add(branchesPriv);
			}
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (BranchesPriv priv : branchesPrivList) {
			branchDAO.addBranchesPriv(priv);
		}
	}

	public ChartOfAccount getChartOfAccountFromChartOfAccountsView(ChartOfAccountsView chartOfAccountsView) {
		ChartOfAccount chartOfAccount = new ChartOfAccount();
		try {
			chartOfAccount.setAccDName(Utils.escapeLiteral(null, chartOfAccountsView.getAccDName(), true).toString());
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

}
