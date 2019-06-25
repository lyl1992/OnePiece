package com.hebiwen.luffy;

import android.app.Application;

public class BaseApplication extends Application {

    /**
     * 单例模式application
     */
    private static BaseApplication application = null;

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
    }

    // 单例模式中获取唯一的BaseApplication实例
    public static BaseApplication getInstance() {
        return application;
    }
}
