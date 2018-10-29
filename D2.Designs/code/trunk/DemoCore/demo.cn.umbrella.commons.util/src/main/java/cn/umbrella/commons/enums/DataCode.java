package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.ICommonEnum;

public enum DataCode implements ICommonEnum<String> {
	EMPTY("", "EMPTY"),
	UNDEFINED("0", "UNDEFINED"),
	INIT("100", "INIT"),
	
//	ERROR("400", "ERROR"),
	UNVERIFIED("401", "UNVERIFIED"),
	INVALID("402", "INVALID"),
	FORBIDDEN("403", "FORBIDDEN"),
//	NOT_FOUND("404", "NOT FOUND"),
	OVERDUE("405", "OVERDUE"),
	EXIST("406", "EXIST"),
	OUT_OF_SERVICE("410", "OUT OF SERVICE"),
//	429	过多的请求	请求超出了频率限制。相应的描述信息会解释具体的原因。
	
	SERVICE_ERROR("500", "SERVICE ERROR"),
	INVALID_AGENT("502", "INVALID AGENT"),
	
//	INVALID_AGENT("503", "INVALID AGENT"),
//	INVALID_AGENT("504", "INVALID AGENT"),
//	503	服务暂时失效	服务器无法响应请求。请稍后重试。
//	504	代理超时	服务器在运行，但是无法响应请求。请稍后重试。
	
	CLOSE("601", "CLOSE"),
	
	REFUSE("701", "REFUSE"),
	
	
	PARAMETER_ERROR("801", "PARAMETER ERROR"),
	CHECK_ERROR("802", "CHECK ERROR"),
	
	FAIL("901", "FAIL"),
	EXCEPTION("9999", "系统异常");
	
	
	private String key;
	private String value;

	private DataCode(String key, String value) {
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
