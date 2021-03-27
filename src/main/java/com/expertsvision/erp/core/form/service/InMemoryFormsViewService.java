package com.expertsvision.erp.core.form.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralConstants;


@Service
public class InMemoryFormsViewService {
	
	
	
	private FormsViewService formsViewService;
	
	private Map<Integer, FormsView> formsViewMap;
	
	
	@Autowired
	public InMemoryFormsViewService(FormsViewService formsViewService) {
		this.formsViewService = formsViewService;
		List<FormsView> formsViewList = formsViewService.getFormsViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
		Map<Integer, FormsView> NewFormsViewMap = convertToFormsViewMap(formsViewList);
		synchronized (this) {
			formsViewMap = NewFormsViewMap;
		}
	}
	
	public FormsView getFormsView(Integer formNo) {
		FormsView formsView = formsViewMap.get(formNo);
		return formsView;
	}
	
	public void updateFormsView() {
		List<FormsView> formsViewList = formsViewService.getFormsViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
		Map<Integer, FormsView> NewFormsViewMap = convertToFormsViewMap(formsViewList);
		synchronized (this) {
			formsViewMap = NewFormsViewMap;
		}
	}
	
	private  Map<Integer, FormsView> convertToFormsViewMap(List<FormsView> formsViewList) {
		Map<Integer, FormsView> formsViewMap = new HashMap<Integer, FormsView>();
		for (FormsView formsView : formsViewList) {
			if (!formsView.getActive()) {
				setAllChildrenToInactive(formsViewList, formsView.getFormNo());
			}
		}
		for (FormsView formsView : formsViewList) {
			formsViewMap.put(formsView.getFormNo(), formsView);
		}
		return formsViewMap;
	}
	
	private void setAllChildrenToInactive(List<FormsView> formsViewList, Integer formNo) {
		for (FormsView formsView : formsViewList) {
			if (formsView.getParentForm().equals(formNo)) {
				formsView.setActive(false);
				setAllChildrenToInactive(formsViewList, formsView.getFormNo());
			}
		}
	}


}
