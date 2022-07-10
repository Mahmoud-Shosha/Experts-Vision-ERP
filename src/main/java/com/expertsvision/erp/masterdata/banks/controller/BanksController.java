package com.expertsvision.erp.masterdata.banks.controller;

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
import com.expertsvision.erp.masterdata.banks.service.BankService;
import com.expertsvision.erp.masterdata.banks.dto.BanksViewFilter;
import com.expertsvision.erp.masterdata.banks.entity.BanksView;

@RestController
@RequestMapping(value = "/banks")
public class BanksController {

	@Autowired
	private Response response;

	@Autowired
	private BankService bankService;

	@GetMapping("")
	public ResponseEntity<Object> getBanksViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<BanksView> banksViewList = bankService.getBanksViewList(loginUser);
		return response.response(banksViewList, HttpStatus.OK);
	}

	@GetMapping("/{bankNo}")
	public ResponseEntity<Object> getBanksView(@PathVariable("bankNo") Integer bankNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		BanksView banksView = bankService.getBanksView(loginUser, bankNo);
		return response.response(banksView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{bankNo}")
	public ResponseEntity<Object> getBanksViewSinglePageNo(@PathVariable("bankNo") Integer bankNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = bankService.getBanksViewSinglePageNo(loginUser, bankNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getMessagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<BanksView> singlePage = bankService.getBanksViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getBanksViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<BanksView> singlePage = bankService.getBanksViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getBanksViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<BanksView> multiplePages = bankService.getBanksViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getBanksViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody BanksViewFilter banksViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<BanksView> multiplePages = bankService
				.getBanksViewFilteredMultiplePages(loginUser, pageNo, banksViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@GetMapping("/preAdd")
	public ResponseEntity<Object> preAdd() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = bankService.preAdd(loginUser);
		return response.response(preData, HttpStatus.OK);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<Object> addBanksView(@RequestBody BanksView banksView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		bankService.addBank(loginUser, banksView);
		return response.response("added", "bank", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateBanksView(@RequestBody BanksView banksView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		bankService.updateBank(loginUser, banksView);
		return response.response("updated", "bank", HttpStatus.OK);
	}

	@DeleteMapping("/{bankNo}")
	public ResponseEntity<Object> deleteBanksView(@PathVariable("bankNo") Integer bankNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		bankService.deleteBank(loginUser, bankNo);
		return response.response("deleted", "bank", HttpStatus.OK);
	}

}
