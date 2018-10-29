package cn.umbrella.commons.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.StringUtils;

/**
 * 
 * @Description: 分页传递信息  
 * @ClassName: BaseQuery  
 * @author zhou.xy
 * @date 2015年12月25日 下午3:32:15  
 *
 */
public class BaseQuery {
	private Map<String, Object> queryData = new HashMap<String, Object>();// 一些查询条件
	private int pageNum = 1;// 当前页
	private int pageSize = 5;// 每页的数量
	private String orderDirection;// 排序方向
	private String orderColumns;// 排序字段（多个以半角逗号隔开）

	private String orderBy;// 排序
	
	public void addParam(String key, Object value){
		this.queryData.put(key, value);
	}
	public Map<String, Object> getQueryData() {
		return queryData;
	}

	public void setQueryData(Map<String, Object> queryData) {
		this.queryData = queryData;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getOrderDirection() {
		return orderDirection;
	}

	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}

	public String getOrderColumns() {
		return orderColumns;
	}

	public void setOrderColumns(String orderColumns) {
		this.orderColumns = orderColumns;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy() {
		if(!StringUtils.isEmpty(getOrderColumns())) {
			this.orderBy = getOrderColumns() + " " + (getOrderDirection() == null ? "" : getOrderDirection());
		}
	}

}
