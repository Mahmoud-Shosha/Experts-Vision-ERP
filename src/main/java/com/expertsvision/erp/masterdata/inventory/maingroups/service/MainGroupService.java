package com.expertsvision.erp.masterdata.inventory.maingroups.service;

import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.inventory.maingroups.dto.MainGroupViewFilter;
import com.expertsvision.erp.masterdata.inventory.maingroups.entity.MainGroupView;

public interface MainGroupService {

	MainGroupView getMainGroupView(String groupCode);

	SinglePage<MainGroupView> getMainGroupViewSinglePage(long pageNo);

	SinglePage<MainGroupView> getMainGroupViewLastPage();

	Long getMainGroupViewSinglePageNo(String groupCode);

	MultiplePages<MainGroupView> getMainGroupViewMultiplePages(long pageNo);

	MultiplePages<MainGroupView> getMainGroupViewFilteredMultiplePages(long pageNo,
			MainGroupViewFilter mainGroupViewFilter);

	void addMainGroup(MainGroupView mainGroupView);

	void updateMainGroup(MainGroupView mainGroupView);

	void deleteMainGroup(String groupCode);

	void generateMainGroupPrivsForAllUsers(String groupCode);

}
