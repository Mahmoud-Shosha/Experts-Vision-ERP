package com.expertsvision.erp.core.label.service;

import java.util.List;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.expertsvision.erp.core.exception.UnauthorizedException;
import com.expertsvision.erp.core.exception.ValidationException;
import com.expertsvision.erp.core.label.dao.LabelsViewDAO;
import com.expertsvision.erp.core.label.dto.LabelsViewFilter;
import com.expertsvision.erp.core.label.entity.LabelsView;
import com.expertsvision.erp.core.label.entity.LabelsViewPK;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.Forms;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.core.validation.CoreValidationService;


@Service
@Transactional
public class LabelsViewServiceImpl implements LabelsViewService {
	
	@Autowired
	private LabelsViewDAO labelsViewDAO;
	
	@Autowired
	private CoreValidationService coreValidationService;

	
	@Override
	public List<LabelsView> getLabelsViewList() {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		List<LabelsView> labelsViewList = null;
		try {
			labelsViewList = labelsViewDAO.getLabelsViewList();
		} catch (HibernateException e) {
			throw new UnauthorizedException("resource");
		}
		return labelsViewList;
	}
	
	
	@Override
	public LabelsView getLabelsView(LabelsViewPK labelsViewPK) {
		LabelsView labelsView = null;
		try {
			labelsView = labelsViewDAO.getLabelsView(labelsViewPK);
		} catch (HibernateException e) {
			throw new UnauthorizedException("resource");
		}
		if (labelsView == null) {
			throw new ValidationException("not_exist", "label");
		}
		return labelsView;
	}
	
	
	@Override
	public SinglePage<LabelsView> getLabelsViewSinglePage(long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		SinglePage<LabelsView> singlePage = null;
		try {
			singlePage = labelsViewDAO.getLabelsViewSinglePage(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public SinglePage<LabelsView> getLabelsViewLastPage() {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		SinglePage<LabelsView> singlePage = null;
		try {
			singlePage = labelsViewDAO.getLabelsViewLastPage();
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return singlePage;
	}
	
	@Override
	public MultiplePages<LabelsView> getLabelsViewMultiplePages(long pageNo) {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		MultiplePages<LabelsView> multiplePages = null;
		try {
			multiplePages = labelsViewDAO.getLabelsViewMultiplePages(pageNo);
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	@Override
	public MultiplePages<LabelsView> getLabelsViewFilteredMultiplePages(long pageNo, LabelsViewFilter LabelViewFilter) {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		MultiplePages<LabelsView> multiplePages = null;
		try {
			multiplePages = labelsViewDAO.getLabelsViewFilteredMultiplePages(pageNo, LabelViewFilter);
		} catch (Exception e) {
			throw new UnauthorizedException("resource");
		}
		return multiplePages;
	}
	
	
	@Override
	public void addLabelsView(UsersView loginUser, LabelsView labelsView) {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		coreValidationService.notNull(labelsView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(labelsView.getLangNo(), "lang_no");
		coreValidationService.notNull(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notBlank(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notNull(labelsView.getLabelCode(), "label_code");
		coreValidationService.notBlank(labelsView.getLabelCode(), "label_code");
		coreValidationService.runDatabaseValidation(labelsViewDAO.addLabelsView(loginUser, labelsView));	
	}
	
	@Override
	public void updateLabelsView(UsersView loginUser, LabelsView labelsView) {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		coreValidationService.notNull(labelsView.getLangNo(), "lang_no");
		coreValidationService.greaterThanZero(labelsView.getLangNo(), "lang_no");
		coreValidationService.notNull(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notBlank(labelsView.getLabelDesc(), "label_desc");
		coreValidationService.notNull(labelsView.getLabelCode(), "label_code");
		coreValidationService.notBlank(labelsView.getLabelCode(), "label_code");
		coreValidationService.runDatabaseValidation(labelsViewDAO.updateLabelsView(loginUser, labelsView));		
	}
	
	@Override
	public void deleteLabelsView(UsersView loginUser, LabelsViewPK labelsViewPK) {
		coreValidationService.activeModuleAndForm(Forms.LABELS);
		try {
			coreValidationService.runDatabaseValidation(labelsViewDAO.deleteLabelsView(loginUser, labelsViewPK));
		} catch (HibernateException e) {
			throw new ValidationException("used_somewhere", "label");
		}
	}

}
