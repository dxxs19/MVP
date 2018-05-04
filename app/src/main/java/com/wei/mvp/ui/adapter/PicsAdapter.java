package com.wei.mvp.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wei.mvp.R;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.util.GlideUtil;

import java.util.List;

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.CusViewHolder>
{
    private Context mContext;
    private List<BeautyPicRespJson.BeautiesBean> mBeautyPics;

    public PicsAdapter(Context context, List<BeautyPicRespJson.BeautiesBean> beautyPics)
    {
        mContext = context;
        mBeautyPics = beautyPics;
    }

    @NonNull
    @Override
    public CusViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CusViewHolder(View.inflate(mContext, R.layout.item_pic, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CusViewHolder holder, int position) {
        BeautyPicRespJson.BeautiesBean beautiesBean = getItem(position);
        String url = beautiesBean.getUrl();
        if (!TextUtils.isEmpty(url))
        {
            GlideUtil.showImage(mContext, holder.showPicImgView, url);
        }
    }

    @Override
    public int getItemCount() {
        return mBeautyPics.size();
    }

    private BeautyPicRespJson.BeautiesBean getItem(int position)
    {
        return mBeautyPics.get(position);
    }

    public static class CusViewHolder extends RecyclerView.ViewHolder
    {
        ImageView showPicImgView;

        public CusViewHolder(View itemView) {
            super(itemView);
            showPicImgView = itemView.findViewById(R.id.imgView_showPic);
        }
    }

}
