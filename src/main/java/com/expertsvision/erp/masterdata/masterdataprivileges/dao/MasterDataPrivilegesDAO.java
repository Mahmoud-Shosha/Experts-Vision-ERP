package com.expertsvision.erp.masterdata.masterdataprivileges.dao;

import java.util.List;
import java.util.Set;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.masterdata.banks.dto.BankVirtualPK;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyPK;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BanksPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BanksPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.CostCenterPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.CostCenterPrivFilter;


public interface MasterDataPrivilegesDAO {
	
	void addMasterDataPrivileges(Object masterDataPrivileges);
	
	void updateBulkMasterDataPrivileges(String str);
	
	List<Integer> getBranchesPK();

	List<Integer> getBranchesPKFromPrivsTable(Integer userId);
	
	List<Object[]> getBranchesPrivs(Integer userId);
	
	List<Object[]> getBranchesPrivs(Set<Integer> userIdList, Set<Integer> branchNoList);
	
	List<BranchesPrivDTO> getBranchesPrivs(UsersView loginUser, BranchesPrivFilter filter);
	
	
	List<AccountsCurrencyPK> getAccountsCurrencyPK();
	
	List<AccountsCurrencyPK> getAccountsCurrencyPKFromPrivsTable(Integer userId);
	
	List<Object[]> getAccountsPrivs(Integer userId);
	
	List<Object[]> getAccountsPrivs(Set<Integer> userIdList, Set<Integer> accNoList, Set<String> curCodeList);
	
	List<AccountsPrivDTO> getAccountsPrivs(UsersView loginUser, AccountsPrivFilter filter);
	

	List<Integer> getCostCenterPK();
	
	List<Integer> getCostCenterPKFromPrivsTable(Integer userId);
	
	List<Object[]> getCostCentersPrivs(Integer userId);
	
	List<Object[]> getCostCentersPrivs(Set<Integer> userIdList, Set<Integer> ccNoList);
	
	List<CostCenterPrivDTO> getCostCentersPrivs(UsersView loginUser, CostCenterPrivFilter filter);
	
	
	List<BankVirtualPK> getBanksVirtualPK();

	List<BankVirtualPK> getBanksVirtualPKFromPrivsTable(Integer userId);

	List<Object[]> getBanksPrivs(Integer userId);

	List<Object[]> getBanksPrivs(Set<Integer> userIdList, Set<Integer> bankNoList);

	List<BanksPrivDTO> getBanksPrivs(UsersView loginUser, BanksPrivFilter filter);
		
}
