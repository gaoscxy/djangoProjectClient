package com.gaos.book.common;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;

import com.gaos.book.utils.SpUtil;
import com.tencent.bugly.crashreport.CrashReport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.Stack;

import io.reactivex.android.BuildConfig;

/**
 * Created by gaos on 2017/8/2.
 */

public class MyApplication extends Application {

    public static int allTextSizeType;
    public static Stack<Activity> store;
    private static Context mContext;
    private static MyApplication instance;

    public static Context getContext() {
        return mContext;
    }

    public static MyApplication getInstance() {
        if (instance == null) {
            synchronized (MyApplication.class) {
                if (instance == null) {
                    instance = new MyApplication();
                }
            }
        }
        return instance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mContext = getApplicationContext();
        initBugly();// bugly初始化及相关设置
        store = new Stack<>();
        registerActivityLifecycleCallbacks(new SwitchBackgroundCallbacks());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy(builder.build());
        }
        SpUtil.init(this);
    }

    /**
     * Bugly初始化及其配置
     */
    private void initBugly() {
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(mContext);
        String processName = getProcessName(android.os.Process.myPid());// 获取当前进程名
        // 设置是否为上报进程
        strategy.setUploadProcess(processName == null || processName.equals(mContext.getPackageName()));
        strategy.setAppVersion(BuildConfig.VERSION_NAME);//App的版本
        strategy.setAppPackageName(BuildConfig.APPLICATION_ID);//App的包名
        strategy.setAppChannel(BuildConfig.FLAVOR);
//        CrashReport.setIsDevelopmentDevice(mContext, BuildConfig.DEBUG);//设置开发设备

        /**
         * 第三个参数为SDK调试模式开关，调试模式的行为特性如下：
         *
         * 输出详细的Bugly SDK的Log；
         * 每一条Crash都会被立即上报；
         * 自定义日志将会在Logcat中输出。
         * 建议在测试阶段建议设置成true，发布时设置为false。
         */
        CrashReport.initCrashReport(mContext, GlobalConstant.BUGLY_APPID, !BuildConfig.DEBUG);//bugly初始化
    }

    /**
     * 栈中销毁并移除所有Act对象
     */
    public void removeAllActivity() {
        if (null != store && store.size() > 0) {
            //创建临时集合对象
            Stack<Activity> activityTemp = new Stack<>();
            for (Activity activity : store) {
                if (null != activity) {
                    //添加到临时集合中
                    activityTemp.add(activity);
                    //结束Activity
                    activity.finish();
                }
            }
            store.removeAll(activityTemp);
        }
        System.gc();
        System.exit(0);
    }

    public Activity getCurActivity() {
        try {
            return store.lastElement();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public Stack<Activity> getStore() {
        return store;
    }


    /**
     * 获取当前的Activity
     */

    private class SwitchBackgroundCallbacks implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(Activity activity, Bundle bundle) {
            store.add(activity);
        }

        @Override
        public void onActivityStarted(Activity activity) {

        }

        @Override
        public void onActivityResumed(Activity activity) {

        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {

        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            store.remove(activity);
        }
    }

    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private static String getProcessName(int pid) {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"));
            String processName = reader.readLine();
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim();
            }
            return processName;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        return null;
    }

}