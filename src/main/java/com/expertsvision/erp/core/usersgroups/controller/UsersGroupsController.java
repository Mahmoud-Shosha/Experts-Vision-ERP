package com.expertsvision.erp.core.usersgroups.controller;

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
import com.expertsvision.erp.core.usersgroups.dto.UsersGroupsViewFilter;
import com.expertsvision.erp.core.usersgroups.entity.UsersGroupsView;
import com.expertsvision.erp.core.usersgroups.service.UsersGroupsService;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@RestController
@RequestMapping(value = "/usersgroups")
public class UsersGroupsController {

	@Autowired
	private Response response;

	@Autowired
	private UsersGroupsService usersGroupsService;

	@GetMapping("")
	public ResponseEntity<Object> getUsersGroupsViewSubordinateList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<UsersGroupsView> usersGroupsViewList = usersGroupsService.getUsersGroupsViewList(loginUser);
		return response.response(usersGroupsViewList, HttpStatus.OK);
	}

	@GetMapping("/{groupNo}")
	public ResponseEntity<Object> getUsersGroupsView(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UsersGroupsView usersGroupsView = usersGroupsService.getUsersGroupsView(loginUser, groupNo);
		return response.response(usersGroupsView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{groupNo}")
	public ResponseEntity<Object> getUsersGroupsViewSinglePageNo(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = usersGroupsService.getUsersGroupsViewSinglePageNo(loginUser, groupNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<UsersGroupsView> singlePage = usersGroupsService.getUsersGroupsViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getUsersGroupsViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<UsersGroupsView> singlePage = usersGroupsService.getUsersGroupsViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getUsersGroupsViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<UsersGroupsView> multiplePages = usersGroupsService.getUsersGroupsViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getUsersGroupsViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody UsersGroupsViewFilter usersGroupsViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<UsersGroupsView> multiplePages = usersGroupsService
				.getUsersGroupsViewFilteredMultiplePages(loginUser, pageNo, usersGroupsViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody UsersGroupsView usersGroupsView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usersGroupsService.addUsersGroups(loginUser, usersGroupsView);
		return response.response("added", "group", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody UsersGroupsView usersGroupsView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usersGroupsService.updateUsersGroups(loginUser, usersGroupsView);
		return response.response("updated", "group", HttpStatus.OK);
	}

	@DeleteMapping("/{groupNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("groupNo") Integer groupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		usersGroupsService.deleteUsersGroups(loginUser, groupNo);
		return response.response("deleted", "group", HttpStatus.OK);
	}

}
