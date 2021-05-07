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

import com.expertsvision.erp.core.module.dto.ModulesViewFilter;
import com.expertsvision.erp.core.module.entity.ModulesView;
import com.expertsvision.erp.core.module.entity.Module;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class ModulesDAOImpl implements ModulesDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<ModulesView> getModulesViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM modules_view";
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		return modulesViewList;
	}
	
	@Override
	public ModulesView getModulesView(Integer moduleNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM modules_view WHERE module_no = :moduleNo";
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		query.setParameter("moduleNo", moduleNo);
		List<ModulesView> modulesViewList = query.getResultList();
		return modulesViewList.isEmpty()? null : modulesViewList.get(0);
	}
	
	@Override
	public SinglePage<ModulesView> getModulesViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ModulesView> modulesViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM modules_view LIMIT 1 OFFSET :Offset";
			Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
			query.setParameter("Offset", pageNo - 1);
			modulesViewList = query.getResultList();
		}
		if (pageNo <= 0 || modulesViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM modules";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<ModulesView>(null, pageNo, count);
		} else {
			return new SinglePage<ModulesView>(modulesViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<ModulesView> getModulesViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM modules_view ORDER BY(order_no) DESC LIMIT 1";
		Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
		List<ModulesView> modulesViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM modules";
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
	public Long getModulesViewSinglePageNo(Integer moduleNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT module_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (order_no)) FROM modules)" +
					 "			AS row_number " +
					 "WHERE module_no = :moduleNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("moduleNo", moduleNo);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<ModulesView> getModulesViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ModulesView> modulesViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM modules_view LIMIT 30 OFFSET :Offset";
			Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			modulesViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM modules";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || modulesViewList.isEmpty()) {
			return new MultiplePages<ModulesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ModulesView>(modulesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<ModulesView> getModulesViewFilteredMultiplePages(long pageNo, ModulesViewFilter ModuleViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<ModulesView> modulesViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("active", ModuleViewFilter.getActive());
		filters.put("module_d_name", ModuleViewFilter.getModuleDName());
		filters.put("module_f_name", ModuleViewFilter.getModuleFName());
		filters.put("module_no", ModuleViewFilter.getModuleNo());
		filters.put("shortcut", ModuleViewFilter.getShortcut());
		String filterQuery = GenerateSql.generateFilterQuery("modules_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<ModulesView> query = session.createNativeQuery(sql, ModulesView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			modulesViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || modulesViewList.isEmpty()) {
			return new MultiplePages<ModulesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<ModulesView>(modulesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	
	@Override
	public void updateModules(Module module) {
		Session session = sessionFactory.getCurrentSession();
		Module DBModule = session.get(Module.class, module.getModuleNo());
		DBModule.setActive(module.getActive());
		DBModule.setModuleDName(module.getModuleDName());
		DBModule.setModuleFName(module.getModuleFName());
		DBModule.setModuleNo(module.getModuleNo());
		DBModule.setOrderNo(module.getOrderNo());
		DBModule.setShortcut(module.getShortcut());
		session.merge(DBModule);
		session.flush();
	}

}
