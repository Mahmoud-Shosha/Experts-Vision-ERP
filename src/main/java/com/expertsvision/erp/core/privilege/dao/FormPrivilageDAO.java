package com.expertsvision.erp.core.privilege.dao;

import java.util.List;

import com.expertsvision.erp.core.privilege.entity.FormPrivilage;
import com.expertsvision.erp.core.privilege.entity.FormPrivilagePK;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;


public interface FormPrivilageDAO {
	
	List<FormPrivilageView> getFormPrivilageViewList();
	
	List<FormPrivilageView> getFormPrivilageViewList(Integer userId);
	
	FormPrivilage getFormPrivilage(FormPrivilagePK FormPrivilagePK);
	
//	LabelsView getLabelsView(LabelsViewPK labelsViewPK);
//	
//	Long getLabelsViewSinglePageNo(LabelsViewPK labelsViewPK);
//	
//	List<String> addLabelsView(UsersView loginUser, LabelsView labelsView);
//	
//	List<String> updateLabelsView(UsersView loginUser, LabelsView labelsView);
//	
//	List<String> deleteLabelsView(UsersView loginUser, LabelsViewPK labelsViewPK);
//	
//	SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo);
//	
//	SinglePage<LabelsView> getLabelsViewLastPage();
//	
//	MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo);
//	
//	MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter);
	
	void addBulkFormPrivilage(List<FormPrivilage> prvsList);
	
	void updateBulkFormPrivilage(List<FormPrivilage> prvsList);
	
	void updateFormPrivilage(FormPrivilage prv);
	
	void deleteBulkFormPrivilage(Integer userId);

}
