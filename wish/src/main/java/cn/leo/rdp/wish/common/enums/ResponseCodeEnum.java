package cn.leo.rdp.wish.common.enums;
/**
 * 
 * @author 刘露leo
 *
 * 2020年10月12日下午3:41:54
 */
public enum ResponseCodeEnum {
	SUCCESS(0,"SUCCESS"),//接口返回成功
    ERROR(1,"ERROR"),//接口返回失败
    AUTH(2,"AUTH"),
    PARAM_NOT_NULL(3,"参数%s不能为空"),
    SUBMIT_REPEAT(4,"请勿重复提交"),
    NOCHANNELNO(1000,"渠道id不能为空"),
    ERRORCHANNELNO(1001,"渠道id格式不正确"),
    NOCHANNEL(1002,"渠道不存在或者未激活"),
    NOSCHOOL(1003,"渠道下无激活学校"),
    NOSCHOOLNO(1004,"學校id不能为空"),
    ERRORSCHOOLNO(1005,"學校id格式不正确"),
    NOTSCHOOL(1006,"学校不存在或者未激活"),
    NOSTUDENTNO(1007,"学生学号不能为空"),
    NOTSTUDENT(1008,"学号对应的学生不存在或者不可用"),
    NOADDCHANNEL(1009,"新增渠道号失败,请检查渠道编号或者登录名是否重复或过长"),
    NOADDSCHOOL(1010,"新增学校失败,请检查学校商户号或者登录名是否重复或者过长"),
    NOTCHANNELID(1011,"渠道id不能为空"),
    NOTCHANNELKEY(1012,"渠道加密方式不能为空"),
    NOTCHANNELMD5KEY(1013,"渠道MD5秘钥不能为空"),
    NOTCHANNELRSAKEY(1014,"渠道RSA秘钥不能为空"),
    NOTORDERNO(1015,"订单号不能为空"),
    NOTORDER(1015,"此订单不存在"),
    NOUPDATECHANNEL(1016,"修改渠道号失败,请检查渠道编号是否重复或过长"),
    NOUPDATESCHOOL(1017,"修改学校失败,请检查学校商户号是否重复或者过长"),
    
    BILL_NOT_FOUND(2001,"账单未找到"),
    BILL_STATUS_NOT_NOT_PAY(2002,"账单%s非未支付状态"),
    AMOUNT_CHECK_WRONG(2003,"金额校验不通过"),
    THIRD_FAIL(2004,"第三方调用失败"),
    
    
    SIGN_FAIL(3001,"验签失败"),
    
    SYSTEMBUSY(9999,"系统繁忙");
	
    private final int code;
    private final String desc;

    ResponseCodeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }

    public String getDesc(){
        return desc;
    }
    
}
