package cn.umbrella.conmmon.util.crypt;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public class DESede {
	@SuppressWarnings("unused")
	private static final String Algorithm = "DESede";

	public DESede() {
	}

	public static byte[] encryptMode(byte keybyte[], byte src[]) {
		try {
			javax.crypto.SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(1, deskey);
			return c1.doFinal(src);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String encryptMode(String key, String src) {
		String res = null;
		try {
			javax.crypto.SecretKey deskey = new SecretKeySpec(
					build3DesKey(key), "DESede");
			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(1, deskey);
			res = Base64.encodeByte(c1.doFinal(src.getBytes()));
			return res;
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static byte[] decryptMode(byte keybyte[], byte src[]) {
		try {
			javax.crypto.SecretKey deskey = new SecretKeySpec(keybyte, "DESede");
			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(2, deskey);
			return c1.doFinal(src);
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String decryptMode(String key, String src) {
		try {
			javax.crypto.SecretKey deskey = new SecretKeySpec(
					build3DesKey(key), "DESede");
			Cipher c1 = Cipher.getInstance("DESede");
			c1.init(2, deskey);
			byte[] byteSrc = Base64.decode(src);
			System.out.println(c1.doFinal(byteSrc));
			return new String(c1.doFinal(byteSrc));
		} catch (NoSuchAlgorithmException e1) {
			e1.printStackTrace();
		} catch (NoSuchPaddingException e2) {
			e2.printStackTrace();
		} catch (Exception e3) {
			e3.printStackTrace();
		}
		return null;
	}

	public static String byte2hex(byte b[]) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0xff);
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}

		return hs.toUpperCase();
	}

	/*
	 * 根据字符串生成密钥字节数组
	 * 
	 * @param keyStr 密钥字符串
	 * 
	 * @return
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public static byte[] build3DesKey(String keyStr)
			throws UnsupportedEncodingException {
		byte[] key = new byte[24]; // 声明一个24位的字节数组，默认里面都是0
		byte[] temp = keyStr.getBytes("UTF-8"); // 将字符串转成字节数组

		/*
		 * 执行数组拷贝 System.arraycopy(源数组，从源数组哪里开始拷贝，目标数组，拷贝多少位)
		 */
		if (key.length > temp.length) {
			// 如果temp不够24位，则拷贝temp数组整个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, temp.length);
		} else {
			// 如果temp大于24位，则拷贝temp数组24个长度的内容到key数组中
			System.arraycopy(temp, 0, key, 0, key.length);
		}
		return key;
	}
}
