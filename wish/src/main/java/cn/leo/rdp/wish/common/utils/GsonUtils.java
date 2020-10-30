package cn.leo.rdp.wish.common.utils;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * 
* @ClassName: GsonUtils  
* @Description: Json数据转换工具类  
* @author leo
* @date 2017年9月14日 
* 
* update 
* @author leo
* @data 2020-10-15 16:44
*
 */
public class GsonUtils {

	private static Gson gson = null;  
	
    static {  
        if (gson == null) {  
            gson = new Gson();  
        }  
    }  
  
    /**
     * 
    * @Title: GsonString  
    * @Description: 将object对象转成json字符串 
    * @author leo
    * @date 2017年9月14日 
    *  @param obj
    *  @return String
    * @throws
     */
    public static String GsonString(Object obj) {  
        String json = null;  
        if (null != gson) {  
        	json = gson.toJson(obj);  
        }  
        return json;  
    }  
  
  
    /**
     * 
    * @Title: GsonToBean  
    * @Description: 将json字符串转成泛型bean 
    * @author leo
    * @date 2017年9月14日 
    *  @param json
    *  @param cls
    *  @return T
    * @throws
     */
    public static <T> T GsonToBean(String json, Class<T> cls) {  
        T t = null;  
        if (null != gson) {  
            t = gson.fromJson(json, cls);  
        }  
        return t;  
    }  
    
    /**
     * 
    * @Title: jsonToList  
    * @Description: json字符串转List
    * @author leo
    * @date 2017年9月14日 
    *  @param json
    *  @param cls
    *  @return List<T>
    * @throws
     */
    public static <T> List<T> jsonToList(String json, Class<T> cls) {  
        Gson gson = new Gson();  
        List<T> list = new ArrayList<T>();  
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        for(final JsonElement elem : array){  
            list.add(gson.fromJson(elem, cls));  
        }  
        return list;  
    }  
    
    /**
     * 
    * @Title: GsonToListMaps  
    * @Description: json字符串转成list中有map的集合
    * @author leo
    * @date 2017年9月14日 
    *  @param json
    *  @return List<Map<String,T>>
    * @throws
     */
    public static <T> List<Map<String, T>> GsonToListMaps(String json) {  
        List<Map<String, T>> list = null;  
        if (gson != null) {  
            list = gson.fromJson(json,new TypeToken<List<Map<String, T>>>() {}.getType());  
        }  
        return list;  
    }  
    
    /**
     * 
    * @Title: GsonToMaps  
    * @Description: json字符串转Map对象
    * @author leo
    * @date 2017年9月14日 
    *  @param json
    *  @return Map<String,T>
    * @throws
     */
    public static <T> Map<String, T> GsonToMaps(String json) {  
        Map<String, T> map = null;  
        if (gson != null) {  
            map = gson.fromJson(json, new TypeToken<Map<String, T>>() {}.getType());  
        }  
        return map;  
    } 
	
    
}
