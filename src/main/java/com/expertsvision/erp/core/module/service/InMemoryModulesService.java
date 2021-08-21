package com.expertsvision.erp.core.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.user.entity.UsersView;


@Service
@Lazy
public class InMemoryModulesService {
	
	
	private ModulesService modulesViewService;
	
	private Map<Integer, ModulesView> modulesViewMap;
	
	private UsersView superAdmin = new UsersView();
	
	
	@Autowired
	public InMemoryModulesService(ModulesService modulesViewService) {
		this.modulesViewService = modulesViewService;
		superAdmin.setSuperAdmin(true);
		List<ModulesView> modulesViewList = modulesViewService.getModulesViewList(superAdmin);
		Map<Integer, ModulesView> newModulesViewMap = convertToModulesViewMap(modulesViewList);
		synchronized (this) {
			modulesViewMap = newModulesViewMap;
		}
	}
	
	public ModulesView getModulesView(Integer moduleNo) {
		ModulesView modulesView = modulesViewMap.get(moduleNo);
		return modulesView;
	}
	
//	public Collection<ModulesView> getAllModulesView() {
//		Collection<ModulesView> modulesViewList = modulesViewMap.values();
//		return modulesViewList;
//	}
	
	public void updateModulesViewMap() {
		List<ModulesView> modulesViewList = modulesViewService.getModulesViewList(superAdmin);
		Map<Integer, ModulesView> newModulesViewMap = convertToModulesViewMap(modulesViewList);
		synchronized (this) {
			modulesViewMap = newModulesViewMap;
		}
	}
	
	private Map<Integer, ModulesView> convertToModulesViewMap(List<ModulesView> modulesViewList) {
		Map<Integer, ModulesView> modulesViewMap = new HashMap<Integer, ModulesView>();
		for (ModulesView modulesView : modulesViewList) {
			modulesViewMap.put(modulesView.getModuleNo(), modulesView);
		}
		return modulesViewMap;
	}


}
