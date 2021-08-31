package com.expertsvision.erp.masterdata.zone.controller;

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
import com.expertsvision.erp.masterdata.zone.dto.ZoneViewFilter;
import com.expertsvision.erp.masterdata.zone.entity.ZoneView;
import com.expertsvision.erp.masterdata.zone.service.ZoneService;

@RestController
@RequestMapping(value = "/zone")
public class ZoneController {

	@Autowired
	private Response response;

	@Autowired
	private ZoneService zoneService;

	@GetMapping("")
	public ResponseEntity<Object> getZoneViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ZoneView> zoneViewList = zoneService.getZoneViewList(loginUser);
		return response.response(zoneViewList, HttpStatus.OK);
	}

	@GetMapping("/{zoneNo}")
	public ResponseEntity<Object> getZoneView(@PathVariable("zoneNo") Integer zoneNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ZoneView zoneView = zoneService.getZoneView(loginUser, zoneNo);
		return response.response(zoneView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{zoneNo}")
	public ResponseEntity<Object> getZoneViewSinglePageNo(@PathVariable("zoneNo") Integer zoneNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = zoneService.getZoneViewSinglePageNo(loginUser, zoneNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ZoneView> singlePage = zoneService.getZoneViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getZoneViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ZoneView> singlePage = zoneService.getZoneViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getZoneViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ZoneView> multiplePages = zoneService.getZoneViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getZoneViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody ZoneViewFilter zoneViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ZoneView> multiplePages = zoneService
				.getZoneViewFilteredMultiplePages(loginUser, pageNo, zoneViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@GetMapping("/nextPK")
	public ResponseEntity<Object> getNextPK() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object PK = zoneService.getNextPK(loginUser);
		return response.response(NextPK.build(PK), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody ZoneView zoneView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		zoneService.addZone(loginUser, zoneView);
		return response.response("added", "zone", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody ZoneView zoneView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		zoneService.updateZone(loginUser, zoneView);
		return response.response("updated", "zone", HttpStatus.OK);
	}

	@DeleteMapping("/{zoneNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("zoneNo") Integer zoneNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		zoneService.deleteZone(loginUser, zoneNo);
		return response.response("deleted", "zone", HttpStatus.OK);
	}

}
