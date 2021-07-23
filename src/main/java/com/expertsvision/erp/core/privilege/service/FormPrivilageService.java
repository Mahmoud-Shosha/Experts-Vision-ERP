package com.expertsvision.erp.core.privilege.service;

import java.sql.Timestamp;
import java.util.List;

import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.user.entity.UsersView;

public interface FormPrivilageService {
	
	List<FormPrivilageView> getFormPrivilageViewList(UsersView loginUser);
	
	List<FormPrivilageView> getFormPrivilageViewList(UsersView loginUser, Integer userId);
	
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

	void generateFormPrivilegesForUser(UsersView loginUser, Integer userId, Timestamp addDate);
	
	void generateFormPrivilegesForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId, Timestamp addDate);

	void updateFormPrivilegesForUserFromAnotherUser(UsersView loginUser, Integer fromUserId, Integer toUserId, Timestamp modifyDate);

	void updateGroupUsersPrivileges(UsersView loginUser, Integer fromUserId, Integer groupNo, Timestamp modifyDate);
	
	void updateFormPrivilage(UsersView loginUser, FormPrivilageView formPrivilageView);
	
	void deleteBulkFormPrivilage(Integer userId);
	
}
