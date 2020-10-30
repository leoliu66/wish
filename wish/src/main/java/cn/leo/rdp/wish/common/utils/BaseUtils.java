package cn.leo.rdp.wish.common.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
/**
 * 基础工具类
 * @version:
 * @Description:  
 * @author: leo
 * @date: 2020年10月26日 下午5:42:25
 */
public class BaseUtils {
	public static final BaseUtils create = new BaseUtils();
	/**
	 * 校验手机号码是否正确
	 * @version:
	 * @author: leo
	 * @date: 2020年10月26日 下午5:43:08
	 * @param
	 */
	public boolean isPhone(String phone) {
	    String regex = "^((13[0-9])|(14[0-9])|(15([0-3]|[5-9]))|(166)|(17[0-9])|(18[0-9])|(19[8|9]))\\d{8}$";
	    if (phone.length() != 11) {
	    	return false;
	    } else {
	        Pattern p = Pattern.compile(regex);
	        Matcher m = p.matcher(phone);
	        boolean isMatch = m.matches();
	        return isMatch;
	    }
	}
	/**
	 * 校验金额
	 * @version:
	 * @author: leo
	 * @date: 2020年10月26日 下午5:58:33
	 * @param
	 */
	 public boolean isNumber(String str){ 
        Pattern pattern=Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$"); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(str); 
        if(match.matches()==false){ 
           return false; 
        }else{ 
           return true; 
        } 
	 }
	 
	/**
	 * 校验是否为空和长度是否超过20位
	 * @version:
	 * @author: leo
	 * @date: 2020年10月26日 下午5:58:33
	 * @param
	 */
	 public boolean isParameterRight(String str){ 
        if(StringUtils.isBlank(str)){
        	return false;
        }
        if(str.length()>20){
    		return false;
        }
        return true;
	 }
}
