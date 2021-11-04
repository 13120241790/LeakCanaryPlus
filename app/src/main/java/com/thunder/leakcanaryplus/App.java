package com.thunder.leakcanaryplus;

import android.app.Application;

import com.thunder.leakcanaryplus.core.HeapAnalysisWrapper;
import com.thunder.leakcanaryplus.core.LeakCanaryManager;
import com.thunder.leakcanaryplus.core.OnHeapInterceptListener;

import org.jetbrains.annotations.NotNull;

public class App extends Application {

    public static final String token1 = "73c3fa1a-7853-412b-ab0b-98311198fa48"; //飞书三人测试群

    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanaryManager.getInstance().init(this, token1);
        LeakCanaryManager.getInstance().setOnHeapInterceptListener(new OnHeapInterceptListener() {
            @Override
            public boolean onHeapAnalyzed(@NotNull HeapAnalysisWrapper heapAnalysisWrapper) {
                return false;
            }
        });
    }
}
