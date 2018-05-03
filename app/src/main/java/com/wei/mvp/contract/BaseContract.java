package com.wei.mvp.contract;

/**
 * @author: WEI
 * @date: 2018/5/2
 */
public interface BaseContract
{
    interface BasePresenter {
        void start();
        void detach();
    }

    interface BaseView {
        void showProgressDlg(String msg);
        void closeProgressDlg();
    }

    interface BaseDataSource {

    }
}
