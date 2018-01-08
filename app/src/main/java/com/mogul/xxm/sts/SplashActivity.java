package com.mogul.xxm.sts;

import com.mogul.xxm.sts.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected boolean isShowBacking() {
        return false;
    }

    @Override
    public void showBack() {
        super.showBack();
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void updateViews() {

    }
}
