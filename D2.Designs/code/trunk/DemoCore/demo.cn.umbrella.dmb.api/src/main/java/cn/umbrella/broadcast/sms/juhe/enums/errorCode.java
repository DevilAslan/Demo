package cn.umbrella.broadcast.sms.juhe.enums;

import cn.umbrella.commons.enums.IGDCommonEnum;

public enum errorCode implements IGDCommonEnum<String> {
	//系统级错误码
 	SYS_10001("10001", "错误的请求KEY"),
 	SYS_10002("10002", "该KEY无请求权限"),
 	SYS_10003("10003", "KEY过期"),
 	SYS_10004("10004", "错误的OPENID"),
 	SYS_10005("10005", "应用未审核超时,请提交认证"),
 	
 	SYS_10007("10007", "未知的请求源"),
 	SYS_10008("10008", "被禁止的IP"),
 	SYS_10009("10009", "被禁止的KEY"),
 	
 	SYS_10011("10011", "当前IP请求超过限制"),
 	SYS_10012("10012", "请求超过次数限制"),
 	SYS_10013("10013", "测试KEY超过请求限制"),
 	SYS_10014("10014", "系统内部异常(调用充值类业务时，请务必联系客服或通过订单查询接口检测订单，避免造成损失)"),
 	SYS_10020("10020", "接口维护"),
 	SYS_10021("10021", "接口停用"),
	
 	//服务级错误码 数据ID
	SERVER_203801("203801", "请输入正确的15或18位身份证"),
	SERVER_203802("203802", "错误的身份证或无结果"),
	SERVER_203803("203803", "身份证校验位不正确"),
	SERVER_203804("203804", "查询失败"),
	
	SERVER_205401("205401", "错误的手机号码"),
	SERVER_205402("205402", "错误的短信模板ID"),
	SERVER_205403("205403", "网络错误,请重试"),
	SERVER_205404("205404", "发送失败,具体原因请参考返回reason"),
	SERVER_205405("205405", "号码异常/同一号码发送次数过于频繁"),
	SERVER_205406("205406", "不被支持的模板"),
	
	DEF("0000", "UNDEFINED");
 	
	private String value;
	private String name;

	private errorCode(String value, String name) {
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
