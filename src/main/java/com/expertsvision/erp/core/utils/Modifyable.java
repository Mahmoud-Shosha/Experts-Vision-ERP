package com.expertsvision.erp.core.utils;

import java.sql.Timestamp;

public interface Modifyable {

	public Timestamp getModifyDate();

	public void setModifyDate(Timestamp modifyDate);

	public Integer getModifyUser();

	public void setModifyUser(Integer modifyUser);

}
