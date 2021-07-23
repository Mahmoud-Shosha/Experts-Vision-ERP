package com.expertsvision.erp.core.form.dao;

import java.util.List;

import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.Form;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface FormsDAO {
	
	List<FormsView> getFormsViewList();
	
	FormsView getFormsView(Integer formNo);
	
	SinglePage<FormsView> getFormsViewSinglePage(long pageNo);
	
	SinglePage<FormsView> getFormsViewLastPage();
	
	Long getFormsViewSinglePageNo(Integer formNo);
	
	MultiplePages<FormsView> getFormsViewMultiplePages(long pageNo);
	
	MultiplePages<FormsView> getFormsViewFilteredMultiplePages(long pageNo, FormsViewFilter FormViewFilter);
	
	List<FormsView> getFormsViewMainTree(UsersView loginUser);
			
	void updateForms(Form Form);
		
}
