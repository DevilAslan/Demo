package cn.com.dhcc.common.util;

import java.util.UUID;

public class UUIDTool {
	public static String getUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
}
