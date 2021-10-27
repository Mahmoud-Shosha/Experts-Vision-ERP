package com.expertsvision.erp.masterdata.employees.service;

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
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.FormsActions;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;
import com.expertsvision.erp.masterdata.employees.dao.EmpInfoDAO;
import com.expertsvision.erp.masterdata.employees.dto.EmpInfoViewFilter;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfo;
import com.expertsvision.erp.masterdata.employees.entity.EmpInfoView;

@Service
public class EmpInfoServiceImpl implements EmpInfoService {

	@Autowired
	private EmpInfoDAO empInfoDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Override
	@Transactional
	public List<EmpInfoView> getEmpInfoViewList(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
		}
		// Return requested data
		List<EmpInfoView> empInfoView = empInfoDAO.getAllEmpInfoViewList();
		return empInfoView;
	}

	@Override
	@Transactional
	public EmpInfoView getEmpInfoView(UsersView loginUsersView, Integer empNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
		}
		// Return requested data
		EmpInfoView empInfoView = empInfoDAO.getEmpInfoView(empNo);
		if (empInfoView == null) {
			throw new ValidationException("not_exist", "emp_no");
		}
		return empInfoView;
	}

	@Override
	@Transactional
	public SinglePage<EmpInfoView> getEmpInfoViewSinglePage(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
		}
		// Return requested data
		SinglePage<EmpInfoView> singlePage = empInfoDAO.getEmpInfoViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<EmpInfoView> getEmpInfoViewLastPage(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
		}
		// Return requested data
		SinglePage<EmpInfoView> singlePage = empInfoDAO.getEmpInfoViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getEmpInfoViewSinglePageNo(UsersView loginUsersView, Integer empoNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
		}
		// Return requested data
		Long singlePageNo = empInfoDAO.getEmpInfoViewSinglePageNo(empoNo);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "emp_no");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<EmpInfoView> getEmpInfoViewMultiplePages(UsersView loginUsersView, long pageNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<EmpInfoView> multiplePages = empInfoDAO.getEmpInfoViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<EmpInfoView> getEmpInfoViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			EmpInfoViewFilter empInfoViewFilter) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
		}
		// Return requested data
		MultiplePages<EmpInfoView> multiplePages = empInfoDAO.getEmpInfoViewFilteredMultiplePages(pageNo,
				empInfoViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addEmpInfo(UsersView loginUsersView, EmpInfoView empInfoView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.ADD);
		}
		// Non-database validation
		coreValidationService.notNull(empInfoView.getEmpNo(), "emp_no");
		coreValidationService.greaterThanZero(empInfoView.getEmpNo(), "emp_no");
		coreValidationService.notNull(empInfoView.getEmpDName(), "name");
		coreValidationService.notBlank(empInfoView.getEmpDName(), "name");
		if ((empInfoView.getEmpFName() != null) && empInfoView.getEmpFName().isBlank())
			empInfoView.setEmpFName(null);
		// Database validation
		EmpInfo empInfo = getEmpInfoFromEmpInfoView(empInfoView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("emp_no", empInfo.getEmpNo());
		if (generalDAO.isEntityExist("emp_info", conditions))
			throw new ValidationException("already_exist", "emp_no");
		conditions.clear();
		conditions.put("emp_d_name", empInfo.getEmpDName());
		if (generalDAO.isEntityExist("emp_info", conditions))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("emp_f_name", empInfo.getEmpFName());
		if (empInfo.getEmpFName() != null && generalDAO.isEntityExist("emp_info", conditions))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();
		// Add the empInfo
		Timestamp add_date = new Timestamp(new Date().getTime());
		empInfo.setAddDate(add_date);
		empInfo.setAddUser(loginUsersView.getUserId());
		empInfo.setModifyDate(null);
		empInfo.setModifyUser(null);
		if (empInfo.getInactive()) {
			empInfo.setInactiveDate(add_date);
			empInfo.setInactiveUser(loginUsersView.getUserId());
		} else {
			empInfo.setInactiveDate(null);
			empInfo.setInactiveUser(null);
			empInfo.setInactiveReason(null);
		}
		empInfoDAO.addEmpInfo(empInfo);
	}

	@Override
	@Transactional
	public void updateEmpInfo(UsersView loginUsersView, EmpInfoView empInfoView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.MODIFY);
		}
		// Non-database validation
		coreValidationService.notNull(empInfoView.getEmpNo(), "emp_no");
		coreValidationService.greaterThanZero(empInfoView.getEmpNo(), "emp_no");
		coreValidationService.notNull(empInfoView.getEmpDName(), "name");
		coreValidationService.notBlank(empInfoView.getEmpDName(), "name");
		if ((empInfoView.getEmpFName() != null) && empInfoView.getEmpFName().isBlank())
			empInfoView.setEmpFName(null);
		// Database validation
		EmpInfo empInfo = getEmpInfoFromEmpInfoView(empInfoView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("emp_no", empInfo.getEmpNo());
		if (!generalDAO.isEntityExist("emp_info", conditions))
			throw new ValidationException("not_exist", "emp_no");
		conditions.clear();
		conditions.put("emp_d_name", empInfo.getEmpDName());
		String exceptionCondition = null;
		exceptionCondition = " and emp_no != " + empInfo.getEmpNo();
		if (generalDAO.isEntityExist("emp_info", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "name");
		conditions.clear();
		conditions.put("emp_f_name", empInfo.getEmpFName());
		if (empInfo.getEmpFName() != null && generalDAO.isEntityExist("emp_info", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "foreign_name");
		conditions.clear();	
		// Update the empInfo
		Timestamp update_date = new Timestamp(new Date().getTime());
		empInfo.setModifyDate(update_date);
		empInfo.setModifyUser(loginUsersView.getUserId());
		EmpInfoView DBEmpInfoView = empInfoDAO.getEmpInfoView(empInfo.getEmpNo());
		if (DBEmpInfoView.getInactive() && !empInfo.getInactive()) {
			empInfo.setInactiveDate(null);
			empInfo.setInactiveUser(null);
			empInfo.setInactiveReason(null);

		} else if (!DBEmpInfoView.getInactive() && empInfo.getInactive()) {
			empInfo.setInactiveDate(update_date);
			empInfo.setInactiveUser(loginUsersView.getUserId());
		} else {
			empInfo.setInactiveDate(DBEmpInfoView.getInactiveDate());
			empInfo.setInactiveUser(DBEmpInfoView.getInactiveUser());
			empInfo.setInactiveReason(DBEmpInfoView.getInactiveReason());
		}
		empInfoDAO.updateEmpInfo(empInfo);
	}

	@Override
	@Transactional
	public void deleteEmpInfo(UsersView loginUsersView, Integer empNo) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.DELETE);
		}
		// Non-database validation
		coreValidationService.notNull(empNo, "emp_no");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("emp_no", empNo);
		if (!generalDAO.isEntityExist("emp_info", conditions))
			throw new ValidationException("not_exist", "emp_no");
		// delete the usersempInfo
		try {
			empInfoDAO.deleteEmpInfo(empNo);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "emp_no");
		}
	}
	
	@Override
	@Transactional
	public PreData preAdd(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.ADD);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		defaultValues.put("emp_no", empInfoDAO.getNextPK());
		// return the data
		return preData;
	}
	
	@Override
	@Transactional
	public PreData preModify(UsersView loginUsersView) {
		// Check module, form, privileges
		if (!loginUsersView.getSuperAdmin()) {
			if (loginUsersView.getAdminUser()) {
				coreValidationService.activeModule(Forms.EMPLOYEE_INFORMATION);
			} else {
				coreValidationService.activeModuleAndForm(Forms.EMPLOYEE_INFORMATION);
			}
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.INCLUDE);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.VIEW);
			coreValidationService.validateHasFormPrivilege(loginUsersView, Forms.EMPLOYEE_INFORMATION, FormsActions.MODIFY);
		}
		Set<String> readOnly = new HashSet<>();
		Map<String, Object> defaultValues = new HashMap<>();
		Map<String, Object> info = new HashMap<>();
		PreData preData = new PreData(readOnly, defaultValues, info);
		// Fill preData object
		defaultValues.put("emp_no", empInfoDAO.getNextPK());
		// return the data
		return preData;
	}

	public EmpInfo getEmpInfoFromEmpInfoView(EmpInfoView empInfoView) {
		EmpInfo empInfo = new EmpInfo();
		try {
			empInfo.setAddDate(empInfoView.getAddDate());
			empInfo.setAddUser(empInfoView.getAddUser());
			empInfo.setEmpDName(Utils.escapeLiteral(null, empInfoView.getEmpDName(), true).toString());
			if (empInfoView.getEmpFName() == null)
				empInfo.setEmpFName(empInfoView.getEmpFName());
			else
				empInfo.setEmpFName(Utils.escapeLiteral(null, empInfoView.getEmpFName(), true).toString());
			empInfo.setEmpNo(empInfoView.getEmpNo());
			empInfo.setInactive(empInfoView.getInactive());
			empInfo.setInactiveDate(empInfoView.getInactiveDate());
			if (empInfoView.getInactiveReason() == null)
				empInfo.setInactiveReason(empInfoView.getInactiveReason());
			else
				empInfo.setInactiveReason(Utils.escapeLiteral(null, empInfoView.getInactiveReason(), true).toString());
			empInfo.setInactiveUser(empInfoView.getInactiveUser());
			empInfo.setModifyDate(empInfoView.getModifyDate());
			empInfo.setModifyUser(empInfoView.getModifyUser());
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return empInfo;
	}

}
