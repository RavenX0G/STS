package com.mogul.xxm.sts;

import android.content.Intent;
import android.os.Handler;

import com.mogul.xxm.sts.base.BaseActivity;
import com.mogul.xxm.sts.ui.MainActivity;

public class SplashActivity extends BaseActivity {

    private Handler mHandler;
    private Runnable mRunnable;

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
        mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                intoMainActivity();
            }
        };
        mHandler.postDelayed(mRunnable, 2000);
    }

    private void intoMainActivity(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
        finish();
    }

    @Override
    protected void updateViews() {

    }
}
