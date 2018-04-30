package com.wei.mvp.contract.home;

import com.wei.mvp.contract.BasePresenter;
import com.wei.mvp.contract.BaseView;
import com.wei.mvp.data.bean.BeautyPicRespJson;

import java.util.List;

public interface HomePageContract {
    interface View extends BaseView<Presenter>
    {
        void showBeautyPics(List<BeautyPicRespJson.BeautiesBean> beautyPics);
    }

    interface Presenter extends BasePresenter
    {
        void loadPics(int page, int pageSize);
    }
}
