package com.expertsvision.erp.systemcommands.server.service;

import com.expertsvision.erp.core.user.entity.UsersView;

public interface ServerService {
	
	void reloadServerCache(UsersView loginUser);

}
