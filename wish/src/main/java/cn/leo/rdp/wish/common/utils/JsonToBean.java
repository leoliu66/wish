package cn.leo.rdp.wish.common.utils;

import cn.leo.common.utils.RdpRedisson;
import org.apache.commons.lang3.StringUtils;

/**
 * 统一转换
 * @version:
 * @Description:  
 * @author: leo
 * @date: 2019年1月2日 下午3:11:14
 */
public class JsonToBean {
	/**
	 * 获取后台vo
	 * @version:
	 * @author: leo
	 * @date: 2019年1月2日 下午3:18:26
	 * @param
	 */
	 /*public static AmindUserInfoVo getRequestVo(RdpRedisson rdpRedisson, Long userId){
	        String json = rdpRedisson.get("adminUserVo"+userId);
	        AmindUserInfoVo vo = null;
	        if(StringUtils.isNotBlank(json)){
	        	vo = GsonUtils.GsonToBean(json,AmindUserInfoclass);
	        }
	        return vo;
	 }
	 
	 *//**
	 * 存放后台vo
	 * @version:
	 * @author: leo
	 * @date: 2019年1月2日 下午3:18:26
	 * @param
	 *//*
	 public static void setRequestVo(RdpRedisson rdpRedisson,Long userId,AmindUserInfoVo vo){
		 String key  = "adminUserVo"+userId;
		 rdpRedisson.set(key, GsonUtils.GsonString(vo));
	 }*/
}
