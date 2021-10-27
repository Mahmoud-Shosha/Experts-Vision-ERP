package com.expertsvision.erp.masterdata.employees.dao;

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
import com.expertsvision.erp.masterdata.employees.dto.EmpInfoViewFilter;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfo;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfoView;

@Repository 	
public class EmpInfoDAOImpl implements EmpInfoDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<EmpInfoView> getAllEmpInfoViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM emp_info_view";
		Query<EmpInfoView> query = session.createNativeQuery(sql, EmpInfoView.class);
		List<EmpInfoView> empInfoViewList = query.getResultList();
		return empInfoViewList;
	}
	
	@Override
	public EmpInfoView getEmpInfoView(Integer empNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM emp_info_view WHERE emp_no = :empNo";
		Query<EmpInfoView> query = session.createNativeQuery(sql, EmpInfoView.class);
		query.setParameter("empNo", empNo);
		List<EmpInfoView> empInfoViewList = query.getResultList();
		return empInfoViewList.isEmpty()? null : empInfoViewList.get(0);
	}
	
	@Override
	public SinglePage<EmpInfoView> getEmpInfoViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<EmpInfoView> empInfoViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM emp_info_view LIMIT 1 OFFSET :Offset";
			Query<EmpInfoView> query = session.createNativeQuery(sql, EmpInfoView.class);
			query.setParameter("Offset", pageNo - 1);
			empInfoViewList = query.getResultList();
		}
		if (pageNo <= 0 || empInfoViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM emp_info";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<EmpInfoView>(null, pageNo, count);
		} else {
			return new SinglePage<EmpInfoView>(empInfoViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<EmpInfoView> getEmpInfoViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM emp_info_view ORDER BY(emp_no) DESC LIMIT 1";
		Query<EmpInfoView> query = session.createNativeQuery(sql, EmpInfoView.class);
		List<EmpInfoView> empInfoViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM emp_info";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (empInfoViewList.isEmpty()) {
			return new SinglePage<EmpInfoView>(null, count, count);
		} else {
			return new SinglePage<EmpInfoView>(empInfoViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getEmpInfoViewSinglePageNo(Integer empNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT emp_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (emp_no)) FROM emp_info_view)" +
					 "			AS row_number " +
					 "WHERE emp_no = :empNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("empNo", empNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<EmpInfoView> getEmpInfoViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<EmpInfoView> empInfoViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  emp_info_view LIMIT 30 OFFSET :Offset";
			Query<EmpInfoView> query = session.createNativeQuery(sql, EmpInfoView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			empInfoViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM emp_info AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || empInfoViewList.isEmpty()) {
			return new MultiplePages<EmpInfoView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<EmpInfoView>(empInfoViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<EmpInfoView> getEmpInfoViewFilteredMultiplePages(long pageNo, EmpInfoViewFilter mpInfoViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<EmpInfoView> empInfoViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("emp_no", mpInfoViewFilter.getEmpNo());
		filters.put("emp_d_name", mpInfoViewFilter.getEmpDName());
		filters.put("emp_f_name", mpInfoViewFilter.getEmpFName());
		String filterQuery = GenerateSql.generateFilterQuery("emp_info_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<EmpInfoView> query = session.createNativeQuery(sql, EmpInfoView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			empInfoViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || empInfoViewList.isEmpty()) {
			return new MultiplePages<EmpInfoView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<EmpInfoView>(empInfoViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addEmpInfo(EmpInfo empInfo) {
		Session session = sessionFactory.getCurrentSession();
		session.save(empInfo);
	}
	
	@Override
	public void updateEmpInfo(EmpInfo empInfo) {
		Session session = sessionFactory.getCurrentSession();
		EmpInfo DBEmpInfo = session.get(EmpInfo.class, empInfo.getEmpNo());
		DBEmpInfo.setEmpDName(empInfo.getEmpDName());
		DBEmpInfo.setEmpFName(empInfo.getEmpFName());
		DBEmpInfo.setInactive(empInfo.getInactive());
		DBEmpInfo.setInactiveDate(empInfo.getInactiveDate());
		DBEmpInfo.setInactiveReason(empInfo.getInactiveReason());
		DBEmpInfo.setInactiveUser(empInfo.getInactiveUser());
		DBEmpInfo.setModifyDate(empInfo.getModifyDate());
		DBEmpInfo.setModifyUser(empInfo.getModifyUser());
		session.merge(DBEmpInfo);
	}
	
	@Override
	public void deleteEmpInfo(Integer empNo) {
		Session session = sessionFactory.getCurrentSession();
		EmpInfo DBEmpInfo = session.get(EmpInfo.class, empNo);
		session.delete(DBEmpInfo);
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(emp_no) + 1 FROM emp_info";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}

}
