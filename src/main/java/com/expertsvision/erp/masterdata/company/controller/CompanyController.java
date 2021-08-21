package com.expertsvision.erp.masterdata.company.controller;

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
import com.expertsvision.erp.masterdata.company.dto.CompanyViewFilter;
import com.expertsvision.erp.masterdata.company.entity.CompanyView;
import com.expertsvision.erp.masterdata.company.service.CompanyService;

@RestController
@RequestMapping(value = "/company")
public class CompanyController {

	@Autowired
	private Response response;

	@Autowired
	private CompanyService companyService;

	@GetMapping("")
	public ResponseEntity<Object> getCompanyViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CompanyView> companyViewList = companyService.getCompanyViewList(loginUser);
		return response.response(companyViewList, HttpStatus.OK);
	}

	@GetMapping("/{companyNo}")
	public ResponseEntity<Object> getCompanyView(@PathVariable("companyNo") Integer companyNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CompanyView companyView = companyService.getCompanyView(loginUser, companyNo);
		return response.response(companyView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{companyNo}")
	public ResponseEntity<Object> getCompanyViewSinglePageNo(@PathVariable("companyNo") Integer companyNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = companyService.getCompanyViewSinglePageNo(loginUser, companyNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CompanyView> singlePage = companyService.getCompanyViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCompanyViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CompanyView> singlePage = companyService.getCompanyViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCompanyViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CompanyView> multiplePages = companyService.getCompanyViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCompanyViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CompanyViewFilter companyViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CompanyView> multiplePages = companyService
				.getCompanyViewFilteredMultiplePages(loginUser, pageNo, companyViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody CompanyView companyView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		companyService.addCompany(loginUser, companyView);
		return response.response("added", "company", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody CompanyView companyView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		companyService.updateCompany(loginUser, companyView);
		return response.response("updated", "company", HttpStatus.OK);
	}

	@DeleteMapping("/{companyNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("companyNo") Integer companyNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		companyService.deleteCompany(loginUser, companyNo);
		return response.response("deleted", "company", HttpStatus.OK);
	}

}
