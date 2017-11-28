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
public enum DataExchangeRate implements IGDCommonEnum<Integer> {
	REALTIME(1, "实时"),
	DAY(2, "日报"),
	WEEK(3, "周报"),
	MONTH(4, "月报"),
	QUARTER(6, "季报"),
	HALFYEAR(7, "半年报"),
	YEAR(5, "年报");
	
	private int value;
	private String name;

	private DataExchangeRate(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public Integer getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public static String getNameByValue(Integer value) {
		for (IGDCommonEnum<Integer> item : values()) {
			if (item.getValue() == value) {
				return item.getName();
			}
		}
		return null;
	}

	public static Integer getValueByName(String name) {
		for (IGDCommonEnum<Integer> item : values()) {
			if (item.getName().equals(name)) {
				return (Integer) item.getValue();
			}
		}
		return null;
	}

	public static Integer getValueByItemName(String itemName) {
		Integer res = null;
		try {
			res = valueOf(itemName).getValue();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
	
	public static String getNameByItemName(String itemName) {
		String res = null;
		try {
			res = valueOf(itemName).getName();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
}
