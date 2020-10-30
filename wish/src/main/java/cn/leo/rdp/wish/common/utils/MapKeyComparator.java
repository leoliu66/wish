package cn.leo.rdp.wish.common.utils;

import java.util.Comparator;

class MapKeyComparator
  implements Comparator<String>
{
  public int compare(String str1, String str2)
  {
    return str1.compareTo(str2);
  }
}
