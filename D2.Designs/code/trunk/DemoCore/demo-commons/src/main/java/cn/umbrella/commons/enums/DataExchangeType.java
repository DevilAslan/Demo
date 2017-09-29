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
public enum DataExchangeType implements IGDCommonEnum<Integer> {
	ALL(1, "全量"),
	INCREASE(2, "增量");
	
	private int value;
	private String name;

	private DataExchangeType(int value, String name) {
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
