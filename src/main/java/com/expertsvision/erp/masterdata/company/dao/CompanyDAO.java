package com.expertsvision.erp.masterdata.company.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.company.dto.CompanyViewFilter;
import com.expertsvision.erp.masterdata.company.entity.Company;
import com.expertsvision.erp.masterdata.company.entity.CompanyView;


public interface CompanyDAO {
	
	List<CompanyView> getAllCompanyViewList();
			
	CompanyView getCompanyView(Integer companyNo);
	
	SinglePage<CompanyView> getCompanyViewSinglePage(long pageNo);
	
	SinglePage<CompanyView> getCompanyViewLastPage();
	
	Long getUserViewSinglePageNo(Integer companyNo);
	
	MultiplePages<CompanyView> getCompanyViewMultiplePages(long pageNo);
	
	MultiplePages<CompanyView> getCompanyViewFilteredMultiplePages(long pageNo, CompanyViewFilter companyViewFilter);
	
	void addCompany(Company company);
	
	void updateCompany(Company company);
	
	void deleteCompany(Integer companyNo);
		
}
