package cn.leo.rdp.wish.common.utils;

import org.springframework.beans.BeanUtils;




/**
 * 数据转换工具类
 * @author 刘露leo
 *
 * 2020年10月17日上午11:44:32
 */
public class ConvertUtils {

	/**
	 * 单个转换
	 * 
	 * @param data
	 * @param clazz
	 * @param converter
	 * @return
	 */
	public static <T1> Object convertBean(T1 data , Object object) {

		BeanUtils.copyProperties(object, data);
        		
		return object;
	}
}
