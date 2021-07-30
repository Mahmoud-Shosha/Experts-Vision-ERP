package com.expertsvision.erp.masterdata.province.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.province.dto.ProvinceViewFilter;
import com.expertsvision.erp.masterdata.province.entity.Province;
import com.expertsvision.erp.masterdata.province.entity.ProvinceView;


public interface ProvinceDAO {
	
	List<ProvinceView> getAllProvinceViewList();
			
	ProvinceView getProvinceView(Integer provinceNo);
	
	SinglePage<ProvinceView> getProvinceViewSinglePage(long pageNo);
	
	SinglePage<ProvinceView> getProvinceViewLastPage();
	
	Long getUserViewSinglePageNo(Integer provinceNo);
	
	MultiplePages<ProvinceView> getProvinceViewMultiplePages(long pageNo);
	
	MultiplePages<ProvinceView> getProvinceViewFilteredMultiplePages(long pageNo, ProvinceViewFilter provinceViewFilter);
	
	void addProvince(Province province);
	
	void updateProvince(Province province);
	
	void deleteProvince(Integer provinceNo);
		
}
