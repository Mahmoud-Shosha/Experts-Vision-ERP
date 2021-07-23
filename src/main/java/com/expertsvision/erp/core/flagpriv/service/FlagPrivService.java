package com.expertsvision.erp.core.flagpriv.service;

import java.sql.Timestamp;
import java.util.List;

import com.expertsvision.erp.core.flagpriv.entity.FlagPrivView;
import com.expertsvision.erp.core.user.entity.UsersView;

public interface FlagPrivService {
	
	List<FlagPrivView> getFlagPrivViewList();
		
	List<FlagPrivView> getFlagPrivViewList(UsersView loginUser, Integer userId);
	
//	ModulesView getModulesView(UsersView loginUser, Integer moduleNo);
//		
//	void updateModulesView(UsersView loginUser, ModulesView modulesView);
//	
//	SinglePage<ModulesView> getModulesViewSinglePage(UsersView loginUser, long pageNo);
//	
//	SinglePage<ModulesView> getModulesViewLastPage(UsersView loginUser);
//	
//	MultiplePages<ModulesView> getModulesViewMultiplePages(UsersView loginUser, long pageNo);
//
//	MultiplePages<ModulesView> getModulesViewFilteredMultiplePages(UsersView loginUser, long pageNo, ModulesViewFilter ModuleViewFilter);

	void generateFlagPrivsForUser(UsersView loginUser, Integer userId, Timestamp addDate);
	
	void generateFlagPrivsForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId, Timestamp addDate);

	void updateFlagPrivsForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId, Timestamp modifyDate);

	void updateGroupUsersFlagPrivs(UsersView loginUser, Integer fromUserId, Integer groupNo, Timestamp modifyDate);
	
	void updateFlagPriv(UsersView loginUser, FlagPrivView flagPrivView);
	
	void deleteBulkFlagPriv(Integer userId);
	
}
