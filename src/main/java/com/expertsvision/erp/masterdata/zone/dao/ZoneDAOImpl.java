package com.expertsvision.erp.masterdata.zone.dao;

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
import com.expertsvision.erp.masterdata.zone.dto.ZoneViewFilter;
import com.expertsvision.erp.masterdata.zone.entity.Zone;
import com.expertsvision.erp.masterdata.zone.entity.ZoneView;

@Repository 	
public class ZoneDAOImpl implements ZoneDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<ZoneView> getAllZoneViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM zone_view";
		Query<ZoneView> query = session.createNativeQuery(sql, ZoneView.class);
		List<ZoneView> zoneViewList = query.getResultList();
		return zoneViewList;
	}
	
	@Override
	public ZoneView getZoneView(Integer zoneNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM zone_view WHERE zone_no = :zoneNo";
		Query<ZoneView> query = session.createNativeQuery(sql, ZoneView.class);
		query.setParameter("zoneNo", zoneNo);
		List<ZoneView> zoneViewList = query.getResultList();
		return zoneViewList.isEmpty()? null : zoneViewList.get(0);
	}
	
	@Override
	public SinglePage<ZoneView> getZoneViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ZoneView> zoneViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM zone_view LIMIT 1 OFFSET :Offset";
			Query<ZoneView> query = session.createNativeQuery(sql, ZoneView.class);
			query.setParameter("Offset", pageNo - 1);
			zoneViewList = query.getResultList();
		}
		if (pageNo <= 0 || zoneViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM zone_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<ZoneView>(null, pageNo, count);
		} else {
			return new SinglePage<ZoneView>(zoneViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<ZoneView> getZoneViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM zone_view ORDER BY(region_no, country_no, province_no, city_no, zone_no) DESC LIMIT 1";
		Query<ZoneView> query = session.createNativeQuery(sql, ZoneView.class);
		List<ZoneView> zoneViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM zone_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (zoneViewList.isEmpty()) {
			return new SinglePage<ZoneView>(null, count, count);
		} else {
			return new SinglePage<ZoneView>(zoneViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer zoneNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT zone_no, region_no, country_no, province_no, city_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (region_no, country_no, province_no, city_no, zone_no)) FROM zone_view)" +
					 "			AS row_number " +
					 "WHERE zone_no = :zoneNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("zoneNo", zoneNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<ZoneView> getZoneViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ZoneView> zoneViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  zone_view LIMIT 30 OFFSET :Offset";
			Query<ZoneView> query = session.createNativeQuery(sql, ZoneView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			zoneViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM region_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || zoneViewList.isEmpty()) {
			return new MultiplePages<ZoneView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ZoneView>(zoneViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<ZoneView> getZoneViewFilteredMultiplePages(long pageNo, ZoneViewFilter zoneViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ZoneView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("zone_no", zoneViewFilter.getZoneNo());
		filters.put("city_no", zoneViewFilter.getCityNo());
		filters.put("zone_d_name", zoneViewFilter.getZoneDName());
		filters.put("zone_f_name", zoneViewFilter.getZoneFName());
		String filterQuery = GenerateSql.generateFilterQuery("zone_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<ZoneView> query = session.createNativeQuery(sql, ZoneView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<ZoneView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ZoneView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(zone_no) + 1 FROM zone";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addZone(Zone zone) {
		Session session = sessionFactory.getCurrentSession();
		session.save(zone);
		session.flush();
	}
	
	@Override
	public void updateZone(Zone zone) {
		Session session = sessionFactory.getCurrentSession();
		Zone DBZone = session.get(Zone.class, zone.getZoneNo());
		DBZone.setModifyDate(zone.getModifyDate());
		DBZone.setModifyUser(zone.getModifyUser());
		DBZone.setZoneDName(zone.getZoneDName());
		DBZone.setZoneFName(zone.getZoneFName());
		DBZone.setCityNo(zone.getCityNo());
		session.merge(DBZone);
		session.flush();
	}
	
	@Override
	public void deleteZone(Integer zoneNo) {
		Session session = sessionFactory.getCurrentSession();
		Zone DBZone = session.get(Zone.class, zoneNo);
		session.delete(DBZone);
		session.flush();
	}

}
