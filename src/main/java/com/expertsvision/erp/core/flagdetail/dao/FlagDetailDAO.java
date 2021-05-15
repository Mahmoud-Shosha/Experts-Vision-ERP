package com.expertsvision.erp.core.flagdetail.dao;

import java.util.List;

import com.expertsvision.erp.core.flagdetail.dto.FlagDetailViewFilter;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetail;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface FlagDetailDAO {

	List<FlagDetailView> getFlagDetailViewList();

	List<FlagDetailView> getFlagDetailViewList(String flagCode);

	FlagDetailView getFlagDetailView(String flagCode, Integer flagValue);

	SinglePage<FlagDetailView> getFlagDetailsViewSinglePage(String flagCode, long pageNo);

	SinglePage<FlagDetailView> getFlagDetailsViewLastPage(String flagCode);

	Long getFlagDetailViewSinglePageNo(String flagCode, Integer flagValue);

	MultiplePages<FlagDetailView> getFlagDetailsViewMultiplePages(String flagCode, long pageNo);

	MultiplePages<FlagDetailView> getFlagDetailsViewFilteredMultiplePages(String flagCode, long pageNo,
			FlagDetailViewFilter FlagDetailViewFilter);

	void addFlagDetail(FlagDetail flagDetail);

	void updateFlagDetail(FlagDetail flagDetail);

	void deleteFlagDetail(String flagCode, Integer flagValue);
}
