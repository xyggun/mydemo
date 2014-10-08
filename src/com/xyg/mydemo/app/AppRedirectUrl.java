package com.xyg.mydemo.app;

import android.content.Context;
import android.content.Intent;

import com.xyg.mydemo.ui.Demo1;
import com.xyg.mydemo.ui.Demo2;
import com.xyg.mydemo.ui.Demo3;

/**
 * ��ҳ����ת������ UrlTitle ��Ӧ��ҳ����ʾ��title UrlActivity ��Ӧд������ת����
 * */
public class AppRedirectUrl {
	private static Context context;
	public final static String[] UrlTitle = { "ʹ�� AsyncTask ������ͼƬ", "ʹ�� AsyncTask ������ͼƬ2" , "gif������ʾ"};
	public static Intent[] UrlActivity = null;

	public AppRedirectUrl(Context context) {
		AppRedirectUrl.context = context;
		AppRedirectUrl.UrlActivity = getIntents();
	}

	private Intent[] getIntents() {
		Intent[] UrlActivity = { 
				new Intent(context, Demo1.class), // ��ת��demo1
				new Intent(context, Demo2.class), // ��ת��demo2
				new Intent(context, Demo3.class), // ��ת��demo3
		};
		return UrlActivity;
	}
}
