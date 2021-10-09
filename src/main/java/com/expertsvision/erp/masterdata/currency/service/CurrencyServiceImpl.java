package com.expertsvision.erp.masterdata.currency.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.DetailValidationException;
import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.currency.dao.CurrencyDAO;
import com.expertsvision.erp.masterdata.currency.dto.CurrencyViewFilter;
import com.expertsvision.erp.masterdata.currency.entity.Currency;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyHistory;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyValue;
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
		currencyView.setCurrencyValuesList(currencyDAO.getCurrencyValuesViewList(currencyCode));
		currencyView.setCurrencyHistoryList(currencyDAO.getCurrencyHistoryViewList(currencyCode));
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
			cv.setCurrencyValuesList(currencyDAO.getCurrencyValuesViewList(cv.getCurrencyCode()));
			cv.setCurrencyHistoryList(currencyDAO.getCurrencyHistoryViewList(cv.getCurrencyCode()));
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
			cv.setCurrencyValuesList(currencyDAO.getCurrencyValuesViewList(cv.getCurrencyCode()));
			cv.setCurrencyHistoryList(currencyDAO.getCurrencyHistoryViewList(cv.getCurrencyCode()));
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

//	@Override
//	@Transactional
//	public MultiplePages<CurrencyHistoryView> getCurrencyHistoryViewMultiplePages(UsersView loginUsersView,
//			String currencyCode, long pageNo) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.CURRENCY);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
//		}
//		// Return requested data
//		MultiplePages<CurrencyHistoryView> multiplePages = currencyDAO.getCurrencyHistoryViewList(currencyCode, pageNo);
//		return multiplePages;
//	}

