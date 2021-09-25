package com.expertsvision.erp.masterdata.costcentergroup.service;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.costcentergroup.dto.CostCenterGroupViewFilter;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroupView;

public interface CostCenterGroupService {
		
	List<CostCenterGroupView> getCostCenterGroupViewList(UsersView loginusersView);
	
	CostCenterGroupView getCostCenterGroupView(UsersView loginUsersView, Integer groupNo);
	
	SinglePage<CostCenterGroupView> getCostCenterGroupViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<CostCenterGroupView> getCostCenterGroupViewLastPage(UsersView loginUsersView);
	
	Long getCostCenterGroupViewSinglePageNo(UsersView loginUsersView, Integer groupNo);
	
	MultiplePages<CostCenterGroupView> getCostCenterGroupViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<CostCenterGroupView> getCostCenterGroupViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   CostCenterGroupViewFilter costCenterGroupViewFilter);
	
	void addCostCenterGroup(UsersView loginUsersView, CostCenterGroupView costCenterGroupView);
	
	void updateCostCenterGroup(UsersView loginUsersView, CostCenterGroupView costCenterGroupView);
	
	void deleteCostCenterGroup(UsersView loginUsersView, Integer groupNo);
	
	PreData preAdd(UsersView loginUsersView);
	
	PreData preModify(UsersView loginUsersView);
	
}
