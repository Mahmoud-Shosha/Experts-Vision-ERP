package com.expertsvision.erp.masterdata.region.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.region.dto.RegionViewFilter;
import com.expertsvision.erp.masterdata.region.entity.RegionView;

public interface RegionService {
		
	List<RegionView> getRegionViewList(UsersView loginusersView);
	
	RegionView getRegionView(UsersView loginUsersView, Integer regionNo);
	
	SinglePage<RegionView> getRegionViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<RegionView> getRegionViewLastPage(UsersView loginUsersView);
	
	Long getRegionViewSinglePageNo(UsersView loginUsersView, Integer regionNo);
	
	MultiplePages<RegionView> getRegionViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<RegionView> getRegionViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   RegionViewFilter regionViewFilter);
	Object getNextPK(UsersView loginUsersView);
	
	void addRegion(UsersView loginUsersView, RegionView regionView);
	
	void updateRegion(UsersView loginUsersView, RegionView regionView);
	
	void deleteRegion(UsersView loginUsersView, Integer regionNo);
	
}
