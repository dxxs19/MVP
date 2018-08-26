package com.wei.mvp.view.widget;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.wei.mvp.R;

public class DropDownPopupWindow extends PopupWindow
{
    public DropDownPopupWindow(Context context, int width, int height)
    {
        View converView = LayoutInflater.from(context).inflate(R.layout.popup, null);
        setContentView(converView);
        setWidth(width);
        setHeight(height);
        setFocusable(true);
        setOutsideTouchable(true);
        setBackgroundDrawable(new BitmapDrawable());
//        setBackgroundDrawable(new ColorDrawable( context.getResources().getColor(R.color.trans)));
    }

    /**
     * 适配7.0-、7.0、7.1及8.0版本手机
     * @param anchor
     * @param xoff
     * @param yoff
     */
    @Override
    public void showAsDropDown(View anchor, int xoff, int yoff)
    {
        if (Build.VERSION.SDK_INT >= 24)
        {
            Rect rect = new Rect();
            anchor.getGlobalVisibleRect(rect);
            int height = anchor.getResources().getDisplayMetrics().heightPixels - rect.bottom - 5;
            setHeight(height);
        }
        super.showAsDropDown(anchor, xoff, yoff);
    }
}
