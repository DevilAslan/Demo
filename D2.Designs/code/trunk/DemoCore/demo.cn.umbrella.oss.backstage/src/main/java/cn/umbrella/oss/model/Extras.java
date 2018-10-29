package cn.umbrella.oss.model;

/***
 *  额外的一些信息，封装在这个对象中。
 ** @author qing.yunhui
 ** @date Sep 27, 2015 4:39:55 PM
 ***/
public class Extras {
	
	private String sysId;
	private String targetTable;
	private String targetField;
	private String targetId;
	private Integer createrId;
	private String creater;
	
	
	public String getSysId() {
		return sysId;
	}
	public void setSysId(String sysId) {
		this.sysId = sysId;
	}
	public String getTargetTable() {
		return targetTable;
	}
	public void setTargetTable(String targetTable) {
		this.targetTable = targetTable;
	}
	public String getTargetField() {
		return targetField;
	}
	public void setTargetField(String targetField) {
		this.targetField = targetField;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public Integer getCreaterId() {
		return createrId;
	}
	public void setCreaterId(Integer createrId) {
		this.createrId = createrId;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}

	
}
