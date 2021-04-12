package com.expertsvision.erp.core.message.dao;

import java.util.List;

import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.Message;
import com.expertsvision.erp.core.message.entity.MessagesPK;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface MessagesDAO {
	
	List<MessagesView> getMessagesViewList();
	
	MessagesView getMessagesView(MessagesPK MessagesViewPK);
	
	SinglePage<MessagesView> getMessagesViewSinglePage(long pageNo);
	
	SinglePage<MessagesView> getMessagesViewLastPage();
	
	Long getMessagesViewSinglePageNo(MessagesPK messagesPK);
	
	MultiplePages<MessagesView> getMessagesViewMultiplePages(long pageNo);
	
	MultiplePages<MessagesView> getMessagesViewFilteredMultiplePages(long pageNo, MessagesViewFilter messagesViewFilter);
	
	void addMessage(Message message);
	
	void updateMessage(Message message);
	
	void deleteMessage(MessagesPK messagesPK);
	
}
