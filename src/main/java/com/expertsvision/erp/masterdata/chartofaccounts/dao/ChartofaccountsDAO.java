package com.expertsvision.erp.masterdata.chartofaccounts.dao;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.branches.dto.BranchesViewFilter;
import com.expertsvision.erp.masterdata.branches.entity.Branch;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.branches.entity.BranchesView;


public interface ChartofaccountsDAO {
	
	List<BranchesView> getAllBranchViewList(UsersView loginUsersView);
			
	BranchesView getBranchView(UsersView loginUsersView, Integer branchNo);
	
	SinglePage<BranchesView> getBranchViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<BranchesView> getBranchViewLastPage(UsersView loginUsersView);
	
	Long getUserViewSinglePageNo(UsersView loginUsersView, Integer branchNo);
	
	MultiplePages<BranchesView> getBranchViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<BranchesView> getBranchViewFilteredMultiplePages(UsersView loginUsersView ,long pageNo, BranchesViewFilter branchesViewFilter);
	
	Object getNextPK();
	
	void addBranch(Branch branch);
	
	void addBranchesPriv(BranchesPriv branchesPriv);
	
	void updateBranch(Branch branch);
	
	void deleteBranch(Integer branchNo);
		
}
