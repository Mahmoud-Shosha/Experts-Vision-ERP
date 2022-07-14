package com.expertsvision.erp.core.shared.masterwithpriv;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Addable;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.Modifyable;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.utils.StringUtils;

public class MasterWithPrivDAO<MasterEntity, MasterEntityView> {

	@Autowired
	private SessionFactory sessionFactory;

	protected Class<MasterEntity> masterEntityClass;
	protected Class<MasterEntityView> masterEntityViewClass;
	protected String PKName;
	protected String orderByColumns;
	protected String MASTER_ENTITY_VIEW_WITH_PRIVS;

	public MasterEntityView getMasterEntityView(String PKValue) {
		Session session = sessionFactory.getCurrentSession();
		String masterEntityViewName = StringUtils.getSnakeCase(masterEntityViewClass.getSimpleName());
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String sql;
		Query<MasterEntityView> query;
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
			sql = "SELECT * FROM " + masterEntityViewName + " WHERE " + PKName + " = :" + PKName;
			query = session.createNativeQuery(sql, masterEntityViewClass);
		} else {
			sql = "SELECT * FROM (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r WHERE " + PKName + " = :" + PKName;
			query = session.createNativeQuery(sql, masterEntityViewClass);
			query.setParameter("userId", loginUser.getUserId());
		}
		query.setParameter(PKName, PKValue);
		List<MasterEntityView> masterEntityViewList = query.getResultList();
		return masterEntityViewList.isEmpty() ? null : masterEntityViewList.get(0);
	}

	public SinglePage<MasterEntityView> getMasterEntityViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String masterEntityViewName = StringUtils.getSnakeCase(masterEntityViewClass.getSimpleName());
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String sql = null;
		Query<MasterEntityView> query;
		List<MasterEntityView> masterEntityViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
				sql = "SELECT * FROM " + masterEntityViewName + " LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, masterEntityViewClass);
			} else {
				sql = "SELECT * FROM (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r LIMIT 1 OFFSET :Offset";
				query = session.createNativeQuery(sql, masterEntityViewClass);
				query.setParameter("userId", loginUser.getUserId());
			}
			query.setParameter("Offset", pageNo - 1);
			masterEntityViewList = query.getResultList();
		}
		if (pageNo <= 0 || masterEntityViewList.isEmpty()) {
			if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
				sql = "SELECT COUNT(*) FROM " + masterEntityViewName;
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				count = query2.getSingleResult().longValue();
			} else {
				sql = "SELECT COUNT(*) FROM (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r";
				@SuppressWarnings("unchecked")
				Query<BigInteger> query2 = session.createNativeQuery(sql);
				query2.setParameter("userId", loginUser.getUserId());
				count = query2.getSingleResult().longValue();
			}
			return new SinglePage<MasterEntityView>(null, pageNo, count);
		} else {
			return new SinglePage<MasterEntityView>(masterEntityViewList.get(0), pageNo, null);
		}
	}

	public SinglePage<MasterEntityView> getMasterEntityViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String masterEntityViewName = StringUtils.getSnakeCase(masterEntityViewClass.getSimpleName());
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String sql;
		Query<MasterEntityView> query;
		long count;
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
			sql = "SELECT * FROM " + masterEntityViewName + " ORDER BY(" + orderByColumns + ") DESC LIMIT 1";
			query = session.createNativeQuery(sql, masterEntityViewClass);
		} else {
			sql = "SELECT * FROM (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r ORDER BY(" + orderByColumns
					+ ") DESC LIMIT 1";
			query = session.createNativeQuery(sql, masterEntityViewClass);
			query.setParameter("userId", loginUser.getUserId());
		}
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
			sql = "SELECT COUNT(*) FROM " + masterEntityViewName;
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUser.getUserId());
			count = query2.getSingleResult().longValue();
		}
		List<MasterEntityView> masterEntityViewList = query.getResultList();
		if (masterEntityViewList.isEmpty()) {
			return new SinglePage<MasterEntityView>(null, count, count);
		} else {
			return new SinglePage<MasterEntityView>(masterEntityViewList.get(0), count, count);
		}
	}

	public Long getMasterEntityViewSinglePageNo(String PKValue) {
		Session session = sessionFactory.getCurrentSession();
		String masterEntityViewName = StringUtils.getSnakeCase(masterEntityViewClass.getSimpleName());
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String sql;
		List<BigInteger> singlePageNoList;
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
			sql = "SELECT row_number FROM (SELECT " + orderByColumns + ", ROW_NUMBER() " + "OVER(ORDER BY ("
					+ orderByColumns + ")) FROM " + masterEntityViewName + ") AS row_number " + "WHERE " + PKName
					+ " = :" + PKName;
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter(PKName, PKValue);
			singlePageNoList = query.getResultList();
		} else {
			sql = "SELECT row_number FROM (SELECT " + orderByColumns + ", ROW_NUMBER() " + "OVER(ORDER BY ("
					+ orderByColumns + ")) FROM (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r) AS row_number " + "WHERE "
					+ PKName + " = :" + PKName;
			@SuppressWarnings("unchecked")
			Query<BigInteger> query = session.createNativeQuery(sql);
			query.setParameter(PKName, PKValue);
			query.setParameter("userId", loginUser.getUserId());
			singlePageNoList = query.getResultList();
		}
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}

	public MultiplePages<MasterEntityView> getMasterEntityViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String masterEntityViewName = StringUtils.getSnakeCase(masterEntityViewClass.getSimpleName());
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String sql = null;
		Query<MasterEntityView> query;
		List<MasterEntityView> masterEntityViewList = null;
		long count;
		if (pageNo > 0) {
			if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
				sql = "SELECT * FROM " + masterEntityViewName + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, masterEntityViewClass);
			} else {
				sql = "SELECT * FROM  (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, masterEntityViewClass);
				query.setParameter("userId", loginUser.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			masterEntityViewList = query.getResultList();
		}
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
			sql = "SELECT COUNT(*) FROM " + masterEntityViewName + " AS foo";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			sql = "SELECT COUNT(*) FROM (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUser.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || masterEntityViewList.isEmpty()) {
			return new MultiplePages<MasterEntityView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<MasterEntityView>(masterEntityViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	public MultiplePages<MasterEntityView> getMasterEntityViewFilteredMultiplePages(long pageNo,
			Map<String, Object> filters) {
		Session session = sessionFactory.getCurrentSession();
		String masterEntityViewName = StringUtils.getSnakeCase(masterEntityViewClass.getSimpleName());
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String sql = null;
		Query<MasterEntityView> query;
		List<MasterEntityView> masterEntityViewList = null;
		long count;
		String filterQuery;
		if (pageNo > 0) {
			if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
				filterQuery = GenerateSql.generateFilterQuery(masterEntityViewName, filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, masterEntityViewClass);
			} else {
				filterQuery = GenerateSql.generateFilterQuery(" (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r ",
						filters);
				sql = filterQuery + " LIMIT 30 OFFSET :Offset";
				query = session.createNativeQuery(sql, masterEntityViewClass);
				query.setParameter("userId", loginUser.getUserId());
			}
			query.setParameter("Offset", (pageNo - 1) * 30);
			masterEntityViewList = query.getResultList();
		}
		if (loginUser.getAdminUser() || loginUser.getSuperAdmin()) {
			filterQuery = GenerateSql.generateFilterQuery(masterEntityViewName, filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			count = query2.getSingleResult().longValue();
		} else {
			filterQuery = GenerateSql.generateFilterQuery(" (" + MASTER_ENTITY_VIEW_WITH_PRIVS + ") AS r ", filters);
			sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			query2.setParameter("userId", loginUser.getUserId());
			count = query2.getSingleResult().longValue();
		}
		if (pageNo <= 0 || masterEntityViewList.isEmpty()) {
			return new MultiplePages<MasterEntityView>(null, pageNo, (long) Math.ceil(count / 30.0));
		} else {
			return new MultiplePages<MasterEntityView>(masterEntityViewList, pageNo, (long) Math.ceil(count / 30.0));
		}
	}

	public void addMasterEntity(Object masterEntity) {
		Session session = sessionFactory.getCurrentSession();
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Timestamp add_date = new Timestamp(new Date().getTime());
		if (masterEntity instanceof Addable) {
			Addable addable = (Addable) masterEntity;
			addable.setAddDate(add_date);
			addable.setAddUser(loginUser.getUserId());
		}
		session.save(masterEntity);
	}

	public void addMasterEntityPriv(List<?> masterEntityPrivList) {
		Session session = sessionFactory.getCurrentSession();
		Timestamp add_date = new Timestamp(new Date().getTime());
		for (Object priv : masterEntityPrivList) {
			if (priv instanceof Addable) {
				Addable addable = (Addable) priv;
				addable.setAddDate(add_date);
				addable.setAddUser(1);
			}
			if (priv instanceof Modifyable) {
				Modifyable modifyable = (Modifyable) priv;
				modifyable.setModifyDate(null);
				modifyable.setModifyUser(null);
			}
			session.save(priv);
		}
	}

	public void updateDBMasterEntity(MasterEntity DBMasterEntity) {
		Session session = sessionFactory.getCurrentSession();
		UsersView loginUser = (UsersView) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Timestamp update_date = new Timestamp(new Date().getTime());
		if (DBMasterEntity instanceof Modifyable) {
			Modifyable modifyable = (Modifyable) DBMasterEntity;
			modifyable.setModifyDate(update_date);
			modifyable.setModifyUser(loginUser.getUserId());
		}
		session.merge(DBMasterEntity);
	}

	public MasterEntity getDBMasterEntity(String PKValue) {
		Session session = sessionFactory.getCurrentSession();
		MasterEntity DBMasterEntity = session.get(masterEntityClass, PKValue);
		return DBMasterEntity;
	}

	public void deleteMasterEntity(String PKValue) {
		Session session = sessionFactory.getCurrentSession();
		MasterEntity DBMasterEntity = session.get(masterEntityClass, PKValue);
		session.delete(DBMasterEntity);
	}

}
