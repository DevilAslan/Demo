package cn.umbrella.commons.utils.usual;

/**
 * 字符串 unicode编码 互相转换
 * 
 * @author zhangyuzhu
 * 
 */
public class UnicodeUtil {

	/**
	 * 字符串 转换成 unicode编码
	 * 
	 * @param str
	 * @return unicode
	 */
	public static String string2Unicode(String str) {
		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {
			// 取出每一个字符
			char c = str.charAt(i);
			// 转换为unicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * unicode 转换为 字符串
	 * 
	 * @param unicode
	 * @return str
	 */
	public static String unicode2String(String unicode) {
		StringBuffer str = new StringBuffer();

		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			// 转换出每一个代码点
			int data = Integer.parseInt(hex[i], 16);
			// 追加成string
			str.append((char) data);
		}

		return str.toString();
	}

	public static void main(String[] args) {
		String str = "北京";
		String result = string2Unicode(str);
		System.out.println(result);
		System.out.println("============");
		System.out.println(unicode2String(result));
	}

}
