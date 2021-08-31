package com.expertsvision.erp.masterdata.zone.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.zone.dto.ZoneViewFilter;
import com.expertsvision.erp.masterdata.zone.entity.Zone;
import com.expertsvision.erp.masterdata.zone.entity.ZoneView;


public interface ZoneDAO {
	
	List<ZoneView> getAllZoneViewList();
			
	ZoneView getZoneView(Integer zoneNo);
	
	SinglePage<ZoneView> getZoneViewSinglePage(long pageNo);
	
	SinglePage<ZoneView> getZoneViewLastPage();
	
	Long getUserViewSinglePageNo(Integer zoneNo);
	
	MultiplePages<ZoneView> getZoneViewMultiplePages(long pageNo);
	
	MultiplePages<ZoneView> getZoneViewFilteredMultiplePages(long pageNo, ZoneViewFilter zoneViewFilter);
	
	Object getNextPK();
	
	void addZone(Zone zone);
	
	void updateZone(Zone zone);
	
	void deleteZone(Integer zoneNo);
		
}
