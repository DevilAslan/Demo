package cn.umbrella.mss.enums;

import cn.umbrella.commons.enums.IGDCommonEnum;

public enum AccessCode implements IGDCommonEnum<String> {
	SUCCESS("101", "业务执行成功"),
	
	INVALID("401", "INVALID"),
	
	FORBIDDEN("403", "FORBIDDEN"),
	NOT_FOUND("404", "NOT FOUND"),
	OVERDUE("405", "OVERDUE"),
	EXIST("406", "EXIST"),
	OUT_OF_SERVICE("407", "OUT OF SERVICE"),

	
	PARAMETER_ERROR("801", "PARAMETER ERROR"),
	CHECK_ERROR("802", "CHECK ERROR"),
	
	FAIL("901", "FAIL"),
	EXCEPTION("9999", "系统异常");
	
	private String value;
	private String name;

	private AccessCode(String value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public static String getNameByValue(String value) {
		for (IGDCommonEnum<String> item : values()) {
			if (item.getValue().equals(value)) {
				return item.getName();
			}
		}
		return null;
	}

	public static String getValueByName(String name) {
		for (IGDCommonEnum<String> item : values()) {
			if (item.getName().equals(name)) {
				return item.getValue();
			}
		}
		return null;
	}

	public static String getValueByItemName(String itemName) {
		String res = null;
		try {
			res = valueOf(itemName).getValue();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
}
