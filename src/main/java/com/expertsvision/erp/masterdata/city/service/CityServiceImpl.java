package com.expertsvision.erp.masterdata.city.service;

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
import com.expertsvision.erp.masterdata.city.dao.CityDAO;
import com.expertsvision.erp.masterdata.city.dto.CityViewFilter;
import com.expertsvision.erp.masterdata.city.entity.City;
import com.expertsvision.erp.masterdata.city.entity.CityView;

@Service
public class CityServiceImpl implements CityService {

	@Autowired
	private CityDAO cityDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<CityView> getCityViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
		}
		// Return requested data
		List<CityView> cityView = cityDAO.getAllCityViewList();
		return cityView;
	}

	@Override
	@Transactional
	public CityView getCityView(UsersView loginUsersView, Integer cityNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
		}
		// Return requested data
		CityView cityView = cityDAO.getCityView(cityNo);
		if (cityView == null) {
			throw new ValidationException("not_exist", "city_no");
		}
		return cityView;
	}

	@Override
	@Transactional
	public SinglePage<CityView> getCityViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CityView> singlePage = cityDAO.getCityViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CityView> getCityViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CityView> singlePage = cityDAO.getCityViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCityViewSinglePageNo(UsersView loginUsersView, Integer cityNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = cityDAO.getUserViewSinglePageNo(cityNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "city_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CityView> getCityViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CityView> multiplePages = cityDAO.getCityViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CityView> getCityViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CityViewFilter cityViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CityView> multiplePages = cityDAO.getCityViewFilteredMultiplePages(pageNo,
				cityViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addCity(UsersView loginUsersView, CityView cityView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(cityView.getCityNo(), "city_no");
		coreValidationService.greaterThanOrEqualZero(cityView.getCityNo(), "city_no");
		coreValidationService.notNull(cityView.getCityDName(), "name");
		coreValidationService.notBlank(cityView.getCityDName(), "name");
		if ((cityView.getCityFName() != null) && cityView.getCityFName().isBlank())
			cityView.setCityFName(null);
		coreValidationService.notNull(cityView.getShortcut(), "shortcut_name");
		coreValidationService.notNull(cityView.getProvinceNo(), "province_no");
		// Database validation
		City city = getCityFromCityView(cityView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("city_no", city.getCityNo());
		if (generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("already_exist", "city_no");
		conditions.clear();
		conditions.put("city_d_name", city.getCityDName());
		if (generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("city_f_name", city.getCityFName());
		if (city.getCityFName() != null && generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut", city.getShortcut());
		if (generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("province_no", city.getProvinceNo());
		if (!generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("not_exist", "province_no");
		// Add the city
		Timestamp add_date = new Timestamp(new Date().getTime());
		city.setAddDate(add_date);
		city.setAddUser(loginUsersView.getUserId());
		city.setModifyDate(null);
		city.setModifyUser(null);
		cityDAO.addCity(city);
	}

	@Override
	@Transactional
	public void updateCity(UsersView loginUsersView, CityView cityView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(cityView.getCityNo(), "city_no");
		coreValidationService.greaterThanOrEqualZero(cityView.getCityNo(), "city_no");
		coreValidationService.notNull(cityView.getCityDName(), "name");
		coreValidationService.notBlank(cityView.getCityDName(), "name");
		if ((cityView.getCityFName() != null) && cityView.getCityFName().isBlank())
			cityView.setCityFName(null);
		coreValidationService.notNull(cityView.getShortcut(), "shortcut_name");
		coreValidationService.notNull(cityView.getProvinceNo(), "province_no");
		// Database validation
		City city = getCityFromCityView(cityView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("city_no", city.getCityNo());
		if (!generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("not_exist", "city_no");
		conditions.clear();
		conditions.put("city_d_name", city.getCityDName());
		String exceptionCondition = null;
		exceptionCondition = " and city_no != " + city.getCityNo();
		if (generalDAO.isEntityExist("city", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("city_f_name", city.getCityFName());
		if (city.getCityFName() != null
				&& generalDAO.isEntityExist("city", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("province_no", city.getProvinceNo());
		if (!generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("not_exist", "province_no");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		city.setModifyDate(update_date);
		city.setModifyUser(loginUsersView.getUserId());
		cityDAO.updateCity(city);
	}

	@Override
	@Transactional
	public void deleteCity(UsersView loginUsersView, Integer cityNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.CITY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.CITY, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(cityNo, "city_no");
		coreValidationService.greaterThanOrEqualZero(cityNo, "city_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("city_no", cityNo);
		if (!generalDAO.isEntityExist("city", conditions))
			throw new ValidationException("not_exist", "city_no");
		// delete the userscity
		try {
			cityDAO.deleteCity(cityNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "city");
		}
	}

	public City getCityFromCityView(CityView cityView) {
		City city = new City();
		try {
			city.setAddDate(cityView.getAddDate());
			city.setAddUser(cityView.getAddUser());
			city.setModifyDate(cityView.getModifyDate());
			city.setModifyUser(cityView.getModifyUser());
			city.setCityDName(Utils.escapeLiteral(null, cityView.getCityDName(), true).toString());
			if (cityView.getCityFName() == null)
				city.setCityFName(cityView.getCityFName());
			else
				city.setCityFName(Utils.escapeLiteral(null, cityView.getCityFName(), true).toString());
			city.setCityNo(cityView.getCityNo());
			city.setProvinceNo(cityView.getProvinceNo());
			city.setShortcut(Utils.escapeLiteral(null, cityView.getShortcut(), true).toString());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return city;
	}

}
