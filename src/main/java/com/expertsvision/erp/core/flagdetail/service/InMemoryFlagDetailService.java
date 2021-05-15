package com.expertsvision.erp.core.flagdetail.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.flagdetail.entity.FlagDetailPK;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;

@Service
@Lazy
public class InMemoryFlagDetailService {

	private FlagDetailService flagDetailService;

	private Map<FlagDetailPK, FlagDetailView> flagDetailViewMap;

	@Autowired
	public InMemoryFlagDetailService(FlagDetailService flagDetailService) {
		this.flagDetailService = flagDetailService;
		List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
		Map<FlagDetailPK, FlagDetailView> newFlagDetailViewMap = convertToFlagDetailViewMap(flagDetailViewList);
		synchronized (this) {
			flagDetailViewMap = newFlagDetailViewMap;
		}
	}

	public FlagDetailView getFlagDetailView(FlagDetailPK flagDetailPK) {
		FlagDetailView flagDetailView = flagDetailViewMap.get(flagDetailPK);
		return flagDetailView;
	}

	public void updateFlagDetailView() {
		List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
		Map<FlagDetailPK, FlagDetailView> newFlagDetailViewMap = convertToFlagDetailViewMap(flagDetailViewList);
		synchronized (this) {
			flagDetailViewMap = newFlagDetailViewMap;
		}
	}

	private Map<FlagDetailPK, FlagDetailView> convertToFlagDetailViewMap(List<FlagDetailView> flagDetailViewList) {
		Map<FlagDetailPK, FlagDetailView> flagDetailViewMap = new HashMap<FlagDetailPK, FlagDetailView>();
		for (FlagDetailView flagDetailView : flagDetailViewList) {
			flagDetailViewMap.put(new FlagDetailPK(flagDetailView.getFlagCode(), flagDetailView.getFlagValue()), flagDetailView);
		}
		return flagDetailViewMap;
	}

}
