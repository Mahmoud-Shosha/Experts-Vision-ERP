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
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsCurrencyPK;
import com.expertsvision.erp.masterdata.chartofaccounts.entity.AccountsPriv;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.AccountsPrivFilter;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivDTO;
import com.expertsvision.erp.masterdata.masterdataprivileges.dto.BranchesPrivFilter;

@Repository
public class MasterDataPrivilegesDAOImpl implements MasterDataPrivilegesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private String filterBySubordinatesQueryWhere = "(WITH RECURSIVE subordinates AS ( "
			+ "SELECT user_id FROM users  WHERE user_id = :managerUserId "
			+ "UNION "
			+ "SELECT u.user_id FROM users u "
			+ "INNER JOIN subordinates s ON s.user_id = u.direct_mang) "
			+ "SELECT user_id FROM subordinates) ";

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
				    +"WHERE user_id IN :userIdList AND branch_no IN :branchNoList";
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
			objArr = (Object[])obj;
			branchesPrivDTO = new BranchesPrivDTO();
			branchesPrivDTO.setUserId((Integer)objArr[0]);
			branchesPrivDTO.setBranchNo((Integer)objArr[1]);
			branchesPrivDTO.setAddPriv((Boolean)objArr[2]);
			branchesPrivDTO.setViewPriv((Boolean)objArr[3]);
			branchesPrivDTO.setAddUser((Integer)objArr[4]);
			branchesPrivDTO.setAddDate((Timestamp)objArr[5]);
			branchesPrivDTO.setModifyUser((Integer)objArr[6]);
			branchesPrivDTO.setModifyDate((Timestamp)objArr[7]);
			branchesPrivDTO.setCanChangeAddPriv((Boolean)objArr[8]);
			branchesPrivDTO.setCanChangeViewPriv((Boolean)objArr[9]);
			branchesPrivDTO.setUserDName((String)objArr[10]);
			branchesPrivDTO.setUserFName((String)objArr[11]);
			branchesPrivDTO.setAdminGroup((Boolean)(objArr[12]==null?false:objArr[12]));
			branchesPrivDTO.setAddUserDName((String)objArr[13]);
			branchesPrivDTO.setAddUserFName((String)objArr[14]);
			branchesPrivDTO.setModifyUserDName((String)objArr[15]);
			branchesPrivDTO.setModifyUserFName((String)objArr[16]);
			branchesPrivDTO.setBranchDName((String)objArr[17]);
			branchesPrivDTO.setBranchFName((String)objArr[18]);
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
			accountsCurrencyPKList.add(new AccountsCurrencyPK((Integer)objArr[0], (String)objArr[1]));
		}
		return accountsCurrencyPKList;
	}
	
	@Override
	public List<AccountsCurrencyPK> getAccountsCurrencyPKFromPrivsTable(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT accNo, curCode FROM AccountsPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		List<AccountsCurrencyPK> accountsCurrencyPKList = new ArrayList<>();
		for (Object[] objArr : result) {
			accountsCurrencyPKList.add(new AccountsCurrencyPK((Integer)objArr[0], (String)objArr[1]));
		}
		return accountsCurrencyPKList;
	}

	@Override
	public List<Object[]> getAccountsPrivs(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT accNo, curCode, addPriv, viewPriv FROM AccountsPriv WHERE userId = :userId";
		Query query = session.createQuery(hql);
		query.setParameter("userId", userId);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}

	@Override
	public List<Object[]> getAccountsPrivs(Set<Integer> userIdList, Set<Integer> accNoList, Set<String> curCodeList) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "SELECT user_id, accNo, curCode, add_priv, view_priv FROM accountsPriv "
				    +"WHERE user_id IN :userIdList AND accNo IN :accNoList AND curCode IN :curCodeList";
		Query query = session.createNativeQuery(hql);
		query.setParameter("userIdList", userIdList);
		query.setParameter("accNoList", accNoList);
		query.setParameter("curCodeList", curCodeList);
		@SuppressWarnings("unchecked")
		List<Object[]> result = query.getResultList();
		return result;
	}
	
	// Continue here .....
	// Donot forget to continue in the servie
	@Override
	@Transactional
	public List<AccountsPrivDTO> getAccountsPrivs(UsersView loginUser, AccountsPrivFilter filter) {
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
			objArr = (Object[])obj;
			branchesPrivDTO = new BranchesPrivDTO();
			branchesPrivDTO.setUserId((Integer)objArr[0]);
			branchesPrivDTO.setBranchNo((Integer)objArr[1]);
			branchesPrivDTO.setAddPriv((Boolean)objArr[2]);
			branchesPrivDTO.setViewPriv((Boolean)objArr[3]);
			branchesPrivDTO.setAddUser((Integer)objArr[4]);
			branchesPrivDTO.setAddDate((Timestamp)objArr[5]);
			branchesPrivDTO.setModifyUser((Integer)objArr[6]);
			branchesPrivDTO.setModifyDate((Timestamp)objArr[7]);
			branchesPrivDTO.setCanChangeAddPriv((Boolean)objArr[8]);
			branchesPrivDTO.setCanChangeViewPriv((Boolean)objArr[9]);
			branchesPrivDTO.setUserDName((String)objArr[10]);
			branchesPrivDTO.setUserFName((String)objArr[11]);
			branchesPrivDTO.setAdminGroup((Boolean)(objArr[12]==null?false:objArr[12]));
			branchesPrivDTO.setAddUserDName((String)objArr[13]);
			branchesPrivDTO.setAddUserFName((String)objArr[14]);
			branchesPrivDTO.setModifyUserDName((String)objArr[15]);
			branchesPrivDTO.setModifyUserFName((String)objArr[16]);
			branchesPrivDTO.setBranchDName((String)objArr[17]);
			branchesPrivDTO.setBranchFName((String)objArr[18]);
			// set branchesPrivDTO data
			branchesPrivDTOList.add(branchesPrivDTO);
		}
//		return branchesPrivDTOList;
		return null;
	}

}
