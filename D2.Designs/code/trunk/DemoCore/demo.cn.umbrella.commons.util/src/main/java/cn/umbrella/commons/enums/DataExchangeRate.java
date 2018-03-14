package cn.umbrella.commons.enums;

/**
 * 
 * 数据交换频率  1实时 2日报 3周报 4月报 5年报'
 *
 * @ClassName: DataExchangeRate  
 * @author Aslan
 * @date 
 *
 */
public enum DataExchangeRate implements ICommonEnum<Integer> {
	REALTIME(1, "实时"),
	DAY(2, "日报"),
	WEEK(3, "周报"),
	MONTH(4, "月报"),
	QUARTER(5, "季报"),
	HALFYEAR(6, "半年报"),
	YEAR(7, "年报"),
	TIMING(8, "定时"),
	TRIGGER(9, "触发");
	
	private int key;
	private String value;

	private DataExchangeRate(int key, String value) {
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
