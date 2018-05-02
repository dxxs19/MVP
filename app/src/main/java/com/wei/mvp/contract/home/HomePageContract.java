package com.wei.mvp.contract.home;

import com.wei.mvp.contract.BaseContract;
import com.wei.mvp.datasource.model.BeautyPicRespJson;

import java.util.List;

public interface HomePageContract
{
    interface View extends BaseContract.BaseView
    {
        void showBeautyPics(List<BeautyPicRespJson.BeautiesBean> beautyPics);
    }

    interface Presenter extends BaseContract.BasePresenter
    {
        void loadPics(int page, int pageSize);
    }

    interface DataSource extends BaseContract.BaseDataSource
    {

    }

}
