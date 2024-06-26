package com.expertsvision.erp.masterdata.masterdataprivileges.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.masterdata.banks.dto.BankVirtualPK;
import com.expertsvision.erp.masterdata.cash.dto.CashInHandVirtualPK;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyPK;
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

@Repository
public class MasterDataPrivilegesDAOImpl implements MasterDataPrivilegesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private String filterBySubordinatesQueryWhere = "(WITH RECURSIVE subordinates AS ( "
			+ "SELECT user_id FROM users  WHERE user_id = :managerUserId " + "UNION " + "SELECT u.user_id FROM users u "
			+ "INNER JOIN subordinates s ON s.user_id = u.direct_mang) " + "SELECT user_id FROM subordinates) ";

	@Override
	public void addMasterDataPrivileges(Object masterDataPrivileges) {
		Session session = sessionFactory.getCurrentSession();
		session.save(masterDataPrivileges);
	}

	@Override
	public void updateBulkMasterDataPrivileges(String str) {
		Session session = sessionFactory.getCurrentSession();
		javax.persistence.Query query = session.createNativeQuery(str);
		query.executeUpdate();
	}

	// $$$$$$$$$$$$$$$ For BranchesPriv $$$$$$$$$$$$$$$

	@Override
	public List<Integer> getBranchesPK() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT branchNo FROM Branch";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Integer> result = query.getResultList();
		return result;
	}

	@Override
	public List<Integer> getBranchesPKFromPrivsTable(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT branchNo FROM BranchesPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Integer> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getBranchesPrivs(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT branchNo, addPriv, viewPriv FROM BranchesPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getBranchesPrivs(Set<Integer> userIdList, Set<Integer> branchNoList) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT user_id, branch_no, add_priv, view_priv FROM branches_priv "
				+ "WHERE user_id IN :userIdList AND branch_no IN :branchNoList";
		Query query = session.createNativeQuery(hql);
		query.setParameter("userIdList", userIdList);
		query.setParameter("branchNoList", branchNoList);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public List<BranchesPrivDTO> getBranchesPrivs(UsersView loginUser, BranchesPrivFilter filter) {
		// prepare the queryString
		String queryString;
		List<BranchesPrivDTO> branchesPrivDTOList = new ArrayList<>();
		Object[] objArr;
		BranchesPrivDTO branchesPrivDTO;
		StringBuilder sb = new StringBuilder();
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin())
			sb.append("SELECT m.*, true AS can_change_add_priv, true AS can_change_view_priv, ");
		else
			sb.append("SELECT m.*, n.add_priv AS can_change_add_priv, n.view_priv AS can_change_view_priv, ");
		sb.append("_user.user_d_name AS user_d_name, _user.user_f_name AS user_f_name, ")
				.append("_group.admin_group AS admin_group, ")
				.append("add_user.user_d_name AS add_user_d_name, add_user.user_f_name AS add_user_f_name, ")
				.append("modify_user.user_d_name As modify_user_d_name, modify_user.user_f_name As modify_user_f_name, ")
				.append("branches.branch_d_name AS branch_d_name, branches.branch_f_name AS branch_f_name ")
				.append("FROM branches_priv AS m ");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append("LEFT JOIN branches_priv n ON n.user_id = :managerUserId AND n.branch_no = m.branch_no ");
		sb.append("LEFT JOIN branches AS branches ON m.branch_no = branches.branch_no ")
				.append("LEFT JOIN users AS _user ON m.user_id = _user.user_id ")
				.append("LEFT JOIN users_groups AS _group ON _user.group_no = _group.group_no ")
				.append("LEFT JOIN users AS add_user ON m.add_user = add_user.user_id ")
				.append("LEFT JOIN users AS modify_user ON m.modify_user = modify_user.user_id WHERE 1 = 1");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append(" AND n.view_priv = true ");
		if (filter.getToBranchNo() != null) {
			sb.append(" AND m.branch_no BETWEEN :fromBranchNo AND :toBranchNo ");
		} else if (filter.getFromBranchNo() != null) {
			sb.append(" AND m.branch_no = :fromBranchNo ");
		}
		if (filter.getToUserId() != null) {
			sb.append(" AND m.user_id BETWEEN :fromUserId AND :toUserId ");
		} else if (filter.getFromUserId() != null) {
			sb.append(" AND m.user_id = :fromUserId ");
		}
		if (filter.getGroupNo() != null) {
			sb.append(" AND m.user_id IN (SELECT user_id FROM users WHERE group_no = :groupNo) ");
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin())) {
			sb.append(" AND m.user_id IN ");
			sb.append(filterBySubordinatesQueryWhere);
		}
		sb.append(" ORDER BY branch_no, user_id ");
		queryString = sb.toString();
		// prepare the query
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(queryString);
		if (filter.getToBranchNo() != null) {
			query.setParameter("fromBranchNo", filter.getFromBranchNo());
			query.setParameter("toBranchNo", filter.getToBranchNo());
		} else if (filter.getFromBranchNo() != null) {
			query.setParameter("fromBranchNo", filter.getFromBranchNo());
		}
		if (filter.getToUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
			query.setParameter("toUserId", filter.getToUserId());
		} else if (filter.getFromUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
		}
		if (filter.getGroupNo() != null) {
			query.setParameter("groupNo", filter.getGroupNo());
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			query.setParameter("managerUserId", loginUser.getUserId());
		// get the result list
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object obj : result) {
			objArr = (Object[]) obj;
			branchesPrivDTO = new BranchesPrivDTO();
			branchesPrivDTO.setUserId((Integer) objArr[0]);
			branchesPrivDTO.setBranchNo((Integer) objArr[1]);
			branchesPrivDTO.setAddPriv((Boolean) objArr[2]);
			branchesPrivDTO.setViewPriv((Boolean) objArr[3]);
			branchesPrivDTO.setAddUser((Integer) objArr[4]);
			branchesPrivDTO.setAddDate((Timestamp) objArr[5]);
			branchesPrivDTO.setModifyUser((Integer) objArr[6]);
			branchesPrivDTO.setModifyDate((Timestamp) objArr[7]);
			branchesPrivDTO.setCanChangeAddPriv((Boolean) objArr[8]);
			branchesPrivDTO.setCanChangeViewPriv((Boolean) objArr[9]);
			branchesPrivDTO.setUserDName((String) objArr[10]);
			branchesPrivDTO.setUserFName((String) objArr[11]);
			branchesPrivDTO.setAdminGroup((Boolean) (objArr[12] == null ? false : objArr[12]));
			branchesPrivDTO.setAddUserDName((String) objArr[13]);
			branchesPrivDTO.setAddUserFName((String) objArr[14]);
			branchesPrivDTO.setModifyUserDName((String) objArr[15]);
			branchesPrivDTO.setModifyUserFName((String) objArr[16]);
			branchesPrivDTO.setBranchDName((String) objArr[17]);
			branchesPrivDTO.setBranchFName((String) objArr[18]);
			// set branchesPrivDTO data
			branchesPrivDTOList.add(branchesPrivDTO);
		}
		return branchesPrivDTOList;
	}

	// $$$$$$$$$$$$$$$ For AccountsPriv $$$$$$$$$$$$$$$

	@Override
	public List<AccountsCurrencyPK> getAccountsCurrencyPK() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT accNo, curCode FROM AccountsCurrency";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<AccountsCurrencyPK> accountsCurrencyPKList = new ArrayList<>();
		for (Object[] objArr : result) {
			accountsCurrencyPKList.add(new AccountsCurrencyPK((Integer) objArr[0], (String) objArr[1]));
		}
		return accountsCurrencyPKList;
	}

	@Override
	public List<AccountsCurrencyPK> getAccountsCurrencyPKFromPrivsTable(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT accNo, accCurr FROM AccountsPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<AccountsCurrencyPK> accountsCurrencyPKList = new ArrayList<>();
		for (Object[] objArr : result) {
			accountsCurrencyPKList.add(new AccountsCurrencyPK((Integer) objArr[0], (String) objArr[1]));
		}
		return accountsCurrencyPKList;
	}

	@Override
	public List<Object[]> getAccountsPrivs(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT accNo, accCurr, addPriv, viewPriv FROM AccountsPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getAccountsPrivs(Set<Integer> userIdList, Set<Integer> accNoList, Set<String> curCodeList) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT user_id, accNo, accCurr, add_priv, view_priv FROM accountsPriv "
				+ "WHERE user_id IN :userIdList AND accNo IN :accNoList AND curCode IN :curCodeList";
		Query query = session.createNativeQuery(hql);
		query.setParameter("userIdList", userIdList);
		query.setParameter("accNoList", accNoList);
		query.setParameter("curCodeList", curCodeList);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public List<AccountsPrivDTO> getAccountsPrivs(UsersView loginUser, AccountsPrivFilter filter) {
		// prepare the queryString
		String queryString;
		List<AccountsPrivDTO> accountsPrivDTOList = new ArrayList<>();
		Object[] objArr;
		AccountsPrivDTO accountsPrivDTO;
		StringBuilder sb = new StringBuilder();
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin())
			sb.append("SELECT m.*, true AS can_change_add_priv, true AS can_change_view_priv, ");
		else
			sb.append("SELECT m.*, n.add_priv AS can_change_add_priv, n.view_priv AS can_change_view_priv, ");
		sb.append("_user.user_d_name AS user_d_name, _user.user_f_name AS user_f_name, ")
				.append("_group.admin_group AS admin_group, ")
				.append("add_user.user_d_name AS add_user_d_name, add_user.user_f_name AS add_user_f_name, ")
				.append("modify_user.user_d_name As modify_user_d_name, modify_user.user_f_name As modify_user_f_name, ")
				.append("accounts.acc_d_name AS acc_d_name, accounts.acc_f_name AS acc_f_name, ")
				.append("currency.currency_code AS currency_code, currency.currency_d_name AS currency_d_name, currency.currency_f_name AS currency_f_name, ")
				.append("accounts.acc_group AS group_no, accounts_group.group_d_name AS group_d_name, accounts_group.group_f_name AS group_f_name ")
				.append("FROM accounts_priv AS m ");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append("LEFT JOIN accounts_priv n ON n.user_id = :managerUserId AND n.acc_no = m.acc_no ");
		sb.append("LEFT JOIN chart_of_accounts AS accounts ON m.acc_no = accounts.acc_no ")
				.append("LEFT JOIN currency AS currency ON m.acc_curr = currency.currency_code ")
				.append("LEFT JOIN users AS _user ON m.user_id = _user.user_id ")
				.append("LEFT JOIN users_groups AS _group ON _user.group_no = _group.group_no ")
				.append("LEFT JOIN accounts_group AS accounts_group ON accounts.acc_group = accounts_group.group_no ")
				.append("LEFT JOIN users AS add_user ON m.add_user = add_user.user_id ")
				.append("LEFT JOIN users AS modify_user ON m.modify_user = modify_user.user_id WHERE 1 = 1");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append(" AND n.view_priv = true ");
		if (filter.getToAccountNo() != null) {
			sb.append(" AND m.acc_no BETWEEN :fromAccNo AND :toAccNo ");
		} else if (filter.getFromAccountNo() != null) {
			sb.append(" AND m.acc_no = :fromAccNo ");
		}
		if (filter.getToGroupNo() != null) {
			sb.append(" AND accounts.acc_group BETWEEN :fromGroupNo AND :toGroupNo ");
		} else if (filter.getFromGroupNo() != null) {
			sb.append(" AND accounts.acc_group = :fromGroupNo ");
		}
		if ((filter.getCurrencyList() != null) && (!filter.getCurrencyList().isEmpty())) {
			sb.append(" AND m.acc_curr IN :currencyList ");
		}
		if (filter.getToUserId() != null) {
			sb.append(" AND m.user_id BETWEEN :fromUserId AND :toUserId ");
		} else if (filter.getFromUserId() != null) {
			sb.append(" AND m.user_id = :fromUserId ");
		}
		if (filter.getGroupNo() != null) {
			sb.append(" AND m.user_id IN (SELECT user_id FROM users WHERE group_no = :groupNo) ");
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin())) {
			sb.append(" AND m.user_id IN ");
			sb.append(filterBySubordinatesQueryWhere);
		}
		sb.append(" ORDER BY m.acc_no, m.acc_curr, m.user_id ");
		queryString = sb.toString();
		// prepare the query
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(queryString);
		if (filter.getToAccountNo() != null) {
			query.setParameter("fromAccNo", filter.getFromAccountNo());
			query.setParameter("toAccNo", filter.getToAccountNo());
		} else if (filter.getFromAccountNo() != null) {
			query.setParameter("fromAccNo", filter.getFromAccountNo());
		}
		if (filter.getToGroupNo() != null) {
			query.setParameter("fromGroupNo", filter.getFromGroupNo());
			query.setParameter("toGroupNo", filter.getToGroupNo());
		} else if (filter.getFromGroupNo() != null) {
			query.setParameter("fromGroupNo", filter.getFromGroupNo());
		}
		if ((filter.getCurrencyList() != null) && (!filter.getCurrencyList().isEmpty())) {
			query.setParameter("currencyList", filter.getCurrencyList());
		}
		if (filter.getToUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
			query.setParameter("toUserId", filter.getToUserId());
		} else if (filter.getFromUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
		}
		if (filter.getGroupNo() != null) {
			query.setParameter("groupNo", filter.getGroupNo());
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			query.setParameter("managerUserId", loginUser.getUserId());
		// get the result list
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object obj : result) {
			objArr = (Object[]) obj;
			accountsPrivDTO = new AccountsPrivDTO();
			accountsPrivDTO.setAccCurr((String) objArr[20]);
			accountsPrivDTO.setAccDName((String) objArr[18]);
			accountsPrivDTO.setAccFName((String) objArr[19]);
			accountsPrivDTO.setAccNo((Integer) objArr[1]);
			accountsPrivDTO.setAddDate((Timestamp) objArr[6]);
			accountsPrivDTO.setAddPriv((Boolean) objArr[3]);
			accountsPrivDTO.setAddUser((Integer) objArr[5]);
			accountsPrivDTO.setAddUserDName((String) objArr[14]);
			accountsPrivDTO.setAddUserFName((String) objArr[15]);
			accountsPrivDTO.setAdminGroup((Boolean) (objArr[13] == null ? false : objArr[13]));
			accountsPrivDTO.setCanChangeAddPriv((Boolean) objArr[9]);
			accountsPrivDTO.setCanChangeViewPriv((Boolean) objArr[10]);
			accountsPrivDTO.setCurrencyDName((String) objArr[21]);
			accountsPrivDTO.setCurrencyFName((String) objArr[22]);
			accountsPrivDTO.setGroupDName((String) objArr[24]);
			accountsPrivDTO.setGroupFName((String) objArr[25]);
			accountsPrivDTO.setGroupNo((Integer) objArr[23]);
			accountsPrivDTO.setModifyDate((Timestamp) objArr[8]);
			accountsPrivDTO.setModifyUser((Integer) objArr[7]);
			accountsPrivDTO.setModifyUserDName((String) objArr[16]);
			accountsPrivDTO.setModifyUserFName((String) objArr[17]);
			accountsPrivDTO.setUserDName((String) objArr[11]);
			accountsPrivDTO.setUserFName((String) objArr[12]);
			accountsPrivDTO.setUserId((Integer) objArr[0]);
			accountsPrivDTO.setViewPriv((Boolean) objArr[4]);
			// set accountsPrivDTO data
			accountsPrivDTOList.add(accountsPrivDTO);
		}
		return accountsPrivDTOList;
	}

	// $$$$$$$$$$$$$$$ For CostCenterPriv $$$$$$$$$$$$$$$

	@Override
	public List<Integer> getCostCenterPK() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT ccNo FROM CostCenter";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Integer> result = query.getResultList();
		return result;
	}

	@Override
	public List<Integer> getCostCenterPKFromPrivsTable(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT costCenter FROM CostCenterPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Integer> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getCostCentersPrivs(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT costCenter, addPriv, viewPriv FROM CostCenterPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getCostCentersPrivs(Set<Integer> userIdList, Set<Integer> ccNoList) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT user_id, cost_center, add_priv, view_priv FROM cost_center_priv "
				+ "WHERE user_id IN :userIdList AND cost_center IN :ccNoList";
		Query query = session.createNativeQuery(hql);
		query.setParameter("userIdList", userIdList);
		query.setParameter("ccNoList", ccNoList);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public List<CostCenterPrivDTO> getCostCentersPrivs(UsersView loginUser, CostCenterPrivFilter filter) {
		// prepare the queryString
		String queryString;
		List<CostCenterPrivDTO> costCenterPrivDTOList = new ArrayList<>();
		Object[] objArr;
		CostCenterPrivDTO costCenterPrivDTO;
		StringBuilder sb = new StringBuilder();
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin())
			sb.append("SELECT m.*, true AS can_change_add_priv, true AS can_change_view_priv, ");
		else
			sb.append("SELECT m.*, n.add_priv AS can_change_add_priv, n.view_priv AS can_change_view_priv, ");
		sb.append("_user.user_d_name AS user_d_name, _user.user_f_name AS user_f_name, ")
				.append("_group.admin_group AS admin_group, ")
				.append("add_user.user_d_name AS add_user_d_name, add_user.user_f_name AS add_user_f_name, ")
				.append("modify_user.user_d_name As modify_user_d_name, modify_user.user_f_name As modify_user_f_name, ")
				.append("cost_center.cc_d_name AS cc_d_name, cost_center.cc_f_name AS cc_f_name ")
				.append("FROM cost_center_priv AS m ");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append("LEFT JOIN cost_center_priv n ON n.user_id = :managerUserId AND n.cost_center = m.cost_center ");
		sb.append("LEFT JOIN cost_center AS cost_center ON m.cost_center = cost_center.cc_no ")
				.append("LEFT JOIN users AS _user ON m.user_id = _user.user_id ")
				.append("LEFT JOIN users_groups AS _group ON _user.group_no = _group.group_no ")
				.append("LEFT JOIN users AS add_user ON m.add_user = add_user.user_id ")
				.append("LEFT JOIN users AS modify_user ON m.modify_user = modify_user.user_id WHERE 1 = 1");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append(" AND n.view_priv = true ");
		if (filter.getToCcNo() != null) {
			sb.append(" AND m.cost_center BETWEEN :fromCcNo AND :toCcNo ");
		} else if (filter.getFromCcNo() != null) {
			sb.append(" AND m.cost_center = :fromCcNo ");
		}
		if (filter.getToGroupNo() != null) {
			sb.append(" AND accounts.acc_group BETWEEN :fromGroupNo AND :toGroupNo ");
		} else if (filter.getFromGroupNo() != null) {
			sb.append(" AND accounts.acc_group = :fromGroupNo ");
		}
		if (filter.getToUserId() != null) {
			sb.append(" AND m.user_id BETWEEN :fromUserId AND :toUserId ");
		} else if (filter.getFromUserId() != null) {
			sb.append(" AND m.user_id = :fromUserId ");
		}
		if (filter.getGroupNo() != null) {
			sb.append(" AND m.user_id IN (SELECT user_id FROM users WHERE group_no = :groupNo) ");
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin())) {
			sb.append(" AND m.user_id IN ");
			sb.append(filterBySubordinatesQueryWhere);
		}
		sb.append(" ORDER BY m.cost_center, user_id ");
		queryString = sb.toString();
		// prepare the query
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(queryString);
		if (filter.getToCcNo() != null) {
			query.setParameter("fromCcNo", filter.getFromCcNo());
			query.setParameter("toCcNo", filter.getToCcNo());
		} else if (filter.getFromCcNo() != null) {
			query.setParameter("fromCcNo", filter.getFromCcNo());
		}
		if (filter.getToGroupNo() != null) {
			query.setParameter("fromGroupNo", filter.getFromGroupNo());
			query.setParameter("toGroupNo", filter.getToGroupNo());
		} else if (filter.getFromGroupNo() != null) {
			query.setParameter("fromGroupNo", filter.getFromGroupNo());
		}
		if (filter.getToUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
			query.setParameter("toUserId", filter.getToUserId());
		} else if (filter.getFromUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
		}
		if (filter.getGroupNo() != null) {
			query.setParameter("groupNo", filter.getGroupNo());
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			query.setParameter("managerUserId", loginUser.getUserId());
		// get the result list
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object obj : result) {
			objArr = (Object[]) obj;
			costCenterPrivDTO = new CostCenterPrivDTO();
			costCenterPrivDTO.setUserId((Integer) objArr[0]);
			costCenterPrivDTO.setCostCenter((Integer) objArr[1]);
			costCenterPrivDTO.setAddPriv((Boolean) objArr[2]);
			costCenterPrivDTO.setViewPriv((Boolean) objArr[3]);
			costCenterPrivDTO.setAddUser((Integer) objArr[4]);
			costCenterPrivDTO.setAddDate((Timestamp) objArr[5]);
			costCenterPrivDTO.setModifyUser((Integer) objArr[6]);
			costCenterPrivDTO.setModifyDate((Timestamp) objArr[7]);
			costCenterPrivDTO.setCanChangeAddPriv((Boolean) objArr[8]);
			costCenterPrivDTO.setCanChangeViewPriv((Boolean) objArr[9]);
			costCenterPrivDTO.setUserDName((String) objArr[10]);
			costCenterPrivDTO.setUserFName((String) objArr[11]);
			costCenterPrivDTO.setAdminGroup((Boolean) (objArr[12] == null ? false : objArr[12]));
			costCenterPrivDTO.setAddUserDName((String) objArr[13]);
			costCenterPrivDTO.setAddUserFName((String) objArr[14]);
			costCenterPrivDTO.setModifyUserDName((String) objArr[15]);
			costCenterPrivDTO.setModifyUserFName((String) objArr[16]);
			costCenterPrivDTO.setCcDName((String) objArr[17]);
			costCenterPrivDTO.setCcFName((String) objArr[18]);
			// set branchesPrivDTO data
			costCenterPrivDTOList.add(costCenterPrivDTO);
		}
		return costCenterPrivDTOList;
	}

	// $$$$$$$$$$$$$$$ For BanksPriv $$$$$$$$$$$$$$$

	@Override
	public List<BankVirtualPK> getBanksVirtualPK() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT bankNo, accCurr FROM BanksDtl";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<BankVirtualPK> bankVirtualPKList = new ArrayList<>();
		for (Object[] objArr : result) {
			bankVirtualPKList.add(new BankVirtualPK((Integer) objArr[0], (String) objArr[1]));
		}
		return bankVirtualPKList;
	}

	@Override
	public List<BankVirtualPK> getBanksVirtualPKFromPrivsTable(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT bankNo, accCurr FROM BanksPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<BankVirtualPK> bankVirtualPKList = new ArrayList<>();
		for (Object[] objArr : result) {
			bankVirtualPKList.add(new BankVirtualPK((Integer) objArr[0], (String) objArr[1]));
		}
		return bankVirtualPKList;
	}

	@Override
	public List<Object[]> getBanksPrivs(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT bankNo, accCurr, addPriv, viewPriv FROM BanksPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getBanksPrivs(Set<Integer> userIdList, Set<Integer> bankNoList) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT user_id, bank_no, add_priv, view_priv FROM banks_priv "
				+ "WHERE user_id IN :userIdList AND bank_no IN :bankNoList";
		Query query = session.createNativeQuery(hql);
		query.setParameter("userIdList", userIdList);
		query.setParameter("bankNoList", bankNoList);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public List<BanksPrivDTO> getBanksPrivs(UsersView loginUser, BanksPrivFilter filter) {
		// prepare the queryString
		String queryString;
		List<BanksPrivDTO> banksPrivDTOList = new ArrayList<>();
		Object[] objArr;
		BanksPrivDTO banksPrivDTO;
		StringBuilder sb = new StringBuilder();
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin())
			sb.append("SELECT m.*, true AS can_change_add_priv, true AS can_change_view_priv, ");
		else
			sb.append("SELECT m.*, n.add_priv AS can_change_add_priv, n.view_priv AS can_change_view_priv, ");
		sb.append("_user.user_d_name AS user_d_name, _user.user_f_name AS user_f_name, ")
				.append("_group.admin_group AS admin_group, ")
				.append("add_user.user_d_name AS add_user_d_name, add_user.user_f_name AS add_user_f_name, ")
				.append("modify_user.user_d_name As modify_user_d_name, modify_user.user_f_name As modify_user_f_name, ")
				.append("banks.bank_d_name AS bank_d_name, banks.bank_f_name AS bank_f_name ")
				.append("FROM banks_priv AS m ");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append("LEFT JOIN banks_priv n ON n.user_id = :managerUserId AND n.bank_no = m.bank_no ");
		sb.append("LEFT JOIN banks AS banks ON m.bank_no = banks.bank_no ")
				.append("LEFT JOIN users AS _user ON m.user_id = _user.user_id ")
				.append("LEFT JOIN users_groups AS _group ON _user.group_no = _group.group_no ")
				.append("LEFT JOIN users AS add_user ON m.add_user = add_user.user_id ")
				.append("LEFT JOIN users AS modify_user ON m.modify_user = modify_user.user_id WHERE 1 = 1");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append(" AND n.view_priv = true ");
		if (filter.getToBankNo() != null) {
			sb.append(" AND m.bank_no BETWEEN :fromBankNo AND :toBankhNo ");
		} else if (filter.getFromBankNo() != null) {
			sb.append(" AND m.bank_no = :fromBankNo ");
		}
		if (filter.getToUserId() != null) {
			sb.append(" AND m.user_id BETWEEN :fromUserId AND :toUserId ");
		} else if (filter.getFromUserId() != null) {
			sb.append(" AND m.user_id = :fromUserId ");
		}
		if (filter.getGroupNo() != null) {
			sb.append(" AND m.user_id IN (SELECT user_id FROM users WHERE group_no = :groupNo) ");
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin())) {
			sb.append(" AND m.user_id IN ");
			sb.append(filterBySubordinatesQueryWhere);
		}
		sb.append(" ORDER BY bank_no, user_id ");
		queryString = sb.toString();
		// prepare the query
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(queryString);
		if (filter.getToBankNo() != null) {
			query.setParameter("fromBankNo", filter.getFromBankNo());
			query.setParameter("toBankhNo", filter.getToBankNo());
		} else if (filter.getFromBankNo() != null) {
			query.setParameter("fromBankNo", filter.getFromBankNo());
		}
		if (filter.getToUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
			query.setParameter("toUserId", filter.getToUserId());
		} else if (filter.getFromUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
		}
		if (filter.getGroupNo() != null) {
			query.setParameter("groupNo", filter.getGroupNo());
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			query.setParameter("managerUserId", loginUser.getUserId());
		// get the result list
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object obj : result) {
			objArr = (Object[]) obj;
			banksPrivDTO = new BanksPrivDTO();
			banksPrivDTO.setUserId((Integer) objArr[0]);
			banksPrivDTO.setBankNo((Integer) objArr[1]);
			banksPrivDTO.setAccCurr((String) objArr[2]);
			banksPrivDTO.setAddPriv((Boolean) objArr[3]);
			banksPrivDTO.setViewPriv((Boolean) objArr[4]);
			banksPrivDTO.setAddUser((Integer) objArr[5]);
			banksPrivDTO.setAddDate((Timestamp) objArr[6]);
			banksPrivDTO.setModifyUser((Integer) objArr[7]);
			banksPrivDTO.setModifyDate((Timestamp) objArr[8]);
			banksPrivDTO.setCanChangeAddPriv((Boolean) objArr[9]);
			banksPrivDTO.setCanChangeViewPriv((Boolean) objArr[10]);
			banksPrivDTO.setUserDName((String) objArr[11]);
			banksPrivDTO.setUserFName((String) objArr[12]);
			banksPrivDTO.setAdminGroup((Boolean) (objArr[13] == null ? false : objArr[13]));
			banksPrivDTO.setAddUserDName((String) objArr[14]);
			banksPrivDTO.setAddUserFName((String) objArr[15]);
			banksPrivDTO.setModifyUserDName((String) objArr[16]);
			banksPrivDTO.setModifyUserFName((String) objArr[17]);
			banksPrivDTO.setBankDName((String) objArr[18]);
			banksPrivDTO.setBankFName((String) objArr[19]);
			// set branchesPrivDTO data
			banksPrivDTOList.add(banksPrivDTO);
		}
		return banksPrivDTOList;
	}

	// $$$$$$$$$$$$$$$ For CashInHandPriv $$$$$$$$$$$$$$$

	@Override
	public List<CashInHandVirtualPK> getCashesVirtualPK() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT cashNo, accCurr FROM CashInHandDtl";
		Query query = session.createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<CashInHandVirtualPK> cashInHandVirtualPKList = new ArrayList<>();
		for (Object[] objArr : result) {
			cashInHandVirtualPKList.add(new CashInHandVirtualPK((Integer) objArr[0], (String) objArr[1]));
		}
		return cashInHandVirtualPKList;
	}

	@Override
	public List<CashInHandVirtualPK> getCashesVirtualPKFromPrivsTable(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT cashNo, accCurr FROM CashInHandPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<CashInHandVirtualPK> cashInHandVirtualPKList = new ArrayList<>();
		for (Object[] objArr : result) {
			cashInHandVirtualPKList.add(new CashInHandVirtualPK((Integer) objArr[0], (String) objArr[1]));
		}
		return cashInHandVirtualPKList;
	}

	@Override
	public List<Object[]> getCashesPrivs(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT cashNo, accCurr, addPriv, viewPriv FROM CashInHandPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getCashesPrivs(Set<Integer> userIdList, Set<Integer> cashNoList) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT user_id, cash_no, add_priv, view_priv FROM cash_in_hand_priv "
				+ "WHERE user_id IN :userIdList AND cash_no IN :cashNoList";
		Query query = session.createNativeQuery(hql);
		query.setParameter("userIdList", userIdList);
		query.setParameter("cashNoList", cashNoList);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	@Transactional
	public List<CashesPrivDTO> getCashesPrivs(UsersView loginUser, CashesPrivFilter filter) {
		// prepare the queryString
		String queryString;
		List<CashesPrivDTO> cashesPrivDTOList = new ArrayList<>();
		Object[] objArr;
		CashesPrivDTO cashesPrivDTO;
		StringBuilder sb = new StringBuilder();
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin())
			sb.append("SELECT m.*, true AS can_change_add_priv, true AS can_change_view_priv, ");
		else
			sb.append("SELECT m.*, n.add_priv AS can_change_add_priv, n.view_priv AS can_change_view_priv, ");
		sb.append("_user.user_d_name AS user_d_name, _user.user_f_name AS user_f_name, ")
				.append("_group.admin_group AS admin_group, ")
				.append("add_user.user_d_name AS add_user_d_name, add_user.user_f_name AS add_user_f_name, ")
				.append("modify_user.user_d_name As modify_user_d_name, modify_user.user_f_name As modify_user_f_name, ")
				.append("cashes.cash_d_name AS cash_d_name, cashes.cash_f_name AS cash_f_name ")
				.append("FROM cash_in_hand_priv AS m ");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append("LEFT JOIN cash_in_hand_priv n ON n.user_id = :managerUserId AND n.cash_no = m.cash_no ");
		sb.append("LEFT JOIN cash_in_hand AS cashes ON m.cash_no = cashes.cash_no ")
				.append("LEFT JOIN users AS _user ON m.user_id = _user.user_id ")
				.append("LEFT JOIN users_groups AS _group ON _user.group_no = _group.group_no ")
				.append("LEFT JOIN users AS add_user ON m.add_user = add_user.user_id ")
				.append("LEFT JOIN users AS modify_user ON m.modify_user = modify_user.user_id WHERE 1 = 1");
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			sb.append(" AND n.view_priv = true ");
		if (filter.getToCashNo() != null) {
			sb.append(" AND m.cash_no BETWEEN :fromCashNo AND :toCashhNo ");
		} else if (filter.getFromCashNo() != null) {
			sb.append(" AND m.cash_no = :fromCashNo ");
		}
		if (filter.getToUserId() != null) {
			sb.append(" AND m.user_id BETWEEN :fromUserId AND :toUserId ");
		} else if (filter.getFromUserId() != null) {
			sb.append(" AND m.user_id = :fromUserId ");
		}
		if (filter.getGroupNo() != null) {
			sb.append(" AND m.user_id IN (SELECT user_id FROM users WHERE group_no = :groupNo) ");
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin())) {
			sb.append(" AND m.user_id IN ");
			sb.append(filterBySubordinatesQueryWhere);
		}
		sb.append(" ORDER BY cash_no, user_id ");
		queryString = sb.toString();
		// prepare the query
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createNativeQuery(queryString);
		if (filter.getToCashNo() != null) {
			query.setParameter("fromCashNo", filter.getFromCashNo());
			query.setParameter("toCashNo", filter.getToCashNo());
		} else if (filter.getFromCashNo() != null) {
			query.setParameter("fromCashNo", filter.getFromCashNo());
		}
		if (filter.getToUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
			query.setParameter("toUserId", filter.getToUserId());
		} else if (filter.getFromUserId() != null) {
			query.setParameter("fromUserId", filter.getFromUserId());
		}
		if (filter.getGroupNo() != null) {
			query.setParameter("groupNo", filter.getGroupNo());
		}
		if (!(loginUser.getAdminUser() || loginUser.getSuperAdmin()))
			query.setParameter("managerUserId", loginUser.getUserId());
		// get the result list
		@SuppressWarnings("unchecked")
		List<Object> result = query.getResultList();
		for (Object obj : result) {
			objArr = (Object[]) obj;
			cashesPrivDTO = new CashesPrivDTO();
			cashesPrivDTO.setUserId((Integer) objArr[0]);
			cashesPrivDTO.setCashNo((Integer) objArr[1]);
			cashesPrivDTO.setAccCurr((String) objArr[2]);
			cashesPrivDTO.setAddPriv((Boolean) objArr[3]);
			cashesPrivDTO.setViewPriv((Boolean) objArr[4]);
			cashesPrivDTO.setAddUser((Integer) objArr[5]);
			cashesPrivDTO.setAddDate((Timestamp) objArr[6]);
			cashesPrivDTO.setModifyUser((Integer) objArr[7]);
			cashesPrivDTO.setModifyDate((Timestamp) objArr[8]);
			cashesPrivDTO.setCanChangeAddPriv((Boolean) objArr[9]);
			cashesPrivDTO.setCanChangeViewPriv((Boolean) objArr[10]);
			cashesPrivDTO.setUserDName((String) objArr[11]);
			cashesPrivDTO.setUserFName((String) objArr[12]);
			cashesPrivDTO.setAdminGroup((Boolean) (objArr[13] == null ? false : objArr[13]));
			cashesPrivDTO.setAddUserDName((String) objArr[14]);
			cashesPrivDTO.setAddUserFName((String) objArr[15]);
			cashesPrivDTO.setModifyUserDName((String) objArr[16]);
			cashesPrivDTO.setModifyUserFName((String) objArr[17]);
			cashesPrivDTO.setCashDName((String) objArr[18]);
			cashesPrivDTO.setCashFName((String) objArr[19]);
			// set branchesPrivDTO data
			cashesPrivDTOList.add(cashesPrivDTO);
		}
		return cashesPrivDTOList;
	}

}
