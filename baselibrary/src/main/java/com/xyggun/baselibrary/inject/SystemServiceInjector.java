package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;

import android.app.Activity;
import android.content.Context;

public class SystemServiceInjector {
	/**
	 * @param context
	 * @param annotation
	 * @param instance
	 * @param field
	 */
	public static void inject(Activity instance, Field field) {
		if (field.isAnnotationPresent(InjectSystemService.class)) {
			InjectSystemService annotation = field.getAnnotation(InjectSystemService.class);
			boolean fieldVstatus = field.isAccessible();
			Context context = instance.getApplicationContext();
			Object value = null;
			//
			final String servieName = annotation.value();
			value = context.getSystemService(servieName);

			if (value == null && field.getAnnotation(Nullable.class) == null) {
				throw new NullPointerException(String.format("Can't inject null value into %s.%s when field is not @Nullable", field.getDeclaringClass(), field.getName()));
			}

			field.setAccessible(true);
			try {
				field.set(instance, value);

			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);
			} catch (IllegalArgumentException f) {
				throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", value != null ? value.getClass() : "(null)", value, field.getType(), field.getName()));
			}
			field.setAccessible(fieldVstatus);
		}
	}
}
