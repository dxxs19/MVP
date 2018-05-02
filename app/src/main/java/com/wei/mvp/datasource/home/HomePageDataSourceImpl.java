package com.wei.mvp.datasource.home;

import com.google.gson.Gson;
import com.wei.mvp.common.Global;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.network.OkHttpHelper;

public class HomePageDataSourceImpl implements IHomePageDataSource
{
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
}
