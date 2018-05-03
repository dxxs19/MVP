package com.wei.mvp.network;

import com.wei.mvp.common.Global;
import com.wei.mvp.network.retrofitservice.BeautyPicsApiService;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author: WEI
 * @date: 2018/5/3
 */
public class RetrofitManager extends BaseRetrofit
{
    private BeautyPicsApiService mPicsApiService;
    private String mBaseUrl = Global.BEAUTY_URL;

    public static RetrofitManager getInstance()
    {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder
    {
        private static final RetrofitManager INSTANCE = new RetrofitManager();
    }

    private RetrofitManager()
    {
        init();
    }

    @Override
    protected void initService()
    {
        mPicsApiService = createService(BeautyPicsApiService.class);
    }

    private <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }

    @Override
    protected String getBaseUrl() {
        return mBaseUrl;
    }

    public void setBaseUrl(String url)
    {
        mBaseUrl = url;
    }

    public BeautyPicsApiService getPicsApiService() {
        return mPicsApiService;
    }
}
