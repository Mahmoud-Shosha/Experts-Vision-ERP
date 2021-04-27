package com.expertsvision.erp.core.user.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.ConfirmException;
import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.privilege.service.FormPrivilageService;
import com.expertsvision.erp.core.user.dao.UsersDAO;
import com.expertsvision.erp.core.user.dto.UsersDTO;
import com.expertsvision.erp.core.user.dto.UsersViewFilter;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;


@Service
public class UsersServiceImpl implements UsersService {
	
	@Autowired
	private UsersDAO usersViewDAO;
	
	@Autowired
	private FormPrivilageService formPrivilageService; 
	
	@Autowired
	private GeneralDAO generalDAO; 
	
	@Autowired
	private CoreValidationService coreValidationService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Override
	@Transactional
	public List<UsersView> getAllUsersViewList() {
		// Only used by system functions, like inmemory DB
		List<UsersView> usersViewList = usersViewDAO.getAllUsersViewList();
		return usersViewList;
	}
	
	@Override
	@Transactional
	public List<UsersDTO> getUsersViewSubordinateList(UsersView loginUser) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		// Return requested data
		List<UsersView> usersViewList = usersViewDAO.getUsersViewSubordinateList(loginUser.getUserId());
		List<UsersDTO> usersDTOsList = new ArrayList<>();
		UsersDTO usersDTO = null;
		for (UsersView usersView : usersViewList) {
			usersDTO = getUsersDTOFromUsersView(usersView);
			usersDTO.setPassword(null);
			usersDTOsList.add(usersDTO);
		}
		return usersDTOsList;
	}
	
	
	@Override
	@Transactional
	public UsersDTO getUsersView(UsersView loginUser, Integer userId) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		// Return requested data
		UsersView usersViewByUserId = usersViewDAO.getUsersView(loginUser.getUserId(), userId);
		if (usersViewByUserId == null) {
			throw new ValidationException("not_exist", "user");
		}
		UsersDTO usersDTO = getUsersDTOFromUsersView(usersViewByUserId);
		usersDTO.setPassword(null);
		return usersDTO;
	}
	
	@Override
	@Transactional
	public SinglePage<UsersDTO> getUsersViewSinglePage(UsersView loginUser, long pageNo) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		// Return requested data
		SinglePage<UsersView> singlePage = usersViewDAO.getUsersViewSinglePage(loginUser.getUserId(), pageNo);
		SinglePage<UsersDTO> newSinglePage = null;
		if (singlePage.getPage() != null) {
			UsersDTO usersDTO = getUsersDTOFromUsersView(singlePage.getPage());
			usersDTO.setPassword(null);
			newSinglePage = new SinglePage<UsersDTO>(usersDTO, singlePage.getPageNo(), singlePage.getPagesCount());
		} else {
			newSinglePage = new SinglePage<UsersDTO>(null, singlePage.getPageNo(), singlePage.getPagesCount());
		}
		return newSinglePage;
	}

	@Override
	@Transactional
	public SinglePage<UsersDTO> getUsersViewLastPage(UsersView loginUser) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		// Return requested data
		SinglePage<UsersView> singlePage = usersViewDAO.getUsersViewLastPage(loginUser.getUserId());
		SinglePage<UsersDTO> newSinglePage = null;
		if (singlePage.getPage() != null) {
			UsersDTO usersDTO = getUsersDTOFromUsersView(singlePage.getPage());
			usersDTO.setPassword(null);
			newSinglePage = new SinglePage<UsersDTO>(usersDTO, singlePage.getPageNo(), singlePage.getPagesCount());
		} else {
			newSinglePage = new SinglePage<UsersDTO>(null, singlePage.getPageNo(), singlePage.getPagesCount());
		}
		return newSinglePage;
	}
	
	@Override
	@Transactional
	public Long getUserViewSinglePageNo(UsersView loginUser, Integer userId) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		// Return requested data
		Long singlePageNo = usersViewDAO.getUserViewSinglePageNo(loginUser.getUserId(), userId);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "user");
		}
		return singlePageNo;
	}
	
	@Override
	@Transactional
	public MultiplePages<UsersDTO> getUsersViewMultiplePages(UsersView loginUser, long pageNo) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		// Return requested data
		MultiplePages<UsersView> multiplePages = usersViewDAO.getUsersViewMultiplePages(loginUser.getUserId(), pageNo);
		MultiplePages<UsersDTO> newMultiplePages = null;
		if (multiplePages.getPages() != null) {
			newMultiplePages = new MultiplePages<>(new ArrayList<>(), multiplePages.getPageNo(), multiplePages.getPagesCount());
			for (UsersView usersView : multiplePages.getPages()) {
				UsersDTO usersDTO = getUsersDTOFromUsersView(usersView);
				usersDTO.setPassword(null);
				newMultiplePages.getPages().add(usersDTO);
			}
		} else {
			newMultiplePages = new MultiplePages<>(null, multiplePages.getPageNo(), multiplePages.getPagesCount());
		}
		return newMultiplePages;
	}
	
	@Override
	@Transactional
	public MultiplePages<UsersDTO> getUsersViewFilteredMultiplePages(UsersView loginUser, long pageNo, UsersViewFilter usersViewFilter) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		// Return requested data
		MultiplePages<UsersView> multiplePages = usersViewDAO.getUsersViewFilteredMultiplePages(loginUser.getUserId(), pageNo, usersViewFilter);
		MultiplePages<UsersDTO> newMultiplePages = null;
		if (multiplePages.getPages() != null) {
			newMultiplePages = new MultiplePages<>(new ArrayList<>(), multiplePages.getPageNo(), multiplePages.getPagesCount());
			for (UsersView usersView : multiplePages.getPages()) {
				UsersDTO usersDTO = getUsersDTOFromUsersView(usersView);
				usersDTO.setPassword(null);
				newMultiplePages.getPages().add(usersDTO);
			}
		} else {
			newMultiplePages = new MultiplePages<>(null, multiplePages.getPageNo(), multiplePages.getPagesCount());
		}
		return newMultiplePages;
	}
	
	@Override
	@Transactional
	public void addUser(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.ADD);
		// Non-database validation
		coreValidationService.notNull(usersView.getUserId(), "user_no");
		coreValidationService.greaterThanOrEqualZero(usersView.getUserId(), "user_no");
		coreValidationService.notNull(usersView.getUserDName(), "name");
		coreValidationService.notBlank(usersView.getUserDName(), "name");
		if ((usersView.getUserFName() != null) && usersView.getUserFName().isBlank()) usersView.setUserFName(null);
		if ((usersView.getInactiveReason() != null) && usersView.getInactiveReason().isBlank()) usersView.setInactiveReason(null);
		coreValidationService.notNull(usersView.getDirectMang(), "direct_manager");
		coreValidationService.greaterThanOrEqualZero(usersView.getDirectMang(), "direct_manager");
		coreValidationService.notNull(usersView.getPassword(), "password");
		coreValidationService.notBlank(usersView.getPassword(), "password");
		// Database validation
		User user = getUserFromUsersView(usersView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("user_id", user.getUserId());
		if (generalDAO.isEntityExist("users", conditions)) throw new ValidationException("already_exist", "user_no");
		conditions.clear();
		conditions.put("user_d_name", user.getUserDName());
		if (generalDAO.isEntityExist("users", conditions)) throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("user_f_name", user.getUserFName());
		if (user.getUserFName() != null && generalDAO.isEntityExist("users", conditions)) throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("user_id", user.getDirectMang());
		if (!generalDAO.isEntityExist("users", conditions)) throw new ValidationException("not_exist", "direct_manager");
		conditions.clear();
		conditions.put("group_no", user.getGroupNo());
		if (user.getGroupNo() != null && !generalDAO.isEntityExist("users_groups", conditions)) throw new ValidationException("not_exist", "group_no");
		if (!isUserSubordinate(loginUser, usersView.getDirectMang())) throw new UnauthorizedException("direct_manager");
		// Add the user
		Timestamp add_date = new Timestamp(new Date().getTime());
		user.setAddDate(add_date);
		user.setAddUser(loginUser.getUserId());
		user.setModifyDate(null);
		user.setModifyUser(null);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setSuperAdmin(false);
		user.setAdminUser(false);
		if (user.getInactive()) {
			user.setInactiveDate(add_date);
			user.setInactiveUser(loginUser.getUserId());
		} else {
			user.setInactiveDate(null);
			user.setInactiveUser(null);
			user.setInactiveReason(null);
		}
		usersViewDAO.addUser(user);
		if (COPY_FROM_USER_PRIVILEDGES != null) {
			if (!isUserSubordinate(loginUser, COPY_FROM_USER_PRIVILEDGES)) throw new UnauthorizedException("copy_privileges_from_user");
			formPrivilageService.generateFormPrivilegesForUserFromAnotherUser(loginUser, COPY_FROM_USER_PRIVILEDGES, user.getUserId(), add_date);
		} else {
			formPrivilageService.generateFormPrivilegesForUser(loginUser, user.getUserId(), add_date);
		}
		inMemoryUsersService.updateUsersView();
	}
	
	@Override
	@Transactional
	public void updateUsersView(UsersView loginUser, UsersView usersView, Integer COPY_FROM_USER_PRIVILEDGES,
								Integer COPY_PRIVILEGES_TO_GROUP, Boolean confirm) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.MODIFY);
		// Non-database validation
		if (loginUser.getUserId().equals(usersView.getUserId())) throw new UnauthorizedException("user");
		coreValidationService.notNull(usersView.getUserId(), "user_no");
		coreValidationService.greaterThanOrEqualZero(usersView.getUserId(), "user_no");
		coreValidationService.notNull(usersView.getUserDName(), "name");
		coreValidationService.notBlank(usersView.getUserDName(), "name");
		if ((usersView.getUserFName() != null) && usersView.getUserFName().isBlank()) usersView.setUserFName(null);
		if ((usersView.getInactiveReason() != null) && usersView.getInactiveReason().isBlank()) usersView.setInactiveReason(null);
		coreValidationService.notNull(usersView.getDirectMang(), "direct_manager");
		coreValidationService.greaterThanOrEqualZero(usersView.getDirectMang(), "direct_manager");
		coreValidationService.notNull(usersView.getPassword(), "password");
		coreValidationService.notBlank(usersView.getPassword(), "password");
		// Database validation
		User user = getUserFromUsersView(usersView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("user_id", user.getUserId());
		if (!generalDAO.isEntityExist("users", conditions)) throw new ValidationException("not_exist", "user_no");
		conditions.clear();
		conditions.put("user_d_name", user.getUserDName());
		String exceptionCondition = null;
		exceptionCondition = " and user_id != " + user.getUserId();
		if (generalDAO.isEntityExist("users", conditions, exceptionCondition)) throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("user_f_name", user.getUserFName());
		if (user.getUserFName() != null && generalDAO.isEntityExist("users", conditions, exceptionCondition)) throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("user_id", user.getDirectMang());
		if (!generalDAO.isEntityExist("users", conditions)) throw new ValidationException("not_exist", "direct_manager");
		conditions.clear();
		conditions.put("group_no", user.getGroupNo());
		if (user.getGroupNo() != null && !generalDAO.isEntityExist("users_groups", conditions)) throw new ValidationException("not_exist", "group_no");
		if (!isUserSubordinate(loginUser, user.getUserId())) throw new UnauthorizedException("user");
		if (!isUserSubordinate(loginUser, usersView.getDirectMang())) throw new UnauthorizedException("direct_manager");
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		user.setModifyDate(update_date);
		user.setModifyUser(loginUser.getUserId());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setSuperAdmin(false);
		user.setAdminUser(false);
		UsersView DBUsersView = usersViewDAO.getUsersView(loginUser.getUserId(), usersView.getUserId());
		if (DBUsersView.getInactive() && !user.getInactive()) {
			user.setInactiveDate(null);
			user.setInactiveUser(null);
			user.setInactiveReason(null);
			
		} else if (!DBUsersView.getInactive() && user.getInactive()) {
			user.setInactiveDate(update_date);
			user.setInactiveUser(loginUser.getUserId());
		} else {
			user.setInactiveDate(DBUsersView.getInactiveDate());
			user.setInactiveUser(DBUsersView.getInactiveUser());
			user.setInactiveReason(DBUsersView.getInactiveReason());
		}
		usersViewDAO.updateUser(user);
		if ((COPY_FROM_USER_PRIVILEDGES != null) && (COPY_PRIVILEGES_TO_GROUP != null)) {
			throw new ValidationException("cannot_both_copy_privileges");
		} else if (COPY_FROM_USER_PRIVILEDGES != null) {
			if (!isUserSubordinate(loginUser, COPY_FROM_USER_PRIVILEDGES)) throw new UnauthorizedException("copy_privileges_from_user");
			formPrivilageService.updateFormPrivilegesForUserFromAnotherUser(loginUser, COPY_FROM_USER_PRIVILEDGES, user.getUserId(), update_date);
		} else if (COPY_PRIVILEGES_TO_GROUP != null) {
			// Check that the group exists
			if (confirm) {
				formPrivilageService.updateGroupUsersPrivileges(loginUser, user.getUserId(), COPY_PRIVILEGES_TO_GROUP, update_date);
				return;
			}
			List<User> groupMembers = getUsersListByGroupNo(COPY_PRIVILEGES_TO_GROUP);
			List<UsersDTO> loginUserSubordinates = getUsersViewSubordinateList(loginUser);
			boolean found = false;
			for (User u : groupMembers) {
				for (UsersDTO u2 : loginUserSubordinates) {
					if (u.getUserId().equals(u2.getUserId())) found = true;
				}
				if (!found) throw new ConfirmException("group_members_not_under_management");
				found = false;
			}
			formPrivilageService.updateGroupUsersPrivileges(loginUser, user.getUserId(), COPY_PRIVILEGES_TO_GROUP, update_date);
		}
		inMemoryUsersService.updateUsersView();
	}
	
	@Override
	@Transactional
	public void deleteUsersView(UsersView loginUser, Integer userId) {
		// Check module, form, privileges
		coreValidationService.activeModuleAndForm(Forms.USERS);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.INCLUDE);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.VIEW);
		coreValidationService.validateHasFormPrivilege(loginUser, Forms.USERS, FormsActions.DELETE);
		// Non-database validation
		if (loginUser.getUserId().equals(userId)) throw new UnauthorizedException("user");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("user_id", userId);
		if (!generalDAO.isEntityExist("users", conditions)) throw new ValidationException("not_exist", "user");
		if (!isUserSubordinate(loginUser, userId)) throw new UnauthorizedException("user");
		// delete the user
		try {
			usersViewDAO.deleteUser(userId);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "user");
		}
		formPrivilageService.deleteBulkFormPrivilage(userId);
		inMemoryUsersService.updateUsersView();
	}
	
	@Override
	public Boolean isUserSubordinate(UsersView loginUser, Integer userId) {
		UsersView usersViewByUserId = usersViewDAO.getUsersView(loginUser.getUserId(), userId);
		if (usersViewByUserId == null) return false;
		else return true;
	}
	
	@Override
	public List<User> getUsersListByGroupNo(Integer groupNo) {
		List<User> usersList = usersViewDAO.getUsersListByGroupNo(groupNo);
		return usersList;
	}
	
	public User getUserFromUsersView(UsersView usersView)  {
		User user = new User();
		try {
			user.setAddDate(usersView.getAddDate());
			user.setAddUser(usersView.getAddUser());
			user.setAdminUser(usersView.getAdminUser());
			user.setDirectMang(usersView.getDirectMang());
			user.setGroupNo(usersView.getGroupNo());
			user.setInactive(usersView.getInactive());
			user.setInactiveDate(usersView.getInactiveDate());
			if (usersView.getInactiveReason() == null)
				user.setInactiveReason(usersView.getInactiveReason());
			else
				user.setInactiveReason(Utils.escapeLiteral(null, usersView.getInactiveReason(), true).toString());
			user.setInactiveUser(usersView.getInactiveUser());
			user.setModifyDate(usersView.getModifyDate());
			user.setModifyUser(usersView.getModifyUser());
			user.setPassword(Utils.escapeLiteral(null, usersView.getPassword(), true).toString());
			user.setSuperAdmin(usersView.getSuperAdmin());
			user.setUserDName(Utils.escapeLiteral(null, usersView.getUserDName(), true).toString());
			if (usersView.getUserFName() == null)
				user.setUserFName(usersView.getUserFName());
			else
				user.setUserFName(Utils.escapeLiteral(null, usersView.getUserFName(), true).toString());
			user.setUserId(usersView.getUserId());
		} catch (SQLException e) {
			 throw new UnauthorizedException("resource");
		}
		return user;
	}
	
	public UsersDTO getUsersDTOFromUsersView(UsersView usersView) {
		UsersDTO usersDTO = new UsersDTO();
		usersDTO.setAddDate(usersView.getAddDate());
		usersDTO.setAddUser(usersView.getAddUser());
		usersDTO.setAddUserDName(usersView.getAddUserDName());
		usersDTO.setAddUserFName(usersView.getAddUserFName());
		usersDTO.setAdminUser(usersView.getAdminUser());
		usersDTO.setDirectMang(usersView.getDirectMang());
		usersDTO.setDirectMangDName(usersView.getAddUserDName());
		usersDTO.setDirectMangFName(usersView.getDirectMangFName());
		usersDTO.setGroupNo(usersView.getGroupNo());
		usersDTO.setGroupNoDName(usersView.getGroupNoDName());
		usersDTO.setGroupNoFName(usersView.getGroupNoFName());
		usersDTO.setInactive(usersView.getInactive());
		usersDTO.setInactiveDate(usersView.getInactiveDate());
		usersDTO.setInactiveReason(usersView.getInactiveReason());
		usersDTO.setInactiveUser(usersView.getInactiveUser());
		usersDTO.setModifyDate(usersView.getModifyDate());
		usersDTO.setModifyUser(usersView.getModifyUser());
		usersDTO.setModifyUserDName(usersView.getModifyUserDName());
		usersDTO.setModifyUserFName(usersView.getModifyUserFName());
		usersDTO.setPassword(usersView.getPassword());
		usersDTO.setSuperAdmin(usersView.getSuperAdmin());
		usersDTO.setUserDName(usersView.getUserDName());
		usersDTO.setUserFName(usersView.getUserFName());
		usersDTO.setUserId(usersView.getUserId());
		return usersDTO;
	}


}
