package com.wei.mvp.presenter;

import com.wei.mvp.contract.home.HomePageContract;
import com.wei.mvp.datasource.home.HomePageDataSourceImpl;
import com.wei.mvp.datasource.home.IHomePageDataSource;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.network.HttpResultSubscriber;

import java.util.List;


public class HomePagePresenter implements HomePageContract.Presenter {
    private IHomePageDataSource mDataSource;
    private HomePageContract.View mHomePage;

    public HomePagePresenter(HomePageContract.View view) {
        mDataSource = new HomePageDataSourceImpl();
        mHomePage = view;
    }

    @Override
    public void loadPics(int page, int pageSize) {
        mDataSource.getPics(page, pageSize, new HttpResultSubscriber<List<BeautyPicRespJson.BeautiesBean>>() {
            @Override
            public void onSuccess(List<BeautyPicRespJson.BeautiesBean> beautiesBeans) {
                mHomePage.showBeautyPics(beautiesBeans);
            }
        });
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {

    }

}
