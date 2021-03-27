package com.expertsvision.erp.core.module.controller;

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

import com.expertsvision.erp.core.module.dto.ModulesViewFilter;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.module.service.ModulesViewService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;


@RestController
@RequestMapping(value = "/modules")
public class ModulesViewController {

	@Autowired
	private Response response;
	
	@Autowired
	private ModulesViewService modulesViewService;
	
	@GetMapping("")
	public ResponseEntity<Object> getModulesViewList() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ModulesView> modulesViewList = modulesViewService.getModulesViewList(loginUser);
		return response.response(modulesViewList, HttpStatus.OK);
	}
	
	@GetMapping("/{moduleNo}")
	public ResponseEntity<Object> getModulesView(@PathVariable("moduleNo") Integer moduleNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModulesView modulesView = modulesViewService.getModulesView(loginUser, moduleNo);
		return response.response(modulesView, HttpStatus.OK);
	}
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getModulesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ModulesView> singlePage = modulesViewService.getModulesViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getModulesViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ModulesView> multiplePages = modulesViewService.getModulesViewMultiplePages(loginUser, pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@GetMapping("/lastPage")
	public ResponseEntity<Object> getModulesViewLastPage() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ModulesView> singlePage = modulesViewService.getModulesViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getModulesViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
																		@RequestBody ModulesViewFilter labelsViewFilter) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ModulesView> multiplePages = modulesViewService.getModulesViewFilteredMultiplePages(loginUser, pageNo, labelsViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	
	@PutMapping("")
	public ResponseEntity<Object> updateModulesView(@RequestBody ModulesView modulesView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		modulesViewService.updateModulesView(loginUser, modulesView);
		return response.response("updated", "module", HttpStatus.OK);
	}

}
