package com.expertsvision.erp.masterdata.employees.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.employees.dto.EmpInfoViewFilter;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfo;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfoView;


public interface EmpInfoDAO {
	
	List<EmpInfoView> getAllEmpInfoViewList();
					
	EmpInfoView getEmpInfoView(Integer empNo);
	
	SinglePage<EmpInfoView> getEmpInfoViewSinglePage(long pageNo);
	
	SinglePage<EmpInfoView> getEmpInfoViewLastPage();
	
	Long getEmpInfoViewSinglePageNo(Integer empNo);
	
	MultiplePages<EmpInfoView> getEmpInfoViewMultiplePages(long pageNo);
	
	MultiplePages<EmpInfoView> getEmpInfoViewFilteredMultiplePages(long pageNo, EmpInfoViewFilter empInfoViewFilter);
		
	void addEmpInfo(EmpInfo empInfo);
	
	void updateEmpInfo(EmpInfo empInfo);
	
	void deleteEmpInfo(Integer empNo);
	
	Object getNextPK();
}
