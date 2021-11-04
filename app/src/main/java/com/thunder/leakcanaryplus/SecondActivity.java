package com.thunder.leakcanaryplus;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

/**
 * 内存泄漏模拟，触发 Leakcanary
 */
public class SecondActivity extends Activity {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

            }
        }, 60000);
    }
}
