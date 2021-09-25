package com.expertsvision.erp.masterdata.accountsgroup.service;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.accountsgroup.dto.AccountsGroupViewFilter;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroupView;

public interface AccountsGroupService {
		
	List<AccountsGroupView> getAccountsGroupViewList(UsersView loginusersView);
	
	AccountsGroupView getAccountsGroupView(UsersView loginUsersView, Integer groupNo);
	
	SinglePage<AccountsGroupView> getAccountsGroupViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<AccountsGroupView> getAccountsGroupViewLastPage(UsersView loginUsersView);
	
	Long getAccountsGroupViewSinglePageNo(UsersView loginUsersView, Integer groupNo);
	
	MultiplePages<AccountsGroupView> getAccountsGroupViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<AccountsGroupView> getAccountsGroupViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   AccountsGroupViewFilter accountsGroupViewFilter);
	
	void addAccountsGroup(UsersView loginUsersView, AccountsGroupView accountsGroupView);
	
	void updateAccountsGroup(UsersView loginUsersView, AccountsGroupView accountsGroupView);
	
	void deleteAccountsGroup(UsersView loginUsersView, Integer groupNo);
	
	PreData preAdd(UsersView loginUsersView);
	
	PreData preModify(UsersView loginUsersView);
	
}
