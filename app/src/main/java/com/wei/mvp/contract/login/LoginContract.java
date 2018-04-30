package com.wei.mvp.contract.login;

import com.wei.mvp.contract.BasePresenter;
import com.wei.mvp.contract.BaseView;

public interface LoginContract {
    interface View extends BaseView<Presenter>
    {

    }

    interface Presenter extends BasePresenter
    {

    }
}
