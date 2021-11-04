package com.thunder.leakcanaryplus.utils;


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.LinkProperties;
import android.text.TextUtils;

import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 获取 IP 地址工具类
 */
public class WifiUtils {

    public static final String IP_SPLIT_REGEX_KEY = "==";
    private static final String IP_V4_ADDRESS_REGEX =
            "([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}";
    private static final Pattern IP_V4_ADDRESS_PATTERN = Pattern.compile(IP_V4_ADDRESS_REGEX);

    public static boolean matches(final String ipAddress) {
        return IP_V4_ADDRESS_PATTERN.matcher(ipAddress).matches();
    }

    /**
     * 获取所有的IP,包括ipv4和macIp
     */
    @SuppressLint("PrivateApi")
    @Nullable
    public static String getIPAddress(Context context) {
        if (context == null) {
            return "";
        }
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        try {
            final Class<ConnectivityManager> cmClass = ConnectivityManager.class;
            final Method methodGetActiveLinkProperties = cmClass.getDeclaredMethod("getActiveLinkProperties");
            final LinkProperties linkProperties = (LinkProperties) methodGetActiveLinkProperties.invoke(cm);
            if (linkProperties == null) {
                return null;
            }
            final Class<LinkProperties> linkPropertiesClass = LinkProperties.class;
            final Method methodGetAllAddresses = linkPropertiesClass.getDeclaredMethod("getAllAddresses");
            final List<InetAddress> inetAddresses = (List<InetAddress>) methodGetAllAddresses.invoke(linkProperties);
            if (inetAddresses != null) {
                final StringBuilder stringBuilder = new StringBuilder();
                for (InetAddress inetAddress : inetAddresses) {
                    if (inetAddress != null) {
                        if (!TextUtils.isEmpty(stringBuilder)) {
                            stringBuilder.append(IP_SPLIT_REGEX_KEY);
                        }
                        stringBuilder.append(inetAddress.getHostAddress());
                    }
                }
                return stringBuilder.toString();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取当前IPV4
     */
    @Nullable
    public static String getIPAddressV4(Context context) {
        final String ipAddress = getIPAddress(context);
        if (ipAddress != null) {
            final String[] ipArray = ipAddress.split(IP_SPLIT_REGEX_KEY);
            if (ipArray.length > 0) {
                for (String ip : ipArray) {
                    final String[] split = ip.split("\\.");
                    if (split.length == 4) {
                        return ip;
                    }
                }
            }
        }
        return "";
    }

}
