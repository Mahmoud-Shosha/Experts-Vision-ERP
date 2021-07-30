package com.expertsvision.erp.masterdata.region.controller;

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
import com.expertsvision.erp.masterdata.region.dto.RegionViewFilter;
import com.expertsvision.erp.masterdata.region.entity.RegionView;
import com.expertsvision.erp.masterdata.region.service.RegionService;

@RestController
@RequestMapping(value = "/region")
public class RegionController {

	@Autowired
	private Response response;

	@Autowired
	private RegionService regionService;

	@GetMapping("")
	public ResponseEntity<Object> getRegionViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<RegionView> regionViewList = regionService.getRegionViewList(loginUser);
		return response.response(regionViewList, HttpStatus.OK);
	}

	@GetMapping("/{regionNo}")
	public ResponseEntity<Object> getRegionView(@PathVariable("regionNo") Integer regionNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		RegionView regionView = regionService.getRegionView(loginUser, regionNo);
		return response.response(regionView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{regionNo}")
	public ResponseEntity<Object> getRegionViewSinglePageNo(@PathVariable("regionNo") Integer regionNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = regionService.getRegionViewSinglePageNo(loginUser, regionNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<RegionView> singlePage = regionService.getRegionViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getRegionViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<RegionView> singlePage = regionService.getRegionViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getRegionViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<RegionView> multiplePages = regionService.getRegionViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getRegionViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody RegionViewFilter regionViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<RegionView> multiplePages = regionService
				.getRegionViewFilteredMultiplePages(loginUser, pageNo, regionViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody RegionView regionView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		regionService.addRegion(loginUser, regionView);
		return response.response("added", "region", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody RegionView regionView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		regionService.updateRegion(loginUser, regionView);
		return response.response("updated", "region", HttpStatus.OK);
	}

	@DeleteMapping("/{regionNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("regionNo") Integer regionNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		regionService.deleteRegion(loginUser, regionNo);
		return response.response("deleted", "region", HttpStatus.OK);
	}

}
