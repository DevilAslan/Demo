package cn.umbrella.wechat.util;

/**
 * @author chu.feifei 整数信用管理器(用于https请求中信任所有证书)
 */
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.X509TrustManager;

public class MyX509TrustManager implements X509TrustManager {

	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {

	}

	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {

	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}

}
