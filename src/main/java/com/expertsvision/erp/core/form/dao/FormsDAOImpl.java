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

import com.expertsvision.erp.core.form.dto.FormsViewFilter;
import com.expertsvision.erp.core.form.entity.Form;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralConstants;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class FormsDAOImpl implements FormsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<FormsView> getFormsViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM forms_view";
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		return formsViewList;
	}
	
	@Override
	public FormsView getFormsView(Integer formNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM forms_view WHERE form_no = :formNo";
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		query.setParameter("formNo", formNo);
		List<FormsView> formsViewList = query.getResultList();
		return formsViewList.isEmpty()? null : formsViewList.get(0);
	}
	
	@Override
	public SinglePage<FormsView> getFormsViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FormsView> formsViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM forms_view LIMIT 1 OFFSET :Offset";
			Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
			query.setParameter("Offset", pageNo - 1);
			formsViewList = query.getResultList();
		}
		if (pageNo <= 0 || formsViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM forms";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<FormsView>(null, pageNo, count);
		} else {
			return new SinglePage<FormsView>(formsViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<FormsView> getFormsViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM forms_view ORDER BY(form_order, form_no) DESC LIMIT 1";
		Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
		List<FormsView> formsViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM forms";
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
	public Long getFormsViewSinglePageNo(Integer formNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT form_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (form_order, form_no)) FROM forms)" +
					 "			AS row_number " +
					 "WHERE form_no = :formNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("formNo", formNo);			
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<FormsView> getFormsViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FormsView> formsViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM forms_view LIMIT 30 OFFSET :Offset";
			Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			formsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM forms";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || formsViewList.isEmpty()) {
			return new MultiplePages<FormsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<FormsView>(formsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<FormsView> getFormsViewFilteredMultiplePages(long pageNo, FormsViewFilter formViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<FormsView> formsViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("active", formViewFilter.getActive());
		filters.put("form_d_name", formViewFilter.getFormDName());
		filters.put("form_f_name", formViewFilter.getFormFName());
		filters.put("form_no", formViewFilter.getFormNo());
		filters.put("main", formViewFilter.getMain());
		filters.put("module_no", formViewFilter.getModuleNo());
		String filterQuery = GenerateSql.generateFilterQuery("forms_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<FormsView> query = session.createNativeQuery(sql, FormsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			formsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || formsViewList.isEmpty()) {
			return new MultiplePages<FormsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<FormsView>(formsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}

	
	@Override
	public List<FormsView> getFormsViewMainTree(UsersView loginUser) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		Query<FormsView> query = null;
		if (loginUser.getSuperAdmin()) {
			sql = "SELECT * FROM forms_view";
			query = session.createNativeQuery(sql, FormsView.class);
		} else if (loginUser.getAdminUser()) {
			sql = "SELECT * FROM forms_view " + 
				  "				WHERE module_no IN (SELECT module_no FROM modules WHERE active = true)" + 
				  "				  	  AND module_no != :INTERNAL_SETTING_MODULE_ID";
			query = session.createNativeQuery(sql, FormsView.class);
			query.setParameter("INTERNAL_SETTING_MODULE_ID", GeneralConstants.INTERNAL_SETTING_MODULE_ID);
		} else {
			sql = "SELECT forms_view.* FROM forms_view" + 
				  "					   LEFT JOIN form_privilage ON forms_view.form_no = form_privilage.form_no" + 
				  "					   LEFT JOIN modules ON forms_view.module_no = modules.module_no" + 
				  "					   WHERE forms_view.active = true AND form_privilage.user_id = :userId" + 
				  "							 AND form_privilage.include_priv = true AND modules.active = true" + 
				  "							 AND forms_view.module_no != :INTERNAL_SETTING_MODULE_ID";
			query = session.createNativeQuery(sql, FormsView.class);
			query.setParameter("userId", loginUser.getUserId());
			query.setParameter("INTERNAL_SETTING_MODULE_ID", GeneralConstants.INTERNAL_SETTING_MODULE_ID);
		}
		List<FormsView> formsViewMainTree = query.getResultList();
		return formsViewMainTree;
	}
	
	
	@Override
	public void updateForms(Form form) {
		Session session = sessionFactory.getCurrentSession();
		Form DBForm = session.get(Form.class, form.getFormNo());
		DBForm.setActive(form.getActive());
		DBForm.setFormDName(form.getFormDName());
		DBForm.setFormFName(form.getFormFName());
		DBForm.setFormOrder(form.getFormOrder());
		DBForm.setMain(form.getMain());
		DBForm.setModuleNo(form.getModuleNo());
		DBForm.setParentForm(form.getParentForm());
		session.merge(DBForm);
		session.flush();
	}

}
