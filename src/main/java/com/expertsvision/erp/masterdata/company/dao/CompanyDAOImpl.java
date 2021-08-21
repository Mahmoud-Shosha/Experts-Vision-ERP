package com.expertsvision.erp.masterdata.company.dao;

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
import com.expertsvision.erp.masterdata.company.dto.CompanyViewFilter;
import com.expertsvision.erp.masterdata.company.entity.Company;
import com.expertsvision.erp.masterdata.company.entity.CompanyView;

@Repository 	
public class CompanyDAOImpl implements CompanyDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<CompanyView> getAllCompanyViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM company_view";
		Query<CompanyView> query = session.createNativeQuery(sql, CompanyView.class);
		List<CompanyView> companyViewList = query.getResultList();
		return companyViewList;
	}
	
	@Override
	public CompanyView getCompanyView(Integer companyNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM company_view WHERE company_no = :companyNo";
		Query<CompanyView> query = session.createNativeQuery(sql, CompanyView.class);
		query.setParameter("companyNo", companyNo);
		List<CompanyView> companyViewList = query.getResultList();
		return companyViewList.isEmpty()? null : companyViewList.get(0);
	}
	
	@Override
	public SinglePage<CompanyView> getCompanyViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CompanyView> companyViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM company_view LIMIT 1 OFFSET :Offset";
			Query<CompanyView> query = session.createNativeQuery(sql, CompanyView.class);
			query.setParameter("Offset", pageNo - 1);
			companyViewList = query.getResultList();
		}
		if (pageNo <= 0 || companyViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM company_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<CompanyView>(null, pageNo, count);
		} else {
			return new SinglePage<CompanyView>(companyViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<CompanyView> getCompanyViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM company_view ORDER BY(company_group, company_no) DESC LIMIT 1";
		Query<CompanyView> query = session.createNativeQuery(sql, CompanyView.class);
		List<CompanyView> companyViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM company_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (companyViewList.isEmpty()) {
			return new SinglePage<CompanyView>(null, count, count);
		} else {
			return new SinglePage<CompanyView>(companyViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer companyNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT company_group, company_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (company_group, company_no)) FROM company_view)" +
					 "			AS row_number " +
					 "WHERE company_no = :companyNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("companyNo", companyNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<CompanyView> getCompanyViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CompanyView> companyViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  company_view LIMIT 30 OFFSET :Offset";
			Query<CompanyView> query = session.createNativeQuery(sql, CompanyView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			companyViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM company_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || companyViewList.isEmpty()) {
			return new MultiplePages<CompanyView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CompanyView>(companyViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<CompanyView> getCompanyViewFilteredMultiplePages(long pageNo, CompanyViewFilter companyViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CompanyView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("company_no", companyViewFilter.getCompanyNo());
		filters.put("company_group", companyViewFilter.getCompanyGroup());
		filters.put("company_d_name", companyViewFilter.getCompanyDName());
		filters.put("company_f_name", companyViewFilter.getCompanyFName());
		String filterQuery = GenerateSql.generateFilterQuery("company_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<CompanyView> query = session.createNativeQuery(sql, CompanyView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<CompanyView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CompanyView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		session.save(company);
		session.flush();
	}
	
	@Override
	public void updateCompany(Company company) {
		Session session = sessionFactory.getCurrentSession();
		Company DBCompany = session.get(Company.class, company.getCompanyNo());
		DBCompany.setModifyDate(company.getModifyDate());
		DBCompany.setModifyUser(company.getModifyUser());
		DBCompany.setCompanyDName(company.getCompanyDName());
		DBCompany.setCompanyFName(company.getCompanyFName());
		DBCompany.setCountryNo(company.getCountryNo());
		DBCompany.setCompanyGroup(null);
		DBCompany.setCompanyMail(null);
		DBCompany.setCompanyWebsite(null);
		DBCompany.setShortcutD(null);
		DBCompany.setShortcutF(null);
		session.merge(DBCompany);
		session.flush();
	}
	
	@Override
	public void deleteCompany(Integer companyNo) {
		Session session = sessionFactory.getCurrentSession();
		Company DBCompany = session.get(Company.class, companyNo);
		session.delete(DBCompany);
		session.flush();
	}

}
