package com.expertsvision.erp.core.flagpriv.dao;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.flagpriv.entity.FlagPriv;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivPK;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivView;

@Repository 	
public class FlagPrivDAOImpl implements FlagPrivDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FlagPrivView> getFlagPrivViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_priv_view";
		Query<FlagPrivView> query = session.createNativeQuery(sql, FlagPrivView.class);
		List<FlagPrivView> flagPrivViewList = query.getResultList();
		return flagPrivViewList;
	}
	
	@Override
	public List<FlagPrivView> getFlagPrivViewList(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM flag_priv_view WHERE user_id = :userId";
		Query<FlagPrivView> query = session.createNativeQuery(sql, FlagPrivView.class);
		query.setParameter("userId", userId);
		List<FlagPrivView> flagPrivViewList = query.getResultList();
		return flagPrivViewList;
	}

	@Override
	public FlagPriv getFlagPriv(FlagPrivPK FlagPrivPK) {
		Session session = sessionFactory.getCurrentSession();
		FlagPriv flagPriv = session.get(FlagPriv.class, FlagPrivPK);
		return flagPriv;
	}
	
	@Override
	public void addBulkFlagPriv(List<FlagPriv> prvsList) {
		if (prvsList.isEmpty())
			return;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO flag_priv (user_id, flag_code, flag_value, add_priv, modify_priv,"
				+ "                        delete_priv, view_priv, print_priv,"
				+ "						   add_user, add_date, modify_user, modify_date)" +
				"	        VALUES ");
		for (FlagPriv prv : prvsList) {
			sql.append("(");
			sql.append(prv.getUserId());
			sql.append(", '");
			sql.append(prv.getFlagCode());
			sql.append("', ");
			sql.append(prv.getFlagValue());
			sql.append(", ");
			sql.append(prv.getAddPriv());
			sql.append(", ");
			sql.append(prv.getModifyPriv());
			sql.append(", ");
			sql.append(prv.getDeletePriv());
			sql.append(", ");
			sql.append(prv.getViewPriv());
			sql.append(", ");
			sql.append(prv.getPrintPriv());
			sql.append(", ");
			sql.append(prv.getAddUser());
			sql.append(", '");
			sql.append(prv.getAddDate());
			sql.append("', ");
			sql.append("null");
			sql.append(", ");
			sql.append("null");
			sql.append("),");
		}
		sql.deleteCharAt(sql.lastIndexOf(","));
		Session session = sessionFactory.getCurrentSession();
		javax.persistence.Query query = session.createNativeQuery(sql.toString());
		query.executeUpdate();
		session.flush();
	}

	@Override
	public void updateBulkFlagPriv(List<FlagPriv> prvsList) {
		if (prvsList.isEmpty())
			return;
		StringBuilder sql = new StringBuilder();
		for (FlagPriv prv : prvsList) {
			sql.append("UPDATE flag_priv SET ");
			sql.append("add_priv=");
			sql.append(prv.getAddPriv());
			sql.append(", modify_priv=");
			sql.append(prv.getModifyPriv());
			sql.append(", delete_priv=");
			sql.append(prv.getDeletePriv());
			sql.append(", view_priv=");
			sql.append(prv.getViewPriv());
			sql.append(", print_priv=");
			sql.append(prv.getPrintPriv());;
			sql.append(", modify_user=");
			sql.append(prv.getModifyUser());
			if (prv.getModifyDate() != null) {
				sql.append(", modify_date='");
				sql.append(prv.getModifyDate());
				sql.append("' WHERE user_id=");
			} else {
				sql.append(", modify_date=");
				sql.append(prv.getModifyDate());
				sql.append(" WHERE user_id=");
			}
			sql.append(prv.getUserId());
			sql.append(" AND flag_code='");
			sql.append(prv.getFlagCode());
			sql.append("' AND flag_value=");
			sql.append(prv.getFlagValue());
			sql.append(";");
		}
		Session session = sessionFactory.getCurrentSession();
		javax.persistence.Query query = session.createNativeQuery(sql.toString());
		query.executeUpdate();
		session.flush();
	}

	@Override
	public void updateFlagPriv(FlagPriv prv) {
		Session session = sessionFactory.getCurrentSession();
		FlagPriv DBFlagPriv = session.get(FlagPriv.class,
				new FlagPrivPK(prv.getUserId(), prv.getFlagCode(), prv.getFlagValue()));
		DBFlagPriv.setAddPriv(prv.getAddPriv());
		DBFlagPriv.setDeletePriv(prv.getDeletePriv());
		DBFlagPriv.setModifyPriv(prv.getModifyPriv());
		DBFlagPriv.setPrintPriv(prv.getPrintPriv());
		DBFlagPriv.setViewPriv(prv.getViewPriv());
		DBFlagPriv.setModifyDate(prv.getModifyDate());
		DBFlagPriv.setModifyUser(prv.getModifyUser());
		session.merge(DBFlagPriv);
		session.flush();
	}

	@Override
	public void deleteBulkFlagPriv(Integer userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "DELETE FROM flag_priv WHERE user_id = :userId";
		Query<FlagPriv> query = session.createNativeQuery(sql, FlagPriv.class);
		query.setParameter("userId", userId);
		query.executeUpdate();
		session.flush();
	}

}
