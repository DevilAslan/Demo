package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.IGDCommonEnum;

public enum RankCode implements IGDCommonEnum<String> {
	
	ORDINARY("1", "ORDINARY"),
	CLASSIC("2", "CLASSIC"),
	RARE("3", "RARE"),
	EPIC("4", "EPIC"),
	LEGEND("5", "LEGEND"),
	
	UNDEFINED("0", "UNDEFINED");
	
	private String value;
	private String name;

	private RankCode(String value, String name) {
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
