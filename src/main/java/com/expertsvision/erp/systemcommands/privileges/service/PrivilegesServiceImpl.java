package com.expertsvision.erp.systemcommands.privileges.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.flagdetail.service.InMemoryFlagDetailService;
import com.expertsvision.erp.core.flagmaster.service.InMemoryFlagMasterService;
import com.expertsvision.erp.core.flagpriv.dao.FlagPrivDAO;
import com.expertsvision.erp.core.flagpriv.entity.FlagPriv;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivPK;
import com.expertsvision.erp.core.flagpriv.service.InMemoryFlagPrivService;
import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.form.service.InMemoryFormsService;
import com.expertsvision.erp.core.module.service.InMemoryModulesService;
import com.expertsvision.erp.core.privilege.dao.FormPrivilageDAO;
import com.expertsvision.erp.core.privilege.entity.FormPrivilage;
import com.expertsvision.erp.core.privilege.entity.FormPrivilagePK;
import com.expertsvision.erp.core.privilege.service.InMemoryFormPrivilageService;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.masterdata.masterdataprivileges.service.MasterDataPrivilegesService;

@Service
public class PrivilegesServiceImpl implements PrivilegesService {

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
	private FormPrivilageDAO formPrivilageViewDAO;
	
	@Autowired
	private FlagPrivDAO flagPrivViewDAO;
	
	@Autowired
	private MasterDataPrivilegesService masterDataPrivilegesService;

	
	@Override
	@Transactional
	public void generateUngeneratedPrivileges(UsersView loginUser, boolean prvSent) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// PREPARE VARAIBLES
		Timestamp currentDate = new Timestamp(new Date().getTime());
		Map<String, List<FlagDetailView>> flagDetailViewListByFlagCode = inMemoryFlagDetailService.getFlagDetailViewListByFlagCode();
		List<FormPrivilage> prvsList = new ArrayList<>();
		FormPrivilage prv = null;
		List<FlagPriv> flagPrvsList = new ArrayList<>();
		FlagPriv flagPrv = null;
		// LOOP OVER USERS
		for (UsersView UsersView : inMemoryUsersService.getAllUsersView()) {
			// Do not generate privs for admin and superuser
			if (!(UsersView.getSuperAdmin() || UsersView.getAdminUser())) {
				// LOOP OVER FORMS
				for (FormsView formsView : inMemoryFormsService.getAllFormsView()) {
					// Generate privs if module is active and user does not has the priv
					if (inMemoryModulesService.getModulesView(formsView.getModuleNo()).getActive()) {
						if (inMemoryFormPrivilageService.getFormPrivilageView(
								new FormPrivilagePK(UsersView.getUserId(), formsView.getFormNo())) == null) {
							prv = new FormPrivilage();
							prv.setAddDate(currentDate);
							prv.setAddPriv(prvSent);
							prv.setAddUser(loginUser.getUserId());
							prv.setAuditPriv(prvSent);
							prv.setDeletePriv(prvSent);
							prv.setFormNo(formsView.getFormNo());
							prv.setIncludePriv(prvSent);
							prv.setModifyDate(null);
							prv.setModifyPriv(prvSent);
							prv.setModifyUser(null);
							prv.setPostPriv(prvSent);
							prv.setPrintPriv(prvSent);
							prv.setUserId(UsersView.getUserId());
							prv.setViewPriv(prvSent);
							prvsList.add(prv);
						}
						if (formsView.getFlagCode() != null) {
							for (FlagDetailView flagDetailView : flagDetailViewListByFlagCode.get(formsView.getFlagCode())) {
								if (inMemoryFlagPrivService.getFlagPrivView(new FlagPrivPK(UsersView.getUserId(), flagDetailView.getFlagCode(), flagDetailView.getFlagValue())) == null) {
									flagPrv = new FlagPriv();
									flagPrv.setAddDate(currentDate);
									flagPrv.setAddPriv(prvSent);
									flagPrv.setAddUser(loginUser.getUserId());
									flagPrv.setDeletePriv(prvSent);
									flagPrv.setModifyDate(null);
									flagPrv.setModifyPriv(prvSent);
									flagPrv.setModifyUser(null);
									flagPrv.setPrintPriv(prvSent);
									flagPrv.setViewPriv(prvSent);
									flagPrv.setUserId(UsersView.getUserId());
									flagPrv.setFlagCode(flagDetailView.getFlagCode());
									flagPrv.setFlagValue(flagDetailView.getFlagValue());
									flagPrvsList.add(flagPrv);
								}
							}
						}
					}
				}
				masterDataPrivilegesService.generateUngeneratedMasterDataPrivileges(loginUser, UsersView, currentDate, prvSent, prvSent);
			}
		}
		formPrivilageViewDAO.addBulkFormPrivilage(prvsList);
		flagPrivViewDAO.addBulkFlagPriv(flagPrvsList);
		inMemoryFormPrivilageService.updateFormPrivilageViewMap();
		inMemoryFlagPrivService.updateFlagPrivView();
	}

}
