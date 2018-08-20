package com.wei.mvp.ui.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.wei.mvp.R;
import com.wei.mvp.contract.home.HomePageContract;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.presenter.HomePagePresenter;
import com.wei.mvp.ui.base.BaseActivity;
import com.wei.mvp.ui.adapter.PicsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MainActivity extends BaseActivity implements HomePageContract.View
{
    private HomePageContract.Presenter mPresenter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private PicsAdapter picsAdapter;
    private List<BeautyPicRespJson.BeautiesBean> beautiesBeans = new ArrayList<>();
    private Unbinder unbinder;
    private int pageIndex = 1, pageSize = 20;
    @BindViews({R.id.tv_1, R.id.tv_2})
    public List<TextView> viewList;

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        unbinder = ButterKnife.bind(this);
        mPresenter = new HomePagePresenter(this);
        // 批量操作
        for (TextView view:viewList) {
            view.setCompoundDrawables(null, null, null, null);
//            view.setEnabled(false);
            view.setClickable(false);
        }
    }

    @OnClick({R.id.tv_1, R.id.tv_2})
    public void clickOpts(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_1:
                showMsg("tv_1");
                break;

            case R.id.tv_2:
                showMsg("tv_2");
                break;

                default:
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        picsAdapter = new PicsAdapter(this, beautiesBeans);
        recyclerView.setAdapter(picsAdapter);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        swipeRefreshLayout = findViewById(R.id.swipeRefresh);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageIndex ++;
                        mPresenter.loadPics(pageIndex, pageSize);
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1000);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
        mPresenter.loadPics(pageIndex, pageSize);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();

    @Override
    public void showBeautyPics(List<BeautyPicRespJson.BeautiesBean> beautyPics) {
        beautiesBeans = beautyPics;
        picsAdapter = new PicsAdapter(MainActivity.this, beautiesBeans);
        recyclerView.setAdapter(picsAdapter);
    }

    @Override
    protected void onDestroy() {
        mPresenter.detach();
        mPresenter = null;
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void showProgressDlg(String msg) {
        log(msg);
    }

    @Override
    public void closeProgressDlg() {
        log("关闭进度对话框!");
    }
}
