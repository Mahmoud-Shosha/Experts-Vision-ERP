package com.expertsvision.erp.masterdata.province.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.province.dto.ProvinceViewFilter;
import com.expertsvision.erp.masterdata.province.entity.ProvinceView;

public interface ProvinceService {
		
	List<ProvinceView> getProvinceViewList(UsersView loginusersView);
	
	ProvinceView getProvinceView(UsersView loginUsersView, Integer provinceNo);
	
	SinglePage<ProvinceView> getProvinceViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<ProvinceView> getProvinceViewLastPage(UsersView loginUsersView);
	
	Long getProvinceViewSinglePageNo(UsersView loginUsersView, Integer provinceNo);
	
	MultiplePages<ProvinceView> getProvinceViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<ProvinceView> getProvinceViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   ProvinceViewFilter provinceViewFilter);
	Object getNextPK(UsersView loginUsersView);
	
	void addProvince(UsersView loginUsersView, ProvinceView provinceView);
	
	void updateProvince(UsersView loginUsersView, ProvinceView provinceView);
	
	void deleteProvince(UsersView loginUsersView, Integer provinceNo);
	
}
