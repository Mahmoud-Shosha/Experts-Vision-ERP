package com.expertsvision.erp.core.utils;

public enum FlagsActions {
	
	ADD("add_priv"),
	MODIFY("modify_priv"),
	DELETE("delete_priv"),
	VIEW("view_priv"),
	PRINT("print_priv");
	
	private String actionString;
	
	FlagsActions(String actionString) {
		this.actionString = actionString;
	}

	public String getActionString() {
		return actionString;
	}

}
