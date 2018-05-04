package com.wei.mvp.datasource.home;

import com.google.gson.Gson;
import com.wei.mvp.common.Global;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.network.HttpResultSubscriber;
import com.wei.mvp.network.OkHttpHelper;
import com.wei.mvp.network.RetrofitManager;
import com.wei.mvp.network.retrofitservice.BeautyPicsApiService;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class HomePageDataSourceImpl implements IHomePageDataSource
{
    private BeautyPicsApiService mPicsApiService;

    public HomePageDataSourceImpl()
    {
        mPicsApiService = RetrofitManager.getInstance().getPicsApiService();
    }

    @Override
    public void loadPics(int page, int pageSize, final GetPicsCallback getPicsCallback)
    {
        getPicsCallback.loading();
        // 下载图片，成功后调用onPicsLoaded。把结果回调回presenter层
        OkHttpHelper.getBuilder().url(Global.BEAUTY_URL + pageSize + "/" + page).build()
                .execute(new OkHttpHelper.OnResult() {
                    @Override
                    public void onFailure(String fail) {
                    }

                    @Override
                    public void onResponse(String result) {
                        BeautyPicRespJson beautyPicRespJson = new Gson().fromJson(result, BeautyPicRespJson.class);
                        if (!beautyPicRespJson.isError())
                        {
                            getPicsCallback.onPicsLoaded(beautyPicRespJson.getResults());
                        }
                    }
                });
    }

    @Override
    public void getPics(int page, int pageSize, final HttpResultSubscriber getPicsCallback)
    {
        mPicsApiService.getPics(pageSize, page)
                .subscribeOn(Schedulers.io())
                .map(beautyPicRespJson -> {
                    // 数据转换
                    if (!beautyPicRespJson.isError()) {
                        return beautyPicRespJson.getResults();
                    }
                    return null;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getPicsCallback);
    }
}
