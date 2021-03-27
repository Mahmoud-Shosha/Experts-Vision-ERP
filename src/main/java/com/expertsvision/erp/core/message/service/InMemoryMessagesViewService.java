package com.expertsvision.erp.core.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.MessagesViewPK;


@Service
public class InMemoryMessagesViewService {
	
	
	
	private MessagesViewService messagesViewService;
	
	private Map<MessagesViewPK, MessagesView> messagesViewMap;
	
	
	@Autowired
	public InMemoryMessagesViewService(MessagesViewService messagesViewService) {
		this.messagesViewService = messagesViewService;
		List<MessagesView> messgaesViewList = messagesViewService.getMessagesViewList();
		Map<MessagesViewPK, MessagesView> newMessagesViewMap = convertToMessageMap(messgaesViewList);
		synchronized (this) {
			messagesViewMap = newMessagesViewMap;
		}
	}
	
	public MessagesView getMessagesView(MessagesViewPK messagePK) {
		MessagesView messagesView = messagesViewMap.get(messagePK);
		return messagesView;
	}
	
	public void updateMessagesView() {
		List<MessagesView> messagesViewList = messagesViewService.getMessagesViewList();
		Map<MessagesViewPK, MessagesView> newMessagesViewMap = convertToMessageMap(messagesViewList);
		synchronized (this) {
			messagesViewMap = newMessagesViewMap;
		}
	}
	
	private Map<MessagesViewPK, MessagesView> convertToMessageMap(List<MessagesView> messagesViewList) {
		Map<MessagesViewPK, MessagesView> messagesViewMap = new HashMap<MessagesViewPK, MessagesView>();
		for (MessagesView messagesView : messagesViewList) {
			messagesViewMap.put(new MessagesViewPK(messagesView.getLangNo(), messagesView.getMessageCode()), messagesView);
		}
		return messagesViewMap;
	}


}
