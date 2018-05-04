package com.wei.mvp.datasource.home;

import com.wei.mvp.contract.home.HomePageContract;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.network.HttpResultSubscriber;

import java.util.List;

public interface IHomePageDataSource extends HomePageContract.DataSource{

    void loadPics(int page, int pageSize, GetPicsCallback getPicsCallback);

    void getPics(int page, int pageSize, HttpResultSubscriber resultSubscriber);

    interface GetPicsCallback{
        void loading();
        void onPicsLoaded(List<BeautyPicRespJson.BeautiesBean> beautyPics);

    }
}
