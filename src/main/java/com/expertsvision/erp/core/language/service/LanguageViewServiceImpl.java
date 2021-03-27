package com.expertsvision.erp.core.language.service;

import java.util.Arrays;
import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.language.dao.LanguageViewDAO;
import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class LanguageViewServiceImpl implements LanguageViewService {
	
	@Autowired
	private LanguageViewDAO languageViewDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;

	@Override
	public List<LanguageView> getLanguageViewList() {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		List<LanguageView> languageViewList = null;
		try {
			languageViewList = languageViewDAO.getLanguageViewList();
		} catch (HibernateException e) {
			throw new UnauthorizedException("resource");
		}
		return languageViewList;
	}

	@Override
	public LanguageView getLanguageView(Integer langNo) {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		LanguageView languageView = null;
		try {
			languageView = languageViewDAO.getLanguageView(langNo);
		} catch (HibernateException e) {
			throw new UnauthorizedException("resource");
		}
		if (languageView == null) {
			throw new ValidationException("not_exist", "language");
		}
		return languageView;
	}
	
	@Override
	public SinglePage<LanguageView> getLanguagesViewSinglePage(long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		SinglePage<LanguageView> singlePage = null;
		try {
			singlePage = languageViewDAO.getLanguagesViewSinglePage(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public SinglePage<LanguageView> getLanguagesViewLastPage() {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		SinglePage<LanguageView> singlePage = null;
		try {
			singlePage = languageViewDAO.getLanguagesViewLastPage();
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public MultiplePages<LanguageView> getLanguagesViewMultiplePages(long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		MultiplePages<LanguageView> multiplePages = null;
		try {
			multiplePages = languageViewDAO.getLanguagesViewMultiplePages(pageNo);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	@Override
	public MultiplePages<LanguageView> getLanguagesViewFilteredMultiplePages(long pageNo, LanguageViewFilter LanguageViewFilter) {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		MultiplePages<LanguageView> multiplePages = null;
		try {
			multiplePages = languageViewDAO.getLanguagesViewFilteredMultiplePages(pageNo, LanguageViewFilter);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	@Override
	public void addLanguageView(UsersView loginUser, LanguageView languageView) {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		coreValidationService.notNull(languageView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(languageView.getLangNo(), "lang_no");
		coreValidationService.notNull(languageView.getLangName(), "name");
		coreValidationService.notBlank(languageView.getLangName(), "name");
		coreValidationService.notNull(languageView.getLangDir(), "lang_dir");
		coreValidationService.inValues(languageView.getLangDir(), Arrays.asList(1,2), "lang_dir");
		coreValidationService.notNull(languageView.getReportExt(), "report_ext");
		coreValidationService.notBlank(languageView.getReportExt(), "report_ext");
		coreValidationService.notNull(languageView.getLangExt(), "lang_ext");
		coreValidationService.notBlank(languageView.getLangExt(), "lang_ext");
		coreValidationService.notNull(languageView.getLangDfl(), "lang_dfl");
		coreValidationService.notNull(languageView.getActive(), "active");
		coreValidationService.runDatabaseValidation(languageViewDAO.addLanguageView(loginUser, languageView));		
	}
	
	@Override
	public void updateLanguageView(UsersView loginUser, LanguageView languageView) {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		coreValidationService.notNull(languageView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(languageView.getLangNo(), "lang_no");
		coreValidationService.notNull(languageView.getLangName(), "name");
		coreValidationService.notBlank(languageView.getLangName(), "name");
		coreValidationService.notNull(languageView.getLangDir(), "lang_dir");
		coreValidationService.inValues(languageView.getLangDir(), Arrays.asList(1,2), "lang_dir");
		coreValidationService.notNull(languageView.getReportExt(), "report_ext");
		coreValidationService.notBlank(languageView.getReportExt(), "report_ext");
		coreValidationService.notNull(languageView.getLangExt(), "lang_ext");
		coreValidationService.notBlank(languageView.getLangExt(), "lang_ext");
		coreValidationService.notNull(languageView.getLangDfl(), "lang_dfl");
		coreValidationService.notNull(languageView.getActive(), "active");
		coreValidationService.runDatabaseValidation(languageViewDAO.updateLanguageView(loginUser, languageView));	
	}
	
	@Override
	public void deleteLanguageView(UsersView loginUser, Integer langNo) {
		coreValidationService.activeModuleAndForm(Forms.LANGUAGE);
		try {
			coreValidationService.runDatabaseValidation(languageViewDAO.deleteLanguageView(loginUser, langNo));
		} catch (HibernateException e) {
			e.printStackTrace();
			throw new ValidationException("used_somewhere", "language");
		}
	}

}
