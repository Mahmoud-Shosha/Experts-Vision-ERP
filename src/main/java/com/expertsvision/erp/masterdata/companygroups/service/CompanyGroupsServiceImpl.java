package com.expertsvision.erp.masterdata.companygroups.service;

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
import com.expertsvision.erp.masterdata.companygroups.dao.CompanyGroupsDAOImpl;
import com.expertsvision.erp.masterdata.companygroups.dto.CompanyGroupsViewFilter;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroup;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroupsView;

@Service
public class CompanyGroupsServiceImpl implements CompanyGroupsService {

	@Autowired
	private CompanyGroupsDAOImpl companyGroupsDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<CompanyGroupsView> getCompanyGroupsViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
		}
		// Return requested data
		List<CompanyGroupsView> companyGroupsView = companyGroupsDAO.getAllCompanyGroupsViewList();
		return companyGroupsView;
	}

	@Override
	@Transactional
	public CompanyGroupsView getCompanyGroupsView(UsersView loginUsersView, Integer companyGroupsNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
		}
		// Return requested data
		CompanyGroupsView companyGroupsView = companyGroupsDAO.getCompanyGroupsView(companyGroupsNo);
		if (companyGroupsView == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return companyGroupsView;
	}

	@Override
	@Transactional
	public SinglePage<CompanyGroupsView> getCompanyGroupsViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CompanyGroupsView> singlePage = companyGroupsDAO.getCompanyGroupsViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CompanyGroupsView> getCompanyGroupsViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CompanyGroupsView> singlePage = companyGroupsDAO.getCompanyGroupsViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCompanyGroupsViewSinglePageNo(UsersView loginUsersView, Integer companyGroupsNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = companyGroupsDAO.getUserViewSinglePageNo(companyGroupsNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CompanyGroupsView> getCompanyGroupsViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CompanyGroupsView> multiplePages = companyGroupsDAO.getCompanyGroupsViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CompanyGroupsView> getCompanyGroupsViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CompanyGroupsViewFilter companyGroupsViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CompanyGroupsView> multiplePages = companyGroupsDAO.getCompanyGroupsViewFilteredMultiplePages(pageNo,
				companyGroupsViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addCompanyGroups(UsersView loginUsersView, CompanyGroupsView companyGroupsView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(companyGroupsView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(companyGroupsView.getGroupNo(), "group_no");
		coreValidationService.notNull(companyGroupsView.getGroupDName(), "name");
		coreValidationService.notBlank(companyGroupsView.getGroupDName(), "name");
		if ((companyGroupsView.getGroupFName() != null) && companyGroupsView.getGroupFName().isBlank())
			companyGroupsView.setGroupFName(null);
		// Database validation
		CompanyGroup companyGroup = getCompanyGroupFromCompanyGroupsView(companyGroupsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", companyGroup.getGroupNo());
		if (generalDAO.isEntityExist("company_groups", conditions))
			throw new ValidationException("already_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", companyGroup.getGroupDName());
		if (generalDAO.isEntityExist("company_groups", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", companyGroup.getGroupFName());
		if (companyGroup.getGroupFName() != null && generalDAO.isEntityExist("company_groups", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		// Add the companyGroups
		Timestamp add_date = new Timestamp(new Date().getTime());
		companyGroup.setAddDate(add_date);
		companyGroup.setAddUser(loginUsersView.getUserId());
		companyGroup.setModifyDate(null);
		companyGroup.setModifyUser(null);
		companyGroupsDAO.addCompanyGroups(companyGroup);
	}

	@Override
	@Transactional
	public void updateCompanyGroups(UsersView loginUsersView, CompanyGroupsView companyGroupsView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(companyGroupsView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(companyGroupsView.getGroupNo(), "group_no");
		coreValidationService.notNull(companyGroupsView.getGroupDName(), "name");
		coreValidationService.notBlank(companyGroupsView.getGroupDName(), "name");
		if ((companyGroupsView.getGroupFName() != null) && companyGroupsView.getGroupFName().isBlank())
			companyGroupsView.setGroupFName(null);
		// Database validation
		CompanyGroup companyGroup = getCompanyGroupFromCompanyGroupsView(companyGroupsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", companyGroup.getGroupNo());
		if (!generalDAO.isEntityExist("company_groups", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", companyGroup.getGroupDName());
		String exceptionCondition = null;
		exceptionCondition = " and group_no != " + companyGroup.getGroupNo();
		if (generalDAO.isEntityExist("company_groups", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", companyGroup.getGroupFName());
		if (companyGroup.getGroupFName() != null
				&& generalDAO.isEntityExist("company_groups", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		companyGroup.setModifyDate(update_date);
		companyGroup.setModifyUser(loginUsersView.getUserId());
		companyGroupsDAO.updateCompanyGroups(companyGroup);
	}

	@Override
	@Transactional
	public void deleteCompanyGroups(UsersView loginUsersView, Integer companyGroupsNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COMPANIES_AND_BRANCHES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COMPANIES_AND_BRANCHES);
				coreValidationService.activeFlagDetail(FlagDetails.COMPANY_GROUPS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COMPANIES_AND_BRANCHES, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COMPANY_GROUPS, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(companyGroupsNo, "group_no");
		coreValidationService.greaterThanOrEqualZero(companyGroupsNo, "group_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", companyGroupsNo);
		if (!generalDAO.isEntityExist("company_groups", conditions))
			throw new ValidationException("not_exist", "group_no");
		// delete the userscompanyGroups
		try {
			companyGroupsDAO.deleteCompanyGroups(companyGroupsNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "group");
		}
	}

	public CompanyGroup getCompanyGroupFromCompanyGroupsView(CompanyGroupsView companyGroupsView) {
		CompanyGroup companyGroups = new CompanyGroup();
		try {
			companyGroups.setAddDate(companyGroupsView.getAddDate());
			companyGroups.setAddUser(companyGroupsView.getAddUser());
			companyGroups.setModifyDate(companyGroupsView.getModifyDate());
			companyGroups.setModifyUser(companyGroupsView.getModifyUser());
			companyGroups.setGroupDName(Utils.escapeLiteral(null, companyGroupsView.getGroupDName(), true).toString());
			if (companyGroupsView.getGroupFName() == null)
				companyGroups.setGroupFName(companyGroupsView.getGroupFName());
			else
				companyGroups.setGroupFName(Utils.escapeLiteral(null, companyGroupsView.getGroupFName(), true).toString());
			companyGroups.setGroupNo(companyGroupsView.getGroupNo());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return companyGroups;
	}

}
