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
import com.expertsvision.erp.masterdata.banks.entity.BanksPriv;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandPriv;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPriv;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterPriv;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BanksPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BanksPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.CashesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.CashesPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.CostCenterPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.CostCenterPrivFilter;
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
		List<BranchesPrivDTO> branchesPrivDTOList = masterDataPrivilegesService.getBranchesPrivs(loginUser,
				branchesPrivFilter);
		return response.response(branchesPrivDTOList, HttpStatus.OK);
	}

	@PutMapping("/branches")
	public ResponseEntity<Object> updateBrachesPriv(@RequestBody List<BranchesPriv> branchesPrivList) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		masterDataPrivilegesService.updateBrachesPriv(loginUser, branchesPrivList);
		return response.response("updated", "branches_prvs", HttpStatus.OK);
	}

	@PostMapping("/accounts")
	public ResponseEntity<Object> getAccountsPrivs(@RequestBody AccountsPrivFilter accountsPrivFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<AccountsPrivDTO> accountsPrivDTOList = masterDataPrivilegesService.getAccountsPrivs(loginUser,
				accountsPrivFilter);
		return response.response(accountsPrivDTOList, HttpStatus.OK);
	}

	@PutMapping("/accounts")
	public ResponseEntity<Object> updateAccountsPriv(@RequestBody List<AccountsPriv> accountsPrivList) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		masterDataPrivilegesService.updateAccountsPriv(loginUser, accountsPrivList);
		return response.response("updated", "accounts_prvs", HttpStatus.OK);
	}

	@PostMapping("/costcenters")
	public ResponseEntity<Object> getCostCenterPrivs(@RequestBody CostCenterPrivFilter costCenterPrivFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CostCenterPrivDTO> costCenterPrivDTOList = masterDataPrivilegesService.getCostCenterPrivs(loginUser,
				costCenterPrivFilter);
		return response.response(costCenterPrivDTOList, HttpStatus.OK);
	}

	@PutMapping("/costcenters")
	public ResponseEntity<Object> updateCostCenterPriv(@RequestBody List<CostCenterPriv> costCenterPrivList) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		masterDataPrivilegesService.updateCostCenterPriv(loginUser, costCenterPrivList);
		return response.response("updated", "cost_ceneter_prvs", HttpStatus.OK);
	}

	@PostMapping("/banks")
	public ResponseEntity<Object> getBankPrivs(@RequestBody BanksPrivFilter banksPrivFilter) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<BanksPrivDTO> banksPrivDTOList = masterDataPrivilegesService.getBanksPrivs(loginUser, banksPrivFilter);
		return response.response(banksPrivDTOList, HttpStatus.OK);
	}

	@PutMapping("/banks")
	public ResponseEntity<Object> updateBankPriv(@RequestBody List<BanksPriv> banksPrivList) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		masterDataPrivilegesService.updateBanksPriv(loginUser, banksPrivList);
		return response.response("updated", "banks_prvs", HttpStatus.OK);
	}

	@PostMapping("/cashes")
	public ResponseEntity<Object> getCostCenterPrivs(@RequestBody CashesPrivFilter cashesPrivFilters) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<CashesPrivDTO> cashesPrivDTOList = masterDataPrivilegesService.getCashesPrivs(loginUser,
				cashesPrivFilters);
		return response.response(cashesPrivDTOList, HttpStatus.OK);
	}

	@PutMapping("/cashes")
	public ResponseEntity<Object> updateCashesPriv(@RequestBody List<CashInHandPriv> cashInHandPrivList) {
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		masterDataPrivilegesService.updateCashesPriv(loginUser, cashInHandPrivList);
		return response.response("updated", "cashes_prvs", HttpStatus.OK);
	}

}
