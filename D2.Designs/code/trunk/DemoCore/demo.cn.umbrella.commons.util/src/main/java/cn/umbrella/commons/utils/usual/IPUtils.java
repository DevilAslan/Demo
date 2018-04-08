package cn.umbrella.commons.utils.usual;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class IPUtils {
	public static String getRemoteIP(HttpServletRequest request) {
		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (null != ip && ip.length() > 15 && ip.contains(",")) {
			ip = ip.split(",")[0];
		}
		return ip;
	}

	public static Map<String, String> getRemoteIpAndPort(
			HttpServletRequest request) {

		String ipAndPort = request.getHeader("Host");
		System.out.println("IpUtil.getRemoteIpAndPort====" + ipAndPort);
		String[] ipAndPortList = ipAndPort.split(":");
		String port = ipAndPortList[1];
		// System.out.println("shouji_port=="+port);

		// String MD5 = request.getHeader("imsi");
		String MD5 = request.getHeader("ctauth");
		// System.out.println("shouji_头增强中MD5==="+MD5);
		String Mdn = request.getHeader("x-up-calling-line-id");
		// System.out.println("shouji_头增强中Mdn==="+Mdn);

		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (null != ip && ip.length() > 15 && ip.contains(",")) {
			ip = ip.split(",")[0];
		}

		// System.out.println("shouji_zuizhong=="+ip);

		Map<String, String> map = new HashMap<String, String>();
		map.put("ip", ip);
		map.put("port", port);
		map.put("MD5", MD5);
		map.put("Mdn", Mdn);

		return map;
	}

	// 开放平台获取IP专用,其他勿用
	public static Map<String, String> getRemoteIpAndPortOpen(
			HttpServletRequest request) {

		try {
			Enumeration<?> enumeration = request.getHeaderNames();
			while (enumeration.hasMoreElements()) {
				String element = (String) enumeration.nextElement();
				System.out.println("shouji_遍历头字段==" + element + "=="
						+ request.getHeader(element));
			}
		} catch (Exception e) {
			System.out.println("shouji_遍历出错=============");
		}

		String ipAndPort = request.getHeader("Host");
		String[] ipAndPortList = ipAndPort.split(":");
		String port = ipAndPortList[1];
		System.out.println("shouji_port==" + port);

		String MD5 = request.getHeader("Ctauth");
		System.out.println("shouji_头增强中MD5===" + MD5);
		String Mdn = request.getHeader("x-up-calling-line-id");
		System.out.println("shouji_头增强中Mdn===" + Mdn);

		String ip = request.getHeader("X-Real-IP");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("X-Forwarded-For");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (null != ip && ip.length() > 15 && ip.contains(",")) {
			ip = ip.split(",")[0];
		}

		System.out.println("shouji_zuizhong==" + ip);

		Map<String, String> map = new HashMap<String, String>();
		map.put("ip", ip);
		map.put("port", port);
		map.put("MD5", MD5);
		map.put("Mdn", Mdn);

		return map;
	}
}
