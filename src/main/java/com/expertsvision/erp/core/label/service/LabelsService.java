package com.expertsvision.erp.core.label.service;

import java.util.List;

import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface LabelsService {
	
	List<LabelsView> getLabelsViewList();
	
	LabelsView getLabelsView(LabelsPK labelsViewPK);
	
	SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo);
	
	SinglePage<LabelsView> getLabelsViewLastPage();
	
	Long getLabelsViewSinglePageNo(LabelsPK labelsViewPK);
	
	MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo);
	
	MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter);
	
	void addLabel(UsersView loginUser, LabelsView labelsView);
	
	void updateLabel(UsersView loginUser, LabelsView labelsView);
	
	void deleteLabel(UsersView loginUser, LabelsPK labelsViewPK);
}
