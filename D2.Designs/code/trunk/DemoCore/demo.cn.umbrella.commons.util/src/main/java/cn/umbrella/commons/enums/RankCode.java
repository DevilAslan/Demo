package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.ICommonEnum;

public enum RankCode implements ICommonEnum<String> {
	
	ORDINARY("1", "ORDINARY"),
	CLASSIC("2", "CLASSIC"),
	RARE("3", "RARE"),
	EPIC("4", "EPIC"),
	LEGEND("5", "LEGEND"),
	
	UNDEFINED("0", "UNDEFINED");
	
	private String key;
	private String value;

	private RankCode(String key, String value) {
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
