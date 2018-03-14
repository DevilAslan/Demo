package cn.umbrella.mss.enums;

import cn.umbrella.commons.enums.ICommonEnum;

public enum ReturnCode implements ICommonEnum<String> {
	SUCCESS("0000", "业务执行成功"),
	
	NOT_LOGIN("0001", "用户未登录"),
	ERROR_VERIFY_CODE("0002", "验证码错误"),
	EMPTY_ACCOUNT("0003", "账号为空"),
	EMPTY_PASSWORD("0004", "密码为空"),
	FAIL_LOGIN("0005", "登录失败"),
	PERSON_LOGIN_DATA_EXCEPTION_IDCARD("0006", "登录数据异常：用户证件号码不存在"),
	NOT_REALNAME_AUTHENTICATION("0007", "未实名认证，不能查看信用档案"),
	
	INVALID_PHONE("0008", "联系电话格式错误"),
	INVALID_EMAIL("0009", "邮箱格式错误"),
	EMPTY_CREDIT_ENTITI_CODE("0010", "信用主体代码为空"),
	EMPTY_CREDIT_ENTITI_NAME("0011", "信用主体名称为空"),
	EMPTY_TABLE_NAME("0012", "目录为空"),
	EMPTY_CHANNEL_ID("0013", "栏目ID为空"),
	EMPTY_CONTENT_ID("0014", "内容ID为空"),
	EMPTY_TABLE_OWNER_ID("0015", "所属部门ID为空"),
	EMPTY_CREDIT_CATEGORY("0016", "信用分类为空"),
	EMPTY_DISSENT_DESC("0017", "异议内容为空"),
	EMPTY_ATTACHMENNT_ID("0018", "附件Id为空"),
	EMPTY_DISSENT_TYPE("0019", "异议类型为空"),
	EMPTY_COMPLAINTO("0020", "被投诉对象为空"),
	EMPTY_COMPLAIN_CONTENT("0021", "投诉内容为空"),
	EMPTY_TRADE_CODE("0022", "行业分类为空"),
	EMPTY_INDUSTRY_CLASS("0023", "行业小分类为空"),
	EMPTY_CREATER("0024", "真实姓名为空"),
	EMPTY_CONSULTATION_TITLE("0025", "咨询标题为空"),
	EMPTY_CONSULTATION_CONTENT("0026", "咨询内容为空"),
	EMPTY_LEAVEMESSAGE("0027", "留言内容为空"),
	EMPTY_PRIMARY("0028", "主键为空"),
	PARSE_PARAMS_ERROR("0029","参数解析错误"),
	AUTH_LEVEL_LOW("0030","认证级别不够"),
	EMPTY_LAT_LON("0031","经纬度不能为空"),
	EMPTY_UNIFY_SOCIETY_CODE("0032","统一社会信用代码为空"),
	EMPTY_STREET_TYPE("0033","诚信一条街类型为空"),
	
	OS_NOT_FOUNT("9993", "没有对应操作系统"),
	COMPLAINTYPE_ERROR("9994", "投诉类型错误"),
	CREDIT_OWNER_TYPE_ERROR("9995", "用户主体类型错误"),
	UPLOAD_FILE_NOT_FOUND("9996", "未找到附件"),
	UPLOAD_ERROR("9997", "上传失败"),
	EMPTY_PARAMS("9998", "参数为空"),
	EXCEPTION("9999", "系统异常");

	private String key;
	private String value;

	private ReturnCode(String key, String value) {
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
