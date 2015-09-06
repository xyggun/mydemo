package com.xyggun.baselibrary.inject;

import android.app.Activity;
import android.view.View;

import com.xyggun.baselibrary.base.BaseFragment;

import java.lang.reflect.Field;

public class ViewInjector {

	/**
	 * 
	 * @param instance
	 * @param field
	 */
	public static void inject(Activity instance, Field field)// set
	{
		if (field.isAnnotationPresent(InjectView.class)) {
			InjectView annotation = field.getAnnotation(InjectView.class);
			boolean fieldVstatus = field.isAccessible();
			View decoView = null;
			decoView = instance.getWindow().getDecorView();
			Object value = null;
			try {
				value = decoView.findViewById(annotation.value());

				if (value == null && field.getAnnotation(Nullable.class) == null)
					throw new NullPointerException(String.format("Can't inject null value into %s.%s when field is not @Nullable", field.getDeclaringClass(), field.getName()));

				field.setAccessible(true);
				field.set(instance, value);
				field.setAccessible(fieldVstatus);

			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);

			} catch (IllegalArgumentException f) {
				throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", value != null ? value.getClass() : "(null)", value, field.getType(), field.getName()));
			}
		}
	}

	/**
	 * 从指定的Activity中依据对应的字段和注解寻找到指定的View。若寻找结果为null,则会产生异常。如果想允许查找失败,请添加@NullAble注解 �?
	 * 
	 * @param instance
	 * @param field
	 */
	public static void inject(BaseFragment instance, Field field)// set
	{
		if (field.isAnnotationPresent(InjectView.class)) {
			InjectView annotation = field.getAnnotation(InjectView.class);
			boolean fieldVstatus = field.isAccessible();
			View decoView = null;
			decoView = instance.getDecorView();
			Object value = null;
			try {
				value = decoView.findViewById(annotation.value());

				if (value == null && field.getAnnotation(Nullable.class) == null)
					throw new NullPointerException(String.format("Can't inject null value into %s.%s when field is not @Nullable", field.getDeclaringClass(), field.getName()));

				field.setAccessible(true);
				field.set(instance, value);
				field.setAccessible(fieldVstatus);

			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);

			} catch (IllegalArgumentException f) {
				throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", value != null ? value.getClass() : "(null)", value, field.getType(), field.getName()));
			}
		}
	}

}