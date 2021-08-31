package com.expertsvision.erp.masterdata.city.controller;

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
import com.expertsvision.erp.masterdata.city.dto.CityViewFilter;
import com.expertsvision.erp.masterdata.city.entity.CityView;
import com.expertsvision.erp.masterdata.city.service.CityService;

@RestController
@RequestMapping(value = "/city")
public class CityController {

	@Autowired
	private Response response;

	@Autowired
	private CityService cityService;

	@GetMapping("")
	public ResponseEntity<Object> getCityViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CityView> cityViewList = cityService.getCityViewList(loginUser);
		return response.response(cityViewList, HttpStatus.OK);
	}

	@GetMapping("/{cityNo}")
	public ResponseEntity<Object> getCityView(@PathVariable("cityNo") Integer cityNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CityView cityView = cityService.getCityView(loginUser, cityNo);
		return response.response(cityView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{cityNo}")
	public ResponseEntity<Object> getCityViewSinglePageNo(@PathVariable("cityNo") Integer cityNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = cityService.getCityViewSinglePageNo(loginUser, cityNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CityView> singlePage = cityService.getCityViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCityViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CityView> singlePage = cityService.getCityViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCityViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CityView> multiplePages = cityService.getCityViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCityViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CityViewFilter cityViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CityView> multiplePages = cityService
				.getCityViewFilteredMultiplePages(loginUser, pageNo, cityViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@GetMapping("/nextPK")
	public ResponseEntity<Object> getNextPK() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object PK = cityService.getNextPK(loginUser);
		return response.response(NextPK.build(PK), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody CityView cityView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cityService.addCity(loginUser, cityView);
		return response.response("added", "city", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody CityView cityView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cityService.updateCity(loginUser, cityView);
		return response.response("updated", "city", HttpStatus.OK);
	}

	@DeleteMapping("/{cityNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("cityNo") Integer cityNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cityService.deleteCity(loginUser, cityNo);
		return response.response("deleted", "city", HttpStatus.OK);
	}

}
