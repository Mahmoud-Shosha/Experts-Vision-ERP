package com.expertsvision.erp.systemcommands.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.systemcommands.server.service.ServerService;


@RestController
@RequestMapping(value = "/systemcommands/server")
public class ServerController {

	@Autowired
	private Response response;
	
	@Autowired
	private ServerService serverService;

	
	@PostMapping("/reloadServerCache")
	public ResponseEntity<Object> reloadServerCache() {
		UsersView loginUser = (UsersView)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		serverService.reloadServerCache(loginUser);
		return response.response("server_reloaded", HttpStatus.OK);
	}
	
}