//	@Override
//	@Transactional
//	public MultiplePages<CurrencyValuesView> getCurrencyValuesViewMultiplePages(UsersView loginUsersView,
//			String currencyCode, long pageNo) {
//		// Check module, form, privileges
//		if (!loginUsersView.getSuperAdmin()) {
//			if (loginUsersView.getAdminUser()) {
//				coreValidationService.activeModule(Forms.CURRENCY);
//			} else {
//				coreValidationService.activeModuleAndForm(Forms.CURRENCY);
//			}
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.INCLUDE);
//			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.CURRENCY, FormsActions.VIEW);
//		}
//		// Return requested data
//		MultiplePages<CurrencyValuesView> multiplePages = currencyDAO.getCurrencyValuesViewList(currencyCode, pageNo);
//		return multiplePages;
//	}

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
		Timestamp add_date = new Timestamp(new Date().getTime());
		List<CurrencyValue> CurrencyValueForAddList = new ArrayList<>();
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
		if (currencyView.getLocalCurrency()) {
			currencyView.setMaxExRate(null);
			currencyView.setMinExRate(null);
			currencyView.setPosExRate(BigDecimal.ONE);
			currencyView.setExchangeRate(BigDecimal.ONE);
		} else {
			if (currencyView.getMaxExRate() != null) {
				coreValidationService.greaterThanZero(currencyView.getMaxExRate(), "max_ex_rate");
				coreValidationService.greaterThanOrEqual(currencyView.getMaxExRate(), currencyView.getExchangeRate(),
						"max_ex_rate", currencyView.getMaxExRate(), "ex_rate", currencyView.getExchangeRate());
			}
			if (currencyView.getMinExRate() != null) {
				coreValidationService.greaterThanZero(currencyView.getMinExRate(), "min_ex_rate");
				coreValidationService.lessThanOrEqual(currencyView.getMinExRate(), currencyView.getExchangeRate(),
						"min_ex_rate", currencyView.getMinExRate(), "ex_rate", currencyView.getExchangeRate());
			}
			if (currencyView.getPosExRate() != null)
				coreValidationService.greaterThanZero(currencyView.getPosExRate(), "pos_ex_rate");
		}
		coreValidationService.notNull(currencyView.getFractionDName(), "fraction_name");
		coreValidationService.notBlank(currencyView.getFractionDName(), "fraction_name");
		if ((currencyView.getFractionFName() != null) && currencyView.getFractionFName().isBlank())
			currencyView.setFractionFName(null);
		// Non-database validation for details
		if (currencyView.getCurrencyValuesList() != null) {
			for (CurrencyValuesView obj : currencyView.getCurrencyValuesList()) {
				obj.setCurrencyCode(currencyView.getCurrencyCode());
				coreValidationService.notNull(obj.getValue(), "currency_value");
				switch (obj.getAction()) {
				case "add":
					break;
				default:
					throw new DetailValidationException("invalid_detail", "action", obj.getAction(), "currency_value", obj.getValue());
				}
			}
		}
		// Database validation
		Currency currency = getCurrencyFromCurrencyView(currencyView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("currency_code", currency.getCurrencyCode());
		if (generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "currency_code");
		conditions.clear();
		conditions.put("currency_d_name", currency.getCurrencyDName());
		if (generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("currency_f_name", currency.getCurrencyFName());
		if (currency.getCurrencyFName() != null && generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("fraction_d_name", currency.getFractionDName());
		if (generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "fraction_name");
		conditions.clear();
		conditions.put("fraction_f_name", currency.getFractionFName());
		if (currency.getFractionFName() != null && generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("already_exist", "fraction_foreign_name");
		conditions.clear();
		if (currency.getLocalCurrency() && getLocalCurrency() != null) {
			throw new ValidationException("already_exist", "local_currency");
		}
		// Database validation for details
		if (currencyView.getCurrencyValuesList() != null) {
			Set<Integer> valuesSetForAdd = new HashSet<>();
			Set<Integer> DBValuesSetForAdd;
			Map<String, Object> parameters = new HashMap<>();
			CurrencyValue currencyValue;
			parameters.put("currencyCode", currencyView.getCurrencyCode());
			for (CurrencyValuesView obj : currencyView.getCurrencyValuesList()) {
				currencyValue = getCurrencyValueFromCurrencyValueView(obj);
				currencyValue.setAddDate(add_date);
				currencyValue.setAddUser(loginUsersView.getUserId());
				currencyValue.setModifyDate(null);
				currencyValue.setModifyUser(null);
				CurrencyValueForAddList.add(currencyValue);
				if (valuesSetForAdd.contains(obj.getValue())) {
					throw new DetailValidationException("already_exist_detail", "currency_value",
							obj.getValue(), "currency_code", currencyView.getCurrencyCode());
				} else {
					valuesSetForAdd.add(obj.getValue());
				}
			}
			DBValuesSetForAdd = generalDAO.getThemIfExist("currency_values",
					"currency_code = :currencyCode", parameters, "value", valuesSetForAdd);
			if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
				throw new DetailValidationException("already_exist_detail", "currency_value",
						DBValuesSetForAdd.toArray()[0], "currency_code", currencyView.getCurrencyCode());
		}
		// Add the currency
		currency.setAddDate(add_date);
		currency.setAddUser(loginUsersView.getUserId());
		currency.setModifyDate(null);
		currency.setModifyUser(null);
		currencyDAO.addCurrency(currency, CurrencyValueForAddList);
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
		Timestamp update_date = new Timestamp(new Date().getTime());
		List<CurrencyValue> CurrencyValueForAddList = new ArrayList<>();
		List<CurrencyValue> CurrencyValueForDeleteList = new ArrayList<>();
		CurrencyHistory CurrencyHistoryForAdd = null;
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
		if (currencyView.getLocalCurrency()) {
			currencyView.setMaxExRate(null);
			currencyView.setMinExRate(null);
			currencyView.setPosExRate(BigDecimal.ONE);
			currencyView.setExchangeRate(BigDecimal.ONE);
		} else {
			if (currencyView.getMaxExRate() != null) {
				coreValidationService.greaterThanZero(currencyView.getMaxExRate(), "max_ex_rate");
				coreValidationService.greaterThanOrEqual(currencyView.getMaxExRate(), currencyView.getExchangeRate(),
						"max_ex_rate", currencyView.getMaxExRate(), "ex_rate", currencyView.getExchangeRate());
			}
			if (currencyView.getMinExRate() != null) {
				coreValidationService.greaterThanZero(currencyView.getMinExRate(), "min_ex_rate");
				coreValidationService.lessThanOrEqual(currencyView.getMinExRate(), currencyView.getExchangeRate(),
						"min_ex_rate", currencyView.getMinExRate(), "ex_rate", currencyView.getExchangeRate());
			}
			if (currencyView.getPosExRate() != null)
				coreValidationService.greaterThanZero(currencyView.getPosExRate(), "pos_ex_rate");
		}
		coreValidationService.notNull(currencyView.getFractionDName(), "fraction_name");
		coreValidationService.notBlank(currencyView.getFractionDName(), "fraction_name");
		if ((currencyView.getFractionFName() != null) && currencyView.getFractionFName().isBlank())
			currencyView.setFractionFName(null);
		// Non-database validation for details
		if (currencyView.getCurrencyValuesList() != null) {
			for (CurrencyValuesView obj : currencyView.getCurrencyValuesList()) {
				obj.setCurrencyCode(currencyView.getCurrencyCode());
				coreValidationService.notNull(obj.getValue(), "currency_value");
				switch (obj.getAction()) {
				case "add":
				case "delete":
					break;
				default:
					throw new DetailValidationException("invalid_detail", "action", obj.getAction(), "currency_value", obj.getValue());
				}
			}
		}
		// Database validation
		Currency currency = getCurrencyFromCurrencyView(currencyView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("currency_code", currency.getCurrencyCode());
		if (!generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("not_exist", "currency_code");
		conditions.clear();
		conditions.put("currency_d_name", currency.getCurrencyDName());
		String exceptionCondition = null;
		exceptionCondition = " and currency_code != '" + currency.getCurrencyCode() + "'";
		if (generalDAO.isEntityExist("currency", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("currency_f_name", currency.getCurrencyFName());
		if (currency.getCurrencyFName() != null && generalDAO.isEntityExist("currency", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("fraction_d_name", currency.getFractionDName());
		if (generalDAO.isEntityExist("currency", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "fraction_name");
		conditions.clear();
		conditions.put("fraction_f_name", currency.getFractionFName());
		if (currency.getFractionFName() != null && generalDAO.isEntityExist("currency", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "fraction_foreign_name");
		conditions.clear();
		if (currency.getLocalCurrency()) {
			CurrencyView localCurrency = getLocalCurrency();
			if ((localCurrency != null) && (!localCurrency.getCurrencyCode().equals(currency.getCurrencyCode())))
				throw new ValidationException("already_exist", "local_currency");
		}
		CurrencyView DBCurrencyView = currencyDAO.getCurrencyView(currencyView.getCurrencyCode());
		if (currencyView.getExchangeRate().compareTo(DBCurrencyView.getExchangeRate()) != 0) {
			CurrencyHistoryForAdd = new CurrencyHistory();
			CurrencyHistoryForAdd.setCurrencyCode(DBCurrencyView.getCurrencyCode());
			CurrencyHistoryForAdd.setExchangeRate(DBCurrencyView.getExchangeRate());
			CurrencyHistoryForAdd.setMaxExRate(DBCurrencyView.getMaxExRate());
			CurrencyHistoryForAdd.setMinExRate(DBCurrencyView.getMinExRate());
			CurrencyHistoryForAdd.setModifyDate(update_date);
			CurrencyHistoryForAdd.setModifyUser(loginUsersView.getUserId());
		}
		// Database validation for details
		if (currencyView.getCurrencyValuesList() != null) {
			Set<Integer> valuesSetForAdd = new HashSet<>();
			Set<Integer> DBValuesSetForAdd;
			Set<Integer> valuesSetForModifyOrDelete = new HashSet<>();
			Set<Integer> DBValuesSetForModifyOrDelete;
			Map<String, Object> parameters = new HashMap<>();
			CurrencyValue currencyValue;
			parameters.put("currencyCode", currencyView.getCurrencyCode());
			for (CurrencyValuesView obj : currencyView.getCurrencyValuesList()) {
				currencyValue = getCurrencyValueFromCurrencyValueView(obj);
				switch (obj.getAction()) {
				case "add":
					currencyValue.setAddDate(update_date);
					currencyValue.setAddUser(loginUsersView.getUserId());
					currencyValue.setModifyDate(null);
					currencyValue.setModifyUser(null);
					CurrencyValueForAddList.add(currencyValue);
					if (valuesSetForAdd.contains(obj.getValue())) {
						throw new DetailValidationException("already_exist_detail", "currency_value",
								obj.getValue(), "currency_code", currencyView.getCurrencyCode());
					} else {
						valuesSetForAdd.add(obj.getValue());
					}
					break;
				case "delete":
					CurrencyValueForDeleteList.add(currencyValue);
					valuesSetForModifyOrDelete.add(currencyValue.getValue());
					break;
				}
			}
			if (!valuesSetForAdd.isEmpty()) {
				DBValuesSetForAdd = generalDAO.getThemIfExist("currency_values",
						"currency_code = :currencyCode", parameters, "value", valuesSetForAdd);
				if (DBValuesSetForAdd != null && !DBValuesSetForAdd.isEmpty())
					throw new DetailValidationException("already_exist_detail", "currency_value",
							DBValuesSetForAdd.toArray()[0], "currency_code", currencyView.getCurrencyCode());
			}
			if (!valuesSetForModifyOrDelete.isEmpty()) {
				DBValuesSetForModifyOrDelete = generalDAO.getThemIfExist("currency_values",
						"currency_code = :currencyCode", parameters, "value", valuesSetForModifyOrDelete);
				if (DBValuesSetForModifyOrDelete != null) {
					for (Integer i : valuesSetForModifyOrDelete) {
						if (!DBValuesSetForModifyOrDelete.contains(i))
							throw new DetailValidationException("not_exist_detail", "currency_value",
									i, "currency_code", currencyView.getCurrencyCode());
					}
				}
			}
			
		}
		// Update the currency
		currency.setModifyDate(update_date);
		currency.setModifyUser(loginUsersView.getUserId());
		currencyDAO.updateCurrency(currency, CurrencyValueForAddList,
				CurrencyValueForDeleteList, CurrencyHistoryForAdd);
	}

	@Override
	@Transactional
	public void deleteCurrency(UsersView loginUsersView, String currencyCode) {
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
		coreValidationService.notNull(currencyCode, "currency_code");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("currency_code", currencyCode);
		if (!generalDAO.isEntityExist("currency", conditions))
			throw new ValidationException("not_exist", "currency_code");
		// delete the userscurrency
		try {
			currencyDAO.deleteCurrency(currencyCode);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "currency_code");
		}
	}
	
	@Override
	@Transactional
	public PreData preAdd(UsersView loginUsersView) {
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
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		CurrencyView localCurrencyView = getLocalCurrency();
		if (localCurrencyView != null) {
			readOnly.add("local_currency");
			info.put("local_currency", localCurrencyView);
		}
		// return the data
		return preData;
	}
	
	@Override
	@Transactional
	public PreData preModify(UsersView loginUsersView) {
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
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		CurrencyView localCurrencyView = getLocalCurrency();
		if (localCurrencyView != null) {
			readOnly.add("local_currency");
			info.put("local_currency", localCurrencyView);
		}
		// return the data
		return preData;
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
	
	public CurrencyValue getCurrencyValueFromCurrencyValueView(CurrencyValuesView currencyValuesView) {
		CurrencyValue currencyValue = new CurrencyValue();
		try {
			currencyValue.setAddDate(currencyValuesView.getAddDate());
			currencyValue.setAddUser(currencyValuesView.getAddUser());
			currencyValue.setCurrencyCode(Utils.escapeLiteral(null, currencyValuesView.getCurrencyCode(), true).toString());
			currencyValue.setModifyDate(currencyValuesView.getModifyDate());
			currencyValue.setModifyUser(currencyValuesView.getModifyUser());
			currencyValue.setValue(currencyValuesView.getValue());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return currencyValue;
	}
	
	@Override
	public CurrencyView getLocalCurrency() {
		CurrencyView currencyView = currencyDAO.getLocalCurrency();
		return currencyView;
	}

}
