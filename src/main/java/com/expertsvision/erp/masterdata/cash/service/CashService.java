package com.expertsvision.erp.masterdata.cash.service;

import java.sql.Timestamp;
import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.branches.dto.BranchesViewFilter;
import com.expertsvision.erp.masterdata.branches.entity.BranchesView;

public interface CashService {
		
	List<BranchesView> getBranchesViewList(UsersView loginusersView);
	
	BranchesView getBranchesView(UsersView loginUsersView, Integer branchesNo);
	
	SinglePage<BranchesView> getBranchesViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<BranchesView> getBranchesViewLastPage(UsersView loginUsersView);
	
	Long getBranchesViewSinglePageNo(UsersView loginUsersView, Integer branchesNo);
	
	MultiplePages<BranchesView> getBranchesViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<BranchesView> getBranchesViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   BranchesViewFilter branchesViewFilter);
	
	Object getNextPK(UsersView loginUsersView);
	
	void addBranches(UsersView loginUsersView, BranchesView branchesView);
	
	void updateBranches(UsersView loginUsersView, BranchesView branchesView);
	
	void deleteBranches(UsersView loginUsersView, Integer branchesNo);

	void generateBranchesPrivsForAllUsers(Integer branchesNo, Timestamp currentDate);
	
}
