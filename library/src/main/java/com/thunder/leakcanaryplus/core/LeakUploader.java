package com.thunder.leakcanaryplus.core;

import org.jetbrains.annotations.NotNull;

import leakcanary.DefaultOnHeapAnalyzedListener;
import leakcanary.OnHeapAnalyzedListener;
import shark.HeapAnalysis;

class LeakUploader implements OnHeapAnalyzedListener {

    private final OnHeapAnalyzedListener listener = DefaultOnHeapAnalyzedListener.Companion.create();

    @Override
    public void onHeapAnalyzed(@NotNull HeapAnalysis heapAnalysis) {
        listener.onHeapAnalyzed(heapAnalysis);
        if (LeakCanaryManager.getInstance().getOnHeapInterceptListener() == null || !LeakCanaryManager.getInstance().getOnHeapInterceptListener().onHeapAnalyzed(new HeapAnalysisWrapper(heapAnalysis))) {
            new FlyBookPoster().request(heapAnalysis.toString());
        }
    }
}
