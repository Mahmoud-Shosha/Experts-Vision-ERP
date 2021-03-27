package com.expertsvision.erp.core.label.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsViewPK;

@Service
public class InMemoryLabelsViewService {
	
	
	private LabelsViewService labelsViewService;
	
	private  Map<LabelsViewPK, LabelsView> labelsViewMap;
	
	@Autowired
	public InMemoryLabelsViewService(LabelsViewService labelsViewService) {
		this.labelsViewService = labelsViewService;
		List<LabelsView> labelsViewList = labelsViewService.getLabelsViewList();
		Map<LabelsViewPK, LabelsView> NewLabelsViewMap = convertToLabelMap(labelsViewList);
		synchronized (this) {
			labelsViewMap = NewLabelsViewMap;
		}
	}
	
	public LabelsView getLabelsView(LabelsViewPK labelsViewPK) {
		LabelsView labelsView = labelsViewMap.get(labelsViewPK);
		return labelsView;
	}
	
	public void updateLabelsView() {		
		List<LabelsView> labelsViewList = labelsViewService.getLabelsViewList();
		Map<LabelsViewPK, LabelsView> NewLabelsViewMap = convertToLabelMap(labelsViewList);
		synchronized (this) {
			labelsViewMap = NewLabelsViewMap;
		}
	}
	
	private Map<LabelsViewPK, LabelsView> convertToLabelMap(List<LabelsView> labelsViewList) {
		Map<LabelsViewPK, LabelsView> NewLabelsViewMap = new HashMap<LabelsViewPK, LabelsView>();
		for (LabelsView labelsView : labelsViewList) {
			NewLabelsViewMap.put(new LabelsViewPK(labelsView.getLangNo(), labelsView.getLabelCode()), labelsView);
		}
		return NewLabelsViewMap;
	}

}
