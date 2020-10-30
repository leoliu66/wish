package cn.leo.rdp.wish.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

/**
 * bean工具处理类
 * create by leo on 2020年10月17日上午10:31:25
 */
public class JavaBeanUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JavaBeanUtils.class);
	
	private static final Map<String, BeanCopier> beanCopierCache = new ConcurrentHashMap<String, BeanCopier>();
	
	public static Map<String,String> obj2Map(Object bean){
		try {
			Class type = bean.getClass(); 
			Map<String,String> returnMap = new TreeMap<String,String>(); 
			BeanInfo beanInfo = Introspector.getBeanInfo(type); 
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
			for (int i = 0; i< propertyDescriptors.length; i++) { 
				PropertyDescriptor descriptor = propertyDescriptors[i]; 
				String propertyName = descriptor.getName(); 
				if (!propertyName.equals("class")) { 
					Method readMethod = descriptor.getReadMethod(); 
					Object result = readMethod.invoke(bean, new Object[0]); 
					if (result != null) { 
						returnMap.put(propertyName, String.valueOf(result)); 
					} else { 
						returnMap.put(propertyName, ""); 
					} 
				} 
			} 
			return returnMap; 
		} catch (Exception e) {
			logger.error("将javabean转map失败------->",e);
			return null;
		}
	}
	
	
	public static Map<String, String> obj2MapWithOutNull(Object bean){
		try {
			Class type = bean.getClass(); 
			Map<String,String> returnMap = new TreeMap<String,String>(); 
			BeanInfo beanInfo = Introspector.getBeanInfo(type); 
			PropertyDescriptor[] propertyDescriptors =  beanInfo.getPropertyDescriptors(); 
			for (int i = 0; i< propertyDescriptors.length; i++) { 
				PropertyDescriptor descriptor = propertyDescriptors[i]; 
				String propertyName = descriptor.getName(); 
				if (!propertyName.equals("class")) { 
					Method readMethod = descriptor.getReadMethod(); 
					Object result = readMethod.invoke(bean, new Object[0]); 
					if (result != null && StringUtils.isNotBlank(result.toString())) { 
						returnMap.put(propertyName, String.valueOf(result)); 
					}
				} 
			} 
			return returnMap; 
		} catch (Exception e) {
			logger.error("将javabean转map失败------->",e);
			return null;
		}
	}
	
	public static <T> T map2Obj(Map<String, String> map, Class<T> beanClass) throws Exception {    
        if (map == null) return null;    
        T obj = beanClass.newInstance();  
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
        for (PropertyDescriptor property : propertyDescriptors) {  
            Method setter = property.getWriteMethod();    
            if (setter != null) {  
                setter.invoke(obj, map.get(property.getName()));   
            }  
        }  
        return obj;  
    }   
	
	
	/**
     * 拷贝属性, 默认转换处理
     *
     * @param source      源对象
     * @param targetClass Class 目标类class
     * @return 目标对象
     */
    public static <T1, T2> T2 copyProperties(T1 source, Class<T2> targetClass) {
        return copyProperties(source, targetClass, null);
    }

    /**
     * 拷贝属性
     *
     * @param source      源对象
     * @param targetClass Class 目标类class
     * @param converter   转换处理类
     * @return 目标对象
     */
    public static <T1, T2> T2 copyProperties(T1 source, Class<T2> targetClass, Converter converter) {
        if (source == null) return null;

        T2 t;
        try {
            t = targetClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        copyProperties(source, t, converter);
        return t;
    }

    /**
     * 拷贝属性, 默认转换处理
     *
     * @param source 源对象
     * @param target 目标对象
     */
    public static <T1, T2> void copyProperties(T1 source, T2 target) {
        copyProperties(source, target, null);
    }

    /**
     * 拷贝属性
     *
     * @param source    源对象
     * @param target    目标对象
     * @param converter 转换处理类
     */
    public static <T1, T2> void copyProperties(T1 source, T2 target, Converter converter) {
        if (source == null || target == null) {
            return;
        }
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass(), converter != null);
        copier.copy(source, target, converter);
    }

    /**
     * 拷贝列表数据的属性, 无转换器
     *
     * @param sourceList  源对象列表
     * @param targetClass 目标对象class
     * @return 目标对象列表
     */
    public static <T1, T2> List<T2> copyListProperties(List<T1> sourceList, Class<T2> targetClass) {
        return copyListProperties(sourceList, targetClass, null);
    }

    /**
     * 拷贝列表数据的属性
     *
     * @param sourceList  源对象列表
     * @param targetClass 目标对象class
     * @param converter   转换对象
     * @return 目标对象列表
     */
    public static <T1, T2> List<T2> copyListProperties(List<T1> sourceList, Class<T2> targetClass, Converter converter) {
        if (sourceList == null || sourceList.size() == 0) {
            return Collections.emptyList();
        }

        List<T2> resultList = new ArrayList<T2>(sourceList.size());
        for (T1 t1 : sourceList) {
            T2 t2;
            try {
                t2 = targetClass.newInstance();
                copyProperties(t1, t2, converter);
                resultList.add(t2);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return resultList;
    }

    /**
     * 获取缓存的key
     *
     * @param class1      Class 源对象所属class
     * @param class2      Class 目标对象所属class
     * @param convertFlag boolean 是否转换
     * @return 缓存key
     */
    private static <T1, T2> String generateKey(Class<T1> class1, Class<T2> class2, boolean convertFlag) {
        return class1.toString() + class2.toString() + String.valueOf(convertFlag);
    }

    /**
     * 获取BeanCopier
     *
     * @param sourceClass Class 源对象所属class
     * @param targetClass Class 目标对象所属class
     * @param convertFlag boolean 是否转换
     * @return 转换实现对象
     */
    private static <T1, T2> BeanCopier getBeanCopier(Class<T1> sourceClass, Class<T2> targetClass, boolean convertFlag) {
        String beanKey = generateKey(sourceClass, targetClass, convertFlag);
        BeanCopier copier;
        if (!beanCopierCache.containsKey(beanKey)) {
            copier = BeanCopier.create(sourceClass, targetClass, convertFlag);
            beanCopierCache.put(beanKey, copier);
        } else {
            copier = beanCopierCache.get(beanKey);
        }
        return copier;
    }
}
