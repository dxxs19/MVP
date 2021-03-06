package com.wei.mvp.ui.base;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.wei.mvp.R;
import com.wei.mvp.util.LogUtil;

public abstract class BaseActivity extends AppCompatActivity
{
    protected final String TAG = getClass().getSimpleName();
    private LinearLayout mContainerLayout;
    private CoordinatorLayout mLayout;
    protected static Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContainer();
        initView();
        initData();
        initListener();
    }

    private void initContainer() {
        mLayout = (CoordinatorLayout) View.inflate(this, R.layout.activity_base, null);
        mContainerLayout = mLayout.findViewById(R.id.ll_container);
    }

    @Override
    public void setContentView(int layoutResID)
    {
        View view = View.inflate(this, layoutResID, null);
        mContainerLayout.addView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        super.setContentView(mLayout);
    }

    protected void log(String msg)
    {
        LogUtil.e(TAG, msg);
    }

    protected void showMsg(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected abstract void initView();
    protected abstract void initData();
    protected abstract void initListener();

}
