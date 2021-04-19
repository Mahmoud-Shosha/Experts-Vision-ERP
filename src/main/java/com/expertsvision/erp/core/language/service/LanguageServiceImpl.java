package com.expertsvision.erp.core.language.service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.language.dao.LanguageDAO;
import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.Language;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class LanguageServiceImpl implements LanguageService {
	
	@Autowired
	private LanguageDAO languageDAO;
	
	@Autowired
	private GeneralDAO generalDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;

	@Override
	@Transactional
	public List<LanguageView> getLanguageViewList() {
		List<LanguageView> languageViewList = languageDAO.getLanguageViewList();
		return languageViewList;
	}

	@Override
	@Transactional
	public LanguageView getLanguageView(Integer langNo) {
		LanguageView languageView = languageDAO.getLanguageView(langNo);
		if (languageView == null) {
			throw new ValidationException("not_exist", "language");
		}
		return languageView;
	}
	
	
	
	@Override
	@Transactional
	public SinglePage<LanguageView> getLanguagesViewSinglePage(long pageNo) {
		SinglePage<LanguageView> singlePage = languageDAO.getLanguagesViewSinglePage(pageNo);
		return singlePage;
	}
	
	@Override
	@Transactional
	public SinglePage<LanguageView> getLanguagesViewLastPage() {
		SinglePage<LanguageView> singlePage = languageDAO.getLanguagesViewLastPage();
		return singlePage;
	}
	
	@Override
	@Transactional
	public Long getLanguageViewSinglePageNo(Integer langNo) {
		 Long singlePageNo = languageDAO.getLanguageViewSinglePageNo(langNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "language");
		}
		return singlePageNo;
	}
	
	@Override
	@Transactional
	public MultiplePages<LanguageView> getLanguagesViewMultiplePages(long pageNo) {
		MultiplePages<LanguageView> multiplePages = languageDAO.getLanguagesViewMultiplePages(pageNo);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public MultiplePages<LanguageView> getLanguagesViewFilteredMultiplePages(long pageNo, LanguageViewFilter LanguageViewFilter) {
		MultiplePages<LanguageView> multiplePages = languageDAO.getLanguagesViewFilteredMultiplePages(pageNo, LanguageViewFilter);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public void addLanguage(UsersView loginUser, LanguageView languageView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Non-database validation
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
		// Database validation
		Language language = getLanguageFromLanguageView(languageView);
		Map<String, Object> conditions = new HashMap<>();
		Map<String, Object> setters = new HashMap<>();
		conditions.put("lang_no", language.getLangNo());
		if (generalDAO.isEntityExist("language", conditions)) throw new ValidationException("already_exist", "lang_code");
		conditions.clear();
		conditions.put("lang_name", language.getLangName());
		if (generalDAO.isEntityExist("language", conditions)) throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("report_ext", language.getReportExt());
		if (generalDAO.isEntityExist("language", conditions)) throw new ValidationException("already_exist", "report_ext");
		conditions.clear();
		conditions.put("lang_ext", language.getLangExt());
		if (generalDAO.isEntityExist("language", conditions)) throw new ValidationException("already_exist", "lang_ext");
		setters.clear();
		setters.put("lang_dfl", false);
		if (language.getLangDfl()) {
			generalDAO.runEntityQuery("language", setters, null);
		}
		// Add the language
		languageDAO.addLanguage(language);
	}
	
	@Override
	@Transactional
	public void updateLanguage(UsersView loginUser, LanguageView languageView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Non-database validation
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
		// Database validation
		Language language = getLanguageFromLanguageView(languageView);
		Map<String, Object> conditions = new HashMap<>();
		Map<String, Object> setters = new HashMap<>();
		conditions.put("lang_no", language.getLangNo());
		if (!generalDAO.isEntityExist("language", conditions)) throw new ValidationException("not_exist", "language");
		conditions.clear();
		conditions.put("lang_name", language.getLangName());
		if (generalDAO.isEntityExist("language", conditions)) throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("report_ext", language.getReportExt());
		if (generalDAO.isEntityExist("language", conditions)) throw new ValidationException("already_exist", "report_ext");
		conditions.clear();
		conditions.put("lang_ext", language.getLangExt());
		if (generalDAO.isEntityExist("language", conditions)) throw new ValidationException("already_exist", "lang_ext");
		setters.clear();
		setters.put("lang_dfl", false);
		LanguageView DBLanguageView = languageDAO.getLanguageView(languageView.getLangNo());
		if (language.getLangDfl() && !DBLanguageView.getLangDfl()) {
			generalDAO.runEntityQuery("language", setters, null);
		} else if (!language.getLangDfl() && DBLanguageView.getLangDfl()) {
			throw new ValidationException("change_true_lang_dfl");
		}
		// Update the language
		languageDAO.updateLanguage(language);	
	}
	
	@Override
	@Transactional
	public void deleteLanguage(UsersView loginUser, Integer langNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("lang_no", langNo);
		if (!generalDAO.isEntityExist("language", conditions)) throw new ValidationException("not_exist", "language");
		conditions.clear();
		conditions.put("lang_no", langNo);
		if (!generalDAO.isEntityExist("labels", conditions)) throw new ValidationException("used_in_screen", "language", "label");
		conditions.clear();
		conditions.put("lang_no", langNo);
		if (!generalDAO.isEntityExist("messages", conditions)) throw new ValidationException("used_in_screen", "language", "message");
		// delete the language
		try {
			languageDAO.deleteLanguage(langNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "language");
		}
	}
	
	public Language getLanguageFromLanguageView(LanguageView languageView)  {
		Language language = new Language();
		try {
			language.setActive(languageView.getActive());
			language.setLangDfl(languageView.getLangDfl());
			language.setLangDir(languageView.getLangDir());
			language.setLangExt(Utils.escapeLiteral(null, languageView.getLangExt(), true).toString());
			language.setLangName(Utils.escapeLiteral(null, languageView.getLangName(), true).toString());
			language.setLangNo(languageView.getLangNo());
			language.setReportExt(Utils.escapeLiteral(null, languageView.getReportExt(), true).toString());
		} catch (SQLException e) {
			 throw new UnauthorizedException("resource");
		}
		return language;
	}

}
