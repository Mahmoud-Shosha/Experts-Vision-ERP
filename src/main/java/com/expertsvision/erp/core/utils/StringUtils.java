package com.expertsvision.erp.core.utils;

public class StringUtils {
	
	public static String getSnakeCase(String camelCase) {
        String regex = "([a-z])([A-Z]+)";
        String replacement = "$1_$2";
        String snakeCase = camelCase.replaceAll(regex, replacement).toLowerCase();
        return snakeCase;
	}

}
