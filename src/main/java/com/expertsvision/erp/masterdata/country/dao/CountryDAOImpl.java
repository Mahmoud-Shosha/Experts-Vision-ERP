package com.expertsvision.erp.masterdata.country.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.country.dto.CountryViewFilter;
import com.expertsvision.erp.masterdata.country.entity.Country;
import com.expertsvision.erp.masterdata.country.entity.CountryView;

@Repository 	
public class CountryDAOImpl implements CountryDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<CountryView> getAllCountryViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM country_view";
		Query<CountryView> query = session.createNativeQuery(sql, CountryView.class);
		List<CountryView> countryViewList = query.getResultList();
		return countryViewList;
	}
	
	@Override
	public CountryView getCountryView(Integer countryNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM country_view WHERE country_no = :countryNo";
		Query<CountryView> query = session.createNativeQuery(sql, CountryView.class);
		query.setParameter("countryNo", countryNo);
		List<CountryView> countryViewList = query.getResultList();
		return countryViewList.isEmpty()? null : countryViewList.get(0);
	}
	
	@Override
	public SinglePage<CountryView> getCountryViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CountryView> countryViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM country_view LIMIT 1 OFFSET :Offset";
			Query<CountryView> query = session.createNativeQuery(sql, CountryView.class);
			query.setParameter("Offset", pageNo - 1);
			countryViewList = query.getResultList();
		}
		if (pageNo <= 0 || countryViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM country_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<CountryView>(null, pageNo, count);
		} else {
			return new SinglePage<CountryView>(countryViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<CountryView> getCountryViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM country_view ORDER BY(region_no, country_no) DESC LIMIT 1";
		Query<CountryView> query = session.createNativeQuery(sql, CountryView.class);
		List<CountryView> countryViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM country_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (countryViewList.isEmpty()) {
			return new SinglePage<CountryView>(null, count, count);
		} else {
			return new SinglePage<CountryView>(countryViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer countryNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT country_no, region_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (region_no, country_no)) FROM country_view)" +
					 "			AS row_number " +
					 "WHERE country_no = :countryNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("countryNo", countryNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<CountryView> getCountryViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CountryView> countryViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  country_view LIMIT 30 OFFSET :Offset";
			Query<CountryView> query = session.createNativeQuery(sql, CountryView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			countryViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM country_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || countryViewList.isEmpty()) {
			return new MultiplePages<CountryView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CountryView>(countryViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<CountryView> getCountryViewFilteredMultiplePages(long pageNo, CountryViewFilter countryViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CountryView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("country_no", countryViewFilter.getCountryNo());
		filters.put("region_no", countryViewFilter.getRegionNo());
		filters.put("country_d_name", countryViewFilter.getCountryDName());
		filters.put("country_f_name", countryViewFilter.getCountryFName());
		filters.put("shortcut", countryViewFilter.getShortcut());
		String filterQuery = GenerateSql.generateFilterQuery("country_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<CountryView> query = session.createNativeQuery(sql, CountryView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<CountryView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CountryView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(country_no) + 1 FROM country";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addCountry(Country country) {
		Session session = sessionFactory.getCurrentSession();
		session.save(country);
		session.flush();
	}
	
	@Override
	public void updateCountry(Country country) {
		Session session = sessionFactory.getCurrentSession();
		Country DBCountry = session.get(Country.class, country.getCountryNo());
		DBCountry.setModifyDate(country.getModifyDate());
		DBCountry.setModifyUser(country.getModifyUser());
		DBCountry.setCountryDName(country.getCountryDName());
		DBCountry.setCountryFName(country.getCountryFName());
		DBCountry.setShortcut(country.getShortcut());
		DBCountry.setRegionNo(country.getRegionNo());
		session.merge(DBCountry);
		session.flush();
	}
	
	@Override
	public void deleteCountry(Integer countryNo) {
		Session session = sessionFactory.getCurrentSession();
		Country DBCountry = session.get(Country.class, countryNo);
		session.delete(DBCountry);
		session.flush();
	}

}
