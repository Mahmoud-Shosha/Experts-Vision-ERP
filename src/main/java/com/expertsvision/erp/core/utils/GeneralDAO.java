package com.expertsvision.erp.core.utils;

import java.util.Map;

public interface GeneralDAO {
		
	Boolean isEntityExist(String tableName, Map<String, Object> conditions);
	
	Boolean isEntityExist(String tableName, Map<String, Object> conditions, String exceptCondition);
	
	void runEntityQuery(String tableName, Map<String, Object> setters, Map<String, Object> conditions);
		
}
