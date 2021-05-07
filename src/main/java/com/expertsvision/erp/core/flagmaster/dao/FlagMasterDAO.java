package com.expertsvision.erp.core.flagmaster.dao;

import java.util.List;

import com.expertsvision.erp.core.flagmaster.dto.FlagMasterViewFilter;
import com.expertsvision.erp.core.flagmaster.entity.FlagMaster;
import com.expertsvision.erp.core.flagmaster.entity.FlagMasterView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface FlagMasterDAO {

	List<FlagMasterView> getFlagMasterViewList();

	FlagMasterView getFlagMasterView(String flagCode);

	SinglePage<FlagMasterView> getFlagMastersViewSinglePage(long pageNo);

	SinglePage<FlagMasterView> getFlagMastersViewLastPage();

	Long getFlagMasterViewSinglePageNo(String flagCode);

	MultiplePages<FlagMasterView> getFlagMastersViewMultiplePages(long pageNo);

	MultiplePages<FlagMasterView> getFlagMastersViewFilteredMultiplePages(long pageNo,
			FlagMasterViewFilter FlagMasterViewFilter);

	void addFlagMaster(FlagMaster flagMaster);

	void updateFlagMaster(FlagMaster flagMaster);

	void deleteFlagMaster(String flagCode);
}
