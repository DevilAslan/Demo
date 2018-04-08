package cn.umbrella.conmmons.genarator;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;



import cn.umbrella.commons.config.CryptConfig;

import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

@SuppressWarnings("restriction")
public class KEYGenerator {
	
	public static String getKey(String keyCode) {
		// 1.初始化key秘钥
		KeyGenerator keyGenerator;
		String keyencode = null;
		try {
			keyGenerator = KeyGenerator.getInstance(keyCode);
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			// 转换key秘钥
			DESedeKeySpec deSedeKeySpec = new DESedeKeySpec(
					secretKey.getEncoded());
			SecretKeyFactory secretKeyFactory = SecretKeyFactory
					.getInstance(keyCode);
			Key key = secretKeyFactory.generateSecret(deSedeKeySpec);
			keyencode = HexBin.encode(key.getEncoded());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return keyencode;
	}
	
	public static void main(String[] args) {
		System.out.println(getKey(CryptConfig.DESEDE));
	}
}
