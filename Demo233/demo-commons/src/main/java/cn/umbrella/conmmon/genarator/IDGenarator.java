package cn.umbrella.conmmon.genarator;

import java.util.UUID;

/**
 * 
 * @create 2012-9-7
 */
public class IDGenarator {

	/**
	 * 32位非特殊符号ID
	 * 
	 * @return
	 */
	public static String getID32() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 16位数字流水号
	 * 
	 * @return
	 */
	public synchronized static String getNUM16() {
		String machineID = "1";
		String orderNO = machineID
				+ (System.currentTimeMillis() + "").substring(1)
				+ (System.nanoTime() + "").substring(7, 10);
		return orderNO;
	}

	public static void main(String[] args) {
		System.out.println(getID32());
		System.out.println(getNUM16());
	}
}
