package com.expertsvision.erp.core.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralConstants;

@Service
public class InMemoryUsersViewService {
	
	
	private UsersViewService usersViewService;
	
	private  Map<Integer, UsersView> usersViewMap;
	
	@Autowired
	public InMemoryUsersViewService(UsersViewService usersViewService) {
		this.usersViewService = usersViewService;
		List<UsersView> usersViewList = usersViewService.getUsersViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
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
		List<UsersView> usersViewList = usersViewService.getUsersViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
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
