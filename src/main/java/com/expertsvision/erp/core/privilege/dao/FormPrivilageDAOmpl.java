package com.expertsvision.erp.core.privilege.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.privilege.entity.FormPrivilage;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;

@Repository 	
public class FormPrivilageDAOmpl implements FormPrivilageDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	// Refactor the following to functions ....
	
	@Override
	public List<FormPrivilageView> getFormPrivilageViewList() {		
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM form_privilage_view";
		Query<FormPrivilageView> query = session.createNativeQuery(sql, FormPrivilageView.class);
		List<FormPrivilageView> formPrivilageViewList = query.getResultList();
		return formPrivilageViewList;
	}
	
	@Override
	public List<FormPrivilageView> getFormPrivilageViewList(Integer userId) {		
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM form_privilage_view WHERE user_id = " + userId;
		Query<FormPrivilageView> query = session.createNativeQuery(sql, FormPrivilageView.class);
		List<FormPrivilageView> formPrivilageViewList = query.getResultList();
		return formPrivilageViewList;
	}
//	
//	@Override
//	public LabelsView getLabelsView(LabelsViewPK labelsViewPK) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT * FROM labels_view WHERE label_code = :labelCode AND lang_no = :langNo";
//		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
//		query.setParameter("labelCode", labelsViewPK.getLabelCode());
//		query.setParameter("langNo", labelsViewPK.getLangNo());		
//		List<LabelsView> labelsViewList = query.getResultList();
//		return labelsViewList.isEmpty()? null : labelsViewList.get(0);
//	}
//	
//	@Override
//	public Long getLabelsViewSinglePageNo(LabelsViewPK labelsViewPK) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT row_number FROM" +
//					 "			(SELECT label_code, lang_no, ROW_NUMBER()" +
//					 "						OVER(ORDER BY (label_code, lang_no)) FROM labels)" +
//					 "			AS row_number " +
//					 "WHERE label_code = :labelCode AND lang_no = :langNo";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query = session.createNativeQuery(sql);
//		query.setParameter("labelCode", labelsViewPK.getLabelCode());
//		query.setParameter("langNo", labelsViewPK.getLangNo());			
//		List<BigInteger> singlePageNoList = query.getResultList();
//		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
//	}
//	
//	@Override
//	public SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = null;
//		List<LabelsView> labelsViewList = null;
//		if (pageNo > 0) {
//			sql = "SELECT * FROM labels_view LIMIT 1 OFFSET :Offset";
//			Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
//			query.setParameter("Offset", pageNo - 1);
//			labelsViewList = query.getResultList();
//		}
//		if (pageNo <= 0 || labelsViewList.isEmpty()) {
//			sql = "SELECT COUNT(*) FROM labels";
//			@SuppressWarnings("unchecked")
//			Query<BigInteger> query2 = session.createNativeQuery(sql);
//			long count = query2.getSingleResult().longValue();
//			return new SinglePage<LabelsView>(null, pageNo, count);
//		} else {
//			return new SinglePage<LabelsView>(labelsViewList.get(0), pageNo, null);
//		}
//	}
//	
//	@Override
//	public SinglePage<LabelsView> getLabelsViewLastPage() {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT * FROM labels_view DESC limit 1";
//		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
//		List<LabelsView> labelsViewList = query.getResultList();
//		sql = "SELECT COUNT(*) FROM labels";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query2 = session.createNativeQuery(sql);
//		long count = query2.getSingleResult().longValue();
//		if (labelsViewList.isEmpty()) {
//			return new SinglePage<LabelsView>(null, count, count);
//		} else {
//			return new SinglePage<LabelsView>(labelsViewList.get(0), count, count);
//		}
//	}
//	
//	@Override
//	public MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = null;
//		List<LabelsView> labelsViewList = null;
//		if (pageNo > 0) {
//			sql = "SELECT * FROM labels_view LIMIT 30 OFFSET :Offset";
//			Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
//			query.setParameter("Offset", (pageNo - 1) * 30);
//			labelsViewList = query.getResultList();
//		}
//		sql = "SELECT COUNT(*) FROM labels";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query2 = session.createNativeQuery(sql);
//		long count = query2.getSingleResult().longValue();
//		if (pageNo <= 0 || labelsViewList.isEmpty()) {
//			return new MultiplePages<LabelsView>(null, pageNo, (long)Math.ceil(count/30.0));
//		} else {
//			return new MultiplePages<LabelsView>(labelsViewList, pageNo, (long)Math.ceil(count/30.0));
//		}
//	}
//	
//	@Override
//	public MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter) {
//		Map<String, Object> filters = new HashMap<String, Object>();
//		// later, you have to reject sql injection
//		filters.put("label_code", LabelViewFilter.getLabelCode());
//		filters.put("lang_no", LabelViewFilter.getLangNo());
//		filters.put("label_desc", LabelViewFilter.getLabelDesc());
//		String filterQuery = GenerateSql.generateFilterQuery("labels_view", filters);
//		Session session = sessionFactory.getCurrentSession();
//		String sql = null;
//		List<LabelsView> labelsViewList = null;
//		if (pageNo > 0) {
//			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
//			Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
//			query.setParameter("Offset", (pageNo - 1) * 30);
//			labelsViewList = query.getResultList();
//		}
//		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
//		@SuppressWarnings("unchecked")
//		Query<BigInteger> query2 = session.createNativeQuery(sql);
//		long count = query2.getSingleResult().longValue();
//		if (pageNo <= 0 || labelsViewList.isEmpty()) {
//			return new MultiplePages<LabelsView>(null, pageNo, (long)Math.ceil(count/30.0));
//		} else {
//			return new MultiplePages<LabelsView>(labelsViewList, pageNo, (long)Math.ceil(count/30.0));
//		}
//	}
//	
//	@Override
//	public List<String> addLabelsView(UsersView loginUser, LabelsView labelsView) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT UNNEST(add_labels_view(%s, %s, '%s', '%s'))";
//		sql = String.format(sql, loginUser.getUserId(), labelsView.getLangNo(), labelsView.getLabelDesc(),
//								 labelsView.getLabelCode());
//		javax.persistence.Query query = session.createNativeQuery(sql);
//		@SuppressWarnings("unchecked")
//		List<String> validationList = query.getResultList();
//		return validationList;
//	}
//	
//	@Override
//	public List<String> updateLabelsView(UsersView loginUser, LabelsView labelsView) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT UNNEST(update_labels_view(%s, %s, '%s', '%s'))";
//		sql = String.format(sql, loginUser.getUserId(), labelsView.getLangNo(), labelsView.getLabelDesc(),
//								 labelsView.getLabelCode());
//		javax.persistence.Query query = session.createNativeQuery(sql);
//		@SuppressWarnings("unchecked")
//		List<String> validationList = query.getResultList();
//		return validationList;
//	}
//	
//	@Override
//	public List<String> deleteLabelsView(UsersView loginUser, LabelsViewPK labelsViewPK) {
//		Session session = sessionFactory.getCurrentSession();
//		String sql = "SELECT UNNEST(delete_labels_view(%s, '%s', %s))";
//		sql = String.format(sql, loginUser.getUserId(), labelsViewPK.getLabelCode(), labelsViewPK.getLangNo());
//		javax.persistence.Query query = session.createNativeQuery(sql);
//		@SuppressWarnings("unchecked")
//		List<String> validationList = query.getResultList();
//		return validationList;
//	}
	
	@Override
	public void addBulkFormPrivilage(List<FormPrivilage> prvsList) {
		if (prvsList.isEmpty()) return;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO form_privilage (user_id, form_no, include_priv, add_priv, modify_priv," + 
				   "                            delete_priv, view_priv, print_priv, audit_priv, post_priv," + 
				   "							add_user, add_date, modify_user, modify_date)" + 
				   "	        VALUES ");
		for (FormPrivilage prv : prvsList) {
			sql.append("(");
			sql.append(prv.getUserId());
			sql.append(", ");
			sql.append(prv.getFormNo());
			sql.append(", ");
			sql.append(prv.getIncludePriv());
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
			sql.append(prv.getAuditPriv());
			sql.append(", ");
			sql.append(prv.getPostPriv());
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
	public void updateBulkFormPrivilage(List<FormPrivilage> prvsList) {
		if (prvsList.isEmpty()) return;
		StringBuilder sql = new StringBuilder();
		for (FormPrivilage prv : prvsList) {
			sql.append("UPDATE form_privilage SET ");
			sql.append("include_priv=");
			sql.append(prv.getIncludePriv());
			sql.append(", add_priv=");
			sql.append(prv.getAddPriv());
			sql.append(", modify_priv=");
			sql.append(prv.getModifyPriv());
			sql.append(", delete_priv=");
			sql.append(prv.getDeletePriv());
			sql.append(", view_priv=");
			sql.append(prv.getViewPriv());
			sql.append(", print_priv=");
			sql.append(prv.getPrintPriv());
			sql.append(", audit_priv=");
			sql.append(prv.getAuditPriv());
			sql.append(", post_priv=");
			sql.append(prv.getPostPriv());
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
			sql.append(" AND form_no=");
			sql.append(prv.getFormNo());
			sql.append(";");
		}
		Session session = sessionFactory.getCurrentSession();
		javax.persistence.Query query = session.createNativeQuery(sql.toString());
		query.executeUpdate();
		session.flush();
	}

	
	@Override
	public void deleteBulkFormPrivilage(Integer userId) {		
		Session session = sessionFactory.getCurrentSession();
		String sql = "DELETE FROM form_privilage WHERE user_id = :userId";
		Query<FormPrivilageView> query = session.createNativeQuery(sql, FormPrivilageView.class);
		query.setParameter("userId", userId);
		query.executeUpdate();
		session.flush();
	}
	
}
