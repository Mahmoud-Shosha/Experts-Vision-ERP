package com.expertsvision.erp.systemcommands.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.flagdetail.service.InMemoryFlagDetailService;
import com.expertsvision.erp.core.flagmaster.service.InMemoryFlagMasterService;
import com.expertsvision.erp.core.flagpriv.service.InMemoryFlagPrivService;
import com.expertsvision.erp.core.form.service.InMemoryFormsService;
import com.expertsvision.erp.core.label.service.InMemoryLabelsService;
import com.expertsvision.erp.core.language.service.InMemoryLanguageService;
import com.expertsvision.erp.core.message.service.InMemoryMessagesService;
import com.expertsvision.erp.core.module.service.InMemoryModulesService;
import com.expertsvision.erp.core.privilege.service.InMemoryFormPrivilageService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;


@Service
public class ServerServiceImpl implements ServerService {


	@Autowired
	@Lazy
	private InMemoryFormsService inMemoryFormsService;

	@Autowired
	@Lazy
	private InMemoryFormPrivilageService inMemoryFormPrivilageService;

	@Autowired
	@Lazy
	private InMemoryModulesService inMemoryModulesService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;
	
	@Autowired
	@Lazy
	private InMemoryFlagMasterService inMemoryFlagMasterService;
	
	@Autowired
	@Lazy
	private InMemoryFlagDetailService inMemoryFlagDetailService;
	
	@Autowired
	@Lazy
	private InMemoryFlagPrivService inMemoryFlagPrivService;
	
	@Autowired
	@Lazy
	private InMemoryLabelsService inMemoryLabelsService;
	
	@Autowired
	@Lazy
	private InMemoryMessagesService inMemoryMessagesService;
	
	@Autowired
	@Lazy
	private InMemoryLanguageService inMemoryLanguageService;

	@Autowired
	@Lazy
	private InMemoryUsersGroupsService inMemoryUsersGroupsService;
	
	
	@Override
	@Transactional
	public void reloadServerCache(UsersView loginUser) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		inMemoryFormsService.updateFormsView();
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
		inMemoryModulesService.updateModulesViewMap();
		inMemoryUsersService.updateUsersView();
		inMemoryFlagMasterService.updateFlagMasterView();
		inMemoryFlagDetailService.updateFlagDetailView();
		inMemoryFlagPrivService.updateFlagPrivView();
		inMemoryLabelsService.updateLabelsView();
		inMemoryMessagesService.updateMessagesView();
		inMemoryLanguageService.updateLanguagesView();
		inMemoryUsersGroupsService.updateUsersGroupsView();
	}
	
}
