package com.expertsvision.erp.core.language.dao;

import java.util.List;

import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.Language;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface LanguageDAO {
	
	List<LanguageView> getLanguageViewList();
	
	LanguageView getLanguageView(Integer langNo);
	
	SinglePage<LanguageView> getLanguagesViewSinglePage(long pageNo);
	
	SinglePage<LanguageView> getLanguagesViewLastPage();
	
	Long getLanguageViewSinglePageNo(Integer langNo);
	
	MultiplePages<LanguageView> getLanguagesViewMultiplePages(long pageNo);
	
	MultiplePages<LanguageView> getLanguagesViewFilteredMultiplePages(long pageNo, LanguageViewFilter LanguageViewFilter);

	void addLanguage(Language language);
	
	void updateLanguage(Language language);
	
	void deleteLanguage(Integer langNo);
}
