package com.expertsvision.erp.core.utils;

import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

import org.postgresql.core.Utils;

import com.expertsvision.erp.core.exception.UnauthorizedException;

public class GenerateSql {
	

	public static String  generateFilterQuery(String tableName, Map<String, Object> filters) {
		String query = "SELECT * FROM " + tableName + " ";
		Set<String> mapKeysSet = filters.keySet();
		Object value;
		if (!mapKeysSet.isEmpty()) query += "WHERE";
		for (String key : mapKeysSet) {
			try {
				value = Utils.escapeLiteral(null, filters.get(key)==null?"":filters.get(key).toString().strip(), true);
			} catch (SQLException e) {
				 throw new UnauthorizedException("resource");
			}
			query += " CAST (" + key + " AS VARCHAR) " + "ILIKE '%%" + value + "%%' AND";
		}
		query = query.substring(0, query.length() - 3);
		return query;
	}
	


}
