package com.liuxd.firstblood;

import android.app.Application;

import com.liuxd.firstblood.util.LogUtil;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Liuxd on 2016/11/19 13:20.
 * 做一些初始化的工作
 */

public class MyApp extends Application {
    private static MyApp INSTANCE;

    public static MyApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化LeakCanary内存泄漏检测
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        INSTANCE = this;
        //初始化Logger日志输出
        LogUtil.resetSetting();
        //初始化Bugly崩溃反馈,也可以在清单文件里配置
        CrashReport.initCrashReport(INSTANCE, "900059976", BuildConfig.BUGLY_ENABLE_DEBUG);
    }
}
