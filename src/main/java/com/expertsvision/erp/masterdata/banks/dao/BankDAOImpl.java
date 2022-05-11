package com.expertsvision.erp.masterdata.banks.dao;

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
import com.expertsvision.erp.masterdata.banks.dto.BanksViewFilter;
import com.expertsvision.erp.masterdata.banks.entity.Bank;
import com.expertsvision.erp.masterdata.banks.entity.BanksDtl;
import com.expertsvision.erp.masterdata.banks.entity.BanksDtlPK;
import com.expertsvision.erp.masterdata.banks.entity.BanksDtlView;
import com.expertsvision.erp.masterdata.banks.entity.BanksPriv;
import com.expertsvision.erp.masterdata.banks.entity.BanksView;

@Repository
public class BankDAOImpl implements BankDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String BANKS_QUERY_FOR_VIEW = "SELECT banks_view.* FROM banks_priv AS priv "
			+ "			LEFT JOIN banks_view AS banks_view ON banks_view.bank_no = priv.bank_no "
			+ "			LEFT JOIN banks_dtl AS banks_dtl ON banks_dtl.bank_no = priv.bank_no "
			+ "			LEFT JOIN accounts_priv AS accounts_priv ON accounts_priv.user_id = :userId AND accounts_priv.acc_no = banks_view.account_no AND accounts_priv.acc_curr = banks_dtl.acc_curr "
			+ "			WHERE priv.user_id = :userId AND priv.view_priv = true AND accounts_priv.view_priv = true "
			+ "			ORDER BY (banks_view.bank_no) ";

	@Override
	public List<BanksView> getAllBankViewList(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<BanksView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM banks_view";
			query = session.createNativeQuery(sql, BanksView.class);
		} else {
			sql = BANKS_QUERY_FOR_VIEW;
			query = session.createNativeQuery(sql, BanksView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		List<BanksView> bankViewList = query.getResultList();
		return bankViewList;
	}

	@Override
	public List<BanksDtlView> getBanksDtlViewList(Integer bankNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM  banks_dtl_view WHERE bank_code = :bankNo";
		Query<BanksDtlView> query = session.createNativeQuery(sql, BanksDtlView.class);
		query.setParameter("bankNo", bankNo);
		List<BanksDtlView> banksDtlViewList = query.getResultList();
		return banksDtlViewList;
	}

	@Override
	public BanksView getBankView(UsersView loginUsersView, Integer bankNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<BanksView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM banks_view WHERE bank_no = :bankNo";
			query = session.createNativeQuery(sql, BanksView.class);
		} else {
			sql = "SELECT * FROM (" + BANKS_QUERY_FOR_VIEW + ") AS r WHERE bank_no = :bankNo";
			query = session.createNativeQuery(sql, BanksView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		query.setParameter("bankNo", bankNo);
		List<BanksView> bankViewList = query.getResultList();
		return bankViewList.isEmpty() ? null : bankViewList.get(0);
	}

	@Override
	public SinglePage<BanksView> getBankViewSinglePage(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<BanksView> query;
		List<BanksView> bankViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM banks_view LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, BanksView.class);
			} else {
				sql = "SELECT * FROM (" + BANKS_QUERY_FOR_VIEW + ") AS r LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, BanksView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", pageNo - 1);
			bankViewList = query.getResultList();
		}
		if (pageNo <= 0 || bankViewList.isEmpty()) {
			if (loginUsersView == null) {
				sql = "SELECT COUNT(*) FROM banks_view";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				count = query2.getSingleResult().longValue();
			} else {
				sql = "SELECT COUNT(*) FROM (" + BANKS_QUERY_FOR_VIEW + ") AS r";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				query2.setParameter("userId", loginUsersView.getUserId());
				count = query2.getSingleResult().longValue();
			}
			return new SinglePage<BanksView>(null, pageNo, count);
		} else {
			return new SinglePage<BanksView>(bankViewList.get(0), pageNo, null);
		}
	}

	@Override
	public SinglePage<BanksView> getBankViewLastPage(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<BanksView> query;
		long count;
		if (loginUsersView == null) {
			sql = "SELECT * FROM banks_view ORDER BY(bank_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, BanksView.class);
		} else {
			sql = "SELECT * FROM (" + BANKS_QUERY_FOR_VIEW + ") AS r ORDER BY(bank_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, BanksView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM banks_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + BANKS_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		List<BanksView> bankViewList = query.getResultList();
		if (bankViewList.isEmpty()) {
			return new SinglePage<BanksView>(null, count, count);
		} else {
			return new SinglePage<BanksView>(bankViewList.get(0), count, count);
		}
	}

	@Override
	public Long getUserViewSinglePageNo(UsersView loginUsersView, Integer bankNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		List<BigInteger> singlePageNoList;
		if (loginUsersView == null) {
			sql = "SELECT row_number FROM" + "			(SELECT bank_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (bank_no)) FROM banks_view)" + "			AS row_number "
					+ "WHERE bank_no = :bankNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("bankNo", bankNo);
			singlePageNoList = query.getResultList();
		} else {
			sql = "SELECT row_number FROM" + "			(SELECT bank_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (bank_no)) FROM (" + BANKS_QUERY_FOR_VIEW + ") AS r)"
					+ "			AS row_number " + "WHERE bank_no = :bankNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("bankNo", bankNo);
			query.setParameter("userId", loginUsersView.getUserId());
			singlePageNoList = query.getResultList();
		}
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}

	@Override
	public MultiplePages<BanksView> getBankViewMultiplePages(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<BanksView> query;
		List<BanksView> bankViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM  banks_view LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BanksView.class);
			} else {
				sql = "SELECT * FROM  (" + BANKS_QUERY_FOR_VIEW + ") AS r LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BanksView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			bankViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM banks_view AS foo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + BANKS_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || bankViewList.isEmpty()) {
			return new MultiplePages<BanksView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<BanksView>(bankViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public MultiplePages<BanksView> getBankViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			BanksViewFilter bankesViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<BanksView> query;
		List<BanksView> bankViewList = null;
		long count;
		String filterQuery;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("bank_no", bankesViewFilter.getBankNo());
		filters.put("bank_d_name", bankesViewFilter.getBankDName());
		filters.put("bank_f_name", bankesViewFilter.getBankFName());
		filters.put("account_no", bankesViewFilter.getAccountNo());
		if (pageNo > 0) {
			if (loginUsersView == null) {
				filterQuery = GenerateSql.generateFilterQuery("banks_view", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BanksView.class);
			} else {
				filterQuery = GenerateSql.generateFilterQuery(" (" + BANKS_QUERY_FOR_VIEW + ") AS r ", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BanksView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			bankViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			filterQuery = GenerateSql.generateFilterQuery("banks_view", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			filterQuery = GenerateSql.generateFilterQuery(" (" + BANKS_QUERY_FOR_VIEW + ") AS r ", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || bankViewList.isEmpty()) {
			return new MultiplePages<BanksView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<BanksView>(bankViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(bank_no) + 1 FROM banks";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null)
			nextPK = 1;
		return nextPK;
	}

	@Override
	public void addBank(Bank bank, List<BanksDtl> banksDtlList) {
		Session session = sessionFactory.getCurrentSession();
		session.save(bank);
		session.flush();
		if (banksDtlList != null) {
			for (BanksDtl obj : banksDtlList) {
				session.save(obj);
			}
			session.flush();
		}
	}

	@Override
	public void addBanksPriv(BanksPriv banksPriv) {
		Session session = sessionFactory.getCurrentSession();
		session.save(banksPriv);
	}

	@Override
	public void updateBank(Bank bank, List<BanksDtl> banksDtlForAddList, List<BanksDtl> banksDtlForDeleteList,
			List<BanksDtl> banksDtlForUpdateList) {
		Session session = sessionFactory.getCurrentSession();
		Bank DBBank = session.get(Bank.class, bank.getBankNo());
		DBBank.setAccountNo(bank.getAccountNo());
		DBBank.setBankDName(bank.getBankDName());
		DBBank.setBankFName(bank.getBankFName());
		DBBank.setBankNo(bank.getBankNo());
		DBBank.setInactive(bank.getInactive());
		DBBank.setInactiveReason(bank.getInactiveReason());
		DBBank.setInactiveUser(bank.getInactiveUser());
		DBBank.setModifyDate(bank.getModifyDate());
		DBBank.setModifyUser(bank.getModifyUser());
		session.merge(DBBank);
		session.flush();

		// Add the details
		if (banksDtlForAddList != null) {
			for (BanksDtl obj : banksDtlForAddList) {
				session.save(obj);
			}
		}
		// Update the details
		if (banksDtlForUpdateList != null) {
			for (BanksDtl obj : banksDtlForUpdateList) {
				BanksDtl DBBanksDtl = session.get(BanksDtl.class,
						new BanksDtlPK(obj.getBankNo(), obj.getAccNo(), obj.getAccCurr()));
				DBBanksDtl.setInactive(obj.getInactive());
				DBBanksDtl.setInactiveReason(obj.getInactiveReason());
				DBBanksDtl.setInactiveUser(obj.getInactiveUser());
				DBBanksDtl.setModifyDate(obj.getModifyDate());
				DBBanksDtl.setModifyUser(obj.getModifyUser());
				session.merge(DBBanksDtl);
			}
		}
		// Delete the details
		if (banksDtlForDeleteList != null) {
			String sql = "DELETE FROM banks_dtl WHERE bank_no = :bankNo AND acc_no = accNo AND acc_curr = :accCurr";
			Query<?> query = session.createNativeQuery(sql);
			query.setParameter("bankNo", bank.getBankNo());
			for (BanksDtl obj : banksDtlForDeleteList) {
				query.setParameter("accNo", obj.getAccNo());
				query.setParameter("accCurr", obj.getAccCurr());
				query.executeUpdate();
			}
		}
		session.flush();
	}

	@Override
	public void deleteBank(Integer bankNo) {
		Session session = sessionFactory.getCurrentSession();
		Bank DBBank = session.get(Bank.class, bankNo);
		session.delete(DBBank);
		session.flush();
	}

}
