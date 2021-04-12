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

import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.Message;
import com.expertsvision.erp.core.message.entity.MessagesPK;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class MessagesDAOImpl implements MessagesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<MessagesView> getMessagesViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM messages_view";
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		return messagesViewList;
	}
	
	@Override
	public MessagesView getMessagesView(MessagesPK messagesViewPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM messages_view WHERE message_code = :messageCode AND lang_no = :langNo";
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		query.setParameter("messageCode", messagesViewPK.getMessageCode());
		query.setParameter("langNo", messagesViewPK.getLangNo());
		List<MessagesView> messagesViewList = query.getResultList();
		return messagesViewList.isEmpty()? null : messagesViewList.get(0);
	}
	
	@Override
	public SinglePage<MessagesView> getMessagesViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<MessagesView> messagesViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM messages_view LIMIT 1 OFFSET :Offset";
			Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
			query.setParameter("Offset", pageNo - 1);
			messagesViewList = query.getResultList();
		}
		if (pageNo <= 0 || messagesViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM messages";
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
		String sql = "SELECT * FROM messages_view ORDER BY(message_code, lang_no) DESC LIMIT 1";
		Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
		List<MessagesView> messagesViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM labels";
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
	public Long getMessagesViewSinglePageNo(MessagesPK messagesPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT message_code, lang_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (message_code, lang_no)) FROM messages)" +
					 "			AS row_number " +
					 "WHERE message_code = :messageCode AND lang_no = :langNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("messageCode", messagesPK.getMessageCode());
		query.setParameter("langNo", messagesPK.getLangNo());			
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<MessagesView> getMessagesViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<MessagesView> messagesViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM messages_view LIMIT 30 OFFSET :Offset";
			Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			messagesViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM messages";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || messagesViewList.isEmpty()) {
			return new MultiplePages<MessagesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<MessagesView>(messagesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<MessagesView> getMessagesViewFilteredMultiplePages(long pageNo, MessagesViewFilter MessageViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<MessagesView> messagesViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("message_code", MessageViewFilter.getMessageCode());
		filters.put("lang_no", MessageViewFilter.getLangNo());
		filters.put("message_desc", MessageViewFilter.getMessageDesc());
		String filterQuery = GenerateSql.generateFilterQuery("messages_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<MessagesView> query = session.createNativeQuery(sql, MessagesView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			messagesViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || messagesViewList.isEmpty()) {
			return new MultiplePages<MessagesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<MessagesView>(messagesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addMessage(Message message) {
		Session session = sessionFactory.getCurrentSession();
		session.save(message);
	}
	
	@Override
	public void updateMessage(Message message) {
		Session session = sessionFactory.getCurrentSession();
		Message DBMessage = session.get(Message.class, new MessagesPK(message.getLangNo(), message.getMessageCode()));
		DBMessage.setLangNo(message.getLangNo());
		DBMessage.setMessageCode(message.getMessageCode());
		DBMessage.setMessageDesc(message.getMessageDesc());
		DBMessage.setModifyDate(message.getModifyDate());
		DBMessage.setModifyUser(message.getModifyUser());
		session.merge(DBMessage);
	}
	
	@Override
	public void deleteMessage(MessagesPK messagesPK) {
		Session session = sessionFactory.getCurrentSession();
		Message DBMessage = session.get(Message.class, messagesPK);
		session.delete(DBMessage);
	}

}
