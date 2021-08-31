package com.expertsvision.erp.masterdata.city.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.city.dto.CityViewFilter;
import com.expertsvision.erp.masterdata.city.entity.CityView;

public interface CityService {
		
	List<CityView> getCityViewList(UsersView loginusersView);
	
	CityView getCityView(UsersView loginUsersView, Integer cityNo);
	
	SinglePage<CityView> getCityViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<CityView> getCityViewLastPage(UsersView loginUsersView);
	
	Long getCityViewSinglePageNo(UsersView loginUsersView, Integer cityNo);
	
	MultiplePages<CityView> getCityViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<CityView> getCityViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   CityViewFilter cityViewFilter);
	
	Object getNextPK(UsersView loginUsersView);
	
	void addCity(UsersView loginUsersView, CityView cityView);
	
	void updateCity(UsersView loginUsersView, CityView cityView);
	
	void deleteCity(UsersView loginUsersView, Integer cityNo);
	
}
