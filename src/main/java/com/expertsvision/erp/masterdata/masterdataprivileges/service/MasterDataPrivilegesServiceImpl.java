package com.expertsvision.erp.masterdata.masterdataprivileges.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.module.service.InMemoryModulesService;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.user.service.UsersService;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.masterdataprivileges.dao.MasterDataPrivilegesDAO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivFilter;

@Service
public class MasterDataPrivilegesServiceImpl implements MasterDataPrivilegesService {

	@Autowired
	private MasterDataPrivilegesDAO masterDataPrivilegesDAO;
	
	@Autowired
	private UsersService usersService;

	@Autowired
	@Lazy
	private InMemoryModulesService inMemoryModulesService;

	@Autowired
	@Lazy
	private InMemoryUsersGroupsService inMemoryUsersGroupsService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;
	
	@Autowired
	private CoreValidationService coreValidationService;

	// VERY IMPORTANT NOTE !!!
	// when adding new one, add it in both generateMasterDataPrivileges and
	// generateUngeneratedMasterDataPrivileges and
	// generateMasterDataPrivilegesFromAnotherUser

	@Override
	public void generateMasterDataPrivileges(UsersView loginUser, UsersView usersView, Timestamp currentDate,
			boolean viewPriv, boolean addPriv) {
		if(usersView.getAdminUser() || usersView.getSuperAdmin()) return;
		// PREPARE VARAIBLES
		if (usersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
			viewPriv = true;
			addPriv = true;
		}
		List<Object> entityPrvsList = new ArrayList<>();
		// "Branches" is in module no 1
		if (inMemoryModulesService.getModulesView(1).getActive()) {
			// LOOP OVER ALL BRANCHES
			for (Integer PK : masterDataPrivilegesDAO.getBranchesPK()) {
				BranchesPriv branchesPriv = new BranchesPriv();
				branchesPriv.setAddDate(currentDate);
				branchesPriv.setAddPriv(addPriv);
				branchesPriv.setAddUser(loginUser.getUserId());
				branchesPriv.setBranchNo(PK);
				branchesPriv.setModifyDate(null);
				branchesPriv.setModifyUser(null);
				branchesPriv.setUserId(usersView.getUserId());
				branchesPriv.setViewPriv(viewPriv);
				entityPrvsList.add(branchesPriv);
			}
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (Object object : entityPrvsList) {
			masterDataPrivilegesDAO.addMasterDataPrivileges(object);
		}
	}
	
	@Override
	public void updateMasterDataPrivileges(UsersView loginUser, UsersView usersView, Timestamp currentDate,
			boolean viewPriv, boolean addPriv) {
		if(usersView.getAdminUser() || usersView.getSuperAdmin()) return;
		// PREPARE VARAIBLES
		StringBuilder sql;
		if (usersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
			viewPriv = true;
			addPriv = true;
		}
		List<String> entityPrvsList = new ArrayList<>();
		// "Branches" is in module no 1
		if (inMemoryModulesService.getModulesView(1).getActive()) {
			// LOOP OVER ALL BRANCHES
			sql = new StringBuilder();
			for (Integer PK : masterDataPrivilegesDAO.getBranchesPK()) {
				sql.append("UPDATE branches_priv SET ");
				sql.append("add_priv=");
				sql.append(addPriv);
				sql.append(", view_priv=");
				sql.append(viewPriv);
				sql.append(", modify_user=");
				sql.append(loginUser.getUserId());
				sql.append(", modify_date='");
				sql.append(currentDate);
				sql.append("' WHERE user_id=");
				sql.append(usersView.getUserId());
				sql.append(" AND branch_no=");
				sql.append(PK);
				sql.append(";");
			}
			entityPrvsList.add(sql.toString());
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (String str : entityPrvsList) {
			masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(str);
		}
	}

	@Override
	public void generateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView,
			Integer fromUserId, Timestamp currentDate) {
		if(usersView.getAdminUser() || usersView.getSuperAdmin()) return;
		// PREPARE VARAIBLES
		UsersView fromUsersView = inMemoryUsersService.getUsersView(fromUserId);
		if ((fromUsersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(fromUsersView.getGroupNo()).getAdminGroup())
				|| (usersView.getGroupNo() != null
						&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup())
				|| fromUsersView.getAdminUser() || fromUsersView.getSuperAdmin()) {
			generateMasterDataPrivileges(loginUser, usersView, currentDate, true, true);
		} else {
			List<Object> entityPrvsList = new ArrayList<>();
			// "Branches" is in module no 1
			if (inMemoryModulesService.getModulesView(1).getActive()) {
				// LOOP OVER ALL BRANCHES
				for (Object[] obj : masterDataPrivilegesDAO.getBranchesPrivs(fromUserId)) {
					BranchesPriv branchesPriv = new BranchesPriv();
					branchesPriv.setAddDate(currentDate);
					branchesPriv.setAddPriv((Boolean) obj[1]);
					branchesPriv.setAddUser(loginUser.getUserId());
					branchesPriv.setBranchNo((Integer) obj[0]);
					branchesPriv.setModifyDate(null);
					branchesPriv.setModifyUser(null);
					branchesPriv.setUserId(usersView.getUserId());
					branchesPriv.setViewPriv((Boolean) obj[2]);
					entityPrvsList.add(branchesPriv);
				}
			}
			// LOOP OVER PRIVS TO SAVE THEM
			for (Object object : entityPrvsList) {
				masterDataPrivilegesDAO.addMasterDataPrivileges(object);
			}
		}
	}

	@Override
	public void updateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView, UsersView fromUsersView,
			Timestamp currentDate) {
		if(usersView.getAdminUser() || usersView.getSuperAdmin()) return;
		// PREPARE VARAIBLES
		StringBuilder sql;
		if ((fromUsersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(fromUsersView.getGroupNo()).getAdminGroup())
				|| (usersView.getGroupNo() != null
						&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup())
				|| fromUsersView.getAdminUser() || fromUsersView.getSuperAdmin()) {
			updateMasterDataPrivileges(loginUser, usersView, currentDate, true, true);
		} else {
			List<String> entityPrvsList = new ArrayList<>();
			// "Branches" is in module no 1
			if (inMemoryModulesService.getModulesView(1).getActive()) {
				// LOOP OVER ALL BRANCHES
				sql = new StringBuilder();
				for (Object[] obj : masterDataPrivilegesDAO.getBranchesPrivs(fromUsersView.getUserId())) {
					sql.append("UPDATE branches_priv SET ");
					sql.append("add_priv=");
					sql.append((Boolean) obj[1]);
					sql.append(", view_priv=");
					sql.append((Boolean) obj[2]);
					sql.append(", modify_user=");
					sql.append(loginUser.getUserId());
					sql.append(", modify_date='");
					sql.append(currentDate);
					sql.append("' WHERE user_id=");
					sql.append(usersView.getUserId());
					sql.append(" AND branch_no=");
					sql.append((Integer) obj[0]);
					sql.append(";");
				}
				entityPrvsList.add(sql.toString());
			}
			// LOOP OVER PRIVS TO SAVE THEM
			for (String str : entityPrvsList) {
				masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(str);
			}
		}
	}
	
