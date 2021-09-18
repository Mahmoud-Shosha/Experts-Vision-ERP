package com.expertsvision.erp.masterdata.currency.service;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.currency.dto.CurrencyViewFilter;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyHistoryView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyValuesView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyView;

public interface CurrencyService {

	List<CurrencyView> getCurrencyViewList(UsersView loginusersView);

	CurrencyView getCurrencyView(UsersView loginUsersView, String currencyCode);

	SinglePage<CurrencyView> getCurrencyViewSinglePage(UsersView loginUsersView, long pageNo);

	SinglePage<CurrencyView> getCurrencyViewLastPage(UsersView loginUsersView);

	Long getCurrencyViewSinglePageNo(UsersView loginUsersView, String currencyCode);

	MultiplePages<CurrencyView> getCurrencyViewMultiplePages(UsersView loginUsersView, long pageNo);

	MultiplePages<CurrencyHistoryView> getCurrencyHistoryViewMultiplePages(UsersView loginUsersView,
			String currencyCode, long pageNo);

	MultiplePages<CurrencyValuesView> getCurrencyCurrencyValuesViewViewMultiplePages(UsersView loginUsersView,
			String currencyCode, long pageNo);

	MultiplePages<CurrencyView> getCurrencyViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CurrencyViewFilter currencyViewFilter);

	void addCurrency(UsersView loginUsersView, CurrencyView currencyView);

	void updateCurrency(UsersView loginUsersView, CurrencyView currencyView);

	void deleteCurrency(UsersView loginUsersView, String currencyCode);
	
	PreData preAdd(UsersView loginUsersView);
	
	PreData preModify(UsersView loginUsersView);
	
	CurrencyView getLocalCurrency();

}
