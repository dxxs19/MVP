package com.wei.mvp;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import com.wei.mvp.datasource.greendao.DaoMaster;
import com.wei.mvp.datasource.greendao.DaoSession;
import com.wei.mvp.network.RetrofitManager;

import org.greenrobot.greendao.database.Database;

public class CusApplication extends Application {
    private final String TAG = getClass().getSimpleName();
    private RefWatcher refWatcher;
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
//        setupLeakCanary();
        RetrofitManager.getInstance().init();
        initGreenDao();
    }

    private void initGreenDao() {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, "company-db");
        Database database = devOpenHelper.getWritableDb();
        daoSession = new DaoMaster(database).newSession();
    }

    public DaoSession getDaoSession()
    {
        return daoSession;
    }

    private void setupLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            // 这个过程专门用于堆分析，不应该在此初始化你的app。如果堆分析的进程正在运行，则什么也不做，直接return。
            // 否则执行后面的install（）来启动进程。这样我们就可以使用LeakCanary了，如果检测到某个Activity 有内存泄露，LeakCanary 就会给出提示。
            refWatcher = RefWatcher.DISABLED;
            return;
        }
        enabledStrictMode();
        refWatcher = LeakCanary.install(this);
    }

    private void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                .detectAll()
                .penaltyLog()
                .penaltyDeath()
                .build());
    }

    /**
     * 其它地方可通过此方法获取到refWaatcher
     * @param context
     * @return
     */
    public static RefWatcher getRefWatcher(Context context)
    {
        return ((CusApplication)context.getApplicationContext()).refWatcher;
    }
}
