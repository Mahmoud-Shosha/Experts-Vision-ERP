package com.expertsvision.erp.masterdata.city.dao;

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
import com.expertsvision.erp.masterdata.city.dto.CityViewFilter;
import com.expertsvision.erp.masterdata.city.entity.City;
import com.expertsvision.erp.masterdata.city.entity.CityView;

@Repository 	
public class CityDAOImpl implements CityDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<CityView> getAllCityViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM city_view";
		Query<CityView> query = session.createNativeQuery(sql, CityView.class);
		List<CityView> cityViewList = query.getResultList();
		return cityViewList;
	}
	
	@Override
	public CityView getCityView(Integer cityNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM city_view WHERE city_no = :cityNo";
		Query<CityView> query = session.createNativeQuery(sql, CityView.class);
		query.setParameter("cityNo", cityNo);
		List<CityView> cityViewList = query.getResultList();
		return cityViewList.isEmpty()? null : cityViewList.get(0);
	}
	
	@Override
	public SinglePage<CityView> getCityViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CityView> cityViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM city_view LIMIT 1 OFFSET :Offset";
			Query<CityView> query = session.createNativeQuery(sql, CityView.class);
			query.setParameter("Offset", pageNo - 1);
			cityViewList = query.getResultList();
		}
		if (pageNo <= 0 || cityViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM city_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<CityView>(null, pageNo, count);
		} else {
			return new SinglePage<CityView>(cityViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<CityView> getCityViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM city_view ORDER BY(region_no, country_no, province_no, city_no) DESC LIMIT 1";
		Query<CityView> query = session.createNativeQuery(sql, CityView.class);
		List<CityView> cityViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM city_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (cityViewList.isEmpty()) {
			return new SinglePage<CityView>(null, count, count);
		} else {
			return new SinglePage<CityView>(cityViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer cityNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT city_no, region_no, country_no, province_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (region_no, country_no, province_no, city_no)) FROM city_view)" +
					 "			AS row_number " +
					 "WHERE city_no = :cityNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("cityNo", cityNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<CityView> getCityViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CityView> cityViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  city_view LIMIT 30 OFFSET :Offset";
			Query<CityView> query = session.createNativeQuery(sql, CityView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			cityViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM region_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || cityViewList.isEmpty()) {
			return new MultiplePages<CityView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CityView>(cityViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<CityView> getCityViewFilteredMultiplePages(long pageNo, CityViewFilter cityViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CityView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("city_no", cityViewFilter.getCityNo());
		filters.put("province_no", cityViewFilter.getProvinceNo());
		filters.put("city_d_name", cityViewFilter.getCityDName());
		filters.put("city_f_name", cityViewFilter.getCityFName());
		filters.put("shortcut", cityViewFilter.getShortcut());
		String filterQuery = GenerateSql.generateFilterQuery("city_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<CityView> query = session.createNativeQuery(sql, CityView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<CityView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CityView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(city_no) + 1 FROM city";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addCity(City city) {
		Session session = sessionFactory.getCurrentSession();
		session.save(city);
		session.flush();
	}
	
	@Override
	public void updateCity(City city) {
		Session session = sessionFactory.getCurrentSession();
		City DBCity = session.get(City.class, city.getCityNo());
		DBCity.setModifyDate(city.getModifyDate());
		DBCity.setModifyUser(city.getModifyUser());
		DBCity.setCityDName(city.getCityDName());
		DBCity.setCityFName(city.getCityFName());
		DBCity.setShortcut(city.getShortcut());
		DBCity.setProvinceNo(city.getProvinceNo());
		session.merge(DBCity);
		session.flush();
	}
	
	@Override
	public void deleteCity(Integer cityNo) {
		Session session = sessionFactory.getCurrentSession();
		City DBCity = session.get(City.class, cityNo);
		session.delete(DBCity);
		session.flush();
	}

}
