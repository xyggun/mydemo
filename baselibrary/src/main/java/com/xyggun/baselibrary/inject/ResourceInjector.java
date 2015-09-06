package com.xyggun.baselibrary.inject;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.xyggun.baselibrary.base.BaseFragment;

import java.lang.reflect.Field;

public class ResourceInjector {

	public static void inject(Activity instance, Field field)// set
	{
		if (field.isAnnotationPresent(InjectResource.class)) {
			Context applicationContext = instance.getApplicationContext();
			boolean fieldVstatus = field.isAccessible();
			Object value = null;
			value = inject(applicationContext, field);
			try {
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
	 * 
	 * @Title: inject
	 * @Description: 注入BaseFragment资源属�??
	 * @param @param instance
	 * @param @param field
	 * @return void
	 * @throws
	 */
	public static void inject(BaseFragment instance, Field field) {
		if (field.isAnnotationPresent(InjectResource.class)) {
			Context applicationContext = instance.getActivity();
			boolean fieldVstatus = field.isAccessible();
			Object value = null;
			value = inject(applicationContext, field);
			try {
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
	 * 
	 * @Title: inject
	 * @Description: 通过上下文对象，和属性类型获取要注入的value
	 * @param @param context
	 * @param @param field
	 * @param @return
	 * @return Object
	 * @throws
	 */
	private static Object inject(Context context, Field field) {
		InjectResource annotation = field.getAnnotation(InjectResource.class);
		Object value = null;
		try {
			final int id = annotation.id();
			final Class<?> t = field.getType();
			final Resources resources = context.getResources();

			if (String.class.isAssignableFrom(t)) {
				value = resources.getString(id);
			} else if (boolean.class.isAssignableFrom(t) || Boolean.class.isAssignableFrom(t)) {
				value = resources.getBoolean(id);
			} else if (ColorStateList.class.isAssignableFrom(t)) {
				value = resources.getColorStateList(id);
			} else if (int.class.isAssignableFrom(t) || Integer.class.isAssignableFrom(t)) {
				value = resources.getInteger(id);
			} else if (Drawable.class.isAssignableFrom(t)) {
				value = resources.getDrawable(id);
			} else if (String[].class.isAssignableFrom(t)) {
				value = resources.getStringArray(id);
			} else if (int[].class.isAssignableFrom(t) || Integer[].class.isAssignableFrom(t)) {
				value = resources.getIntArray(id);
			} else if (Animation.class.isAssignableFrom(t)) {
				value = AnimationUtils.loadAnimation(context, id);
			} else if (Movie.class.isAssignableFrom(t)) {
				value = resources.getMovie(id);
			}

			if (value == null && field.getAnnotation(Nullable.class) == null) {
				throw new NullPointerException(String.format("Can't inject null value into %s.%s when field is not @Nullable", field.getDeclaringClass(), field.getName()));
			} else {
				return value;
			}
		} catch (IllegalArgumentException f) {
			throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", value != null ? value.getClass() : "(null)", value, field.getType(), field.getName()));
		}
	}

}
