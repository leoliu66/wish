package cn.leo.rdp.wish.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther: leo
 * @Date: 2020/9/13 17:10
 * @Description: map排序工具
 */

public class MapSort {
	public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
		if ((map == null) || (map.isEmpty())) {
			return null;
		}
		Map sortMap = new TreeMap(new MapKeyComparator());
		sortMap.putAll(map);
		return sortMap;
	}

	public static String getQueryString(Map<String, Object> pMap) {
		StringBuffer queryStr = new StringBuffer("");
		try {
			Map resultMap = sortMapByKey(pMap);
			Iterator iter = resultMap.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String)iter.next();
				if(StringUtils.isNotBlank(resultMap.get(key).toString())) {
					if (queryStr.toString().equals("")) {
				          //queryStr.append(key + "=" + URLEncoder.encode(resultMap.get(key).toString(), "UTF-8"));
						queryStr.append(key + "=" + resultMap.get(key).toString());
				    }else {
				        //queryStr.append("&" + key + "=" + URLEncoder.encode(resultMap.get(key).toString(), "UTF-8"));
				    	queryStr.append("&" + key + "=" + resultMap.get(key).toString());
				    }
				}
			}
		} catch (Exception localException) {
		}
		return queryStr.toString();
	}
	
}
