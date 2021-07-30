package com.expertsvision.erp.masterdata.region.service;

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
import com.expertsvision.erp.masterdata.region.dao.RegionDAO;
import com.expertsvision.erp.masterdata.region.dto.RegionViewFilter;
import com.expertsvision.erp.masterdata.region.entity.Region;
import com.expertsvision.erp.masterdata.region.entity.RegionView;

@Service
public class RegionServiceImpl implements RegionService {

	@Autowired
	private RegionDAO regionDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<RegionView> getRegionViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
		}
		// Return requested data
		List<RegionView> regionView = regionDAO.getAllRegionViewList();
		return regionView;
	}

	@Override
	@Transactional
	public RegionView getRegionView(UsersView loginUsersView, Integer regionNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
		}
		// Return requested data
		RegionView regionView = regionDAO.getRegionView(regionNo);
		if (regionView == null) {
			throw new ValidationException("not_exist", "region_no");
		}
		return regionView;
	}

	@Override
	@Transactional
	public SinglePage<RegionView> getRegionViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<RegionView> singlePage = regionDAO.getRegionViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<RegionView> getRegionViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<RegionView> singlePage = regionDAO.getRegionViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getRegionViewSinglePageNo(UsersView loginUsersView, Integer regionNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = regionDAO.getUserViewSinglePageNo(regionNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "region_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<RegionView> getRegionViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<RegionView> multiplePages = regionDAO.getRegionViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<RegionView> getRegionViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			RegionViewFilter regionViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<RegionView> multiplePages = regionDAO.getRegionViewFilteredMultiplePages(pageNo,
				regionViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addRegion(UsersView loginUsersView, RegionView regionView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(regionView.getRegionNo(), "region_no");
		coreValidationService.greaterThanOrEqualZero(regionView.getRegionNo(), "region_no");
		coreValidationService.notNull(regionView.getRegionDName(), "name");
		coreValidationService.notBlank(regionView.getRegionDName(), "name");
		if ((regionView.getRegionFName() != null) && regionView.getRegionFName().isBlank())
			regionView.setRegionFName(null);
		coreValidationService.notNull(regionView.getShortcut(), "shortcut_name");
		// Database validation
		Region region = getRegionFromRegionView(regionView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("region_no", region.getRegionNo());
		if (generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("already_exist", "region_no");
		conditions.clear();
		conditions.put("region_d_name", region.getRegionDName());
		if (generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("region_f_name", region.getRegionFName());
		if (region.getRegionFName() != null && generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut", region.getShortcut());
		if (generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("already_exist", "shortcut_name");
		// Add the region
		Timestamp add_date = new Timestamp(new Date().getTime());
		region.setAddDate(add_date);
		region.setAddUser(loginUsersView.getUserId());
		region.setModifyDate(null);
		region.setModifyUser(null);
		regionDAO.addRegion(region);
	}

	@Override
	@Transactional
	public void updateRegion(UsersView loginUsersView, RegionView regionView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(regionView.getRegionNo(), "region_no");
		coreValidationService.greaterThanOrEqualZero(regionView.getRegionNo(), "region_no");
		coreValidationService.notNull(regionView.getRegionDName(), "name");
		coreValidationService.notBlank(regionView.getRegionDName(), "name");
		if ((regionView.getRegionFName() != null) && regionView.getRegionFName().isBlank())
			regionView.setRegionFName(null);
		coreValidationService.notNull(regionView.getShortcut(), "shortcut_name");
		// Database validation
		Region region = getRegionFromRegionView(regionView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("region_no", region.getRegionNo());
		if (!generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("not_exist", "region_no");
		conditions.clear();
		conditions.put("region_d_name", region.getRegionDName());
		String exceptionCondition = null;
		exceptionCondition = " and region_no != " + region.getRegionNo();
		if (generalDAO.isEntityExist("region", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("region_f_name", region.getRegionFName());
		if (region.getRegionFName() != null
				&& generalDAO.isEntityExist("region", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		region.setModifyDate(update_date);
		region.setModifyUser(loginUsersView.getUserId());
		regionDAO.updateRegion(region);
	}

	@Override
	@Transactional
	public void deleteRegion(UsersView loginUsersView, Integer regionNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.REGION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.REGION, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(regionNo, "region_no");
		coreValidationService.greaterThanOrEqualZero(regionNo, "region_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("region_no", regionNo);
		if (!generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("not_exist", "region_no");
		// delete the usersregion
		try {
			regionDAO.deleteRegion(regionNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "region");
		}
	}

	public Region getRegionFromRegionView(RegionView regionView) {
		Region region = new Region();
		try {
			region.setAddDate(regionView.getAddDate());
			region.setAddUser(regionView.getAddUser());
			region.setModifyDate(regionView.getModifyDate());
			region.setModifyUser(regionView.getModifyUser());
			region.setRegionDName(Utils.escapeLiteral(null, regionView.getRegionDName(), true).toString());
			if (regionView.getRegionFName() == null)
				region.setRegionFName(regionView.getRegionFName());
			else
				region.setRegionFName(Utils.escapeLiteral(null, regionView.getRegionFName(), true).toString());
			region.setRegionNo(regionView.getRegionNo());
			region.setShortcut(Utils.escapeLiteral(null, regionView.getShortcut(), true).toString());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return region;
	}

}
