package com.expertsvision.erp.masterdata.province.controller;

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
import com.expertsvision.erp.masterdata.province.dto.ProvinceViewFilter;
import com.expertsvision.erp.masterdata.province.entity.ProvinceView;
import com.expertsvision.erp.masterdata.province.service.ProvinceService;

@RestController
@RequestMapping(value = "/province")
public class ProvinceController {

	@Autowired
	private Response response;

	@Autowired
	private ProvinceService provinceService;

	@GetMapping("")
	public ResponseEntity<Object> getProvinceViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ProvinceView> provinceViewList = provinceService.getProvinceViewList(loginUser);
		return response.response(provinceViewList, HttpStatus.OK);
	}

	@GetMapping("/{provinceNo}")
	public ResponseEntity<Object> getProvinceView(@PathVariable("provinceNo") Integer provinceNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ProvinceView provinceView = provinceService.getProvinceView(loginUser, provinceNo);
		return response.response(provinceView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{provinceNo}")
	public ResponseEntity<Object> getProvinceViewSinglePageNo(@PathVariable("provinceNo") Integer provinceNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = provinceService.getProvinceViewSinglePageNo(loginUser, provinceNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ProvinceView> singlePage = provinceService.getProvinceViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getProvinceViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ProvinceView> singlePage = provinceService.getProvinceViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getProvinceViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ProvinceView> multiplePages = provinceService.getProvinceViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getProvinceViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody ProvinceViewFilter provinceViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ProvinceView> multiplePages = provinceService
				.getProvinceViewFilteredMultiplePages(loginUser, pageNo, provinceViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody ProvinceView provinceView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		provinceService.addProvince(loginUser, provinceView);
		return response.response("added", "province", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody ProvinceView provinceView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		provinceService.updateProvince(loginUser, provinceView);
		return response.response("updated", "province", HttpStatus.OK);
	}

	@DeleteMapping("/{provinceNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("provinceNo") Integer provinceNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		provinceService.deleteProvince(loginUser, provinceNo);
		return response.response("deleted", "province", HttpStatus.OK);
	}

}
