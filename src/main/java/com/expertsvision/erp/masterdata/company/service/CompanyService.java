package com.expertsvision.erp.masterdata.company.service;

import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.company.dto.CompanyViewFilter;
import com.expertsvision.erp.masterdata.company.entity.CompanyView;

public interface CompanyService {
		
	List<CompanyView> getCompanyViewList(UsersView loginusersView);
	
	CompanyView getCompanyView(UsersView loginUsersView, Integer companyNo);
	
	SinglePage<CompanyView> getCompanyViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<CompanyView> getCompanyViewLastPage(UsersView loginUsersView);
	
	Long getCompanyViewSinglePageNo(UsersView loginUsersView, Integer companyNo);
	
	MultiplePages<CompanyView> getCompanyViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<CompanyView> getCompanyViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, 
																		   CompanyViewFilter companyViewFilter);
	Object getNextPK(UsersView loginUsersView);
	
	void addCompany(UsersView loginUsersView, CompanyView companyView);
	
	void updateCompany(UsersView loginUsersView, CompanyView companyView);
	
	void deleteCompany(UsersView loginUsersView, Integer companyNo);
	
}
