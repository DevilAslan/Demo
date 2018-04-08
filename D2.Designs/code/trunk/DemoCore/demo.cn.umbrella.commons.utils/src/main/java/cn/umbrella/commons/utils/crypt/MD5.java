/** 
 * MD5.java Created on Oct 29, 2008
 * Copyright 2008@CBI Tech. 
 * All right reserved. 
 */
package cn.umbrella.commons.utils.crypt;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5 加密工具类
 * 
 * @Time 11:41:54 AM
 */
public class MD5 {

	private final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };
	private final static char[] hexDigit = { // 用来将字节转换成 16 进制表示的字符
	'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e',
			'f' };

	/**
	 * 转换字节数组为16进制字串
	 * 
	 * @param b
	 *            字节数组
	 * @return 16进制字串
	 */
	public static String byteArrayToHexString(byte[] b) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++) {
			resultSb.append(byteToHexString(b[i]));
		}
		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n = 256 + n;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static String MD5Encode(String origin) {

		String resultString = null;
		try {
			resultString = new String(origin.trim());
			MessageDigest md = MessageDigest.getInstance("MD5");
			resultString = byteArrayToHexString(md.digest(resultString
					.getBytes("utf-8")));
		} catch (IOException e) {

		} catch (NoSuchAlgorithmException ex) {

		}
		return resultString;
	}

	public static String MD5EncodeToUpperCase(String origin) {

		return MD5Encode(origin).toUpperCase();
	}

	public static String MD5Encode(String str, String key) {

		String resultString = null;
		if (str != null && key != null) {

			byte[] src = (str + key).getBytes();
			// char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
			// '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c',
			// 'd', 'e', 'f' };

			java.security.MessageDigest md = null;
			try {
				md = java.security.MessageDigest.getInstance("MD5");
				md.update(src);
				byte tmp[] = md.digest();
				// MD5 的计算结果是一个 128 位的长整数，
				char chr[] = new char[16 * 2];
				// 每个字节用 16 进制表示的话，使用两个字符，
				int k = 0; // 表示转换结果中对应的字符位置
				for (int i = 0; i < 16; i++) {
					// 从第一个字节开始，对 MD5 的每一个字节
					// 转换成 16 进制字符的转换
					byte byte0 = tmp[i]; // 取第 i 个字节
					chr[k++] = hexDigit[byte0 >>> 4 & 0xf];
					// 取字节中高 4 位的数字转换,
					// >>> 为逻辑右移，将符号位一起右移
					chr[k++] = hexDigit[byte0 & 0xf];
					// 取字节中低 4 位的数字转换
				}
				resultString = new String(chr); // 换后的结果转换为字符串
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}
		return resultString;
	}

	public static void main(String[] args) {
		System.out.println(MD5Encode("a"));
	}

}
