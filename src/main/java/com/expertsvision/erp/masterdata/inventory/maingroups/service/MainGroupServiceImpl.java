package com.expertsvision.erp.masterdata.inventory.maingroups.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.shared.masterwithpriv.MasterWithPrivService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.inventory.maingroups.dao.MainGroupDAO;
import com.expertsvision.erp.masterdata.inventory.maingroups.dto.MainGroupViewFilter;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroup;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupPriv;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupView;

@Service
public class MainGroupServiceImpl extends MasterWithPrivService implements MainGroupService {

	@Autowired
	private MainGroupDAO mainGroupDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Autowired
	@Lazy
	private InMemoryUsersGroupsService inMemoryUsersGroupsService;

	public MainGroupServiceImpl() {
		this.form = Forms.INV_MAIN_GROUPS;
	}

	@Override
	@Transactional
	public MainGroupView getMainGroupView(String groupCode) {
		checkScreenViewPriv();
		MainGroupView mainGroupView = mainGroupDAO.getMainGroupView(groupCode);
		checkMasterEntityViewExist(mainGroupView, "group_code");
		return mainGroupView;
	}

	@Override
	@Transactional
	public SinglePage<MainGroupView> getMainGroupViewSinglePage(long pageNo) {
		checkScreenViewPriv();
		SinglePage<MainGroupView> singlePage = mainGroupDAO.getMainGroupViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<MainGroupView> getMainGroupViewLastPage() {
		checkScreenViewPriv();
		SinglePage<MainGroupView> singlePage = mainGroupDAO.getMainGroupViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getMainGroupViewSinglePageNo(String groupCode) {
		checkScreenViewPriv();
		Long singlePageNo = mainGroupDAO.getMainGroupViewSinglePageNo(groupCode);
		checkMasterEntityViewExist(singlePageNo, "group_code");
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<MainGroupView> getMainGroupViewMultiplePages(long pageNo) {
		checkScreenViewPriv();
		MultiplePages<MainGroupView> multiplePages = mainGroupDAO.getMainGroupViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<MainGroupView> getMainGroupViewFilteredMultiplePages(long pageNo,
			MainGroupViewFilter mainGroupViewFilter) {
		checkScreenViewPriv();
		MultiplePages<MainGroupView> multiplePages = mainGroupDAO.getMainGroupViewFilteredMultiplePages(pageNo,
				mainGroupViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addMainGroup(MainGroupView mainGroupView) {
		// Check module, form, privileges
		checkScreenAddPriv();
		// Non-database validation
		coreValidationService.notNull(mainGroupView.getGroupCode(), "group_code");
		coreValidationService.notBlank(mainGroupView.getGroupCode(), "group_code");
		coreValidationService.notNull(mainGroupView.getGroupDName(), "name");
		coreValidationService.notBlank(mainGroupView.getGroupDName(), "name");
		mainGroupView.setGroupFName(getNullIfBlank(mainGroupView.getGroupFName()));
		// Database validation
		MainGroup mainGroup = getMainGroupFromMainGroupView(mainGroupView);
		checkNotExist("main_group", "group_code", mainGroup.getGroupCode(), "group_code");
		checkNotExist("main_group", "group_d_name", mainGroup.getGroupDName(), "name");
		checkNotExist("main_group", "group_f_name", mainGroup.getGroupFName(), "foreign_name");
		// Add the MainGroup
		mainGroupDAO.addMainGroup(mainGroup);
		generateMainGroupPrivsForAllUsers(mainGroup.getGroupCode());
	}

	@Override
	@Transactional
	public void updateMainGroup(MainGroupView mainGroupView) {
		// Check module, form, privileges
		checkScreenModifyPriv();
		// Non-database validation
		coreValidationService.notNull(mainGroupView.getGroupCode(), "group_code");
		coreValidationService.notBlank(mainGroupView.getGroupCode(), "group_code");
		coreValidationService.notNull(mainGroupView.getGroupDName(), "name");
		coreValidationService.notBlank(mainGroupView.getGroupDName(), "name");
		mainGroupView.setGroupFName(getNullIfBlank(mainGroupView.getGroupFName()));
		// Database validation
		MainGroup mainGroup = getMainGroupFromMainGroupView(mainGroupView);
		checkExist("main_group", "group_code", mainGroup.getGroupCode(), "group_code");
		String where = " and group_code != '" + mainGroup.getGroupCode() + "'";
		checkNotExist("main_group", "group_d_name", mainGroup.getGroupDName(), "name", where);
		checkNotExist("main_group", "group_f_name", mainGroup.getGroupFName(), "foreign_name", where);
		// Update the MainGroup
		mainGroupDAO.updateMainGroup(mainGroup);
	}

	@Override
	@Transactional
	public void deleteMainGroup(String groupCode) {
		// Check module, form, privileges
		checkScreenDeletePriv();
		// Non-database validation
		coreValidationService.notNull(groupCode, "group_code");
		coreValidationService.notBlank(groupCode, "group_code");
		// Database validation
		checkExist("main_group", "group_code", groupCode, "group_code");
		// Delete the MainGroup
		try {
			mainGroupDAO.deleteMainGroup(groupCode);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "group_code");
		}
	}

	@Override
	public void generateMainGroupPrivsForAllUsers(String groupCode) {
		/*
		 * $$$$$$$$$$$$___Do not forget to add in
		 * MasterDataPrivilegesService___$$$$$$$$$$$$
		 */
		// Prepare variables
		List<MainGroupPriv> mainGroupPrivList = new ArrayList<>();
		MainGroupPriv mainGroupPriv;
		boolean viewPriv, addPriv;
		// Loop over all users
		for (UsersView usersView : inMemoryUsersService.getAllUsersView()) {
			// Do not generate privs for admin and superuser
			if (!(usersView.getSuperAdmin() || usersView.getAdminUser())) {
				viewPriv = getViewPriv(usersView);
				addPriv = getAddPriv(usersView);
				mainGroupPriv = new MainGroupPriv();
				mainGroupPriv.setGroupCode(groupCode);
				mainGroupPriv.setUserId(usersView.getUserId());
				mainGroupPriv.setViewPriv(viewPriv);
				mainGroupPriv.setAddPriv(addPriv);
				mainGroupPrivList.add(mainGroupPriv);
			}
		}
		// Add the privs in the database
		mainGroupDAO.addMainGroupesPriv(mainGroupPrivList);
	}

	public MainGroup getMainGroupFromMainGroupView(MainGroupView mainGroupView) {
		// Map all fields and escape string fields
		MainGroup mainGroup = new MainGroup();
		mainGroup.setAddDate(mainGroupView.getAddDate());
		mainGroup.setAddUser(mainGroupView.getAddUser());
		mainGroup.setGroupCode(escapeMandatoryString(mainGroup.getGroupCode()));
		mainGroup.setGroupDName(escapeMandatoryString(mainGroup.getGroupDName()));
		mainGroup.setGroupFName(escapeOptionalString(mainGroup.getGroupFName()));
		mainGroup.setModifyDate(mainGroupView.getModifyDate());
		mainGroup.setModifyUser(mainGroupView.getModifyUser());
		return mainGroup;
	}

}
