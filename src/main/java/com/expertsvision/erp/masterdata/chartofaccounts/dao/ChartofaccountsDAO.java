package com.expertsvision.erp.masterdata.chartofaccounts.dao;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.chartofaccounts.dto.ChartOfAccountsViewFilter;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrency;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyView;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPriv;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccount;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;


public interface ChartofaccountsDAO {
	
	List<ChartOfAccountsView> getAllChartOfAccountsViewList();
	
	List<AccountsCurrencyView> getAccountsCurrencyViewList(UsersView loginUsersView, Integer accNo);
			
	ChartOfAccountsView getChartOfAccountsView(Integer accNo);
	
	SinglePage<ChartOfAccountsView> getChartOfAccountsViewSinglePage(long pageNo);
	
	SinglePage<ChartOfAccountsView> getChartOfAccountsViewLastPage();
	
	Long getUserViewSinglePageNo(Integer accNo);
	
	MultiplePages<ChartOfAccountsView> getChartOfAccountsViewMultiplePages(long pageNo);
	
	MultiplePages<ChartOfAccountsView> getChartOfAccountsViewFilteredMultiplePages(long pageNo, ChartOfAccountsViewFilter chartOfAccountsViewFilter);
	
	Object getNextPK(Integer parentAcc);
	
	void addChartOfAccount(ChartOfAccount chartOfAccount, List<AccountsCurrency> accountsCurrencyList);
	
	void addAccountsPriv(AccountsPriv accountsPriv);
	
	void updateChartOfAccount(ChartOfAccount chartOfAccount,
			List<AccountsCurrency> accountsCurrencyForAddList,
			List<AccountsCurrency> accountsCurrencyForDeleteList,
			List<AccountsCurrency> accountsCurrencyForUpdateList);
	
	void deleteChartOfAccount(Integer accNo);
	
	boolean hasSubAccounts(Integer accNo);
		
}
