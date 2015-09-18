package com.xyggun.mydemo.app;

import android.app.Application;
import android.util.Log;

import com.baidu.mapapi.SDKInitializer;
import com.xyggun.mydemo.exception.CrashHandler;

import cn.jpush.android.api.JPushInterface;

/**
 *
 * demo接入了极光推送，一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class AppContext extends Application {
    private static final String JPushTAG = "JPush";
    private static AppContext instance;

    @Override
    public void onCreate() {
        Log.d(JPushTAG, "[ExampleApplication] onCreate");
        super.onCreate();


        instance = this;
        SDKInitializer.initialize(this);

        // 初始化错误处理
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(this);

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }

    public static AppContext getInstance() {
        return instance;
    }
}
