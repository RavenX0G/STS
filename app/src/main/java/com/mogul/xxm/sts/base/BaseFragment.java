package com.mogul.xxm.sts.base;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mogul.xxm.sts.MyApplication;
import com.mogul.xxm.sts.R;
import com.mogul.xxm.sts.utils.StatusBarUtil;
import com.mogul.xxm.sts.widget.MStatusDialog;
import com.mogul.xxm.sts.widget.MToastConfig;

import butterknife.ButterKnife;


/**
 Created by wangyang on 2017/11/22 0001.
 */

public abstract class BaseFragment extends Fragment {
    private Handler mHandler = new Handler();
    Toolbar mToolbar;
    TextView mToolbarTitle;
    TextView mToolbarSubTitle;
    protected Context mContext;
    //缓存Fragment view
    private View mRootView;
    private boolean mIsMulti = false;


    protected MStatusDialog mMStatusDialog;
    protected MToastConfig toastConfig;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(attachLayoutRes(), null);
            ButterKnife.bind(this, mRootView);
            mToolbar = (Toolbar) mRootView.findViewById(R.id.toolbar);
            mToolbarTitle = (TextView) mRootView.findViewById(R.id.toolbar_title);
            mToolbarSubTitle = (TextView) mRootView.findViewById(R.id.toolbar_subtitle);

            mMStatusDialog = new MStatusDialog(getActivity());


            toastConfig = new MToastConfig.Builder()
                    .setGravity(MToastConfig.MToastGravity.CENTRE)
                    .setBackgroundStrokeColor(Color.WHITE)
                    .setBackgroundStrokeWidth(1)
                    .setBackgroundCornerRadius(10)
                    .build();


            if (mToolbar != null) {
                //将Toolbar显示到界面
                ((BaseActivity) getActivity()).setSupportActionBar(mToolbar);
            }
            if (mToolbarTitle != null) {
                mToolbarTitle.setText((getActivity()).getTitle());
                //设置默认的标题不显示
                ((BaseActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
            }

            //判断是否有Toolbar,并默认显示返回按钮
            if (null != mToolbar && ((BaseActivity) getActivity()).isShowBacking()) {
                ((BaseActivity) getActivity()).showBack();
            }

            initInjector();
            initViews();

        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && isVisible() && mRootView != null && !mIsMulti) {
            mIsMulti = true;
            updateViews();
        } else {
            super.setUserVisibleHint(isVisibleToUser);
        }
    }

    protected MyApplication getAppComponent() {
        return MyApplication.getInstance();
    }


    /**
     * 获取头部标题的TextView
     *
     * @return
     */
    public TextView getToolbarTitle() {
        return mToolbarTitle;
    }

    /**
     * 获取头部sub标题的TextView
     *
     * @return
     */
    public TextView getSubTitle() {
        return mToolbarSubTitle;
    }

    /**
     * 绑定布局文件
     *
     * @return 布局文件ID
     */
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


    protected void setStatusBar(int color) {
        StatusBarUtil.setColor(getActivity(), color);
    }



    protected void setStatusBar() {
        StatusBarUtil.setColor(getActivity(), getResources().getColor(R.color.appThemeColor));
    }

}
