package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.ICommonEnum;

public enum AccessCode implements ICommonEnum<String> {
	UNDEFINED("0", "UNDEFINED"),
	SUCCESS("200", "SUCCESS"),
	
	ERROR("400", "ERROR"),
	UNVERIFIED("401", "UNVERIFIED"),
	INVALID("402", "INVALID"),
	FORBIDDEN("403", "FORBIDDEN"),
	NOT_FOUND("404", "NOT FOUND"),
	OVERDUE("405", "OVERDUE"),
	EXIST("406", "EXIST"),
	GONE("410", "410 Gone"),
	
	SERVICE_ERROR("500", "SERVICE ERROR"),
	INVALID_AGENT("502", "INVALID AGENT"),
	OUT_OF_SERVICE("503", "OUT OF SERCIVE,PLEASE TRY AGAIN LATER"),
	PROXY_TIMEOUT("504", "PROXY TIMEOUT,PLEASE TRY AGAIN LATER"),
	
	CLOSE("601", "CLOSE"),
	
	REFUSE("701", "REFUSE"),
	UNAUTHORIZED("702", "UNAUTHORIZED"),
	
	
	PARAMETER_ERROR("801", "PARAMETER ERROR"),
	CHECK_ERROR("802", "CHECK ERROR"),
	
	FAIL("901", "FAIL"),
	EXCEPTION("9999", "系统异常");
	
	
	
	
//	405	请求方法不合适	该接口不支持该方法的请求。
	
//	429	过多的请求	请求超出了频率限制。相应的描述信息会解释具体的原因。
	
	private String key;
	private String value;

	private AccessCode(String key, String value) {
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
