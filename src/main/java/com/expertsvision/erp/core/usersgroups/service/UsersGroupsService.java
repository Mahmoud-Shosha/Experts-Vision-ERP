package com.expertsvision.erp.core.usersgroups.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.usersgroups.dto.UsersGroupsViewFilter;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroupsView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface UsersGroupsService {
	
	List<UsersGroupsView> getAllUsersGroupsViewList();
	
	List<UsersGroupsView> getUsersGroupsViewList(UsersView loginusersView);
	
	UsersGroupsView getUsersGroupsView(UsersView loginUsersView, Integer groupNo);
	
	SinglePage<UsersGroupsView> getUsersGroupsViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<UsersGroupsView> getUsersGroupsViewLastPage(UsersView loginUsersView);
	
	Long getUsersGroupsViewSinglePageNo(UsersView loginUsersView, Integer groupNo);
	
	MultiplePages<UsersGroupsView> getUsersGroupsViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<UsersGroupsView> getUsersGroupsViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   UsersGroupsViewFilter usersGroupsViewFilter);
	
	void addUsersGroups(UsersView loginUsersView, UsersGroupsView usersGroupsView);
	
	void updateUsersGroups(UsersView loginUsersView, UsersGroupsView usersGroupsView);
	
	void deleteUsersGroups(UsersView loginUsersView, Integer groupNo);
	
}
