package cn.umbrella.broadcast.config;

import cn.umbrella.commons.config.Env;

public class SignConfig {

	public static String TERMINAL = "";

	static {
		if (Env.FLAG_DEVELOP.equals(Env.FLAG_RUN)) {
			TERMINAL = "";
		} else {
			TERMINAL = "<OK学车-测试>";
		}
	}

	public static void main(String[] args) {
		System.out.println("SignConfig:" + SignConfig.TERMINAL);
	}
}