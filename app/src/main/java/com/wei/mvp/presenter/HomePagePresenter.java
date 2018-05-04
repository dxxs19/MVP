package com.wei.mvp.presenter;

import com.wei.mvp.contract.home.HomePageContract;
import com.wei.mvp.datasource.home.HomePageDataSourceImpl;
import com.wei.mvp.datasource.home.IHomePageDataSource;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.network.HttpResultSubscriber;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.List;


public class HomePagePresenter implements HomePageContract.Presenter {
    private IHomePageDataSource mDataSource;
    // 防止内存泄露
    private SoftReference<HomePageContract.View> mView;

    public HomePagePresenter(HomePageContract.View view) {
        mDataSource = new HomePageDataSourceImpl();
        mView = new SoftReference<>(view);
    }

    @Override
    public void loadPics(int page, int pageSize) {
        mDataSource.getPics(page, pageSize, new HttpResultSubscriber<List<BeautyPicRespJson.BeautiesBean>>() {
            @Override
            public void onSuccess(List<BeautyPicRespJson.BeautiesBean> beautiesBeans)
            {
                if (beautiesBeans.size() > 0 && getView() != null)
                {
                    getView().showBeautyPics(beautiesBeans);
                }
            }
        });
    }

    private HomePageContract.View getView()
    {
        if (mView != null)
        {
            return mView.get();
        }
        return null;
    }

    @Override
    public void start() {

    }

    @Override
    public void detach() {
        if (mView != null)
        {
            mView.clear();
            mView = null;
        }
        if (mDataSource != null)
        {
            mDataSource = null;
        }
    }

}
