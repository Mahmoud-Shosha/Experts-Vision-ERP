package com.expertsvision.erp.masterdata.country.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.country.dto.CountryViewFilter;
import com.expertsvision.erp.masterdata.country.entity.Country;
import com.expertsvision.erp.masterdata.country.entity.CountryView;


public interface CountryDAO {
	
	List<CountryView> getAllCountryViewList();
			
	CountryView getCountryView(Integer countryNo);
	
	SinglePage<CountryView> getCountryViewSinglePage(long pageNo);
	
	SinglePage<CountryView> getCountryViewLastPage();
	
	Long getUserViewSinglePageNo(Integer countryNo);
	
	MultiplePages<CountryView> getCountryViewMultiplePages(long pageNo);
	
	MultiplePages<CountryView> getCountryViewFilteredMultiplePages(long pageNo, CountryViewFilter countryViewFilter);
	
	void addCountry(Country country);
	
	void updateCountry(Country country);
	
	void deleteCountry(Integer countryNo);
		
}
