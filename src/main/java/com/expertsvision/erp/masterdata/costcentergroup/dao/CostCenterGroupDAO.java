package com.expertsvision.erp.masterdata.costcentergroup.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.costcentergroup.dto.CostCenterGroupViewFilter;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroup;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroupView;


public interface CostCenterGroupDAO {
	
	List<CostCenterGroupView> getAllCostCenterGroupViewList();
			
	CostCenterGroupView getCostCenterGroupView(Integer groupNo);
	
	SinglePage<CostCenterGroupView> getCostCenterGroupViewSinglePage(long pageNo);
	
	SinglePage<CostCenterGroupView> getCostCenterGroupViewLastPage();
	
	Long getUserViewSinglePageNo(Integer groupNo);
	
	MultiplePages<CostCenterGroupView> getCostCenterGroupViewMultiplePages(long pageNo);
	
	MultiplePages<CostCenterGroupView> getCostCenterGroupViewFilteredMultiplePages(long pageNo, CostCenterGroupViewFilter costCenterGroupViewFilter);
	
	Object getNextPK();
	
	void addCostCenterGroup(CostCenterGroup costCenterGroup);
	
	void updateCostCenterGroup(CostCenterGroup costCenterGroup);
	
	void deleteCostCenterGroup(Integer groupNo);
		
}
