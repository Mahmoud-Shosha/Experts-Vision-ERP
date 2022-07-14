package com.expertsvision.erp.masterdata.cash.controller;

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
import com.expertsvision.erp.masterdata.cash.dto.CashInHandViewFilter;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandView;
import com.expertsvision.erp.masterdata.cash.service.CashService;

@RestController
@RequestMapping(value = "/cash")
public class CashController {

	@Autowired
	private Response response;

	@Autowired
	private CashService cashService;

	@GetMapping("")
	public ResponseEntity<Object> getCashInHandViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CashInHandView> cashInHandViewList = cashService.getCashInHandViewList(loginUser);
		return response.response(cashInHandViewList, HttpStatus.OK);
	}

	@GetMapping("/{cashNo}")
	public ResponseEntity<Object> getCashInHandView(@PathVariable("cashNo") Integer cashNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		CashInHandView cashInHandView = cashService.getCashInHandView(loginUser, cashNo);
		return response.response(cashInHandView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{cashNo}")
	public ResponseEntity<Object> getCashInHandViewSinglePageNo(@PathVariable("cashNo") Integer cashNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = cashService.getCashInHandViewSinglePageNo(loginUser, cashNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getCashInHandVViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CashInHandView> singlePage = cashService.getCashInHandViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getCashInHandViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<CashInHandView> singlePage = cashService.getCashInHandViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getCashInHandViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CashInHandView> multiplePages = cashService.getCashInHandViewMultiplePages(loginUser, pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getCashInHandViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody CashInHandViewFilter cashInHandViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<CashInHandView> multiplePages = cashService.getCashInHandViewFilteredMultiplePages(loginUser, pageNo,
				cashInHandViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@GetMapping("/preAdd")
	public ResponseEntity<Object> preAdd() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = cashService.preAdd(loginUser);
		return response.response(preData, HttpStatus.OK);
	}

	@PostMapping(path = "")
	public ResponseEntity<Object> addCashInHandView(@RequestBody CashInHandView cashInHandView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cashService.addCashInHand(loginUser, cashInHandView);
		return response.response("added", "cash_on_hand", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateCashInHandView(@RequestBody CashInHandView cashInHandView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cashService.updateCashInHand(loginUser, cashInHandView);
		return response.response("updated", "cash_on_hand", HttpStatus.OK);
	}

	@DeleteMapping("/{cashNo}")
	public ResponseEntity<Object> deleteCashInHandView(@PathVariable("cashNo") Integer cashNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		cashService.deleteCashInHand(loginUser, cashNo);
		return response.response("deleted", "cash_on_hand", HttpStatus.OK);
	}

}
