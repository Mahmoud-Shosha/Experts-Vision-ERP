package com.expertsvision.erp.masterdata.accountsgroup.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.accountsgroup.dto.AccountsGroupViewFilter;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroup;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroupView;


public interface AccountsGroupDAO {
	
	List<AccountsGroupView> getAllAccountsGroupViewList();
			
	AccountsGroupView getAccountsGroupView(Integer groupNo);
	
	SinglePage<AccountsGroupView> getAccountsGroupViewSinglePage(long pageNo);
	
	SinglePage<AccountsGroupView> getAccountsGroupViewLastPage();
	
	Long getUserViewSinglePageNo(Integer groupNo);
	
	MultiplePages<AccountsGroupView> getAccountsGroupViewMultiplePages(long pageNo);
	
	MultiplePages<AccountsGroupView> getAccountsGroupViewFilteredMultiplePages(long pageNo, AccountsGroupViewFilter accountsGroupViewFilter);
	
	Object getNextPK();
	
	void addAccountsGroup(AccountsGroup accountsGroup);
	
	void updateAccountsGroup(AccountsGroup accountsGroup);
	
	void deleteAccountsGroup(Integer groupNo);
		
}
