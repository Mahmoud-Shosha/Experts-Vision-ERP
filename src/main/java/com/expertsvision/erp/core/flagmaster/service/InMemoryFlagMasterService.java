package com.expertsvision.erp.core.flagmaster.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.flagmaster.entity.FlagMasterView;

@Service
@Lazy
public class InMemoryFlagMasterService {

	private FlagMasterService flagMasterViewService;

	private Map<String, FlagMasterView> flagMasterViewMap;

	@Autowired
	public InMemoryFlagMasterService(FlagMasterService flagMasterViewService) {
		this.flagMasterViewService = flagMasterViewService;
		List<FlagMasterView> flagMasterViewList = flagMasterViewService.getFlagMasterViewList();
		Map<String, FlagMasterView> newFlagMasterViewMap = convertToFlagMasterViewMap(flagMasterViewList);
		synchronized (this) {
			flagMasterViewMap = newFlagMasterViewMap;
		}
	}

	public FlagMasterView getFlagMasterView(String flagCode) {
		FlagMasterView flagMasterView = flagMasterViewMap.get(flagCode);
		return flagMasterView;
	}

	public void updateFlagMasterView() {
		List<FlagMasterView> flagMasterViewList = flagMasterViewService.getFlagMasterViewList();
		Map<String, FlagMasterView> newFlagMasterViewMap = convertToFlagMasterViewMap(flagMasterViewList);
		synchronized (this) {
			flagMasterViewMap = newFlagMasterViewMap;
		}
	}

	private Map<String, FlagMasterView> convertToFlagMasterViewMap(List<FlagMasterView> flagMasterViewList) {
		Map<String, FlagMasterView> flagMasterViewMap = new HashMap<String, FlagMasterView>();
		for (FlagMasterView flagMasterView : flagMasterViewList) {
			flagMasterViewMap.put(flagMasterView.getFlagCode(), flagMasterView);
		}
		return flagMasterViewMap;
	}

}
