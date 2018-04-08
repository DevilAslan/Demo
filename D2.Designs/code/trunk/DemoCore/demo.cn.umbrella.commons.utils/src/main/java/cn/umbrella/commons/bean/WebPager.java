package cn.umbrella.commons.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页类
 */
public class WebPager {
	public static final String PAGE_NAME= "pager";
	public static final String PAGE_NO= "pageNo";
	public static final String PAGE_SIZE= "pageSize";
	public static final String START= "start";
	
	private int DEFULT_PAGE_SIZE = 10; // 总页数
	
	private int totalPage; // 总页数
	private int totalCount; // 总记录数
	private int pageNo = 1; // 当前页 从1开始
	private int pageSize = DEFULT_PAGE_SIZE; // 每页的数量
	private int prePage;
	private int nextPage;
	private boolean isFirst;
	private boolean isLast;
	private List<?> list;
	private int start;
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		if(pageNo<1){
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
		this.start = (this.pageNo-1)*this.pageSize;
	}

	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		if(pageSize<1){
			this.pageSize = DEFULT_PAGE_SIZE;
		} else {
			this.pageSize = pageSize;
		}
	}

	public int getPrePage() {
		return prePage;
	}
	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}
	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}

	public boolean isLast() {
		return isLast;
	}
	public void setLast(boolean isLast) {
		this.isLast = isLast;
	}

	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	
	public int getStart() {
		return start;
	}
	
	/**
	 * 分页处理
	 * @param currentPage 当前页
	 * @param pageSize 每页的数量
	 * @param totalCount 总记录数
	 */
	public void paging(int pageNo, int pageSize, int totalCount) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.totalCount = totalCount;

		if (pageNo < 1) {
			this.pageNo = 1;
		}

		this.totalPage = (this.totalCount + pageSize - 1) / pageSize;

		if (this.pageNo > 1) {
			this.prePage = this.pageNo - 1;
		} else {
			this.prePage = 1;
		}

		if (this.pageNo < totalPage) {
			this.nextPage = this.pageNo + 1;
		} else {
			this.nextPage = totalPage;
		}

		if (this.pageNo <= 1) {
			this.isFirst = true;
		} else {
			this.isFirst = false;
		}

		if (this.pageNo >= totalPage) {
			this.isLast = true;
		} else {
			this.isLast = false;
		}
	}
	
	public void init(Map<String, Object> map){
		if(map == null){
			map = new HashMap<String, Object>();
		}
		if(map.containsKey(PAGE_SIZE)){
			this.setPageSize(((Integer)map.get(PAGE_SIZE)).intValue());;
		}
		if(map.containsKey(PAGE_NO)){
			this.setPageNo(((Integer)map.get(PAGE_NO)).intValue());
		}
		map.put(PAGE_SIZE, pageSize);
		map.put(PAGE_NO, pageNo);
		map.put(START, start);
	}
}
