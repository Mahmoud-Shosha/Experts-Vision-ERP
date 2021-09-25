package com.expertsvision.erp.masterdata.costcentergroup.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.costcentergroup.dto.CostCenterGroupViewFilter;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroupView;
import com.expertsvision.erp.masterdata.costcentergroup.service.CostCenterGroupService;

@RestController
@RequestMapping(value = "/costcentergroup")
public class CostCenterGroupController {

	@Autowired
	private Response response;

	@Autowired
	private CostCenterGroupService costCenterGroupService;

	@GetMapping("")
	public ResponseEntity<Object> getCostCenterGroupViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CostCenterGroupView> costCenterGroupViewList = costCenterGroupService.getCostCenterGroupViewList(loginUser);
		return response.response(costCenterGroupViewList, HttpStatus.OK);
	}

	@GetMapping("/{groupNo}")
	public ResponseEntity<Object> getCostCenterGroupView(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CostCenterGroupView costCenterGroupView = costCenterGroupService.getCostCenterGroupView(loginUser, groupNo);
		return response.response(costCenterGroupView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{groupNo}")
	public ResponseEntity<Object> getCostCenterGroupViewSinglePageNo(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = costCenterGroupService.getCostCenterGroupViewSinglePageNo(loginUser, groupNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CostCenterGroupView> singlePage = costCenterGroupService.getCostCenterGroupViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCostCenterGroupViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CostCenterGroupView> singlePage = costCenterGroupService.getCostCenterGroupViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCostCenterGroupViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CostCenterGroupView> multiplePages = costCenterGroupService.getCostCenterGroupViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCostCenterGroupViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CostCenterGroupViewFilter costCenterGroupViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CostCenterGroupView> multiplePages = costCenterGroupService
				.getCostCenterGroupViewFilteredMultiplePages(loginUser, pageNo, costCenterGroupViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody CostCenterGroupView costCenterGroupView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		costCenterGroupService.addCostCenterGroup(loginUser, costCenterGroupView);
		return response.response("added", "group", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody CostCenterGroupView costCenterGroupView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		costCenterGroupService.updateCostCenterGroup(loginUser, costCenterGroupView);
		return response.response("updated", "group", HttpStatus.OK);
	}

	@DeleteMapping("/{groupNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		costCenterGroupService.deleteCostCenterGroup(loginUser, groupNo);
		return response.response("deleted", "group", HttpStatus.OK);
	}
	
	@GetMapping("/preAdd")
	public ResponseEntity<Object> preAdd() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = costCenterGroupService.preAdd(loginUser);
		return response.response(preData, HttpStatus.OK);
	}
	
	@GetMapping("/preModify")
	public ResponseEntity<Object> preModify() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = costCenterGroupService.preModify(loginUser);
		return response.response(preData, HttpStatus.OK);
	}

}
