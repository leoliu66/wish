package cn.leo.rdp.wish.common.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 校验工具类
 * create by leo on 2020年9月14日下午6:47:43
 */
public class ValiUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(ValiUtils.class);
	
	/****
	 * 校验 请求参数是否为空 
	 * @param object req
	 * @param fields 是否指定参数
 	 * @return 
	 */
	public static Pair<Boolean,String> checkNotNull(Object object,String... fields){
    	if(object == null || StringUtils.isBlank(object.toString())) return Pair.of(false, "对象不能为空");
    	Class c = object.getClass();
    	if(fields.length == 0) {
    		Field[] declaredFields = c.getDeclaredFields();
    		for (Field field : declaredFields) {
				if(!checkSingleField(object, field, c)) return Pair.of(false, field.getName());
			}
    	} else {
    		for (String fieldStr : fields) {
    			Field field;
				try {
					field = c.getDeclaredField(fieldStr);
				} catch (Exception e) {
					logger.info("校验异常，没有找到该属性，fieldName={}",fieldStr,e);
					return Pair.of(false, fieldStr);
				}
    			if(!checkSingleField(object, field, c)) return Pair.of(false, fieldStr);
    		}
    	}
    	return Pair.of(true, StringUtils.EMPTY);
    }
	
	@SuppressWarnings("unchecked")
	private static boolean checkSingleField(Object object,Field field,Class c) {
		try {
			String methodName = "get"+StringUtils.capitalize(field.getName());
			if(!StringUtils.equals(methodName, "getSerialVersionUID")) {
				Method method =c.getMethod(methodName);
				Object result = method.invoke(object);
				if(result == null || StringUtils.isBlank(result.toString())) {
					logger.info("非空校验不通过，beanName={},field={}",c.getName(),field.getName());
					return false;
				}
			}
		} catch (Exception e) {
			logger.error("校验异常",e);
			return false;
		}
		return true;
	}
}
