package com.expertsvision.erp.core.form.dao;

import java.util.List;

import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface FormsViewDAO {
	
	List<FormsView> getFormsViewList(UsersView loginUser);
	
	FormsView getFormsView(UsersView loginUser, Integer formNo);
	
	List<FormsView> getFormsViewMainTree(UsersView loginUser);
		
	List<String> updateFormsView(UsersView loginUser, FormsView FormsView);
	
	SinglePage<FormsView> getFormsViewSinglePage(UsersView loginUser, long pageNo);
	
	SinglePage<FormsView> getFormsViewLastPage(UsersView loginUser);
	
	MultiplePages<FormsView> getFormsViewMultiplePages(UsersView loginUser, long pageNo);
	
	MultiplePages<FormsView> getFormsViewFilteredMultiplePages(UsersView loginUser, long pageNo, FormsViewFilter FormViewFilter);
		
}
