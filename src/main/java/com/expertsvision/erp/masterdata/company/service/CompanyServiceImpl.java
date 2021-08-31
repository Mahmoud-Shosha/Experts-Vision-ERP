package com.expertsvision.erp.masterdata.company.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.company.dao.CompanyDAO;
import com.expertsvision.erp.masterdata.company.dto.CompanyViewFilter;
import com.expertsvision.erp.masterdata.company.entity.Company;
import com.expertsvision.erp.masterdata.company.entity.CompanyView;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyDAO companyDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<CompanyView> getCompanyViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		List<CompanyView> companyView = companyDAO.getAllCompanyViewList();
		return companyView;
	}

	@Override
	@Transactional
	public CompanyView getCompanyView(UsersView loginUsersView, Integer companyNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		CompanyView companyView = companyDAO.getCompanyView(companyNo);
		if (companyView == null) {
			throw new ValidationException("not_exist", "company_no");
		}
		return companyView;
	}

	@Override
	@Transactional
	public SinglePage<CompanyView> getCompanyViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CompanyView> singlePage = companyDAO.getCompanyViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CompanyView> getCompanyViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CompanyView> singlePage = companyDAO.getCompanyViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCompanyViewSinglePageNo(UsersView loginUsersView, Integer companyNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = companyDAO.getUserViewSinglePageNo(companyNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "company_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CompanyView> getCompanyViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CompanyView> multiplePages = companyDAO.getCompanyViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CompanyView> getCompanyViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CompanyViewFilter companyViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CompanyView> multiplePages = companyDAO.getCompanyViewFilteredMultiplePages(pageNo,
				companyViewFilter);
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
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
		}
		// Return requested data
		Object PK = companyDAO.getNextPK();
		return PK;
	}

	@Override
	@Transactional
	public void addCompany(UsersView loginUsersView, CompanyView companyView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(companyView.getCompanyNo(), "company_no");
		coreValidationService.greaterThanOrEqualZero(companyView.getCompanyNo(), "company_no");
		coreValidationService.notNull(companyView.getCompanyDName(), "name");
		coreValidationService.notBlank(companyView.getCompanyDName(), "name");
		if ((companyView.getCompanyFName() != null) && companyView.getCompanyFName().isBlank())
			companyView.setCompanyFName(null);
		coreValidationService.notNull(companyView.getShortcutD(), "shortcut_name");
		coreValidationService.notBlank(companyView.getShortcutD(), "shortcut_name");
		if ((companyView.getShortcutF() != null) && companyView.getShortcutF().isBlank())
			companyView.setShortcutF(null);
		coreValidationService.notNull(companyView.getCompanyGroup(), "group_no");
		// Database validation
		Company company = getCompanyFromCompanyView(companyView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("company_no", company.getCompanyNo());
		if (generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("already_exist", "company_no");
		conditions.clear();
		conditions.put("company_d_name", company.getCompanyDName());
		if (generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("company_f_name", company.getCompanyFName());
		if (company.getCompanyFName() != null && generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut_d", company.getShortcutD());
		if (generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("shortcut_f", company.getShortcutF());
		if (company.getCompanyFName() != null && generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("already_exist", "shortcut_f_name");
		conditions.clear();
		conditions.put("group_no", company.getCompanyGroup());
		if (!generalDAO.isEntityExist("company_groups", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		conditions.put("country_no", company.getCountryNo());
		if (company.getCountryNo() != null && !generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");		
		// Add the company
		Timestamp add_date = new Timestamp(new Date().getTime());
		company.setAddDate(add_date);
		company.setAddUser(loginUsersView.getUserId());
		company.setModifyDate(null);
		company.setModifyUser(null);
		companyDAO.addCompany(company);
	}

	@Override
	@Transactional
	public void updateCompany(UsersView loginUsersView, CompanyView companyView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(companyView.getCompanyNo(), "company_no");
		coreValidationService.greaterThanOrEqualZero(companyView.getCompanyNo(), "company_no");
		coreValidationService.notNull(companyView.getCompanyDName(), "name");
		coreValidationService.notBlank(companyView.getCompanyDName(), "name");
		if ((companyView.getCompanyFName() != null) && companyView.getCompanyFName().isBlank())
			companyView.setCompanyFName(null);
		coreValidationService.notNull(companyView.getShortcutD(), "shortcut_name");
		coreValidationService.notBlank(companyView.getShortcutD(), "shortcut_name");
		if ((companyView.getShortcutF() != null) && companyView.getShortcutF().isBlank())
			companyView.setShortcutF(null);
		coreValidationService.notNull(companyView.getCompanyGroup(), "group_no");
		// Database validation
		Company company = getCompanyFromCompanyView(companyView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("company_no", company.getCompanyNo());
		if (!generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("not_exist", "company_no");
		conditions.clear();
		conditions.put("company_d_name", company.getCompanyDName());
		String exceptionCondition = null;
		exceptionCondition = " and company_no != " + company.getCompanyNo();
		if (generalDAO.isEntityExist("company", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("company_f_name", company.getCompanyFName());
		if (company.getCompanyFName() != null && generalDAO.isEntityExist("company", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut_d", company.getShortcutD());
		if (generalDAO.isEntityExist("company", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("shortcut_f", company.getShortcutF());
		if (company.getCompanyFName() != null && generalDAO.isEntityExist("company", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "shortcut_f_name");
		conditions.clear();
		conditions.put("group_no", company.getCompanyGroup());
		if (!generalDAO.isEntityExist("company_groups", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		conditions.put("country_no", company.getCountryNo());
		if (company.getCountryNo() != null && !generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		company.setModifyDate(update_date);
		company.setModifyUser(loginUsersView.getUserId());
		companyDAO.updateCompany(company);
	}

	@Override
	@Transactional
	public void deleteCompany(UsersView loginUsersView, Integer companyNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANIES);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANIES, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(companyNo, "company_no");
		coreValidationService.greaterThanOrEqualZero(companyNo, "company_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("company_no", companyNo);
		if (!generalDAO.isEntityExist("company", conditions))
			throw new ValidationException("not_exist", "company_no");
		// delete the userscompany
		try {
			companyDAO.deleteCompany(companyNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "company_no");
		}
	}

	public Company getCompanyFromCompanyView(CompanyView companyView) {
		Company company = new Company();
		try {
			company.setAddDate(companyView.getAddDate());
			company.setAddUser(companyView.getAddUser());
			company.setModifyDate(companyView.getModifyDate());
			company.setModifyUser(companyView.getModifyUser());
			company.setCompanyDName(Utils.escapeLiteral(null, companyView.getCompanyDName(), true).toString());
			if (companyView.getCompanyFName() == null)
				company.setCompanyFName(companyView.getCompanyFName());
			else
				company.setCompanyFName(Utils.escapeLiteral(null, companyView.getCompanyFName(), true).toString());
			company.setCompanyNo(companyView.getCompanyNo());
			company.setCountryNo(companyView.getCountryNo());
			company.setCompanyGroup(companyView.getCompanyGroup());
			if (companyView.getCompanyMail() == null)
				company.setCompanyMail(companyView.getCompanyMail());
			else
				company.setCompanyMail(Utils.escapeLiteral(null, companyView.getCompanyMail(), true).toString());
			if (companyView.getCompanyWebsite() == null)
				company.setCompanyWebsite(companyView.getCompanyWebsite());
			else
				company.setCompanyWebsite(Utils.escapeLiteral(null, companyView.getCompanyWebsite(), true).toString());			
			company.setShortcutD(Utils.escapeLiteral(null, companyView.getShortcutD(), true).toString());
			if (companyView.getShortcutF() == null)
				company.setShortcutF(companyView.getShortcutF());
			else
				company.setShortcutF(Utils.escapeLiteral(null, companyView.getShortcutF(), true).toString());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return company;
	}

}
