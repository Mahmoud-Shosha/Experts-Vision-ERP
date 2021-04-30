package com.expertsvision.erp.core.usersgroups.dao;

import java.util.List;

import com.expertsvision.erp.core.usersgroups.dto.UsersGroupsViewFilter;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroup;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroupsView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;


public interface UsersGroupsDAO {
	
	List<UsersGroupsView> getAllUsersGroupsViewList();
			
	UsersGroupsView getUsersGroupsView(Integer groupNo);
	
	SinglePage<UsersGroupsView> getUsersGroupsViewSinglePage(long pageNo);
	
	SinglePage<UsersGroupsView> getUsersGroupsViewLastPage();
	
	Long getUserViewSinglePageNo(Integer groupNo);
	
	MultiplePages<UsersGroupsView> getUsersGroupsViewMultiplePages(long pageNo);
	
	MultiplePages<UsersGroupsView> getUsersGroupsViewFilteredMultiplePages(long pageNo, UsersGroupsViewFilter usersGroupsViewFilter);
	
	void addUsersGroup(UsersGroup usersGroup);
	
	void updateUsersGroup(UsersGroup usersGroup);
	
	void deleteUsersGroup(Integer groupNo);
		
}
