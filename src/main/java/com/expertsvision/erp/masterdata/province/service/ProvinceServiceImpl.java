package com.expertsvision.erp.masterdata.province.service;

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
import com.expertsvision.erp.masterdata.province.dao.ProvinceDAO;
import com.expertsvision.erp.masterdata.province.dto.ProvinceViewFilter;
import com.expertsvision.erp.masterdata.province.entity.Province;
import com.expertsvision.erp.masterdata.province.entity.ProvinceView;

@Service
public class ProvinceServiceImpl implements ProvinceService {

	@Autowired
	private ProvinceDAO provinceDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<ProvinceView> getProvinceViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
		}
		// Return requested data
		List<ProvinceView> provinceView = provinceDAO.getAllProvinceViewList();
		return provinceView;
	}

	@Override
	@Transactional
	public ProvinceView getProvinceView(UsersView loginUsersView, Integer provinceNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
		}
		// Return requested data
		ProvinceView provinceView = provinceDAO.getProvinceView(provinceNo);
		if (provinceView == null) {
			throw new ValidationException("not_exist", "province_no");
		}
		return provinceView;
	}

	@Override
	@Transactional
	public SinglePage<ProvinceView> getProvinceViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<ProvinceView> singlePage = provinceDAO.getProvinceViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<ProvinceView> getProvinceViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<ProvinceView> singlePage = provinceDAO.getProvinceViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getProvinceViewSinglePageNo(UsersView loginUsersView, Integer provinceNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = provinceDAO.getUserViewSinglePageNo(provinceNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "province_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<ProvinceView> getProvinceViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<ProvinceView> multiplePages = provinceDAO.getProvinceViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<ProvinceView> getProvinceViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			ProvinceViewFilter provinceViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<ProvinceView> multiplePages = provinceDAO.getProvinceViewFilteredMultiplePages(pageNo,
				provinceViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addProvince(UsersView loginUsersView, ProvinceView provinceView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(provinceView.getProvinceNo(), "province_no");
		coreValidationService.greaterThanOrEqualZero(provinceView.getProvinceNo(), "province_no");
		coreValidationService.notNull(provinceView.getProvinceDName(), "name");
		coreValidationService.notBlank(provinceView.getProvinceDName(), "name");
		if ((provinceView.getProvinceFName() != null) && provinceView.getProvinceFName().isBlank())
			provinceView.setProvinceFName(null);
		coreValidationService.notNull(provinceView.getShortcut(), "shortcut_name");
		coreValidationService.notNull(provinceView.getCountryNo(), "country_no");
		// Database validation
		Province province = getProvinceFromProvinceView(provinceView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("province_no", province.getProvinceNo());
		if (generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("already_exist", "province_no");
		conditions.clear();
		conditions.put("province_d_name", province.getProvinceDName());
		if (generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("province_f_name", province.getProvinceFName());
		if (province.getProvinceFName() != null && generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut", province.getShortcut());
		if (generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("country_no", province.getCountryNo());
		if (!generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");
		// Add the province
		Timestamp add_date = new Timestamp(new Date().getTime());
		province.setAddDate(add_date);
		province.setAddUser(loginUsersView.getUserId());
		province.setModifyDate(null);
		province.setModifyUser(null);
		provinceDAO.addProvince(province);
	}

	@Override
	@Transactional
	public void updateProvince(UsersView loginUsersView, ProvinceView provinceView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(provinceView.getProvinceNo(), "province_no");
		coreValidationService.greaterThanOrEqualZero(provinceView.getProvinceNo(), "province_no");
		coreValidationService.notNull(provinceView.getProvinceDName(), "name");
		coreValidationService.notBlank(provinceView.getProvinceDName(), "name");
		if ((provinceView.getProvinceFName() != null) && provinceView.getProvinceFName().isBlank())
			provinceView.setProvinceFName(null);
		coreValidationService.notNull(provinceView.getShortcut(), "shortcut_name");
		coreValidationService.notNull(provinceView.getCountryNo(), "country_no");
		// Database validation
		Province province = getProvinceFromProvinceView(provinceView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("province_no", province.getProvinceNo());
		if (!generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("not_exist", "province_no");
		conditions.clear();
		conditions.put("province_d_name", province.getProvinceDName());
		String exceptionCondition = null;
		exceptionCondition = " and province_no != " + province.getProvinceNo();
		if (generalDAO.isEntityExist("province", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("province_f_name", province.getProvinceFName());
		if (province.getProvinceFName() != null
				&& generalDAO.isEntityExist("province", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("country_no", province.getCountryNo());
		if (!generalDAO.isEntityExist("country", conditions))
			throw new ValidationException("not_exist", "country_no");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		province.setModifyDate(update_date);
		province.setModifyUser(loginUsersView.getUserId());
		provinceDAO.updateProvince(province);
	}

	@Override
	@Transactional
	public void deleteProvince(UsersView loginUsersView, Integer provinceNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.GEOGRAPHICAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.GEOGRAPHICAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.PROVINCE);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.GEOGRAPHICAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.PROVINCE, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(provinceNo, "province_no");
		coreValidationService.greaterThanOrEqualZero(provinceNo, "province_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("province_no", provinceNo);
		if (!generalDAO.isEntityExist("province", conditions))
			throw new ValidationException("not_exist", "province_no");
		// delete the usersprovince
		try {
			provinceDAO.deleteProvince(provinceNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "province");
		}
	}

	public Province getProvinceFromProvinceView(ProvinceView provinceView) {
		Province province = new Province();
		try {
			province.setAddDate(provinceView.getAddDate());
			province.setAddUser(provinceView.getAddUser());
			province.setModifyDate(provinceView.getModifyDate());
			province.setModifyUser(provinceView.getModifyUser());
			province.setProvinceDName(Utils.escapeLiteral(null, provinceView.getProvinceDName(), true).toString());
			if (provinceView.getProvinceFName() == null)
				province.setProvinceFName(provinceView.getProvinceFName());
			else
				province.setProvinceFName(Utils.escapeLiteral(null, provinceView.getProvinceFName(), true).toString());
			province.setProvinceNo(provinceView.getProvinceNo());
			province.setCountryNo(provinceView.getCountryNo());
			province.setShortcut(Utils.escapeLiteral(null, provinceView.getShortcut(), true).toString());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return province;
	}

}
