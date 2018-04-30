package com.wei.mvp.contract;

public interface BaseView<T> {
//    void setPresenter(T presenter);
    void showProgressDlg(String msg);
    void closeProgressDlg();
}
