package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferenceInjector {
	/**
	 * 
	 * @param context
	 * @param annotation
	 * @param instance
	 * @param field
	 */
	public static void inject(Activity instance, Field field) {
		if (field.isAnnotationPresent(InjectPreference.class)) {
			Context context = instance.getApplicationContext();
			SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
			InjectPreference annotation = field.getAnnotation(InjectPreference.class);
			boolean fieldVstatus = field.isAccessible();
			String key = annotation.name().length() == 0 ? null : annotation.name();
			if (key == null) {
				// try to load by resource
				key = context.getApplicationContext().getApplicationContext().getString(annotation.id());
			}
			if (key == null && field.getAnnotation(Nullable.class) == null) {
				throw new NullPointerException("Please ensure set key name or has valid key id!");
			}
			Object value = null;
			final Class<?> t = field.getType();
			if (String.class.isAssignableFrom(t)) {
				value = preferences.getString(key, null);
			} else if (boolean.class.isAssignableFrom(t) || Boolean.class.isAssignableFrom(t)) {
				value = preferences.getBoolean(key, false);
			} else if (int.class.isAssignableFrom(t) || Integer.class.isAssignableFrom(t)) {
				value = preferences.getInt(key, 0);
			} else if (long.class.isAssignableFrom(t) || Long.class.isAssignableFrom(t)) {
				value = preferences.getFloat(key, 0);
			} else if (float.class.isAssignableFrom(t) || Float.class.isAssignableFrom(t)) {
				value = preferences.getFloat(key, 0);
			}

			field.setAccessible(true);
			try {
				field.set(instance, value);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			field.setAccessible(fieldVstatus);
		}
	}
}
