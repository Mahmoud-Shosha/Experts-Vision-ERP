package com.expertsvision.erp.masterdata.cash.dao;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.cash.dto.CashInHandViewFilter;
import com.expertsvision.erp.masterdata.cash.entity.CashInHand;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandDtlView;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandPriv;
import com.expertsvision.erp.masterdata.cash.entity.CashInHandView;

public interface CashDAO {

	List<CashInHandView> getAllCashInHandViewList(UsersView loginUsersView);

	List<CashInHandDtlView> getCashInHandDtlViewList(Integer cashNo);

	CashInHandView getCashInHandView(UsersView loginUsersView, Integer cashNo);

	SinglePage<CashInHandView> getCashInHandViewSinglePage(UsersView loginUsersView, long pageNo);

	SinglePage<CashInHandView> getCashInHandViewLastPage(UsersView loginUsersView);

	Long getUserViewSinglePageNo(UsersView loginUsersView, Integer cashNo);

	MultiplePages<CashInHandView> getCashInHandViewMultiplePages(UsersView loginUsersView, long pageNo);

	MultiplePages<CashInHandView> getCashInHandViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			CashInHandViewFilter cashInHandesViewFilter);

	Object getNextPK();

	void addCashInHand(CashInHand cashInHand);

	void addCashInHandsPriv(CashInHandPriv banksPriv);

	void updateCashInHand(CashInHand cashInHand);

	void deleteCashInHand(Integer cashNo);

}
