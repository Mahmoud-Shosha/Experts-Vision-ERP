package com.expertsvision.erp.core.flagdetail.service;

import java.util.List;

import com.expertsvision.erp.core.flagdetail.dto.FlagDetailViewFilter;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailMainTree;
import com.expertsvision.erp.core.flagdetail.entity.FlagDetailView;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface FlagDetailService {

	List<FlagDetailView> getFlagDetailViewList();

	List<FlagDetailView> getFlagDetailViewList(UsersView loginUser, String flagCode);
	
	public List<FlagDetailMainTree> getFlagDetailMainTree(Integer userId);

	FlagDetailView getFlagDetailView(UsersView loginUser, String flagCode, Integer flagValue);

	SinglePage<FlagDetailView> getFlagDetailsViewSinglePage(UsersView loginUser, String flagCode, long pageNo);

	SinglePage<FlagDetailView> getFlagDetailsViewLastPage(UsersView loginUser, String flagCode);

	Long getFlagDetailViewSinglePageNo(UsersView loginUser, String flagCode, Integer flagValue);

	MultiplePages<FlagDetailView> getFlagDetailsViewMultiplePages(UsersView loginUser, String flagCode, long pageNo);

	MultiplePages<FlagDetailView> getFlagDetailsViewFilteredMultiplePages(UsersView loginUser, String flagCode,
			long pageNo, FlagDetailViewFilter FlagDetailViewFilter);

	void addFlagDetail(UsersView loginUser, FlagDetailView flagDetailView);

	void updateFlagDetail(UsersView loginUser, FlagDetailView flagDetailView);

	void deleteFlagDetail(UsersView loginUser, String flagCode, Integer flagValue);

}
