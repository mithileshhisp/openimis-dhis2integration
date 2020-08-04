package org.beehyv.dhis2openimis.adapter.dhis.pojo;

public class Pager {
	private Long page;
	private Long pageCount;
	private Long total;
	private Long pageSize;
	private String nextPage;
	
	public Pager() {
		
	}
	
	public Long getPage() {
		return page;
	}
	public void setPage(Long page) {
		this.page = page;
	}
	public Long getPageCount() {
		return pageCount;
	}
	public void setPageCount(Long pageCount) {
		this.pageCount = pageCount;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getPageSize() {
		return pageSize;
	}
	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}
	public String getNextPage() {
		return nextPage;
	}
	public void setNextPage(String nextPage) {
		this.nextPage = nextPage;
	}

	@Override
	public String toString() {
		return "Pager [page=" + page + ", pageCount=" + pageCount + ", total=" + total + ", pageSize=" + pageSize
				+ ", nextPage=" + nextPage + "]";
	}
	
}
