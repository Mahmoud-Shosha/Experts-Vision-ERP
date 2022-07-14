package com.expertsvision.erp.masterdata.cash.service;

import java.sql.Timestamp;
import java.util.List;


import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.cash.dto.CashInHandViewFilter;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandView;

public interface CashService {
		
	List<CashInHandView> getCashInHandViewList(UsersView loginusersView);
	
	CashInHandView getCashInHandView(UsersView loginUsersView, Integer cashInHandNo);
	
	SinglePage<CashInHandView> getCashInHandViewSinglePage(UsersView loginUsersView, long pageNo);
	
	SinglePage<CashInHandView> getCashInHandViewLastPage(UsersView loginUsersView);
	
	Long getCashInHandViewSinglePageNo(UsersView loginUsersView, Integer cashInHandNo);
	
	MultiplePages<CashInHandView> getCashInHandViewMultiplePages(UsersView loginUsersView, long pageNo);
	
	MultiplePages<CashInHandView> getCashInHandViewFilteredMultiplePages(UsersView loginUsersView, long pageNo, CashInHandViewFilter cashInHandViewFilter);
	
	PreData preAdd(UsersView loginUsersView);
	
	void addCashInHand(UsersView loginUsersView, CashInHandView cashInHandView);
	
	void updateCashInHand(UsersView loginUsersView, CashInHandView cashInHandView);
	
	void deleteCashInHand(UsersView loginUsersView, Integer cashInHandNo);

	void generateCashInHandPrivsForAllUsers(UsersView loginUsersView, Integer cashInHandNo, String AccCurr,Timestamp currentDate);
	
}
