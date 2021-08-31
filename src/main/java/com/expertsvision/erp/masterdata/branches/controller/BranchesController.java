package com.expertsvision.erp.masterdata.branches.controller;

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
import com.expertsvision.erp.masterdata.branches.dto.BranchesViewFilter;
import com.expertsvision.erp.masterdata.branches.entity.BranchesView;
import com.expertsvision.erp.masterdata.branches.service.BranchesService;

@RestController
@RequestMapping(value = "/branches")
public class BranchesController {

	@Autowired
	private Response response;

	@Autowired
	private BranchesService branchesService;

	@GetMapping("")
	public ResponseEntity<Object> getBranchesViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<BranchesView> branchesViewList = branchesService.getBranchesViewList(loginUser);
		return response.response(branchesViewList, HttpStatus.OK);
	}

	@GetMapping("/{branchesNo}")
	public ResponseEntity<Object> getBranchesView(@PathVariable("branchesNo") Integer branchesNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BranchesView branchesView = branchesService.getBranchesView(loginUser, branchesNo);
		return response.response(branchesView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{branchesNo}")
	public ResponseEntity<Object> getBranchesViewSinglePageNo(@PathVariable("branchesNo") Integer branchesNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = branchesService.getBranchesViewSinglePageNo(loginUser, branchesNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<BranchesView> singlePage = branchesService.getBranchesViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getBranchesViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<BranchesView> singlePage = branchesService.getBranchesViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getBranchesViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<BranchesView> multiplePages = branchesService.getBranchesViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getBranchesViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody BranchesViewFilter branchesViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<BranchesView> multiplePages = branchesService
				.getBranchesViewFilteredMultiplePages(loginUser, pageNo, branchesViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@GetMapping("/nextPK")
	public ResponseEntity<Object> getNextPK() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object PK = branchesService.getNextPK(loginUser);
		return response.response(NextPK.build(PK), HttpStatus.OK);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<Object> addUsersGroup(@RequestBody BranchesView branchesView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		branchesService.addBranches(loginUser, branchesView);
		return response.response("added", "branch", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody BranchesView branchesView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		branchesService.updateBranches(loginUser, branchesView);
		return response.response("updated", "branch", HttpStatus.OK);
	}

	@DeleteMapping("/{branchesNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("branchesNo") Integer branchesNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		branchesService.deleteBranches(loginUser, branchesNo);
		return response.response("deleted", "branch", HttpStatus.OK);
	}

}
