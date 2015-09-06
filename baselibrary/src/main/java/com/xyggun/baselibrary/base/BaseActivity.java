package com.xyggun.baselibrary.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.xyggun.baselibrary.R;
import com.xyggun.baselibrary.inject.InjectorFactory;


public class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

		AppManager.getAppManager().addActivity(this);
		InjectorFactory.injectBeforeSetContentView(this);// 放入试图前对注解解析

        // 设置沉浸式标题栏
		getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
				WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
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
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	public void finishs() {
		super.finish();
		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
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
		InjectorFactory.Destory(this); // 垃圾回收
		AppManager.getAppManager().finishActivity(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		if (intent != null) {
			InjectorFactory.injectOnNewIntent(this); // 当有新的意图是 解析注解
		}
	}

	@Override
	public void setContentView(int layoutResID) {
		super.setContentView(layoutResID);
		InjectorFactory.injectAfterSetContentView(this); // 放入内容后对注解解析
	}

	@Override
	public void setContentView(View view) {
		super.setContentView(view);
		InjectorFactory.injectAfterSetContentView(this); // 放入内容后对注解解析
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		super.setContentView(view, params);
		InjectorFactory.injectAfterSetContentView(this); // 放入内容后对注解解析
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
