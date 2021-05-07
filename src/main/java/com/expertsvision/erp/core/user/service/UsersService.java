package com.expertsvision.erp.core.user.service;

import java.util.List;

import com.expertsvision.erp.core.user.dto.UsersDTO;
import com.expertsvision.erp.core.user.dto.UsersViewFilter;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface UsersService {
	
	List<UsersView> getAllUsersViewList();
		
	List<UsersDTO> getUsersViewSubordinateList(UsersView usersView);
	
	UsersDTO getUsersView(UsersView usersView, Integer userId);
	
	UsersView getUsersView(Integer userId);
	
	SinglePage<UsersDTO> getUsersViewSinglePage(UsersView usersView, long pageNo);
	
	SinglePage<UsersDTO> getUsersViewLastPage(UsersView usersView);
	
	Long getUserViewSinglePageNo(UsersView usersView, Integer userId);
	
	MultiplePages<UsersDTO> getUsersViewMultiplePages(UsersView usersView, long pageNo);
	
	MultiplePages<UsersDTO> getUsersViewFilteredMultiplePages(UsersView usersView, long pageNo, UsersViewFilter usersViewFilter);
	
	void addUser(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES);
	
	void updateUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES,
			 			 Integer COPY_PRIVILEGES_TO_GROUP, Boolean confirm);
	
	void deleteUsersView(UsersView loginUser, Integer userId);
	
	Boolean isUserSubordinate(UsersView usersView, Integer userId);
	
	List<User> getUsersListByGroupNo(Integer groupNo);
	
}
