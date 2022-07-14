package com.expertsvision.erp.masterdata.inventory.maingroups.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.inventory.maingroups.dto.MainGroupViewFilter;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupView;
import com.expertsvision.erp.masterdata.inventory.maingroups.service.MainGroupService;

@RestController
@RequestMapping(value = "/inv/maingroup")
public class MainGroupController {

	@Autowired
	private Response response;

	@Autowired
	private MainGroupService mainGroupService;

	@GetMapping("/{groupCode}")
	public ResponseEntity<Object> getMainGroupView(@PathVariable("groupCode") String groupCode) {
		MainGroupView mainGroupView = mainGroupService.getMainGroupView(groupCode);
		return response.response(mainGroupView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{groupCode}")
	public ResponseEntity<Object> getMainGroupViewSinglePageNo(@PathVariable("groupCode") String groupCode) {
		long singlePageNo = mainGroupService.getMainGroupViewSinglePageNo(groupCode);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		SinglePage<MainGroupView> singlePage = mainGroupService.getMainGroupViewSinglePage(pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getMainGroupViewLastPage() {
		SinglePage<MainGroupView> singlePage = mainGroupService.getMainGroupViewLastPage();
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getMainGroupViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		MultiplePages<MainGroupView> multiplePages = mainGroupService.getMainGroupViewMultiplePages(pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getMainGroupViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody MainGroupViewFilter mainGroupViewFilter) {
		MultiplePages<MainGroupView> multiplePages = mainGroupService.getMainGroupViewFilteredMultiplePages(pageNo,
				mainGroupViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping(path = "")
	public ResponseEntity<Object> addUsersGroup(@RequestBody MainGroupView mainGroupView) {
		mainGroupService.addMainGroup(mainGroupView);
		return response.response("added", "group", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody MainGroupView mainGroupView) {
		mainGroupService.updateMainGroup(mainGroupView);
		return response.response("updated", "group", HttpStatus.OK);
	}

	@DeleteMapping("/{groupCode}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("groupCode") String groupCode) {
		mainGroupService.deleteMainGroup(groupCode);
		return response.response("deleted", "group", HttpStatus.OK);
	}

}
