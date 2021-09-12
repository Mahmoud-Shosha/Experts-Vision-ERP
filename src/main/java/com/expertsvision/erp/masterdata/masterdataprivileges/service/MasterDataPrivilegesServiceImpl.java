package com.expertsvision.erp.masterdata.masterdataprivileges.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.module.service.InMemoryModulesService;
import com.expertsvision.erp.core.user.dao.UsersDAO;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.user.service.UsersService;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPrivPK;
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
	
	@Autowired
	private UsersDAO usersViewDAO;
	
	@Autowired
	private GeneralDAO generalDAO;

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
	@Transactional
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
		if (filter.getToBranchNo() != null)
			coreValidationService.notNull(filter.getFromBranchNo(), "branch_no");
		if (filter.getToUserId() != null)
			coreValidationService.notNull(filter.getFromUserId(), "user_no");
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
	
	@Override
	@Transactional
	public void updateBrachesPriv(UsersView loginUser, List<BranchesPriv> branchesPrivList) {
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
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.MODIFY);
		}
		Set<Integer> givenUserNoSet = new HashSet<>();
		Set<Integer> givenBranchNoSet = new HashSet<>();
		Set<Integer> subordinatesUsersNoSet = new HashSet<>();
		Map<Integer, Object[]> loginUserPrvsMap = new HashMap<>();
		Map<BranchesPrivPK, Object[]> DBUserPrvsMap = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		Timestamp currentDate = new Timestamp(new Date().getTime());
		List<UsersView> subordinatesUsersView = usersViewDAO.getUsersViewSubordinateList(loginUser.getUserId());
		Set<Integer> adminGroupNoList = inMemoryUsersGroupsService.getAdminGroupNoList();
		for (UsersView obj : subordinatesUsersView) {
			if (!obj.getUserId().equals(loginUser.getUserId()) && !obj.getAdminUser() && !obj.getSuperAdmin())
				subordinatesUsersNoSet.add(obj.getUserId());
		}
		for (BranchesPriv prv : branchesPrivList) {
			coreValidationService.notNull(prv.getUserId(), "user_no", "branch_no", prv.getBranchNo());
			coreValidationService.notNull(prv.getBranchNo(), "branch_no", "user_no", prv.getUserId());
			coreValidationService.notNull(prv.getAddPriv(), "add_prv", "branch_no", prv.getBranchNo());
			coreValidationService.notNull(prv.getViewPriv(), "view_prv", "branch_no", prv.getBranchNo());
			if (!subordinatesUsersNoSet.contains(prv.getUserId()))
				throw new UnauthorizedException("user_no");
			if (adminGroupNoList.contains(inMemoryUsersService.getUsersView(prv.getUserId()).getGroupNo()))
				throw new UnauthorizedException("user_no");
			givenUserNoSet.add(prv.getUserId());
			givenBranchNoSet.add(prv.getBranchNo());
		}
		if (!loginUser.getAdminUser() && !loginUser.getSuperAdmin()) {
			Set<Integer> tempHashSet = new HashSet<>();
			tempHashSet.add(loginUser.getUserId());
			List<Object[]> loginUserPrvsList = masterDataPrivilegesDAO.getBranchesPrivs(tempHashSet, givenBranchNoSet);
			List<Object[]> DBUserPrvsList = masterDataPrivilegesDAO.getBranchesPrivs(givenUserNoSet, givenBranchNoSet);
			for (Object[] objArr : loginUserPrvsList) {
				loginUserPrvsMap.put((Integer)objArr[1], objArr);
			}
			for (Object[] objArr : DBUserPrvsList) {
				DBUserPrvsMap.put(new BranchesPrivPK((Integer)objArr[0], (Integer)objArr[1]), objArr);
			}
			for (BranchesPriv prv : branchesPrivList) {
				if (loginUserPrvsMap.get(prv.getBranchNo()) == null)
					throw new ValidationException("not_exist", "branch_no");
				if ((prv.getViewPriv()!=DBUserPrvsMap.get(new BranchesPrivPK(prv.getUserId(), prv.getBranchNo()))[3])
						&& !(Boolean)loginUserPrvsMap.get(prv.getBranchNo())[3])
					throw new UnauthorizedException("view_prv");
				if ((prv.getAddPriv()!=DBUserPrvsMap.get(new BranchesPrivPK(prv.getUserId(), prv.getBranchNo()))[2])
						&& !(Boolean)loginUserPrvsMap.get(prv.getBranchNo())[2])
					throw new UnauthorizedException("add_prv");
			}
		} else {
			Set<Integer> DBBranchNoSet = generalDAO.getThemIfExist("branches", "branch_no", givenBranchNoSet);
			for (Integer obj: givenBranchNoSet) {
				if (!DBBranchNoSet.contains(obj))
					throw new ValidationException("not_exist", "branch_no");
			}
		}
		// LOOP OVER ALL PRVS
		for (BranchesPriv prv : branchesPrivList) {
			sql.append("UPDATE branches_priv SET ")
			.append("add_priv=")
			.append(prv.getAddPriv())
			.append(", view_priv=")
			.append(prv.getViewPriv())
			.append(", modify_user=")
			.append(loginUser.getUserId())
			.append(", modify_date='")
			.append(currentDate)
			.append("' WHERE user_id=")
			.append(prv.getUserId())
			.append(" AND branch_no=")
			.append(prv.getBranchNo())
			.append(";");
		}
		masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(sql.toString());
	}

}
