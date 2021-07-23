package com.expertsvision.erp.core.flagdetail.service;

import java.sql.SQLException;
import java.util.ArrayList;
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
import com.expertsvision.erp.core.flagdetail.dao.FlagDetailDAO;
import com.expertsvision.erp.core.flagdetail.dto.FlagDetailViewFilter;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetail;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailMainTree;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.user.service.InMemoryUsersService;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;

@Service
public class FlagDetailServiceImpl implements FlagDetailService {

	@Autowired
	private FlagDetailDAO flagDetailDAO;

	@Autowired
	private GeneralDAO generalDAO;

	@Autowired
	private CoreValidationService coreValidationService;
	
	@Autowired
	private FlagDetailService flagDetailService;

	@Autowired
	@Lazy
	private InMemoryFlagDetailService inMemoryFlagDetailService;
	
	@Autowired
	@Lazy
	private InMemoryUsersService inMemoryUsersService;

	@Override
	@Transactional
	public List<FlagDetailView> getFlagDetailViewList() {
		// Return requested data
		List<FlagDetailView> flagDetailViewList = flagDetailDAO.getFlagDetailViewList();
		return flagDetailViewList;
	}

	@Override
	@Transactional
	public List<FlagDetailView> getFlagDetailViewList(UsersView loginUser, String flagCode) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		List<FlagDetailView> flagDetailViewList = flagDetailDAO.getFlagDetailViewList(flagCode);
		return flagDetailViewList;
	}
	
	
	@Override
	@Transactional
	public List<FlagDetailMainTree> getFlagDetailMainTree(Integer userId) {
		UsersView loginUser = inMemoryUsersService.getUsersView(userId);
		List<FlagDetailMainTree> FlagDetailMainTree;
		// Return requested data
		if (loginUser.getSuperAdmin() || loginUser.getAdminUser()) {
			List<FlagDetailView> flagDetailViewList = flagDetailService.getFlagDetailViewList();
			FlagDetailMainTree = new ArrayList<>();
			FlagDetailMainTree NewFlagDetailMainTree;
			for (FlagDetailView flagDetailView : flagDetailViewList) {
				NewFlagDetailMainTree = new FlagDetailMainTree();
				NewFlagDetailMainTree.setActive(true);
//				NewFlagDetailMainTree.setAddDate(null);
				NewFlagDetailMainTree.setAddPriv(true);
//				NewFlagDetailMainTree.setAddUser(null);
				NewFlagDetailMainTree.setDeletePriv(true);
				NewFlagDetailMainTree.setFlagCode(flagDetailView.getFlagCode());
				NewFlagDetailMainTree.setFlagPriv(flagDetailView.getFlagPriv());
				NewFlagDetailMainTree.setFlagSr(flagDetailView.getFlagSr());
				NewFlagDetailMainTree.setFlagValue(flagDetailView.getFlagValue());
				NewFlagDetailMainTree.setLabelCode(flagDetailView.getLabelCode());
//				NewFlagDetailMainTree.setModifyDate(null);
				NewFlagDetailMainTree.setModifyPriv(true);
//				NewFlagDetailMainTree.setModifyUser(null);
				NewFlagDetailMainTree.setPrintPriv(true);
				NewFlagDetailMainTree.setUserId(userId);
				NewFlagDetailMainTree.setViewPriv(true);
				FlagDetailMainTree.add(NewFlagDetailMainTree);
			}
		} else {
			FlagDetailMainTree = flagDetailDAO.getFlagDetailMainTree(userId);
		}
		return FlagDetailMainTree;
	}

	@Override
	@Transactional
	public FlagDetailView getFlagDetailView(UsersView loginUser, String flagCode, Integer flagValue) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		FlagDetailView flagDetailView = flagDetailDAO.getFlagDetailView(flagCode, flagValue);
		if (flagDetailView == null) {
			throw new ValidationException("not_exist", "flag_detail");
		}
		return flagDetailView;
	}

	@Override
	@Transactional
	public SinglePage<FlagDetailView> getFlagDetailsViewSinglePage(UsersView loginUser, String flagCode, long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<FlagDetailView> singlePage = flagDetailDAO.getFlagDetailsViewSinglePage(flagCode, pageNo);
		return singlePage;
	}

	@Override
	@Transactional
	public SinglePage<FlagDetailView> getFlagDetailsViewLastPage(UsersView loginUser, String flagCode) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		SinglePage<FlagDetailView> singlePage = flagDetailDAO.getFlagDetailsViewLastPage(flagCode);
		return singlePage;
	}

	@Override
	@Transactional
	public Long getFlagDetailViewSinglePageNo(UsersView loginUser, String flagCode, Integer flagValue) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		Long singlePageNo = flagDetailDAO.getFlagDetailViewSinglePageNo(flagCode, flagValue);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "flag_detail");
		}
		return singlePageNo;
	}

	@Override
	@Transactional
	public MultiplePages<FlagDetailView> getFlagDetailsViewMultiplePages(UsersView loginUser, String flagCode,
			long pageNo) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<FlagDetailView> multiplePages = flagDetailDAO.getFlagDetailsViewMultiplePages(flagCode, pageNo);
		return multiplePages;
	}

	@Override
	@Transactional
	public MultiplePages<FlagDetailView> getFlagDetailsViewFilteredMultiplePages(UsersView loginUser, String flagCode,
			long pageNo, FlagDetailViewFilter FlagDetailViewFilter) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Return requested data
		MultiplePages<FlagDetailView> multiplePages = flagDetailDAO.getFlagDetailsViewFilteredMultiplePages(flagCode,
				pageNo, FlagDetailViewFilter);
		return multiplePages;
	}

	@Override
	@Transactional
	public void addFlagDetail(UsersView loginUser, FlagDetailView flagDetailView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(flagDetailView.getFlagCode(), "flag_code");
		coreValidationService.notBlank(flagDetailView.getFlagCode(), "flag_code");
		coreValidationService.notNull(flagDetailView.getLabelCode(), "label_code");
		coreValidationService.notBlank(flagDetailView.getLabelCode(), "label_code");
		coreValidationService.notNull(flagDetailView.getActive(), "active");
		coreValidationService.notNull(flagDetailView.getFlagPriv(), "flag_priv");
		coreValidationService.notNull(flagDetailView.getFlagSr(), "flag_sr");
		coreValidationService.greaterThanZero(flagDetailView.getFlagSr(), "flag_sr");
		coreValidationService.notNull(flagDetailView.getFlagValue(), "flag_value");
		coreValidationService.greaterThanZero(flagDetailView.getFlagValue(), "flag_value");
		// Database validation
		FlagDetail flagDetail = getFlagDetailFromFlagDetailView(flagDetailView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("flag_code", flagDetail.getFlagCode());
		conditions.put("flag_value", flagDetail.getFlagValue());
		if (generalDAO.isEntityExist("flag_detail", conditions))
			throw new ValidationException("already_exist", "flag_detail_code");
		conditions.clear();
		conditions.put("flag_code", flagDetail.getFlagCode());
		conditions.put("label_code", flagDetail.getLabelCode());
		if (generalDAO.isEntityExist("flag_detail", conditions))
			throw new ValidationException("already_exist", "label_code");
		conditions.clear();
		conditions.put("label_code", flagDetail.getLabelCode());
		if (!generalDAO.isEntityExist("labels", conditions))
			throw new ValidationException("not_exist", "label_code");
		conditions.clear();
		// Add the flagDetail
		flagDetailDAO.addFlagDetail(flagDetail);
		inMemoryFlagDetailService.updateFlagDetailView();
	}

	@Override
	@Transactional
	public void updateFlagDetail(UsersView loginUser, FlagDetailView flagDetailView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Non-database validationcoreValidation
		coreValidationService.notBlank(flagDetailView.getFlagCode(), "flag_code");
		coreValidationService.notNull(flagDetailView.getLabelCode(), "label_code");
		coreValidationService.notBlank(flagDetailView.getLabelCode(), "label_code");
		coreValidationService.notNull(flagDetailView.getActive(), "active");
		coreValidationService.notNull(flagDetailView.getFlagPriv(), "flag_priv");
		coreValidationService.notNull(flagDetailView.getFlagSr(), "flag_sr");
		coreValidationService.greaterThanZero(flagDetailView.getFlagSr(), "flag_sr");
		coreValidationService.notNull(flagDetailView.getFlagValue(), "flag_value");
		coreValidationService.greaterThanZero(flagDetailView.getFlagValue(), "flag_value");
		// Database validation
		FlagDetail flagDetail = getFlagDetailFromFlagDetailView(flagDetailView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("flag_code", flagDetail.getFlagCode());
		conditions.put("flag_value", flagDetail.getFlagValue());
		if (!generalDAO.isEntityExist("flag_detail", conditions))
			throw new ValidationException("not_exist", "flag_detail_code");
		conditions.clear();
		conditions.put("flag_code", flagDetail.getFlagCode());
		conditions.put("label_code", flagDetail.getLabelCode());
		String exceptionCondition = null;
		exceptionCondition = " and flag_value != " + flagDetail.getFlagValue();
		if (generalDAO.isEntityExist("flag_detail", conditions, exceptionCondition))
			throw new ValidationException("already_exist", "label_code");
		conditions.clear();
		conditions.put("label_code", flagDetail.getLabelCode());
		if (!generalDAO.isEntityExist("labels", conditions))
			throw new ValidationException("not_exist", "label_code");
		conditions.clear();
		// Update the flagDetail
		flagDetailDAO.updateFlagDetail(flagDetail);
		inMemoryFlagDetailService.updateFlagDetailView();
	}

	@Override
	@Transactional
	public void deleteFlagDetail(UsersView loginUser, String flagCode, Integer flagValue) {
		// Check form privileges
		if (!loginUser.getSuperAdmin())
			throw new UnauthorizedException("resource");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("flag_code", flagCode);
		conditions.put("flag_value", flagValue);
		if (!generalDAO.isEntityExist("flag_detail", conditions))
			throw new ValidationException("not_exist", "flag_detail");
		conditions.clear();
		conditions.put("flag_code", flagCode);
		conditions.put("flag_value", flagValue);
		if (generalDAO.isEntityExist("flag_priv", conditions))
			throw new ValidationException("used_in_screen", "flag_value", "flag_priv");
		// delete the flagDetail
		try {
			flagDetailDAO.deleteFlagDetail(flagCode, flagValue);
		} catch (Exception e) {
			throw new ValidationException("flag_detail", "flag");
		}
		inMemoryFlagDetailService.updateFlagDetailView();
	}

	public FlagDetail getFlagDetailFromFlagDetailView(FlagDetailView flagDetailView) {
		FlagDetail flagDetail = new FlagDetail();
		try {
			flagDetail.setActive(flagDetailView.getActive());
			flagDetail.setFlagCode(Utils.escapeLiteral(null, flagDetailView.getFlagCode(), true).toString());
			flagDetail.setFlagPriv(flagDetailView.getFlagPriv());
			flagDetail.setFlagSr(flagDetailView.getFlagSr());
			flagDetail.setFlagValue(flagDetailView.getFlagValue());
			flagDetail.setLabelCode(Utils.escapeLiteral(null, flagDetailView.getLabelCode(), true).toString());
			flagDetail.setLangNo(1);
		} catch (SQLException e) {
			throw new UnauthorizedException("resource");
		}
		return flagDetail;
	}

}
