package cn.leo.rdp.wish.common.enums;

/**
 * 签名方式枚举
 * create by leo on 2020年10月24日下午3:56:18
 */
public enum SignTypeEnum {

	MD5("MD5"),
	RSA_1_256("RSA_1_256"),
	RSA_1_1("RSA_1_1"),
    ;
    private final String code;

    SignTypeEnum(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

}
