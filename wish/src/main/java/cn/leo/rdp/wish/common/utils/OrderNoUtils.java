package cn.leo.rdp.wish.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 订单编号工具类
 * @author leo
 */
public class OrderNoUtils {
	
	private static final ThreadLocal<SimpleDateFormat> FORMATTER = new ThreadLocal<SimpleDateFormat>();
	
	/**
	 * 生成支付订单号
	 * @return
	 */
	public static String genPayOrderNo(String stuno, Long schId, Integer billType) {
		if(FORMATTER.get() == null) {
			FORMATTER.set(new SimpleDateFormat("yyyyMMddHHmmssSSS"));
		}
		StringBuilder sb = new StringBuilder();
		sb.append(stuno);
		sb.append(schId);	
		sb.append(FORMATTER.get().format(new Date()));
		sb.append(billType);
		return sb.toString();
	}
}