	@Override
	public void updateGroupUsersMasterDataPrivileges(UsersView loginUser, UsersView fromUsersView, Integer groupNo,
			Timestamp currentDate) {
		List<User> usersList = usersService.getUsersListByGroupNo(groupNo);
		UsersView usersView;
		if (usersList.isEmpty())
			return;
		for (User user : usersList) {
			usersView = inMemoryUsersService.getUsersView(user.getUserId());
			updateMasterDataPrivilegesFromAnotherUser(loginUser, usersView, fromUsersView, currentDate);
		}
	}

	@Override
	public void generateUngeneratedMasterDataPrivileges(UsersView loginUser, UsersView usersView, Timestamp currentDate,
			boolean viewPriv, boolean addPriv) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// PREPARE VARAIBLES
		if (usersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
			viewPriv = true;
			addPriv = true;
		}
		List<Object> entityPrvsList = new ArrayList<>();
		List<Integer> existPKList = masterDataPrivilegesDAO.getBranchesPKFromPrivsTable(usersView.getUserId());
		// "Branches" is in module no 1
		if (inMemoryModulesService.getModulesView(1).getActive()) {
			// LOOP OVER ALL BRANCHES
			for (Integer PK : masterDataPrivilegesDAO.getBranchesPK()) {
				if (!existPKList.contains(PK)) {
					BranchesPriv branchesPriv = new BranchesPriv();
					branchesPriv.setAddDate(currentDate);
					branchesPriv.setAddPriv(addPriv);
					branchesPriv.setAddUser(loginUser.getUserId());
					branchesPriv.setBranchNo(PK);
					branchesPriv.setModifyDate(null);
					branchesPriv.setModifyUser(null);
					branchesPriv.setUserId(usersView.getUserId());
					branchesPriv.setViewPriv(viewPriv);
					entityPrvsList.add(branchesPriv);
				}
			}
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (Object object : entityPrvsList) {
			masterDataPrivilegesDAO.addMasterDataPrivileges(object);
		}
	}
	
