package com.expertsvision.erp.masterdata.country.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.country.dto.CountryViewFilter;
import com.expertsvision.erp.masterdata.country.entity.CountryView;

public interface CountryService {
		
	List<CountryView> getCountryViewList(UsersView loginusersView);
	
	CountryView getCountryView(UsersView loginUsersView, Integer countryNo);
	
	SinglePage<CountryView> getCountryViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<CountryView> getCountryViewLastPage(UsersView loginUsersView);
	
	Long getCountryViewSinglePageNo(UsersView loginUsersView, Integer countryNo);
	
	MultiplePages<CountryView> getCountryViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<CountryView> getCountryViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   CountryViewFilter countryViewFilter);
	
	void addCountry(UsersView loginUsersView, CountryView countryView);
	
	void updateCountry(UsersView loginUsersView, CountryView countryView);
	
	void deleteCountry(UsersView loginUsersView, Integer countryNo);
	
}
