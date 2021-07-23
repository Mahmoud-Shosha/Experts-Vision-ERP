package com.expertsvision.erp.core.flagdetail.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.flagdetail.dto.FlagDetailViewFilter;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetail;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailMainTree;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailPK;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository
public class FlagDetailDAOImpl implements FlagDetailDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FlagDetailView> getFlagDetailViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_detail_view";
		Query<FlagDetailView> query = session.createNativeQuery(sql, FlagDetailView.class);
		List<FlagDetailView> fagDetailViewList = query.getResultList();
		return fagDetailViewList;
	}

	@Override
	public List<FlagDetailView> getFlagDetailViewList(String flagCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_detail_view WHERE flag_code = :flagCode";
		Query<FlagDetailView> query = session.createNativeQuery(sql, FlagDetailView.class);
		query.setParameter("flagCode", flagCode);
		List<FlagDetailView> fagDetailViewList = query.getResultList();
		return fagDetailViewList;
	}

	@Override
	public List<FlagDetailMainTree> getFlagDetailMainTree(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_detail_main_tree WHERE user_id = :userId"
				   + "                                    AND active = true AND (flag_priv = false OR view_priv = true)";
		Query<FlagDetailMainTree> query = session.createNativeQuery(sql, FlagDetailMainTree.class);
		query.setParameter("userId", userId);
		List<FlagDetailMainTree> FlagDetailMainTree = query.getResultList();
		return FlagDetailMainTree;
	}

	@Override
	public FlagDetailView getFlagDetailView(String flagCode, Integer flagValue) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_detail_view WHERE flag_code = :flagCode AND flag_value = :flagValue";
		Query<FlagDetailView> query = session.createNativeQuery(sql, FlagDetailView.class);
		query.setParameter("flagCode", flagCode);
		query.setParameter("flagValue", flagValue);
		List<FlagDetailView> fagDetailViewList = query.getResultList();
		return fagDetailViewList.isEmpty() ? null : fagDetailViewList.get(0);
	}

	@Override
	public SinglePage<FlagDetailView> getFlagDetailsViewSinglePage(String flagCode, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FlagDetailView> fagDetailViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM flag_detail_view WHERE flag_code = :flagCode LIMIT 1 OFFSET :Offset";
			Query<FlagDetailView> query = session.createNativeQuery(sql, FlagDetailView.class);
			query.setParameter("flagCode", flagCode);
			query.setParameter("Offset", pageNo - 1);
			fagDetailViewList = query.getResultList();
		}
		if (pageNo <= 0 || fagDetailViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM flag_detail WHERE flag_code = :flagCode";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("flagCode", flagCode);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<FlagDetailView>(null, pageNo, count);
		} else {
			return new SinglePage<FlagDetailView>(fagDetailViewList.get(0), pageNo, null);
		}
	}

	@Override
	public SinglePage<FlagDetailView> getFlagDetailsViewLastPage(String flagCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_detail_view WHERE flag_code = :flagCode ORDER BY(flag_code, flag_sr, flag_value) DESC LIMIT 1";
		Query<FlagDetailView> query = session.createNativeQuery(sql, FlagDetailView.class);
		query.setParameter("flagCode", flagCode);
		List<FlagDetailView> fagDetailViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM flag_detail WHERE flag_code = :flagCode";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		query2.setParameter("flagCode", flagCode);
		long count = query2.getSingleResult().longValue();
		if (fagDetailViewList.isEmpty()) {
			return new SinglePage<FlagDetailView>(null, count, count);
		} else {
			return new SinglePage<FlagDetailView>(fagDetailViewList.get(0), count, count);
		}
	}

	@Override
	public Long getFlagDetailViewSinglePageNo(String flagCode, Integer flagValue) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" + "			(SELECT flag_code, flag_sr, flag_value, ROW_NUMBER()"
				+ "						OVER(ORDER BY (flag_code, flag_sr, flag_value))"
				+ "                      FROM (SELECT * FROM flag_detail WHERE flag_code = :flagCode) AS selected_rows)"
				+ "			AS row_number " + "WHERE flag_code = :flagCode AND flag_value = :flagValue";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("flagCode", flagCode);
		query.setParameter("flagValue", flagValue);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}

	@Override
	public MultiplePages<FlagDetailView> getFlagDetailsViewMultiplePages(String flagCode, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FlagDetailView> flagDetailViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM flag_detail_view WHERE flag_code = :flagCode LIMIT 30 OFFSET :Offset";
			Query<FlagDetailView> query = session.createNativeQuery(sql, FlagDetailView.class);
			query.setParameter("flagCode", flagCode);
			query.setParameter("Offset", (pageNo - 1) * 30);
			flagDetailViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM flag_detail WHERE flag_code = :flagCode";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		query2.setParameter("flagCode", flagCode);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || flagDetailViewList.isEmpty()) {
			return new MultiplePages<FlagDetailView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<FlagDetailView>(flagDetailViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public MultiplePages<FlagDetailView> getFlagDetailsViewFilteredMultiplePages(String flagCode, long pageNo,
			FlagDetailViewFilter flagDetailViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FlagDetailView> flagDetailViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("active", flagDetailViewFilter.getActive());
		filters.put("flag_code", flagDetailViewFilter.getFlagCode());
		filters.put("flag_priv", flagDetailViewFilter.getFlagPriv());
		filters.put("flag_value", flagDetailViewFilter.getFlagValue());
		filters.put("label_code", flagDetailViewFilter.getLabelCode());
		String filterQuery = GenerateSql.generateFilterQuery("flag_detail_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " AND flag_code = :flagCode LIMIT 30 OFFSET :Offset";
			Query<FlagDetailView> query = session.createNativeQuery(sql, FlagDetailView.class);
			query.setParameter("flagCode", flagCode);
			query.setParameter("Offset", (pageNo - 1) * 30);
			flagDetailViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + " AND flag_code = :flagCode) As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		query2.setParameter("flagCode", flagCode);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || flagDetailViewList.isEmpty()) {
			return new MultiplePages<FlagDetailView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<FlagDetailView>(flagDetailViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public void addFlagDetail(FlagDetail flagDetail) {
		Session session = sessionFactory.getCurrentSession();
		session.save(flagDetail);
		session.flush();
	}

	@Override
	public void updateFlagDetail(FlagDetail flagDetail) {
		Session session = sessionFactory.getCurrentSession();
		FlagDetail DBFlagDetail = session.get(FlagDetail.class,
				new FlagDetailPK(flagDetail.getFlagCode(), flagDetail.getFlagValue()));
		DBFlagDetail.setActive(flagDetail.getActive());
		DBFlagDetail.setFlagPriv(flagDetail.getFlagPriv());
		DBFlagDetail.setFlagSr(flagDetail.getFlagSr());
		DBFlagDetail.setLabelCode(flagDetail.getLabelCode());
		DBFlagDetail.setLangNo(flagDetail.getLangNo());
		session.merge(DBFlagDetail);
		session.flush();
	}

	@Override
	public void deleteFlagDetail(String flagCode, Integer flagValue) {
		Session session = sessionFactory.getCurrentSession();
		FlagDetail DBFlagDetail = session.get(FlagDetail.class, new FlagDetailPK(flagCode, flagValue));
		session.delete(DBFlagDetail);
		session.flush();
	}

}
