package com.mogul.xxm.sts;

import android.app.Application;
import android.content.Context;

import com.mogul.xxm.sts.constant.Constant;

import cn.bmob.v3.Bmob;

/**
 * Time:1/9/18 21:36
 * Created by Curtain.
 */

public class MyApplication extends Application{

    private static MyApplication INSTANCE;
    private Context appContext;

    public synchronized static MyApplication getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new MyApplication();
        }
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
        //第一：默认初始化
        Bmob.initialize(this, Constant.BMOB_APP_ID);
    }
}
