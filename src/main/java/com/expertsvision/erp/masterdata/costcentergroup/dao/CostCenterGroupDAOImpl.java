package com.expertsvision.erp.masterdata.costcentergroup.dao;

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
import com.expertsvision.erp.masterdata.costcentergroup.dto.CostCenterGroupViewFilter;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroup;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroupView;

@Repository 	
public class CostCenterGroupDAOImpl implements CostCenterGroupDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<CostCenterGroupView> getAllCostCenterGroupViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cost_center_group_view";
		Query<CostCenterGroupView> query = session.createNativeQuery(sql, CostCenterGroupView.class);
		List<CostCenterGroupView> costCenterGroupViewList = query.getResultList();
		return costCenterGroupViewList;
	}
	
	@Override
	public CostCenterGroupView getCostCenterGroupView(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cost_center_group_view WHERE group_no = :groupNo";
		Query<CostCenterGroupView> query = session.createNativeQuery(sql, CostCenterGroupView.class);
		query.setParameter("groupNo", groupNo);
		List<CostCenterGroupView> costCenterGroupViewList = query.getResultList();
		return costCenterGroupViewList.isEmpty()? null : costCenterGroupViewList.get(0);
	}
	
	@Override
	public SinglePage<CostCenterGroupView> getCostCenterGroupViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CostCenterGroupView> costCenterGroupViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM cost_center_group_view LIMIT 1 OFFSET :Offset";
			Query<CostCenterGroupView> query = session.createNativeQuery(sql, CostCenterGroupView.class);
			query.setParameter("Offset", pageNo - 1);
			costCenterGroupViewList = query.getResultList();
		}
		if (pageNo <= 0 || costCenterGroupViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM cost_center_group";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<CostCenterGroupView>(null, pageNo, count);
		} else {
			return new SinglePage<CostCenterGroupView>(costCenterGroupViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<CostCenterGroupView> getCostCenterGroupViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM cost_center_group_view ORDER BY(group_no) DESC LIMIT 1";
		Query<CostCenterGroupView> query = session.createNativeQuery(sql, CostCenterGroupView.class);
		List<CostCenterGroupView> costCenterGroupViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM cost_center_group";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (costCenterGroupViewList.isEmpty()) {
			return new SinglePage<CostCenterGroupView>(null, count, count);
		} else {
			return new SinglePage<CostCenterGroupView>(costCenterGroupViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT group_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (group_no)) FROM cost_center_group_view)" +
					 "			AS row_number " +
					 "WHERE group_no = :groupNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("groupNo", groupNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<CostCenterGroupView> getCostCenterGroupViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CostCenterGroupView> costCenterGroupViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  cost_center_group_view LIMIT 30 OFFSET :Offset";
			Query<CostCenterGroupView> query = session.createNativeQuery(sql, CostCenterGroupView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			costCenterGroupViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM cost_center_group AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || costCenterGroupViewList.isEmpty()) {
			return new MultiplePages<CostCenterGroupView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CostCenterGroupView>(costCenterGroupViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<CostCenterGroupView> getCostCenterGroupViewFilteredMultiplePages(long pageNo, CostCenterGroupViewFilter costCenterGroupViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CostCenterGroupView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("group_no", costCenterGroupViewFilter.getGroupNo());
		filters.put("group_d_name", costCenterGroupViewFilter.getGroupDName());
		filters.put("group_f_name", costCenterGroupViewFilter.getGroupFName());
		String filterQuery = GenerateSql.generateFilterQuery("cost_center_group_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<CostCenterGroupView> query = session.createNativeQuery(sql, CostCenterGroupView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<CostCenterGroupView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CostCenterGroupView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public Object getNextPK() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT max(group_no) + 1 FROM cost_center_group";
		@SuppressWarnings("unchecked")
		Query<Object> query = session.createNativeQuery(sql);
		Object nextPK = query.getSingleResult();
		if (nextPK == null) nextPK = 1;
		return nextPK;
	}
	
	@Override
	public void addCostCenterGroup(CostCenterGroup costCenterGroup) {
		Session session = sessionFactory.getCurrentSession();
		session.save(costCenterGroup);
		session.flush();
	}
	
	@Override
	public void updateCostCenterGroup(CostCenterGroup costCenterGroup) {
		Session session = sessionFactory.getCurrentSession();
		CostCenterGroup DBCostCenterGroup = session.get(CostCenterGroup.class, costCenterGroup.getGroupNo());
		DBCostCenterGroup.setGroupDName(costCenterGroup.getGroupDName());
		DBCostCenterGroup.setGroupFName(costCenterGroup.getGroupFName());
		DBCostCenterGroup.setModifyDate(costCenterGroup.getModifyDate());
		DBCostCenterGroup.setModifyUser(costCenterGroup.getModifyUser());
		session.merge(DBCostCenterGroup);
		session.flush();
	}
	
	@Override
	public void deleteCostCenterGroup(Integer costCenterGroupNo) {
		Session session = sessionFactory.getCurrentSession();
		CostCenterGroup DBCostCenterGroup = session.get(CostCenterGroup.class, costCenterGroupNo);
		session.delete(DBCostCenterGroup);
		session.flush();
	}

}
