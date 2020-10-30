package cn.leo.rdp.wish.common.utils;

import java.security.SecureRandom;
import java.util.Random;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 * @author leo
 *
 */
public class IdGen {

	private static SecureRandom random = new SecureRandom();

	/**
	 * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 使用SecureRandom随机生成Long.
	 */
	public static long randomLong() {
		return Math.abs(random.nextLong());
	}
	
	/**
	 * 随机数
	 * @param args
	 */
	public static String randomint() {
		return String.valueOf(new Random().nextInt(899999) + 100000);
	}

	public static void main(String[] args) {
		System.out.println(uuid());
	}
}
