package com.expertsvision.erp.masterdata.currency.controller;

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
import com.expertsvision.erp.masterdata.currency.dto.CurrencyViewFilter;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyView;
import com.expertsvision.erp.masterdata.currency.service.CurrencyService;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {

	@Autowired
	private Response response;

	@Autowired
	private CurrencyService currencyService;

	@GetMapping("")
	public ResponseEntity<Object> getCurrencyViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CurrencyView> currencyViewList = currencyService.getCurrencyViewList(loginUser);
		return response.response(currencyViewList, HttpStatus.OK);
	}

	@GetMapping("/{currencyCode}")
	public ResponseEntity<Object> getCurrencyView(@PathVariable("currencyCode") String currencyCode) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CurrencyView currencyView = currencyService.getCurrencyView(loginUser, currencyCode);
		return response.response(currencyView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{currencyCode}")
	public ResponseEntity<Object> getCurrencyViewSinglePageNo(@PathVariable("currencyCode") String currencyCode) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = currencyService.getCurrencyViewSinglePageNo(loginUser, currencyCode);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CurrencyView> singlePage = currencyService.getCurrencyViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCurrencyViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CurrencyView> singlePage = currencyService.getCurrencyViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCurrencyViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CurrencyView> multiplePages = currencyService.getCurrencyViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCurrencyViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CurrencyViewFilter currencyViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CurrencyView> multiplePages = currencyService
				.getCurrencyViewFilteredMultiplePages(loginUser, pageNo, currencyViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

//	@GetMapping("/pages/currencyValues/{currencyCode}/{pageNo}")
//	public ResponseEntity<Object> getCurrencyValuesViewMultiplePages(@PathVariable("pageNo") Long pageNo,
//															   @PathVariable("currencyCode") String currencyCode) {
//		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		MultiplePages<CurrencyValuesView> multiplePages = currencyService.getCurrencyValuesViewMultiplePages(loginUser,
//				currencyCode, pageNo);
//		return response.response(multiplePages, HttpStatus.OK);
//	}
//	
//	@GetMapping("/pages/currencyHistory/{currencyCode}/{pageNo}")
//	public ResponseEntity<Object> getCurrencyHistoryViewMultiplePages(@PathVariable("pageNo") Long pageNo,
//															   @PathVariable("currencyCode") String currencyCode) {
//		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		MultiplePages<CurrencyHistoryView> multiplePages = currencyService.getCurrencyHistoryViewMultiplePages(loginUser,
//				currencyCode, pageNo);
//		return response.response(multiplePages, HttpStatus.OK);
//	}
	

	@PostMapping("")
	public ResponseEntity<Object> addUsersGroup(@RequestBody CurrencyView currencyView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currencyService.addCurrency(loginUser, currencyView);
		return response.response("added", "currency", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody CurrencyView currencyView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currencyService.updateCurrency(loginUser, currencyView);
		return response.response("updated", "currency", HttpStatus.OK);
	}

	@DeleteMapping("/{currencyNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("currencyNo") String currencyCode) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		currencyService.deleteCurrency(loginUser, currencyCode);
		return response.response("deleted", "currency", HttpStatus.OK);
	}
	
	@GetMapping("/preAdd")
	public ResponseEntity<Object> preAdd() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = currencyService.preAdd(loginUser);
		return response.response(preData, HttpStatus.OK);
	}
	
	@GetMapping("/preModify")
	public ResponseEntity<Object> preModify() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = currencyService.preModify(loginUser);
		return response.response(preData, HttpStatus.OK);
	}

}
