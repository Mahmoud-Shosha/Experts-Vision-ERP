package com.expertsvision.erp.core.language.dao;

import java.util.List;

import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface LanguageViewDAO {
	
	List<LanguageView> getLanguageViewList();
	
	LanguageView getLanguageView(Integer langNo);
			
	List<String> addLanguageView(UsersView loginUser, LanguageView languageView);
	
	List<String> updateLanguageView(UsersView loginUser, LanguageView languageView);
	
	List<String> deleteLanguageView(UsersView loginUser,  Integer langNo);
	
	SinglePage<LanguageView> getLanguagesViewSinglePage(long pageNo);
	
	SinglePage<LanguageView> getLanguagesViewLastPage();
	
	MultiplePages<LanguageView> getLanguagesViewMultiplePages(long pageNo);
	
	MultiplePages<LanguageView> getLanguagesViewFilteredMultiplePages(long pageNo, LanguageViewFilter LanguageViewFilter);

}
