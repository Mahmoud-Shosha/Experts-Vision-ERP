package com.expertsvision.erp.masterdata.costcenters.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.costcenters.dto.CostCenterViewFilter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterPriv;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterView;

@Repository
public class CostCenterDAOImpl implements CostCenterDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String COST_CENTER_QUERY_FOR_VIEW = "SELECT cost_center_view.* FROM cost_center_priv AS priv "
			+ "LEFT JOIN cost_center_view AS cost_center_view ON cost_center_view.cc_no = priv.cost_center "
			+ "WHERE priv.user_id = :userId AND priv.view_priv = true " + "ORDER BY (cost_center_view.cc_no) ";

	@Override
	public List<CostCenterView> getAllCostCenterViewList(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<CostCenterView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM cost_center_view";
			query = session.createNativeQuery(sql, CostCenterView.class);
		} else {
			sql = COST_CENTER_QUERY_FOR_VIEW;
			query = session.createNativeQuery(sql, CostCenterView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		List<CostCenterView> branchViewList = query.getResultList();
		return branchViewList;
	}

	@Override
	public CostCenterView getCostCenterView(UsersView loginUsersView, Integer ccNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<CostCenterView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM cost_center_view WHERE cc_no = :ccNo";
			query = session.createNativeQuery(sql, CostCenterView.class);
		} else {
			sql = "SELECT * FROM (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r WHERE cc_no = :ccNo";
			query = session.createNativeQuery(sql, CostCenterView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		query.setParameter("ccNo", ccNo);
		List<CostCenterView> branchViewList = query.getResultList();
		return branchViewList.isEmpty() ? null : branchViewList.get(0);
	}

	@Override
	public SinglePage<CostCenterView> getCostCenterViewSinglePage(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<CostCenterView> query;
		List<CostCenterView> branchViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM cost_center_view LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, CostCenterView.class);
			} else {
				sql = "SELECT * FROM (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, CostCenterView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", pageNo - 1);
			branchViewList = query.getResultList();
		}
		if (pageNo <= 0 || branchViewList.isEmpty()) {
			if (loginUsersView == null) {
				sql = "SELECT COUNT(*) FROM cost_center_view";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				count = query2.getSingleResult().longValue();
			} else {
				sql = "SELECT COUNT(*) FROM (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				query2.setParameter("userId", loginUsersView.getUserId());
				count = query2.getSingleResult().longValue();
			}
			return new SinglePage<CostCenterView>(null, pageNo, count);
		} else {
			return new SinglePage<CostCenterView>(branchViewList.get(0), pageNo, null);
		}
	}

	@Override
	public SinglePage<CostCenterView> getCostCenterViewLastPage(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<CostCenterView> query;
		long count;
		if (loginUsersView == null) {
			sql = "SELECT * FROM cost_center_view ORDER BY(cc_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, CostCenterView.class);
		} else {
			sql = "SELECT * FROM (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r ORDER BY(cc_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, CostCenterView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM cost_center_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		List<CostCenterView> branchViewList = query.getResultList();
		if (branchViewList.isEmpty()) {
			return new SinglePage<CostCenterView>(null, count, count);
		} else {
			return new SinglePage<CostCenterView>(branchViewList.get(0), count, count);
		}
	}

	@Override
	public Long getUserViewSinglePageNo(UsersView loginUsersView, Integer ccNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		List<BigInteger> singlePageNoList;
		if (loginUsersView == null) {
			sql = "SELECT row_number FROM" + "			(SELECT cc_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (cc_no)) FROM cost_center_view)" + "			AS row_number "
					+ "WHERE cc_no = :ccNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("ccNo", ccNo);
			singlePageNoList = query.getResultList();
		} else {
			sql = "SELECT row_number FROM" + "			(SELECT cc_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (cc_no)) FROM (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r)"
					+ "			AS row_number " + "WHERE cc_no = :ccNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("ccNo", ccNo);
			query.setParameter("userId", loginUsersView.getUserId());
			singlePageNoList = query.getResultList();
		}
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}

	@Override
	public MultiplePages<CostCenterView> getCostCenterViewMultiplePages(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<CostCenterView> query;
		List<CostCenterView> branchViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM  cost_center_view LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CostCenterView.class);
			} else {
				sql = "SELECT * FROM  (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CostCenterView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			branchViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM cost_center_view AS foo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || branchViewList.isEmpty()) {
			return new MultiplePages<CostCenterView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<CostCenterView>(branchViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public MultiplePages<CostCenterView> getCostCenterViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CostCenterViewFilter costCenterViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<CostCenterView> query;
		List<CostCenterView> branchViewList = null;
		long count;
		String filterQuery;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("cc_no", costCenterViewFilter.getCcNo());
		filters.put("cc_d_name", costCenterViewFilter.getCcDName());
		filters.put("cc_f_name", costCenterViewFilter.getCcFName());
		filters.put("cc_group", costCenterViewFilter.getCcGroup());
		filters.put("parent_cc", costCenterViewFilter.getParentCc());
		filters.put("sub", costCenterViewFilter.getSub());
		if (pageNo > 0) {
			if (loginUsersView == null) {
				filterQuery = GenerateSql.generateFilterQuery("cost_center_view", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CostCenterView.class);
			} else {
				filterQuery = GenerateSql.generateFilterQuery(" (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r ", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CostCenterView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			branchViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			filterQuery = GenerateSql.generateFilterQuery("cost_center_view", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			filterQuery = GenerateSql.generateFilterQuery(" (" + COST_CENTER_QUERY_FOR_VIEW + ") AS r ", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || branchViewList.isEmpty()) {
			return new MultiplePages<CostCenterView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<CostCenterView>(branchViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public Object getNextPK(Integer parentCc) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(cc_no) FROM cost_center WHERE parent_cc = :parentCc";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		query.setParameter("parentCc", parentCc);
		Object nextPK = query.getSingleResult();
		return nextPK;
	}

	@Override
	public void addCostCenter(CostCenter costCenter) {
		Session session = sessionFactory.getCurrentSession();
		session.save(costCenter);
	}

	@Override
	public void addCostCenterPriv(CostCenterPriv costCenterPriv) {
		Session session = sessionFactory.getCurrentSession();
		session.save(costCenterPriv);
	}

	@Override
	public void updateCostCenter(CostCenter costCenter) {
		Session session = sessionFactory.getCurrentSession();
		CostCenter DBCostCenter = session.get(CostCenter.class, costCenter.getCcNo());
		DBCostCenter.setCcDName(costCenter.getCcDName());
		DBCostCenter.setCcFName(costCenter.getCcFName());
		DBCostCenter.setCcGroup(costCenter.getCcGroup());
		DBCostCenter.setInactive(costCenter.getInactive());
		DBCostCenter.setInactiveDate(costCenter.getInactiveDate());
		DBCostCenter.setInactiveReason(costCenter.getInactiveReason());
		DBCostCenter.setInactiveUser(costCenter.getInactiveUser());
		DBCostCenter.setModifyDate(costCenter.getModifyDate());
		DBCostCenter.setModifyUser(costCenter.getModifyUser());
		DBCostCenter.setParentCc(costCenter.getParentCc());
		DBCostCenter.setSub(costCenter.getSub());
		session.merge(DBCostCenter);
	}

	@Override
	public void deleteCostCenter(Integer ccNo) {
		Session session = sessionFactory.getCurrentSession();
		CostCenter DBCostCenter = session.get(CostCenter.class, ccNo);
		session.delete(DBCostCenter);
	}

	@Override
	public boolean hasSubCostCenters(Integer ccNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT 1 FROM cost_center WHERE parent_cc = :parentCc";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		query.setParameter("parentCc", ccNo);
		return query.getResultList().size() > 0;
	}

}
