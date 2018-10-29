package cn.umbrella.rss.test.game;

import cn.umbrella.commons.enums.ICommonEnum;

public enum GameCode implements ICommonEnum<String> {
	A("A", "6"),
	B("B", ""),
	C("C", ""),
	D("D", "1"),
	E("E", "8"),
	F("F", ""),
	G("C", ""),
	H("H", "4"),
	I("I", ""),
	J("J", "5"),
	K("J", "9"),
	L("L", ""),
	M("M", ""),
	N("N", "6"),
	O("O", "2"),
	P("P", ""),
	Q("Q", ""),
	R("R", "8"),
	S("S", "0"),
	T("T", "7"),
	U("U", "4"),
	V("V", ""),
	W("W", "5"),
	X("X", ""),
	Y("Y", "3"),
	Z("Z", ""),
	
//	AA("Y", "3"),
	;
	
	
	
//	405	请求方法不合适	该接口不支持该方法的请求。
	
//	429	过多的请求	请求超出了频率限制。相应的描述信息会解释具体的原因。
	
	private String key;
	private String value;

	private GameCode(String key, String value) {
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
