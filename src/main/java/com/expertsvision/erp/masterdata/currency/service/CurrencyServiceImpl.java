package com.expertsvision.erp.masterdata.currency.service;

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

import com.expertsvision.erp.core.exception.DetailValidationException;
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
import com.expertsvision.erp.masterdata.currency.dao.CurrencyDAO;
import com.expertsvision.erp.masterdata.currency.dto.CurrencyViewFilter;
import com.expertsvision.erp.masterdata.currency.entity.Currency;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyHistoryView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyValuesView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyView;

@Service
public class CurrencyServiceImpl implements CurrencyService {

	@Autowired
	private CurrencyDAO currencyDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Override
	@Transactional
	public List<CurrencyView> getCurrencyViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		List<CurrencyView> currencyView = currencyDAO.getAllCurrencyViewList();
		return currencyView;
	}

	@Override
	@Transactional
	public CurrencyView getCurrencyView(UsersView loginUsersView, String currencyCode) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		CurrencyView currencyView = currencyDAO.getCurrencyView(currencyCode);
		if (currencyView == null) {
			throw new ValidationException("not_exist", "currency_code");
		}
		currencyView.setCurrencyValuesPages(currencyDAO.getCurrencyValuesViewList(currencyCode, 1));
		currencyView.setCurrencyHistoryPages(currencyDAO.getCurrencyHistoryViewList(currencyCode, 1));
		return currencyView;
	}

	@Override
	@Transactional
	public SinglePage<CurrencyView> getCurrencyViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		SinglePage<CurrencyView> singlePage = currencyDAO.getCurrencyViewSinglePage(pageNo);
		if (singlePage.getPage() != null) {
			CurrencyView cv = singlePage.getPage();
			cv.setCurrencyValuesPages(currencyDAO.getCurrencyValuesViewList(cv.getCurrencyCode(), 1));
			cv.setCurrencyHistoryPages(currencyDAO.getCurrencyHistoryViewList(cv.getCurrencyCode(), 1));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CurrencyView> getCurrencyViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		SinglePage<CurrencyView> singlePage = currencyDAO.getCurrencyViewLastPage();
		if (singlePage.getPage() != null) {
			CurrencyView cv = singlePage.getPage();
			cv.setCurrencyValuesPages(currencyDAO.getCurrencyValuesViewList(cv.getCurrencyCode(), 1));
			cv.setCurrencyHistoryPages(currencyDAO.getCurrencyHistoryViewList(cv.getCurrencyCode(), 1));
		}
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCurrencyViewSinglePageNo(UsersView loginUsersView, String currencyCode) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = currencyDAO.getCurrencyViewSinglePageNo(currencyCode);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "currency_code");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CurrencyView> getCurrencyViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CurrencyView> multiplePages = currencyDAO.getCurrencyViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CurrencyHistoryView> getCurrencyHistoryViewMultiplePages(UsersView loginUsersView,
			String currencyCode, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CurrencyHistoryView> multiplePages = currencyDAO.getCurrencyHistoryViewList(currencyCode, pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CurrencyValuesView> getCurrencyCurrencyValuesViewViewMultiplePages(UsersView loginUsersView,
			String currencyCode, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CurrencyValuesView> multiplePages = currencyDAO.getCurrencyValuesViewList(currencyCode, pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CurrencyView> getCurrencyViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CurrencyViewFilter currencyViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CurrencyView> multiplePages = currencyDAO.getCurrencyViewFilteredMultiplePages(pageNo,
				currencyViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addCurrency(UsersView loginUsersView, CurrencyView currencyView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(currencyView.getCurrencyCode(), "currency_code");
		coreValidationService.notBlank(currencyView.getCurrencyCode(), "currency_code");
		coreValidationService.notNull(currencyView.getCurrencyDName(), "name");
		coreValidationService.notBlank(currencyView.getCurrencyDName(), "name");
		if ((currencyView.getCurrencyFName() != null) && currencyView.getCurrencyFName().isBlank())
			currencyView.setCurrencyFName(null);
		coreValidationService.notNull(currencyView.getExchangeRate(), "ex_rate");
		coreValidationService.greaterThanZero(currencyView.getExchangeRate(), "ex_rate");
		coreValidationService.notNull(currencyView.getFractionNo(), "fraction_no");
		coreValidationService.notNull(currencyView.getLocalCurrency(), "local_currency");
		if (currencyView.getMaxExRate() != null)
			coreValidationService.greaterThanZero(currencyView.getMaxExRate(), "max_ex_rate");
		if (currencyView.getMinExRate() != null)
			coreValidationService.greaterThanZero(currencyView.getMinExRate(), "min_ex_rate");
		if (currencyView.getPosExRate() != null)
			coreValidationService.greaterThanZero(currencyView.getPosExRate(), "pos_ex_rate");
		coreValidationService.notNull(currencyView.getFractionDName(), "fraction_name");
		coreValidationService.notBlank(currencyView.getFractionDName(), "fraction_name");
		if ((currencyView.getFractionFName() != null) && currencyView.getFractionFName().isBlank())
			currencyView.setFractionFName(null);
		for (CurrencyValuesView obj : currencyView.getCurrencyValuesPages().getPages()) {
			obj.setCurrencyCode(currencyView.getCurrencyCode());
			switch (obj.getAction()) {
			case "add":
				coreValidationService.notNull(obj.getValue(), "currency_value");
				break;
			default:
				throw new DetailValidationException("invalid", "action", obj.getAction(), "currency_value", obj.getValue());
				break;
			}
		}

		// Database validation
		Currency currency = getCurrencyFromCurrencyView(currencyView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("currency_no", currency.getCurrencyNo());
		if (generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "currency_no");
		conditions.clear();
		conditions.put("currency_d_name", currency.getCurrencyDName());
		if (generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("currency_f_name", currency.getCurrencyFName());
		if (currency.getCurrencyFName() != null && generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("shortcut", currency.getShortcut());
		if (generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "shortcut_name");
		conditions.clear();
		conditions.put("region_no", currency.getRegionNo());
		if (!generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("not_exist", "region_no");
		// Add the currency
		Timestamp add_date = new Timestamp(new Date().getTime());
		currency.setAddDate(add_date);
		currency.setAddUser(loginUsersView.getUserId());
		currency.setModifyDate(null);
		currency.setModifyUser(null);
		currencyDAO.addCurrency(currency);
	}

	@Override
	@Transactional
	public void updateCurrency(UsersView loginUsersView, CurrencyView currencyView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(currencyView.getCurrencyNo(), "currency_no");
		coreValidationService.greaterThanOrEqualZero(currencyView.getCurrencyNo(), "currency_no");
		coreValidationService.notNull(currencyView.getCurrencyDName(), "name");
		coreValidationService.notBlank(currencyView.getCurrencyDName(), "name");
		if ((currencyView.getCurrencyFName() != null) && currencyView.getCurrencyFName().isBlank())
			currencyView.setCurrencyFName(null);
		coreValidationService.notNull(currencyView.getShortcut(), "shortcut_name");
		coreValidationService.notNull(currencyView.getRegionNo(), "region_no");
		// Database validation
		Currency currency = getCurrencyFromCurrencyView(currencyView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("currency_no", currency.getCurrencyNo());
		if (!generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("not_exist", "currency_no");
		conditions.clear();
		conditions.put("currency_d_name", currency.getCurrencyDName());
		String exceptionCondition = null;
		exceptionCondition = " and currency_no != " + currency.getCurrencyNo();
		if (generalDAO.isEntityExist("currency", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("currency_f_name", currency.getCurrencyFName());
		if (currency.getCurrencyFName() != null && generalDAO.isEntityExist("currency", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("region_no", currency.getRegionNo());
		if (!generalDAO.isEntityExist("region", conditions))
			throw new ValidationException("not_exist", "region_no");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		currency.setModifyDate(update_date);
		currency.setModifyUser(loginUsersView.getUserId());
		currencyDAO.updateCurrency(currency);
	}

	@Override
	@Transactional
	public void deleteCurrency(UsersView loginUsersView, Integer currencyNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.CURRENCY);
			} else {
				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(currencyNo, "currency_no");
		coreValidationService.greaterThanOrEqualZero(currencyNo, "currency_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("currency_no", currencyNo);
		if (!generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("not_exist", "currency_no");
		// delete the userscurrency
		try {
			currencyDAO.deleteCurrency(currencyNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "currency");
		}
	}

	public Currency getCurrencyFromCurrencyView(CurrencyView currencyView) {
		Currency currency = new Currency();
		try {
			currency.setAddDate(currencyView.getAddDate());
			currency.setAddUser(currencyView.getAddUser());
			currency.setCurrencyCode(Utils.escapeLiteral(null, currencyView.getCurrencyCode(), true).toString());
			currency.setCurrencyDName(Utils.escapeLiteral(null, currencyView.getCurrencyDName(), true).toString());
			if (currencyView.getCurrencyFName() == null)
				currency.setCurrencyFName(currencyView.getCurrencyFName());
			else
				currency.setCurrencyFName(Utils.escapeLiteral(null, currencyView.getCurrencyFName(), true).toString());
			currency.setExchangeRate(currencyView.getExchangeRate());
			currency.setFractionDName(Utils.escapeLiteral(null, currencyView.getFractionDName(), true).toString());
			if (currencyView.getFractionFName() == null)
				currency.setFractionFName(currencyView.getFractionFName());
			else
				currency.setFractionFName(Utils.escapeLiteral(null, currencyView.getFractionFName(), true).toString());
			currency.setFractionNo(currencyView.getFractionNo());
			currency.setLocalCurrency(currencyView.getLocalCurrency());
			currency.setMaxExRate(currencyView.getMaxExRate());
			currency.setMinExRate(currencyView.getMinExRate());
			currency.setModifyDate(currencyView.getModifyDate());
			currency.setModifyUser(currencyView.getModifyUser());
			currency.setPosExRate(currencyView.getPosExRate());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return currency;
	}

}
