package com.expertsvision.erp.masterdata.currency.dao;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.utils.GenerateSql;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.currency.dto.CurrencyViewFilter;
import com.expertsvision.erp.masterdata.currency.entity.Currency;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyHistory;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyHistoryView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyValue;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyValuePK;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyValuesView;
import com.expertsvision.erp.masterdata.currency.entity.CurrencyView;

@Repository 	
public class CurrencyDAOImpl implements CurrencyDAO {


	@Autowired
	private SessionFactory sessionFactory;
	
	
	@Override
	public List<CurrencyView> getAllCurrencyViewList() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM currency_view";
		Query<CurrencyView> query = session.createNativeQuery(sql, CurrencyView.class);
		List<CurrencyView> currencyViewList = query.getResultList();
		return currencyViewList;
	}
	
	@Override
	public MultiplePages<CurrencyHistoryView> getCurrencyHistoryViewList(String currencyCode, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CurrencyHistoryView> currencyHistoryViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  currency_history_view WHERE currency_code = :currencyCode LIMIT 30 OFFSET :Offset";
			Query<CurrencyHistoryView> query = session.createNativeQuery(sql, CurrencyHistoryView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			query.setParameter("currencyCode", currencyCode);
			currencyHistoryViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM currency_history_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || currencyHistoryViewList.isEmpty()) {
			return new MultiplePages<CurrencyHistoryView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CurrencyHistoryView>(currencyHistoryViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	
	@Override
	public MultiplePages<CurrencyValuesView> getCurrencyValuesViewList(String currencyCode, long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CurrencyValuesView> currencyValuesViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  currency_values_view WHERE currency_code = :currencyCode LIMIT 30 OFFSET :Offset";
			Query<CurrencyValuesView> query = session.createNativeQuery(sql, CurrencyValuesView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			query.setParameter("currencyCode", currencyCode);
			currencyValuesViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM currency_values_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || currencyValuesViewList.isEmpty()) {
			return new MultiplePages<CurrencyValuesView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CurrencyValuesView>(currencyValuesViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public CurrencyView getCurrencyView(String currencyCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM currency_view WHERE currency_code = :currencyCode";
		Query<CurrencyView> query = session.createNativeQuery(sql, CurrencyView.class);
		query.setParameter("currencyCode", currencyCode);
		List<CurrencyView> currencyViewList = query.getResultList();
		return currencyViewList.isEmpty()? null : currencyViewList.get(0);
	}
	
	@Override
	public SinglePage<CurrencyView> getCurrencyViewSinglePage(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CurrencyView> currencyViewList =null;
		if (pageNo > 0) {
			sql = "SELECT * FROM currency_view LIMIT 1 OFFSET :Offset";
			Query<CurrencyView> query = session.createNativeQuery(sql, CurrencyView.class);
			query.setParameter("Offset", pageNo - 1);
			currencyViewList = query.getResultList();
		}
		if (pageNo <= 0 || currencyViewList.isEmpty()) {
			sql = "SELECT COUNT(*) FROM currency_view";
			@SuppressWarnings("unchecked")
			Query<BigInteger> query2 = session.createNativeQuery(sql);
			long count = query2.getSingleResult().longValue();
			return new SinglePage<CurrencyView>(null, pageNo, count);
		} else {
			return new SinglePage<CurrencyView>(currencyViewList.get(0), pageNo, null);
		}
	}
	
	@Override
	public SinglePage<CurrencyView> getCurrencyViewLastPage() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM currency_view ORDER BY(currency_code) DESC LIMIT 1";
		Query<CurrencyView> query = session.createNativeQuery(sql, CurrencyView.class);
		List<CurrencyView> currencyViewList = query.getResultList();
		sql = "SELECT COUNT(*) FROM currency_view";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (currencyViewList.isEmpty()) {
			return new SinglePage<CurrencyView>(null, count, count);
		} else {
			return new SinglePage<CurrencyView>(currencyViewList.get(0), count, count);
		}
	}
	
	@Override
	public Long getCurrencyViewSinglePageNo(String currencyCode) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT row_number FROM" +
					 "			(SELECT currency_code, ROW_NUMBER()" +
					 "						OVER(ORDER BY (currency_code)) FROM currency_view)" +
					 "			AS row_number " +
					 "WHERE currency_code = :currencyCode";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query = session.createNativeQuery(sql);
		query.setParameter("currencyCode", currencyCode);
		List<BigInteger> singlePageNoList = query.getResultList();
		return singlePageNoList.isEmpty() ? null : singlePageNoList.get(0).longValue();
	}
	
	@Override
	public MultiplePages<CurrencyView> getCurrencyViewMultiplePages(long pageNo) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CurrencyView> currencyViewList = null;
		if (pageNo > 0) {
			sql = "SELECT * FROM  currency_view LIMIT 30 OFFSET :Offset";
			Query<CurrencyView> query = session.createNativeQuery(sql, CurrencyView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			currencyViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM currency_view AS foo";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || currencyViewList.isEmpty()) {
			return new MultiplePages<CurrencyView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CurrencyView>(currencyViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public MultiplePages<CurrencyView> getCurrencyViewFilteredMultiplePages(long pageNo, CurrencyViewFilter currencyViewFilter) {
		Session session = sessionFactory.getCurrentSession();
		String sql = null;
		List<CurrencyView> usersViewList = null;
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("currency_code", currencyViewFilter.getCurrencyCode());
		filters.put("currency_d_name", currencyViewFilter.getCurrencyDName());
		filters.put("currency_f_name", currencyViewFilter.getCurrencyFName());
		filters.put("exchange_rate", currencyViewFilter.getExchangeRate());
		filters.put("fraction_d_name", currencyViewFilter.getFractionDName());
		filters.put("fraction_f_name", currencyViewFilter.getFractionFName());
		String filterQuery = GenerateSql.generateFilterQuery("currency_view", filters);
		if (pageNo > 0) {
			sql = filterQuery + " LIMIT 30 OFFSET :Offset";
			Query<CurrencyView> query = session.createNativeQuery(sql, CurrencyView.class);
			query.setParameter("Offset", (pageNo - 1) * 30);
			usersViewList = query.getResultList();
		}
		sql = "SELECT COUNT(*) FROM (" + filterQuery + ") As filteredRowsCount";
		@SuppressWarnings("unchecked")
		Query<BigInteger> query2 = session.createNativeQuery(sql);
		long count = query2.getSingleResult().longValue();
		if (pageNo <= 0 || usersViewList.isEmpty()) {
			return new MultiplePages<CurrencyView>(null, pageNo, (long)Math.ceil(count/30.0));
		} else {
			return new MultiplePages<CurrencyView>(usersViewList, pageNo, (long)Math.ceil(count/30.0));
		}
	}
	
	@Override
	public void addCurrency(Currency currency, List<CurrencyValue> currencyValueList) {
		Session session = sessionFactory.getCurrentSession();
		session.save(currency);
		session.flush();
		for (CurrencyValue obj: currencyValueList) {
			session.save(obj);
		}
		session.flush();
	}
	
	@Override
	public void updateCurrency(Currency currency, List<CurrencyValue> CurrencyValueForAddList,
			List<CurrencyValue> CurrencyValueForModifyList, List<CurrencyValue> CurrencyValueForDeleteList,
			CurrencyHistory CurrencyHistoryForAdd) {
		Session session = sessionFactory.getCurrentSession();
		Currency DBCurrency = session.get(Currency.class, currency.getCurrencyCode());
		DBCurrency.setCurrencyDName(currency.getCurrencyDName());
		DBCurrency.setCurrencyFName(currency.getCurrencyFName());
		DBCurrency.setExchangeRate(currency.getExchangeRate());
		DBCurrency.setFractionDName(currency.getFractionDName());
		DBCurrency.setFractionFName(currency.getFractionFName());
		DBCurrency.setFractionNo(currency.getFractionNo());
		DBCurrency.setLocalCurrency(currency.getLocalCurrency());
		DBCurrency.setMaxExRate(currency.getMaxExRate());
		DBCurrency.setMinExRate(currency.getMinExRate());
		DBCurrency.setModifyDate(currency.getModifyDate());
		DBCurrency.setModifyUser(currency.getModifyUser());
		DBCurrency.setPosExRate(currency.getPosExRate());
		session.merge(DBCurrency);
		session.flush();
		// Add the details
		for (CurrencyValue obj : CurrencyValueForAddList) {
			session.save(obj);
		}
		if (CurrencyHistoryForAdd != null)
			session.save(CurrencyHistoryForAdd);
		// Modify the details
		CurrencyValue DBCurrencyValue;
		for (CurrencyValue obj : CurrencyValueForModifyList) {
			DBCurrencyValue = session.get(CurrencyValue.class,
					new CurrencyValuePK(obj.getCurrencyCode(), obj.getValue()));
			DBCurrencyValue.setModifyDate(obj.getModifyDate());
			DBCurrencyValue.setModifyUser(obj.getModifyUser());
			DBCurrencyValue.setValue(obj.getValue());
			session.merge(DBCurrencyValue);
		}
		// Delete the details
		String sql = "DELETE FROM currency_values WHERE currency_code = :currencyCode AND value = :value";
		Query<?> query = session.createNativeQuery(sql);
		for (CurrencyValue obj : CurrencyValueForDeleteList) {
			query.setParameter("currencyCode", obj.getCurrencyCode());
			query.setParameter("currencyCode", obj.getValue());
			query.executeUpdate();
		}
		session.flush();
	}
	
	@Override
	public void deleteCurrency(String currencyCode) {
		Session session = sessionFactory.getCurrentSession();
		Currency DBCurrency = session.get(Currency.class, currencyCode);
		session.delete(DBCurrency);
		session.flush();
	}
	
	
	@Override
	public CurrencyView getLocalCurrency() {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM currency_view WHERE local_currency = true";
		Query<CurrencyView> query = session.createNativeQuery(sql, CurrencyView.class);
		List<CurrencyView> currencyViewList = query.getResultList();
		return currencyViewList.isEmpty()? null : currencyViewList.get(0);
	}

}
