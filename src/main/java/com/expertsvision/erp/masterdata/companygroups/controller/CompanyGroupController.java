package com.expertsvision.erp.masterdata.companygroups.controller;

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
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.companygroups.dto.CompanyGroupsViewFilter;
import com.expertsvision.erp.masterdata.companygroups.entity.CompanyGroupsView;
import com.expertsvision.erp.masterdata.companygroups.service.CompanyGroupsService;

@RestController
@RequestMapping(value = "/companyGroup")
public class CompanyGroupController {

	@Autowired
	private Response response;

	@Autowired
	private CompanyGroupsService companyGroupService;

	@GetMapping("")
	public ResponseEntity<Object> getCompanyGroupViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CompanyGroupsView> companyGroupViewList = companyGroupService.getCompanyGroupsViewList(loginUser);
		return response.response(companyGroupViewList, HttpStatus.OK);
	}

	@GetMapping("/{companyGroupNo}")
	public ResponseEntity<Object> getCompanyGroupView(@PathVariable("companyGroupNo") Integer companyGroupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CompanyGroupsView companyGroupView = companyGroupService.getCompanyGroupsView(loginUser, companyGroupNo);
		return response.response(companyGroupView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{companyGroupNo}")
	public ResponseEntity<Object> getCompanyGroupViewSinglePageNo(@PathVariable("companyGroupNo") Integer companyGroupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = companyGroupService.getCompanyGroupsViewSinglePageNo(loginUser, companyGroupNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CompanyGroupsView> singlePage = companyGroupService.getCompanyGroupsViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCompanyGroupViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CompanyGroupsView> singlePage = companyGroupService.getCompanyGroupsViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCompanyGroupViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CompanyGroupsView> multiplePages = companyGroupService.getCompanyGroupsViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCompanyGroupViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CompanyGroupsViewFilter companyGroupViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CompanyGroupsView> multiplePages = companyGroupService
				.getCompanyGroupsViewFilteredMultiplePages(loginUser, pageNo, companyGroupViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody CompanyGroupsView companyGroupView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		companyGroupService.addCompanyGroups(loginUser, companyGroupView);
		return response.response("added", "group", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody CompanyGroupsView companyGroupView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		companyGroupService.updateCompanyGroups(loginUser, companyGroupView);
		return response.response("updated", "group", HttpStatus.OK);
	}

	@DeleteMapping("/{companyGroupNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("companyGroupNo") Integer companyGroupNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		companyGroupService.deleteCompanyGroups(loginUser, companyGroupNo);
		return response.response("deleted", "group", HttpStatus.OK);
	}

}
