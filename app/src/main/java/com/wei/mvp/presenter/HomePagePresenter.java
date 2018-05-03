package com.wei.mvp.presenter;

import android.util.Log;

import com.wei.mvp.common.Global;
import com.wei.mvp.contract.home.HomePageContract;
import com.wei.mvp.datasource.home.HomePageDataSourceImpl;
import com.wei.mvp.datasource.home.IHomePageDataSource;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.network.HttpResultSubscriber;
import com.wei.mvp.network.RetrofitManager;
import com.wei.mvp.network.retrofitservice.BeautyPicsApiService;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class HomePagePresenter implements HomePageContract.Presenter {
    private final String TAG = getClass().getSimpleName();
    private IHomePageDataSource mDataSource;
    private HomePageContract.View mHomePage;

    public HomePagePresenter(HomePageContract.View view) {
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
                if (beautyPics != null && beautyPics.size() > 0) {
                    mHomePage.showBeautyPics(beautyPics);
                }
            }
        });

    }

    @Override
    public void start() {
        retrofitRequest();
    }

    @Override
    public void detach() {

    }

    private void retrofitRequest() {
        RetrofitManager retrofitManager = RetrofitManager.getInstance();
        Retrofit retrofit = retrofitManager.getRetrofit(Global.BEAUTY_URL);
        BeautyPicsApiService retrofitRequest = retrofit.create(BeautyPicsApiService.class);
        Observable<BeautyPicRespJson> observable = retrofitRequest.getPics(20, 2);
        observable.subscribeOn(Schedulers.io())
                .map(new Function<BeautyPicRespJson, List<BeautyPicRespJson.BeautiesBean>>() {
                    @Override
                    public List<BeautyPicRespJson.BeautiesBean> apply(BeautyPicRespJson beautyPicRespJson) {
                        // 数据转换
                        if (!beautyPicRespJson.isError()) {
                            return beautyPicRespJson.getResults();
                        }
                        return null;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpResultSubscriber<List<BeautyPicRespJson.BeautiesBean>>() {
                    @Override
                    public void onSuccess(List<BeautyPicRespJson.BeautiesBean> beautiesBeans) {
                        mHomePage.showBeautyPics(beautiesBeans);
                    }
                });
    }

}
