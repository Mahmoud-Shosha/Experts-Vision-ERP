package com.expertsvision.erp.core.usersgroups.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.usersgroups.dto.UsersGroupsViewFilter;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroup;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroupsView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class UsersGroupsDAOImpl implements UsersGroupsDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<UsersGroupsView> getAllUsersGroupsViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM users_groups_view";
		Query<UsersGroupsView> query = session.createNativeQuery(sql, UsersGroupsView.class);
		List<UsersGroupsView> usersGroupsViewList = query.getResultList();
		return usersGroupsViewList;
	}
	
	@Override
	public UsersGroupsView getUsersGroupsView(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM users_groups_view WHERE group_no = :groupNo";
		Query<UsersGroupsView> query = session.createNativeQuery(sql, UsersGroupsView.class);
		query.setParameter("groupNo", groupNo);
		List<UsersGroupsView> usersGroupsViewList = query.getResultList();
		return usersGroupsViewList.isEmpty()? null : usersGroupsViewList.get(0);
	}
	
	@Override
	public SinglePage<UsersGroupsView> getUsersGroupsViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<UsersGroupsView> usersGroupsViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM users_groups_view LIMIT 1 OFFSET :Offset";
			Query<UsersGroupsView> query = session.createNativeQuery(sql, UsersGroupsView.class);
			query.setParameter("Offset", pageNo - 1);
			usersGroupsViewList = query.getResultList();
		}
		if (pageNo <= 0 || usersGroupsViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM users_groups_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<UsersGroupsView>(null, pageNo, count);
		} else {
			return new SinglePage<UsersGroupsView>(usersGroupsViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<UsersGroupsView> getUsersGroupsViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM users_groups_view ORDER BY(group_no) DESC LIMIT 1";
		Query<UsersGroupsView> query = session.createNativeQuery(sql, UsersGroupsView.class);
		List<UsersGroupsView> usersGroupsViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM users_groups_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (usersGroupsViewList.isEmpty()) {
			return new SinglePage<UsersGroupsView>(null, count, count);
		} else {
			return new SinglePage<UsersGroupsView>(usersGroupsViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT group_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (group_no)) FROM users_groups_view)" +
					 "			AS row_number " +
					 "WHERE group_no = :groupNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("groupNo", groupNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<UsersGroupsView> getUsersGroupsViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<UsersGroupsView> usersGroupsViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  users_groups_view LIMIT 30 OFFSET :Offset";
			Query<UsersGroupsView> query = session.createNativeQuery(sql, UsersGroupsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersGroupsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM users_groups_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersGroupsViewList.isEmpty()) {
			return new MultiplePages<UsersGroupsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<UsersGroupsView>(usersGroupsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<UsersGroupsView> getUsersGroupsViewFilteredMultiplePages(long pageNo, UsersGroupsViewFilter usersGroupsViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<UsersGroupsView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("group_no", usersGroupsViewFilter.getGroupNo());
		filters.put("group_d_name", usersGroupsViewFilter.getGroupDName());
		filters.put("group_f_name", usersGroupsViewFilter.getGroupFName());
		String filterQuery = GenerateSql.generateFilterQuery("users_groups_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<UsersGroupsView> query = session.createNativeQuery(sql, UsersGroupsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<UsersGroupsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<UsersGroupsView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addUsersGroup(UsersGroup usersGroup) {
		Session session = sessionFactory.getCurrentSession();
		session.save(usersGroup);
		session.flush();
	}
	
	@Override
	public void updateUsersGroup(UsersGroup usersGroup) {
		Session session = sessionFactory.getCurrentSession();
		UsersGroup DBUsersGroup = session.get(UsersGroup.class, usersGroup.getGroupNo());
		DBUsersGroup.setAdminGroup(usersGroup.getAdminGroup());
		DBUsersGroup.setGroupDName(usersGroup.getGroupDName());
		DBUsersGroup.setGroupFName(usersGroup.getGroupFName());
		DBUsersGroup.setGroupNo(usersGroup.getGroupNo());
		DBUsersGroup.setModifyDate(usersGroup.getModifyDate());
		DBUsersGroup.setModifyUser(usersGroup.getModifyUser());
		session.merge(DBUsersGroup);
		session.flush();
	}
	
	@Override
	public void deleteUsersGroup(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		UsersGroup DBUsersGroup = session.get(UsersGroup.class, groupNo);
		session.delete(DBUsersGroup);
		session.flush();
	}

}
