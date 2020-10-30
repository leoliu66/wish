package cn.leo.rdp.wish.common.utils;

import java.io.Serializable;

import com.alibaba.druid.support.json.JSONUtils;

/**
 * 响应消息封装
 * @version:
 * @Description:  
 * @author: leo
 * @date: 2020年10月22日 下午6:06:07
 */
public class MsgResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	/**业务状态: true 成功      false:失败**/
	private boolean success = false;
	
	/**业务代码**/
	private String code;
	
	/**响应内容**/
	private String message;
	
	/**响应扩展数据**/
	private Object data;

	public MsgResponse(){}

	public boolean isSuccess() {
		return success;
	}

	public MsgResponse setSuccess(boolean success) {
		this.success = success;
		return this;
	}

	public String getCode() {
		return code;
	}

	public MsgResponse setCode(String code) {
		this.code = code;
		return this;
	}

	public String getMessage() {
		return message;
	}

	public MsgResponse setMessage(String message) {
		this.message = message;
		return this;
	}

	public Object getData() {
		return data;
	}

	public MsgResponse setData(Object data) {
		this.data = data;
		return this;
	}

	@Override
	public String toString() {
		return JSONUtils.toJSONString(this);
	};
	
	/**
	 * 创建一个新的MsgResponse
	 * @version:
	 * @author: leo
	 * @date: 2019年1月11日
	 * @param
	 */
	public static MsgResponse createMsgRespone(){
		return new MsgResponse();
	}
	
	
	
	
	
}
