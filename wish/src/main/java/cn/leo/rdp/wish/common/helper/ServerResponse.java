package cn.leo.rdp.wish.common.helper;
import java.io.Serializable;

import cn.leo.rdp.wish.common.enums.ResponseCodeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 
 * @author 刘露leo
 * */
//保证序列化json的时候,如果是null的对象,key也会消失
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class ServerResponse<T> implements Serializable {

    private static final long serialVersionUID = 1705128918324618250L;
    private int status;
    private String msg;
    private T data;

    private ServerResponse(){
    }
    private ServerResponse(int status){
        this.status = status;
    }

    private ServerResponse(int status, T data){
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data){
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg){
        this.status = status;
        this.msg = msg;
    }

    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseCodeEnum.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }

    public T getData(){
        return data;
    }

    public String getMsg(){
        return msg;
    }

    public static <T> ServerResponse<T> createBySuccess(){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode());
    }

    public static <T> ServerResponse<T> createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode(),msg);
    }

    public static <T> ServerResponse<T> createBySuccess(T data){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode(),data);
    }

    public static <T> ServerResponse<T> createBySuccess(String msg, T data){
        return new ServerResponse<T>(ResponseCodeEnum.SUCCESS.getCode(),msg,data);
    }

    public static <T> ServerResponse<T> createByError(){
        return new ServerResponse<T>(ResponseCodeEnum.ERROR.getCode(),ResponseCodeEnum.ERROR.getDesc());
    }

    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCodeEnum.ERROR.getCode(),errorMessage);
    }
    
    public static <T> ServerResponse<T> createByErrorMessage(String errorMessage, T data){
        return new ServerResponse<T>(ResponseCodeEnum.ERROR.getCode(),errorMessage,data);
    }

    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
    
    public static <T> ServerResponse<T> createByErrorCodeMessage(int errorCode, String errorMessage, T data){
        return new ServerResponse<T>(errorCode,errorMessage,data);
    }
    
    public static <T> ServerResponse<T> createByAuthMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCodeEnum.AUTH.getCode(),errorMessage);
    }
    public static <T> ServerResponse<T> createByCodeEnum(ResponseCodeEnum codeEnum){
        return new ServerResponse<T>(codeEnum.getCode(),codeEnum.getDesc());
    }
    public static <T> ServerResponse<T> createByCodeEnumData(ResponseCodeEnum codeEnum, T t){
        return new ServerResponse<T>(codeEnum.getCode(),codeEnum.getDesc(), t);
    }
    public static <T> ServerResponse<T> formatErrorCode(ResponseCodeEnum codeEnum, Object... args){
        return new ServerResponse<T>(codeEnum.getCode(),String.format(codeEnum.getDesc(), args));
    }
}
