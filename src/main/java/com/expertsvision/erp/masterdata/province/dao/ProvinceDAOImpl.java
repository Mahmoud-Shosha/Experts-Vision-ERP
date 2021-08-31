package com.expertsvision.erp.masterdata.province.dao;

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
import com.expertsvision.erp.masterdata.province.dto.ProvinceViewFilter;
import com.expertsvision.erp.masterdata.province.entity.Province;
import com.expertsvision.erp.masterdata.province.entity.ProvinceView;

@Repository 	
public class ProvinceDAOImpl implements ProvinceDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<ProvinceView> getAllProvinceViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM province_view";
		Query<ProvinceView> query = session.createNativeQuery(sql, ProvinceView.class);
		List<ProvinceView> provinceViewList = query.getResultList();
		return provinceViewList;
	}
	
	@Override
	public ProvinceView getProvinceView(Integer provinceNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM province_view WHERE province_no = :provinceNo";
		Query<ProvinceView> query = session.createNativeQuery(sql, ProvinceView.class);
		query.setParameter("provinceNo", provinceNo);
		List<ProvinceView> provinceViewList = query.getResultList();
		return provinceViewList.isEmpty()? null : provinceViewList.get(0);
	}
	
	@Override
	public SinglePage<ProvinceView> getProvinceViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ProvinceView> provinceViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM province_view LIMIT 1 OFFSET :Offset";
			Query<ProvinceView> query = session.createNativeQuery(sql, ProvinceView.class);
			query.setParameter("Offset", pageNo - 1);
			provinceViewList = query.getResultList();
		}
		if (pageNo <= 0 || provinceViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM province_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<ProvinceView>(null, pageNo, count);
		} else {
			return new SinglePage<ProvinceView>(provinceViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<ProvinceView> getProvinceViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM province_view ORDER BY(region_no, country_no, province_no) DESC LIMIT 1";
		Query<ProvinceView> query = session.createNativeQuery(sql, ProvinceView.class);
		List<ProvinceView> provinceViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM province_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (provinceViewList.isEmpty()) {
			return new SinglePage<ProvinceView>(null, count, count);
		} else {
			return new SinglePage<ProvinceView>(provinceViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer provinceNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT province_no, region_no, country_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (region_no, country_no, province_no)) FROM province_view)" +
					 "			AS row_number " +
					 "WHERE province_no = :provinceNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("provinceNo", provinceNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<ProvinceView> getProvinceViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ProvinceView> provinceViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  province_view LIMIT 30 OFFSET :Offset";
			Query<ProvinceView> query = session.createNativeQuery(sql, ProvinceView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			provinceViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM province_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || provinceViewList.isEmpty()) {
			return new MultiplePages<ProvinceView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ProvinceView>(provinceViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<ProvinceView> getProvinceViewFilteredMultiplePages(long pageNo, ProvinceViewFilter provinceViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ProvinceView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("province_no", provinceViewFilter.getProvinceNo());
		filters.put("country_no", provinceViewFilter.getCountryNo());
		filters.put("province_d_name", provinceViewFilter.getProvinceDName());
		filters.put("province_f_name", provinceViewFilter.getProvinceFName());
		filters.put("shortcut", provinceViewFilter.getShortcut());
		String filterQuery = GenerateSql.generateFilterQuery("province_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<ProvinceView> query = session.createNativeQuery(sql, ProvinceView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<ProvinceView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ProvinceView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(province_no) + 1 FROM province";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addProvince(Province province) {
		Session session = sessionFactory.getCurrentSession();
		session.save(province);
		session.flush();
	}
	
	@Override
	public void updateProvince(Province province) {
		Session session = sessionFactory.getCurrentSession();
		Province DBProvince = session.get(Province.class, province.getProvinceNo());
		DBProvince.setModifyDate(province.getModifyDate());
		DBProvince.setModifyUser(province.getModifyUser());
		DBProvince.setProvinceDName(province.getProvinceDName());
		DBProvince.setProvinceFName(province.getProvinceFName());
		DBProvince.setShortcut(province.getShortcut());
		DBProvince.setCountryNo(province.getCountryNo());
		session.merge(DBProvince);
		session.flush();
	}
	
	@Override
	public void deleteProvince(Integer provinceNo) {
		Session session = sessionFactory.getCurrentSession();
		Province DBProvince = session.get(Province.class, provinceNo);
		session.delete(DBProvince);
		session.flush();
	}

}
