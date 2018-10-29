package cn.umbrella.commons.bean;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.WebRequest;

import cn.umbrella.commons.config.Constant;

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
	private int pageSize = 15;// 每页的数量
	private String orderDirection;// 排序方向
	private String orderColumns;// 排序字段（多个以半角逗号隔开）

	private String orderBy;// 排序
	
	public BaseQuery() {
	}

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
	
	/**
	 * 
	 * @Description: 封装查询条件 
	 * @Title: EncapsulateQueryCondition 
	 * @param request
	 * @return BaseQuery
	 * @throws
	 */
	@SuppressWarnings("unchecked")
	public static BaseQuery encapsulateQueryCondition(HttpServletRequest request) {
		BaseQuery query = new BaseQuery();
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String key = parameterNames.nextElement();
			String value = request.getParameter(key);
			if(key.startsWith("arr_")) {
				String[] values = request.getParameterValues(key);
				key = key.split("_|\\[")[1];
				query.getQueryData().put(key, values);
				continue;
			}
			switch (key) {
			case "pageNum":
				query.setPageNum(Integer.parseInt(value));
				break;
			case "pageSize":
				query.setPageSize(Integer.parseInt(value));
				break;
			case "orderColumns":
				query.setOrderColumns(value);
				break;
			case "orderDirection":
				query.setOrderDirection(value);
				break;
			default:
				query.getQueryData().put(key, value);
				break;
			}
		}
		query.setOrderBy();// 将排序字段和排序方向拼接
		return query;
	}
	
	/**
	 * 获取参数（包含为空的数据）
	 * @param request
	 * @return
	 */
	public static BaseQuery queryParameter(WebRequest request) {
		BaseQuery query = new BaseQuery();
		Iterator<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasNext()) {
			String key = parameterNames.next();
			String value = request.getParameter(key);
			query.getQueryData().put(key, value);
		}
		return query;
	}
	
	/**
	 *  封装查询条件 
	 *
	 * @Title: encapsulateQueryCondition 
	 * @param request
	 * @return BaseQuery
	 */
	public static BaseQuery encapsulateQueryCondition(WebRequest request) {
		BaseQuery query = new BaseQuery();
		Iterator<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasNext()) {
			String key = parameterNames.next();
			String value = request.getParameter(key);
			if(key.startsWith(Constant.ARRAY_FLAG)) {// 数组
				if(StringUtils.isNotBlank(value)) {
					query.getQueryData().put(key.replace(Constant.ARRAY_FLAG, ""), value.split(","));
				}
			} else if(key.indexOf("[") > 0) {
				String[] values = request.getParameterValues(key);
				if(values != null && values.length > 0) {
					List<String> lists = new ArrayList<String>();
					for (String string : values) {
						if(StringUtils.isNotBlank(string)) {
							lists.add(string);
						}
					}
					query.getQueryData().put(key.replace("[]", ""), lists.toArray());
				}
			} else {
				switch (key) {
				case "page":
					if(!"NaN".equals(value)) {
						if(Integer.parseInt(value) > 1){
							query.setPageNum(Integer.parseInt(value));
						}
					}
					break;
				case "rows":
					if(!"NaN".equals(value)) {
						query.setPageSize(Integer.parseInt(value));
					}
					break;
				case "sidx":// 针对jqgrid排序
					query.addParam("orderColumns", value);
					break;
				case "sord":
					query.addParam("orderDirection", value);
					break;
				case "sort":// 针对easyui datagrid排序
					query.addParam("orderColumns", value);
					break;
				case "order":
					query.addParam("orderDirection", value);
					break;
				default:
					if(StringUtils.isNotBlank(value)) {
						query.getQueryData().put(key, value);
					}
					break;
				}
			}
		}
		
		Map<String, Object> queryData = query.getQueryData();
		if(queryData != null && queryData.size() > 0) {
			String orderInfo = "";
			String orderColumns = "";
			String orderDirection = "";
			if(queryData.containsKey("orderColumns")) {
				orderColumns = (String) queryData.get("orderColumns");
			}
			if(queryData.containsKey("orderDirection")) {
				orderDirection = (String) queryData.get("orderDirection");
			}
			if(StringUtils.isNotEmpty(orderColumns)) {
				String[] orderColumnArr = orderColumns.split(",");
				if(StringUtils.isNotEmpty(orderDirection)) {
					String[] orderDirectionArr = orderDirection.split(",");
					int directionLength = orderDirectionArr.length;
					for(int i = 0; i < orderColumnArr.length; i++) {
						String col = orderColumnArr[i].trim();
						if(i < directionLength) {
							orderInfo += col + " " + orderDirectionArr[i].trim() + ",";
						} else {
							orderInfo += col + ",";
						}
					}
					if(orderInfo.length() > 0) {
						orderInfo = orderInfo.substring(0, orderInfo.length() - 1);
					}
				} else {
					orderInfo = orderColumns;
				}
				query.addParam("queryOrderInfo", orderInfo);
			}
		}
		return query;
	}
}
