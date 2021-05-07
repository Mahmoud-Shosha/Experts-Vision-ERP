package com.expertsvision.erp.core.flagmaster.service;

import java.sql.SQLException;
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
import com.expertsvision.erp.core.flagmaster.dao.FlagMasterDAO;
import com.expertsvision.erp.core.flagmaster.dto.FlagMasterViewFilter;
import com.expertsvision.erp.core.flagmaster.entity.FlagMaster;
import com.expertsvision.erp.core.flagmaster.entity.FlagMasterView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class FlagMasterServiceImpl implements FlagMasterService {

	@Autowired
	private FlagMasterDAO flagMasterDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;

	@Autowired
	@Lazy
	private InMemoryFlagMasterService inMemoryFlagMasterService;

	@Override
	@Transactional
	public List<FlagMasterView> getFlagMasterViewList() {
		List<FlagMasterView> flagMasterViewList = flagMasterDAO.getFlagMasterViewList();
		return flagMasterViewList;
	}

	@Override
	@Transactional
	public FlagMasterView getFlagMasterView(String flagCode) {
		FlagMasterView flagMasterView = flagMasterDAO.getFlagMasterView(flagCode);
		if (flagMasterView == null) {
			throw new ValidationException("not_exist", "flag");
		}
		return flagMasterView;
	}

	@Override
	@Transactional
	public SinglePage<FlagMasterView> getFlagMastersViewSinglePage(UsersView loginUser, long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<FlagMasterView> singlePage = flagMasterDAO.getFlagMastersViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<FlagMasterView> getFlagMastersViewLastPage(UsersView loginUser) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<FlagMasterView> singlePage = flagMasterDAO.getFlagMastersViewLastPage();
		return singlePage;
	}

	@Override
	@Transactional
	public Long getFlagMasterViewSinglePageNo(UsersView loginUser, String flagCode) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		Long singlePageNo = flagMasterDAO.getFlagMasterViewSinglePageNo(flagCode);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "flag");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<FlagMasterView> getFlagMastersViewMultiplePages(UsersView loginUser, long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<FlagMasterView> multiplePages = flagMasterDAO.getFlagMastersViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<FlagMasterView> getFlagMastersViewFilteredMultiplePages(UsersView loginUser, long pageNo,
			FlagMasterViewFilter FlagMasterViewFilter) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<FlagMasterView> multiplePages = flagMasterDAO.getFlagMastersViewFilteredMultiplePages(pageNo,
				FlagMasterViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addFlagMaster(UsersView loginUser, FlagMasterView flagMasterView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(flagMasterView.getFlagCode(), "flag_code");
		coreValidationService.notBlank(flagMasterView.getFlagCode(), "flag_code");
		coreValidationService.notNull(flagMasterView.getLabelCode(), "label_code");
		coreValidationService.notBlank(flagMasterView.getLabelCode(), "label_code");
		// Database validation
		FlagMaster flagMaster = getFlagMasterFromFlagMasterView(flagMasterView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("flag_code", flagMaster.getFlagCode());
		if (generalDAO.isEntityExist("flag_master", conditions))
			throw new ValidationException("already_exist", "flag_code");
		conditions.clear();
		conditions.put("label_code", flagMaster.getLabelCode());
		if (generalDAO.isEntityExist("flag_master", conditions))
			throw new ValidationException("already_exist", "label_code");
		conditions.clear();
		conditions.put("label_code", flagMaster.getLabelCode());
		if (!generalDAO.isEntityExist("labels", conditions))
			throw new ValidationException("not_exist", "label_code");
		conditions.clear();
		// Add the flagMaster
		flagMasterDAO.addFlagMaster(flagMaster);
		inMemoryFlagMasterService.updateFlagMasterView();
	}

	@Override
	@Transactional
	public void updateFlagMaster(UsersView loginUser, FlagMasterView flagMasterView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(flagMasterView.getFlagCode(), "flag_code");
		coreValidationService.notBlank(flagMasterView.getFlagCode(), "flag_code");
		coreValidationService.notNull(flagMasterView.getLabelCode(), "label_code");
		coreValidationService.notBlank(flagMasterView.getLabelCode(), "label_code");
		// Database validation
		FlagMaster flagMaster = getFlagMasterFromFlagMasterView(flagMasterView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("flag_code", flagMaster.getFlagCode());
		if (!generalDAO.isEntityExist("flag_master", conditions))
			throw new ValidationException("not_exist", "flag_code");
		conditions.clear();
		conditions.put("label_code", flagMaster.getLabelCode());
		String exceptionCondition = null;
		exceptionCondition = " and flag_code != " + "'" + flagMaster.getFlagCode() + "'";
		if (generalDAO.isEntityExist("flag_master", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "label_code");
		conditions.clear();
		conditions.put("label_code", flagMaster.getLabelCode());
		if (!generalDAO.isEntityExist("labels", conditions))
			throw new ValidationException("not_exist", "label_code");
		conditions.clear();
		// Update the flagMaster
		flagMasterDAO.updateFlagMaster(flagMaster);
		inMemoryFlagMasterService.updateFlagMasterView();
	}

	@Override
	@Transactional
	public void deleteFlagMaster(UsersView loginUser, String flagCode) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("flag_code", flagCode);
		if (!generalDAO.isEntityExist("flag_master", conditions))
			throw new ValidationException("not_exist", "flag");
		conditions.clear();
		conditions.put("flag_code", flagCode);
		if (generalDAO.isEntityExist("flag_detail", conditions))
			throw new ValidationException("used_in_screen", "flag_detail", "flag_code");
		// delete the flagMaster
		try {
			flagMasterDAO.deleteFlagMaster(flagCode);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "flag");
		}
		inMemoryFlagMasterService.updateFlagMasterView();
	}

	public FlagMaster getFlagMasterFromFlagMasterView(FlagMasterView flagMasterView) {
		FlagMaster flagMaster = new FlagMaster();
		try {
			flagMaster.setFlagCode(Utils.escapeLiteral(null, flagMasterView.getFlagCode(), true).toString());
			flagMaster.setLabelCode(Utils.escapeLiteral(null, flagMasterView.getLabelCode(), true).toString());
			flagMaster.setLangNo(1);
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return flagMaster;
	}

}
