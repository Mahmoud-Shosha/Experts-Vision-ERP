package com.expertsvision.erp.core.message.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.MessagesPK;


@Service
@Lazy
public class InMemoryMessagesService {
	
	
	
	private MessagesService messagesViewService;
	
	private Map<MessagesPK, MessagesView> messagesViewMap;
	
	
	@Autowired
	public InMemoryMessagesService(MessagesService messagesViewService) {
		this.messagesViewService = messagesViewService;
		List<MessagesView> messgaesViewList = messagesViewService.getMessagesViewList();
		Map<MessagesPK, MessagesView> newMessagesViewMap = convertToMessageMap(messgaesViewList);
		synchronized (this) {
			messagesViewMap = newMessagesViewMap;
		}
	}
	
	public MessagesView getMessagesView(MessagesPK messagePK) {
		MessagesView messagesView = messagesViewMap.get(messagePK);
		return messagesView;
	}
	
	public void updateMessagesView() {
		List<MessagesView> messagesViewList = messagesViewService.getMessagesViewList();
		Map<MessagesPK, MessagesView> newMessagesViewMap = convertToMessageMap(messagesViewList);
		synchronized (this) {
			messagesViewMap = newMessagesViewMap;
		}
	}
	
	private Map<MessagesPK, MessagesView> convertToMessageMap(List<MessagesView> messagesViewList) {
		Map<MessagesPK, MessagesView> messagesViewMap = new HashMap<MessagesPK, MessagesView>();
		for (MessagesView messagesView : messagesViewList) {
			messagesViewMap.put(new MessagesPK(messagesView.getLangNo(), messagesView.getMessageCode()), messagesView);
		}
		return messagesViewMap;
	}


}
