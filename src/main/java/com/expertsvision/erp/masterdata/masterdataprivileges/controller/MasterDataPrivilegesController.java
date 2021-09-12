package com.expertsvision.erp.masterdata.masterdataprivileges.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expertsvision.erp.core.response.Response;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.service.MasterDataPrivilegesService;

@RestController
@RequestMapping(value = "/masterdataprivileges")
public class MasterDataPrivilegesController {

	@Autowired
	private Response response;

	@Autowired
	private MasterDataPrivilegesService masterDataPrivilegesService;

	@PostMapping("/branches")
	public ResponseEntity<Object> getBranchesPriv(@RequestBody BranchesPrivFilter branchesPrivFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<BranchesPrivDTO> branchesPrivDTOList = masterDataPrivilegesService.getBranchesPrivs(loginUser, branchesPrivFilter);
		return response.response(branchesPrivDTOList, HttpStatus.OK);
	}
	
	@PutMapping("/branches")
	public ResponseEntity<Object> updateBrachesPriv(@RequestBody List<BranchesPriv> branchesPrivList) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		masterDataPrivilegesService.updateBrachesPriv(loginUser, branchesPrivList);
		return response.response("updated", "branches_prvs", HttpStatus.OK);
	}

}
