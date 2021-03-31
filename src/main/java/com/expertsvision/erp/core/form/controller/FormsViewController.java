package com.expertsvision.erp.core.form.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.form.service.FormsViewService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;


@RestController
@RequestMapping(value = "/forms")
public class FormsViewController {

	@Autowired
	private Response response;
	
	@Autowired
	private FormsViewService formsViewService;
	
	@GetMapping("")
	public ResponseEntity<Object> getFormsViewList() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FormsView> formsViewList = formsViewService.getFormsViewList(loginUser);
		return response.response(formsViewList, HttpStatus.OK);
	}
	
	@GetMapping("/{formNo}")
	public ResponseEntity<Object> getFormsView(@PathVariable("formNo") Integer formNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FormsView formsView = formsViewService.getFormsView(loginUser, formNo);
		return response.response(formsView, HttpStatus.OK);
	}
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getFormsViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<FormsView> singlePage = formsViewService.getFormsViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/lastPage")
	public ResponseEntity<Object> getFormsViewLastPage() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<FormsView> singlePage = formsViewService.getFormsViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getFormsViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<FormsView> multiplePages = formsViewService.getFormsViewMultiplePages(loginUser, pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getFormsViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
																		@RequestBody FormsViewFilter labelsViewFilter) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<FormsView> multiplePages = formsViewService.getFormsViewFilteredMultiplePages(loginUser, pageNo, labelsViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@GetMapping("/mainTree")
	public ResponseEntity<Object> getFormsViewMainTree() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FormsView> formsViewList = formsViewService.getFormsViewMainTree(loginUser);
		return response.response(formsViewList, HttpStatus.OK);
	}
	
	
	@PutMapping("")
	public ResponseEntity<Object> updateFormsView(@RequestBody FormsView formsView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		formsViewService.updateFormsView(loginUser, formsView);
		return response.response("updated", "form", HttpStatus.OK);
	}

}
