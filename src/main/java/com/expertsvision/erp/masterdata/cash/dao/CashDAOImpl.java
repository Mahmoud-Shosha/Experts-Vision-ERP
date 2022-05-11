package com.expertsvision.erp.masterdata.cash.dao;

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
import com.expertsvision.erp.masterdata.cash.dto.CashInHandViewFilter;
import com.expertsvision.erp.masterdata.cash.entity.CashInHand;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandDtlView;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandPriv;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandView;

@Repository
public class CashDAOImpl implements CashDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String CASHS_QUERY_FOR_VIEW = "SELECT cash_in_hand_view.* FROM cash_in_hand_priv AS priv "
			+ "					LEFT JOIN cash_in_hand_view AS cash_in_hand_view ON cash_in_hand_view.cash_no = priv.cash_no "
			+ "					LEFT JOIN cash_in_hand_dtl AS cash_in_hand_dtl ON cash_in_hand_dtl.cash_no = priv.cash_no "
			+ "					LEFT JOIN accounts_priv AS accounts_priv ON accounts_priv.user_id = :userId AND accounts_priv.acc_no = cash_in_hand_view.acc_no AND accounts_priv.acc_curr = cash_in_hand_dtl.acc_curr "
			+ "					LEFT JOIN branches_priv AS branches_priv ON branches_priv.user_id = :userId "
			+ "					WHERE priv.user_id = :userId AND priv.view_priv = true AND accounts_priv.view_priv = true AND branches_priv.view_priv = true "
			+ "					ORDER BY (cash_in_hand_view.cash_no) ";

	@Override
	public List<CashInHandView> getAllCashInHandViewList(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<CashInHandView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM cash_in_hand_view";
			query = session.createNativeQuery(sql, CashInHandView.class);
		} else {
			sql = CASHS_QUERY_FOR_VIEW;
			query = session.createNativeQuery(sql, CashInHandView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		List<CashInHandView> cashInHandViewList = query.getResultList();
		return cashInHandViewList;
	}

	@Override
	public List<CashInHandDtlView> getCashInHandDtlViewList(Integer cashNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM  cash_in_hand_dtl_view WHERE cash_no = :cashNo";
		Query<CashInHandDtlView> query = session.createNativeQuery(sql, CashInHandDtlView.class);
		query.setParameter("cashNo", cashNo);
		List<CashInHandDtlView> cashInHandsDtlViewList = query.getResultList();
		return cashInHandsDtlViewList;
	}

	@Override
	public CashInHandView getCashInHandView(UsersView loginUsersView, Integer cashNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<CashInHandView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM cash_in_hand_view WHERE cash_no = :cashNo";
			query = session.createNativeQuery(sql, CashInHandView.class);
		} else {
			sql = "SELECT * FROM (" + CASHS_QUERY_FOR_VIEW + ") AS r WHERE cash_no = :cashNo";
			query = session.createNativeQuery(sql, CashInHandView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		query.setParameter("cashNo", cashNo);
		List<CashInHandView> cashInHandViewList = query.getResultList();
		return cashInHandViewList.isEmpty() ? null : cashInHandViewList.get(0);
	}

	@Override
	public SinglePage<CashInHandView> getCashInHandViewSinglePage(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<CashInHandView> query;
		List<CashInHandView> cashInHandViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM cash_in_hand_view LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, CashInHandView.class);
			} else {
				sql = "SELECT * FROM (" + CASHS_QUERY_FOR_VIEW + ") AS r LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, CashInHandView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", pageNo - 1);
			cashInHandViewList = query.getResultList();
		}
		if (pageNo <= 0 || cashInHandViewList.isEmpty()) {
			if (loginUsersView == null) {
				sql = "SELECT COUNT(*) FROM cash_in_hand_view";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				count = query2.getSingleResult().longValue();
			} else {
				sql = "SELECT COUNT(*) FROM (" + CASHS_QUERY_FOR_VIEW + ") AS r";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				query2.setParameter("userId", loginUsersView.getUserId());
				count = query2.getSingleResult().longValue();
			}
			return new SinglePage<CashInHandView>(null, pageNo, count);
		} else {
			return new SinglePage<CashInHandView>(cashInHandViewList.get(0), pageNo, null);
		}
	}

	@Override
	public SinglePage<CashInHandView> getCashInHandViewLastPage(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<CashInHandView> query;
		long count;
		if (loginUsersView == null) {
			sql = "SELECT * FROM cash_in_hand_view ORDER BY(cash_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, CashInHandView.class);
		} else {
			sql = "SELECT * FROM (" + CASHS_QUERY_FOR_VIEW + ") AS r ORDER BY(cash_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, CashInHandView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM cash_in_hand_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + CASHS_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		List<CashInHandView> cashInHandViewList = query.getResultList();
		if (cashInHandViewList.isEmpty()) {
			return new SinglePage<CashInHandView>(null, count, count);
		} else {
			return new SinglePage<CashInHandView>(cashInHandViewList.get(0), count, count);
		}
	}

	@Override
	public Long getUserViewSinglePageNo(UsersView loginUsersView, Integer cashNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		List<BigInteger> singlePageNoList;
		if (loginUsersView == null) {
			sql = "SELECT row_number FROM" + "			(SELECT cash_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (cash_no)) FROM cash_in_hand_view)"
					+ "			AS row_number " + "WHERE cash_no = :cashNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("cashNo", cashNo);
			singlePageNoList = query.getResultList();
		} else {
			sql = "SELECT row_number FROM" + "			(SELECT cash_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (cash_no)) FROM (" + CASHS_QUERY_FOR_VIEW + ") AS r)"
					+ "			AS row_number " + "WHERE cash_no = :cashNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("cashNo", cashNo);
			query.setParameter("userId", loginUsersView.getUserId());
			singlePageNoList = query.getResultList();
		}
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}

	@Override
	public MultiplePages<CashInHandView> getCashInHandViewMultiplePages(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<CashInHandView> query;
		List<CashInHandView> cashInHandViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM  cash_in_hand_view LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CashInHandView.class);
			} else {
				sql = "SELECT * FROM  (" + CASHS_QUERY_FOR_VIEW + ") AS r LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CashInHandView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			cashInHandViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM cash_in_hand_view AS foo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + CASHS_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || cashInHandViewList.isEmpty()) {
			return new MultiplePages<CashInHandView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<CashInHandView>(cashInHandViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public MultiplePages<CashInHandView> getCashInHandViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CashInHandViewFilter cashInHandViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<CashInHandView> query;
		List<CashInHandView> cashInHandViewList = null;
		long count;
		String filterQuery;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("acc_no", cashInHandViewFilter.getAccNo());
		filters.put("branch_no", cashInHandViewFilter.getBranchNo());
		filters.put("cash_d_name", cashInHandViewFilter.getCashDName());
		filters.put("cash_f_name", cashInHandViewFilter.getCashFNo());
		filters.put("cash_no", cashInHandViewFilter.getCashNo());
		filters.put("pos", cashInHandViewFilter.getPos());

		if (pageNo > 0) {
			if (loginUsersView == null) {
				filterQuery = GenerateSql.generateFilterQuery("cash_in_hand_view", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CashInHandView.class);
			} else {
				filterQuery = GenerateSql.generateFilterQuery(" (" + CASHS_QUERY_FOR_VIEW + ") AS r ", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, CashInHandView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			cashInHandViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			filterQuery = GenerateSql.generateFilterQuery("cash_in_hand_view", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			filterQuery = GenerateSql.generateFilterQuery(" (" + CASHS_QUERY_FOR_VIEW + ") AS r ", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || cashInHandViewList.isEmpty()) {
			return new MultiplePages<CashInHandView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<CashInHandView>(cashInHandViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(cash_no) + 1 FROM cashInHands";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null)
			nextPK = 1;
		return nextPK;
	}

	@Override
	public void addCashInHand(CashInHand cashInHand) {
		Session session = sessionFactory.getCurrentSession();
		session.save(cashInHand);
		session.flush();
	}

	@Override
	public void addCashInHandsPriv(CashInHandPriv cashInHandPriv) {
		Session session = sessionFactory.getCurrentSession();
		session.save(cashInHandPriv);
	}

	@Override
	public void updateCashInHand(CashInHand cashInHand) {
		Session session = sessionFactory.getCurrentSession();
		CashInHand DBCashInHand = session.get(CashInHand.class, cashInHand.getCashNo());
		DBCashInHand.setAccNo(cashInHand.getAccNo());
		DBCashInHand.setBranchNo(cashInHand.getBranchNo());
		DBCashInHand.setCashDName(cashInHand.getCashDName());
		DBCashInHand.setCashFNo(cashInHand.getCashFNo());
		DBCashInHand.setCashNo(cashInHand.getCashNo());
		DBCashInHand.setInactive(cashInHand.getInactive());
		DBCashInHand.setInactiveReason(cashInHand.getInactiveReason());
		DBCashInHand.setInactiveUser(cashInHand.getInactiveUser());
		DBCashInHand.setModifyDate(cashInHand.getModifyDate());
		DBCashInHand.setModifyUser(cashInHand.getModifyUser());
		DBCashInHand.setPos(cashInHand.getPos());
		session.merge(DBCashInHand);
		session.flush();
	}

	@Override
	public void deleteCashInHand(Integer cashInHandNo) {
		Session session = sessionFactory.getCurrentSession();
		CashInHand DBCashInHand = session.get(CashInHand.class, cashInHandNo);
		session.delete(DBCashInHand);
		session.flush();
	}

}
