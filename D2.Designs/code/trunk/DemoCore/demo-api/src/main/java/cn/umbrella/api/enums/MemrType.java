package cn.umbrella.api.enums;

import cn.umbrella.commons.enums.IGDCommonEnum;

public enum MemrType  implements IGDCommonEnum<Integer>{
	TOURIST(0, "游客"),//默认游客
	//TOURIST_SITE_CODE(SITE_CODE, "游客"),//其他游客
	PERSON(1, "个人"),
	ENTERPRISE(2, "企业");

	private int key;
	private String value;

	private MemrType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	@Override
	public Integer getKey() {
		return this.key;
	}
	
	@Override
	public String getValue() {
		return this.value;
	}
	
	public static String getValueByKey(int key) {
		for (IGDCommonEnum<Integer> item : values()) {
			if (item.getKey().equals(key)) {
				return item.getValue();
			}
		}
		return null;
	}

	public static String getKeyByValue(String value) {
		for (IGDCommonEnum<Integer> item : values()) {
			if (item.getValue().equals(value)) {
				return item.getValue();
			}
		}
		return null;
	}

	public static Integer getKeyByItemValue(String itemValue) {
		Integer res = null;
		try {
			res = valueOf(itemValue).getKey();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
}
