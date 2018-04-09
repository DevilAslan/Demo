package cn.umbrella.commons.enums;

public enum IsEnable implements ICommonEnum<Integer>{
	
	DISABLE(0, "禁用"), 
	ENABLE(1, " 启用");
	
	private Integer key;
	private String value;
	
	/** 根据默认脚标（0、1、2、3、4）获得对象 */
	public static IsEnable valueOf(Integer ordinal) {
		if (ordinal < 0 || ordinal >= values().length) {
			throw new IndexOutOfBoundsException("Invalid ordinal");
		}
		return values()[ordinal];
	}
	
	private IsEnable(Integer key, String value) {
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

	public static String getValueByKey(Integer key) {
		for (ICommonEnum<Integer> item : values()) {
			if (item.getKey().equals(key)) {
				return item.getValue();
			}
		}
		return null;
	}

	public static String getKeyByValue(String value) {
		for (ICommonEnum<Integer> item : values()) {
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
