package com.expertsvision.erp.masterdata.masterdataprivileges.dao;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository 	
public class MasterDataPrivilegesDAOImpl implements MasterDataPrivilegesDAO {


	@Autowired
	private SessionFactory sessionFactory;
	

	@Override
	public void addMasterDataPrivileges(Object masterDataPrivileges) {
		Session session = sessionFactory.getCurrentSession();
		session.save(masterDataPrivileges);
	}
	
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
	public void updateBulkMasterDataPrivileges(String str) {
		Session session = sessionFactory.getCurrentSession();
		javax.persistence.Query query = session.createNativeQuery(str);
		query.executeUpdate();
	}
	
}
