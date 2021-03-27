package com.expertsvision.erp.core.message.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.MessagesViewPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
@Transactional
public class MessagesViewDAOImpl implements MessagesViewDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MessagesView> getMessagesViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_messages_view_list()";
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		return messagesViewList;
	}
	
	@Override
	public MessagesView getMessagesView(MessagesViewPK messagesViewPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_messages_view('%s', %s)";
		sql = String.format(sql, messagesViewPK.getMessageCode(), messagesViewPK.getLangNo());
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		return messagesViewList.isEmpty()? null : messagesViewList.get(0);
	}
	
	@Override
	public SinglePage<MessagesView> getMessagesViewSinglePage( long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_messages_view_single_page(%s)";
		sql = String.format(sql, pageNo);
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		if (messagesViewList.isEmpty()) {
			sql = "SELECT * FROM get_table_rows_count('%s')";
			sql = String.format(sql, "messages");
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<MessagesView>(null, pageNo, count);
		} else {
			return new SinglePage<MessagesView>(messagesViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<MessagesView> getMessagesViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_messages_view_last_page()";
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "messages");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (messagesViewList.isEmpty()) {
			return new SinglePage<MessagesView>(null, count, count);
		} else {
			return new SinglePage<MessagesView>(messagesViewList.get(0), count, count);
		}
	}
	
	@Override
	public MultiplePages<MessagesView> getMessagesViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_messages_view_multiple_pages(%s)";
		sql = String.format(sql, pageNo);
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "messages");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (messagesViewList.isEmpty()) {
			return new MultiplePages<MessagesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<MessagesView>(messagesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<MessagesView> getMessagesViewFilteredMultiplePages(long pageNo, MessagesViewFilter MessageViewFilter) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("message_code", MessageViewFilter.getMessageCode());
		filters.put("lang_no", MessageViewFilter.getLangNo());
		filters.put("message_desc", MessageViewFilter.getMessageDesc());
		String filterQuery = GenerateSql.generateFilterQuery("messages_view", filters);
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_messages_view_filtered_multiple_pages(%s, '%s')";
		sql = String.format(sql, pageNo, filterQuery);
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		sql = "SELECT * FROM get_query_rows_count('%s')";
		sql = String.format(sql, filterQuery);
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (messagesViewList.isEmpty()) {
			return new MultiplePages<MessagesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<MessagesView>(messagesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public List<String> addMessagesView(UsersView loginUser, MessagesView messagesView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(add_messages_view(%s, %s, '%s', '%s'))";
		sql = String.format(sql, loginUser.getUserId(), messagesView.getLangNo(), messagesView.getMessageDesc(),
														messagesView.getMessageCode());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> updateMessagesView(UsersView loginUser, MessagesView messagesView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(update_messages_view(%s, %s, '%s', '%s'))";
		sql = String.format(sql, loginUser.getUserId(), messagesView.getLangNo(), messagesView.getMessageDesc(),
														messagesView.getMessageCode());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> deleteMessagesView(UsersView loginUser, MessagesViewPK messagesViewPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(delete_messages_view(%s, '%s', %s))";
		sql = String.format(sql, loginUser.getUserId(), messagesViewPK.getMessageCode(), messagesViewPK.getLangNo());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}

}
