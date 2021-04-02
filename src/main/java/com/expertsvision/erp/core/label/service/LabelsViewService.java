package com.expertsvision.erp.core.label.service;

import java.util.List;

import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsViewPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface LabelsViewService {
	
	List<LabelsView> getLabelsViewList();
	
	LabelsView getLabelsView(LabelsViewPK labelsViewPK);
	
	Long getLabelsViewSinglePageNo(LabelsViewPK labelsViewPK);
	
	void addLabelsView(UsersView loginUser, LabelsView labelsView);
	
	void updateLabelsView(UsersView loginUser, LabelsView labelsView);
	
	void deleteLabelsView(UsersView loginUser, LabelsViewPK labelsViewPK);
	
	SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo);
	
	SinglePage<LabelsView> getLabelsViewLastPage();
	
	MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo);
	
	MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter);
}
