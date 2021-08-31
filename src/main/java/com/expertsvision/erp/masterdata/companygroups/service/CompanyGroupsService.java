package com.expertsvision.erp.masterdata.companygroups.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.companygroups.dto.CompanyGroupsViewFilter;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroupsView;

public interface CompanyGroupsService {
		
	List<CompanyGroupsView> getCompanyGroupsViewList(UsersView loginusersView);
	
	CompanyGroupsView getCompanyGroupsView(UsersView loginUsersView, Integer companyGroupsNo);
	
	SinglePage<CompanyGroupsView> getCompanyGroupsViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<CompanyGroupsView> getCompanyGroupsViewLastPage(UsersView loginUsersView);
	
	Long getCompanyGroupsViewSinglePageNo(UsersView loginUsersView, Integer companyGroupsNo);
	
	MultiplePages<CompanyGroupsView> getCompanyGroupsViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<CompanyGroupsView> getCompanyGroupsViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   CompanyGroupsViewFilter companyGroupsViewFilter);
	Object getNextPK(UsersView loginUsersView);
	
	void addCompanyGroups(UsersView loginUsersView, CompanyGroupsView companyGroupsView);
	
	void updateCompanyGroups(UsersView loginUsersView, CompanyGroupsView companyGroupsView);
	
	void deleteCompanyGroups(UsersView loginUsersView, Integer companyGroupsNo);
	
}
