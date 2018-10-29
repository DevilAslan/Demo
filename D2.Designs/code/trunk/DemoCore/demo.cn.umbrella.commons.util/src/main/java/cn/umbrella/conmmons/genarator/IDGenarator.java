package cn.aslan.mj.common.genarator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Author Shaw
 * @create 2018-08-15
 */
public class IDGenarator {

	/**
	 * 32位非特殊符号UUID
	 * 
	 * @return
	 */
	public static String getUUID32() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
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

	/**
	 * 20位数字流水号(17位日期+3位随机数字)
	 *
	 * @return
	 */
	public synchronized static String getDateNUM20() {
		//格式化当前时间
		SimpleDateFormat sfDate = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		//得到17位时间如：20170411094039080
		String strDate = sfDate.format(new Date());
		//为了防止高并发重复,再获取3个随机数
		String random = getRandom620(3);
		return (strDate+random);
	}

	/**
	 * 获取6-10 的随机位数数字
	 * @param length	想要生成的长度
	 * @return result
	 */
	public static String getRandom620(Integer length) {
		String result = "";
		Random rand = new Random();
		int n = 20;
		if (null != length && length > 0) {
			n = length;
		}
		int randInt = 0;
		for (int i = 0; i < n; i++) {
			randInt = rand.nextInt(10);
			result += randInt;
		}
		return result;
	}

	public static void main(String[] args) {
		System.out.println(getUUID32());
		System.out.println(getNUM16());
	}
}
