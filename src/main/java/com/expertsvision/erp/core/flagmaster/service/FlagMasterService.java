package com.expertsvision.erp.core.flagmaster.service;

import java.util.List;

import com.expertsvision.erp.core.flagmaster.dto.FlagMasterViewFilter;
import com.expertsvision.erp.core.flagmaster.entity.FlagMasterView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface FlagMasterService {

	List<FlagMasterView> getFlagMasterViewList();

	FlagMasterView getFlagMasterView(String flagCode);

	SinglePage<FlagMasterView> getFlagMastersViewSinglePage(UsersView loginUser, long pageNo);

	SinglePage<FlagMasterView> getFlagMastersViewLastPage(UsersView loginUser);

	Long getFlagMasterViewSinglePageNo(UsersView loginUser, String flagCode);

	MultiplePages<FlagMasterView> getFlagMastersViewMultiplePages(UsersView loginUser, long pageNo);

	MultiplePages<FlagMasterView> getFlagMastersViewFilteredMultiplePages(UsersView loginUser, long pageNo,
			FlagMasterViewFilter FlagMasterViewFilter);

	void addFlagMaster(UsersView loginUser, FlagMasterView flagMasterView);

	void updateFlagMaster(UsersView loginUser, FlagMasterView flagMasterView);

	void deleteFlagMaster(UsersView loginUser, String flagCode);

}
