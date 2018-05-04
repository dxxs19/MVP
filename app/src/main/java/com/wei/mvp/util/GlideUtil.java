package com.wei.mvp.util;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.wei.mvp.R;
import com.wei.mvp.datasource.model.GlideApp;

public class GlideUtil {
    public static void showImage(Context context, ImageView imageView, String url)
    {
        GlideApp.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.drawable.leak_canary_icon)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .override(520, 750)
                .into(imageView);
    }
}
