package com.xyggun.mydemo.app;

import android.app.Application;
import android.util.Log;
import cn.jpush.android.api.JPushInterface;

/**
 *
 * demo接入了极光推送，一般建议在自定义 Application 类里初始化。也可以在主 Activity 里。
 */
public class AppContext extends Application {
    private static final String JPushTAG = "JPush";

    @Override
    public void onCreate() {
        Log.d(JPushTAG, "[ExampleApplication] onCreate");
        super.onCreate();

        JPushInterface.setDebugMode(true); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush
    }
}
