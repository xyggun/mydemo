package com.xyggun.baselibrary.inject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.tecoming.t_base.app.BaseFragment;

import android.app.Activity;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickInjector {

	public static void injectOnClick(Activity activity) {
		Method[] methods = activity.getClass().getDeclaredMethods();
		if (null != methods && methods.length > 0) {
			for (Method method : methods) {
				injectViewOnclick(activity, method);
			}
		}
	}

	public static void injectViewOnclick(final Activity instance, final Method method) {
		if (method.isAnnotationPresent(OnClick.class)) {
			OnClick clickInject = method.getAnnotation(OnClick.class);
			View decorView = instance.getWindow().getDecorView();
			if (null != decorView && clickInject.value()[0] != -1) {
				for (int value : clickInject.value()) {
					View targetView = null;
					targetView = decorView.findViewById(value);
					if (null != targetView) {
						targetView.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								try {
									method.invoke(instance, v);
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}
							}
						});
					} else {
						throw new NullPointerException(String.format("Can't find view by id= %s", value));
					}
				}
			}
		}
	}

	public static void injectViewOnclick(final BaseFragment instance, final Method method) {
		if (method.isAnnotationPresent(OnClick.class)) {
			OnClick clickInject = method.getAnnotation(OnClick.class);
			View decorView = instance.getDecorView();
			if (null != decorView && clickInject.value()[0] != -1) {
				for (int value : clickInject.value()) {
					View targetView = null;
					targetView = decorView.findViewById(value);
					if (null != targetView) {
						targetView.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								try {
									method.invoke(instance, v);
								} catch (IllegalArgumentException e) {
									e.printStackTrace();
								} catch (IllegalAccessException e) {
									e.printStackTrace();
								} catch (InvocationTargetException e) {
									e.printStackTrace();
								}
							}
						});
					} else {
						throw new NullPointerException(String.format("Can't find view by id= %s", value));
					}
				}
			}
		}
	}

}
