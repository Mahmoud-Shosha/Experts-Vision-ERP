package com.expertsvision.erp.masterdata.country.controller;

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
import com.expertsvision.erp.masterdata.country.dto.CountryViewFilter;
import com.expertsvision.erp.masterdata.country.entity.CountryView;
import com.expertsvision.erp.masterdata.country.service.CountryService;

@RestController
@RequestMapping(value = "/country")
public class CountryController {

	@Autowired
	private Response response;

	@Autowired
	private CountryService countryService;

	@GetMapping("")
	public ResponseEntity<Object> getCountryViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CountryView> countryViewList = countryService.getCountryViewList(loginUser);
		return response.response(countryViewList, HttpStatus.OK);
	}

	@GetMapping("/{countryNo}")
	public ResponseEntity<Object> getCountryView(@PathVariable("countryNo") Integer countryNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CountryView countryView = countryService.getCountryView(loginUser, countryNo);
		return response.response(countryView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{countryNo}")
	public ResponseEntity<Object> getCountryViewSinglePageNo(@PathVariable("countryNo") Integer countryNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = countryService.getCountryViewSinglePageNo(loginUser, countryNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CountryView> singlePage = countryService.getCountryViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCountryViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CountryView> singlePage = countryService.getCountryViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCountryViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CountryView> multiplePages = countryService.getCountryViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCountryViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CountryViewFilter countryViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CountryView> multiplePages = countryService
				.getCountryViewFilteredMultiplePages(loginUser, pageNo, countryViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@GetMapping("/nextPK")
	public ResponseEntity<Object> getNextPK() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object PK = countryService.getNextPK(loginUser);
		return response.response(NextPK.build(PK), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody CountryView countryView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		countryService.addCountry(loginUser, countryView);
		return response.response("added", "country", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody CountryView countryView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		countryService.updateCountry(loginUser, countryView);
		return response.response("updated", "country", HttpStatus.OK);
	}

	@DeleteMapping("/{countryNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("countryNo") Integer countryNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		countryService.deleteCountry(loginUser, countryNo);
		return response.response("deleted", "country", HttpStatus.OK);
	}

}
