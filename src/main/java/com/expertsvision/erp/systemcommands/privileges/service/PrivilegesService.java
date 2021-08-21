package com.expertsvision.erp.systemcommands.privileges.service;

import com.expertsvision.erp.core.user.entity.UsersView;

public interface PrivilegesService {
	
	void generateUngeneratedPrivileges(UsersView loginUser, boolean prv);

}
