package com.xyggun.baselibrary.inject.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;

import com.xyggun.baselibrary.inject.InjectorFactory;


public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		InjectorFactory.injectBeforeSetContentView(this);// 放入试图前对注解解析
	}

	protected void onStart() {
		super.onStart();
		InjectorFactory.injectOnStart(this);// 在执行onstart时 对注解解析
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	protected void onResume() {
		super.onResume();
	}

	protected void onPause() {
		super.onPause();
	}

	public void startActivitys(Intent intent) {
		startActivity(intent);
	}

	public void finishs() {
		super.finish();
	}

	public void defaultFinish() {
		super.finish();
	}

	@Override
	public void onBackPressed() {
		finishs();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		InjectorFactory.Destory(this);// 垃圾回收
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent != null) {
			InjectorFactory.injectOnNewIntent(this);// 当有新的意图是 解析注解
		}
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		InjectorFactory.injectAfterSetContentView(this);// 放入内容后对注解解析
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		InjectorFactory.injectAfterSetContentView(this);// 放入内容后对注解解析
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		InjectorFactory.injectAfterSetContentView(this);// 放入内容后对注解解析
	}

	/**
	 * 以较短的时间来toast显示，大约3秒钟显示。
	 *
	 * @param msg
	 */
	public void showMessage(String msg) {
		if (msg != null) {
			Toast.makeText(this, msg + "", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 以较长的时间来toast显示，大约5秒钟显示。
	 *
	 * @param msg
	 */
	public void showLongMessage(String msg) {
		if (msg != null) {
			Toast.makeText(this, msg + "", Toast.LENGTH_LONG).show();
		}
	}

}
