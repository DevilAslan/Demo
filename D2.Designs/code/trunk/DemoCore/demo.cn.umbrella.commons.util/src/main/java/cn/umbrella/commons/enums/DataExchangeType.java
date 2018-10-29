package cn.umbrella.commons.enums;

/**
 * 
 * 数据交换策略  1全量 2增量  
 *
 * @ClassName: DataExchangeType  
 * @author Aslan
 * @date 
 *
 */
public enum DataExchangeType implements ICommonEnum<Integer> {
	ALL(1, "全量"),
	INCREASE(2, "增量");
	
	private int key;
	private String value;

	private DataExchangeType(int key, String value) {
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

	public static Integer getKeyByItemValue(String itemValue) {
		Integer res = null;
		try {
			res = valueOf(itemValue).getKey();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
}
