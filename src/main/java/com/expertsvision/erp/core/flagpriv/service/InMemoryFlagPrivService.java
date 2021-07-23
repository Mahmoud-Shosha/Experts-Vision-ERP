package com.expertsvision.erp.core.flagpriv.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.flagpriv.entity.FlagPrivPK;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivView;

@Service
@Lazy
public class InMemoryFlagPrivService {

	private FlagPrivService flagPrivViewService;

	private Map<FlagPrivPK, FlagPrivView> flagPrivViewMap;

	@Autowired
	public InMemoryFlagPrivService(FlagPrivService flagPrivViewService) {
		this.flagPrivViewService = flagPrivViewService;
		List<FlagPrivView> flagPrivViewList = flagPrivViewService.getFlagPrivViewList();
		Map<FlagPrivPK, FlagPrivView> newFlagPrivViewMap = convertToFlagPrivViewMap(flagPrivViewList);
		synchronized (this) {
			flagPrivViewMap = newFlagPrivViewMap;
		}
	}

	public FlagPrivView getFlagPrivView(FlagPrivPK flagPrivPK) {
		FlagPrivView flagPrivView = flagPrivViewMap.get(flagPrivPK);
		return flagPrivView;
	}

	public void updateFlagPrivView() {
		List<FlagPrivView> flagPrivViewList = flagPrivViewService.getFlagPrivViewList();
		Map<FlagPrivPK, FlagPrivView> newFlagPrivViewMap = convertToFlagPrivViewMap(flagPrivViewList);
		synchronized (this) {
			flagPrivViewMap = newFlagPrivViewMap;
		}
	}

	private Map<FlagPrivPK, FlagPrivView> convertToFlagPrivViewMap(List<FlagPrivView> flagPrivViewList) {
		Map<FlagPrivPK, FlagPrivView> flagPrivViewMap = new HashMap<FlagPrivPK, FlagPrivView>();
		for (FlagPrivView flagPrivView : flagPrivViewList) {
			flagPrivViewMap.put(
					new FlagPrivPK(flagPrivView.getUserId(), flagPrivView.getFlagCode(), flagPrivView.getFlagValue()),
					flagPrivView);
		}
		return flagPrivViewMap;
	}

}
