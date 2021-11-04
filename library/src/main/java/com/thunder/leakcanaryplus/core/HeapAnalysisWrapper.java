package com.thunder.leakcanaryplus.core;

import shark.HeapAnalysis;

/**
 * shark 的 HeapAnalysis 包装，为了做一层接口隔离
 */
public class HeapAnalysisWrapper {
    private HeapAnalysis heapAnalysis;

    public HeapAnalysisWrapper(HeapAnalysis heapAnalysis) {
        this.heapAnalysis = heapAnalysis;
    }

    public HeapAnalysis getHeapAnalysis() {
        return heapAnalysis;
    }

    public void setHeapAnalysis(HeapAnalysis heapAnalysis) {
        this.heapAnalysis = heapAnalysis;
    }
}
