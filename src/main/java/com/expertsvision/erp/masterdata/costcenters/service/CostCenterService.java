package com.expertsvision.erp.masterdata.costcenters.service;

import java.sql.Timestamp;
import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.costcenters.dto.CostCenterViewFilter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterView;

public interface CostCenterService {

	List<CostCenterView> getCostCenterViewList(UsersView loginusersView);

	CostCenterView getCostCenterView(UsersView loginUsersView, Integer ccNo);

	SinglePage<CostCenterView> getCostCenterViewSinglePage(UsersView loginUsersView, long pageNo);

	SinglePage<CostCenterView> getCostCenterViewLastPage(UsersView loginUsersView);

	Long getCostCenterViewSinglePageNo(UsersView loginUsersView, Integer ccNo);

	MultiplePages<CostCenterView> getCostCenterViewMultiplePages(UsersView loginUsersView, long pageNo);

	MultiplePages<CostCenterView> getCostCenterViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CostCenterViewFilter branchesViewFilter);

	Object getNextPK(UsersView loginUsersView, Integer parentCc);

	void addCostCenter(UsersView loginUsersView, CostCenterView costCenterView);

	void updateCostCenter(UsersView loginUsersView, CostCenterView costCenterView);

	void deleteCostCenter(UsersView loginUsersView, Integer ccNo);

	void generateCostCenterPrivsForAllUsers(Integer ccNo, Timestamp currentDate);

	boolean hasSubCostCenters(Integer ccNo);

}
