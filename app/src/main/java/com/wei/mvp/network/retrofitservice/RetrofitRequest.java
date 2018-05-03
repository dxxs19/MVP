package com.wei.mvp.network.retrofitservice;

import com.wei.mvp.datasource.model.BeautyPicRespJson;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author: WEI
 * @date: 2018/5/2
 */
public interface RetrofitRequest{

    @GET("{pageSize}/{pageIndex}")
    Call<BeautyPicRespJson> getPics(@Path("pageSize") int pageSize, @Path("pageIndex") int pageIndex);
}
