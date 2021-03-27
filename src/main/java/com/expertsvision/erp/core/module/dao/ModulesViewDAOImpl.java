package com.expertsvision.erp.core.module.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.module.dto.ModulesViewFilter;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
@Transactional
public class ModulesViewDAOImpl implements ModulesViewDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ModulesView> getModulesViewList(UsersView loginUser) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_modules_view_list(%s)";
		sql = String.format(sql, loginUser.getUserId());
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		return modulesViewList;
	}
	
	@Override
	public ModulesView getModulesView(UsersView loginUser, Integer moduleNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_modules_view(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), moduleNo);
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		return modulesViewList.isEmpty()? null : modulesViewList.get(0);
	}
	
	@Override
	public SinglePage<ModulesView> getModulesViewSinglePage(UsersView loginUser, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_modules_view_single_page(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), pageNo);
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		if (modulesViewList.isEmpty()) {
			sql = "SELECT * FROM get_table_rows_count('%s')";
			sql = String.format(sql, "modules");
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<ModulesView>(null, pageNo, count);
		} else {
			return new SinglePage<ModulesView>(modulesViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<ModulesView> getModulesViewLastPage(UsersView loginUser) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_modules_view_last_page(%s)";
		sql = String.format(sql, loginUser.getUserId());
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "modules");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (modulesViewList.isEmpty()) {
			return new SinglePage<ModulesView>(null, count, count);
		} else {
			return new SinglePage<ModulesView>(modulesViewList.get(0), count, count);
		}
	}
	
	@Override
	public MultiplePages<ModulesView> getModulesViewMultiplePages(UsersView loginUser, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_modules_view_multiple_pages(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), pageNo);
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "modules");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (modulesViewList.isEmpty()) {
			return new MultiplePages<ModulesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ModulesView>(modulesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<ModulesView> getModulesViewFilteredMultiplePages(UsersView loginUser, long pageNo, ModulesViewFilter ModuleViewFilter) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("active", ModuleViewFilter.getActive());
		filters.put("module_d_name", ModuleViewFilter.getModuleDName());
		filters.put("module_f_name", ModuleViewFilter.getModuleFName());
		filters.put("module_no", ModuleViewFilter.getModuleNo());
		filters.put("shortcut", ModuleViewFilter.getShortcut());
		String filterQuery = GenerateSql.generateFilterQuery("modules_view", filters);
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_modules_view_filtered_multiple_pages(%s, %s, '%s')";
		sql = String.format(sql, loginUser.getUserId(), pageNo, filterQuery);
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		sql = "SELECT * FROM get_query_rows_count('%s')";
		sql = String.format(sql, filterQuery);
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (modulesViewList.isEmpty()) {
			return new MultiplePages<ModulesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ModulesView>(modulesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	
	@Override
	public List<String> updateModulesView(UsersView loginUser, ModulesView modulesView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(update_modules_view(%s, %s, '%s', '%s', '%s', %s, %s))";
		sql = String.format(sql, loginUser.getUserId(), modulesView.getModuleNo(), modulesView.getModuleDName(),
														modulesView.getModuleFName(), modulesView.getShortcut(),
														modulesView.getActive(), modulesView.getOrderNo());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}

}
