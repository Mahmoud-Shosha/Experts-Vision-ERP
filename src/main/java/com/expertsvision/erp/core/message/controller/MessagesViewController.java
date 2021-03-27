package com.expertsvision.erp.core.message.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.message.dto.MessagesViewFilter;
import com.expertsvision.erp.core.message.entity.MessagesView;
import com.expertsvision.erp.core.message.entity.MessagesViewPK;
import com.expertsvision.erp.core.message.service.MessagesViewService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;


@RestController
@RequestMapping(value = "/public/messages")
public class MessagesViewController {

	@Autowired
	private Response response;
	
	@Autowired
	private MessagesViewService messagesViewService;
	
	@GetMapping("")
	public ResponseEntity<Object> getMessagesViewList() {
		List<MessagesView> messagesViewList = messagesViewService.getMessagesViewList();
		return response.response(messagesViewList, HttpStatus.OK);
	}
	
	@GetMapping("/{messageCode}/{langNo}")
	public ResponseEntity<Object> getMessagesView(@PathVariable("messageCode") String messageCode,
												  @PathVariable("langNo") Integer langNo) {
		MessagesView messagesView = messagesViewService.getMessagesView(new MessagesViewPK(langNo, messageCode));
		return response.response(messagesView, HttpStatus.OK);
	}
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		SinglePage<MessagesView> singlePage = messagesViewService.getMessagesViewSinglePage(pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getMessagesViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		MultiplePages<MessagesView> multiplePages = messagesViewService.getMessagesViewMultiplePages(pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@GetMapping("/lastPage")
	public ResponseEntity<Object> getMessagesViewLastPage() {
		SinglePage<MessagesView> singlePage = messagesViewService.getMessagesViewLastPage();
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getMessagesViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
																		@RequestBody MessagesViewFilter messagesViewFilter) {
		MultiplePages<MessagesView> multiplePages = messagesViewService.getMessagesViewFilteredMultiplePages(pageNo, messagesViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addMessagesView(@RequestBody MessagesView messagesView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		messagesViewService.addMessagesView(loginUser, messagesView);
		return response.response("added", "message", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateMessagesView(@RequestBody MessagesView messagesView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		messagesViewService.updateMessagesView(loginUser, messagesView);
		return response.response("updated", "message", HttpStatus.OK);
	}
	
	@DeleteMapping("/{messageCode}/{langNo}")
	public ResponseEntity<Object> deleteMessagesView(@PathVariable("messageCode") String messageCode,
												   	 @PathVariable("langNo") Integer langNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		messagesViewService.deleteMessagesView(loginUser, new MessagesViewPK(langNo, messageCode));
		return response.response("deleted", "message", HttpStatus.OK);
	}

}
