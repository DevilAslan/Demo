/** 
 * RandomNumberUtil.java Created on Jan 26, 2010
 * Copyright 2010@JSHX. 
 * All right reserved. 
 */
package cn.umbrella.commons.util.math;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

/**
 * 
 * @Time 7:14:55 PM
 */
public class RandomNumberUtil {

	private static final int[] prefix = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

	/**
	 * 随机产生最大为18位的long型数据(long型数据的最大值是9223372036854775807,共有19位)
	 * 
	 * @param digit
	 *            用户指定随机数据的位数
	 */
	public static long randomLong(int digit) {
		if (digit >= 19 || digit <= 0)
			throw new IllegalArgumentException(
					"digit should between 1 and 18(1<=digit<=18)");
		String s = RandomStringUtils.randomNumeric(digit - 1);
		return Long.parseLong(getPrefix() + s);
	}

	/**
	 * 随机产生在指定位数之间的long型数据,位数包括两边的值,minDigit<=maxDigit
	 * 
	 * @param minDigit
	 *            用户指定随机数据的最小位数 minDigit>=1
	 * @param maxDigit
	 *            用户指定随机数据的最大位数 maxDigit<=18
	 */
	public static long randomLong(int minDigit, int maxDigit) {
		if (minDigit > maxDigit) {
			throw new IllegalArgumentException("minDigit > maxDigit");
		}
		if (minDigit <= 0 || maxDigit >= 19) {
			throw new IllegalArgumentException("minDigit <=0 || maxDigit>=19");
		}
		return randomLong(minDigit + getDigit(maxDigit - minDigit));
	}

	private static int getDigit(int max) {
		return RandomUtils.nextInt(0, max+1);
	}

	/**
	 * 保证第一位不是零
	 * 
	 * @return
	 */
	private static String getPrefix() {
		return prefix[RandomUtils.nextInt(0,9)] + "";
	}

	public void test() {
		long startTime;
		long endTime;
		int times = 8000000;
		Random rand = new Random();
		startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			@SuppressWarnings("unused")
			int a = rand.nextInt(5);
			// System.out.println(a);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Random.nextInt(): " + (endTime - startTime));
		startTime = System.currentTimeMillis();
		for (int i = 0; i < times; i++) {
			@SuppressWarnings("unused")
			int a = (int) (Math.random() * 5);
		}
		endTime = System.currentTimeMillis();
		System.out.println("Math.random(): " + (endTime - startTime));
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(randomLong(18));
		System.out.println(Long.MAX_VALUE);
		System.out.println(Long.MIN_VALUE);
		System.out.println(System.currentTimeMillis());
		int len = 2;
		System.out.println(randomLong(len));
		System.out.println(randomLong(len));
		System.out.println(randomLong(len));
		System.out.println(randomLong(len));
		System.out.println(randomLong(len));
		System.out.println(randomLong(len));
		System.out.println(randomLong(len));
	}

}
