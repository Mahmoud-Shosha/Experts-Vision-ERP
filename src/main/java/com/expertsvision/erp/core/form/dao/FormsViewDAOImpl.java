package com.expertsvision.erp.core.form.dao;

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

import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralConstants;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
@Transactional
public class FormsViewDAOImpl implements FormsViewDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FormsView> getFormsViewList(UsersView loginUser) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view_list(%s)";
		sql = String.format(sql, loginUser.getUserId());
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		return formsViewList;
	}
	
	@Override
	public FormsView getFormsView(UsersView loginUser, Integer formNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), formNo);
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		return formsViewList.isEmpty()? null : formsViewList.get(0);
	}
	
	@Override
	public Long getFormsViewSinglePageNo(UsersView loginUser, Integer formNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view_single_page_no(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), formNo);
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.get(0) == null ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public SinglePage<FormsView> getFormsViewSinglePage(UsersView loginUser, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view_single_page(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), pageNo);
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		if (formsViewList.isEmpty()) {
			sql = "SELECT * FROM get_table_rows_count('%s')";
			sql = String.format(sql, "forms");
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<FormsView>(null, pageNo, count);
		} else {
			return new SinglePage<FormsView>(formsViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<FormsView> getFormsViewLastPage(UsersView loginUser) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view_last_page(%s)";
		sql = String.format(sql, loginUser.getUserId());
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "forms");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (formsViewList.isEmpty()) {
			return new SinglePage<FormsView>(null, count, count);
		} else {
			return new SinglePage<FormsView>(formsViewList.get(0), count, count);
		}
	}
	
	@Override
	public MultiplePages<FormsView> getFormsViewMultiplePages(UsersView loginUser, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view_multiple_pages(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), pageNo);
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "forms");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (formsViewList.isEmpty()) {
			return new MultiplePages<FormsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<FormsView>(formsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<FormsView> getFormsViewFilteredMultiplePages(UsersView loginUser, long pageNo, FormsViewFilter FormViewFilter) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("active", FormViewFilter.getActive());
		filters.put("form_d_name", FormViewFilter.getFormDName());
		filters.put("form_f_name", FormViewFilter.getFormFName());
		filters.put("form_no", FormViewFilter.getFormNo());
		filters.put("main", FormViewFilter.getMain());
		filters.put("module_no", FormViewFilter.getModuleNo());
		String filterQuery = GenerateSql.generateFilterQuery("forms_view", filters);
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view_filtered_multiple_pages(%s, %s, '%s')";
		sql = String.format(sql, loginUser.getUserId(), pageNo, filterQuery);
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		sql = "SELECT * FROM get_query_rows_count('%s')";
		sql = String.format(sql, filterQuery);
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (formsViewList.isEmpty()) {
			return new MultiplePages<FormsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<FormsView>(formsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}

	
	@Override
	public List<FormsView> getFormsViewMainTree(UsersView loginUser) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_forms_view_main_tree(%s, %s)";
		sql = String.format(sql, loginUser.getUserId(), GeneralConstants.INTERNAL_SETTING_MODULE_ID);
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewMainTree = query.getResultList();
		return formsViewMainTree;
	}
	
	
	@Override
	public List<String> updateFormsView(UsersView loginUser, FormsView formsView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(update_forms_view(%s, %s, %s, '%s', '%s', %s, %s, %s, %s))";
		sql = String.format(sql, loginUser.getUserId(), formsView.getModuleNo(), formsView.getFormNo(),
														formsView.getFormDName(), formsView.getFormFName(),
														formsView.getParentForm(), formsView.getActive(),
														formsView.getFormOrder(), formsView.getMain());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}

}
