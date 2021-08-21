package com.expertsvision.erp.masterdata.masterdataprivileges.service;

import java.sql.Timestamp;

import com.expertsvision.erp.core.user.entity.UsersView;

public interface MasterDataPrivilegesService {
	
	void generateMasterDataPrivileges(UsersView loginUsersView, UsersView usersView, Timestamp currentDate, boolean viewPriv, boolean addPriv);
	
	void updateMasterDataPrivileges(UsersView loginUsersView, UsersView usersView, Timestamp currentDate, boolean viewPriv, boolean addPriv);
	
	void generateUngeneratedMasterDataPrivileges(UsersView loginUser, UsersView usersView, Timestamp currentDate, boolean viewPriv, boolean addPriv);

	void generateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView ,
			Integer fromUserId, Timestamp currentDate);
	
	void updateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView ,
			UsersView fromUsersView, Timestamp currentDate);
	
	void updateGroupUsersMasterDataPrivileges(UsersView loginUser, UsersView fromUsersView, Integer groupNo,
			Timestamp currentDate);
}
