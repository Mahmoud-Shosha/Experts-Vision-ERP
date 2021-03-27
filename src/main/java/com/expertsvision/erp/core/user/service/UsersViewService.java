package com.expertsvision.erp.core.user.service;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;

public interface UsersViewService {
	
	List<UsersView> getUsersViewList(UsersView loginUser);
	
	List<UsersView> getUsersViewSubordinateList(UsersView usersView);
	
	UsersView getUsersView(UsersView loginUser, Integer userId);
	
	void addUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES);
	
	void updateUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES,
			 			 Integer COPY_PRIVILEGES_TO_GROUP, Boolean confirm);
	
	void deleteUsersView(UsersView loginUser, Integer userId);
	
}
