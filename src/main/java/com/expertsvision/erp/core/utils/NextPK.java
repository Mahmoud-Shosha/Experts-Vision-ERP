package com.expertsvision.erp.core.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NextPK {

	@JsonProperty("next_PK")
	private Object nextPK;
	
	
	public NextPK(Object nextPK) {
		this.nextPK = nextPK;
	}

	public Object getNextPK() {
		return nextPK;
	}

	public void setNextPK(Object nextPK) {
		this.nextPK = nextPK;
	}

	public static NextPK build(Object nextPK) {
		return new NextPK(nextPK);
	}
}
