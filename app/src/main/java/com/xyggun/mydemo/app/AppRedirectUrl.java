package com.xyggun.mydemo.app;

import android.content.Context;
import android.content.Intent;

import com.xyggun.mydemo.baidumap.BaiduMapDemoActivity;
import com.xyggun.mydemo.jpush.JPushMainActivity;
import com.xyggun.mydemo.pullzoomdemo.recyclerview.PullToZoomRecyclerActivity;
import com.xyggun.mydemo.ui.Demo1Activity;
import com.xyggun.mydemo.ui.Demo2Activity;
import com.xyggun.mydemo.ui.Demo3Activity;
import com.xyggun.mydemo.ui.Demo4Activity;
import com.xyggun.mydemo.ui.LifeCycleActivity;

/**
 * 简单页面跳转控制类 UrlTitle 对应于页上显示的title UrlActivity 对应写死的跳转链接
 */
public class AppRedirectUrl {
    private static Context context;
    public final static String[] UrlTitle =
            {"使用 AsyncTask 来下载图片", "使用 AsyncTask 来下载图片2", "gif动画显示", "瀑布流", "极光推送demo","百度地图demo"
            ,"PullToZoomRecyclerActivity DEMO","activity 生命周期"};
    public static Intent[] UrlActivity = null;

    public AppRedirectUrl(Context context) {
        AppRedirectUrl.context = context;
        AppRedirectUrl.UrlActivity = getIntents();
    }

    private Intent[] getIntents() {
        Intent[] UrlActivity = {
                new Intent(context, Demo1Activity.class), // 跳转到demo1
                new Intent(context, Demo2Activity.class), // 跳转到demo2
                new Intent(context, Demo3Activity.class), // 跳转到demo3
                new Intent(context, Demo4Activity.class), // 跳转到demo4
                new Intent(context, JPushMainActivity.class), // 跳转到极光推送页面
                new Intent(context, BaiduMapDemoActivity.class), // 跳转到百度地图demo
                new Intent(context, PullToZoomRecyclerActivity.class), // 跳转到下拉放大页面
                new Intent(context, LifeCycleActivity.class), // 跳转到生命周期页面
        };
        return UrlActivity;
    }
}
