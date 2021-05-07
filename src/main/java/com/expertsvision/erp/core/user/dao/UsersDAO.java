package com.expertsvision.erp.core.user.dao;

import java.util.List;

import com.expertsvision.erp.core.user.dto.UsersViewFilter;
import com.expertsvision.erp.core.user.entity.User;
import com.expertsvision.erp.core.user.entity.UsersView;
import com.expertsvision.erp.core.utils.MultiplePages;
import com.expertsvision.erp.core.utils.SinglePage;

public interface UsersDAO {

	List<UsersView> getAllUsersViewList();

	List<UsersView> getUsersViewSubordinateList(Integer loginUserId);

	UsersView getUsersView(Integer loginUserId, Integer userId);

	UsersView getUsersView(Integer userId);

	SinglePage<UsersView> getUsersViewSinglePage(Integer loginUserId, long pageNo);

	SinglePage<UsersView> getUsersViewLastPage(Integer loginUserId);

	Long getUserViewSinglePageNo(Integer loginUserId, Integer userId);

	MultiplePages<UsersView> getUsersViewMultiplePages(Integer loginUserId, long pageNo);

	MultiplePages<UsersView> getUsersViewFilteredMultiplePages(Integer loginUserId, long pageNo,
			UsersViewFilter usersViewFilter);

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(Integer userId);

	List<User> getUsersListByGroupNo(Integer groupNo);

}
