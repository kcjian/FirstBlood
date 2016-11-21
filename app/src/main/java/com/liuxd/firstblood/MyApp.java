package com.liuxd.firstblood;

import android.app.Application;

/**
 * Created by Liuxd on 2016/11/19 13:20.
 */

public class MyApp extends Application {
    private static MyApp INSTANCE;

    public static MyApp getInstance() {
        return INSTANCE;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }
}
