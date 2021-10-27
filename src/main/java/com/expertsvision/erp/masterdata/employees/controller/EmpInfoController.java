package com.expertsvision.erp.masterdata.employees.controller;

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
import com.expertsvision.erp.masterdata.employees.dto.EmpInfoViewFilter;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfoView;
import com.expertsvision.erp.masterdata.employees.service.EmpInfoService;

@RestController
@RequestMapping(value = "/empinfo")
public class EmpInfoController {

	@Autowired
	private Response response;

	@Autowired
	private EmpInfoService empInfoService;

	@GetMapping("")
	public ResponseEntity<Object> getEmpInfoViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<EmpInfoView> empInfoViewList = empInfoService.getEmpInfoViewList(loginUser);
		return response.response(empInfoViewList, HttpStatus.OK);
	}

	@GetMapping("/{empNo}")
	public ResponseEntity<Object> getEmpInfoView(@PathVariable("empNo") Integer empNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		EmpInfoView empInfoView = empInfoService.getEmpInfoView(loginUser, empNo);
		return response.response(empInfoView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{empNo}")
	public ResponseEntity<Object> getEmpInfoViewSinglePageNo(@PathVariable("empNo") Integer empNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = empInfoService.getEmpInfoViewSinglePageNo(loginUser, empNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<EmpInfoView> singlePage = empInfoService.getEmpInfoViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getEmpInfoViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<EmpInfoView> singlePage = empInfoService.getEmpInfoViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getEmpInfoViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<EmpInfoView> multiplePages = empInfoService.getEmpInfoViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getEmpInfoViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody EmpInfoViewFilter empInfoViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<EmpInfoView> multiplePages = empInfoService
				.getEmpInfoViewFilteredMultiplePages(loginUser, pageNo, empInfoViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}	

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody EmpInfoView empInfoView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		empInfoService.addEmpInfo(loginUser, empInfoView);
		return response.response("added", "emp", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody EmpInfoView empInfoView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		empInfoService.updateEmpInfo(loginUser, empInfoView);
		return response.response("updated", "emp", HttpStatus.OK);
	}

	@DeleteMapping("/{empNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("empNo") Integer empNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		empInfoService.deleteEmpInfo(loginUser, empNo);
		return response.response("deleted", "emp", HttpStatus.OK);
	}
	
	@GetMapping("/preAdd")
	public ResponseEntity<Object> preAdd() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = empInfoService.preAdd(loginUser);
		return response.response(preData, HttpStatus.OK);
	}
	
	@GetMapping("/preModify")
	public ResponseEntity<Object> preModify() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = empInfoService.preModify(loginUser);
		return response.response(preData, HttpStatus.OK);
	}

}
