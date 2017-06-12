package cn.umbrella.conmmon.model;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final int default_PageSize = 20;

	int currentPage = 1; // 当前页码 默认:1

	int pageSize = 20; // 每页要显示的记录数 默认:20

	long totalCount = 0;// 总记录数 默认:0

	int pageCount = 0;// 总页数 默认:0

	List<?> recordList; // 本页的数据列表

	public Page() {
		pageSize = default_PageSize;
	}

	public Page(int currentPage, int pageSize, int totalCount,
			List<?> recordList) {
		this.currentPage = currentPage;
		this.pageSize = pageSize <= 0 ? Page.default_PageSize : pageSize;
		this.totalCount = totalCount;
		this.recordList = recordList;

		pageCount = (totalCount + pageSize - 1) / pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (this.currentPage > this.pageCount) {
			this.currentPage = this.pageCount;
		}
		if (this.currentPage < 1) {
			this.currentPage = 1;
		}
		this.currentPage = currentPage;
	}

	public List<?> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<?> recordList) {
		this.recordList = recordList;
	}

	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
		doPageBreak();
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		doPageBreak();
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void doPageBreak() {
		this.pageCount = (int) Math.ceil(((double) this.totalCount)
				/ ((double) this.pageSize));
	}
}
