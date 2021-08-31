package com.expertsvision.erp.masterdata.city.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.city.dto.CityViewFilter;
import com.expertsvision.erp.masterdata.city.entity.City;
import com.expertsvision.erp.masterdata.city.entity.CityView;


public interface CityDAO {
	
	List<CityView> getAllCityViewList();
			
	CityView getCityView(Integer cityNo);
	
	SinglePage<CityView> getCityViewSinglePage(long pageNo);
	
	SinglePage<CityView> getCityViewLastPage();
	
	Long getUserViewSinglePageNo(Integer cityNo);
	
	MultiplePages<CityView> getCityViewMultiplePages(long pageNo);
	
	MultiplePages<CityView> getCityViewFilteredMultiplePages(long pageNo, CityViewFilter cityViewFilter);
	
	Object getNextPK();
	
	void addCity(City city);
	
	void updateCity(City city);
	
	void deleteCity(Integer cityNo);
		
}
