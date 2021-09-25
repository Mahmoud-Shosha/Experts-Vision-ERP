package com.expertsvision.erp.masterdata.accountsgroup.controller;

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
import com.expertsvision.erp.masterdata.accountsgroup.dto.AccountsGroupViewFilter;
import com.expertsvision.erp.masterdata.accountsgroup.entity.AccountsGroupView;
import com.expertsvision.erp.masterdata.accountsgroup.service.AccountsGroupService;

@RestController
@RequestMapping(value = "/accountsgroup")
public class AccountsGroupController {

	@Autowired
	private Response response;

	@Autowired
	private AccountsGroupService accountsGroupService;

	@GetMapping("")
	public ResponseEntity<Object> getAccountsGroupViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<AccountsGroupView> accountsGroupViewList = accountsGroupService.getAccountsGroupViewList(loginUser);
		return response.response(accountsGroupViewList, HttpStatus.OK);
	}

	@GetMapping("/{groupNo}")
	public ResponseEntity<Object> getAccountsGroupView(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		AccountsGroupView accountsGroupView = accountsGroupService.getAccountsGroupView(loginUser, groupNo);
		return response.response(accountsGroupView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{groupNo}")
	public ResponseEntity<Object> getAccountsGroupViewSinglePageNo(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = accountsGroupService.getAccountsGroupViewSinglePageNo(loginUser, groupNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<AccountsGroupView> singlePage = accountsGroupService.getAccountsGroupViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getAccountsGroupViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<AccountsGroupView> singlePage = accountsGroupService.getAccountsGroupViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getAccountsGroupViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<AccountsGroupView> multiplePages = accountsGroupService.getAccountsGroupViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getAccountsGroupViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody AccountsGroupViewFilter accountsGroupViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<AccountsGroupView> multiplePages = accountsGroupService
				.getAccountsGroupViewFilteredMultiplePages(loginUser, pageNo, accountsGroupViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody AccountsGroupView accountsGroupView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		accountsGroupService.addAccountsGroup(loginUser, accountsGroupView);
		return response.response("added", "group", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody AccountsGroupView accountsGroupView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		accountsGroupService.updateAccountsGroup(loginUser, accountsGroupView);
		return response.response("updated", "group", HttpStatus.OK);
	}

	@DeleteMapping("/{groupNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		accountsGroupService.deleteAccountsGroup(loginUser, groupNo);
		return response.response("deleted", "group", HttpStatus.OK);
	}
	
	@GetMapping("/preAdd")
	public ResponseEntity<Object> preAdd() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = accountsGroupService.preAdd(loginUser);
		return response.response(preData, HttpStatus.OK);
	}
	
	@GetMapping("/preModify")
	public ResponseEntity<Object> preModify() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = accountsGroupService.preModify(loginUser);
		return response.response(preData, HttpStatus.OK);
	}

}
