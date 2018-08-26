package com.wei.mvp.ui.home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wei.mvp.CusApplication;
import com.wei.mvp.R;
import com.wei.mvp.contract.home.HomePageContract;
import com.wei.mvp.datasource.greendao.CompanyDao;
import com.wei.mvp.datasource.greendao.DaoSession;
import com.wei.mvp.datasource.model.BeautyPicRespJson;
import com.wei.mvp.datasource.model.Company;
import com.wei.mvp.datasource.model.Employee;
import com.wei.mvp.presenter.HomePagePresenter;
import com.wei.mvp.ui.base.BaseActivity;
import com.wei.mvp.ui.adapter.PicsAdapter;
import com.wei.mvp.util.LogUtil;
import com.wei.mvp.view.widget.DropDownPopupWindow;

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
    private DaoSession mDaoSession;
    private CompanyDao mCompanyDao;

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
//            view.setClickable(false);
        }
        testGreenDao();
    }

    private void testGreenDao() {
        mDaoSession = ((CusApplication)getApplication()).getDaoSession();
        mCompanyDao = mDaoSession.getCompanyDao();
    }

    @OnClick({R.id.tv_1, R.id.tv_2})
    public void clickOpts(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_1:
                showMsg("tv_1");
                Company employee = new Company(null, "合立正通", "番禺区小谷围");
                mCompanyDao.insert(employee);
                break;

            case R.id.tv_2:
                showMsg("tv_2");
//                showPopupWin(view);
                Company company = queryTargetCompany("合立正通");
                if (company == null)
                {
                    showMsg("公司信息不存在！");
                    return;
                }
                LogUtil.e(TAG, company.toString());
//                String newCompanyName = "景联科技";
//                updateCompanyName(company, newCompanyName);
//                logNewCompanyInfos(newCompanyName);
//                deleteTargetCompany(newCompanyName);
                Employee employee1 = new Employee(null, "caixiangwei",  28, "男", company.getId());
                Employee employee2 = new Employee(null, "蔡祥伟",  29, "男", company.getId());
                Employee employee3 = new Employee(null, "刘亦菲",  30, "女", company.getId());
                saveEmplyees(employee1, employee2, employee3);
                logAllDatas();
                break;

                default:
        }
    }

    private void saveEmplyees(Employee... employees) {
        mDaoSession.getEmployeeDao().saveInTx(employees);
    }

    private void logAllDatas() {
        List<Company> companies = mCompanyDao.queryBuilder().list();
        for (Company company:companies) {
            List<Employee> employees = company.getEmployees();
            if (employees != null && employees.size() > 0)
            {
                for (Employee employee:employees) {
                    LogUtil.e(TAG, employee.toString());
                }
            }
        }
    }

    private void deleteTargetCompany(String newCompanyName) {
        Company company = queryTargetCompany(newCompanyName);
        if (company != null)
        {
            mCompanyDao.deleteByKey(company.getId());
        }
    }

    private void logNewCompanyInfos(String newCompanyName) {
        Company company = queryTargetCompany(newCompanyName);
        LogUtil.e(TAG, "newCompanyInfo : " + company.toString());
    }

    private void updateCompanyName(Company company, String newCompanyName) {
        company.setName(newCompanyName);
        mCompanyDao.update(company);
    }

    private Company queryTargetCompany(String companyName)
    {
        List<Company> companies = mCompanyDao.queryBuilder().where(CompanyDao.Properties.Name.eq(companyName)).build().list();
        if (companies != null && companies.size() > 0)
        {
            return companies.get(0);
        }
        return null;
    }

    private void showPopupWin(View view)
    {
        DropDownPopupWindow dropDownPopupWindow = new DropDownPopupWindow(this, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        dropDownPopupWindow.showAsDropDown(view,0, 10);
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
