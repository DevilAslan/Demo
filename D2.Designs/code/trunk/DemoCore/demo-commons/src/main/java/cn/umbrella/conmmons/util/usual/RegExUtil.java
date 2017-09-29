/** 
 * RegExTool.java Created on Jan 8, 2009
 * Copyright 2009@CBI Tech. 
 * All right reserved. 
 */
package cn.umbrella.conmmons.util.usual;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式 工具类
 * 
 * @Time 11:23:10 AM
 */
public class RegExUtil {

	public static final String HTML_PATTERN = "<[^<>]*>";
	public static final String NULL_STR = "";

	/**
	 * 清除字符串中的html标签
	 * 
	 * @param str
	 * @return
	 */
	public static String cleanupHtmlTag(String str) {
		str = convertToHtml(str);
		return convert(str, HTML_PATTERN, NULL_STR);
	}

	/**
	 * 将字符串中回车换行替换成html标签
	 * 
	 * @param param
	 *            目标字符串
	 * @return
	 */
	public static String convertToHtml(String text) {
		String BRTag = "<br />";
		StringBuffer sb = new StringBuffer("");
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c == '\n') {
				sb.append(BRTag);
			} else if (c == '\r' && i < text.length() - 1
					&& text.charAt(i + 1) == '\n') {
				sb.append(BRTag);
				i++;
			} else
				sb.append(c);
		}
		return sb.toString();
	}

	/**
	 * 在目标字符串中替换指定模式的字符
	 * 
	 * @param source
	 *            目标字符串
	 * @param p
	 *            指定模式
	 * @param r
	 *            替换字符串
	 * @return
	 */
	public static String convert(String str, String p, String r) {
		Pattern pattern = Pattern.compile(p, Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(str);
		StringBuffer stringbuffer = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(stringbuffer, r);
		}
		matcher.appendTail(stringbuffer);
		return stringbuffer.toString();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		log("----------------------");
		String sc = "<li><a href=\"#\" onmouseover=\"this.style.behavior='url(#default#homepage)';this.setHomePage('http://www.itlearner.com/');return(false);\">设为首页</a></li>";
		sc = sc + "<li><a href=\"http://tool.itlearner.com\">站长助手</a></li>";
		log(sc);
		log("----------------------");
		String str = cleanupHtmlTag(sc);
		log(str);

	}

	public static void log(Object obj) {
		System.out.println(obj);
	}

	/**
	 * 检查email输入是否正确 正确的书写格式为 username@domain
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isEmail(String value) {
		return value
				.matches("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");
	}

	/**
	 * 检查电话输入是否正确 正确格式 012-87654321、0123-87654321、0123－7654321
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isTel(String value) {
		return value.matches("\\d{4}-\\d{8}|\\d{4}-\\d{7}|\\d(3)-\\d(8)");
	}

	/**
	 * 检查手机输入是否正确
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isMobile(String value) {
		return value.matches("^1[3|4|5|8][0-9]\\d{8}$");
	}

	/**
	 * 检查中文名输入是否正确
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isChineseName(String value, int length) {
		return value.matches("^[\u4e00-\u9fa5]+$") && value.length() <= length;
	}

	/**
	 * 检查HTML中首尾空行或空格
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isBlank(String value) {
		return value.matches("^\\s*|\\s*$");
	}

	/**
	 * 检查字符串是否含有HTML标签
	 * 
	 * @param value
	 * @return
	 */

	public static boolean isHtmlTag(String value) {
		return value.matches("<(\\S*?)[^>]*>.*?</\\1>|<.*? />");
	}

	/**
	 * 检查URL是否合法
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isURL(String value) {
		return value.matches("[a-zA-z]+://[^\\s]*");
	}

	/**
	 * 检查IP是否合法
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIP(String value) {
		return value.matches("\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}+\\.\\d{1,3}");
	}

	/**
	 * 检查ID是否合法，开头必须是大小写字母，其他位可以有大小写字符、数字、下划线
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isID(String value) {
		return value.matches("[a-zA-Z][a-zA-Z0-9_]{4,15}$");
	}

	/**
	 * 检查QQ是否合法，必须是数字，且首位不能为0，最长15位
	 * 
	 * @param value
	 * @return
	 */

	public static boolean isQQ(String value) {
		return value.matches("[1-9][0-9]{4,13}");
	}

	/**
	 * 检查邮编是否合法
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isPostCode(String value) {
		return value.matches("[1-9]\\d{5}(?!\\d)");
	}

	/**
	 * 检查身份证是否合法,15位
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIDCard15(String value) {
		return value
				.matches("^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$");
	}

	/**
	 * 检查身份证是否合法,18位
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isIDCard18(String value) {
		return value
				.matches("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{4}$");
	}

	/**
	 * 检查输入是否超出规定长度
	 * 
	 * @param length
	 * @param value
	 * @return
	 */
	public static boolean isLength(String value, int length) {
		return ((value == null || "".equals(value.trim())) ? 0 : value.length()) <= length;
	}

	/**
	 * 检查是否为空字符串,空：true,不空:false
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isNull(String value) {
		return value == null || "".equals(value.trim());
	}

}
