package com.expertsvision.erp.core.label.controller;

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

import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsViewPK;
import com.expertsvision.erp.core.label.service.LabelsViewService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@RestController
@RequestMapping(value = "/public/labels")
public class LabelsViewController {
		
	@Autowired
	private Response response;
	
	@Autowired
	private LabelsViewService labelsViewService;
	
	@GetMapping("")
	public ResponseEntity<Object> getLabelsViewList() {
		List<LabelsView> labelsViewList = labelsViewService.getLabelsViewList();
		return response.response(labelsViewList, HttpStatus.OK);
	}
	
	@GetMapping("/{labelCode}/{langNo}")
	public ResponseEntity<Object> getLabelsView(@PathVariable("labelCode") String labelCode,
												@PathVariable("langNo") Integer langNo) {
		LabelsView labelsView = labelsViewService.getLabelsView(new LabelsViewPK(langNo, labelCode));
		return response.response(labelsView, HttpStatus.OK);
	}
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getLabelsViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		SinglePage<LabelsView> singlePage = labelsViewService.getLabelsViewSinglePage(pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/lastPage")
	public ResponseEntity<Object> getMessagesViewLastPage() {
		SinglePage<LabelsView> singlePage = labelsViewService.getLabelsViewLastPage();
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getLabelsViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		MultiplePages<LabelsView> multiplePages = labelsViewService.getLabelsViewMultiplePages(pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getLabelsViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
																		@RequestBody LabelsViewFilter labelsViewFilter) {
		MultiplePages<LabelsView> multiplePages = labelsViewService.getLabelsViewFilteredMultiplePages(pageNo, labelsViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addLabelsView(@RequestBody LabelsView labelsView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		labelsViewService.addLabelsView(loginUser, labelsView);
		return response.response("added", "label", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateLabelsView(@RequestBody LabelsView labelsView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		labelsViewService.updateLabelsView(loginUser, labelsView);
		return response.response("updated", "label", HttpStatus.OK);
	}
	
	@DeleteMapping("/{labelCode}/{langNo}")
	public ResponseEntity<Object> deleteLabelsView(@PathVariable("labelCode") String labelCode,
												   @PathVariable("langNo") Integer langNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		labelsViewService.deleteLabelsView(loginUser, new LabelsViewPK(langNo, labelCode));
		return response.response("deleted", "label", HttpStatus.OK);
	}

}
