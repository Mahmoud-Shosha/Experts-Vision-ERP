package com.expertsvision.erp.core.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.user.dao.UsersViewDAO;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.validation.CoreValidationService;


@Service
@Transactional
public class UsersViewServiceImpl implements UsersViewService {
	
	@Autowired
	private UsersViewDAO usersViewDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;
	
	
	@Override
	public List<UsersView> getUsersViewList(UsersView loginUser) {
		coreValidationService.activeModuleAndForm(Forms.USERS);
		List<UsersView> usersViewList = null;
		try {
			usersViewList = usersViewDAO.getUsersViewList(loginUser);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return usersViewList;
	}

	
	@Override
	public List<UsersView> getUsersViewSubordinateList(UsersView usersView) {
		coreValidationService.activeModuleAndForm(Forms.USERS);
		List<UsersView> usersViewList = null;
		try {
			usersViewList = usersViewDAO.getUsersViewSubordinateList(usersView);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return usersViewList;
	}
	
	
	@Override
	public UsersView getUsersView(UsersView loginUser, Integer userId) {
		coreValidationService.activeModuleAndForm(Forms.USERS);
		UsersView usersViewByUserId = null;
		try {
			usersViewByUserId = usersViewDAO.getUsersView(loginUser, userId);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		if (usersViewByUserId == null) {
			throw new ValidationException("not_exist", "user");
		}
		return usersViewByUserId;
	}
	
	
	@Override
	public void addUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES) {
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.notNull(usersView.getUserId(), "user_no");
		coreValidationService.greaterThanOrEqualZero(usersView.getUserId(), "user_no");
		coreValidationService.notNull(usersView.getUserDName(), "name");
		coreValidationService.notBlank(usersView.getUserDName(), "name");
		coreValidationService.notNull(usersView.getDirectMang(), "direct_manager");
		coreValidationService.greaterThanOrEqualZero(usersView.getDirectMang(), "direct_manager");
		coreValidationService.notNull(usersView.getPassword(), "password");
		coreValidationService.notBlank(usersView.getPassword(), "password");
		coreValidationService.runDatabaseValidation(usersViewDAO.addUsersView(loginUser, usersView, COPY_FROM_USER_PRIVILEDGES));	
	}
	
	@Override
	public void updateUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES,
								Integer COPY_PRIVILEGES_TO_GROUP, Boolean confirm) {
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.notNull(usersView.getUserId(), "user_no");
		coreValidationService.greaterThanOrEqualZero(usersView.getUserId(), "user_no");
		coreValidationService.notNull(usersView.getUserDName(), "name");
		coreValidationService.notBlank(usersView.getUserDName(), "name");
		coreValidationService.notNull(usersView.getDirectMang(), "direct_manager");
		coreValidationService.greaterThanOrEqualZero(usersView.getDirectMang(), "direct_manager");
		coreValidationService.notNull(usersView.getPassword(), "password");
		coreValidationService.notBlank(usersView.getPassword(), "password");
		coreValidationService.runDatabaseValidation(usersViewDAO.updateUsersView(loginUser, usersView,
																				 COPY_FROM_USER_PRIVILEDGES,
																				 COPY_PRIVILEGES_TO_GROUP, confirm));	
	}
	
	@Override
	public void deleteUsersView(UsersView loginUser, Integer userId) {
		coreValidationService.activeModuleAndForm(Forms.USERS);
		try {
			coreValidationService.runDatabaseValidation(usersViewDAO.deleteUsersView(loginUser, userId));
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "user");
		}
	}


}
