package com.expertsvision.erp.core.language.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.language.entity.LanguageView;

@Service
@Lazy
public class InMemoryLanguageService {
	

	private LanguageService languageViewService;
	
	private Map<Integer, LanguageView> languageViewMap;
	
	
	@Autowired
	public InMemoryLanguageService(LanguageService languageViewService) {
		this.languageViewService = languageViewService;
		List<LanguageView> languageViewList = languageViewService.getLanguageViewList();
		Map<Integer, LanguageView> newLanguageViewMap = convertToLanguageViewMap(languageViewList);
		synchronized (this) {
			languageViewMap = newLanguageViewMap;
		}
	}
	
	public LanguageView getLanguageView(Integer langNo) {
		LanguageView languageView = languageViewMap.get(langNo);
		return languageView;
	}
	
	public void updateLanguagesView() {
		List<LanguageView> languageViewList = languageViewService.getLanguageViewList();
		Map<Integer, LanguageView> newLanguageViewMap = convertToLanguageViewMap(languageViewList);
		synchronized (this) {
			languageViewMap = newLanguageViewMap;
		}
	}
	
	private Map<Integer, LanguageView> convertToLanguageViewMap(List<LanguageView> languageViewList) {
		Map<Integer, LanguageView> languageViewMap = new HashMap<Integer, LanguageView>();
		for (LanguageView languageView : languageViewList) {
			languageViewMap.put(languageView.getLangNo(), languageView);
		}
		return languageViewMap;
	}


}
