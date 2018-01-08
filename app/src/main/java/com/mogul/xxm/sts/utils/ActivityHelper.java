package com.mogul.xxm.sts.utils;

import android.app.Activity;

import java.util.Stack;

public class ActivityHelper {
    protected final String TAG = getClass().getSimpleName();
    private static ActivityHelper mInstance = null;
    private Stack<Activity> activityStack;//activity栈

    public static synchronized ActivityHelper getInstance() {
        if (mInstance == null) {
            mInstance = new ActivityHelper();
        }
        return mInstance;
    }

    /**
     * 把一个activity压入栈中
     * @param activity   Activity对象
     */
    public void pushOneActivity(Activity activity) {
        pushOneActivity(activity,false);
    }
    /**
     * 把一个activity压入栈中
     * @param activity   Activity对象
     * @param isClearOldActivity    是否清除历史Activity
     */
    public void pushOneActivity(Activity activity, boolean isClearOldActivity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        if(isClearOldActivity){
            finishAllActivity();
        }
        activityStack.add(activity);
        LogUtil.i(TAG, "pushOneActivity:" + activityStack.size());
    }
    /**
     * 获取栈顶的activity，先进后出原则
     * @return 栈顶Activity
     */
    public Activity getLastActivity() {
        return activityStack.lastElement();
    }

    /**
     * 移除一个activity
     * @param activity Activity对象
     */
    public void popOneActivity(Activity activity) {
        if (activityStack != null && activityStack.size() > 0) {
            if (activity != null) {
                activity.finish();
                activityStack.remove(activity);
                activity = null;
            }
        }
        LogUtil.i(TAG,"popOneActivity:"+activityStack.size());
    }

    /**
     * 退出所有activity
     */
    public void finishAllActivity() {
        if (activityStack != null) {
            while (activityStack.size() > 0) {
                Activity activity = getLastActivity();
                if (activity == null) break;
                popOneActivity(activity);
            }
        }
        LogUtil.i(TAG,"finishAllActivity:"+activityStack.size());
    }

    /**
     * 保留最后一个Activity,关闭其他Activity
     */
    public void keepLastActivity(){
        Activity lastActivity = getLastActivity();
        if(lastActivity!=null){
            activityStack.remove(lastActivity);
            finishAllActivity();
            pushOneActivity(lastActivity);
        }
    }
}

