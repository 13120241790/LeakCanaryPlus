package com.thunder.leakcanaryplus.utils;

public class ApiCheckUtils {
    public static boolean foundSDK(String className) {
        Class cls = null;
        try {
            cls = Class.forName(className);
        } catch (ClassNotFoundException var3) {
            var3.printStackTrace();
        }
        return cls != null;
    }
}
