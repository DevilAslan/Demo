package cn.umbrella.commons.enums;

import cn.umbrella.commons.enums.ICommonEnum;

public enum Unicode implements ICommonEnum<String> {
	
	/** 7位ASCII字符，也叫作ISO646-US、Unicode字符集的基本拉丁块 */
	US_ASCII("US_ASCII", "US_ASCII"),
	/** ISO 拉丁字母表 No.1，也叫作 ISO-LATIN-1 */
	ISO_8859_1("ISO_8859_1", "ISO_8859_1"),
	/** 8 位 UCS 转换格式 */
	UTF_8("UTF_8", "UTF_8"),
	/** 16 位 UCS 转换格式，Big Endian（最低地址存放高位字节）字节顺序 */
	UTF_16BE("UTF_16BE", "UTF_16BE"),
	/** 16 位 UCS 转换格式，Little-endian（最高地址存放低位字节）字节顺序 */
	UTF_16LE("UTF_16LE", "UTF_16LE"),
	/** 16 位 UCS 转换格式，字节顺序由可选的字节顺序标记来标识 */
	UTF_16("UTF_16", "UTF_16"),
	/** 中文超大字符集 */
	GBK("GBK", "GBK");
	
	private String key;
	private String value;

	private Unicode(String key, String value) {
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
