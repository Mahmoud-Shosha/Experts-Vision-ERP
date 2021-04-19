package com.expertsvision.erp.core.module.service;

import java.util.List;

import com.expertsvision.erp.core.module.dto.ModulesViewFilter;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface ModulesService {
	
	List<ModulesView> getModulesViewList(UsersView loginUser);
	
	ModulesView getModulesView(UsersView loginUser, Integer moduleNo);
	
	SinglePage<ModulesView> getModulesViewSinglePage(UsersView loginUser, long pageNo);
	
	SinglePage<ModulesView> getModulesViewLastPage(UsersView loginUser);
	
	Long getModulesViewSinglePageNo(UsersView loginUser, Integer moduleNo);
	
	MultiplePages<ModulesView> getModulesViewMultiplePages(UsersView loginUser, long pageNo);

	MultiplePages<ModulesView> getModulesViewFilteredMultiplePages(UsersView loginUser, long pageNo, ModulesViewFilter ModuleViewFilter);

	void updateModules(UsersView loginUser, ModulesView modulesView);
}
