package com.expertsvision.erp.masterdata.chartofaccounts.service;

import java.sql.Timestamp;
import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.chartofaccounts.dto.ChartOfAccountsViewFilter;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;

public interface ChartofaccountsService {
		
	List<ChartOfAccountsView> getChartOfAccountsViewList(UsersView loginusersView);
	
	ChartOfAccountsView getChartOfAccountsView(UsersView loginUsersView, Integer accNo);
	
	SinglePage<ChartOfAccountsView> getChartOfAccountsViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<ChartOfAccountsView> getChartOfAccountsViewLastPage(UsersView loginUsersView);
	
	Long getChartOfAccountsViewSinglePageNo(UsersView loginUsersView, Integer accNo);
	
	MultiplePages<ChartOfAccountsView> getChartOfAccountsViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<ChartOfAccountsView> getChartOfAccountsViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   ChartOfAccountsViewFilter chartOfAccountsViewFilter);
	
	Object getNextPK(UsersView loginUsersView, Integer parentAcc);
	
	void addChartOfAccount(UsersView loginUsersView, ChartOfAccountsView chartOfAccountsView);
	
	void updateChartOfAccount(UsersView loginUsersView, ChartOfAccountsView chartOfAccountsView);
	
	void deleteChartOfAccount(UsersView loginUsersView, Integer accNo);

	void generateBranchesPrivsForAllUsers(Integer accNo, Timestamp currentDate);

}
