package com.mogul.xxm.sts;

import android.app.Application;

/**
 * Time:1/9/18 21:36
 * Created by Curtain.
 */

public class MyApplication extends Application{

    private static MyApplication INSTANCE;

    public synchronized static MyApplication getInstance() {
        if (null == INSTANCE) {
            INSTANCE = new MyApplication();
        }
        return INSTANCE;
    }
}
