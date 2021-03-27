package com.expertsvision.erp.core.utils;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MultiplePages<T> {
	
	@JsonProperty("pages")
	private List<T> pages;
	
	@JsonProperty("page_no")
	private long pageNo;
	
	@JsonProperty("pages_count")
	private long pagesCount;
	

	public MultiplePages(List<T> pages, long pageNo, long pagesCount) {
		super();
		this.pages = pages;
		this.pageNo = pageNo;
		this.pagesCount = pagesCount;
	}
	

	public List<T> getPages() {
		return pages;
	}

	public void setPages(List<T> pages) {
		this.pages = pages;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(long pagesCount) {
		this.pagesCount = pagesCount;
	}


}
