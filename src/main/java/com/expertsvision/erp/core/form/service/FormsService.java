package com.expertsvision.erp.core.form.service;

import java.util.List;

import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface FormsService {
	
	List<FormsView> getFormsViewList(UsersView loginUser);
	
	List<FormsView> getFormsViewList();
	
	FormsView getFormsView(UsersView loginUser, Integer formNo);
	
	SinglePage<FormsView> getFormsViewSinglePage(UsersView loginUser, long pageNo);
	
	SinglePage<FormsView> getFormsViewLastPage(UsersView loginUser);
	
	Long getFormsViewSinglePageNo(UsersView loginUser, Integer formNo);
	
	List<FormsView> getFormsViewMainTree(UsersView loginUser);
	
	Boolean IsFormsViewInMainTree(Integer userId, Integer formNo);
	
	MultiplePages<FormsView> getFormsViewMultiplePages(UsersView loginUser, long pageNo);
	
	MultiplePages<FormsView> getFormsViewFilteredMultiplePages(UsersView loginUser, long pageNo, FormsViewFilter FormViewFilter);
		
	void updateFormsView(UsersView loginUser, FormsView formsView);

}
