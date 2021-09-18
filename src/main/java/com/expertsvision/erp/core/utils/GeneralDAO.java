package com.expertsvision.erp.core.utils;

import java.util.Map;
import java.util.Set;

public interface GeneralDAO {
		
	Boolean isEntityExist(String tableName, Map<String, Object> conditions);
	
	Boolean isEntityExist(String tableName, Map<String, Object> conditions, String exceptCondition);
	
	void runEntityQuery(String tableName, Map<String, Object> setters, Map<String, Object> conditions);
	
	<T> Set<T> getThemIfExist(String tableName, String whr, Map<String, Object> parameters, String columnName, Set<T> columnValues); 
	
	<T> Set<T> getThemIfExist(String tableName, String columnName, Set<T> columnValues);
	
}
