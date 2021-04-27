package com.expertsvision.erp.core.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.user.entity.UsersView;


@Service
@Lazy
public class InMemoryUsersService {
	
	
	private UsersService usersViewService;
	
	private  Map<Integer, UsersView> usersViewMap;
	
	@Autowired
	public InMemoryUsersService(UsersService usersViewService) {
		this.usersViewService = usersViewService;
		List<UsersView> usersViewList = usersViewService.getAllUsersViewList();
		Map<Integer, UsersView> NewUsersViewMap = convertToUserMap(usersViewList);
		synchronized (this) {
			usersViewMap = NewUsersViewMap;
		}
	}
	
	public UsersView getUsersView(Integer userId) {
		UsersView usersView = usersViewMap.get(userId);
		return usersView;
	}
	
	public void updateUsersView() {		
		List<UsersView> usersViewList = usersViewService.getAllUsersViewList();
		Map<Integer, UsersView> NewUsersViewMap = convertToUserMap(usersViewList);
		synchronized (this) {
			usersViewMap = NewUsersViewMap;
		}
	}
	
	private Map<Integer, UsersView> convertToUserMap(List<UsersView> usersViewList) {
		Map<Integer, UsersView> NewUsersViewMap = new HashMap<Integer, UsersView>();
		for (UsersView usersView : usersViewList) {
			NewUsersViewMap.put(usersView.getUserId(), usersView);
		}
		return NewUsersViewMap;
	}

}
