package com.expertsvision.erp.masterdata.currency.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.currency.dto.CurrencyViewFilter;
import com.expertsvision.erp.masterdata.currency.entity.Currency;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyHistoryView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyValuesView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyView;


public interface CurrencyDAO {
	
	List<CurrencyView> getAllCurrencyViewList();
	
	MultiplePages<CurrencyValuesView> getCurrencyValuesViewList(String currencyCode, long pageNo);
	
	MultiplePages<CurrencyHistoryView> getCurrencyHistoryViewList(String currencyCode, long pageNo);
			
	CurrencyView getCurrencyView(String currencyCode);
	
	SinglePage<CurrencyView> getCurrencyViewSinglePage(long pageNo);
	
	SinglePage<CurrencyView> getCurrencyViewLastPage();
	
	Long getCurrencyViewSinglePageNo(String currencyCode);
	
	MultiplePages<CurrencyView> getCurrencyViewMultiplePages(long pageNo);
	
	MultiplePages<CurrencyView> getCurrencyViewFilteredMultiplePages(long pageNo, CurrencyViewFilter currencyViewFilter);
		
	void addCurrency(Currency currency);
	
	void updateCurrency(Currency currency);
	
	void deleteCurrency(String currencyCode);
		
}
