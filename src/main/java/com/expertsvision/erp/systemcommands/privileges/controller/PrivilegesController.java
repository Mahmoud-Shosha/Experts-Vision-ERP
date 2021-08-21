package com.expertsvision.erp.systemcommands.privileges.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.systemcommands.privileges.service.PrivilegesService;


@RestController
@RequestMapping(value = "/systemcommands/privileges")
public class PrivilegesController {

	@Autowired
	private Response response;
	
	@Autowired
	private PrivilegesService privilegesService;

	
	@PostMapping("/generateUngeneratedPrivileges//{prv}")
	public ResponseEntity<Object> generateUngeneratedPrivileges(@PathVariable("prv") boolean prv) {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		privilegesService.generateUngeneratedPrivileges(loginUser, prv);
		return response.response("generate_ungenerated_priv", HttpStatus.OK);
	}
	
}
