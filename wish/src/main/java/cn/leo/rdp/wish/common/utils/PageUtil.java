package cn.leo.rdp.wish.common.utils;

import java.util.LinkedHashMap;
import java.util.Map;

public class PageUtil {
	
	
	public static Map<String, Object> groupPage(Integer totalNums, Integer rows, Integer page)
	  {
	    Map<String,Object> maps = new LinkedHashMap<String,Object>();
	    int totalpage = 0;
	    if(totalNums%rows>0) {
	    	totalpage = totalNums / rows + 1;
	    }else {
	    	totalpage = totalNums / rows;
	    }
	    
	    Integer nextPage = 1;
	    if (totalNums.intValue() > page.intValue() + 1) {
	    	nextPage = 0;
	    }
	    maps.put("nextPage", nextPage);
	    maps.put("totalPage", totalpage);
	    maps.put("page", page);

	    return maps;
	  }
	
	/***
	 * 获取总页数
	 */
	public static int totalPage(Integer totalNums, Integer rows) {
		int totalpage = 0;
		if(totalNums%rows>0) {
	    	totalpage = totalNums / rows + 1;
	    }else {
	    	totalpage = totalNums / rows;
	    }
		return totalpage;
	}

}
