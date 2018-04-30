package com.wei.mvp.presenter;

import com.wei.mvp.contract.home.HomePageContract;
import com.wei.mvp.data.bean.BeautyPicRespJson;
import com.wei.mvp.data.source.HomePageDataSourceImpl;
import com.wei.mvp.data.source.IHomePageDataSource;

import java.util.List;

public class HomePagePresenter implements HomePageContract.Presenter
{
    private IHomePageDataSource mDataSource;
    private HomePageContract.View mHomePage;

    public HomePagePresenter(HomePageContract.View view)
    {
        mDataSource = new HomePageDataSourceImpl();
        mHomePage = view;
    }

    @Override
    public void loadPics(int page, int pageSize) {
        mDataSource.loadPics(page, pageSize, new IHomePageDataSource.GetPicsCallback() {
            @Override
            public void loading() {
                mHomePage.showProgressDlg("图片加载中,请稍候...");
            }

            @Override
            public void onPicsLoaded(List<BeautyPicRespJson.BeautiesBean> beautyPics) {
                if (beautyPics != null && beautyPics.size() > 0)
                {
                    mHomePage.showBeautyPics(beautyPics);
                }
            }
        });

    }

    @Override
    public void start() {

    }

}
