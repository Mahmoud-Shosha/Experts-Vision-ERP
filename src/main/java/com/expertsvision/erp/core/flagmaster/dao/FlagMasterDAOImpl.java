package com.expertsvision.erp.core.flagmaster.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.flagmaster.dto.FlagMasterViewFilter;
import com.expertsvision.erp.core.flagmaster.entity.FlagMaster;
import com.expertsvision.erp.core.flagmaster.entity.FlagMasterView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class FlagMasterDAOImpl implements FlagMasterDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FlagMasterView> getFlagMasterViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_master_view";
		Query<FlagMasterView> query = session.createNativeQuery(sql, FlagMasterView.class);
		List<FlagMasterView> fagMasterViewList = query.getResultList();
		return fagMasterViewList;
	}
	
	@Override
	public FlagMasterView getFlagMasterView(String flagCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_master_view WHERE flag_code = :flagCode";
		Query<FlagMasterView> query = session.createNativeQuery(sql, FlagMasterView.class);
		query.setParameter("flagCode", flagCode);
		List<FlagMasterView> fagMasterViewList = query.getResultList();
		return fagMasterViewList.isEmpty()? null : fagMasterViewList.get(0);
	}
	
	@Override
	public SinglePage<FlagMasterView> getFlagMastersViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FlagMasterView> fagMasterViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM flag_master_view LIMIT 1 OFFSET :Offset";
			Query<FlagMasterView> query = session.createNativeQuery(sql, FlagMasterView.class);
			query.setParameter("Offset", pageNo - 1);
			fagMasterViewList = query.getResultList();
		}
		if (pageNo <= 0 || fagMasterViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM flag_master";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<FlagMasterView>(null, pageNo, count);
		} else {
			return new SinglePage<FlagMasterView>(fagMasterViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<FlagMasterView> getFlagMastersViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_master_view ORDER BY(flag_code) DESC LIMIT 1";
		Query<FlagMasterView> query = session.createNativeQuery(sql, FlagMasterView.class);
		List<FlagMasterView> fagMasterViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM flag_master";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (fagMasterViewList.isEmpty()) {
			return new SinglePage<FlagMasterView>(null, count, count);
		} else {
			return new SinglePage<FlagMasterView>(fagMasterViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getFlagMasterViewSinglePageNo(String flagCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT flag_code, ROW_NUMBER()" +
					 "						OVER(ORDER BY (flag_code)) FROM flag_master)" +
					 "			AS row_number " +
					 "WHERE flag_code = :flagCode";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("flagCode", flagCode);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<FlagMasterView> getFlagMastersViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FlagMasterView> flagMasterViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM flag_master_view LIMIT 30 OFFSET :Offset";
			Query<FlagMasterView> query = session.createNativeQuery(sql, FlagMasterView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			flagMasterViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM flag_master";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || flagMasterViewList.isEmpty()) {
			return new MultiplePages<FlagMasterView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<FlagMasterView>(flagMasterViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<FlagMasterView> getFlagMastersViewFilteredMultiplePages(long pageNo, FlagMasterViewFilter flagMasterViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FlagMasterView> flagMasterViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("flag_code", flagMasterViewFilter.getFlagCode());
		filters.put("label_code", flagMasterViewFilter.getLabelCode());
		String filterQuery = GenerateSql.generateFilterQuery("flag_master_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<FlagMasterView> query = session.createNativeQuery(sql, FlagMasterView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			flagMasterViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || flagMasterViewList.isEmpty()) {
			return new MultiplePages<FlagMasterView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<FlagMasterView>(flagMasterViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addFlagMaster(FlagMaster flagMaster) {
		Session session = sessionFactory.getCurrentSession();
		session.save(flagMaster);
		session.flush();
	}
	
	@Override
	public void updateFlagMaster(FlagMaster flagMaster) {
		Session session = sessionFactory.getCurrentSession();
		FlagMaster DBFlagMaster = session.get(FlagMaster.class, flagMaster.getFlagCode());
		DBFlagMaster.setFlagCode(flagMaster.getFlagCode());
		DBFlagMaster.setLabelCode(flagMaster.getLabelCode());
		DBFlagMaster.setLangNo(flagMaster.getLangNo());
		session.merge(DBFlagMaster);
		session.flush();
	}
	
	@Override
	public void deleteFlagMaster(String flagCode) {
		Session session = sessionFactory.getCurrentSession();
		FlagMaster DBFlagMaster = session.get(FlagMaster.class, flagCode);
		session.delete(DBFlagMaster);
		session.flush();
	}

}
