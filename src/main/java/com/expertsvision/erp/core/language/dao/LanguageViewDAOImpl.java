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
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
@Transactional
public class LanguageViewDAOImpl implements LanguageViewDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LanguageView> getLanguageViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_language_view_list()";
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> languageViewList = query.getResultList();
		return languageViewList;
	}
	
	@Override
	public LanguageView getLanguageView(Integer langNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_language_view(%s)";
		sql = String.format(sql, langNo);
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> languageViewList = query.getResultList();
		return languageViewList.isEmpty()? null : languageViewList.get(0);
	}
	
	@Override
	public SinglePage<LanguageView> getLanguagesViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_language_view_single_page(%s)";
		sql = String.format(sql, pageNo);
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> languagesViewList = query.getResultList();
		if (languagesViewList.isEmpty()) {
			sql = "SELECT * FROM get_table_rows_count('%s')";
			sql = String.format(sql, "language");
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<LanguageView>(null, pageNo, count);
		} else {
			return new SinglePage<LanguageView>(languagesViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<LanguageView> getLanguagesViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_language_view_last_page()";
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> languagesViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "language");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (languagesViewList.isEmpty()) {
			return new SinglePage<LanguageView>(null, count, count);
		} else {
			return new SinglePage<LanguageView>(languagesViewList.get(0), count, count);
		}
	}
	
	@Override
	public MultiplePages<LanguageView> getLanguagesViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_language_view_multiple_pages(%s)";
		sql = String.format(sql, pageNo);
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> languagesViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "language");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (languagesViewList.isEmpty()) {
			return new MultiplePages<LanguageView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LanguageView>(languagesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<LanguageView> getLanguagesViewFilteredMultiplePages(long pageNo, LanguageViewFilter LanguageViewFilter) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("lang_no", LanguageViewFilter.getLangNo());
		filters.put("lang_name", LanguageViewFilter.getLangName());
		filters.put("active", LanguageViewFilter.getActive());
		filters.put("lang_dfl", LanguageViewFilter.getLangDfl());
		String filterQuery = GenerateSql.generateFilterQuery("language_view", filters);
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_language_view_filtered_multiple_pages(%s, '%s')";
		sql = String.format(sql, pageNo, filterQuery);
		Query<LanguageView> query = session.createNativeQuery(sql, LanguageView.class);
		List<LanguageView> languagesViewList = query.getResultList();
		sql = "SELECT * FROM get_query_rows_count('%s')";
		sql = String.format(sql, filterQuery);
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (languagesViewList.isEmpty()) {
			return new MultiplePages<LanguageView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LanguageView>(languagesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public List<String> addLanguageView(UsersView loginUser, LanguageView languageView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(add_language_view(%s, %s, '%s', %s, '%s', '%s', %s, %s))";
		sql = String.format(sql, loginUser.getUserId(), languageView.getLangNo(), languageView.getLangName(), languageView.getLangDir(), languageView.getReportExt(),
								 languageView.getLangExt(), languageView.getLangDfl(), languageView.getActive());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> updateLanguageView(UsersView loginUser, LanguageView languageView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(update_language_view(%s, %s, '%s', %s, '%s', '%s', %s, %s))";
		sql = String.format(sql, loginUser.getUserId(), languageView.getLangNo(), languageView.getLangName(), languageView.getLangDir(), languageView.getReportExt(),
								 languageView.getLangExt(), languageView.getLangDfl(), languageView.getActive());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> deleteLanguageView(UsersView loginUser, Integer langNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(delete_language_view(%s, %s))";
		sql = String.format(sql, loginUser.getUserId(), langNo);
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}

}
