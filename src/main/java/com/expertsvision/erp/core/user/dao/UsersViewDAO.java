package com.expertsvision.erp.core.user.dao;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;


public interface UsersViewDAO {
	
	List<UsersView> getUsersViewList(UsersView loginUser);
	
	List<UsersView> getUsersViewSubordinateList(UsersView usersView);
	
	UsersView getUsersView(UsersView loginUser, Integer userId);
	
	List<String> addUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES);
	
	List<String> updateUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES,
							     Integer COPY_PRIVILEGES_TO_GROUP, Boolean confirm);
	
	List<String> deleteUsersView(UsersView loginUser,  Integer userId);

}
