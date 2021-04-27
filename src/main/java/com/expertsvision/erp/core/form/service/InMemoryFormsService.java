package com.expertsvision.erp.core.form.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.form.entity.FormsView;
import com.expertsvision.erp.core.user.entity.UsersView;


@Service
@Lazy
public class InMemoryFormsService {
	
	
	private FormsService formsViewService;
	
	private Map<Integer, FormsView> formsViewMap;
	
	private UsersView superAdmin = new UsersView();
	
	
	@Autowired
	public InMemoryFormsService(FormsService formsViewService) {
		this.formsViewService = formsViewService;
		superAdmin.setSuperAdmin(true);
		List<FormsView> formsViewList = formsViewService.getFormsViewList(superAdmin);
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
		List<FormsView> formsViewList = formsViewService.getFormsViewList(superAdmin);
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
