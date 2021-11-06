package com.expertsvision.erp.masterdata.costcenters.dao;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.costcenters.dto.CostCenterViewFilter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterPriv;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterView;

public interface CostCenterDAO {

	List<CostCenterView> getAllCostCenterViewList(UsersView loginUsersView);

	CostCenterView getCostCenterView(UsersView loginUsersView, Integer ccNo);

	SinglePage<CostCenterView> getCostCenterViewSinglePage(UsersView loginUsersView, long pageNo);

	SinglePage<CostCenterView> getCostCenterViewLastPage(UsersView loginUsersView);

	Long getUserViewSinglePageNo(UsersView loginUsersView, Integer costCenterNo);

	MultiplePages<CostCenterView> getCostCenterViewMultiplePages(UsersView loginUsersView, long pageNo);

	MultiplePages<CostCenterView> getCostCenterViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CostCenterViewFilter costCenteresViewFilter);

	Object getNextPK(Integer parentCc);

	void addCostCenter(CostCenter costCenter);

	void addCostCenterPriv(CostCenterPriv costCenteresPriv);

	void updateCostCenter(CostCenter costCenter);

	void deleteCostCenter(Integer ccNo);

	boolean hasSubCostCenters(Integer ccNo);

}
