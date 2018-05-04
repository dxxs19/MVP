package com.wei.mvp.network;

import com.wei.mvp.common.Global;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRetrofit
{
    public static final int DEFAULT_TIME_OUT = 30;
    protected Retrofit mRetrofit;

    public void init()
    {
        initRetrofit();
        initService();
    }

    private void initRetrofit()
    {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(Global.BEAUTY_URL)
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private OkHttpClient getOkHttpClient()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.readTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        builder.writeTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        return builder.build();
    }

    protected abstract void initService();

}
