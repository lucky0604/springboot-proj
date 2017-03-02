package com.lucky.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by lucky on 3/1/17.
 */
public class PublicUtil {
    public static String getProjectPath() {
        String currentPath = "";
        currentPath = System.getProperty("user.dir") + "/";
        return currentPath;
    }

    /**
     * 获取本机IP
     */
    public static String getIp() {
        String ip = "";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ip = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return ip;
    }

    public static boolean isJsonRequest(HttpServletRequest request) {
        return null != request && request.getMethod().contains("post") && request.getContentType().contains("application/json");
    }
}
