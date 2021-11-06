package com.expertsvision.erp.masterdata.costcenters.service;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.usersgroups.service.InMemoryUsersGroupsService;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.costcenters.dao.CostCenterDAO;
import com.expertsvision.erp.masterdata.costcenters.dto.CostCenterViewFilter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenter;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterPriv;
import com.expertsvision.erp.masterdata.costcenters.entity.CostCenterView;

@Service
public class CostCenterServiceImpl implements CostCenterService {

	@Autowired
	private CostCenterDAO costCenterDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Autowired
	@Lazy
	private InMemoryUsersGroupsService inMemoryUsersGroupsService;

	@Override
	@Transactional
	public List<CostCenterView> getCostCenterViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		List<CostCenterView> costCenteresView = costCenterDAO.getAllCostCenterViewList(loginUsersView);
		return costCenteresView;
	}

	@Override
	@Transactional
	public CostCenterView getCostCenterView(UsersView loginUsersView, Integer costCenteresNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		CostCenterView costCenteresView = costCenterDAO.getCostCenterView(loginUsersView, costCenteresNo);
		if (costCenteresView == null) {
			throw new ValidationException("not_exist", "cc_no");
		}
		return costCenteresView;
	}

	@Override
	@Transactional
	public SinglePage<CostCenterView> getCostCenterViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<CostCenterView> singlePage = costCenterDAO.getCostCenterViewSinglePage(loginUsersView, pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CostCenterView> getCostCenterViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		SinglePage<CostCenterView> singlePage = costCenterDAO.getCostCenterViewLastPage(loginUsersView);
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCostCenterViewSinglePageNo(UsersView loginUsersView, Integer costCenteresNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		Long singlePageNo = costCenterDAO.getUserViewSinglePageNo(loginUsersView, costCenteresNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "cc_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CostCenterView> getCostCenterViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<CostCenterView> multiplePages = costCenterDAO.getCostCenterViewMultiplePages(loginUsersView,
				pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CostCenterView> getCostCenterViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CostCenterViewFilter costCenteresViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			loginUsersView = null;
		MultiplePages<CostCenterView> multiplePages = costCenterDAO
				.getCostCenterViewFilteredMultiplePages(loginUsersView, pageNo, costCenteresViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public Object getNextPK(UsersView loginUsersView, Integer parentCc) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
		}
		// Return requested data
		if (parentCc.equals(0))
			return null;
		Integer PK = (Integer) costCenterDAO.getNextPK(parentCc);
		if (PK == null)
			return null;
		String childDigitsString = PK.toString().substring(parentCc.toString().length());
		if ((Math.pow(10, childDigitsString.length()) - 1) > Integer.parseInt(childDigitsString))
			return ++PK;
		else
			return null;
	}

	@Override
	@Transactional
	public void addCostCenter(UsersView loginUsersView, CostCenterView costCenterView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(costCenterView.getCcNo(), "cc_no");
		coreValidationService.greaterThanOrEqualZero(costCenterView.getCcNo(), "cc_no");
		coreValidationService.notNull(costCenterView.getCcDName(), "name");
		coreValidationService.notBlank(costCenterView.getCcDName(), "name");
		if ((costCenterView.getCcFName() != null) && costCenterView.getCcFName().isBlank())
			costCenterView.setCcFName(null);
		coreValidationService.notNull(costCenterView.getParentCc(), "parent_cc");
		coreValidationService.greaterThanOrEqualZero(costCenterView.getParentCc(), "parent_cc");
		coreValidationService.notNull(costCenterView.getSub(), "type");
		coreValidationService.notNull(costCenterView.getInactive(), "inactive");
		// Database validation
		CostCenter costCenter = getCostCenterFromCostCenterView(costCenterView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("cc_no", costCenter.getCcNo());
		if (generalDAO.isEntityExist("cost_center", conditions))
			throw new ValidationException("already_exist", "cc_no");
		conditions.clear();
		conditions.put("cc_d_name", costCenter.getCcDName());
		if (generalDAO.isEntityExist("cost_center", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("cc_f_name", costCenter.getCcFName());
		if (costCenter.getCcFName() != null && generalDAO.isEntityExist("cost_center", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("group_no", costCenter.getCcGroup());
		if (costCenter.getCcGroup() != null && !generalDAO.isEntityExist("cost_center_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		if (!costCenter.getParentCc().equals(0)) {
			CostCenterView parentCCView;
			if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
				parentCCView = costCenterDAO.getCostCenterView(null, costCenter.getParentCc());
			else
				parentCCView = costCenterDAO.getCostCenterView(loginUsersView, costCenter.getParentCc());
			if (parentCCView == null)
				throw new ValidationException("not_exist", "parent_cc");
			if (parentCCView.getSub())
				throw new ValidationException("invalid", "parent_cc");
		}
		// Add the costCenteres
		Timestamp add_date = new Timestamp(new Date().getTime());
		costCenter.setAddDate(add_date);
		costCenter.setAddUser(loginUsersView.getUserId());
		costCenter.setModifyDate(null);
		costCenter.setModifyUser(null);
		if (costCenter.getInactive()) {
			costCenter.setInactiveDate(add_date);
			costCenter.setInactiveUser(loginUsersView.getUserId());
		} else {
			costCenter.setInactiveDate(null);
			costCenter.setInactiveUser(null);
			costCenter.setInactiveReason(null);
		}
		costCenterDAO.addCostCenter(costCenter);
		generateCostCenterPrivsForAllUsers(costCenter.getCcNo(), add_date);
	}

	@Override
	@Transactional
	public void updateCostCenter(UsersView loginUsersView, CostCenterView costCenterView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(costCenterView.getCcNo(), "cc_no");
		coreValidationService.greaterThanOrEqualZero(costCenterView.getCcNo(), "cc_no");
		coreValidationService.notNull(costCenterView.getCcDName(), "name");
		coreValidationService.notBlank(costCenterView.getCcDName(), "name");
		if ((costCenterView.getCcFName() != null) && costCenterView.getCcFName().isBlank())
			costCenterView.setCcFName(null);
		coreValidationService.notNull(costCenterView.getParentCc(), "parent_cc");
		coreValidationService.greaterThanOrEqualZero(costCenterView.getParentCc(), "parent_cc");
		coreValidationService.notNull(costCenterView.getSub(), "type");
		coreValidationService.notNull(costCenterView.getInactive(), "inactive");
		// Database validation
		CostCenter costCenter = getCostCenterFromCostCenterView(costCenterView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("cc_no", costCenter.getCcNo());
		if (!generalDAO.isEntityExist("cost_center", conditions))
			throw new ValidationException("not_exist", "cc_no");
		conditions.clear();
		conditions.put("cc_d_name", costCenter.getCcDName());
		String exceptionCondition = null;
		exceptionCondition = " and cc_no != " + costCenter.getCcNo();
		if (generalDAO.isEntityExist("cost_center", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("cc_f_name", costCenter.getCcFName());
		if (costCenter.getCcFName() != null && generalDAO.isEntityExist("cost_center", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		conditions.put("group_no", costCenter.getCcGroup());
		if (costCenter.getCcGroup() != null && !generalDAO.isEntityExist("cost_center_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		if (!costCenter.getParentCc().equals(0)) {
			CostCenterView parentCCView;
			if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
				parentCCView = costCenterDAO.getCostCenterView(null, costCenter.getParentCc());
			else
				parentCCView = costCenterDAO.getCostCenterView(loginUsersView, costCenter.getParentCc());
			if (parentCCView == null)
				throw new ValidationException("not_exist", "parent_cc");
			if (parentCCView.getSub())
				throw new ValidationException("invalid", "parent_cc");
		}
		// Update the user
		// Update the chartOfAccount
		Timestamp update_date = new Timestamp(new Date().getTime());
		CostCenterView DBCostCenterView;
		if (loginUsersView.getAdminUser() || loginUsersView.getSuperAdmin())
			DBCostCenterView = costCenterDAO.getCostCenterView(null, costCenterView.getCcNo());
		else
			DBCostCenterView = costCenterDAO.getCostCenterView(loginUsersView, costCenterView.getCcNo());
		if (DBCostCenterView.getInactive() && !costCenterView.getInactive()) {
			costCenter.setInactiveDate(null);
			costCenter.setInactiveUser(null);
			costCenter.setInactiveReason(null);

		} else if (!DBCostCenterView.getInactive() && costCenterView.getInactive()) {
			costCenter.setInactiveDate(update_date);
			costCenter.setInactiveUser(loginUsersView.getUserId());
		} else {
			costCenter.setInactiveDate(DBCostCenterView.getInactiveDate());
			costCenter.setInactiveUser(DBCostCenterView.getInactiveUser());
			costCenter.setInactiveReason(DBCostCenterView.getInactiveReason());
		}
		costCenter.setModifyDate(update_date);
		costCenter.setModifyUser(loginUsersView.getUserId());
		costCenterDAO.updateCostCenter(costCenter);
	}

	@Override
	@Transactional
	public void deleteCostCenter(UsersView loginUsersView, Integer ccNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.COST_CENTERS);
			} else {
				coreValidationService.activeModuleAndForm(Forms.COST_CENTERS);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.COST_CENTERS, FormsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(ccNo, "cc_no");
		coreValidationService.greaterThanOrEqualZero(ccNo, "cc_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("cc_no", ccNo);
		if (!generalDAO.isEntityExist("cost_center", conditions))
			throw new ValidationException("not_exist", "cc_no");
		if (hasSubCostCenters(ccNo))
			throw new ValidationException("has_sub_cost_centers");
		// delete the userscostCenteres
		try {
			costCenterDAO.deleteCostCenter(ccNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "cc_no");
		}
	}

	@Override
	public void generateCostCenterPrivsForAllUsers(Integer ccNo, Timestamp currentDate) {
		/*
		 * $$$$$$$$$$$$___Do not forget to add in
		 * MasterDataPrivilegesService___$$$$$$$$$$$$
		 */
		// PREPARE VARAIBLES
		List<CostCenterPriv> costCenterPrivList = new ArrayList<>();
		CostCenterPriv costCenterPriv;
		boolean viewPriv = true;
		boolean addPriv = true;
		// LOOP OVER ALL USERS
		for (UsersView usersView : inMemoryUsersService.getAllUsersView()) {
			// Do not generate privs for admin and superuser
			if (!(usersView.getSuperAdmin() || usersView.getAdminUser())) {
				if (usersView.getGroupNo() != null
						&& inMemoryUsersGroupsService.getUsersGroupsView(usersView.getGroupNo()).getAdminGroup()) {
					viewPriv = true;
					addPriv = true;
				} else {
					viewPriv = false;
					addPriv = false;
				}
				costCenterPriv = new CostCenterPriv();
				costCenterPriv.setAddDate(currentDate);
				costCenterPriv.setAddPriv(addPriv);
				costCenterPriv.setAddUser(1);
				costCenterPriv.setCostCenter(ccNo);
				costCenterPriv.setModifyDate(null);
				costCenterPriv.setModifyUser(null);
				costCenterPriv.setUserId(usersView.getUserId());
				costCenterPriv.setViewPriv(viewPriv);
				costCenterPrivList.add(costCenterPriv);
			}
		}
		// LOOP OVER PRIVS TO SAVE THEM
		for (CostCenterPriv priv : costCenterPrivList) {
			costCenterDAO.addCostCenterPriv(priv);
		}
	}

	@Override
	public boolean hasSubCostCenters(Integer ccNo) {
		return costCenterDAO.hasSubCostCenters(ccNo);
	}

	public CostCenter getCostCenterFromCostCenterView(CostCenterView costCenterView) {
		CostCenter costCenter = new CostCenter();
		try {
			costCenter.setAddDate(costCenterView.getAddDate());
			costCenter.setAddUser(costCenterView.getAddUser());
			costCenter.setCcDName(Utils.escapeLiteral(null, costCenterView.getCcDName(), true).toString());
			if (costCenterView.getCcFName() == null)
				costCenter.setCcFName(costCenterView.getCcFName());
			else
				costCenter.setCcFName(Utils.escapeLiteral(null, costCenterView.getCcFName(), true).toString());
			costCenter.setCcGroup(costCenterView.getCcGroup());
			costCenter.setCcNo(costCenterView.getCcNo());
			costCenter.setInactive(costCenterView.getInactive());
			costCenter.setInactiveDate(costCenterView.getInactiveDate());
			if (costCenterView.getInactiveReason() == null)
				costCenter.setInactiveReason(costCenterView.getInactiveReason());
			else
				costCenter.setInactiveReason(
						Utils.escapeLiteral(null, costCenterView.getInactiveReason(), true).toString());
			costCenter.setInactiveUser(costCenterView.getInactiveUser());
			costCenter.setModifyDate(costCenterView.getModifyDate());
			costCenter.setModifyUser(costCenterView.getModifyUser());
			costCenter.setParentCc(costCenterView.getParentCc());
			costCenter.setSub(costCenterView.getSub());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return costCenter;
	}

}
