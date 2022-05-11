package com.expertsvision.erp.masterdata.banks.dao;

import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.banks.dto.BanksViewFilter;
import com.expertsvision.erp.masterdata.banks.entity.Bank;
import com.expertsvision.erp.masterdata.banks.entity.BanksDtl;
import com.expertsvision.erp.masterdata.banks.entity.BanksDtlView;
import com.expertsvision.erp.masterdata.banks.entity.BanksPriv;
import com.expertsvision.erp.masterdata.banks.entity.BanksView;

public interface BankDAO {

	List<BanksView> getAllBankViewList(UsersView loginUsersView);

	List<BanksDtlView> getBanksDtlViewList(Integer bankNo);

	BanksView getBankView(UsersView loginUsersView, Integer bankNo);

	SinglePage<BanksView> getBankViewSinglePage(UsersView loginUsersView, long pageNo);

	SinglePage<BanksView> getBankViewLastPage(UsersView loginUsersView);

	Long getUserViewSinglePageNo(UsersView loginUsersView, Integer bankNo);

	MultiplePages<BanksView> getBankViewMultiplePages(UsersView loginUsersView, long pageNo);

	MultiplePages<BanksView> getBankViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			BanksViewFilter branchesViewFilter);

	Object getNextPK();

	void addBank(Bank bank, List<BanksDtl> banksDtlList);

	void addBanksPriv(BanksPriv banksPriv);

	void updateBank(Bank bank, List<BanksDtl> banksDtlForAddList, List<BanksDtl> banksDtlForDeleteList,
			List<BanksDtl> banksDtlForUpdateList);

	void deleteBank(Integer bankNo);

}
