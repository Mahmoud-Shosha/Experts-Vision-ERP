package com.expertsvision.erp.core.utils;

import java.sql.Timestamp;

public interface Inactivable {

	boolean getInactive();

	void setInactive(boolean inactive);

	String getInactiveReason();

	void setInactiveReason(String inactiveReason);

	Timestamp getInactiveDate();

	void setInactiveDate(Timestamp addDate);

	Integer getInactiveUser();

	void setInactiveUser(Integer inactiveUser);

}
