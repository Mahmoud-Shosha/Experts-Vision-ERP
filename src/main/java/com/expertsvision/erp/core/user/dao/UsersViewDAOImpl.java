package com.expertsvision.erp.core.user.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.user.entity.UsersView;

@Repository 	
@Transactional
public class UsersViewDAOImpl implements UsersViewDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<UsersView> getUsersViewList(UsersView loginUser) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_users_view_list(%s)";
		sql = String.format(sql, loginUser.getUserId());
		Query<UsersView> query = session.createNativeQuery(sql, UsersView.class);
		List<UsersView> usersViewList = query.getResultList();
		return usersViewList;
	}
	

	@Override
	public List<UsersView> getUsersViewSubordinateList(UsersView usersView) {
		Session session = sessionFactory.getCurrentSession();
		Query<UsersView> query = session.createNativeQuery("SELECT * FROM get_users_view_subordinates_list(:user_id)",
														   UsersView.class);
		query.setParameter("user_id", usersView.getUserId());
		List<UsersView> usersViewList =query.getResultList();
		return usersViewList;
	}
	
	@Override
	public UsersView getUsersView(UsersView loginUser, Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		Query<UsersView> query = session.createNativeQuery("SELECT * FROM get_users_view(:login_user_id, :user_id)",
													  	   UsersView.class);
		query.setParameter("login_user_id", loginUser.getUserId());
		query.setParameter("user_id", userId);
		UsersView usersView =query.getSingleResult();
		return usersView;
	}
	
	@Override
	public List<String> addUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT unnest(add_users_view(%s, %s, '%s', '%s', %s, %s, '%s', %s, '%s', %s))";
		sql = String.format(sql, loginUser.getUserId(), usersView.getUserId(), usersView.getUserDName(),
											   usersView.getUserFName(), usersView.getDirectMang(), usersView.getGroupNo(),
											   usersView.getPassword(), usersView.getInactive(), usersView.getInactiveReason(),
											   COPY_FROM_USER_PRIVILEDGES);
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> updateUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES,
			 							Integer COPY_PRIVILEGES_TO_GROUP, Boolean confirm) {
		Session session = sessionFactory.getCurrentSession();
		javax.persistence.Query query = session.createNativeQuery("SELECT unnest(update_users_view(:login_user_id, :user_id, "
													  + ":user_d_name, :user_f_name, :direct_mang, :group_no, :password, "
													  + ":inactive, :inactive_reason, :COPY_FROM_USER_PRIVILEDGES, "
													  + ":COPY_PRIVILEGES_TO_GROUP, :confirm))");
		query.setParameter("login_user_id", loginUser.getUserId());
		query.setParameter("user_id", usersView.getUserId());
		query.setParameter("user_d_name", usersView.getUserDName());
		query.setParameter("user_f_name", usersView.getUserFName());
		query.setParameter("direct_mang", usersView.getDirectMang());
		query.setParameter("group_no", usersView.getGroupNo());
		query.setParameter("password", usersView.getPassword());
		query.setParameter("inactive", usersView.getInactive());
		query.setParameter("inactive_reason", usersView.getInactiveReason());
		query.setParameter("COPY_FROM_USER_PRIVILEDGES", COPY_FROM_USER_PRIVILEDGES);
		query.setParameter("COPY_PRIVILEGES_TO_GROUP", COPY_PRIVILEGES_TO_GROUP);
		query.setParameter("confirm", confirm);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> deleteUsersView(UsersView loginUser, Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		javax.persistence.Query query = session.createNativeQuery("SELECT unnest(delete_users_view(:login_user_id, :user_no))");
		query.setParameter("login_user_id", loginUser.getUserId());
		query.setParameter("user_no", userId);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	

}
