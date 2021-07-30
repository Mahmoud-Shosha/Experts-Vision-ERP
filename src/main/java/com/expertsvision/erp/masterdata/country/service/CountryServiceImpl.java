package com.expertsvision.erp.masterdata.country.service;

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
import com.expertsvision.erp.masterdata.country.dao.CountryDAO;
import com.expertsvision.erp.masterdata.country.dto.CountryViewFilter;
import com.expertsvision.erp.masterdata.country.entity.Country;
import com.expertsvision.erp.masterdata.country.entity.CountryView;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<CountryView> getCountryViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
		}
		// Return requested data
		List<CountryView> countryView = countryDAO.getAllCountryViewList();
		return countryView;
	}

	@Override
	@Transactional
	public CountryView getCountryView(UsersView loginUsersView, Integer countryNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
		}
		// Return requested data
		CountryView countryView = countryDAO.getCountryView(countryNo);
		if (countryView == null) {
			throw new ValidationException("not_exist", "country_no");
		}
		return countryView;
	}

	@Override
	@Transactional
	public SinglePage<CountryView> getCountryViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CountryView> singlePage = countryDAO.getCountryViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CountryView> getCountryViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CountryView> singlePage = countryDAO.getCountryViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCountryViewSinglePageNo(UsersView loginUsersView, Integer countryNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = countryDAO.getUserViewSinglePageNo(countryNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "country_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CountryView> getCountryViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CountryView> multiplePages = countryDAO.getCountryViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CountryView> getCountryViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CountryViewFilter countryViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CountryView> multiplePages = countryDAO.getCountryViewFilteredMultiplePages(pageNo,
				countryViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addCountry(UsersView loginUsersView, CountryView countryView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(countryView.getCountryNo(), "country_no");
		coreValidationService.greaterThanOrEqualZero(countryView.getCountryNo(), "country_no");
		coreValidationService.notNull(countryView.getCountryDName(), "name");
		coreValidationService.notBlank(countryView.getCountryDName(), "name");
		if ((countryView.getCountryFName() != null) && countryView.getCountryFName().isBlank())
			countryView.setCountryFName(null);
		coreValidationService.notNull(countryView.getShortcut(), "shortcut_name");
		coreValidationService.notNull(countryView.getRegionNo(), "region_no");
		// Database validation
		Country country = getCountryFromCountryView(countryView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("country_no", country.getCountryNo());
		if (generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("already_exist", "country_no");
		conditions.clear();
		conditions.put("country_d_name", country.getCountryDName());
		if (generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("country_f_name", country.getCountryFName());
		if (country.getCountryFName() != null && generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut", country.getShortcut());
		if (generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("region_no", country.getRegionNo());
		if (!generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("not_exist", "region_no");
		// Add the country
		Timestamp add_date = new Timestamp(new Date().getTime());
		country.setAddDate(add_date);
		country.setAddUser(loginUsersView.getUserId());
		country.setModifyDate(null);
		country.setModifyUser(null);
		countryDAO.addCountry(country);
	}

	@Override
	@Transactional
	public void updateCountry(UsersView loginUsersView, CountryView countryView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(countryView.getCountryNo(), "country_no");
		coreValidationService.greaterThanOrEqualZero(countryView.getCountryNo(), "country_no");
		coreValidationService.notNull(countryView.getCountryDName(), "name");
		coreValidationService.notBlank(countryView.getCountryDName(), "name");
		if ((countryView.getCountryFName() != null) && countryView.getCountryFName().isBlank())
			countryView.setCountryFName(null);
		coreValidationService.notNull(countryView.getShortcut(), "shortcut_name");
		coreValidationService.notNull(countryView.getRegionNo(), "region_no");
		// Database validation
		Country country = getCountryFromCountryView(countryView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("country_no", country.getCountryNo());
		if (!generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");
		conditions.clear();
		conditions.put("country_d_name", country.getCountryDName());
		String exceptionCondition = null;
		exceptionCondition = " and country_no != " + country.getCountryNo();
		if (generalDAO.isEntityExist("country", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("country_f_name", country.getCountryFName());
		if (country.getCountryFName() != null
				&& generalDAO.isEntityExist("country", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("region_no", country.getRegionNo());
		if (!generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("not_exist", "region_no");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		country.setModifyDate(update_date);
		country.setModifyUser(loginUsersView.getUserId());
		countryDAO.updateCountry(country);
	}

	@Override
	@Transactional
	public void deleteCountry(UsersView loginUsersView, Integer countryNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COUNTRY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COUNTRY, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(countryNo, "country_no");
		coreValidationService.greaterThanOrEqualZero(countryNo, "country_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("country_no", countryNo);
		if (!generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");
		// delete the userscountry
		try {
			countryDAO.deleteCountry(countryNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "country");
		}
	}

	public Country getCountryFromCountryView(CountryView countryView) {
		Country country = new Country();
		try {
			country.setAddDate(countryView.getAddDate());
			country.setAddUser(countryView.getAddUser());
			country.setModifyDate(countryView.getModifyDate());
			country.setModifyUser(countryView.getModifyUser());
			country.setCountryDName(Utils.escapeLiteral(null, countryView.getCountryDName(), true).toString());
			if (countryView.getCountryFName() == null)
				country.setCountryFName(countryView.getCountryFName());
			else
				country.setCountryFName(Utils.escapeLiteral(null, countryView.getCountryFName(), true).toString());
			country.setCountryNo(countryView.getCountryNo());
			country.setRegionNo(countryView.getRegionNo());
			country.setShortcut(Utils.escapeLiteral(null, countryView.getShortcut(), true).toString());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return country;
	}

}
