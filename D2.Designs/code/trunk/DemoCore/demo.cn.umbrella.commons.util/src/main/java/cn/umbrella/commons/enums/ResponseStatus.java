package cn.umbrella.commons.enums;

/**
 * 
 * @ClassName: AjaxReponseStatus
 * @Description: Ajax请求返回状态
 * @author zhou.xy
 * @date 2015年9月18日 上午11:42:56
 *
 */
public enum ResponseStatus implements ICommonEnum<Integer>{
	FAILED(0, "失败"), 
	SUCCESS(1, "成功");

	private int key;
	private String value;

	private ResponseStatus(int key, String value) {
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
