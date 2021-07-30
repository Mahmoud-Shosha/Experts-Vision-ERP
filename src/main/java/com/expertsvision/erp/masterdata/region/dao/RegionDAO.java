package com.expertsvision.erp.masterdata.region.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.region.dto.RegionViewFilter;
import com.expertsvision.erp.masterdata.region.entity.Region;
import com.expertsvision.erp.masterdata.region.entity.RegionView;


public interface RegionDAO {
	
	List<RegionView> getAllRegionViewList();
			
	RegionView getRegionView(Integer regionNo);
	
	SinglePage<RegionView> getRegionViewSinglePage(long pageNo);
	
	SinglePage<RegionView> getRegionViewLastPage();
	
	Long getUserViewSinglePageNo(Integer regionNo);
	
	MultiplePages<RegionView> getRegionViewMultiplePages(long pageNo);
	
	MultiplePages<RegionView> getRegionViewFilteredMultiplePages(long pageNo, RegionViewFilter regionViewFilter);
	
	void addRegion(Region region);
	
	void updateRegion(Region region);
	
	void deleteRegion(Integer regionNo);
		
}
