package com.expertsvision.erp.core.flagmaster.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.flagmaster.dto.FlagMasterViewFilter;
import com.expertsvision.erp.core.flagmaster.entity.FlagMasterView;
import com.expertsvision.erp.core.flagmaster.service.FlagMasterService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;


@RestController
public class FlagMasterController {

	@Autowired
	private Response response;
	
	@Autowired
	private FlagMasterService flagMasterService;

	
	@GetMapping("/public/flagMaster")
	public ResponseEntity<Object> getFlagMasterViewList() {
		List<FlagMasterView> flagMasterViewList = flagMasterService.getFlagMasterViewList();
		return response.response(flagMasterViewList, HttpStatus.OK);
	}
	
	@GetMapping("/public/flagMaster/{flagCode}")
	public ResponseEntity<Object> getFlagMasterView(@PathVariable("flagCode") String flagCode) {
		FlagMasterView flagMasterViewByLangNo = flagMasterService.getFlagMasterView(flagCode);
		return response.response(flagMasterViewByLangNo, HttpStatus.OK);
	}
	
	@GetMapping("/flagMaster/page/{pageNo}")
	public ResponseEntity<Object> getFlagMastersViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<FlagMasterView> singlePage = flagMasterService.getFlagMastersViewSinglePage(loginUser, pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/flagMaster/lastPage")
	public ResponseEntity<Object> getFlagMastersViewLastPage() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SinglePage<FlagMasterView> singlePage = flagMasterService.getFlagMastersViewLastPage(loginUser);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/flagMaster/pageNo/{flagCode}")
	public ResponseEntity<Object> getMessagesViewSinglePageNo(@PathVariable("flagCode") String flagCode) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long singlePageNo = flagMasterService.getFlagMasterViewSinglePageNo(loginUser, flagCode);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}
	
	@GetMapping("/flagMaster/pages/{pageNo}")
	public ResponseEntity<Object> getFlagMastersViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<FlagMasterView> multiplePages = flagMasterService.getFlagMastersViewMultiplePages(loginUser, pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("/flagMaster/filteredPages/{pageNo}")
	public ResponseEntity<Object> getFlagMastersViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
																		@RequestBody FlagMasterViewFilter FlagMasterViewFilter) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MultiplePages<FlagMasterView> multiplePages = flagMasterService.getFlagMastersViewFilteredMultiplePages(loginUser, pageNo, FlagMasterViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
//	@PostMapping("/flagMaster")
//	public ResponseEntity<Object> addFlagMasterView(@RequestBody FlagMasterView flagMasterView) {
//		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		flagMasterService.addFlagMaster(loginUser, flagMasterView);
//		return response.response("added", "flag", HttpStatus.OK);
//	}
//	
//	@PutMapping("/flagMaster")
//	public ResponseEntity<Object> updateFlagMasterView(@RequestBody FlagMasterView flagMasterView) {
//		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		flagMasterService.updateFlagMaster(loginUser, flagMasterView);
//		return response.response("updated", "flag", HttpStatus.OK);
//	}
//	
//	@DeleteMapping("/flagMaster/{flagMaster}")
//	public ResponseEntity<Object> deleteFlagMasterView(@PathVariable("flagMaster") String flagMaster) {
//		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		flagMasterService.deleteFlagMaster(loginUser, flagMaster);
//		return response.response("deleted", "flag", HttpStatus.OK);
//	}

}
