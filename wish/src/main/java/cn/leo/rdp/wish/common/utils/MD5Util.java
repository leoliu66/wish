package cn.leo.rdp.wish.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther: leo
 * @Date: 2020/9/13 16:40
 * @Description: md5加密工具
 */
public class MD5Util {

    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 返回大写MD5
     *
     * @param origin
     * @param charsetname
     * @return
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString.getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString.getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString.toUpperCase();
    }
    
    /****
     * 双重加密
     * @param origin 加密参数
     * @param charsetname 编码
     * @return
     */
    public static String MD5EncodeDouble(String origin, String charsetname) {
    	return MD5Encode(MD5Encode(origin,charsetname).toUpperCase(),charsetname).toUpperCase();
    }

    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String md5s(String plainText) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(plainText.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            return buf.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }

    }
    
    public static void main(String[] args) {
    	//System.out.println(MD5Encode(MD5Encode("123456","utf-8").toUpperCase(),"utf-8").toUpperCase());;
    	System.out.println(MD5Encode("body=%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98%31%32%33%E8%AF%B7%E9%97%AE&deviceInfo=%E8%AE%BE%E5%A4%87%E5%8F%B7&payPlatform=pay.weixin.raw.app&token=tc71d390540ba46119338203c8faa4386&total_fee=100&key=k487f2c77c1914fba8a47b7ec45730205","utf-8"));
    }
}
