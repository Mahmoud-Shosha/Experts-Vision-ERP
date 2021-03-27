package com.expertsvision.erp.core.label.dao;

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

import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsViewPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
@Transactional
public class LabelsViewDAOImpl implements LabelsViewDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LabelsView> getLabelsViewList() {		
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_labels_view_list()";
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> LabelsViewList = query.getResultList();
		return LabelsViewList;
	}
	
	
	@Override
	public LabelsView getLabelsView(LabelsViewPK labelsViewPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_labels_view('%s', %s)";
		sql = String.format(sql, labelsViewPK.getLabelCode(), labelsViewPK.getLangNo());
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> labelsViewList = query.getResultList();
		return labelsViewList.isEmpty()? null : labelsViewList.get(0);
	}
	
	@Override
	public SinglePage<LabelsView> getLabelsViewSinglePage( long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_labels_view_single_page(%s)";
		sql = String.format(sql, pageNo);
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> labelsViewList = query.getResultList();
		if (labelsViewList.isEmpty()) {
			sql = "SELECT * FROM get_table_rows_count('%s')";
			sql = String.format(sql, "labels");
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<LabelsView>(null, pageNo, count);
		} else {
			return new SinglePage<LabelsView>(labelsViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<LabelsView> getLabelsViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_labels_view_last_page()";
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> labelsViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "labels");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (labelsViewList.isEmpty()) {
			return new SinglePage<LabelsView>(null, count, count);
		} else {
			return new SinglePage<LabelsView>(labelsViewList.get(0), count, count);
		}
	}
	
	@Override
	public MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_labels_view_multiple_pages(%s)";
		sql = String.format(sql, pageNo);
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> labelsViewList = query.getResultList();
		sql = "SELECT * FROM get_table_rows_count('%s')";
		sql = String.format(sql, "labels");
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (labelsViewList.isEmpty()) {
			return new MultiplePages<LabelsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LabelsView>(labelsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("label_code", LabelViewFilter.getLabelCode());
		filters.put("lang_no", LabelViewFilter.getLangNo());
		filters.put("label_desc", LabelViewFilter.getLabelDesc());
		String filterQuery = GenerateSql.generateFilterQuery("labels_view", filters);
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM get_labels_view_filtered_multiple_pages(%s, '%s')";
		sql = String.format(sql, pageNo, filterQuery);
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> labelsViewList = query.getResultList();
		sql = "SELECT * FROM get_query_rows_count('%s')";
		sql = String.format(sql, filterQuery);
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (labelsViewList.isEmpty()) {
			return new MultiplePages<LabelsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LabelsView>(labelsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public List<String> addLabelsView(UsersView loginUser, LabelsView labelsView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(add_labels_view(%s, %s, '%s', '%s'))";
		sql = String.format(sql, loginUser.getUserId(), labelsView.getLangNo(), labelsView.getLabelDesc(),
								 labelsView.getLabelCode());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> updateLabelsView(UsersView loginUser, LabelsView labelsView) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(update_labels_view(%s, %s, '%s', '%s'))";
		sql = String.format(sql, loginUser.getUserId(), labelsView.getLangNo(), labelsView.getLabelDesc(),
								 labelsView.getLabelCode());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}
	
	@Override
	public List<String> deleteLabelsView(UsersView loginUser, LabelsViewPK labelsViewPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT UNNEST(delete_labels_view(%s, '%s', %s))";
		sql = String.format(sql, loginUser.getUserId(), labelsViewPK.getLabelCode(), labelsViewPK.getLangNo());
		javax.persistence.Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<String> validationList = query.getResultList();
		return validationList;
	}

}
