package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.ICommonEnum;

public enum CharsetCode implements ICommonEnum<String> {
	UNDEFINED("0", "UNDEFINED"),
	
	UTF_8("utf-8", "中文");
	
	private String key;
	private String value;

	private CharsetCode(String key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public String getKey() {
		return this.key;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	public static String getValueByKey(String key) {
		for (ICommonEnum<String> item : values()) {
			if (item.getKey().equals(key)) {
				return item.getValue();
			}
		}
		return null;
	}

	public static String getKeyByValue(String value) {
		for (ICommonEnum<String> item : values()) {
			if (item.getValue().equals(value)) {
				return item.getValue();
			}
		}
		return null;
	}

	public static String getKeyByItemValue(String itemValue) {
		String res = null;
		try {
			res = valueOf(itemValue).getValue();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
}
