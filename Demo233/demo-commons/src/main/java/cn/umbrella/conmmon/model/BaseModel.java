package cn.umbrella.conmmon.model;

import java.util.Date;

/**
 * 此类为所有数据库模型层的基类 请保持公共字段名称和数据库名称一致
 * 
 */
public class BaseModel {
	private String rowId;
	private String state;
	private String sort;
	private Date createTime;
	private Date updateTime;

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
