package cn.leo.rdp.wish.common.utils;

import java.util.List;

/**
 * @author admin
 * @Title: EmptyUtil
 * @ProjectName wft-rdp
 * @Description: TODO
 * @date 2020/10/2614:10
 */
public class EmptyUtil {
    /**
     * 判断对象为空
     * @param obj
     * 对象名
     * @return 是否为空
     */
    @SuppressWarnings("rawtypes")
    public static boolean isEmpty(Object obj)
    {
        if (obj == null)
        {
            return true;
        }
        if ((obj instanceof List))
        {
            return ((List) obj).size() == 0;
        }
        if ((obj instanceof String))
        {
            return ((String) obj).trim().equals("");
        }
        return false;
    }

    /**
     * 判断对象不为空
     * @param obj
     * 对象名
     * @return 是否不为空
     */
    public static boolean isNotEmpty(Object obj)
    {
        return !isEmpty(obj);
    }

}
