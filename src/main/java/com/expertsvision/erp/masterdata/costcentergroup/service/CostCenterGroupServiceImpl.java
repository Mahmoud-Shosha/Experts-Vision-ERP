package com.expertsvision.erp.masterdata.costcentergroup.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.postgresql.core.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.FlagDetails;
import com.expertsvision.erp.core.utils.FlagsActions;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.costcentergroup.dao.CostCenterGroupDAO;
import com.expertsvision.erp.masterdata.costcentergroup.dto.CostCenterGroupViewFilter;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroup;
import com.expertsvision.erp.masterdata.costcentergroup.entity.CostCenterGroupView;

@Service
public class CostCenterGroupServiceImpl implements CostCenterGroupService {

	@Autowired
	private CostCenterGroupDAO costCenterGroupDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;


	@Override
	@Transactional
	public List<CostCenterGroupView> getCostCenterGroupViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		List<CostCenterGroupView> costCenterGroupView = costCenterGroupDAO.getAllCostCenterGroupViewList();
		return costCenterGroupView;
	}

	@Override
	@Transactional
	public CostCenterGroupView getCostCenterGroupView(UsersView loginUsersView, Integer groupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		CostCenterGroupView costCenterGroupView = costCenterGroupDAO.getCostCenterGroupView(groupNo);
		if (costCenterGroupView == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return costCenterGroupView;
	}

	@Override
	@Transactional
	public SinglePage<CostCenterGroupView> getCostCenterGroupViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CostCenterGroupView> singlePage = costCenterGroupDAO.getCostCenterGroupViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<CostCenterGroupView> getCostCenterGroupViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		SinglePage<CostCenterGroupView> singlePage = costCenterGroupDAO.getCostCenterGroupViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getCostCenterGroupViewSinglePageNo(UsersView loginUsersView, Integer groupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = costCenterGroupDAO.getUserViewSinglePageNo(groupNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "group_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<CostCenterGroupView> getCostCenterGroupViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CostCenterGroupView> multiplePages = costCenterGroupDAO.getCostCenterGroupViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<CostCenterGroupView> getCostCenterGroupViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CostCenterGroupViewFilter costCenterGroupViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
		}
		// Return requested data
		MultiplePages<CostCenterGroupView> multiplePages = costCenterGroupDAO.getCostCenterGroupViewFilteredMultiplePages(pageNo,
				costCenterGroupViewFilter);
		return multiplePages;
	}
	

	@Override
	@Transactional
	public void addCostCenterGroup(UsersView loginUsersView, CostCenterGroupView costCenterGroupView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(costCenterGroupView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(costCenterGroupView.getGroupNo(), "group_no");
		coreValidationService.notNull(costCenterGroupView.getGroupDName(), "name");
		coreValidationService.notBlank(costCenterGroupView.getGroupDName(), "name");
		if ((costCenterGroupView.getGroupFName() != null) && costCenterGroupView.getGroupFName().isBlank())
			costCenterGroupView.setGroupFName(null);
		// Database validation
		CostCenterGroup costCenterGroup = getCostCenterGroupFromCostCenterGroupView(costCenterGroupView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", costCenterGroup.getGroupNo());
		if (generalDAO.isEntityExist("cost_center_group", conditions))
			throw new ValidationException("already_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", costCenterGroup.getGroupDName());
		if (generalDAO.isEntityExist("cost_center_group", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", costCenterGroup.getGroupFName());
		if (costCenterGroup.getGroupFName() != null && generalDAO.isEntityExist("cost_center_group", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();	
		// Add the costCenterGroup
		Timestamp add_date = new Timestamp(new Date().getTime());
		costCenterGroup.setAddDate(add_date);
		costCenterGroup.setAddUser(loginUsersView.getUserId());
		costCenterGroup.setModifyDate(null);
		costCenterGroup.setModifyUser(null);
		costCenterGroupDAO.addCostCenterGroup(costCenterGroup);
	}

	@Override
	@Transactional
	public void updateCostCenterGroup(UsersView loginUsersView, CostCenterGroupView costCenterGroupView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(costCenterGroupView.getGroupNo(), "group_no");
		coreValidationService.greaterThanOrEqualZero(costCenterGroupView.getGroupNo(), "group_no");
		coreValidationService.notNull(costCenterGroupView.getGroupDName(), "name");
		coreValidationService.notBlank(costCenterGroupView.getGroupDName(), "name");
		if ((costCenterGroupView.getGroupFName() != null) && costCenterGroupView.getGroupFName().isBlank())
			costCenterGroupView.setGroupFName(null);
		// Database validation
		CostCenterGroup costCenterGroup = getCostCenterGroupFromCostCenterGroupView(costCenterGroupView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", costCenterGroup.getGroupNo());
		if (!generalDAO.isEntityExist("cost_center_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		conditions.clear();
		conditions.put("group_d_name", costCenterGroup.getGroupDName());
		String exceptionCondition = null;
		exceptionCondition = " and group_no != " + costCenterGroup.getGroupNo();
		if (generalDAO.isEntityExist("cost_center_group", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("group_f_name", costCenterGroup.getGroupFName());
		if (costCenterGroup.getGroupFName() != null && generalDAO.isEntityExist("cost_center_group", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		// Update the user
		Timestamp update_date = new Timestamp(new Date().getTime());
		costCenterGroup.setModifyDate(update_date);
		costCenterGroup.setModifyUser(loginUsersView.getUserId());
		costCenterGroupDAO.updateCostCenterGroup(costCenterGroup);
	}

	@Override
	@Transactional
	public void deleteCostCenterGroup(UsersView loginUsersView, Integer costCenterGroupNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(costCenterGroupNo, "group_no");
		coreValidationService.greaterThanOrEqualZero(costCenterGroupNo, "group_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("group_no", costCenterGroupNo);
		if (!generalDAO.isEntityExist("cost_center_group", conditions))
			throw new ValidationException("not_exist", "group_no");
		// delete the userscostCenterGroup
		try {
			costCenterGroupDAO.deleteCostCenterGroup(costCenterGroupNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "group_no");
		}
	}
	
	@Override
	@Transactional
	public PreData preAdd(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.MODIFY);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		defaultValues.put("nextPK", costCenterGroupDAO.getNextPK());
		// return the data
		return preData;
	}
	
	@Override
	@Transactional
	public PreData preModify(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.FINANCIAL_CODING);
			} else {
				coreValidationService.activeModuleAndForm(Forms.FINANCIAL_CODING);
				coreValidationService.activeFlagDetail(FlagDetails.COST_CENTER_GROUP);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.FINANCIAL_CODING, FormsActions.INCLUDE);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.VIEW);
			coreValidationService.validateHasFlagDetailPrivilege(loginUsersView, FlagDetails.COST_CENTER_GROUP, FlagsActions.MODIFY);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		defaultValues.put("nextPK", costCenterGroupDAO.getNextPK());
		// return the data
		return preData;
	}

	public CostCenterGroup getCostCenterGroupFromCostCenterGroupView(CostCenterGroupView costCenterGroupView) {
		CostCenterGroup costCenterGroup = new CostCenterGroup();
		try {
			costCenterGroup.setAddDate(costCenterGroupView.getAddDate());
			costCenterGroup.setAddUser(costCenterGroupView.getAddUser());
			costCenterGroup.setGroupDName(Utils.escapeLiteral(null, costCenterGroupView.getGroupDName(), true).toString());
			if (costCenterGroupView.getGroupFName() == null)
				costCenterGroup.setGroupFName(costCenterGroupView.getGroupFName());
			else
				costCenterGroup.setGroupFName(Utils.escapeLiteral(null, costCenterGroupView.getGroupFName(), true).toString());
			costCenterGroup.setGroupNo(costCenterGroupView.getGroupNo());
			costCenterGroup.setModifyDate(costCenterGroupView.getModifyDate());
			costCenterGroup.setModifyUser(costCenterGroupView.getModifyUser());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return costCenterGroup;
	}

}
