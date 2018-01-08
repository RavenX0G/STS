package com.mogul.xxm.sts.base;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.mogul.xxm.sts.R;
import com.mogul.xxm.sts.utils.ActivityHelper;
import com.mogul.xxm.sts.utils.StatusBarUtil;
import com.mogul.xxm.sts.widget.MStatusDialog;
import com.mogul.xxm.sts.widget.MToastConfig;

import butterknife.ButterKnife;

/**
 * Time:1/8/18 22:01
 * Created by Curtain.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected final String TAG = this.getClass().getSimpleName();
    Toolbar mToolbar;
    TextView mToolbarTitle;
    TextView mToolbarSubTitle;
    protected MStatusDialog mMStatusDialog;
    protected MToastConfig toastConfig;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(attachLayoutRes());
        ButterKnife.bind(this);
        setStatusBar();
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        mToolbarSubTitle = (TextView) findViewById(R.id.toolbar_subtitle);

        mMStatusDialog = new MStatusDialog(this);

        toastConfig = new MToastConfig.Builder()
                .setGravity(MToastConfig.MToastGravity.CENTRE)
                .setBackgroundStrokeColor(Color.WHITE)
                .setBackgroundStrokeWidth(1)
                .setBackgroundCornerRadius(10)
                .build();

        ActivityHelper.getInstance().pushOneActivity(this);
        if (mToolbar != null) {
            //将Toolbar显示到界面
            setSupportActionBar(mToolbar);
        }
        if (mToolbarTitle != null) {
            mToolbarTitle.setText(getTitle());
            //设置默认的标题不显示
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        //判断是否有Toolbar,并默认显示返回按钮
        if (null != mToolbar && isShowBacking()) {
            showBack();
        }

        initInjector();
        initViews();
        updateViews();
    }

    /**
     * 是否显示后退按钮,默认显示,可在子类重写该方法.
     *
     * @return
     */
    protected boolean isShowBacking() {
        return true;
    }

    /**
     * 版本号小于21的后退按钮图片
     */
    public void showBack() {
        //setNavigationIcon必须在setSupportActionBar(toolbar);方法后面加入
        if (mToolbar != null) {
            mToolbar.setNavigationIcon(R.mipmap.ic_back);
            mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
    @LayoutRes
    protected abstract int attachLayoutRes();

    /**
     * Dagger 注入
     */
    protected abstract void initInjector();

    /**
     * 初始化视图控件
     */
    protected abstract void initViews();

    /**
     * 更新视图控件
     */
    protected abstract void updateViews();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityHelper.getInstance().popOneActivity(this);
    }

    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorTitleBg));
    }


    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config=new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config,res.getDisplayMetrics());
        return res;
    }
}
