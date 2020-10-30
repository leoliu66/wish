package cn.leo.rdp.wish.common.helper;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
/**
 * 汉字转拼音
 * @author 刘露leo
 *
 * 2019年1月2日上午10:10:07
 */
public class HanyuPinyinHelper {
	 /** 
      * 将文字转为汉语拼音
	 * @throws BadHanyuPinyinOutputFormatCombination 
     */
    public static String toHanyuPinyin(String ChineseLanguage) throws BadHanyuPinyinOutputFormatCombination{
        char[] cl_chars = ChineseLanguage.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ;
        for (int i=0; i<cl_chars.length; i++){
            if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){// 如果字符是中文,则将中文转为汉语拼音
                hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
            } else {// 如果字符不是中文,则不转换
                hanyupinyin += cl_chars[i];
            }
        }
        return hanyupinyin;
    }
    
}
