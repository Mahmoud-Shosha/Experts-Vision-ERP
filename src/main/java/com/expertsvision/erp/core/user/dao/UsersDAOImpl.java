package com.expertsvision.erp.core.user.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.user.dto.UsersViewFilter;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class UsersDAOImpl implements UsersDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	private String usersViewSubordinateQuery = "WITH RECURSIVE subordinates AS (" + 
	  	                                       "				 SELECT * FROM users_view WHERE user_id = :managerUserId" + 
		                                       "							   UNION" + 
		                                       "							   SELECT u.* FROM users_view u" + 
		                                       "							   INNER JOIN subordinates s ON s.user_id = u.direct_mang)" + 
		                                       "							   SELECT * FROM subordinates order by user_id";
	
	
	@Override
	public List<UsersView> getAllUsersViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM users_view";
		Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
		List<UsersView> usersViewList = query.getResultList();
		return usersViewList;
	}
	
	@Override
	public List<UsersView> getUsersViewSubordinateList(Integer loginUserId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = usersViewSubordinateQuery;
		Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
		query.setParameter("managerUserId", loginUserId);
		List<UsersView> usersViewList = query.getResultList();
		return usersViewList;
	}
	
	@Override
	public UsersView getUsersView(Integer loginUserId, Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM ( " + usersViewSubordinateQuery + " ) AS foo WHERE user_id = :userId";
		Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
		query.setParameter("managerUserId", loginUserId);
		query.setParameter("userId", userId);
		List<UsersView> usersViewList = query.getResultList();
		return usersViewList.isEmpty()? null : usersViewList.get(0);
	}
	
	@Override
	public UsersView getUsersView(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		UsersView usersView = session.get(UsersView.class, userId);
		return usersView;
	}
	
	@Override
	public SinglePage<UsersView> getUsersViewSinglePage(Integer loginUserId, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<UsersView> usersViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM ( " + usersViewSubordinateQuery + " ) AS foo LIMIT 1 OFFSET :Offset";
			Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
			query.setParameter("managerUserId", loginUserId);
			query.setParameter("Offset", pageNo - 1);
			usersViewList = query.getResultList();
		}
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM ( " + usersViewSubordinateQuery + " ) AS foo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("managerUserId", loginUserId);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<UsersView>(null, pageNo, count);
		} else {
			return new SinglePage<UsersView>(usersViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<UsersView> getUsersViewLastPage(Integer loginUserId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM ( " + usersViewSubordinateQuery + " ) AS foo ORDER BY(user_id) DESC LIMIT 1";
		Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
		query.setParameter("managerUserId", loginUserId);
		List<UsersView> usersViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM ( " + usersViewSubordinateQuery + " ) AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		query2.setParameter("managerUserId", loginUserId);
		long count = query2.getSingleResult().longValue();
		if (usersViewList.isEmpty()) {
			return new SinglePage<UsersView>(null, count, count);
		} else {
			return new SinglePage<UsersView>(usersViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getUserViewSinglePageNo(Integer loginUserId, Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT user_id, ROW_NUMBER()" +
					 "						OVER(ORDER BY (user_id)) FROM ( " + usersViewSubordinateQuery + " ) AS foo)" +
					 "			AS row_number " +
					 "WHERE user_id = :userId";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("managerUserId", loginUserId);
		query.setParameter("userId", userId);			
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<UsersView> getUsersViewMultiplePages(Integer loginUserId, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<UsersView> usersViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  ( " + usersViewSubordinateQuery + " ) AS foo LIMIT 30 OFFSET :Offset";
			Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
			query.setParameter("managerUserId", loginUserId);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM ( " + usersViewSubordinateQuery + " ) AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		query2.setParameter("managerUserId", loginUserId);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<UsersView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<UsersView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<UsersView> getUsersViewFilteredMultiplePages(Integer loginUserId, long pageNo, UsersViewFilter usersViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<UsersView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("direct_mang", usersViewFilter.getDirectMang());
		filters.put("group_no", usersViewFilter.getGroupNo());
		filters.put("inactive", usersViewFilter.getInactive());
		filters.put("user_d_name", usersViewFilter.getUserDName());
		filters.put("user_f_name", usersViewFilter.getUserFName());
		filters.put("user_id", usersViewFilter.getUserId());	
		String filterQuery = GenerateSql.generateFilterQuery(" ( " + usersViewSubordinateQuery + " ) AS foo ",
															 filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
			query.setParameter("managerUserId", loginUserId);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		query2.setParameter("managerUserId", loginUserId);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<UsersView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<UsersView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		session.flush();
	}
	
	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		User DBUser = session.get(User.class, user.getUserId());
		DBUser.setAdminUser(user.getAdminUser());
		DBUser.setDirectMang(user.getDirectMang());
		DBUser.setGroupNo(user.getGroupNo());
		DBUser.setInactive(user.getInactive());
		DBUser.setInactiveDate(user.getInactiveDate());
		DBUser.setInactiveReason(user.getInactiveReason());
		DBUser.setInactiveUser(user.getInactiveUser());
		DBUser.setModifyDate(user.getModifyDate());
		DBUser.setModifyUser(user.getModifyUser());
		DBUser.setPassword(user.getPassword());
		DBUser.setSuperAdmin(user.getSuperAdmin());
		DBUser.setUserDName(user.getUserDName());
		DBUser.setUserFName(user.getUserFName());
		DBUser.setUserId(user.getUserId());
		session.merge(DBUser);
		session.flush();
	}
	
	@Override
	public void deleteUser(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		User DBUser = session.get(User.class, userId);
		session.delete(DBUser);
		session.flush();
	}
	
	@Override
	public List<User> getUsersListByGroupNo(Integer groupNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM users WHERE group_no = :groupNo";
		Query<User> query = session.createNativeQuery(sql, User.class);
		query.setParameter("groupNo", groupNo);
		List<User> usersList = query.getResultList();
		return usersList;
	}
	

}
