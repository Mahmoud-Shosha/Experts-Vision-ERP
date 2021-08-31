package com.expertsvision.erp.masterdata.branches.dao;

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
import com.expertsvision.erp.masterdata.branches.dto.BranchesViewFilter;
import com.expertsvision.erp.masterdata.branches.entity.Branch;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.branches.entity.BranchesView;

@Repository
public class BranchDAOImpl implements BranchDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private final String BRANCHES_QUERY_FOR_VIEW = "SELECT branches_view.* FROM branches_priv AS priv "
			+ "LEFT JOIN branches_view AS branches_view ON branches_view.branch_no = priv.branch_no "
			+ "WHERE priv.user_id = :userId AND priv.view_priv = true "
			+ "ORDER BY (branches_view.company_no, branches_view.branch_no) ";

	@Override
	public List<BranchesView> getAllBranchViewList(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<BranchesView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM branches_view";
			query = session.createNativeQuery(sql, BranchesView.class);
		} else {
			sql = BRANCHES_QUERY_FOR_VIEW;
			query = session.createNativeQuery(sql, BranchesView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		List<BranchesView> branchViewList = query.getResultList();
		return branchViewList;
	}

	@Override
	public BranchesView getBranchView(UsersView loginUsersView,Integer branchNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<BranchesView> query;
		if (loginUsersView == null) {
			sql = "SELECT * FROM branches_view WHERE branch_no = :branchNo";
			query = session.createNativeQuery(sql, BranchesView.class);
		} else {
			sql = "SELECT * FROM (" + BRANCHES_QUERY_FOR_VIEW + ") AS r WHERE branch_no = :branchNo";
			query = session.createNativeQuery(sql, BranchesView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		query.setParameter("branchNo", branchNo);
		List<BranchesView> branchViewList = query.getResultList();
		return branchViewList.isEmpty() ? null : branchViewList.get(0);
	}

	@Override
	public SinglePage<BranchesView> getBranchViewSinglePage(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<BranchesView> query;
		List<BranchesView> branchViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM branches_view LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, BranchesView.class);
			} else {
				sql = "SELECT * FROM (" + BRANCHES_QUERY_FOR_VIEW + ") AS r LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, BranchesView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}	
			query.setParameter("Offset", pageNo - 1);
			branchViewList = query.getResultList();
		}
		if (pageNo <= 0 || branchViewList.isEmpty()) {
			if (loginUsersView == null) {
				sql = "SELECT COUNT(*) FROM branches_view";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				count = query2.getSingleResult().longValue();
			} else {
				sql = "SELECT COUNT(*) FROM (" + BRANCHES_QUERY_FOR_VIEW + ") AS r";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				query2.setParameter("userId", loginUsersView.getUserId());
				count = query2.getSingleResult().longValue();
			}
			return new SinglePage<BranchesView>(null, pageNo, count);
		} else {
			return new SinglePage<BranchesView>(branchViewList.get(0), pageNo, null);
		}
	}

	@Override
	public SinglePage<BranchesView> getBranchViewLastPage(UsersView loginUsersView) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		Query<BranchesView> query;
		long count;
		if (loginUsersView == null) {
			sql = "SELECT * FROM branches_view ORDER BY(company_no, branch_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, BranchesView.class);
		} else {
			sql = "SELECT * FROM (" + BRANCHES_QUERY_FOR_VIEW + ") AS r ORDER BY(company_no, branch_no) DESC LIMIT 1";
			query = session.createNativeQuery(sql, BranchesView.class);
			query.setParameter("userId", loginUsersView.getUserId());
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM branches_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + BRANCHES_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		List<BranchesView> branchViewList = query.getResultList();
		if (branchViewList.isEmpty()) {
			return new SinglePage<BranchesView>(null, count, count);
		} else {
			return new SinglePage<BranchesView>(branchViewList.get(0), count, count);
		}
	}

	@Override
	public Long getUserViewSinglePageNo(UsersView loginUsersView, Integer branchNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql;
		List<BigInteger> singlePageNoList;
		if (loginUsersView == null) {
			sql = "SELECT row_number FROM" + "			(SELECT company_no, branch_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (company_no, branch_no)) FROM branches_view)"
					+ "			AS row_number " + "WHERE branch_no = :branchNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("branchNo", branchNo);
			singlePageNoList = query.getResultList();
		} else {
			sql = "SELECT row_number FROM" + "			(SELECT company_no, branch_no, ROW_NUMBER()"
					+ "						OVER(ORDER BY (company_no, branch_no)) FROM (" + BRANCHES_QUERY_FOR_VIEW + ") AS r)"
					+ "			AS row_number " + "WHERE branch_no = :branchNo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter("branchNo", branchNo);
			query.setParameter("userId", loginUsersView.getUserId());
			singlePageNoList = query.getResultList();
		}
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}

	@Override
	public MultiplePages<BranchesView> getBranchViewMultiplePages(UsersView loginUsersView, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<BranchesView> query;
		List<BranchesView> branchViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUsersView == null) {
				sql = "SELECT * FROM  branches_view LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BranchesView.class);
			} else {
				sql = "SELECT * FROM  (" + BRANCHES_QUERY_FOR_VIEW + ") AS r LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BranchesView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			branchViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			sql = "SELECT COUNT(*) FROM branches_view AS foo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + BRANCHES_QUERY_FOR_VIEW + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || branchViewList.isEmpty()) {
			return new MultiplePages<BranchesView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<BranchesView>(branchViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	@Override
	public MultiplePages<BranchesView> getBranchViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			BranchesViewFilter branchesViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<BranchesView> query;
		List<BranchesView> branchViewList = null;
		long count;
		String filterQuery;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("branch_no", branchesViewFilter.getBranchNo());
		filters.put("company_no", branchesViewFilter.getCompanyNo());
		filters.put("branch_d_name", branchesViewFilter.getBranchDName());
		filters.put("branch_f_name", branchesViewFilter.getBranchFName());
		if (pageNo > 0) {
			if (loginUsersView == null) {
				filterQuery = GenerateSql.generateFilterQuery("branches_view", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BranchesView.class);
			} else {
				filterQuery = GenerateSql.generateFilterQuery(" (" + BRANCHES_QUERY_FOR_VIEW + ") AS r ", filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, BranchesView.class);
				query.setParameter("userId", loginUsersView.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			branchViewList = query.getResultList();
		}
		if (loginUsersView == null) {
			filterQuery = GenerateSql.generateFilterQuery("branches_view", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			filterQuery = GenerateSql.generateFilterQuery(" (" + BRANCHES_QUERY_FOR_VIEW + ") AS r ", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUsersView.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || branchViewList.isEmpty()) {
			return new MultiplePages<BranchesView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<BranchesView>(branchViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(branch_no) + 1 FROM branches";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}

	@Override
	public void addBranch(Branch branch) {
		Session session = sessionFactory.getCurrentSession();
		session.save(branch);
		session.flush();
	}

	@Override
	public void addBranchesPriv(BranchesPriv branchesPriv) {
		Session session = sessionFactory.getCurrentSession();
		session.save(branchesPriv);
	}

	@Override
	public void updateBranch(Branch branch) {
		Session session = sessionFactory.getCurrentSession();
		Branch DBBranch = session.get(Branch.class, branch.getBranchNo());
		DBBranch.setModifyDate(branch.getModifyDate());
		DBBranch.setModifyUser(branch.getModifyUser());
		DBBranch.setBranchDName(branch.getBranchDName());
		DBBranch.setBranchFName(branch.getBranchFName());
		DBBranch.setCountryNo(branch.getCountryNo());
		DBBranch.setBranchDAddress(branch.getBranchDAddress());
		DBBranch.setBranchFAddress(branch.getBranchFAddress());
		DBBranch.setCapital(branch.getCapital());
		DBBranch.setCityNo(branch.getCityNo());
		DBBranch.setCompanyNo(branch.getCompanyNo());
		DBBranch.setCrNo(branch.getCrNo());
		DBBranch.setLogo(branch.getLogo());
		DBBranch.setProvinceNo(branch.getProvinceNo());
		DBBranch.setReportDHeader1(branch.getReportDHeader1());
		DBBranch.setReportDHeader2(branch.getReportDHeader2());
		DBBranch.setReportDHeader3(branch.getReportDHeader3());
		DBBranch.setReportFHeader1(branch.getReportFHeader1());
		DBBranch.setReportFHeader2(branch.getReportFHeader2());
		DBBranch.setReportFHeader3(branch.getReportFHeader3());
		DBBranch.setShortcutD(branch.getShortcutD());
		DBBranch.setShortcutF(branch.getShortcutF());
		DBBranch.setTaxNo(branch.getTaxNo());
		DBBranch.setTelephoneNo(branch.getTelephoneNo());
		session.merge(DBBranch);
		session.flush();
	}

	@Override
	public void deleteBranch(Integer branchNo) {
		Session session = sessionFactory.getCurrentSession();
		Branch DBBranch = session.get(Branch.class, branchNo);
		session.delete(DBBranch);
		session.flush();
	}

}
