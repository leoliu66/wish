package cn.leo.rdp.wish.common.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 获取请求ip 支持代理网络下
 * @author leo
 */
public class IpUtils {

	/**
	 * 获取当前网络ip
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("X-Forwarded-For");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					//mac 这里不设置为非常慢
					//scutil --set HostName "localhost"
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		String[] ips = StringUtils.split(ipAddress,",");
		return ips[0];
	}
	
	public static String getLocalIp()
	  {
	    String localip = null;
	    String netip = null;
	    try {
	      Enumeration netInterfaces = NetworkInterface.getNetworkInterfaces();
	      InetAddress ip = null;
	      boolean finded = false;
	      while ((netInterfaces.hasMoreElements()) && (!finded)) {
	        NetworkInterface ni = (NetworkInterface)netInterfaces.nextElement();
	        Enumeration address = ni.getInetAddresses();
	        while (address.hasMoreElements()) {
	          ip = (InetAddress)address.nextElement();
	          if ((!ip.isSiteLocalAddress()) && (!ip.isLoopbackAddress()) && (ip.getHostAddress().indexOf(":") == -1)) {
	            netip = ip.getHostAddress();
	            finded = true;
	            break;
	          }
	          if ((ip.isSiteLocalAddress()) && (!ip.isLoopbackAddress()) && (ip.getHostAddress().indexOf(":") == -1)) {
	            localip = ip.getHostAddress();
	          }
	        }
	      }
	    }
	    catch (SocketException e) {
	      e.printStackTrace();
	    }

	    if ((netip != null) && (!"".equals(netip))) {
	      return netip;
	    }
	    return localip;
	  }
}
