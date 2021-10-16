package com.expertsvision.erp.masterdata.chartofaccounts.controller;

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
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.chartofaccounts.dto.ChartOfAccountsViewFilter;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.ChartOfAccountsView;
import com.expertsvision.erp.masterdata.chartofaccounts.service.ChartofaccountsService;

@RestController
@RequestMapping(value = "/chartofaccounts")
public class ChartofaccountsController {

	@Autowired
	private Response response;

	@Autowired
	private ChartofaccountsService chartofaccountsService;

	@GetMapping("")
	public ResponseEntity<Object> getChartOfAccountsViewList() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<ChartOfAccountsView> chartOfAccountsViewList = chartofaccountsService.getChartOfAccountsViewList(loginUser);
		return response.response(chartOfAccountsViewList, HttpStatus.OK);
	}

	@GetMapping("/{accNo}")
	public ResponseEntity<Object> getChartOfAccountsView(@PathVariable("accNo") Integer accNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ChartOfAccountsView chartOfAccountsView = chartofaccountsService.getChartOfAccountsView(loginUser, accNo);
		return response.response(chartOfAccountsView, HttpStatus.OK);
	}

	@GetMapping("pageNo/{accNo}")
	public ResponseEntity<Object> getChartOfAccountsViewSinglePageNo(@PathVariable("accNo") Integer accNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = chartofaccountsService.getChartOfAccountsViewSinglePageNo(loginUser, accNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getChartOfAccountsViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ChartOfAccountsView> singlePage = chartofaccountsService.getChartOfAccountsViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/lastPage")
	public ResponseEntity<Object> getChartOfAccountsViewLastPage() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<ChartOfAccountsView> singlePage = chartofaccountsService.getChartOfAccountsViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getChartOfAccountsViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ChartOfAccountsView> multiplePages = chartofaccountsService.getChartOfAccountsViewMultiplePages(loginUser,
				pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getChartOfAccountsViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
			@RequestBody ChartOfAccountsViewFilter chartOfAccountsViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<ChartOfAccountsView> multiplePages = chartofaccountsService
				.getChartOfAccountsViewFilteredMultiplePages(loginUser, pageNo, chartOfAccountsViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@GetMapping("/nextPK/{parentAcc}")
	public ResponseEntity<Object> getNextPK(@PathVariable("parentAcc") Integer parentAcc) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Object PK = chartofaccountsService.getNextPK(loginUser, parentAcc);
		return response.response(NextPK.build(PK), HttpStatus.OK);
	}
	
	@PostMapping(path = "")
	public ResponseEntity<Object> addUsersGroup(@RequestBody ChartOfAccountsView chartOfAccountsView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		chartofaccountsService.addChartOfAccount(loginUser, chartOfAccountsView);
		return response.response("added", "account", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateUsersGroup(@RequestBody ChartOfAccountsView chartOfAccountsView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		chartofaccountsService.updateChartOfAccount(loginUser, chartOfAccountsView);
		return response.response("updated", "account", HttpStatus.OK);
	}

	@DeleteMapping("/{accNo}")
	public ResponseEntity<Object> deleteUsersGroup(@PathVariable("accNo") Integer accNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		chartofaccountsService.deleteChartOfAccount(loginUser, accNo);
		return response.response("deleted", "account", HttpStatus.OK);
	}
	
	@GetMapping("/preAdd")
	public ResponseEntity<Object> preAdd() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = chartofaccountsService.preAdd(loginUser);
		return response.response(preData, HttpStatus.OK);
	}
	
	@GetMapping("/preModify")
	public ResponseEntity<Object> preModify() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		PreData preData = chartofaccountsService.preModify(loginUser);
		return response.response(preData, HttpStatus.OK);
	}

}
