package com.thunder.leakcanaryplus.core;

import android.text.TextUtils;
import android.util.Log;

import com.thunder.leakcanaryplus.utils.ApiCheckUtils;

import okhttp3.MediaType;

abstract class BasePoster implements Poster {

    protected static final String DING_TALK_URL = "https://oapi.dingtalk.com/robot/send?access_token=";
    protected static final String FLY_BOOL_URL = "https://open.feishu.cn/open-apis/bot/v2/hook/";

    protected static final MediaType jsonType = MediaType.parse("application/json; charset=utf-8");

    @Override
    public void request(String leakString) {
        if (TextUtils.isEmpty(leakString)) {
            Log.e(LeakCanaryManager.class.getSimpleName(), "leakString is null");
            return;
        }
        if (!ApiCheckUtils.foundSDK("okhttp3.OkHttpClient")) {
            Log.e(LeakCanaryManager.class.getSimpleName(), "Not found dependencies : OkHttp3");
            return;
        }
    }
}
