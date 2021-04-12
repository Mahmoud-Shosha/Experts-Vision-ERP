package com.expertsvision.erp.core.message.service;

import java.util.List;

import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.MessagesPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface MessagesService {
	
	List<MessagesView> getMessagesViewList();
	
	MessagesView getMessagesView(MessagesPK messagesViewPK);
	
	SinglePage<MessagesView> getMessagesViewSinglePage(long pageNo);
	
	SinglePage<MessagesView> getMessagesViewLastPage();
	
	Long getMessagesViewSinglePageNo(MessagesPK messagesViewPK);
	
	MultiplePages<MessagesView> getMessagesViewMultiplePages(long pageNo);
	
	MultiplePages<MessagesView> getMessagesViewFilteredMultiplePages(long pageNo, MessagesViewFilter messagesViewFilter);
	
	void addMessage(UsersView loginUser, MessagesView messagesView);
	
	void updateMessage(UsersView loginUser, MessagesView messagesView);
	
	void deleteMessage(UsersView loginUser, MessagesPK messagesViewPK);
}
