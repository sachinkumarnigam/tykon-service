/**
 * 
 */
package com.tykon.api.framework.service.core.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author sachin
 *
 */
public class ClientUtil {

	protected final static Logger logger = LoggerFactory.getLogger(ClientUtil.class);

	// Get Client IPAddress
	public static String getClientIPAddress(HttpServletRequest properRequest) {
		String ipAddress = properRequest.getHeader("X-FORWARDED-FOR");
		if (ipAddress == null) {
			ipAddress = properRequest.getRemoteAddr();
		}
		if (ipAddress != null) {
			String[] s = ipAddress.split(",");
			return s[0];
		}
		return ipAddress;
	}

	// Get Client MAC Address
	public static String getMacAddress(InetAddress ip) throws SocketException {
		String macAddress;
		NetworkInterface network = NetworkInterface.getByInetAddress(ip);
		byte[] mac = network.getHardwareAddress();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < mac.length; i++) {
			sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
		}
		macAddress = sb.toString();
		return macAddress;
	}

	// Get Client Browser Details
	public static String getUserAgent(HttpServletRequest request) {
		return request.getHeader("user-agent");
	}

	// Get Local Server IP
	public static String getLocalServerIP(HttpServletRequest properRequest) {
		String localServerIP;
		localServerIP = properRequest.getLocalAddr();
		return localServerIP;
	}

	public static InetAddress getInetAddress() throws UnknownHostException {
		return InetAddress.getLocalHost();
	}

	public static String getServerIp() {
		String serverIp = "";
		InetAddress addr;
		try {
			NetworkInterface iface = null;
			for (final Enumeration ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
				iface = (NetworkInterface) ifaces.nextElement();
				InetAddress ia = null;
				for (final Enumeration ips = iface.getInetAddresses(); ips.hasMoreElements();) {
					ia = (InetAddress) ips.nextElement();
					final String ip = ia.getHostAddress();
					if (!ip.equals("127.0.0.1") && (ip.indexOf(".") > -1)) {
						serverIp = ip;
					}
				}
			}
		} catch (final Exception e) {
			try {
				addr = InetAddress.getLocalHost();
				final String ipAddress = addr.getHostAddress();
				serverIp = ipAddress;
			} catch (final UnknownHostException e1) {
				logger.info("ip generation", e1);
			}
		}
		return serverIp;
	}

}
