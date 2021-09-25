package com.expertsvision.erp.masterdata.accountsgroup.dao;

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
import com.expertsvision.erp.masterdata.accountsgroup.dto.AccountsGroupViewFilter;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroup;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroupView;

@Repository 	
public class AccountsGroupDAOImpl implements AccountsGroupDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<AccountsGroupView> getAllAccountsGroupViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM accounts_group_view";
		Query<AccountsGroupView> query = session.createNativeQuery(sql, AccountsGroupView.class);
		List<AccountsGroupView> accountsGroupViewList = query.getResultList();
		return accountsGroupViewList;
	}
	
	@Override
	public AccountsGroupView getAccountsGroupView(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM accounts_group_view WHERE group_no = :groupNo";
		Query<AccountsGroupView> query = session.createNativeQuery(sql, AccountsGroupView.class);
		query.setParameter("groupNo", groupNo);
		List<AccountsGroupView> accountsGroupViewList = query.getResultList();
		return accountsGroupViewList.isEmpty()? null : accountsGroupViewList.get(0);
	}
	
	@Override
	public SinglePage<AccountsGroupView> getAccountsGroupViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<AccountsGroupView> accountsGroupViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM accounts_group_view LIMIT 1 OFFSET :Offset";
			Query<AccountsGroupView> query = session.createNativeQuery(sql, AccountsGroupView.class);
			query.setParameter("Offset", pageNo - 1);
			accountsGroupViewList = query.getResultList();
		}
		if (pageNo <= 0 || accountsGroupViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM accounts_group";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<AccountsGroupView>(null, pageNo, count);
		} else {
			return new SinglePage<AccountsGroupView>(accountsGroupViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<AccountsGroupView> getAccountsGroupViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM accounts_group_view ORDER BY(group_no) DESC LIMIT 1";
		Query<AccountsGroupView> query = session.createNativeQuery(sql, AccountsGroupView.class);
		List<AccountsGroupView> accountsGroupViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM accounts_group_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (accountsGroupViewList.isEmpty()) {
			return new SinglePage<AccountsGroupView>(null, count, count);
		} else {
			return new SinglePage<AccountsGroupView>(accountsGroupViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT group_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (group_no)) FROM accounts_group_view)" +
					 "			AS row_number " +
					 "WHERE group_no = :groupNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("groupNo", groupNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<AccountsGroupView> getAccountsGroupViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<AccountsGroupView> accountsGroupViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  accounts_group_view LIMIT 30 OFFSET :Offset";
			Query<AccountsGroupView> query = session.createNativeQuery(sql, AccountsGroupView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			accountsGroupViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM accounts_group_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || accountsGroupViewList.isEmpty()) {
			return new MultiplePages<AccountsGroupView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<AccountsGroupView>(accountsGroupViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<AccountsGroupView> getAccountsGroupViewFilteredMultiplePages(long pageNo, AccountsGroupViewFilter accountsGroupViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<AccountsGroupView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("group_no", accountsGroupViewFilter.getGroupNo());
		filters.put("group_d_name", accountsGroupViewFilter.getGroupDName());
		filters.put("group_f_name", accountsGroupViewFilter.getGroupFName());
		String filterQuery = GenerateSql.generateFilterQuery("accounts_group_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<AccountsGroupView> query = session.createNativeQuery(sql, AccountsGroupView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<AccountsGroupView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<AccountsGroupView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(group_no) + 1 FROM accounts_group";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addAccountsGroup(AccountsGroup company) {
		Session session = sessionFactory.getCurrentSession();
		session.save(company);
		session.flush();
	}
	
	@Override
	public void updateAccountsGroup(AccountsGroup accountsGroup) {
		Session session = sessionFactory.getCurrentSession();
		AccountsGroup DBAccountsGroup = session.get(AccountsGroup.class, accountsGroup.getGroupNo());
		DBAccountsGroup.setGroupDName(accountsGroup.getGroupDName());
		DBAccountsGroup.setGroupFName(accountsGroup.getGroupFName());
		DBAccountsGroup.setModifyDate(accountsGroup.getModifyDate());
		DBAccountsGroup.setModifyUser(accountsGroup.getModifyUser());
		session.merge(DBAccountsGroup);
		session.flush();
	}
	
	@Override
	public void deleteAccountsGroup(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		AccountsGroup DBAccountsGroup = session.get(AccountsGroup.class, groupNo);
		session.delete(DBAccountsGroup);
		session.flush();
	}

}
