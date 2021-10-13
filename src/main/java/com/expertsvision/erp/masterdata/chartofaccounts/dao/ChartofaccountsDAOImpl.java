package com.expertsvision.erp.masterdata.chartofaccounts.dao;

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
import com.expertsvision.erp.masterdata.chartofaccounts.dto.ChartOfAccountsViewFilter;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrency;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyPK;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyView;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPriv;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccount;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;


@Repository
public class ChartofaccountsDAOImpl implements ChartofaccountsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String ACCOUNTS_CURRENCY_QUERY_FOR_VIEW = "SELECT accounts_currency_view.* FROM accounts_priv AS priv "
			+ "LEFT JOIN accounts_currency_view AS accounts_currency_view "
			+ "ON accounts_currency_view.acc_no = priv.acc_no AND accounts_currency_view.cur_code = priv.acc_curr "
			+ "WHERE priv.user_id = :userId AND priv.acc_no = :accNo AND priv.view_priv = true "
			+ "ORDER BY (accounts_currency_view.acc_no, accounts_currency_view.cur_code) ";

	@Override
	public List<ChartOfAccountsView> getAllChartOfAccountsViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM chart_of_accounts_view";
		Query<ChartOfAccountsView> query = session.createNativeQuery(sql, ChartOfAccountsView.class);
		List<ChartOfAccountsView> chartOfAccountsViewList = query.getResultList();
		return chartOfAccountsViewList;
	}
	
	@Override
	public List<AccountsCurrencyView> getAccountsCurrencyViewList(UsersView loginUsersView, Integer accNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<AccountsCurrencyView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM accounts_currency_view WHERE acc_no = :accNo ";
			query = session.createNativeQuery(sql, AccountsCurrencyView.class);
			query.setParameter("accNo", accNo);
		} else {
			sql = ACCOUNTS_CURRENCY_QUERY_FOR_VIEW;
			query = session.createNativeQuery(sql, AccountsCurrencyView.class);
			query.setParameter("userId", loginUsersView.getUserId());
			query.setParameter("accNo", accNo);
		}
		List<AccountsCurrencyView> accountsCurrencyViewList = query.getResultList();
		return accountsCurrencyViewList;
	}

	@Override
	public ChartOfAccountsView getChartOfAccountsView(Integer accNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		sql = "SELECT * FROM chart_of_accounts_view WHERE acc_no = :accNo";
		Query<ChartOfAccountsView> query = session.createNativeQuery(sql, ChartOfAccountsView.class);
		query.setParameter("accNo", accNo);
		List<ChartOfAccountsView> chartOfAccountsViewList = query.getResultList();
		return chartOfAccountsViewList.isEmpty()? null : chartOfAccountsViewList.get(0);
	}

	@Override
	public SinglePage<ChartOfAccountsView> getChartOfAccountsViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ChartOfAccountsView> chartOfAccountsViewList = null;
		long count;
		if (pageNo > 0) {
			sql = "SELECT * FROM chart_of_accounts_view LIMIT 1 OFFSET :Offset";
			Query<ChartOfAccountsView> query = session.createNativeQuery(sql, ChartOfAccountsView.class);
			query.setParameter("Offset", pageNo - 1);
			chartOfAccountsViewList = query.getResultList();
		}
		if (pageNo <= 0 || chartOfAccountsViewList.isEmpty()) {
				sql = "SELECT COUNT(*) FROM chart_of_accounts_view";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				count = query2.getSingleResult().longValue();
			return new SinglePage<ChartOfAccountsView>(null, pageNo, count);
		} else {
			return new SinglePage<ChartOfAccountsView>(chartOfAccountsViewList.get(0), pageNo, null);
		}
	}

	@Override
	public SinglePage<ChartOfAccountsView> getChartOfAccountsViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM chart_of_accounts_view ORDER BY(acc_no) DESC LIMIT 1";
		Query<ChartOfAccountsView> query = session.createNativeQuery(sql, ChartOfAccountsView.class);
		List<ChartOfAccountsView> chartOfAccountsViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM chart_of_accounts_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (chartOfAccountsViewList.isEmpty()) {
			return new SinglePage<ChartOfAccountsView>(null, count, count);
		} else {
			return new SinglePage<ChartOfAccountsView>(chartOfAccountsViewList.get(0), count, count);
		}
	}

	@Override
	public Long getUserViewSinglePageNo(Integer accNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT acc_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (acc_no)) FROM chart_of_accounts_view)" +
					 "			AS row_number " +
					 "WHERE acc_no = :accNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("accNo", accNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}

	@Override
	public MultiplePages<ChartOfAccountsView> getChartOfAccountsViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ChartOfAccountsView> chartOfAccountsViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  chart_of_accounts_view LIMIT 30 OFFSET :Offset";
			Query<ChartOfAccountsView> query = session.createNativeQuery(sql, ChartOfAccountsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			chartOfAccountsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM chart_of_accounts AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || chartOfAccountsViewList.isEmpty()) {
			return new MultiplePages<ChartOfAccountsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ChartOfAccountsView>(chartOfAccountsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}

	@Override
	public MultiplePages<ChartOfAccountsView> getChartOfAccountsViewFilteredMultiplePages(long pageNo, ChartOfAccountsViewFilter chartOfAccountsViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ChartOfAccountsView> chartOfAccountsViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("acc_d_name", chartOfAccountsViewFilter.getAccDName());
		filters.put("acc_f_name", chartOfAccountsViewFilter.getAccFName());
		filters.put("acc_no", chartOfAccountsViewFilter.getAccNo());
		filters.put("bs", chartOfAccountsViewFilter.getBs());
		filters.put("parent_acc", chartOfAccountsViewFilter.getParentAcc());
		filters.put("sub", chartOfAccountsViewFilter.getSub());
		String filterQuery = GenerateSql.generateFilterQuery("chart_of_accounts_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<ChartOfAccountsView> query = session.createNativeQuery(sql, ChartOfAccountsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			chartOfAccountsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || chartOfAccountsViewList.isEmpty()) {
			return new MultiplePages<ChartOfAccountsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ChartOfAccountsView>(chartOfAccountsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK(Integer parentAcc) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(acc_no) FROM chart_of_accounts WHERE parent_acc = :parentAcc";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		query.setParameter("parentAcc", parentAcc);
		Object nextPK = query.getSingleResult();
		return nextPK;
	}

	@Override
	public void addChartOfAccount(ChartOfAccount chartOfAccount, List<AccountsCurrency> accountsCurrencyList) {
		Session session = sessionFactory.getCurrentSession();
		session.save(chartOfAccount);
		session.flush();
		if (accountsCurrencyList != null) {
			for (AccountsCurrency obj: accountsCurrencyList) {
				session.save(obj);
			}
			session.flush();
		}
	}

	@Override
	public void addAccountsPriv(AccountsPriv accountsPriv) {
		Session session = sessionFactory.getCurrentSession();
		session.save(accountsPriv);
	}

	@Override
	public void updateChartOfAccount(ChartOfAccount chartOfAccount,
			List<AccountsCurrency> accountsCurrencyForAddList,
			List<AccountsCurrency> accountsCurrencyForDeleteList,
			List<AccountsCurrency> accountsCurrencyForUpdateList) {
		Session session = sessionFactory.getCurrentSession();
		ChartOfAccount DBChartOfAccount = session.get(ChartOfAccount.class, chartOfAccount.getAccNo());
		DBChartOfAccount.setAccDName(chartOfAccount.getAccDName());
		DBChartOfAccount.setAccDtl(chartOfAccount.getAccDtl());
		DBChartOfAccount.setAccFName(chartOfAccount.getAccFName());
		DBChartOfAccount.setAccGroup(chartOfAccount.getAccGroup());
		DBChartOfAccount.setAccType(chartOfAccount.getAccType());
		DBChartOfAccount.setBs(chartOfAccount.getBs());
		DBChartOfAccount.setCashFlowType(chartOfAccount.getCashFlowType());
		DBChartOfAccount.setDr(chartOfAccount.getDr());
		DBChartOfAccount.setInactive(chartOfAccount.getInactive());
		DBChartOfAccount.setInactiveDate(chartOfAccount.getInactiveDate());
		DBChartOfAccount.setInactiveReason(chartOfAccount.getInactiveReason());
		DBChartOfAccount.setInactiveUser(chartOfAccount.getInactiveUser());
		DBChartOfAccount.setLevel(chartOfAccount.getLevel());
		DBChartOfAccount.setModifyDate(chartOfAccount.getModifyDate());
		DBChartOfAccount.setModifyUser(chartOfAccount.getModifyUser());
		DBChartOfAccount.setParentAcc(chartOfAccount.getParentAcc());
		DBChartOfAccount.setSub(chartOfAccount.getSub());
		session.merge(DBChartOfAccount);
		session.flush();
		
		// Add the details
		if (accountsCurrencyForAddList != null) {
			for (AccountsCurrency obj : accountsCurrencyForAddList) {
				session.save(obj);
			}
		}
		// Update the details
		if (accountsCurrencyForUpdateList != null) {
			for (AccountsCurrency obj : accountsCurrencyForUpdateList) {
				AccountsCurrency DBAccountsCurrency = session.get(AccountsCurrency.class,
						new AccountsCurrencyPK(obj.getAccNo(), obj.getCurCode()));
				DBAccountsCurrency.setActive(obj.getActive());
				DBAccountsCurrency.setModifyDate(obj.getModifyDate());
				DBAccountsCurrency.setModifyUser(obj.getModifyUser());
				DBAccountsCurrency.setUsed(obj.getUsed());
				session.merge(DBChartOfAccount);
			}
		}
		// Delete the details
		if (accountsCurrencyForDeleteList != null) {
			String sql = "DELETE FROM accounts_currency WHERE acc_no = :accNo AND cur_code = :curCode";
			Query<?> query = session.createNativeQuery(sql);
			query.setParameter("accNo", chartOfAccount.getAccNo());
			for (AccountsCurrency obj : accountsCurrencyForDeleteList) {
				query.setParameter("curCode", obj.getCurCode());
				query.executeUpdate();
			}
		}
		session.flush();
	}

	@Override
	public void deleteChartOfAccount(Integer accNo) {
		Session session = sessionFactory.getCurrentSession();
		ChartOfAccount chartOfAccount = session.get(ChartOfAccount.class, accNo);
		session.delete(chartOfAccount);
		session.flush();
	}
	
	@Override
	public boolean hasSubAccounts(Integer accNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT 1 FROM chart_of_accounts WHERE parent_acc = :parentAcc";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		query.setParameter("parentAcc", accNo);
		return query.getResultList().size() > 0;
	}

}
