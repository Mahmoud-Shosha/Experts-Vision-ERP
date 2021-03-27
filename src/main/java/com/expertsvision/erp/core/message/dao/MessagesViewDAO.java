package com.expertsvision.erp.core.message.dao;

import java.util.List;

import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.MessagesViewPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface MessagesViewDAO {
	
	List<MessagesView> getMessagesViewList();
	
	MessagesView getMessagesView(MessagesViewPK MessagesViewPK);
	
	List<String> addMessagesView(UsersView loginUser, MessagesView MessagesView);
	
	List<String> updateMessagesView(UsersView loginUser, MessagesView MessagesView);
	
	List<String> deleteMessagesView(UsersView loginUser, MessagesViewPK messagesViewPK);
	
	SinglePage<MessagesView> getMessagesViewSinglePage(long pageNo);
	
	SinglePage<MessagesView> getMessagesViewLastPage();
	
	MultiplePages<MessagesView> getMessagesViewMultiplePages(long pageNo);
	
	MultiplePages<MessagesView> getMessagesViewFilteredMultiplePages(long pageNo, MessagesViewFilter messagesViewFilter);
	
}
