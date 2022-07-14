package com.expertsvision.erp.core.utils;

import java.sql.Timestamp;

public interface Addable {

	Timestamp getAddDate();

	void setAddDate(Timestamp addDate);

	Integer getAddUser();

	void setAddUser(Integer addUser);

}
