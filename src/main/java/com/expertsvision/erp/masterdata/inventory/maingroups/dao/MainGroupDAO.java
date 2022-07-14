package com.expertsvision.erp.masterdata.inventory.maingroups.dao;

import java.util.List;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.inventory.maingroups.dto.MainGroupViewFilter;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroup;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupPriv;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupView;

public interface MainGroupDAO {

	MainGroupView getMainGroupView(String groupCode);

	SinglePage<MainGroupView> getMainGroupViewSinglePage(long pageNo);

	SinglePage<MainGroupView> getMainGroupViewLastPage();

	Long getMainGroupViewSinglePageNo(String groupCode);

	MultiplePages<MainGroupView> getMainGroupViewMultiplePages(long pageNo);

	MultiplePages<MainGroupView> getMainGroupViewFilteredMultiplePages(long pageNo,
			MainGroupViewFilter mainGroupesViewFilter);

	void addMainGroup(MainGroup mainGroup);

	void addMainGroupesPriv(List<MainGroupPriv> mainGroupPrivList);

	void updateMainGroup(MainGroup mainGroup);

	void deleteMainGroup(String groupCode);

}
