package cn.com.dhcc.common.util;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 数据相关工具类  
 *
 * @ClassName: NumberUtil  
 * @author zhou.xy
 * @date 2016年11月16日 上午11:32:49  
 *
 */
public class NumberUtil {
	public static String getFixLengthString(int length) {
		Random rm = new Random();
		// 获得随机数
		double pross = (1 + rm.nextDouble()) * Math.pow(10, length);
		// 将获得的获得随机数转化为字符串
		String fixLenthString = String.valueOf(pross);
		// 返回固定的长度的随机数
		return fixLenthString.substring(1, length + 1);
	}
	
	/**
	 * 
	* @Title: isDigital
	* @Description: 判断是否都为数字
	* @param str
	* @return boolean
	 */
	public static boolean isDigital(String str) {
		return str == null || "".equals(str) ? false : str.matches("^[0-9]*$");
	}
	
	/**
	 * 
	* @Title: extractDigital
	* @Description: 提取字符串中的数字（拼接在一起），可以改造一下返回单个数字集合
	* @param string
	* @return String
	 */
	public static String extractDigital(String string) {
		if (StringUtils.isBlank(string)) {
			return "";
		}

		string = string.trim();
		String reg = "[^\\d.]+";
		Pattern p = Pattern.compile(reg);   
		Matcher m = p.matcher(string);  
		String result = m.replaceAll("").trim();
		
		return result;
	}
	
	public static void main(String[] args) {
		String a = getFixLengthString(5);
		System.out.println(a);
		
		System.out.println(isDigital("2345678"));
		System.out.println(isDigital("2345d678"));
		System.out.println(isDigital("a2345d678"));
		System.out.println(isDigital("345 678"));
		
		String str = "100%";
		String str2 = "100次";
		String str3 = "love23next234csdn3423javaeye次";
		System.out.println(extractDigital(str));
		System.out.println(extractDigital(str2));
		System.out.println(extractDigital(str3));
	}
}
