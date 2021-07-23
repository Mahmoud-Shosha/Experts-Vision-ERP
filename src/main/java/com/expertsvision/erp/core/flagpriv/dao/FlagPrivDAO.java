package com.expertsvision.erp.core.flagpriv.dao;

import java.util.List;

import com.expertsvision.erp.core.flagpriv.entity.FlagPriv;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivPK;
import com.expertsvision.erp.core.flagpriv.entity.FlagPrivView;

public interface FlagPrivDAO {

	List<FlagPrivView> getFlagPrivViewList();

	List<FlagPrivView> getFlagPrivViewList(Integer userId);

	FlagPriv getFlagPriv(FlagPrivPK FlagPrivPK);

	void addBulkFlagPriv(List<FlagPriv> prvsList);

	void updateBulkFlagPriv(List<FlagPriv> prvsList);

	void updateFlagPriv(FlagPriv prv);

	void deleteBulkFlagPriv(Integer userId);

//	FlagMasterView getFlagMasterView(String flagCode);
//
//	SinglePage<FlagMasterView> getFlagMastersViewSinglePage(long pageNo);
//
//	SinglePage<FlagMasterView> getFlagMastersViewLastPage();
//
//	Long getFlagMasterViewSinglePageNo(String flagCode);
//
//	MultiplePages<FlagMasterView> getFlagMastersViewMultiplePages(long pageNo);
//
//	MultiplePages<FlagMasterView> getFlagMastersViewFilteredMultiplePages(long pageNo,
//			FlagMasterViewFilter FlagMasterViewFilter);
//
//	void addFlagMaster(FlagMaster flagMaster);
//
//	void updateFlagMaster(FlagMaster flagMaster);
//
//	void deleteFlagMaster(String flagCode);
}
