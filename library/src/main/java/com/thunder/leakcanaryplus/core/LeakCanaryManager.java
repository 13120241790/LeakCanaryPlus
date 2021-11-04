package com.thunder.leakcanaryplus.core;

import android.content.Context;
import android.util.Log;

import com.thunder.leakcanaryplus.utils.ApiCheckUtils;

import leakcanary.LeakCanary;

/**
 * 封装 LeakCanary 2.0+ 版本 API 提供 飞书内存泄漏群上报
 * {@link @https://square.github.io/leakcanary/api/leakcanary-android-core/leakcanary/}
 *
 * @author zhouxuming
 * @version 1.0
 */
public class LeakCanaryManager {

    private static LeakCanaryManager mInstance;
    private int retainedVisibleThreshold = 1;
    private String accessToken;
    private Context context;

    private LeakCanaryManager() {

    }

    public static LeakCanaryManager getInstance() {
        if (mInstance == null) {
            mInstance = new LeakCanaryManager();
        }
        return mInstance;
    }

    /**
     * @param context                  application context
     * @param retainedVisibleThreshold val retainedVisibleThreshold:Int
     *                                 When the app is visible, LeakCanary will wait for at least retainedVisibleThreshold
     *                                 retained instances before dumping the heap. Dumping the heap freezes the UI and can be
     *                                 frustrating for developers who are trying to work. This is especially frustrating as the
     *                                 Android Framework has a number of leaks that cannot easily be fixed.
     * @param accessToken              access to DingTalk post group token {@link @https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq}
     */
    public void init(Context context, int retainedVisibleThreshold, String accessToken) {
        if (context != null) {
            this.context = context.getApplicationContext();
        }
        this.accessToken = accessToken;
        if (ApiCheckUtils.foundSDK("leakcanary.LeakCanary")) {
            LeakCanary.setConfig(LeakCanary.getConfig().newBuilder().retainedVisibleThreshold(retainedVisibleThreshold).onHeapAnalyzedListener(new LeakUploader()).build());
        } else {
            Log.e(LeakCanaryManager.class.getSimpleName(), "Not found dependencies : leakcanary2");
        }
    }

    /**
     * @param context     application context
     * @param accessToken access to DingTalk post group token {@link @https://ding-doc.dingtalk.com/doc#/serverapi2/qf2nxq}
     */
    public void init(Context context, String accessToken) {
        init(context, retainedVisibleThreshold, accessToken);
    }

    private OnHeapInterceptListener mOnHeapInterceptListener;

    /**
     * Heap 拦截器 : 如果 onHeapAnalyzed 返回 true 表示自己处理 , 返回 false 表示走默认实现
     *
     * @param onHeapInterceptListener {@link OnHeapInterceptListener}
     */
    public void setOnHeapInterceptListener(OnHeapInterceptListener onHeapInterceptListener) {
        mOnHeapInterceptListener = onHeapInterceptListener;
    }

    OnHeapInterceptListener getOnHeapInterceptListener() {
        return mOnHeapInterceptListener;
    }

    String getAccessToken() {
        return accessToken;
    }

    public Context getAppContext() {
        return context;
    }
}
