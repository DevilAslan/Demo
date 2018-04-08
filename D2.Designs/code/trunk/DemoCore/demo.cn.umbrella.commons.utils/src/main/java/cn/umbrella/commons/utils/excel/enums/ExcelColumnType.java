package cn.umbrella.commons.utils.excel.enums;

/**
 * Excel 字段的类型，用于控制字段在导入导出时候是否进行性处理
 * 
 * @author zhang.xiaolei
 * @createDate 2015年5月27日
 */
public enum ExcelColumnType {
	ALL(0,"导入和导出"),IMP(1,"仅导入"),EXP(2,"仅导出");
	
	private String name;
	private int value;
	
	private ExcelColumnType(int value,String name){
		this.value=value;
		this.name=name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
}
