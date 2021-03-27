package com.expertsvision.erp.core.label.dao;

import java.util.List;

import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsViewPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;


public interface LabelsViewDAO {
	
	List<LabelsView> getLabelsViewList();
	
	LabelsView getLabelsView(LabelsViewPK labelsViewPK);
	
	List<String> addLabelsView(UsersView loginUser, LabelsView labelsView);
	
	List<String> updateLabelsView(UsersView loginUser, LabelsView labelsView);
	
	List<String> deleteLabelsView(UsersView loginUser, LabelsViewPK labelsViewPK);
	
	SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo);
	
	SinglePage<LabelsView> getLabelsViewLastPage();
	
	MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo);
	
	MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter);

}
