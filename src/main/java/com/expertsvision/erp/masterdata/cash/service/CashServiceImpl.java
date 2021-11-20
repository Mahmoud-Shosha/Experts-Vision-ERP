package com.expertsvision.erp.masterdata.cash.service;

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
import com.expertsvision.erp.masterdata.branches.dto.BranchesViewFilter;
import com.expertsvision.erp.masterdata.branches.entity.Branch;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.branches.entity.BranchesView;

@Service
public class CashServiceImpl implements CashService {

	@Autowired
	private BranchDAO branchDAO;

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
	public List<BranchesView> getBranchesViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		List<BranchesView> branchesView = branchDAO.getAllBranchViewList(loginUsersView);
		return branchesView;
	}

	@Override
	@Transactional
	public BranchesView getBranchesView(UsersView loginUsersView, Integer branchesNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		BranchesView branchesView = branchDAO.getBranchView(loginUsersView, branchesNo);
		if (branchesView == null) {
			throw new ValidationException("not_exist", "branch_no");
		}
		return branchesView;
	}

	@Override
	@Transactional
	public SinglePage<BranchesView> getBranchesViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<BranchesView> singlePage = branchDAO.getBranchViewSinglePage(loginUsersView, pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<BranchesView> getBranchesViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<BranchesView> singlePage = branchDAO.getBranchViewLastPage(loginUsersView);
		return singlePage;
	}

	@Override
	@Transactional
	public Long getBranchesViewSinglePageNo(UsersView loginUsersView, Integer branchesNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		Long singlePageNo = branchDAO.getUserViewSinglePageNo(loginUsersView, branchesNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "branch_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<BranchesView> getBranchesViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<BranchesView> multiplePages = branchDAO.getBranchViewMultiplePages(loginUsersView, pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<BranchesView> getBranchesViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			BranchesViewFilter branchesViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<BranchesView> multiplePages = branchDAO.getBranchViewFilteredMultiplePages(loginUsersView, pageNo,
				branchesViewFilter);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public Object getNextPK(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES, FlagsActions.VIEW);
		}
		// Return requested data
		Object PK = branchDAO.getNextPK();
		return PK;
	}

	@Override
	@Transactional
	public void addBranches(UsersView loginUsersView, BranchesView branchView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.ADD);
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
		Branch branch = getBranchFromBranchesView(branchView);
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
	public void updateBranches(UsersView loginUsersView, BranchesView branchView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.MODIFY);
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
		Branch branch = getBranchFromBranchesView(branchView);
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
	public void deleteBranches(UsersView loginUsersView, Integer branchesNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.BRANCHES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.BRANCHES,
					FlagsActions.DELETE);
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

	public Branch getBranchFromBranchesView(BranchesView branchesView) {
		Branch branch = new Branch();
		try {
			branch.setAddDate(branchesView.getAddDate());
			branch.setAddUser(branchesView.getAddUser());
			branch.setModifyDate(branchesView.getModifyDate());
			branch.setModifyUser(branchesView.getModifyUser());
			branch.setBranchDName(Utils.escapeLiteral(null, branchesView.getBranchDName(), true).toString());
			if (branchesView.getBranchFName() == null)
				branch.setBranchFName(branchesView.getBranchFName());
			else
				branch.setBranchFName(Utils.escapeLiteral(null, branchesView.getBranchFName(), true).toString());
			branch.setBranchNo(branchesView.getBranchNo());
			branch.setCountryNo(branchesView.getCountryNo());
			branch.setShortcutD(Utils.escapeLiteral(null, branchesView.getShortcutD(), true).toString());
			if (branchesView.getShortcutF() == null)
				branch.setShortcutF(branchesView.getShortcutF());
			else
				branch.setShortcutF(Utils.escapeLiteral(null, branchesView.getShortcutF(), true).toString());
			if (branchesView.getBranchDAddress() == null)
				branch.setBranchDAddress(branchesView.getBranchDAddress());
			else
				branch.setBranchDAddress(Utils.escapeLiteral(null, branchesView.getBranchDAddress(), true).toString());
			if (branchesView.getBranchFAddress() == null)
				branch.setBranchFAddress(branchesView.getBranchFAddress());
			else
				branch.setBranchFAddress(Utils.escapeLiteral(null, branchesView.getBranchFAddress(), true).toString());
			if (branchesView.getReportDHeader1() == null)
				branch.setReportDHeader1(branchesView.getReportDHeader1());
			else
				branch.setReportDHeader1(Utils.escapeLiteral(null, branchesView.getReportDHeader1(), true).toString());
			if (branchesView.getReportFHeader1() == null)
				branch.setReportFHeader1(branchesView.getReportFHeader1());
			else
				branch.setReportFHeader1(Utils.escapeLiteral(null, branchesView.getReportFHeader1(), true).toString());
			if (branchesView.getReportDHeader2() == null)
				branch.setReportDHeader2(branchesView.getReportDHeader2());
			else
				branch.setReportDHeader2(Utils.escapeLiteral(null, branchesView.getReportDHeader2(), true).toString());
			if (branchesView.getReportFHeader2() == null)
				branch.setReportFHeader2(branchesView.getReportFHeader2());
			else
				branch.setReportFHeader2(Utils.escapeLiteral(null, branchesView.getReportFHeader2(), true).toString());
			if (branchesView.getReportDHeader3() == null)
				branch.setReportDHeader3(branchesView.getReportDHeader3());
			else
				branch.setReportDHeader3(Utils.escapeLiteral(null, branchesView.getReportDHeader3(), true).toString());
			if (branchesView.getReportFHeader3() == null)
				branch.setReportFHeader3(branchesView.getReportFHeader3());
			else
				branch.setReportFHeader3(Utils.escapeLiteral(null, branchesView.getReportFHeader3(), true).toString());
			if (branchesView.getTelephoneNo() == null)
				branch.setTelephoneNo(branchesView.getTelephoneNo());
			else
				branch.setTelephoneNo(Utils.escapeLiteral(null, branchesView.getTelephoneNo(), true).toString());
			branch.setCapital(branchesView.getCapital());
			branch.setCityNo(branchesView.getCityNo());
			branch.setCompanyNo(branchesView.getCompanyNo());
			branch.setCrNo(branchesView.getCrNo());
			branch.setLogo(branchesView.getLogo());
			branch.setProvinceNo(branchesView.getProvinceNo());
			branch.setTaxNo(branchesView.getTaxNo());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return branch;
	}

}
