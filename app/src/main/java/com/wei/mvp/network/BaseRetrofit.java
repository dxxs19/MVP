package com.wei.mvp.network;

import com.wei.mvp.common.Global;
import com.wei.mvp.util.LogUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okhttp3.logging.HttpLoggingInterceptor;
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
        builder.addInterceptor(httpLogInterceptor());
        return builder.build();
    }

    private Interceptor httpLogInterceptor()
    {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.e("okhttp : " , message);
            }
        });
        return loggingInterceptor;
    }

    protected abstract void initService();

    protected abstract String getBaseUrl();
}
