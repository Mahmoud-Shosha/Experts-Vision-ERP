package com.expertsvision.erp.masterdata.region.dao;

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
import com.expertsvision.erp.masterdata.region.dto.RegionViewFilter;
import com.expertsvision.erp.masterdata.region.entity.Region;
import com.expertsvision.erp.masterdata.region.entity.RegionView;

@Repository 	
public class RegionDAOImpl implements RegionDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<RegionView> getAllRegionViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM region_view";
		Query<RegionView> query = session.createNativeQuery(sql, RegionView.class);
		List<RegionView> regionViewList = query.getResultList();
		return regionViewList;
	}
	
	@Override
	public RegionView getRegionView(Integer regionNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM region_view WHERE region_no = :regionNo";
		Query<RegionView> query = session.createNativeQuery(sql, RegionView.class);
		query.setParameter("regionNo", regionNo);
		List<RegionView> regionViewList = query.getResultList();
		return regionViewList.isEmpty()? null : regionViewList.get(0);
	}
	
	@Override
	public SinglePage<RegionView> getRegionViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<RegionView> regionViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM region_view LIMIT 1 OFFSET :Offset";
			Query<RegionView> query = session.createNativeQuery(sql, RegionView.class);
			query.setParameter("Offset", pageNo - 1);
			regionViewList = query.getResultList();
		}
		if (pageNo <= 0 || regionViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM region_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<RegionView>(null, pageNo, count);
		} else {
			return new SinglePage<RegionView>(regionViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<RegionView> getRegionViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM region_view ORDER BY(region_no) DESC LIMIT 1";
		Query<RegionView> query = session.createNativeQuery(sql, RegionView.class);
		List<RegionView> regionViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM region_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (regionViewList.isEmpty()) {
			return new SinglePage<RegionView>(null, count, count);
		} else {
			return new SinglePage<RegionView>(regionViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer regionNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT region_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (region_no)) FROM region_view)" +
					 "			AS row_number " +
					 "WHERE region_no = :regionNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("regionNo", regionNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<RegionView> getRegionViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<RegionView> regionViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  region_view LIMIT 30 OFFSET :Offset";
			Query<RegionView> query = session.createNativeQuery(sql, RegionView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			regionViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM region_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || regionViewList.isEmpty()) {
			return new MultiplePages<RegionView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<RegionView>(regionViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<RegionView> getRegionViewFilteredMultiplePages(long pageNo, RegionViewFilter regionViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<RegionView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("region_no", regionViewFilter.getRegionNo());
		filters.put("region_d_name", regionViewFilter.getRegionDName());
		filters.put("region_f_name", regionViewFilter.getRegionFName());
		filters.put("shortcut", regionViewFilter.getShortcut());
		String filterQuery = GenerateSql.generateFilterQuery("region_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<RegionView> query = session.createNativeQuery(sql, RegionView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<RegionView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<RegionView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(region_no) + 1 FROM region";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addRegion(Region region) {
		Session session = sessionFactory.getCurrentSession();
		session.save(region);
		session.flush();
	}
	
	@Override
	public void updateRegion(Region region) {
		Session session = sessionFactory.getCurrentSession();
		Region DBRegion = session.get(Region.class, region.getRegionNo());
		DBRegion.setModifyDate(region.getModifyDate());
		DBRegion.setModifyUser(region.getModifyUser());
		DBRegion.setRegionDName(region.getRegionDName());
		DBRegion.setRegionFName(region.getRegionFName());
		DBRegion.setShortcut(region.getShortcut());
		session.merge(DBRegion);
		session.flush();
	}
	
	@Override
	public void deleteRegion(Integer regionNo) {
		Session session = sessionFactory.getCurrentSession();
		Region DBRegion = session.get(Region.class, regionNo);
		session.delete(DBRegion);
		session.flush();
	}

}
