package com.expertsvision.erp.core.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.label.entity.LabelsPK;
import com.expertsvision.erp.core.label.service.InMemoryLabelsService;
import com.expertsvision.erp.core.message.entity.MessagesPK;
import com.expertsvision.erp.core.message.service.InMemoryMessagesService;

@Service
public class Response {

	@Autowired
	private InMemoryLabelsService inMemoryLabelsViewService;
	
	@Autowired
	private InMemoryMessagesService inMemoryMessagesViewService;
	
	
	public ResponseEntity<Object> response(Object list, HttpStatus httpStatus) {
		ResponseEntity<Object> response = new ResponseEntity<Object>(list, httpStatus);
		return response;
	}
	
	public ResponseEntity<Object> response(String messageCode, HttpStatus httpStatus) {
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesPK(2, messageCode)).getMessageDesc();
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
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesPK(2, messageCode)).getMessageDesc();
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
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesPK(2, messageCode)).getMessageDesc();
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
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesPK(2, messageCode)).getMessageDesc();
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		System.out.println(valueAr + valueEn);
		message.get("message").put("ar", messageAr.replace("#1", labelAr).replace("#v1", valueAr));
		message.get("message").put("en", messageEn.replace("#1", labelEn).replace("#v1", valueEn));
		ResponseEntity<Object> response = new ResponseEntity<Object>(message, httpStatus);
		return response;
	}
	
	public ResponseEntity<Object> response(String messageCode, String labelCodeFirst, Object valueFirst, String labelCodeSecond, Object valueSecond, HttpStatus httpStatus) {
		String messageAr = inMemoryMessagesViewService.getMessagesView(new MessagesPK(1, messageCode)).getMessageDesc();
		String messageEn = inMemoryMessagesViewService.getMessagesView(new MessagesPK(2, messageCode)).getMessageDesc();
		if (labelCodeFirst != null && !labelCodeFirst.isBlank()) {
			String labelFirstAr = inMemoryLabelsViewService.getLabelsView(new LabelsPK(1, labelCodeFirst)).getLabelDesc();
			String labelFirstEn = inMemoryLabelsViewService.getLabelsView(new LabelsPK(2, labelCodeFirst)).getLabelDesc();
			messageAr = messageAr.replace("#1", labelFirstAr);
			messageEn = messageEn.replace("#1", labelFirstEn);
		} else {
			messageAr = messageAr.replace("#1", "");
			messageEn = messageEn.replace("#1", "");
		}
		if (labelCodeSecond != null && !labelCodeSecond.isBlank()) {
			String labelSecondAr = inMemoryLabelsViewService.getLabelsView(new LabelsPK(1, labelCodeSecond)).getLabelDesc();
			String labelSecondEn = inMemoryLabelsViewService.getLabelsView(new LabelsPK(2, labelCodeSecond)).getLabelDesc();
			messageAr = messageAr.replace("#2", labelSecondAr);
			messageEn = messageEn.replace("#2", labelSecondEn);
		} else {
			messageAr = messageAr.replace("#2", "");
			messageEn = messageEn.replace("#2", "");
		}
		if (valueFirst != null && !valueFirst.toString().isBlank()) {
			messageAr = messageAr.replace("#v1", valueFirst.toString());
			messageEn = messageEn.replace("#v1", valueFirst.toString());
		} else {
			messageAr = messageAr.replace("#v1", "");
			messageEn = messageEn.replace("#v1", "");
		}
		if (valueSecond != null && !valueSecond.toString().isBlank()) {
			messageAr = messageAr.replace("#v2", valueSecond.toString());
			messageEn = messageEn.replace("#v2", valueSecond.toString());
		} else {
			messageAr = messageAr.replace("#v2", "");
			messageEn = messageEn.replace("#v2", "");
		}
		Map<String, Map<String, String>> message = new HashMap<String, Map<String, String>>();
		message.put("message", new HashMap<String, String>());
		message.get("message").put("ar", messageAr);
		message.get("message").put("en", messageEn);
		ResponseEntity<Object> response = new ResponseEntity<Object>(message, httpStatus);
		return response;
	}
	
}
