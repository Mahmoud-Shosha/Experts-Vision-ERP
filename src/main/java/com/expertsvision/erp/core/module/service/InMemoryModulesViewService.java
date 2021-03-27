package com.expertsvision.erp.core.module.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralConstants;


@Service
public class InMemoryModulesViewService {
	
	
	private ModulesViewService modulesViewService;
	
	private Map<Integer, ModulesView> modulesViewMap;
	
	
	@Autowired
	public InMemoryModulesViewService(ModulesViewService modulesViewService) {
		this.modulesViewService = modulesViewService;
		List<ModulesView> modulesViewList = modulesViewService.getModulesViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
		Map<Integer, ModulesView> newModulesViewMap = convertToModulesViewMap(modulesViewList);
		synchronized (this) {
			modulesViewMap = newModulesViewMap;
		}
	}
	
	public ModulesView getModulesView(Integer moduleNo) {
		ModulesView modulesView = modulesViewMap.get(moduleNo);
		return modulesView;
	}
	
	public void updateModulesViewMap() {
		List<ModulesView> modulesViewList = modulesViewService.getModulesViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
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
