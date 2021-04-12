package com.expertsvision.erp.core.message.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.message.dao.MessagesDAO;
import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.Message;
import com.expertsvision.erp.core.message.entity.MessagesPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class MessagesServiceImpl implements MessagesService {
	
	@Autowired
	private MessagesDAO messagesDAO;
	
	@Autowired
	private GeneralDAO generalDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;

	
	@Override
	@Transactional
	public List<MessagesView> getMessagesViewList() {
		List<MessagesView> messagesViewList = messagesDAO.getMessagesViewList();
		return messagesViewList;
	}
	
	
	@Override
	@Transactional
	public MessagesView getMessagesView(MessagesPK messagesViewPK) {
		MessagesView messagesView = messagesDAO.getMessagesView(messagesViewPK);
		if (messagesView == null) {
			throw new ValidationException("not_exist", "message");
		}
		return messagesView;
	}
	
	@Override
	@Transactional
	public SinglePage<MessagesView> getMessagesViewSinglePage(long pageNo) {
		SinglePage<MessagesView> singlePage = messagesDAO.getMessagesViewSinglePage(pageNo);
		return singlePage;
	}
	
	@Override
	@Transactional
	public SinglePage<MessagesView> getMessagesViewLastPage() {
		SinglePage<MessagesView> singlePage = messagesDAO.getMessagesViewLastPage();
		return singlePage;
	}
	
	@Override
	@Transactional
	public Long getMessagesViewSinglePageNo(MessagesPK messagesPK) {
		 Long singlePageNo = messagesDAO.getMessagesViewSinglePageNo(messagesPK);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "message");
		}
		return singlePageNo;
	}
	
	@Override
	@Transactional
	public MultiplePages<MessagesView> getMessagesViewMultiplePages(long pageNo) {
		MultiplePages<MessagesView> multiplePages = messagesDAO.getMessagesViewMultiplePages(pageNo);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public MultiplePages<MessagesView> getMessagesViewFilteredMultiplePages(long pageNo, MessagesViewFilter MessageViewFilter) {
		MultiplePages<MessagesView> multiplePages = messagesDAO.getMessagesViewFilteredMultiplePages(pageNo, MessageViewFilter);
		return multiplePages;
	}
	
	
	@Override
	@Transactional
	public void addMessage(UsersView loginUser, MessagesView messagesView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(messagesView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(messagesView.getLangNo(), "lang_no");
		coreValidationService.notNull(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notBlank(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notNull(messagesView.getMessageCode(), "message_code");
		coreValidationService.notBlank(messagesView.getMessageCode(), "message_code");
		// Database validation
		Message message = getMessageFromMessagesView(messagesView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("message_code", message.getMessageCode());
		conditions.put("lang_no", message.getLangNo());
		if (generalDAO.isEntityExist("messages", conditions)) throw new ValidationException("already_exist", "message_code");
		conditions.clear();
		conditions.put("message_desc", message.getMessageDesc());
		if (generalDAO.isEntityExist("messages", conditions)) throw new ValidationException("already_exist", "message_desc");
		conditions.clear();
		conditions.put("lang_no", message.getLangNo());
		if (!generalDAO.isEntityExist("language", conditions)) throw new ValidationException("not_exist", "lang_no");
		// Add the message
		message.setAddDate(new Timestamp(new Date().getTime()));
		message.setAddUser(loginUser.getUserId());
		message.setModifyDate(null);
		message.setModifyUser(null);
		messagesDAO.addMessage(message);
	}
	
	@Override
	@Transactional
	public void updateMessage(UsersView loginUser, MessagesView messagesView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(messagesView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(messagesView.getLangNo(), "lang_no");
		coreValidationService.notNull(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notBlank(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notNull(messagesView.getMessageCode(), "message_code");
		coreValidationService.notBlank(messagesView.getMessageCode(), "message_code");
		// Database validation
		Message message = getMessageFromMessagesView(messagesView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("message_code", message.getMessageCode());
		conditions.put("lang_no", message.getLangNo());
		if (!generalDAO.isEntityExist("messages", conditions)) throw new ValidationException("not_exist", "message");
		conditions.clear();
		conditions.put("message_desc", message.getMessageDesc());
		String exceptionCondition = null;
		try {
			exceptionCondition = " and not (message_code = '" + 
					Utils.escapeLiteral(null, message.getMessageCode()==null?"":message.getMessageCode().toString().strip(), true) +
					"' and lang_no = " + 
					Utils.escapeLiteral(null, message.getLangNo()==null?"":message.getLangNo().toString().strip(), true) +
					") ";
		} catch (SQLException e) {
			 throw new UnauthorizedException("resource");
		}
		if (generalDAO.isEntityExist("messages", conditions, exceptionCondition)) throw new ValidationException("already_exist", "message_desc");
		conditions.clear();
		conditions.put("lang_no", message.getLangNo());
		if (!generalDAO.isEntityExist("language", conditions)) throw new ValidationException("not_exist", "lang_no");
		// Update the message
		message.setModifyDate(new Timestamp(new Date().getTime()));
		message.setModifyUser(loginUser.getUserId());
		messagesDAO.updateMessage(message);
	}
	
	@Override
	@Transactional
	public void deleteMessage(UsersView loginUser, MessagesPK messagesViewPK) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("message_code", messagesViewPK.getMessageCode());
		conditions.put("lang_no", messagesViewPK.getLangNo());
		if (!generalDAO.isEntityExist("messages", conditions)) throw new ValidationException("not_exist", "message");
		// delete the message
		messagesDAO.deleteMessage(messagesViewPK);
	}
	
	public Message getMessageFromMessagesView(MessagesView messagesView)  {
		Message message = new Message();
		try {
			message.setAddDate(messagesView.getAddDate());
			message.setAddUser(messagesView.getAddUser());
			message.setLangNo(messagesView.getLangNo());
			message.setMessageCode(Utils.escapeLiteral(null, messagesView.getMessageCode(), true).toString());
			message.setMessageDesc(Utils.escapeLiteral(null, messagesView.getMessageDesc(), true).toString());
			message.setModifyDate(messagesView.getModifyDate());
			message.setModifyUser(messagesView.getModifyUser());
		} catch (SQLException e) {
			 throw new UnauthorizedException("resource");
		}
		return message;
	}
}
