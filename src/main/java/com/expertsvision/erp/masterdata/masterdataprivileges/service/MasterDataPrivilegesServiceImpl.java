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
import com.expertsvision.erp.masterdata.banks.dto.BankVirtualPK;
import com.expertsvision.erp.masterdata.banks.entity.BanksPriv;
import com.expertsvision.erp.masterdata.banks.entity.BanksPrivPK;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPriv;
import com.expertsvision.erp.masterdata.branches.entity.BranchesPrivPK;
import com.expertsvision.erp.masterdata.cash.dto.CashInHandVirtualPK;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandPriv;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandPrivPK;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyPK;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPriv;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPrivPK;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterPriv;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterPrivPK;
import com.expertsvision.erp.masterdata.masterdataprivileges.dao.MasterDataPrivilegesDAO;
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
	// ...

	@Override
	public void generateMasterDataPrivileges(UsersView loginUser, UsersView usersView, Timestamp currentDate,
			boolean viewPriv, boolean addPriv) {
		if (usersView.getAdminUser() || usersView.getSuperAdmin())
			return;
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
		// "ChartOfAccounts" is in module no 2
		// "CostCenter" is in module no 2
		// "Banks" is in module no 2
		// "Cashes" is in module no 2
		if (inMemoryModulesService.getModulesView(2).getActive()) {
			// LOOP OVER ALL AccountsPriv
			AccountsPriv accountPriv = null;
			for (AccountsCurrencyPK PK : masterDataPrivilegesDAO.getAccountsCurrencyPK()) {
				accountPriv = new AccountsPriv();
				accountPriv.setAccCurr(PK.getCurCode());
				accountPriv.setAccNo(PK.getAccNo());
				accountPriv.setAddDate(currentDate);
				accountPriv.setAddPriv(addPriv);
				accountPriv.setAddUser(loginUser.getUserId());
				accountPriv.setModifyDate(null);
				accountPriv.setModifyUser(null);
				accountPriv.setUserId(usersView.getUserId());
				accountPriv.setViewPriv(viewPriv);
				entityPrvsList.add(accountPriv);
			}
			// LOOP OVER ALL CostCenters
			CostCenterPriv costCenterPriv = null;
			for (Integer PK : masterDataPrivilegesDAO.getCostCenterPK()) {
				costCenterPriv = new CostCenterPriv();
				costCenterPriv.setCostCenter(PK);
				costCenterPriv.setAddDate(currentDate);
				costCenterPriv.setAddPriv(addPriv);
				costCenterPriv.setAddUser(loginUser.getUserId());
				costCenterPriv.setModifyDate(null);
				costCenterPriv.setModifyUser(null);
				costCenterPriv.setUserId(usersView.getUserId());
				costCenterPriv.setViewPriv(viewPriv);
				entityPrvsList.add(costCenterPriv);
			}
			// LOOP OVER ALL Banks
			BanksPriv banksPriv = null;
			for (BankVirtualPK PK : masterDataPrivilegesDAO.getBanksVirtualPK()) {
				banksPriv = new BanksPriv();
				banksPriv.setBankNo(PK.getBankNo());
				banksPriv.setAccCurr(PK.getAccCurr());
				banksPriv.setAddDate(currentDate);
				banksPriv.setAddUser(loginUser.getUserId());
				banksPriv.setModifyDate(null);
				banksPriv.setModifyUser(null);
				banksPriv.setAddPriv(addPriv);
				banksPriv.setViewPriv(viewPriv);
				banksPriv.setUserId(usersView.getUserId());
				entityPrvsList.add(banksPriv);
			}
			// LOOP OVER ALL cashes
			CashInHandPriv cashInHandPriv = null;
			for (CashInHandVirtualPK PK : masterDataPrivilegesDAO.getCashesVirtualPK()) {
				cashInHandPriv = new CashInHandPriv();
				cashInHandPriv.setCashNo(PK.getCashNo());
				cashInHandPriv.setAccCurr(PK.getAccCurr());
				cashInHandPriv.setAddDate(currentDate);
				cashInHandPriv.setAddUser(loginUser.getUserId());
				cashInHandPriv.setModifyDate(null);
				cashInHandPriv.setModifyUser(null);
				cashInHandPriv.setAddPriv(addPriv);
				cashInHandPriv.setViewPriv(viewPriv);
				cashInHandPriv.setUserId(usersView.getUserId());
				entityPrvsList.add(cashInHandPriv);
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
		if (usersView.getAdminUser() || usersView.getSuperAdmin())
			return;
		// PREPARE VARAIBLES
		StringBuilder sql = new StringBuilder();
		if (usersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
			viewPriv = true;
			addPriv = true;
		}
		// "Branches" is in module no 1
		if (inMemoryModulesService.getModulesView(1).getActive()) {
			// LOOP OVER ALL BRANCHES
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
		}
		// "ChartOfAccounts" is in module no 2
		// "CostCenter" is in module no 2
		// "Banks" is in module no 2
		// "Cashes" is in module no 2
		if (inMemoryModulesService.getModulesView(2).getActive()) {
			// LOOP OVER ALL AccountsPriv
			for (AccountsCurrencyPK PK : masterDataPrivilegesDAO.getAccountsCurrencyPK()) {
				sql.append("UPDATE accounts_priv SET ");
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
				sql.append(" AND acc_curr='");
				sql.append(PK.getCurCode());
				sql.append("' AND acc_no=");
				sql.append(PK.getAccNo());
				sql.append(";");
			}
			// LOOP OVER ALL CostCenters
			for (Integer PK : masterDataPrivilegesDAO.getCostCenterPK()) {
				sql.append("UPDATE cost_center_priv SET ");
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
				sql.append(" AND cost_center=");
				sql.append(PK);
				sql.append(";");
			}
			// LOOP OVER ALL banks
			for (BankVirtualPK PK : masterDataPrivilegesDAO.getBanksVirtualPK()) {
				sql.append("UPDATE banks_priv SET ");
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
				sql.append(" AND bank_no=");
				sql.append(PK.getBankNo());
				sql.append(" AND acc_curr='");
				sql.append(PK.getAccCurr());
				sql.append("';");
			}
			// LOOP OVER ALL cashes
			for (CashInHandVirtualPK PK : masterDataPrivilegesDAO.getCashesVirtualPK()) {
				sql.append("UPDATE cash_in_hand_priv SET ");
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
				sql.append(" AND cash_no=");
				sql.append(PK.getCashNo());
				sql.append(" AND acc_curr='");
				sql.append(PK.getAccCurr());
				sql.append("';");
			}
		}
		// SAVE ALL PRIVS
		masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(sql.toString());
	}

	@Override
	public void generateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView,
			Integer fromUserId, Timestamp currentDate) {
		if (usersView.getAdminUser() || usersView.getSuperAdmin())
			return;
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
			// "ChartOfAccounts" is in module no 2
			// "CostCenter" is in module no 2
			// "Banks" is in module no 2
			// "Cashes" is in module no 2
			if (inMemoryModulesService.getModulesView(2).getActive()) {
				// LOOP OVER ALL AccountsPriv
				AccountsPriv accountPriv = null;
				for (Object[] obj : masterDataPrivilegesDAO.getAccountsPrivs(fromUserId)) {
					accountPriv = new AccountsPriv();
					accountPriv.setAccCurr((String) obj[1]);
					accountPriv.setAccNo((Integer) obj[0]);
					accountPriv.setAddDate(currentDate);
					accountPriv.setAddPriv((Boolean) obj[2]);
					accountPriv.setAddUser(loginUser.getUserId());
					accountPriv.setModifyDate(null);
					accountPriv.setModifyUser(null);
					accountPriv.setUserId(usersView.getUserId());
					accountPriv.setViewPriv((Boolean) obj[3]);
					entityPrvsList.add(accountPriv);
				}
				// LOOP OVER ALL CostCenter
				CostCenterPriv CostCenterPriv = null;
				for (Object[] obj : masterDataPrivilegesDAO.getCostCentersPrivs(fromUserId)) {
					CostCenterPriv = new CostCenterPriv();
					CostCenterPriv.setCostCenter((Integer) obj[0]);
					CostCenterPriv.setAddDate(currentDate);
					CostCenterPriv.setAddPriv((Boolean) obj[1]);
					CostCenterPriv.setAddUser(loginUser.getUserId());
					CostCenterPriv.setModifyDate(null);
					CostCenterPriv.setModifyUser(null);
					CostCenterPriv.setUserId(usersView.getUserId());
					CostCenterPriv.setViewPriv((Boolean) obj[2]);
					entityPrvsList.add(CostCenterPriv);
				}
				// LOOP OVER ALL Banks
				BanksPriv banksPriv = null;
				for (Object[] obj : masterDataPrivilegesDAO.getBanksPrivs(fromUserId)) {
					banksPriv = new BanksPriv();
					banksPriv.setBankNo((Integer) obj[0]);
					banksPriv.setAccCurr((String) obj[1]);
					banksPriv.setAddDate(currentDate);
					banksPriv.setAddUser(loginUser.getUserId());
					banksPriv.setModifyDate(null);
					banksPriv.setModifyUser(null);
					banksPriv.setAddPriv((Boolean) obj[2]);
					banksPriv.setViewPriv((Boolean) obj[3]);
					banksPriv.setUserId(usersView.getUserId());
					entityPrvsList.add(banksPriv);
				}
				// LOOP OVER ALL Cashes
				CashInHandPriv cashInHandPriv = null;
				for (Object[] obj : masterDataPrivilegesDAO.getCashesPrivs(fromUserId)) {
					cashInHandPriv = new CashInHandPriv();
					cashInHandPriv.setCashNo((Integer) obj[0]);
					cashInHandPriv.setAccCurr((String) obj[1]);
					cashInHandPriv.setAddDate(currentDate);
					cashInHandPriv.setAddUser(loginUser.getUserId());
					cashInHandPriv.setModifyDate(null);
					cashInHandPriv.setModifyUser(null);
					cashInHandPriv.setAddPriv((Boolean) obj[2]);
					cashInHandPriv.setViewPriv((Boolean) obj[3]);
					cashInHandPriv.setUserId(usersView.getUserId());
					entityPrvsList.add(cashInHandPriv);
				}
			}
			// LOOP OVER PRIVS TO SAVE THEM
			for (Object object : entityPrvsList) {
				masterDataPrivilegesDAO.addMasterDataPrivileges(object);
			}
		}
	}

	@Override
	public void updateMasterDataPrivilegesFromAnotherUser(UsersView loginUser, UsersView usersView,
			UsersView fromUsersView, Timestamp currentDate) {
		if (usersView.getAdminUser() || usersView.getSuperAdmin())
			return;
		// PREPARE VARAIBLES
		StringBuilder sql = new StringBuilder();
		fromUsersView = inMemoryUsersService.getUsersView(fromUsersView.getUserId());
		if ((fromUsersView.getGroupNo() != null
				&& inMemoryUsersGroupsService.getUsersGroupsView(fromUsersView.getGroupNo()).getAdminGroup())
				|| (usersView.getGroupNo() != null
						&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup())
				|| fromUsersView.getAdminUser() || fromUsersView.getSuperAdmin()) {
			updateMasterDataPrivileges(loginUser, usersView, currentDate, true, true);
		} else {
			// "Branches" is in module no 1
			if (inMemoryModulesService.getModulesView(1).getActive()) {
				// LOOP OVER ALL BRANCHES
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
			}
			// "ChartOfAccounts" is in module no 2
			// "CostCenter" is in module no 2
			// "Banks" is in module no 2
			// "Cashes" is in module no 2
			if (inMemoryModulesService.getModulesView(2).getActive()) {
				// LOOP OVER ALL AccountsPriv
				for (Object[] obj : masterDataPrivilegesDAO.getAccountsPrivs(fromUsersView.getUserId())) {
					sql.append("UPDATE accounts_priv SET ");
					sql.append("add_priv=");
					sql.append((Boolean) obj[2]);
					sql.append(", view_priv=");
					sql.append((Boolean) obj[3]);
					sql.append(", modify_user=");
					sql.append(loginUser.getUserId());
					sql.append(", modify_date='");
					sql.append(currentDate);
					sql.append("' WHERE user_id=");
					sql.append(usersView.getUserId());
					sql.append(" AND acc_curr='");
					sql.append((String) obj[1]);
					sql.append("' AND acc_no=");
					sql.append((Integer) obj[0]);
					sql.append(";");
				}
				// LOOP OVER ALL CostCenter
				for (Object[] obj : masterDataPrivilegesDAO.getCostCentersPrivs(fromUsersView.getUserId())) {
					sql.append("UPDATE cost_center_priv SET ");
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
					sql.append(" AND cost_center=");
					sql.append((Integer) obj[0]);
					sql.append(";");
				}
				// LOOP OVER ALL Banks
				for (Object[] obj : masterDataPrivilegesDAO.getBanksPrivs(fromUsersView.getUserId())) {
					sql.append("UPDATE banks_priv SET ");
					sql.append("add_priv=");
					sql.append((Boolean) obj[2]);
					sql.append(", view_priv=");
					sql.append((Boolean) obj[3]);
					sql.append(", modify_user=");
					sql.append(loginUser.getUserId());
					sql.append(", modify_date='");
					sql.append(currentDate);
					sql.append("' WHERE user_id=");
					sql.append(usersView.getUserId());
					sql.append(" AND bank_no=");
					sql.append((Integer) obj[0]);
					sql.append(" AND acc_curr='");
					sql.append((String) obj[1]);
					sql.append("';");
				}
				// LOOP OVER ALL Cashes
				for (Object[] obj : masterDataPrivilegesDAO.getCashesPrivs(fromUsersView.getUserId())) {
					sql.append("UPDATE cash_in_hand_priv SET ");
					sql.append("add_priv=");
					sql.append((Boolean) obj[2]);
					sql.append(", view_priv=");
					sql.append((Boolean) obj[3]);
					sql.append(", modify_user=");
					sql.append(loginUser.getUserId());
					sql.append(", modify_date='");
					sql.append(currentDate);
					sql.append("' WHERE user_id=");
					sql.append(usersView.getUserId());
					sql.append(" AND cash_no=");
					sql.append((Integer) obj[0]);
					sql.append(" AND acc_curr='");
					sql.append((String) obj[1]);
					sql.append("';");
				}
			}
			// SAVE ALL PRIVS TO SAVE THEM
			masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(sql.toString());
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
		List<AccountsCurrencyPK> existPKList2 = masterDataPrivilegesDAO
				.getAccountsCurrencyPKFromPrivsTable(usersView.getUserId());
		List<Integer> existPKList3 = masterDataPrivilegesDAO.getCostCenterPKFromPrivsTable(usersView.getUserId());
		List<BankVirtualPK> existPKListForBanks = masterDataPrivilegesDAO
				.getBanksVirtualPKFromPrivsTable(usersView.getUserId());
		List<CashInHandVirtualPK> existPKListForCashes = masterDataPrivilegesDAO
				.getCashesVirtualPKFromPrivsTable(usersView.getUserId());
		// "ChartOfAccounts" is in module no 2
		// "CostCenter" is in module no 2
		// "Banks" is in module no 2
		// "Cashes" is in module no 2
		if (inMemoryModulesService.getModulesView(2).getActive()) {
			// LOOP OVER ALL AccountsPriv
			AccountsPriv accountPriv = null;
			for (AccountsCurrencyPK PK : masterDataPrivilegesDAO.getAccountsCurrencyPK()) {
				if (!existPKList2.contains(PK)) {
					accountPriv = new AccountsPriv();
					accountPriv.setAccCurr(PK.getCurCode());
					accountPriv.setAccNo(PK.getAccNo());
					accountPriv.setAddDate(currentDate);
					accountPriv.setAddPriv(addPriv);
					accountPriv.setAddUser(loginUser.getUserId());
					accountPriv.setModifyDate(null);
					accountPriv.setModifyUser(null);
					accountPriv.setUserId(usersView.getUserId());
					accountPriv.setViewPriv(viewPriv);
					entityPrvsList.add(accountPriv);
				}
			}
			// LOOP OVER ALL CostCenters
			CostCenterPriv costCenterPriv = null;
			for (Integer PK : masterDataPrivilegesDAO.getCostCenterPK()) {
				if (!existPKList3.contains(PK)) {
					costCenterPriv = new CostCenterPriv();
					costCenterPriv.setCostCenter(PK);
					costCenterPriv.setAddDate(currentDate);
					costCenterPriv.setAddPriv(addPriv);
					costCenterPriv.setAddUser(loginUser.getUserId());
					costCenterPriv.setModifyDate(null);
					costCenterPriv.setModifyUser(null);
					costCenterPriv.setUserId(usersView.getUserId());
					costCenterPriv.setViewPriv(viewPriv);
					entityPrvsList.add(costCenterPriv);
				}
			}
			// LOOP OVER ALL Banks
			BanksPriv banksPriv = null;
			for (BankVirtualPK PK : masterDataPrivilegesDAO.getBanksVirtualPK()) {
				if (!existPKListForBanks.contains(PK)) {
					banksPriv = new BanksPriv();
					banksPriv.setBankNo(PK.getBankNo());
					banksPriv.setAccCurr(PK.getAccCurr());
					banksPriv.setAddDate(currentDate);
					banksPriv.setAddUser(loginUser.getUserId());
					banksPriv.setModifyDate(null);
					banksPriv.setModifyUser(null);
					banksPriv.setAddPriv(addPriv);
					banksPriv.setViewPriv(viewPriv);
					banksPriv.setUserId(usersView.getUserId());
					entityPrvsList.add(banksPriv);
				}
			}
			// LOOP OVER ALL Cashes
			CashInHandPriv cashInHandPriv = null;
			for (CashInHandVirtualPK PK : masterDataPrivilegesDAO.getCashesVirtualPK()) {
				if (!existPKListForCashes.contains(PK)) {
					cashInHandPriv = new CashInHandPriv();
					cashInHandPriv.setCashNo(PK.getCashNo());
					cashInHandPriv.setAccCurr(PK.getAccCurr());
					cashInHandPriv.setAddDate(currentDate);
					cashInHandPriv.setAddUser(loginUser.getUserId());
					cashInHandPriv.setModifyDate(null);
					cashInHandPriv.setModifyUser(null);
					cashInHandPriv.setAddPriv(addPriv);
					cashInHandPriv.setViewPriv(viewPriv);
					cashInHandPriv.setUserId(usersView.getUserId());
					entityPrvsList.add(cashInHandPriv);
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
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
		}
		if (filter.getToBranchNo() != null)
			coreValidationService.notNull(filter.getFromBranchNo(), "branch_no");
		if (filter.getToUserId() != null)
			coreValidationService.notNull(filter.getFromUserId(), "user_no");
		List<BranchesPrivDTO> branchesPrivDTOList = masterDataPrivilegesDAO.getBranchesPrivs(loginUser, filter);
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
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
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
				loginUserPrvsMap.put((Integer) objArr[1], objArr);
			}
			for (Object[] objArr : DBUserPrvsList) {
				DBUserPrvsMap.put(new BranchesPrivPK((Integer) objArr[0], (Integer) objArr[1]), objArr);
			}
			for (BranchesPriv prv : branchesPrivList) {
				if (loginUserPrvsMap.get(prv.getBranchNo()) == null)
					throw new ValidationException("not_exist", "branch_no");
				if ((prv.getViewPriv() != DBUserPrvsMap.get(new BranchesPrivPK(prv.getUserId(), prv.getBranchNo()))[3])
						&& !(Boolean) loginUserPrvsMap.get(prv.getBranchNo())[3])
					throw new UnauthorizedException("view_prv");
				if ((prv.getAddPriv() != DBUserPrvsMap.get(new BranchesPrivPK(prv.getUserId(), prv.getBranchNo()))[2])
						&& !(Boolean) loginUserPrvsMap.get(prv.getBranchNo())[2])
					throw new UnauthorizedException("add_prv");
			}
		} else {
			Set<Integer> DBBranchNoSet = generalDAO.getThemIfExist("branches", "branch_no", givenBranchNoSet);
			for (Integer obj : givenBranchNoSet) {
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

	// $$$$$$$$$$$$$$$ For AccountsPriv $$$$$$$$$$$$$$$

	@Override
	@Transactional
	public List<AccountsPrivDTO> getAccountsPrivs(UsersView loginUser, AccountsPrivFilter filter) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
		}
		if (filter.getToAccountNo() != null)
			coreValidationService.notNull(filter.getFromAccountNo(), "acc_no");
		if (filter.getToGroupNo() != null)
			coreValidationService.notNull(filter.getFromGroupNo(), "group_no");
		if (filter.getToUserId() != null)
			coreValidationService.notNull(filter.getFromUserId(), "user_no");
		List<AccountsPrivDTO> accountsPrivDTOList = masterDataPrivilegesDAO.getAccountsPrivs(loginUser, filter);
		return accountsPrivDTOList;
	}

	@Override
	@Transactional
	public void updateAccountsPriv(UsersView loginUser, List<AccountsPriv> accountsPrivList) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.MODIFY);
		}
		Set<Integer> givenUserNoSet = new HashSet<>();
		Set<Integer> givenAccNoSet = new HashSet<>();
		Set<String> givenAccountCurrencyCodeSet = new HashSet<>();
		Set<Integer> subordinatesUsersNoSet = new HashSet<>();
		Map<AccountsCurrencyPK, Object[]> loginUserPrvsMap = new HashMap<>();
		Map<AccountsPrivPK, Object[]> DBUserPrvsMap = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		Timestamp currentDate = new Timestamp(new Date().getTime());
		List<UsersView> subordinatesUsersView = usersViewDAO.getUsersViewSubordinateList(loginUser.getUserId());
		Set<Integer> adminGroupNoList = inMemoryUsersGroupsService.getAdminGroupNoList();
		for (UsersView obj : subordinatesUsersView) {
			if (!obj.getUserId().equals(loginUser.getUserId()) && !obj.getAdminUser() && !obj.getSuperAdmin())
				subordinatesUsersNoSet.add(obj.getUserId());
		}
		for (AccountsPriv prv : accountsPrivList) {
			coreValidationService.notNull(prv.getUserId(), "user_no", "branch_no", prv.getAccNo());
			coreValidationService.notNull(prv.getAccNo(), "acc_no", "user_no", prv.getUserId());
			coreValidationService.notNull(prv.getAccCurr(), "currency_code", "user_no", prv.getUserId());
			coreValidationService.notNull(prv.getAddPriv(), "add_prv", "acc_no", prv.getAccNo());
			coreValidationService.notNull(prv.getViewPriv(), "view_prv", "acc_no", prv.getAccNo());
			if (!subordinatesUsersNoSet.contains(prv.getUserId()))
				throw new UnauthorizedException("user_no");
			if (adminGroupNoList.contains(inMemoryUsersService.getUsersView(prv.getUserId()).getGroupNo()))
				throw new UnauthorizedException("user_no");
			givenUserNoSet.add(prv.getUserId());
			givenAccNoSet.add(prv.getAccNo());
			givenAccountCurrencyCodeSet.add(prv.getAccCurr());
		}
		if (!loginUser.getAdminUser() && !loginUser.getSuperAdmin()) {
			Set<Integer> tempHashSet = new HashSet<>();
			tempHashSet.add(loginUser.getUserId());
			List<Object[]> loginUserPrvsList = masterDataPrivilegesDAO.getAccountsPrivs(tempHashSet, givenAccNoSet,
					givenAccountCurrencyCodeSet);
			List<Object[]> DBUserPrvsList = masterDataPrivilegesDAO.getAccountsPrivs(givenUserNoSet, givenAccNoSet,
					givenAccountCurrencyCodeSet);
			for (Object[] objArr : loginUserPrvsList) {
				loginUserPrvsMap.put(new AccountsCurrencyPK((Integer) objArr[1], (String) objArr[2]), objArr);
			}
			for (Object[] objArr : DBUserPrvsList) {
				DBUserPrvsMap.put(new AccountsPrivPK((Integer) objArr[0], (Integer) objArr[1], (String) objArr[2]),
						objArr);
			}
			for (AccountsPriv prv : accountsPrivList) {
				if (loginUserPrvsMap.get(new AccountsCurrencyPK(prv.getAccNo(), prv.getAccCurr())) == null)
					throw new ValidationException("not_exist", "acc_no");
				if ((prv.getViewPriv() != DBUserPrvsMap
						.get(new AccountsPrivPK(prv.getUserId(), prv.getAccNo(), prv.getAccCurr()))[4])
						&& !(Boolean) loginUserPrvsMap.get(new AccountsCurrencyPK(prv.getAccNo(), prv.getAccCurr()))[4])
					throw new UnauthorizedException("view_prv");
				if ((prv.getAddPriv() != DBUserPrvsMap
						.get(new AccountsPrivPK(prv.getUserId(), prv.getAccNo(), prv.getAccCurr()))[3])
						&& !(Boolean) loginUserPrvsMap.get(new AccountsCurrencyPK(prv.getAccNo(), prv.getAccCurr()))[3])
					throw new UnauthorizedException("add_prv");
			}
		} else {
			Set<Integer> DBAccNoSet = generalDAO.getThemIfExist("chart_of_accounts", "acc_no", givenAccNoSet);
			for (Integer obj : givenAccNoSet) {
				if (!DBAccNoSet.contains(obj))
					throw new ValidationException("not_exist", "acc_no");
			}
		}
		// LOOP OVER ALL PRVS
		for (AccountsPriv prv : accountsPrivList) {
			sql.append("UPDATE accounts_priv SET ")
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
					.append(" AND acc_no=")
					.append(prv.getAccNo())
					.append(" AND acc_curr='")
					.append(prv.getAccCurr())
					.append("';");
		}
		masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(sql.toString());
	}

	// $$$$$$$$$$$$$$$ For CostCenterPriv $$$$$$$$$$$$$$$

	@Override
	@Transactional
	public List<CostCenterPrivDTO> getCostCenterPrivs(UsersView loginUser, CostCenterPrivFilter filter) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
		}
		if (filter.getToCcNo() != null)
			coreValidationService.notNull(filter.getFromCcNo(), "cc_no");
		if (filter.getToUserId() != null)
			coreValidationService.notNull(filter.getFromUserId(), "user_no");
		if (filter.getToGroupNo() != null)
			coreValidationService.notNull(filter.getFromGroupNo(), "group_no");
		List<CostCenterPrivDTO> costCenterPrivDTOList = masterDataPrivilegesDAO.getCostCentersPrivs(loginUser, filter);
		return costCenterPrivDTOList;
	}

	@Override
	@Transactional
	public void updateCostCenterPriv(UsersView loginUser, List<CostCenterPriv> costCenterPrivList) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.MODIFY);
		}
		Set<Integer> givenUserNoSet = new HashSet<>();
		Set<Integer> givenCcNoSet = new HashSet<>();
		Set<Integer> subordinatesUsersNoSet = new HashSet<>();
		Map<Integer, Object[]> loginUserPrvsMap = new HashMap<>();
		Map<CostCenterPrivPK, Object[]> DBUserPrvsMap = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		Timestamp currentDate = new Timestamp(new Date().getTime());
		List<UsersView> subordinatesUsersView = usersViewDAO.getUsersViewSubordinateList(loginUser.getUserId());
		Set<Integer> adminGroupNoList = inMemoryUsersGroupsService.getAdminGroupNoList();
		for (UsersView obj : subordinatesUsersView) {
			if (!obj.getUserId().equals(loginUser.getUserId()) && !obj.getAdminUser() && !obj.getSuperAdmin())
				subordinatesUsersNoSet.add(obj.getUserId());
		}
		for (CostCenterPriv prv : costCenterPrivList) {
			coreValidationService.notNull(prv.getUserId(), "user_no", "cc_no", prv.getCostCenter());
			coreValidationService.notNull(prv.getCostCenter(), "cc_no", "user_no", prv.getUserId());
			coreValidationService.notNull(prv.getAddPriv(), "add_prv", "cc_no", prv.getCostCenter());
			coreValidationService.notNull(prv.getViewPriv(), "view_prv", "cc_no", prv.getCostCenter());
			if (!subordinatesUsersNoSet.contains(prv.getUserId()))
				throw new UnauthorizedException("user_no");
			if (adminGroupNoList.contains(inMemoryUsersService.getUsersView(prv.getUserId()).getGroupNo()))
				throw new UnauthorizedException("user_no");
			givenUserNoSet.add(prv.getUserId());
			givenCcNoSet.add(prv.getCostCenter());
		}
		if (!loginUser.getAdminUser() && !loginUser.getSuperAdmin()) {
			Set<Integer> tempHashSet = new HashSet<>();
			tempHashSet.add(loginUser.getUserId());
			List<Object[]> loginUserPrvsList = masterDataPrivilegesDAO.getCostCentersPrivs(tempHashSet, givenCcNoSet);
			List<Object[]> DBUserPrvsList = masterDataPrivilegesDAO.getCostCentersPrivs(givenUserNoSet, givenCcNoSet);
			for (Object[] objArr : loginUserPrvsList) {
				loginUserPrvsMap.put((Integer) objArr[1], objArr);
			}
			for (Object[] objArr : DBUserPrvsList) {
				DBUserPrvsMap.put(new CostCenterPrivPK((Integer) objArr[0], (Integer) objArr[1]), objArr);
			}
			for (CostCenterPriv prv : costCenterPrivList) {
				if (loginUserPrvsMap.get(prv.getCostCenter()) == null)
					throw new ValidationException("not_exist", "cc_no");
				if ((prv.getViewPriv() != DBUserPrvsMap
						.get(new CostCenterPrivPK(prv.getUserId(), prv.getCostCenter()))[3])
						&& !(Boolean) loginUserPrvsMap.get(prv.getCostCenter())[3])
					throw new UnauthorizedException("view_prv");
				if ((prv.getAddPriv() != DBUserPrvsMap
						.get(new CostCenterPrivPK(prv.getUserId(), prv.getCostCenter()))[2])
						&& !(Boolean) loginUserPrvsMap.get(prv.getCostCenter())[2])
					throw new UnauthorizedException("add_prv");
			}
		} else {
			Set<Integer> DBCcNoSet = generalDAO.getThemIfExist("cost_center", "cc_no", givenCcNoSet);
			for (Integer obj : givenCcNoSet) {
				if (!DBCcNoSet.contains(obj))
					throw new ValidationException("not_exist", "cc_no");
			}
		}
		// LOOP OVER ALL PRVS
		for (CostCenterPriv prv : costCenterPrivList) {
			sql.append("UPDATE cost_center_priv SET ")
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
					.append(" AND cost_center=")
					.append(prv.getCostCenter())
					.append(";");
		}
		masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(sql.toString());
	}

	// $$$$$$$$$$$$$$$ For BanksPriv $$$$$$$$$$$$$$$

	@Override
	@Transactional
	public List<BanksPrivDTO> getBanksPrivs(UsersView loginUser, BanksPrivFilter filter) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
		}
		if (filter.getToBankNo() != null)
			coreValidationService.notNull(filter.getToBankNo(), "bank_no");
		if (filter.getToUserId() != null)
			coreValidationService.notNull(filter.getFromUserId(), "user_no");
		List<BanksPrivDTO> banksPrivDTOList = masterDataPrivilegesDAO.getBanksPrivs(loginUser, filter);
		return banksPrivDTOList;
	}

	@Override
	@Transactional
	public void updateBanksPriv(UsersView loginUser, List<BanksPriv> banksPrivList) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.MODIFY);
		}
		Set<Integer> givenUserNoSet = new HashSet<>();
		Set<Integer> givenBankNoSet = new HashSet<>();
		Set<Integer> subordinatesUsersNoSet = new HashSet<>();
		Map<Integer, Object[]> loginUserPrvsMap = new HashMap<>();
		Map<BanksPrivPK, Object[]> DBUserPrvsMap = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		Timestamp currentDate = new Timestamp(new Date().getTime());
		List<UsersView> subordinatesUsersView = usersViewDAO.getUsersViewSubordinateList(loginUser.getUserId());
		Set<Integer> adminGroupNoList = inMemoryUsersGroupsService.getAdminGroupNoList();
		for (UsersView obj : subordinatesUsersView) {
			if (!obj.getUserId().equals(loginUser.getUserId()) && !obj.getAdminUser() && !obj.getSuperAdmin())
				subordinatesUsersNoSet.add(obj.getUserId());
		}
		for (BanksPriv prv : banksPrivList) {
			coreValidationService.notNull(prv.getUserId(), "user_no", "bank_no", prv.getBankNo());
			coreValidationService.notNull(prv.getBankNo(), "bank_no", "user_no", prv.getUserId());
			coreValidationService.notNull(prv.getAddPriv(), "add_prv", "bank_no", prv.getBankNo());
			coreValidationService.notNull(prv.getViewPriv(), "view_prv", "bank_no", prv.getBankNo());
			if (!subordinatesUsersNoSet.contains(prv.getUserId()))
				throw new UnauthorizedException("user_no");
			if (adminGroupNoList.contains(inMemoryUsersService.getUsersView(prv.getUserId()).getGroupNo()))
				throw new UnauthorizedException("user_no");
			givenUserNoSet.add(prv.getUserId());
			givenBankNoSet.add(prv.getBankNo());
		}
		if (!loginUser.getAdminUser() && !loginUser.getSuperAdmin()) {
			Set<Integer> tempHashSet = new HashSet<>();
			tempHashSet.add(loginUser.getUserId());
			List<Object[]> loginUserPrvsList = masterDataPrivilegesDAO.getBanksPrivs(tempHashSet, givenBankNoSet);
			List<Object[]> DBUserPrvsList = masterDataPrivilegesDAO.getBanksPrivs(givenUserNoSet, givenBankNoSet);
			for (Object[] objArr : loginUserPrvsList) {
				loginUserPrvsMap.put((Integer) objArr[1], objArr);
			}
			for (Object[] objArr : DBUserPrvsList) {
				DBUserPrvsMap.put(new BanksPrivPK((Integer) objArr[0], (Integer) objArr[1]), objArr);
			}
			for (BanksPriv prv : banksPrivList) {
				if (loginUserPrvsMap.get(prv.getBankNo()) == null)
					throw new ValidationException("not_exist", "bank_no");
				if ((prv.getViewPriv() != DBUserPrvsMap.get(new BanksPrivPK(prv.getUserId(), prv.getBankNo()))[3])
						&& !(Boolean) loginUserPrvsMap.get(prv.getBankNo())[3])
					throw new UnauthorizedException("view_prv");
				if ((prv.getAddPriv() != DBUserPrvsMap.get(new BanksPrivPK(prv.getUserId(), prv.getBankNo()))[2])
						&& !(Boolean) loginUserPrvsMap.get(prv.getBankNo())[2])
					throw new UnauthorizedException("add_prv");
			}
		} else {
			Set<Integer> DBBankNoSet = generalDAO.getThemIfExist("banks", "bank_no", givenBankNoSet);
			for (Integer obj : givenBankNoSet) {
				if (!DBBankNoSet.contains(obj))
					throw new ValidationException("not_exist", "bank_no");
			}
		}
		// LOOP OVER ALL PRVS
		for (BanksPriv prv : banksPrivList) {
			sql.append("UPDATE banks_priv SET ")
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
					.append(" AND bank_no=")
					.append(prv.getBankNo())
					.append(";");
		}
		masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(sql.toString());
	}

	// $$$$$$$$$$$$$$$ For CashInHandPriv $$$$$$$$$$$$$$$

	@Override
	@Transactional
	public List<CashesPrivDTO> getCashesPrivs(UsersView loginUser, CashesPrivFilter filter) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
		}
		if (filter.getToCashNo() != null)
			coreValidationService.notNull(filter.getToCashNo(), "cash_no");
		if (filter.getToUserId() != null)
			coreValidationService.notNull(filter.getFromUserId(), "user_no");
		List<CashesPrivDTO> cashsPrivDTOList = masterDataPrivilegesDAO.getCashesPrivs(loginUser, filter);
		return cashsPrivDTOList;
	}

	@Override
	@Transactional
	public void updateCashesPriv(UsersView loginUser, List<CashInHandPriv> cashInHandPrivList) {
		// Check module, form, privileges
		if (!loginUser.getSuperAdmin()) {
			if (loginUser.getAdminUser()) {
				coreValidationService.activeModule(Forms.MASTER_DATA_PRIVILEGES);
			} else {
				coreValidationService.activeModuleAndForm(Forms.MASTER_DATA_PRIVILEGES);
			}
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUser, Forms.MASTER_DATA_PRIVILEGES,
					FormsActions.MODIFY);
		}
		Set<Integer> givenUserNoSet = new HashSet<>();
		Set<Integer> givenCashNoSet = new HashSet<>();
		Set<Integer> subordinatesUsersNoSet = new HashSet<>();
		Map<Integer, Object[]> loginUserPrvsMap = new HashMap<>();
		Map<CashInHandPrivPK, Object[]> DBUserPrvsMap = new HashMap<>();
		StringBuilder sql = new StringBuilder();
		Timestamp currentDate = new Timestamp(new Date().getTime());
		List<UsersView> subordinatesUsersView = usersViewDAO.getUsersViewSubordinateList(loginUser.getUserId());
		Set<Integer> adminGroupNoList = inMemoryUsersGroupsService.getAdminGroupNoList();
		for (UsersView obj : subordinatesUsersView) {
			if (!obj.getUserId().equals(loginUser.getUserId()) && !obj.getAdminUser() && !obj.getSuperAdmin())
				subordinatesUsersNoSet.add(obj.getUserId());
		}
		for (CashInHandPriv prv : cashInHandPrivList) {
			coreValidationService.notNull(prv.getUserId(), "user_no", "cash_no", prv.getCashNo());
			coreValidationService.notNull(prv.getCashNo(), "cash_no", "user_no", prv.getUserId());
			coreValidationService.notNull(prv.getAddPriv(), "add_prv", "cash_no", prv.getCashNo());
			coreValidationService.notNull(prv.getViewPriv(), "view_prv", "cash_no", prv.getCashNo());
			if (!subordinatesUsersNoSet.contains(prv.getUserId()))
				throw new UnauthorizedException("user_no");
			if (adminGroupNoList.contains(inMemoryUsersService.getUsersView(prv.getUserId()).getGroupNo()))
				throw new UnauthorizedException("user_no");
			givenUserNoSet.add(prv.getUserId());
			givenCashNoSet.add(prv.getCashNo());
		}
		if (!loginUser.getAdminUser() && !loginUser.getSuperAdmin()) {
			Set<Integer> tempHashSet = new HashSet<>();
			tempHashSet.add(loginUser.getUserId());
			List<Object[]> loginUserPrvsList = masterDataPrivilegesDAO.getCashesPrivs(tempHashSet, givenCashNoSet);
			List<Object[]> DBUserPrvsList = masterDataPrivilegesDAO.getCashesPrivs(givenUserNoSet, givenCashNoSet);
			for (Object[] objArr : loginUserPrvsList) {
				loginUserPrvsMap.put((Integer) objArr[1], objArr);
			}
			for (Object[] objArr : DBUserPrvsList) {
				DBUserPrvsMap.put(new CashInHandPrivPK((Integer) objArr[0], (Integer) objArr[1]), objArr);
			}
			for (CashInHandPriv prv : cashInHandPrivList) {
				if (loginUserPrvsMap.get(prv.getCashNo()) == null)
					throw new ValidationException("not_exist", "cash_no");
				if ((prv.getViewPriv() != DBUserPrvsMap.get(new CashInHandPrivPK(prv.getUserId(), prv.getCashNo()))[3])
						&& !(Boolean) loginUserPrvsMap.get(prv.getCashNo())[3])
					throw new UnauthorizedException("view_prv");
				if ((prv.getAddPriv() != DBUserPrvsMap.get(new CashInHandPrivPK(prv.getUserId(), prv.getCashNo()))[2])
						&& !(Boolean) loginUserPrvsMap.get(prv.getCashNo())[2])
					throw new UnauthorizedException("add_prv");
			}
		} else {
			Set<Integer> DBCashNoSet = generalDAO.getThemIfExist("cash_in_hand", "cash_no", givenCashNoSet);
			for (Integer obj : givenCashNoSet) {
				if (!DBCashNoSet.contains(obj))
					throw new ValidationException("not_exist", "cash_no");
			}
		}
		// LOOP OVER ALL PRVS
		for (CashInHandPriv prv : cashInHandPrivList) {
			sql.append("UPDATE cash_in_hand_priv SET ")
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
					.append(" AND cash_no=")
					.append(prv.getCashNo())
					.append(";");
		}
		masterDataPrivilegesDAO.updateBulkMasterDataPrivileges(sql.toString());
	}
}