	// $$$$$$$$$$$$$$$ For BranchesPriv $$$$$$$$$$$$$$$ 
	
	@Override
	public List<BranchesPrivDTO> getBranchesPrivs(UsersView loginUser, BranchesPrivFilter filter) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.VIEW);
		}
		if (filter.getToBranchNo() != null && filter.getFromBranchNo() == null)
			throw new ValidationException("you_must_enter", "branch_no");
		if (filter.getToUserId() != null && filter.getFromUserId() == null)
			throw new ValidationException("you_must_enter", "user_no");
		List<BranchesPrivDTO> branchesPrivDTOList = masterDataPrivilegesDAO.getBranchesPrivs(loginUser, filter);
		// Add privileges  for adminUser and superAdmin
//		if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
//			Set<BranchesView> branchesViewSet = new HashSet<>();
//			BranchesView branchesView;
//			BranchesPrivDTO branchesPrivDTO;
//			for (BranchesPrivDTO obj : branchesPrivDTOList) {
//				branchesView = new BranchesView();
//				branchesView.setBranchNo(obj.getBranchNo());
//				branchesView.setBranchDName(obj.getBranchDName());
//				branchesView.setBranchFName(obj.getBranchFName());
//				branchesViewSet.add(branchesView);
//			}
//			if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
//				UsersView adminUser = new UsersView();
//				for (UsersView obj : inMemoryUsersService.getAllUsersView()) {
//					if (obj.getAdminUser())
//						adminUser = obj;
//				}
//				for (BranchesView obj : branchesViewSet) {
//					branchesPrivDTO = new BranchesPrivDTO();
//					branchesPrivDTO.setBranchNo(obj.getBranchNo());
//					branchesPrivDTO.setBranchDName(obj.getBranchDName());
//					branchesPrivDTO.setBranchFName(obj.getBranchFName());
//					branchesPrivDTO.setAddPriv(true);
//					branchesPrivDTO.setViewPriv(true);
//					branchesPrivDTO.setCanChangeAddPriv(true);
//					branchesPrivDTO.setCanChangeViewPriv(true);
//					branchesPrivDTO.setUserDName(adminUser.getUserDName());
//					branchesPrivDTO.setUserFName(adminUser.getUserFName());
//					branchesPrivDTO.setUserId(adminUser.getUserId());
//					branchesPrivDTOList.add(branchesPrivDTO);
//				}
//			}
//			if (loginUser.getSuperAdmin()) {
//				UsersView superAdmin = new UsersView();
//				for (UsersView obj : inMemoryUsersService.getAllUsersView()) {
//					if (obj.getSuperAdmin())
//						superAdmin = obj;
//				}
//				for (BranchesView obj : branchesViewSet) {
//					branchesPrivDTO = new BranchesPrivDTO();
//					branchesPrivDTO.setBranchNo(obj.getBranchNo());
//					branchesPrivDTO.setBranchDName(obj.getBranchDName());
//					branchesPrivDTO.setBranchFName(obj.getBranchFName());
//					branchesPrivDTO.setAddPriv(true);
//					branchesPrivDTO.setViewPriv(true);
//					branchesPrivDTO.setCanChangeAddPriv(true);
//					branchesPrivDTO.setCanChangeViewPriv(true);
//					branchesPrivDTO.setUserDName(superAdmin.getUserDName());
//					branchesPrivDTO.setUserFName(superAdmin.getUserFName());
//					branchesPrivDTO.setUserId(superAdmin.getUserId());
//					branchesPrivDTOList.add(branchesPrivDTO);
//				}
//			}
//		}
		return branchesPrivDTOList;
	}

}
