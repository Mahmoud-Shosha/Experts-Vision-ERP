package com.expertsvision.erp.core.flagdetail.controller;

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

import com.expertsvision.erp.core.flagdetail.dto.FlagDetailViewFilter;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailMainTree;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.flagdetail.service.FlagDetailService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@RestController
@RequestMapping("/flagDetail")
public class FlagDetailController {

	@Autowired
	private Response response;

	@Autowired
	private FlagDetailService flagDetailService;
	
	@GetMapping("")
	public ResponseEntity<Object> getAllFlagDetailViewList() {
		List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
		return response.response(flagDetailViewList, HttpStatus.OK);
	}

	@GetMapping("/{flagCode}")
	public ResponseEntity<Object> getFlagDetailViewList(@PathVariable("flagCode") String flagCode) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList(loginUser, flagCode);
		return response.response(flagDetailViewList, HttpStatus.OK);
	}
	
	@GetMapping("/flagDetailMainTree")
	public ResponseEntity<Object> getFlagDetailMainTree() {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FlagDetailMainTree> FlagDetailMainTree = flagDetailService.getFlagDetailMainTree(loginUser.getUserId());
		return response.response(FlagDetailMainTree, HttpStatus.OK);
	}

	@GetMapping("/{flagCode}/{flagValue}")
	public ResponseEntity<Object> getFlagDetailView(@PathVariable("flagCode") String flagCode,
			@PathVariable("flagValue") Integer flagValue) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		FlagDetailView flagDetailViewByLangNo = flagDetailService.getFlagDetailView(loginUser, flagCode, flagValue);
		return response.response(flagDetailViewByLangNo, HttpStatus.OK);
	}

	@GetMapping("/{flagCode}/page/{pageNo}")
	public ResponseEntity<Object> getFlagDetailsViewSinglePage(@PathVariable("flagCode") String flagCode,
			@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<FlagDetailView> singlePage = flagDetailService.getFlagDetailsViewSinglePage(loginUser, flagCode,
				pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/{flagCode}/lastPage")
	public ResponseEntity<Object> getFlagDetailsViewLastPage(@PathVariable("flagCode") String flagCode) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<FlagDetailView> singlePage = flagDetailService.getFlagDetailsViewLastPage(loginUser, flagCode);
		return response.response(singlePage, HttpStatus.OK);
	}

	@GetMapping("/pageNo/{flagCode}/{flagValue}")
	public ResponseEntity<Object> getMessagesViewSinglePageNo(@PathVariable("flagCode") String flagCode,
			@PathVariable("flagValue") Integer flagValue) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = flagDetailService.getFlagDetailViewSinglePageNo(loginUser, flagCode, flagValue);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}

	@GetMapping("/{flagCode}/pages/{pageNo}")
	public ResponseEntity<Object> getFlagDetailsViewMultiplePages(@PathVariable("flagCode") String flagCode,
			@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<FlagDetailView> multiplePages = flagDetailService.getFlagDetailsViewMultiplePages(loginUser,
				flagCode, pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("/{flagCode}/filteredPages/{pageNo}")
	public ResponseEntity<Object> getFlagDetailsViewFilteredMultiplePages(@PathVariable("flagCode") String flagCode,
			@PathVariable("pageNo") Long pageNo, @RequestBody FlagDetailViewFilter FlagDetailViewFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<FlagDetailView> multiplePages = flagDetailService
				.getFlagDetailsViewFilteredMultiplePages(loginUser, flagCode, pageNo, FlagDetailViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Object> addFlagDetailView(@RequestBody FlagDetailView flagDetailView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		flagDetailService.addFlagDetail(loginUser, flagDetailView);
		return response.response("added", "flag_detail", HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateFlagDetailView(@RequestBody FlagDetailView flagDetailView) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		flagDetailService.updateFlagDetail(loginUser, flagDetailView);
		return response.response("updated", "flag_detail", HttpStatus.OK);
	}

	@DeleteMapping("/{flagCode}/{flagValue}")
	public ResponseEntity<Object> deleteFlagDetailView(@PathVariable("flagCode") String flagCode,
			@PathVariable("flagValue") Integer flagValue) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		flagDetailService.deleteFlagDetail(loginUser, flagCode, flagValue);
		return response.response("deleted", "flag_detail", HttpStatus.OK);
	}

}
