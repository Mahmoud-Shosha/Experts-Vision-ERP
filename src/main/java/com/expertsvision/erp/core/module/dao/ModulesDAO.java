package com.expertsvision.erp.core.module.dao;

import java.util.List;

import com.expertsvision.erp.core.module.dto.ModulesViewFilter;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.module.entity.Module;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface ModulesDAO {
	
	List<ModulesView> getModulesViewList();
	
	ModulesView getModulesView(Integer moduleNo);
	
	SinglePage<ModulesView> getModulesViewSinglePage(long pageNo);
	
	SinglePage<ModulesView> getModulesViewLastPage();
	
	Long getModulesViewSinglePageNo(Integer moduleNo);
	
	MultiplePages<ModulesView> getModulesViewMultiplePages(long pageNo);
	
	MultiplePages<ModulesView> getModulesViewFilteredMultiplePages(long pageNo, ModulesViewFilter ModuleViewFilter);

	void updateModules(Module module);
		
}
