package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;

import android.view.ViewGroup;

public class ViewGroupInjector {

	/**
	 * 
	 * @param decoView
	 * @param annotation
	 * @param instance
	 * @param field
	 */
	public static void inject(ViewGroup instance, Field field)// set
	{
		if (field.isAnnotationPresent(InjectView.class)) {
			InjectView annotation = field.getAnnotation(InjectView.class);
			boolean fieldVstatus = field.isAccessible();
			Object value = null;
			try {
				value = instance.findViewById(annotation.value());

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
