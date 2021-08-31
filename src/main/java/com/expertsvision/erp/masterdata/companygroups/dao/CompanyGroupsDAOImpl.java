package com.expertsvision.erp.masterdata.companygroups.dao;

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
import com.expertsvision.erp.masterdata.companygroups.dto.CompanyGroupsViewFilter;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroup;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroupsView;

@Repository 	
public class CompanyGroupsDAOImpl implements CompanyGroupDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<CompanyGroupsView> getAllCompanyGroupsViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM company_groups_view";
		Query<CompanyGroupsView> query = session.createNativeQuery(sql, CompanyGroupsView.class);
		List<CompanyGroupsView> companyGroupsViewList = query.getResultList();
		return companyGroupsViewList;
	}
	
	@Override
	public CompanyGroupsView getCompanyGroupsView(Integer companyGroupsNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM company_groups_view WHERE group_no = :companyGroupsNo";
		Query<CompanyGroupsView> query = session.createNativeQuery(sql, CompanyGroupsView.class);
		query.setParameter("companyGroupsNo", companyGroupsNo);
		List<CompanyGroupsView> companyGroupsViewList = query.getResultList();
		return companyGroupsViewList.isEmpty()? null : companyGroupsViewList.get(0);
	}
	
	@Override
	public SinglePage<CompanyGroupsView> getCompanyGroupsViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CompanyGroupsView> companyGroupsViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM company_groups_view LIMIT 1 OFFSET :Offset";
			Query<CompanyGroupsView> query = session.createNativeQuery(sql, CompanyGroupsView.class);
			query.setParameter("Offset", pageNo - 1);
			companyGroupsViewList = query.getResultList();
		}
		if (pageNo <= 0 || companyGroupsViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM company_groups_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<CompanyGroupsView>(null, pageNo, count);
		} else {
			return new SinglePage<CompanyGroupsView>(companyGroupsViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<CompanyGroupsView> getCompanyGroupsViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM company_groups_view ORDER BY(group_no) DESC LIMIT 1";
		Query<CompanyGroupsView> query = session.createNativeQuery(sql, CompanyGroupsView.class);
		List<CompanyGroupsView> companyGroupsViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM company_groups_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (companyGroupsViewList.isEmpty()) {
			return new SinglePage<CompanyGroupsView>(null, count, count);
		} else {
			return new SinglePage<CompanyGroupsView>(companyGroupsViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer companyGroupsNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT group_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (group_no)) FROM company_groups_view)" +
					 "			AS row_number " +
					 "WHERE group_no = :companyGroupsNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("companyGroupsNo", companyGroupsNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<CompanyGroupsView> getCompanyGroupsViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CompanyGroupsView> companyGroupsViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  company_groups_view LIMIT 30 OFFSET :Offset";
			Query<CompanyGroupsView> query = session.createNativeQuery(sql, CompanyGroupsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			companyGroupsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM company_groups_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || companyGroupsViewList.isEmpty()) {
			return new MultiplePages<CompanyGroupsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CompanyGroupsView>(companyGroupsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<CompanyGroupsView> getCompanyGroupsViewFilteredMultiplePages(long pageNo, CompanyGroupsViewFilter companyGroupsViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CompanyGroupsView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("group_no", companyGroupsViewFilter.getGroupNo());
		filters.put("group_d_name", companyGroupsViewFilter.getGroupDName());
		filters.put("group_f_name", companyGroupsViewFilter.getGroupFName());
		String filterQuery = GenerateSql.generateFilterQuery("company_groups_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<CompanyGroupsView> query = session.createNativeQuery(sql, CompanyGroupsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<CompanyGroupsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CompanyGroupsView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(group_no) + 1 FROM company_groups";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addCompanyGroups(CompanyGroup companyGroup) {
		Session session = sessionFactory.getCurrentSession();
		session.save(companyGroup);
		session.flush();
	}
	
	@Override
	public void updateCompanyGroups(CompanyGroup companyGroup) {
		Session session = sessionFactory.getCurrentSession();
		CompanyGroup DBCompanyGroup = session.get(CompanyGroup.class, companyGroup.getGroupNo());
		DBCompanyGroup.setGroupDName(companyGroup.getGroupDName());
		DBCompanyGroup.setGroupFName(companyGroup.getGroupFName());
		DBCompanyGroup.setModifyDate(companyGroup.getModifyDate());
		DBCompanyGroup.setModifyUser(companyGroup.getModifyUser());
		session.merge(DBCompanyGroup);
		session.flush();
	}
	
	@Override
	public void deleteCompanyGroups(Integer companyGroupNo) {
		Session session = sessionFactory.getCurrentSession();
		CompanyGroup DBCompanyGroup = session.get(CompanyGroup.class, companyGroupNo);
		session.delete(DBCompanyGroup);
		session.flush();
	}

}
