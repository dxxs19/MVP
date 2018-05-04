package com.wei.mvp.network;

import com.wei.mvp.network.retrofitservice.BeautyPicsApiService;

/**
 * @author: WEI
 * @date: 2018/5/3
 */
public class RetrofitManager extends BaseRetrofit
{
    private BeautyPicsApiService mPicsApiService;

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

    private <T> T createService(Class<T> service) {
        return mRetrofit.create(service);
    }

    public BeautyPicsApiService getPicsApiService() {
        return mPicsApiService;
    }
}
