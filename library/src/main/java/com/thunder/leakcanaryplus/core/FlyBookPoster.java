package com.thunder.leakcanaryplus.core;

import android.text.TextUtils;
import android.util.Log;

import com.thunder.leakcanaryplus.ssl.SSLSocketClient;
import com.thunder.leakcanaryplus.utils.WifiUtils;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 基于飞书的机器人群消息上报
 */
class FlyBookPoster extends BasePoster {

    @Override
    public void request(String leakString) {
        super.request(leakString);
        FlyBookInfo info = new FlyBookInfo();
        FlyBookInfo.ContentInfo contentInfo = new FlyBookInfo.ContentInfo();
        String ip = WifiUtils.getIPAddressV4(LeakCanaryManager.getInstance().getAppContext());
        Log.e(LeakCanaryManager.class.getSimpleName(), "ip : " + ip);
        contentInfo.setText(leakString, ip);
        info.setContent(contentInfo);
        String json = flyBookInfoToJson(info);
        Log.e(LeakCanaryManager.class.getSimpleName(), json);
        if (!TextUtils.isEmpty(json)) {
            OkHttpClient client = new OkHttpClient.Builder().sslSocketFactory(SSLSocketClient.getSSLSocketFactory()).build();
            RequestBody body = RequestBody.create(jsonType, json);
            Request request = new Request.Builder()
                    .url(FLY_BOOL_URL + LeakCanaryManager.getInstance().getAccessToken())
                    .addHeader("Content-Type", "application/json; charset=UTF-8")
                    .post(body)
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    Log.e(LeakCanaryManager.class.getSimpleName(), "onFailure IOException", e);
                }

                @Override
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    Log.e(LeakCanaryManager.class.getSimpleName(), "onResponse" + response.body().string());
                }
            });
        }
    }

    /**
     * Android 原生对象转Json，这样不管应用用 Gson/FastJson/Jackson 都无关紧要
     */
    private String flyBookInfoToJson(FlyBookInfo info) {
        JSONObject infoObject = new JSONObject();
        JSONObject contentObject = new JSONObject();
        try {
            infoObject.put("msg_type", info.getMsgType());
            contentObject.put("text", info.getContent().getText());
            infoObject.putOpt("content", contentObject);
            return infoObject.toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
