package com.expertsvision.erp.masterdata.masterdataprivileges.service;

import java.sql.Timestamp;
import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPriv;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivFilter;

public interface MasterDataPrivilegesService {
	
	void generateMasterDataPrivileges(UsersView loginUsersView, UsersView usersView, Timestamp currentDate, boolean viewPriv, boolean addPriv);
	
	void updateMasterDataPrivileges(UsersView loginUsersView, UsersView usersView, Timestamp currentDate, boolean viewPriv, boolean addPriv);
	
	void generateUngeneratedMasterDataPrivileges(UsersView loginUser, UsersView usersView, Timestamp currentDate, boolean viewPriv, boolean addPriv);

	void generateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView ,
			Integer fromUserId, Timestamp currentDate);
	
	void updateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView ,
			UsersView fromUsersView, Timestamp currentDate);
	
	void updateGroupUsersMasterDataPrivileges(UsersView loginUser, UsersView fromUsersView, Integer groupNo,
			Timestamp currentDate);
	
	List<BranchesPrivDTO> getBranchesPrivs(UsersView loginUser, BranchesPrivFilter filter);
	
	void updateBrachesPriv(UsersView loginUser, List<BranchesPriv> branchesPrivList);
	
	
	List<AccountsPrivDTO> getAccountsPrivs(UsersView loginUser, AccountsPrivFilter filter);
	
	void updateAccountsPriv(UsersView loginUser, List<AccountsPriv> AccountsPrivList);
}
