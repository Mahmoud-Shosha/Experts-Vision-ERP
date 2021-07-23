package com.expertsvision.erp.core.flagpriv.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.flagpriv.entity.FlagPrivView;
import com.expertsvision.erp.core.flagpriv.service.FlagPrivService;
import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;

@RestController
@RequestMapping("/flagPriv")
public class FlagPrivController {

	@Autowired
	private Response response;

	@Autowired
	private FlagPrivService flagPrivService;

	@GetMapping("/{userId}")
	public ResponseEntity<Object> getFlagPrivViewList(@PathVariable("userId") Integer userId) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<FlagPrivView> flagPrivList = flagPrivService.getFlagPrivViewList(loginUser, userId);
		return response.response(flagPrivList, HttpStatus.OK);
	}

	@PutMapping("")
	public ResponseEntity<Object> updateFlagPriv(@RequestBody FlagPrivView flagPrivView) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		flagPrivService.updateFlagPriv(loginUser, flagPrivView);
		return response.response("updated", "privilege", HttpStatus.OK);
	}

}
