package com.expertsvision.erp.masterdata.masterdataprivileges.dao;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivFilter;


public interface MasterDataPrivilegesDAO {
	
	void addMasterDataPrivileges(Object masterDataPrivileges);
	
	void updateBulkMasterDataPrivileges(String str);
	
	List<Integer> getBranchesPK();

	List<Integer> getBranchesPKFromPrivsTable(Integer userId);
	
	List<Object[]> getBranchesPrivs(Integer userId);
	
	List<BranchesPrivDTO> getBranchesPrivs(UsersView loginUser, BranchesPrivFilter filter);
		
}
