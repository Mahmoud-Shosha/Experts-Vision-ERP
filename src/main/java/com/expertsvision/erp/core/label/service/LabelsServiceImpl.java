package com.expertsvision.erp.core.label.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.postgresql.core.Utils;
import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.label.dao.LabelsDAO;
import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.Label;
import com.expertsvision.erp.core.label.entity.LabelsPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;


@Service
public class LabelsServiceImpl implements LabelsService {
	
	@Autowired
	private LabelsDAO labelsViewDAO;
	
	@Autowired
	private GeneralDAO generalDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;

	
	@Override
	@Transactional
	public List<LabelsView> getLabelsViewList() {
		List<LabelsView> labelsViewList = labelsViewDAO.getLabelsViewList();
		return labelsViewList;
	}
	
	
	@Override
	@Transactional
	public LabelsView getLabelsView(LabelsPK labelsViewPK) {
		LabelsView labelsView = labelsViewDAO.getLabelsView(labelsViewPK);
		if (labelsView == null) {
			throw new ValidationException("not_exist", "label");
		}
		return labelsView;
	}
	
	@Override
	@Transactional
	public SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo) {
		 SinglePage<LabelsView> singlePage = labelsViewDAO.getLabelsViewSinglePage(pageNo);
		return singlePage;
	}
	
	@Override
	@Transactional
	public SinglePage<LabelsView> getLabelsViewLastPage() {
		SinglePage<LabelsView> singlePage = labelsViewDAO.getLabelsViewLastPage();
		return singlePage;
	}
	
	@Override
	@Transactional
	public Long getLabelsViewSinglePageNo(LabelsPK labelsViewPK) {
		 Long singlePageNo = labelsViewDAO.getLabelsViewSinglePageNo(labelsViewPK);
		if (singlePageNo == null) {
			throw new ValidationException("not_exist", "label");
		}
		return singlePageNo;
	}
	
	@Override
	@Transactional
	public MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo) {
		MultiplePages<LabelsView> multiplePages = labelsViewDAO.getLabelsViewMultiplePages(pageNo);
		return multiplePages;
	}
	
	@Override
	@Transactional
	public MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter) {
		MultiplePages<LabelsView> multiplePages = labelsViewDAO.getLabelsViewFilteredMultiplePages(pageNo, LabelViewFilter);
		return multiplePages;
	}
	
	
	@Override
	@Transactional
	public void addLabel(UsersView loginUser, LabelsView labelsView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(labelsView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(labelsView.getLangNo(), "lang_no");
		coreValidationService.notNull(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notBlank(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notNull(labelsView.getLabelCode(), "label_code");
		coreValidationService.notBlank(labelsView.getLabelCode(), "label_code");
		// Database validation
		Label label = getLabelFromLabelsView(labelsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("label_code", label.getLabelCode());
		conditions.put("lang_no", label.getLangNo());
		if (generalDAO.isEntityExist("labels", conditions)) throw new ValidationException("already_exist", "label_code");
		conditions.clear();
		conditions.put("label_desc", label.getLabelDesc());
		if (generalDAO.isEntityExist("labels", conditions)) throw new ValidationException("already_exist", "label_desc");
		conditions.clear();
		conditions.put("lang_no", label.getLangNo());
		if (!generalDAO.isEntityExist("language", conditions)) throw new ValidationException("not_exist", "lang_no");
		// Add the label
		label.setAddDate(new Timestamp(new Date().getTime()));
		label.setAddUser(loginUser.getUserId());
		label.setModifyDate(null);
		label.setModifyUser(null);
		labelsViewDAO.addLabel(label);
	}
	
	@Override
	@Transactional
	public void updateLabel(UsersView loginUser, LabelsView labelsView) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Non-database validation
		coreValidationService.notNull(labelsView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(labelsView.getLangNo(), "lang_no");
		coreValidationService.notNull(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notBlank(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notNull(labelsView.getLabelCode(), "label_code");
		coreValidationService.notBlank(labelsView.getLabelCode(), "label_code");
		// Database validation
		Label label = getLabelFromLabelsView(labelsView);
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("label_code", label.getLabelCode());
		conditions.put("lang_no", label.getLangNo());
		if (!generalDAO.isEntityExist("labels", conditions)) throw new ValidationException("not_exist", "label");
		conditions.clear();
		conditions.put("label_desc", label.getLabelDesc());
		String exceptionCondition = null;
		try {
			exceptionCondition = " and not (label_code = '" + 
					Utils.escapeLiteral(null, label.getLabelCode()==null?"":label.getLabelCode().toString().strip(), true) +
					"' and lang_no = " + 
					Utils.escapeLiteral(null, label.getLangNo()==null?"":label.getLangNo().toString().strip(), true) +
					") ";
		} catch (SQLException e) {
			 throw new UnauthorizedException("resource");
		}
		if (generalDAO.isEntityExist("labels", conditions, exceptionCondition)) throw new ValidationException("already_exist", "label_desc");
		conditions.clear();
		conditions.put("lang_no", label.getLangNo());
		if (!generalDAO.isEntityExist("language", conditions)) throw new ValidationException("not_exist", "lang_no");
		// Update the label
		label.setModifyDate(new Timestamp(new Date().getTime()));
		label.setModifyUser(loginUser.getUserId());
		labelsViewDAO.updateLabel(label);
	}
	
	@Override
	@Transactional
	public void deleteLabel(UsersView loginUser, LabelsPK labelsViewPK) {
		// Check form privileges
		if (!loginUser.getSuperAdmin()) throw new UnauthorizedException("resource");
		// Database validation
		Map<String, Object> conditions = new HashMap<>();
		conditions.put("label_code", labelsViewPK.getLabelCode());
		conditions.put("lang_no", labelsViewPK.getLangNo());
		if (!generalDAO.isEntityExist("labels", conditions)) throw new ValidationException("not_exist", "label");
		// delete the label
		try {
			labelsViewDAO.deleteLabel(labelsViewPK);
		} catch (Exception e) {
			throw new ValidationException("used_somewhere", "label");
		}
	}
	
	public Label getLabelFromLabelsView(LabelsView labelsView)  {
		Label label = new Label();
		try {
			label.setAddDate(labelsView.getAddDate());
			label.setAddUser(labelsView.getAddUser());
			label.setLabelCode(Utils.escapeLiteral(null, labelsView.getLabelCode(), true).toString());
			label.setLabelDesc(Utils.escapeLiteral(null, labelsView.getLabelDesc(), true).toString());
			label.setLangNo(labelsView.getLangNo());
			label.setModifyDate(labelsView.getModifyDate());
			label.setModifyUser(labelsView.getModifyUser());
		} catch (SQLException e) {
			 throw new UnauthorizedException("resource");
		}
		return label;
	}

}
