package com.expertsvision.erp.core.privilege.service;

import java.util.List;

import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.user.entity.UsersView;

public interface FormPrivilageService {
	
	List<FormPrivilageView> getFormPrivilageViewList(UsersView loginUser);
	
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

}
