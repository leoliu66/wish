package cn.leo.rdp.wish.common.utils;
import java.util.Random;
import java.util.UUID;

/**
 * 各种编号生成工厂
 * @version:
 * @Description:  
 * @author: leo
 * @date: 2020年10月26日 下午3:42:03
 */
public class CodeFactory {
	public static final CodeFactory create = new CodeFactory();

	/**
	 * 生成指定长度的随机数 字符串
	 * @param length 指定生成字符串的长度
	 * @return 返回指定长度的随机数 字符串
	 */
	public String randomString (int length) {
		StringBuilder code = new StringBuilder(length);
		Random random = new Random();
		for (int i = 0; i < length; i++) code.append(random.nextInt(10));
		return code.toString();
	}
	
	/**
	 * 返回批次编号
	 * @version:
	 * @author: leo
	 * @date: 2020年10月26日 下午3:43:13
	 * @param
	 */
	public String payBatchNo(){
		return "PC"+randomString(9);	
	}
	
	/**
	 * 返回账单编号
	 * @version:
	 * @author: leo
	 * @date: 2020年10月26日 下午3:43:13
	 * @param
	 */
	public String payBillDeatailNo(){
		return "ZD"+DateUtils.getNow()+randomString(4);	
	}
	
	
    /**
     * 获得8个长度的十六进制的UUID
     * @version:
     * @author: leo
     * @date: 2020年10月26日 下午3:48:01
     * @param
     */
    public String get8UUID(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }
  
    
}
