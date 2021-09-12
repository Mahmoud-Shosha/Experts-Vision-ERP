package com.expertsvision.erp.core.usersgroups.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.usersgroups.entity.UsersGroupsView;

@Service
@Lazy
public class InMemoryUsersGroupsService {

	private UsersGroupsService usersGroupsService;

	private Map<Integer, UsersGroupsView> usersGroupsViewMap;

	@Autowired
	public InMemoryUsersGroupsService(UsersGroupsService usersGroupsService) {
		this.usersGroupsService = usersGroupsService;
		List<UsersGroupsView> usersGroupsViewList = usersGroupsService.getAllUsersGroupsViewList();
		Map<Integer, UsersGroupsView> newUsersGroupsViewMap = convertToUsersGroupsMap(usersGroupsViewList);
		synchronized (this) {
			usersGroupsViewMap = newUsersGroupsViewMap;
		}
	}

	public UsersGroupsView getUsersGroupsView(Integer groupNo) {
		UsersGroupsView usersGroupsView = usersGroupsViewMap.get(groupNo);
		return usersGroupsView;
	}
	
	public Set<Integer> getAdminGroupNoList() {
		Set<Integer> adminGroupNoList = new HashSet<>();
		for (Integer groupNo : usersGroupsViewMap.keySet()) {
			if (usersGroupsViewMap.get(groupNo).getAdminGroup())
				adminGroupNoList.add(groupNo);
		}
		return adminGroupNoList;
	}

	public void updateUsersGroupsView() {
		List<UsersGroupsView> usersGroupsViewList = usersGroupsService.getAllUsersGroupsViewList();
		Map<Integer, UsersGroupsView> newUsersGroupsViewMap = convertToUsersGroupsMap(usersGroupsViewList);
		synchronized (this) {
			usersGroupsViewMap = newUsersGroupsViewMap;
		}
	}

	private Map<Integer, UsersGroupsView> convertToUsersGroupsMap(List<UsersGroupsView> usersGroupsViewList) {
		Map<Integer, UsersGroupsView> usersGroupsViewMap = new HashMap<Integer, UsersGroupsView>();
		for (UsersGroupsView usersGroupsView : usersGroupsViewList) {
			usersGroupsViewMap.put(usersGroupsView.getGroupNo(), usersGroupsView);
		}
		return usersGroupsViewMap;
	}

}
