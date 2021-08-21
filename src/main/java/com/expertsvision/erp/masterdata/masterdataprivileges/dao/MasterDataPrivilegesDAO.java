package com.expertsvision.erp.masterdata.masterdataprivileges.dao;

import java.util.List;


public interface MasterDataPrivilegesDAO {
	
	void addMasterDataPrivileges(Object masterDataPrivileges);
	
	void updateBulkMasterDataPrivileges(String str);
	
	List<Integer> getBranchesPK();

	List<Integer> getBranchesPKFromPrivsTable(Integer userId);
	
	List<Object[]> getBranchesPrivs(Integer userId);
		
}
