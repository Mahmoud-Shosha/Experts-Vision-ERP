package com.expertsvision.erp.masterdata.companygroups.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.companygroups.dto.CompanyGroupsViewFilter;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroup;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroupsView;


public interface CompanyGroupDAO {
	
	List<CompanyGroupsView> getAllCompanyGroupsViewList();
			
	CompanyGroupsView getCompanyGroupsView(Integer companyGroupNo);
	
	SinglePage<CompanyGroupsView> getCompanyGroupsViewSinglePage(long pageNo);
	
	SinglePage<CompanyGroupsView> getCompanyGroupsViewLastPage();
	
	Long getUserViewSinglePageNo(Integer companyGroupNo);
	
	MultiplePages<CompanyGroupsView> getCompanyGroupsViewMultiplePages(long pageNo);
	
	MultiplePages<CompanyGroupsView> getCompanyGroupsViewFilteredMultiplePages(long pageNo, CompanyGroupsViewFilter companyGroupViewFilter);
	
	void addCompanyGroups(CompanyGroup companyGroup);
	
	void updateCompanyGroups(CompanyGroup companyGroup);
	
	void deleteCompanyGroups(Integer companyGroupNo);
		
}
