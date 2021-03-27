package com.expertsvision.erp.core.message.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.message.dao.MessagesViewDAO;
import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.MessagesViewPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class MessagesViewServiceImpl implements MessagesViewService {
	
	@Autowired
	private MessagesViewDAO messagesViewDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;

	
	@Override
	public List<MessagesView> getMessagesViewList() {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		List<MessagesView> messagesViewList = null;
		try {
			messagesViewList = messagesViewDAO.getMessagesViewList();
		} catch (HibernateException e) {
			throw new UnauthorizedException("resource");
		}
		return messagesViewList;
	}
	
	
	@Override
	public MessagesView getMessagesView(MessagesViewPK messagesViewPK) {
		MessagesView messagesView = null;
		try {
			messagesView = messagesViewDAO.getMessagesView(messagesViewPK);
		} catch (HibernateException e) {
			throw new UnauthorizedException("resource");
		}
		if (messagesView == null) {
			throw new ValidationException("not_exist", "message");
		}
		return messagesView;
	}
	
	@Override
	public SinglePage<MessagesView> getMessagesViewSinglePage(long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		SinglePage<MessagesView> singlePage = null;
		try {
			singlePage = messagesViewDAO.getMessagesViewSinglePage(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public SinglePage<MessagesView> getMessagesViewLastPage() {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		SinglePage<MessagesView> singlePage = null;
		try {
			singlePage = messagesViewDAO.getMessagesViewLastPage();
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public MultiplePages<MessagesView> getMessagesViewMultiplePages(long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		MultiplePages<MessagesView> multiplePages = null;
		try {
			multiplePages = messagesViewDAO.getMessagesViewMultiplePages(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	@Override
	public MultiplePages<MessagesView> getMessagesViewFilteredMultiplePages(long pageNo, MessagesViewFilter MessageViewFilter) {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		MultiplePages<MessagesView> multiplePages = null;
		try {
			multiplePages = messagesViewDAO.getMessagesViewFilteredMultiplePages(pageNo, MessageViewFilter);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	
	@Override
	public void addMessagesView(UsersView loginUser, MessagesView messagesView) {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		coreValidationService.notNull(messagesView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(messagesView.getLangNo(), "lang_no");
		coreValidationService.notNull(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notBlank(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notNull(messagesView.getMessageCode(), "message_code");
		coreValidationService.notBlank(messagesView.getMessageCode(), "message_code");
		coreValidationService.runDatabaseValidation(messagesViewDAO.addMessagesView(loginUser, messagesView));	
	}
	
	@Override
	public void updateMessagesView(UsersView loginUser, MessagesView messagesView) {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		coreValidationService.notNull(messagesView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(messagesView.getLangNo(), "lang_no");
		coreValidationService.notNull(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notBlank(messagesView.getMessageDesc(), "message_desc");
		coreValidationService.notNull(messagesView.getMessageCode(), "message_code");
		coreValidationService.notBlank(messagesView.getMessageCode(), "message_code");
		coreValidationService.runDatabaseValidation(messagesViewDAO.updateMessagesView(loginUser, messagesView));		
	}
	
	@Override
	public void deleteMessagesView(UsersView loginUser, MessagesViewPK messagesViewPK) {
		coreValidationService.activeModuleAndForm(Forms.MESSAGES);
		try {
			coreValidationService.runDatabaseValidation(messagesViewDAO.deleteMessagesView(loginUser, messagesViewPK));
		} catch (HibernateException e) {
			throw new ValidationException("used_somewhere", "message");
		}
	}
}
