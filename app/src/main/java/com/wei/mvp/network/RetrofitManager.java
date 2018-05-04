package com.wei.mvp.network;

import com.wei.mvp.common.Global;
import com.wei.mvp.network.retrofitservice.BeautyPicsApiService;

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

    @Override
    protected void initService()
    {
        mPicsApiService = createService(BeautyPicsApiService.class);
    }

    @Override
    protected String getBaseUrl() {
        return mBaseUrl;
    }

    public void setBaseUrl(String url)
    {
        mBaseUrl = url;
    }

    private <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }

    public BeautyPicsApiService getPicsApiService() {
        return mPicsApiService;
    }
}
