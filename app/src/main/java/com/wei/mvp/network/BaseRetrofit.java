package com.wei.mvp.network;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public abstract class BaseRetrofit
{
    public static final int DEFAULT_TIME_OUT = 30;
    protected Retrofit mRetrofit;

    protected void init()
    {
        initRetrofit();
        initService();
    }

    private void initRetrofit()
    {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(getBaseUrl())
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

    public Retrofit getRetrofit()
    {
        return mRetrofit;
    }

    protected abstract void initService();

    protected abstract String getBaseUrl();
}
