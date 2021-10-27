package com.expertsvision.erp.masterdata.employees.service;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.employees.dto.EmpInfoViewFilter;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfoView;

public interface EmpInfoService {

	List<EmpInfoView> getEmpInfoViewList(UsersView loginusersView);

	EmpInfoView getEmpInfoView(UsersView loginUsersView, Integer empNo);

	SinglePage<EmpInfoView> getEmpInfoViewSinglePage(UsersView loginUsersView, long pageNo);

	SinglePage<EmpInfoView> getEmpInfoViewLastPage(UsersView loginUsersView);

	Long getEmpInfoViewSinglePageNo(UsersView loginUsersView, Integer empNo);

	MultiplePages<EmpInfoView> getEmpInfoViewMultiplePages(UsersView loginUsersView, long pageNo);

	MultiplePages<EmpInfoView> getEmpInfoViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			EmpInfoViewFilter empInfoViewFilter);

	void addEmpInfo(UsersView loginUsersView, EmpInfoView empInfoView);

	void updateEmpInfo(UsersView loginUsersView, EmpInfoView empInfoView);

	void deleteEmpInfo(UsersView loginUsersView, Integer empNo);
	
	PreData preAdd(UsersView loginUsersView);
	
	PreData preModify(UsersView loginUsersView);
	
}
