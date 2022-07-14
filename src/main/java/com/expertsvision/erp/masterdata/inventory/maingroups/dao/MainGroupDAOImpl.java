package com.expertsvision.erp.masterdata.inventory.maingroups.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.expertsvision.erp.core.shared.masterwithpriv.MasterWithPrivDAO;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.inventory.maingroups.dto.MainGroupViewFilter;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroup;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupPriv;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupView;

@Repository
public class MainGroupDAOImpl extends MasterWithPrivDAO<MainGroup, MainGroupView> implements MainGroupDAO {

	public MainGroupDAOImpl() {
		this.masterEntityClass = MainGroup.class;
		this.masterEntityViewClass = MainGroupView.class;
		this.PKName = "group_code";
		this.orderByColumns = "group_code";
		this.MASTER_ENTITY_VIEW_WITH_PRIVS = "SELECT main_group_view.* FROM main_group_priv AS priv "
				+ "LEFT JOIN main_group_view AS main_group_view ON main_group_view.group_code  = priv.group_code "
				+ "WHERE priv.user_id = :userId AND priv.view_priv = true ORDER BY (main_group_view.group_code) ";

	}

	@Override
	public MainGroupView getMainGroupView(String groupCode) {
		MainGroupView mainGroupView = getMasterEntityView(groupCode);
		return mainGroupView;
	}

	@Override
	public SinglePage<MainGroupView> getMainGroupViewSinglePage(long pageNo) {
		SinglePage<MainGroupView> singlePage = getMasterEntityViewSinglePage(pageNo);
		return singlePage;
	}

	@Override
	public SinglePage<MainGroupView> getMainGroupViewLastPage() {
		SinglePage<MainGroupView> singlePage = getMasterEntityViewLastPage();
		return singlePage;
	}

	@Override
	public Long getMainGroupViewSinglePageNo(String groupCode) {
		Long pageNo = getMasterEntityViewSinglePageNo(groupCode);
		return pageNo;
	}

	@Override
	public MultiplePages<MainGroupView> getMainGroupViewMultiplePages(long pageNo) {
		MultiplePages<MainGroupView> multiplePages = getMasterEntityViewMultiplePages(pageNo);
		return multiplePages;
	}

	@Override
	public MultiplePages<MainGroupView> getMainGroupViewFilteredMultiplePages(long pageNo,
			MainGroupViewFilter mainGroupViewFilter) {
		Map<String, Object> filters = new HashMap<String, Object>();
		filters.put("group_code", mainGroupViewFilter.getGroupCode());
		filters.put("group_d_name", mainGroupViewFilter.getGroupDName());
		filters.put("group_f_name", mainGroupViewFilter.getGroupFName());
		MultiplePages<MainGroupView> multiplePages = getMasterEntityViewFilteredMultiplePages(pageNo, filters);
		return multiplePages;
	}

	@Override
	public void addMainGroup(MainGroup mainGroup) {
		addMasterEntity(mainGroup);
	}

	@Override
	public void addMainGroupesPriv(List<MainGroupPriv> mainGroupPrivList) {
		addMasterEntityPriv(mainGroupPrivList);
	}

	@Override
	public void updateMainGroup(MainGroup mainGroup) {
		// Map all fields except pk and auditing fields
		MainGroup DBMainGroup = getDBMasterEntity(mainGroup.getGroupCode());
		DBMainGroup.setGroupDName(mainGroup.getGroupDName());
		DBMainGroup.setGroupFName(mainGroup.getGroupFName());
		updateDBMasterEntity(DBMainGroup);
	}

	@Override
	public void deleteMainGroup(String groupCode) {
		deleteMasterEntity(groupCode);
	}

}
