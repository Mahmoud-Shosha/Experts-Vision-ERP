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

import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.Label;
import com.expertsvision.erp.core.label.entity.LabelsPK;
import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

@Repository 	
public class LabelsDAOImpl implements LabelsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<LabelsView> getLabelsViewList() {		
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM labels_view";
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> LabelsViewList = query.getResultList();
		return LabelsViewList;
	}
	
	
	@Override
	public LabelsView getLabelsView(LabelsPK labelsViewPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM labels_view WHERE label_code = :labelCode AND lang_no = :langNo";
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		query.setParameter("labelCode", labelsViewPK.getLabelCode());
		query.setParameter("langNo", labelsViewPK.getLangNo());		
		List<LabelsView> labelsViewList = query.getResultList();
		return labelsViewList.isEmpty()? null : labelsViewList.get(0);
	}
	
	@Override
	public SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<LabelsView> labelsViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM labels_view LIMIT 1 OFFSET :Offset";
			Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
			query.setParameter("Offset", pageNo - 1);
			labelsViewList = query.getResultList();
		}
		if (pageNo <= 0 || labelsViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM labels";
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
		String sql = "SELECT * FROM labels_view ORDER BY (label_code, lang_no) DESC limit 1";
		Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
		List<LabelsView> labelsViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM labels";
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
	public Long getLabelsViewSinglePageNo(LabelsPK labelsViewPK) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT label_code, lang_no, ROW_NUMBER()" +
					 "						OVER(ORDER BY (label_code, lang_no)) FROM labels)" +
					 "			AS row_number " +
					 "WHERE label_code = :labelCode AND lang_no = :langNo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("labelCode", labelsViewPK.getLabelCode());
		query.setParameter("langNo", labelsViewPK.getLangNo());			
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<LabelsView> labelsViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM labels_view LIMIT 30 OFFSET :Offset";
			Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			labelsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM labels";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || labelsViewList.isEmpty()) {
			return new MultiplePages<LabelsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LabelsView>(labelsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<LabelsView> labelsViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("label_code", LabelViewFilter.getLabelCode());
		filters.put("lang_no", LabelViewFilter.getLangNo());
		filters.put("label_desc", LabelViewFilter.getLabelDesc());
		String filterQuery = GenerateSql.generateFilterQuery("labels_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<LabelsView> query = session.createNativeQuery(sql, LabelsView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			labelsViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || labelsViewList.isEmpty()) {
			return new MultiplePages<LabelsView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<LabelsView>(labelsViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addLabel(Label label) {
		Session session = sessionFactory.getCurrentSession();
		session.save(label);
	}
	
	@Override
	public void updateLabel(Label label) {
		Session session = sessionFactory.getCurrentSession();
		Label DBLabel = session.get(Label.class, new LabelsPK(label.getLangNo(), label.getLabelCode()));
		DBLabel.setLabelCode(label.getLabelCode());
		DBLabel.setLabelDesc(label.getLabelDesc());
		DBLabel.setLangNo(label.getLangNo());
		DBLabel.setModifyDate(label.getModifyDate());
		DBLabel.setModifyUser(label.getModifyUser());
		session.merge(DBLabel);
	}
	
	@Override
	public void deleteLabel(LabelsPK labelsViewPK) {
		Session session = sessionFactory.getCurrentSession();
		Label DBLabel = session.get(Label.class, labelsViewPK);
		session.delete(DBLabel);
	}

}
