package com.xyggun.baselibrary.inject;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xyggun.baselibrary.inject.base.BaseFragment;

public class SetContentViewInject {
	/**
	 * 迭代对象实例，注入所有包含SetContentView的字段�??
	 * 
	 * @param instance
	 */
	public static void inject(Activity instance) {
		SetContentView setContentView = instance.getClass().getAnnotation(SetContentView.class);
		if (setContentView != null) {
			int value = setContentView.value();
			instance.setContentView(value);
		}
	}

	/**
	 * 注入�?有包含SetContentView的字段�??
	 * 
	 * @param instance
	 */
	public static void inject(BaseFragment instance, LayoutInflater inflater, ViewGroup container) {
		SetContentView setContentView = instance.getClass().getAnnotation(SetContentView.class);
		if (setContentView != null) {
			int value = setContentView.value();
			View view = inflater.inflate(value, container, false);
			instance.setDecorView(view);
		}
	}

}
