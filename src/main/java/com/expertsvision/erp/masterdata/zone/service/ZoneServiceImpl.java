package com.expertsvision.erp.masterdata.zone.service;

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
import com.expertsvision.erp.masterdata.zone.dao.ZoneDAO;
import com.expertsvision.erp.masterdata.zone.dto.ZoneViewFilter;
import com.expertsvision.erp.masterdata.zone.entity.Zone;
import com.expertsvision.erp.masterdata.zone.entity.ZoneView;

@Service
public class ZoneServiceImpl implements ZoneService {

	@Autowired
	private ZoneDAO zoneDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<ZoneView> getZoneViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
		}
		// Return requested data
		List<ZoneView> zoneView = zoneDAO.getAllZoneViewList();
		return zoneView;
	}

	@Override
	@Transactional
	public ZoneView getZoneView(UsersView loginUsersView, Integer zoneNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
		}
		// Return requested data
		ZoneView zoneView = zoneDAO.getZoneView(zoneNo);
		if (zoneView == null) {
			throw new ValidationException("not_exist", "zone_no");
		}
		return zoneView;
	}

	@Override
	@Transactional
	public SinglePage<ZoneView> getZoneViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<ZoneView> singlePage = zoneDAO.getZoneViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<ZoneView> getZoneViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<ZoneView> singlePage = zoneDAO.getZoneViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getZoneViewSinglePageNo(UsersView loginUsersView, Integer zoneNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = zoneDAO.getUserViewSinglePageNo(zoneNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "zone_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<ZoneView> getZoneViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<ZoneView> multiplePages = zoneDAO.getZoneViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<ZoneView> getZoneViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			ZoneViewFilter zoneViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<ZoneView> multiplePages = zoneDAO.getZoneViewFilteredMultiplePages(pageNo,
				zoneViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addZone(UsersView loginUsersView, ZoneView zoneView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(zoneView.getZoneNo(), "zone_no");
		coreValidationService.greaterThanOrEqualZero(zoneView.getZoneNo(), "zone_no");
		coreValidationService.notNull(zoneView.getZoneDName(), "name");
		coreValidationService.notBlank(zoneView.getZoneDName(), "name");
		if ((zoneView.getZoneFName() != null) && zoneView.getZoneFName().isBlank())
			zoneView.setZoneFName(null);
		coreValidationService.notNull(zoneView.getCityNo(), "city_no");
		// Database validation
		Zone zone = getZoneFromZoneView(zoneView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("zone_no", zone.getZoneNo());
		if (generalDAO.isEntityExist("zone", conditions))
			throw new ValidationException("already_exist", "zone_no");
		conditions.clear();
		conditions.put("zone_d_name", zone.getZoneDName());
		if (generalDAO.isEntityExist("zone", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("zone_f_name", zone.getZoneFName());
		if (zone.getZoneFName() != null && generalDAO.isEntityExist("zone", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("city_no", zone.getCityNo());
		if (!generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("not_exist", "city_no");
		// Add the zone
		Timestamp add_date = new Timestamp(new Date().getTime());
		zone.setAddDate(add_date);
		zone.setAddUser(loginUsersView.getUserId());
		zone.setModifyDate(null);
		zone.setModifyUser(null);
		zoneDAO.addZone(zone);
	}

	@Override
	@Transactional
	public void updateZone(UsersView loginUsersView, ZoneView zoneView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(zoneView.getZoneNo(), "zone_no");
		coreValidationService.greaterThanOrEqualZero(zoneView.getZoneNo(), "zone_no");
		coreValidationService.notNull(zoneView.getZoneDName(), "name");
		coreValidationService.notBlank(zoneView.getZoneDName(), "name");
		if ((zoneView.getZoneFName() != null) && zoneView.getZoneFName().isBlank())
			zoneView.setZoneFName(null);
		coreValidationService.notNull(zoneView.getCityNo(), "city_no");
		// Database validation
		Zone zone = getZoneFromZoneView(zoneView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("zone_no", zone.getZoneNo());
		if (!generalDAO.isEntityExist("zone", conditions))
			throw new ValidationException("not_exist", "zone_no");
		conditions.clear();
		conditions.put("zone_d_name", zone.getZoneDName());
		String exceptionCondition = null;
		exceptionCondition = " and zone_no != " + zone.getZoneNo();
		if (generalDAO.isEntityExist("zone", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("zone_f_name", zone.getZoneFName());
		if (zone.getZoneFName() != null
				&& generalDAO.isEntityExist("zone", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("city_no", zone.getCityNo());
		if (!generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("not_exist", "city_no");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		zone.setModifyDate(update_date);
		zone.setModifyUser(loginUsersView.getUserId());
		zoneDAO.updateZone(zone);
	}

	@Override
	@Transactional
	public void deleteZone(UsersView loginUsersView, Integer zoneNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.ZONE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.ZONE, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(zoneNo, "zone_no");
		coreValidationService.greaterThanOrEqualZero(zoneNo, "zone_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("zone_no", zoneNo);
		if (!generalDAO.isEntityExist("zone", conditions))
			throw new ValidationException("not_exist", "zone_no");
		// delete the userszone
		try {
			zoneDAO.deleteZone(zoneNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "zone");
		}
	}

	public Zone getZoneFromZoneView(ZoneView zoneView) {
		Zone zone = new Zone();
		try {
			zone.setAddDate(zoneView.getAddDate());
			zone.setAddUser(zoneView.getAddUser());
			zone.setModifyDate(zoneView.getModifyDate());
			zone.setModifyUser(zoneView.getModifyUser());
			zone.setZoneDName(Utils.escapeLiteral(null, zoneView.getZoneDName(), true).toString());
			if (zoneView.getZoneFName() == null)
				zone.setZoneFName(zoneView.getZoneFName());
			else
				zone.setZoneFName(Utils.escapeLiteral(null, zoneView.getZoneFName(), true).toString());
			zone.setZoneNo(zoneView.getZoneNo());
			zone.setCityNo(zoneView.getCityNo());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return zone;
	}

}
