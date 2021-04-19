package com.expertsvision.erp.core.language.service;

import java.util.List;

import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface LanguageService {
	
	List<LanguageView> getLanguageViewList();
		
	LanguageView getLanguageView(Integer langNo);
	
	SinglePage<LanguageView> getLanguagesViewSinglePage(long pageNo);
	
	SinglePage<LanguageView> getLanguagesViewLastPage();
	
	Long getLanguageViewSinglePageNo(Integer langNo);
	
	MultiplePages<LanguageView> getLanguagesViewMultiplePages(long pageNo);
	
	MultiplePages<LanguageView> getLanguagesViewFilteredMultiplePages(long pageNo, LanguageViewFilter LanguageViewFilter);

	void addLanguage(UsersView loginUser, LanguageView languageView);
	
	void updateLanguage(UsersView loginUser, LanguageView languageView);
	
	void deleteLanguage(UsersView loginUser, Integer langNo);
	
}
