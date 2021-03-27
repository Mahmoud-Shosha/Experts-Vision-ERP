package com.expertsvision.erp.core.utils;



import com.fasterxml.jackson.annotation.JsonProperty;

public class SinglePage<T> {
	
	@JsonProperty("page")
	private T page;
	
	@JsonProperty("page_no")
	private long pageNo;
	
	@JsonProperty("pages_count")
	private Long pagesCount;

	
	public SinglePage(T page, long pageNo, Long pagesCount) {
		super();
		this.page = page;
		this.pageNo = pageNo;
		this.pagesCount = pagesCount;
	}

	
	public T getPage() {
		return page;
	}

	public void setPage(T page) {
		this.page = page;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}


	public Long getPagesCount() {
		return pagesCount;
	}

	public void setPagesCount(Long pagesCount) {
		this.pagesCount = pagesCount;
	}
	

}
