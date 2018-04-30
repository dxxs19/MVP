package com.wei.mvp.data.source;

import com.wei.mvp.data.bean.BeautyPicRespJson;

import java.util.List;

public interface IHomePageDataSource {

    void loadPics(int page, int pageSize, GetPicsCallback getPicsCallback);

    interface GetPicsCallback{
        void loading();
        void onPicsLoaded(List<BeautyPicRespJson.BeautiesBean> beautyPics);
    }

}
