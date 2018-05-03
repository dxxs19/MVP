package com.wei.mvp.network;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: WEI
 * @date: 2018/5/3
 */
public class RetrofitManager
{
    private static volatile RetrofitManager sRetrofitManager;

    public static RetrofitManager getInstance()
    {
        if (sRetrofitManager == null)
        {
            synchronized (RetrofitManager.class)
            {
                if (sRetrofitManager == null)
                {
                    sRetrofitManager = new RetrofitManager();
                }
            }
        }
        return sRetrofitManager;
    }

    public Retrofit getRetrofit(String baseUrl)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit;
    }
}
