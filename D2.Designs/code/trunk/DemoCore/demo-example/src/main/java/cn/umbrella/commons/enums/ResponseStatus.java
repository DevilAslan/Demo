package cn.umbrella.commons.enums;

/**
 * 
 * @ClassName: AjaxReponseStatus
 * @Description: Ajax请求返回状态
 * @author zhou.xy
 * @date 2015年9月18日 上午11:42:56
 *
 */
public enum ResponseStatus implements ICommonEnum{
	FAILED(0, "失败"), 
	SUCCESS(1, "成功");

	private final int value;
	private final String name;

	private ResponseStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static String getNameByValue(int ordinal) {
		for (ResponseStatus v : values()) {
			if (v.getValue() == ordinal) {
				return v.getName();
			}
		}
		return null;
	}
}
