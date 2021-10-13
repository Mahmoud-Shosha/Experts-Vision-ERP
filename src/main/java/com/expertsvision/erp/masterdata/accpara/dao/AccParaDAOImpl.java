package com.expertsvision.erp.masterdata.accpara.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.masterdata.accpara.entity.AccPara;

@Repository 	
public class AccParaDAOImpl implements AccParaDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
//	@Override
//	public List<AccPara> getAllAccParaList() {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT * FROM accounts_group_view";
//		Query<AccPara> query = session.createNativeQuery(sql, AccPara.class);
//		List<AccPara> accParaList = query.getResultList();
//		return accParaList;
//	}
	
	@Override
	public AccPara getAccPara() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM acc_para LIMIT 1";
		Query<AccPara> query = session.createNativeQuery(sql, AccPara.class);
		List<AccPara> accParaList = query.getResultList();
		return accParaList.isEmpty()? null : accParaList.get(0);
	}
	
//	@Override
//	public SinglePage<AccPara> getAccParaSinglePage(long pageNo) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = null;
//		List<AccPara> accParaList =null;
//		if (pageNo > 0) {
//			sql = "SELECT * FROM accounts_group_view LIMIT 1 OFFSET :Offset";
//			Query<AccPara> query = session.createNativeQuery(sql, AccPara.class);
//			query.setParameter("Offset", pageNo - 1);
//			accParaList = query.getResultList();
//		}
//		if (pageNo <= 0 || accParaList.isEmpty()) {
//			sql = "SELECT COUNT(*) FROM accounts_group";
//			@SuppressWarnings("unchecked")
//			Query<BigInteger> query2 = session.createNativeQuery(sql);
//			long count = query2.getSingleResult().longValue();
//			return new SinglePage<AccPara>(null, pageNo, count);
//		} else {
//			return new SinglePage<AccPara>(accParaList.get(0), pageNo, null);
//		}
//	}
//	
//	@Override
//	public SinglePage<AccPara> getAccParaLastPage() {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT * FROM accounts_group_view ORDER BY(group_no) DESC LIMIT 1";
//		Query<AccPara> query = session.createNativeQuery(sql, AccPara.class);
//		List<AccPara> accParaList = query.getResultList();
//		sql = "SELECT COUNT(*) FROM accounts_group_view";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query2 = session.createNativeQuery(sql);
//		long count = query2.getSingleResult().longValue();
//		if (accParaList.isEmpty()) {
//			return new SinglePage<AccPara>(null, count, count);
//		} else {
//			return new SinglePage<AccPara>(accParaList.get(0), count, count);
//		}
//	}
//	
//	@Override
//	public Long getUserViewSinglePageNo(Integer groupNo) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT row_number FROM" +
//					 "			(SELECT group_no, ROW_NUMBER()" +
//					 "						OVER(ORDER BY (group_no)) FROM accounts_group_view)" +
//					 "			AS row_number " +
//					 "WHERE group_no = :groupNo";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query = session.createNativeQuery(sql);
//		query.setParameter("groupNo", groupNo);
//		List<BigInteger> singlePageNoList = query.getResultList();
//		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
//	}
//	
//	@Override
//	public MultiplePages<AccPara> getAccParaMultiplePages(long pageNo) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = null;
//		List<AccPara> accParaList = null;
//		if (pageNo > 0) {
//			sql = "SELECT * FROM  accounts_group_view LIMIT 30 OFFSET :Offset";
//			Query<AccPara> query = session.createNativeQuery(sql, AccPara.class);
//			query.setParameter("Offset", (pageNo - 1) * 30);
//			accParaList = query.getResultList();
//		}
//		sql = "SELECT COUNT(*) FROM accounts_group_view AS foo";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query2 = session.createNativeQuery(sql);
//		long count = query2.getSingleResult().longValue();
//		if (pageNo <= 0 || accParaList.isEmpty()) {
//			return new MultiplePages<AccPara>(null, pageNo, (long)Math.ceil(count/30.0));
//		} else {
//			return new MultiplePages<AccPara>(accParaList, pageNo, (long)Math.ceil(count/30.0));
//		}
//	}
//	
//	@Override
//	public MultiplePages<AccPara> getAccParaFilteredMultiplePages(long pageNo, AccParaFilter accParaFilter) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = null;
//		List<AccPara> usersViewList = null;
//		Map<String, Object> filters = new HashMap<String, Object>();
//		filters.put("group_no", accParaFilter.getGroupNo());
//		filters.put("group_d_name", accParaFilter.getGroupDName());
//		filters.put("group_f_name", accParaFilter.getGroupFName());
//		String filterQuery = GenerateSql.generateFilterQuery("accounts_group_view", filters);
//		if (pageNo > 0) {
//			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
//			Query<AccPara> query = session.createNativeQuery(sql, AccPara.class);
//			query.setParameter("Offset", (pageNo - 1) * 30);
//			usersViewList = query.getResultList();
//		}
//		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query2 = session.createNativeQuery(sql);
//		long count = query2.getSingleResult().longValue();
//		if (pageNo <= 0 || usersViewList.isEmpty()) {
//			return new MultiplePages<AccPara>(null, pageNo, (long)Math.ceil(count/30.0));
//		} else {
//			return new MultiplePages<AccPara>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
//		}
//	}
//	
//	@Override
//	public Object getNextPK() {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT max(group_no) + 1 FROM accounts_group";
//		@SuppressWarnings("unchecked")
//		Query<Object> query = session.createNativeQuery(sql);
//		Object nextPK = query.getSingleResult();
//		if (nextPK == null) nextPK = 1;
//		return nextPK;
//	}
//	
//	@Override
//	public void addAccountsGroup(AccountsGroup company) {
//		Session session = sessionFactory.getCurrentSession();
//		session.save(company);
//		session.flush();
//	}
//	
//	@Override
//	public void updateAccountsGroup(AccountsGroup accountsGroup) {
//		Session session = sessionFactory.getCurrentSession();
//		AccountsGroup DBAccountsGroup = session.get(AccountsGroup.class, accountsGroup.getGroupNo());
//		DBAccountsGroup.setGroupDName(accountsGroup.getGroupDName());
//		DBAccountsGroup.setGroupFName(accountsGroup.getGroupFName());
//		DBAccountsGroup.setModifyDate(accountsGroup.getModifyDate());
//		DBAccountsGroup.setModifyUser(accountsGroup.getModifyUser());
//		session.merge(DBAccountsGroup);
//		session.flush();
//	}
//	
//	@Override
//	public void deleteAccountsGroup(Integer groupNo) {
//		Session session = sessionFactory.getCurrentSession();
//		AccountsGroup DBAccountsGroup = session.get(AccountsGroup.class, groupNo);
//		session.delete(DBAccountsGroup);
//		session.flush();
//	}

}
