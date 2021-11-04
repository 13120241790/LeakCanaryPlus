package com.thunder.leakcanaryplus.core;

import org.jetbrains.annotations.NotNull;

import shark.HeapAnalysis;

/**
 * @author zhouxuming
 * @version 1.0
 * @see LeakCanaryManager#setOnHeapInterceptListener(OnHeapInterceptListener)
 * Heap 拦截器 : 如果 onHeapAnalyzed 返回 true 表示自己处理 , 返回 false 表示走默认实现
 */
public interface OnHeapInterceptListener {
    /**
     * @param heapAnalysisWrapper The hprof file that was analyzed.
     * @return onHeapAnalyzed 返回 true 表示自己处理 , 返回 false 表示走默认实现
     * @see HeapAnalysis
     */
    boolean onHeapAnalyzed(@NotNull HeapAnalysisWrapper heapAnalysisWrapper);
}
