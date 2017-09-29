package cn.umbrella.conmmons.util.crypt;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class UTF8 {

	public static String decode(ByteBuffer bytes) {
		return decode(bytes.toString());
	}

	public static String decode(StringBuffer bytes) {
		return decode(bytes.toString());
	}

	public static String decode(String bytes) {
		String result = null;
		try {
			result = new String(bytes.getBytes("UTF-8"), "utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			result = "decode error";
		}
		return result;
	}
}
