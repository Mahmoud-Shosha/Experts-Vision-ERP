package com.expertsvision.erp.masterdata.costcenters.controller;

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
import com.expertsvision.erp.core.utils.NextPK;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.costcenters.dto.CostCenterViewFilter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterView;
import com.expertsvision.erp.masterdata.costcenters.service.CostCenterService;

@RestController
@RequestMapping(value = "/costcenters")
public class CostCenterController {

	@Autowired
	private Response response;

	@Autowired
	private CostCenterService costCenterService;

	@GetMapping("")
	public ResponseEntity<Object> getCostCenterViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CostCenterView> costCenterViewList = costCenterService.getCostCenterViewList(loginUser);
		return response.response(costCenterViewList, HttpStatus.OK);
	}

	@GetMapping("/{ccNo}")
	public ResponseEntity<Object> getCostCenterView(@PathVariable("ccNo") Integer ccNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CostCenterView costCenterView = costCenterService.getCostCenterView(loginUser, ccNo);
		return response.response(costCenterView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{ccNo}")
	public ResponseEntity<Object> getCostCenterViewSinglePageNo(@PathVariable("ccNo") Integer ccNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = costCenterService.getCostCenterViewSinglePageNo(loginUser, ccNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CostCenterView> singlePage = costCenterService.getCostCenterViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCostCenterViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CostCenterView> singlePage = costCenterService.getCostCenterViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCostCenterViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CostCenterView> multiplePages = costCenterService.getCostCenterViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCostCenterViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CostCenterViewFilter costCenterViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CostCenterView> multiplePages = costCenterService
				.getCostCenterViewFilteredMultiplePages(loginUser, pageNo, costCenterViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@GetMapping("/nextPK/{parentCc}")
	public ResponseEntity<Object> getNextPK(@PathVariable("parentCc") Integer parentCc) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object PK = costCenterService.getNextPK(loginUser, parentCc);
		return response.response(NextPK.build(PK), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody CostCenterView costCenterView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		costCenterService.addCostCenter(loginUser, costCenterView);
		return response.response("added", "cost_center", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody CostCenterView costCenterView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		costCenterService.updateCostCenter(loginUser, costCenterView);
		return response.response("updated", "cost_center", HttpStatus.OK);
	}

	@DeleteMapping("/{ccNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("ccNo") Integer ccNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		costCenterService.deleteCostCenter(loginUser, ccNo);
		return response.response("deleted", "cost_center", HttpStatus.OK);
	}

}
