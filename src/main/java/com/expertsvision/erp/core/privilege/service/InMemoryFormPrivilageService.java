package com.expertsvision.erp.core.privilege.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expertsvision.erp.core.privilege.entity.FormPrivilagePK;
import com.expertsvision.erp.core.privilege.entity.FormPrivilageView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.GeneralConstants;


@Service
public class InMemoryFormPrivilageService {
	
	
	private FormPrivilageService formPrivilageService;
	
	private Map<FormPrivilagePK, FormPrivilageView> formPrivilageViewMap;
	
	
	@Autowired
	public InMemoryFormPrivilageService(FormPrivilageService formPrivilageService) {
		this.formPrivilageService = formPrivilageService;
		List<FormPrivilageView> formPrivilageViewList = formPrivilageService.getFormPrivilageViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
		Map<FormPrivilagePK, FormPrivilageView> newFormPrivilageViewMap = convertToFormPrivilageMap(formPrivilageViewList);
		synchronized (this) {
			formPrivilageViewMap = newFormPrivilageViewMap;
		}
	}
	
	public FormPrivilageView getFormPrivilageView(FormPrivilagePK formPrivilagePK) {
		FormPrivilageView formPrivilageView = formPrivilageViewMap.get(formPrivilagePK);
		return formPrivilageView;
	}
	
	public void updateFormPrivilageViewMap() {
		List<FormPrivilageView> formPrivilageViewList = formPrivilageService.getFormPrivilageViewList(new UsersView(GeneralConstants.SUPER_USER_NO));
		Map<FormPrivilagePK, FormPrivilageView> newFormPrivilageViewMap = convertToFormPrivilageMap(formPrivilageViewList);
		synchronized (this) {
			formPrivilageViewMap = newFormPrivilageViewMap;
		}
	}
	
	private Map<FormPrivilagePK, FormPrivilageView> convertToFormPrivilageMap(List<FormPrivilageView> formPrivilageViewList) {
		Map<FormPrivilagePK, FormPrivilageView> formPrivilageViewMap = new HashMap<FormPrivilagePK, FormPrivilageView>();
		for (FormPrivilageView formPrivilageView : formPrivilageViewList) {
			formPrivilageViewMap.put(new FormPrivilagePK(formPrivilageView.getUserId(), formPrivilageView.getFormNo()), formPrivilageView);
		}
		return formPrivilageViewMap;
	}


}
