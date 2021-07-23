package com.expertsvision.erp.core.privilege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.privilege.service.FormPrivilageService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;

@RestController
@RequestMapping("/formprivileges")
public class FormPrivilageController {

	@Autowired
	private Response response;

	@Autowired
	private FormPrivilageService formPrivilageService;

	@GetMapping("/{userId}")
	public ResponseEntity<Object> getFormPrivilageViewList(@PathVariable("userId") Integer userId) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FormPrivilageView> formPrivilageViewList = formPrivilageService.getFormPrivilageViewList(loginUser, userId);
		return response.response(formPrivilageViewList, HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updatFormPrivilageView(@RequestBody FormPrivilageView formPrivilageView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		formPrivilageService.updateFormPrivilage(loginUser, formPrivilageView);
		return response.response("updated", "privilege", HttpStatus.OK);
	}

}
