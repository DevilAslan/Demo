package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.IGDCommonEnum;

public enum AccessCode implements IGDCommonEnum<String> {
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
	OUT_OF_SERVICE("503", "SERVER OUT OF SERCIVE,PLEASE TRY AGAIN LATER"),
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
	
	private String value;
	private String name;

	private AccessCode(String value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public String getValue() {
		return this.value;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public static String getNameByValue(String value) {
		for (IGDCommonEnum<String> item : values()) {
			if (item.getValue().equals(value)) {
				return item.getName();
			}
		}
		return null;
	}

	public static String getValueByName(String name) {
		for (IGDCommonEnum<String> item : values()) {
			if (item.getName().equals(name)) {
				return item.getValue();
			}
		}
		return null;
	}

	public static String getValueByItemName(String itemName) {
		String res = null;
		try {
			res = valueOf(itemName).getValue();
		} catch (IllegalArgumentException | NullPointerException e) {
		}
		return res;
	}
}
