package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.IGDCommonEnum;

public enum AccessCode implements IGDCommonEnum<String> {
	SUCCESS("101", "SUCCESS"),
	
	INVALID("401", "INVALID"),
	
	FORBIDDEN("403", "FORBIDDEN"),
	NOT_FOUND("404", "NOT FOUND"),
	OVERDUE("405", "OVERDUE"),
	EXIST("406", "EXIST"),
	OUT_OF_SERVICE("407", "OUT OF SERVICE"),

	
	PARAMETER_ERROR("801", "PARAMETER ERROR"),
	CHECK_ERROR("802", "CHECK ERROR"),
	
	FAIL("901", "FAIL"),
	EXCEPTION("9999", "系统异常");
	
	
	
	
//	200	OK	Success!
//	400	错误的请求	该请求是无效的。相应的描述信息会说明原因。
//	401	未验证	没有验证信息或者验证失败
//	403	被拒绝	理解该请求，但不被接受。相应的描述信息会说明原因。
//	404	无法找到	资源不存在，请求的用户的不存在，请求的格式不被支持。
//	405	请求方法不合适	该接口不支持该方法的请求。
//	410	已下线	请求的资源已下线。请参考相关公告。
//	429	过多的请求	请求超出了频率限制。相应的描述信息会解释具体的原因。
//	500	内部服务错误	服务器内部出错了。请联系我们尽快解决问题。
//	502	无效代理	业务服务器下线了或者正在升级。请稍后重试。
//	503	服务暂时失效	服务器无法响应请求。请稍后重试。
//	504	代理超时	服务器在运行，但是无法响应请求。请稍后重试。
	
	
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
