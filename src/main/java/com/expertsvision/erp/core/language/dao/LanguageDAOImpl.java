package com.expertsvision.erp.core.language.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.Language;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class LanguageDAOImpl implements LanguageDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LanguageView> getLanguageViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM language_view";
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> languageViewList = query.getResultList();
		return languageViewList;
	}
	
	@Override
	public LanguageView getLanguageView(Integer langNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM language_view WHERE lang_no = :langNo";
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		query.setParameter("langNo", langNo);
		List<LanguageView> languageViewList = query.getResultList();
		return languageViewList.isEmpty()? null : languageViewList.get(0);
	}
	
	@Override
	public SinglePage<LanguageView> getLanguagesViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<LanguageView> languageViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM language_view LIMIT 1 OFFSET :Offset";
			Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
			query.setParameter("Offset", pageNo - 1);
			languageViewList = query.getResultList();
		}
		if (pageNo <= 0 || languageViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM language";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<LanguageView>(null, pageNo, count);
		} else {
			return new SinglePage<LanguageView>(languageViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<LanguageView> getLanguagesViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM language_view ORDER BY(lang_no) DESC LIMIT 1";
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> messagesViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM language";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (messagesViewList.isEmpty()) {
			return new SinglePage<LanguageView>(null, count, count);
		} else {
			return new SinglePage<LanguageView>(messagesViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getLanguageViewSinglePageNo(Integer langNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT lang_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (lang_no)) FROM language)" +
					 "			AS row_number " +
					 "WHERE lang_no = :langNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("langNo", langNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<LanguageView> getLanguagesViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<LanguageView> languageViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM language_view LIMIT 30 OFFSET :Offset";
			Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			languageViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM language";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || languageViewList.isEmpty()) {
			return new MultiplePages<LanguageView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LanguageView>(languageViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<LanguageView> getLanguagesViewFilteredMultiplePages(long pageNo, LanguageViewFilter LanguageViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<LanguageView> languageViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("lang_no", LanguageViewFilter.getLangNo());
		filters.put("lang_name", LanguageViewFilter.getLangName());
		filters.put("active", LanguageViewFilter.getActive());
		filters.put("lang_dfl", LanguageViewFilter.getLangDfl());
		String filterQuery = GenerateSql.generateFilterQuery("language_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			languageViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || languageViewList.isEmpty()) {
			return new MultiplePages<LanguageView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LanguageView>(languageViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(lang_no) + 1 FROM language_view";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addLanguage(Language language) {
		Session session = sessionFactory.getCurrentSession();
		session.save(language);
		session.flush();
	}
	
	@Override
	public void updateLanguage(Language language) {
		Session session = sessionFactory.getCurrentSession();
		Language DBLanguage = session.get(Language.class, language.getLangNo());
		DBLanguage.setActive(language.getActive());
		DBLanguage.setLangDfl(language.getLangDfl());
		DBLanguage.setLangDir(language.getLangDir());
		DBLanguage.setLangExt(language.getLangExt());
		DBLanguage.setLangName(language.getLangName());
		DBLanguage.setLangNo(language.getLangNo());
		DBLanguage.setReportExt(language.getReportExt());
		session.merge(DBLanguage);
		session.flush();
	}
	
	@Override
	public void deleteLanguage(Integer langNo) {
		Session session = sessionFactory.getCurrentSession();
		Language DBLanguage = session.get(Language.class, langNo);
		session.delete(DBLanguage);
		session.flush();
	}

}
