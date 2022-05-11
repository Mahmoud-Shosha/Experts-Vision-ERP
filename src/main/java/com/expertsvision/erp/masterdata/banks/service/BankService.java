package com.expertsvision.erp.masterdata.banks.service;

import java.sql.Timestamp;
import java.util.List;

import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.PreData;
import com.expertsvision.erp.core.utils.SinglePage;
import com.expertsvision.erp.masterdata.banks.dto.BanksViewFilter;
import com.expertsvision.erp.masterdata.banks.entity.BanksView;

public interface BankService {

	List<BanksView> getBanksViewList(UsersView loginusersView);

	BanksView getBanksView(UsersView loginUsersView, Integer bankNo);

	SinglePage<BanksView> getBanksViewSinglePage(UsersView loginUsersView, long pageNo);

	SinglePage<BanksView> getBanksViewLastPage(UsersView loginUsersView);

	Long getBanksViewSinglePageNo(UsersView loginUsersView, Integer bankNo);

	MultiplePages<BanksView> getBanksViewMultiplePages(UsersView loginUsersView, long pageNo);

	MultiplePages<BanksView> getBanksViewFilteredMultiplePages(UsersView loginUsersView, long pageNo,
			BanksViewFilter banksViewFilter);
	
	void addBank(UsersView loginUsersView, BanksView banksView);

	void updateBank(UsersView loginUsersView, BanksView banksView);

	void deleteBank(UsersView loginUsersView, Integer bankNo);

	PreData preAdd(UsersView loginUsersView);

	void generateBankPrivsForAllUsers(Integer bankNo, String AccCurr, Timestamp currentDate);

}
