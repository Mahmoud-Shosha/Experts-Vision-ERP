package com.expertsvision.erp.core.label.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsPK;

@Service
@Lazy
public class InMemoryLabelsService {
	
	
	private LabelsService labelsViewService;
	
	private  Map<LabelsPK, LabelsView> labelsViewMap;
	
	@Autowired
	public InMemoryLabelsService(LabelsService labelsViewService) {
		this.labelsViewService = labelsViewService;
		List<LabelsView> labelsViewList = labelsViewService.getLabelsViewList();
		Map<LabelsPK, LabelsView> NewLabelsViewMap = convertToLabelMap(labelsViewList);
		synchronized (this) {
			labelsViewMap = NewLabelsViewMap;
		}
	}
	
	public LabelsView getLabelsView(LabelsPK labelsViewPK) {
		LabelsView labelsView = labelsViewMap.get(labelsViewPK);
		return labelsView;
	}
	
	public void updateLabelsView() {		
		List<LabelsView> labelsViewList = labelsViewService.getLabelsViewList();
		Map<LabelsPK, LabelsView> NewLabelsViewMap = convertToLabelMap(labelsViewList);
		synchronized (this) {
			labelsViewMap = NewLabelsViewMap;
		}
	}
	
	private Map<LabelsPK, LabelsView> convertToLabelMap(List<LabelsView> labelsViewList) {
		Map<LabelsPK, LabelsView> NewLabelsViewMap = new HashMap<LabelsPK, LabelsView>();
		for (LabelsView labelsView : labelsViewList) {
			NewLabelsViewMap.put(new LabelsPK(labelsView.getLangNo(), labelsView.getLabelCode()), labelsView);
		}
		return NewLabelsViewMap;
	}

}
