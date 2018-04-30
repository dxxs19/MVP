package com.wei.mvp.net;

import com.google.gson.Gson;
import com.wei.mvp.common.Global;
import com.wei.mvp.data.bean.BeautyPicRespJson;
import com.wei.mvp.util.LogUtil;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttpHelper
{
    private final String TAG = getClass().getSimpleName();
    private String mUrl;

    public OkHttpHelper(Builder builder) {
        mUrl = builder.url;
    }

    public static Builder getBuilder()
    {
        return new Builder();
    }

    public static class Builder
    {
        private String url;

        public Builder url(String url)
        {
            this.url = url;
            return this;
        }

        public OkHttpHelper build()
        {
            return new OkHttpHelper(this);
        }
    }

    public void execute(final OnResult onResult)
    {
        Request request = new Request.Builder().url(mUrl).build();
        Call call = new OkHttpClient().newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                onResult.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
//                LogUtil.e(TAG, "result = " + result);
                onResult.onResponse(result);
            }
        });
    }

    public interface OnResult
    {
        void onFailure(String fail);
        void onResponse(String response);
    }
}
