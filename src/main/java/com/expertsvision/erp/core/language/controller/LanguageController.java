package com.expertsvision.erp.core.language.controller;

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

import com.expertsvision.erp.core.language.dto.LanguageViewFilter;
import com.expertsvision.erp.core.language.entity.LanguageView;
import com.expertsvision.erp.core.language.service.LanguageService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.NextPK;
import com.expertsvision.erp.core.utils.SinglePage;


@RestController
@RequestMapping(value = "/public/language")
public class LanguageController {

	@Autowired
	private Response response;
	
	@Autowired
	private LanguageService languageService;

	
	@GetMapping("")
	public ResponseEntity<Object> getLanguageViewList() {
		List<LanguageView> languageViewList = languageService.getLanguageViewList();
		return response.response(languageViewList, HttpStatus.OK);
	}
	
	@GetMapping("/{langNo}")
	public ResponseEntity<Object> getLanguageView(@PathVariable("langNo") Integer langNo) {
		LanguageView languageViewByLangNo = languageService.getLanguageView(langNo);
		return response.response(languageViewByLangNo, HttpStatus.OK);
	}
	
	@GetMapping("/page/{pageNo}")
	public ResponseEntity<Object> getLanguagesViewSinglePage(@PathVariable("pageNo") Long pageNo) {
		SinglePage<LanguageView> singlePage = languageService.getLanguagesViewSinglePage(pageNo);
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("/lastPage")
	public ResponseEntity<Object> getLanguagesViewLastPage() {
		SinglePage<LanguageView> singlePage = languageService.getLanguagesViewLastPage();
		return response.response(singlePage, HttpStatus.OK);
	}
	
	@GetMapping("pageNo/{langNo}")
	public ResponseEntity<Object> getLanguagesViewSinglePageNo(@PathVariable("langNo") Integer langNo) {
		long singlePageNo = languageService.getLanguageViewSinglePageNo(langNo);
		Map<String, Long> singlePageNoMap = new HashMap<>();
		singlePageNoMap.put("page_no", singlePageNo);
		return response.response(singlePageNoMap, HttpStatus.OK);
	}
	
	@GetMapping("/pages/{pageNo}")
	public ResponseEntity<Object> getLanguagesViewMultiplePages(@PathVariable("pageNo") Long pageNo) {
		MultiplePages<LanguageView> multiplePages = languageService.getLanguagesViewMultiplePages(pageNo);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@PostMapping("/filteredPages/{pageNo}")
	public ResponseEntity<Object> getLanguagesViewFilteredMultiplePages(@PathVariable("pageNo") Long pageNo,
																		@RequestBody LanguageViewFilter LanguageViewFilter) {
		MultiplePages<LanguageView> multiplePages = languageService.getLanguagesViewFilteredMultiplePages(pageNo, LanguageViewFilter);
		return response.response(multiplePages, HttpStatus.OK);
	}
	
	@GetMapping("/nextPK")
	public ResponseEntity<Object> getNextPK() {
		Object PK = languageService.getNextPK();
		return response.response(NextPK.build(PK), HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<Object> addLanguageView(@RequestBody LanguageView languageView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		languageService.addLanguage(loginUser, languageView);
		return response.response("added", "language", HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity<Object> updateLanguageView(@RequestBody LanguageView languageView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		languageService.updateLanguage(loginUser, languageView);
		return response.response("updated", "language", HttpStatus.OK);
	}
	
	@DeleteMapping("/{langNo}")
	public ResponseEntity<Object> deleteLanguageView(@PathVariable("langNo")Integer langNo) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		languageService.deleteLanguage(loginUser, langNo);
		return response.response("deleted", "language", HttpStatus.OK);
	}

}
