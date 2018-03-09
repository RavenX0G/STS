package com.mogul.xxm.sts.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.mogul.xxm.sts.BmobBean.SiteBean;
import com.mogul.xxm.sts.R;
import com.mogul.xxm.sts.base.BaseActivity;
import com.mogul.xxm.sts.widget.MToast;

import butterknife.BindView;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class MainActivity extends BaseActivity implements TabHost.OnTabChangeListener{

    @BindView(R.id.home_container)
    FrameLayout homeContainer;
    @BindView(android.R.id.tabcontent)
    FrameLayout tabcontent;
    @BindView(android.R.id.tabhost)
    FragmentTabHost tabhost;

    private Fragment[] mFragments;
    private FragmentTabHost mTabHost;
    //    当前选中的views的下标
    private int currentIndex = 0;
    private Context mContext;
    @Override
    protected int attachLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initInjector() {

    }

    @Override
    protected void initViews() {
        mContext = this;
        mFragments = MainGenerator.getFragments();
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        // 关联TabHost
        mTabHost.setup(this, getSupportFragmentManager(), R.id.home_container);
        //注意，监听要设置在添加Tab之前
        mTabHost.setOnTabChangedListener(this);

        //添加Tab
        for (int i = 0; i < 3; i++) {
            //生成TabSpec
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(MainGenerator.mTabTitle[i]).setIndicator(MainGenerator.getTabView(this, i));
            // 添加Tab 到TabHost，并绑定Fragment
            Bundle bundle = new Bundle();
//            bundle.putString("from", "FragmentTabHost Tab");
            mTabHost.addTab(tabSpec, mFragments[i].getClass(), bundle);
        }


        //去掉Tab 之间的分割线
        mTabHost.getTabWidget().setDividerDrawable(null);
        mTabHost.setCurrentTab(0);
    }

    @Override
    protected void updateViews() {

    }

    @Override
    public void onTabChanged(String s) {
        updateTabState();
    }


    /**
     * 更新Tab 的状态
     */
    private void updateTabState() {
        TabWidget tabWidget = mTabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getTabCount(); i++) {
            View view = tabWidget.getChildTabViewAt(i);
            ImageView tabIcon = (ImageView) view.findViewById(R.id.tab_image);
            TextView tabText = (TextView) view.findViewById(R.id.tab_title);
            if (i == mTabHost.getCurrentTab()) {
                currentIndex = i;
                tabIcon.setImageResource(MainGenerator.mTabResPressed[i]);
                tabText.setTextColor(getResources().getColor(R.color.colorMajorText));
            } else {
                tabIcon.setImageResource(MainGenerator.mTabRes[i]);
                tabText.setTextColor(getResources().getColor(R.color.colorUnSelectedText));
            }
        }
    }
}
