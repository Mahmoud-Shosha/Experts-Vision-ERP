package com.expertsvision.erp.masterdata.zone.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.zone.dto.ZoneViewFilter;
import com.expertsvision.erp.masterdata.zone.entity.ZoneView;

public interface ZoneService {
		
	List<ZoneView> getZoneViewList(UsersView loginusersView);
	
	ZoneView getZoneView(UsersView loginUsersView, Integer zoneNo);
	
	SinglePage<ZoneView> getZoneViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<ZoneView> getZoneViewLastPage(UsersView loginUsersView);
	
	Long getZoneViewSinglePageNo(UsersView loginUsersView, Integer zoneNo);
	
	MultiplePages<ZoneView> getZoneViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<ZoneView> getZoneViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   ZoneViewFilter zoneViewFilter);
	
	Object getNextPK(UsersView loginUsersView);
	
	void addZone(UsersView loginUsersView, ZoneView zoneView);
	
	void updateZone(UsersView loginUsersView, ZoneView zoneView);
	
	void deleteZone(UsersView loginUsersView, Integer zoneNo);
	
}
