package com.expertsvision.erp.core.utils;

import java.util.Map;
import java.util.Set;

public class GenerateSql {
	
	public static String generateFilterQuery(String tableName, Map<String, Object> filters) {
		String query = "SELECT * FROM " + tableName + " ";
		Set<String> mapKeysSet = filters.keySet();
		Object value;
		if (!mapKeysSet.isEmpty()) query += "WHERE";
		for (String key : mapKeysSet) {
			value = filters.get(key);
			if (value == null) value = "";
			else if (value instanceof String) value = ((String)value).strip();
			query += " CAST (" + key + " AS VARCHAR) " + "ILIKE ''%%" + value + "%%'' AND";
		}
		query = query.substring(0, query.length() - 3);
		return query;
	}

}
