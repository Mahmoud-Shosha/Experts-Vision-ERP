package com.expertsvision.erp.core.utils;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.exception.UnauthorizedException;



@Repository
public class GeneralDAOImpl implements GeneralDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Boolean isEntityExist(String tableName, Map<String, Object> conditions) {
		return isEntityExist(tableName, conditions, null);
	}
	
	@Override
	public Boolean isEntityExist(String tableName, Map<String, Object> conditions, String exceptCondition) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "SELECT * FROM " + tableName +" WHERE ";
		Set<String> conditionsList =  conditions.keySet();
		Object value = null;
		if (conditionsList.isEmpty()) throw new RuntimeException("You should have at least one condition in the conditions");
		for (String condition : conditionsList) {
			value = conditions.get(condition);
			try {
				Long.parseLong(value.toString());
				try {
					sql += condition + " = " + Utils.escapeLiteral(null, value==null?"":value.toString().strip(), true) + " and ";
				} catch (SQLException e) {
					throw new UnauthorizedException("resource");
				}
			} catch (NumberFormatException e1) {
				try {
					Double.parseDouble(value.toString());
					try {
						sql += condition + " = " + Utils.escapeLiteral(null, value==null?"":value.toString().strip(), true) + " and ";
					} catch (SQLException e2) {
						throw new UnauthorizedException("resource");
					}
				} catch (NumberFormatException e3) {
					try {
						sql += condition + " = '" + Utils.escapeLiteral(null, value==null?"":value.toString().strip(), true) + "' and ";
					} catch (SQLException e2) {
						throw new UnauthorizedException("resource");
					}
				}
				
			}
			
		}
		sql = sql.substring(0, sql.length() - 4);
		sql += " " + ((exceptCondition == null) ? "" : exceptCondition);
		Query query = session.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object> resultList = query.getResultList();
		return !resultList.isEmpty();
	}

}
