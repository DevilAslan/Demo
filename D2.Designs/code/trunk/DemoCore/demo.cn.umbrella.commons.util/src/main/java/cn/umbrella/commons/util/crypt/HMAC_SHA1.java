package cn.umbrella.commons.util.crypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class HMAC_SHA1 {
	/**
	 * 使用 HMAC-SHA1 签名方法对对encryptText进行签名
	 * 
	 * @param encryptText
	 *            被签名的字符串
	 * @param encryptKey
	 *            密钥
	 * @return
	 * @throws Exception
	 */
	public static String HmacSHA1Encrypt(String encryptText, String encryptKey)
			throws Exception, UnsupportedEncodingException,
			NoSuchAlgorithmException, InvalidKeyException {
		byte[] data = encryptKey.getBytes("UTF-8");
		// 根据给定的字节数组构造一个密钥,第二参数指定一个密钥算法的名称
		SecretKey secretKey = new SecretKeySpec(data, "HMAC-SHA1");
		// 生成一个指定 Mac 算法 的 Mac 对象
		Mac mac = Mac.getInstance("HmacSHA1");
		// 用给定密钥初始化 Mac 对象
		mac.init(secretKey);
		byte[] text = encryptText.getBytes("UTF-8");
		// 完成 Mac 操作
		return getBase64Bytes(mac.doFinal(text));
	}

	public static String getBase64(String str) {
		byte[] b = null;
		String s = null;
		try {
			b = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	public static String getBase64Bytes(byte[] b) {
		String s = null;
		if (b != null) {
			s = new BASE64Encoder().encode(b);
		}
		return s;
	}

	public static void main(String[] args) throws Exception {
		// first rule
		String encryptKey = "wew00Ms09";// 分配得到
		String code = "65qw5r54";// 一般为识别编码
		SimpleDateFormat transactionSdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String eventTime = transactionSdf.format(new Date());
		String encryptText = code + eventTime + "";

		String mac = HMAC_SHA1.HmacSHA1Encrypt(encryptText, encryptKey);
		System.out.println(mac);

	}

}
