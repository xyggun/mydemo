package com.xyg.mydemo.app;

import android.content.Context;
import android.content.Intent;

import com.xyg.mydemo.ui.Demo1;
import com.xyg.mydemo.ui.Demo2;
import com.xyg.mydemo.ui.Demo3;

/**
 * 简单页面跳转控制类 UrlTitle 对应于页上显示的title UrlActivity 对应写死的跳转链接
 * */
public class AppRedirectUrl {
	private static Context context;
	public final static String[] UrlTitle = { "使用 AsyncTask 来下载图片", "使用 AsyncTask 来下载图片2" , "gif动画显示"};
	public static Intent[] UrlActivity = null;

	public AppRedirectUrl(Context context) {
		AppRedirectUrl.context = context;
		AppRedirectUrl.UrlActivity = getIntents();
	}

	private Intent[] getIntents() {
		Intent[] UrlActivity = { 
				new Intent(context, Demo1.class), // 跳转到demo1
				new Intent(context, Demo2.class), // 跳转到demo2
				new Intent(context, Demo3.class), // 跳转到demo3
		};
		return UrlActivity;
	}
}
