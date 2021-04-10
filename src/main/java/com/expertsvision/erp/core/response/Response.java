package com.expertsvision.erp.core.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.label.entity.LabelsPK;
import com.expertsvision.erp.core.label.service.InMemoryLabelsService;
import com.expertsvision.erp.core.message.entity.MessagesViewPK;
import com.expertsvision.erp.core.message.service.InMemoryMessagesViewService;

@Service
public class Response {

	@Autowired
	private InMemoryLabelsService inMemoryLabelsViewService;
	
	@Autowired
	private InMemoryMessagesViewService inMemoryMessagesViewService;
	
	
	public ResponseEntity<Object> response(Object list, HttpStatus httpStatus) {
		ResponseEntity<Object> response = new ResponseEntity<Object>(list, httpStatus);
		return response;
	}
	
	public ResponseEntity<Object> response(String messageCode, HttpStatus httpStatus) {
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(2, messageCode)).getMessageDesc();
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		message.get("message").put("ar", messageAr);
		message.get("message").put("en", messageEn);
		ResponseEntity<Object> response = new ResponseEntity<Object>(message, httpStatus);
		return response;
	}
	
	public ResponseEntity<Object> response(String messageCode, String labelCode, HttpStatus httpStatus) {
		String labelAr = inMemoryLabelsViewService.getLabelsView(new LabelsPK(1, labelCode)).getLabelDesc();
		String labelEn = inMemoryLabelsViewService.getLabelsView(new LabelsPK(2, labelCode)).getLabelDesc();
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(2, messageCode)).getMessageDesc();
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		message.get("message").put("ar", messageAr.replace("#1", labelAr));
		message.get("message").put("en", messageEn.replace("#1", labelEn));
		ResponseEntity<Object> response = new ResponseEntity<Object>(message, httpStatus);
		return response;
	}
	
	public ResponseEntity<Object> response(String messageCode, String labelCode, Object value, HttpStatus httpStatus) {
		String labelAr = inMemoryLabelsViewService.getLabelsView(new LabelsPK(1, labelCode)).getLabelDesc();
		String labelEn = inMemoryLabelsViewService.getLabelsView(new LabelsPK(2, labelCode)).getLabelDesc();
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(2, messageCode)).getMessageDesc();
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		message.get("message").put("ar", messageAr.replace("#1", labelAr).replace("#v1", value.toString()));
		message.get("message").put("en", messageEn.replace("#1", labelEn).replace("#v1", value.toString()));
		ResponseEntity<Object> response = new ResponseEntity<Object>(message, httpStatus);
		return response;
	}
	
	public ResponseEntity<Object> response(String messageCode, String labelCode, String valueCode, HttpStatus httpStatus) {
		String valueAr = inMemoryLabelsViewService.getLabelsView(new LabelsPK(1, valueCode)).getLabelDesc();
		String valueEn = inMemoryLabelsViewService.getLabelsView(new LabelsPK(2, valueCode)).getLabelDesc();
		String labelAr = inMemoryLabelsViewService.getLabelsView(new LabelsPK(1, labelCode)).getLabelDesc();
		String labelEn = inMemoryLabelsViewService.getLabelsView(new LabelsPK(2, labelCode)).getLabelDesc();
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesViewPK(2, messageCode)).getMessageDesc();
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		System.out.println(valueAr + valueEn);
		message.get("message").put("ar", messageAr.replace("#1", labelAr).replace("#v1", valueAr));
		message.get("message").put("en", messageEn.replace("#1", labelEn).replace("#v1", valueEn));
		ResponseEntity<Object> response = new ResponseEntity<Object>(message, httpStatus);
		return response;
	}
	
}
