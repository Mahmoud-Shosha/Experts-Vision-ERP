package com.expertsvision.erp.core.utils;

public enum FormsActions {
	
	INCLUDE("include_priv"),
	ADD("add_priv"),
	MODIFY("modify_priv"),
	DELETE("delete_priv"),
	VIEW("view_priv"),
	PRINT("print_priv"),
	AUDIT("audit_priv"),
	POST("post_priv");
	
	private String actionString;
	
	FormsActions(String actionString) {
		this.actionString = actionString;
	}

	public String getActionString() {
		return actionString;
	}

}
