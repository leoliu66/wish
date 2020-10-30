package cn.leo.rdp.wish.common.utils;

/**
 * 文件工具
 * @author leo
 *
 */
public class FileUtils {

	public static String getFileId(String relativePath) {
		return relativePath.substring(relativePath.lastIndexOf("/")+1, relativePath.lastIndexOf("."));
	}
}
