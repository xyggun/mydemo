package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;

import android.app.Activity;
import android.os.Bundle;

public class ExtrasInjector {

	public static void inject(Activity instance) {
		for (Field annotatedField : ReflectTool.getAnnotedFields(instance.getClass())) {
			inject(instance, annotatedField);
		}
	}

	public static void inject(Activity instance, Field field)// set
	{

		if (field.isAnnotationPresent(InjectExtras.class)) {
			Bundle extras = instance.getIntent().getExtras();
			InjectExtras annotation = field.getAnnotation(InjectExtras.class);
			Object value;
			boolean fieldVstatus = field.isAccessible();
			final String id = annotation.name();

			if (extras == null || !extras.containsKey(id)) {
				// If no extra found and the extra injection is optional, no
				// injection happens.
				if (annotation.optional()) {
					return;
				} else {
					throw new IllegalStateException(String.format("Can't find the mandatory extra identified by key [%s] on field %s.%s", id, field.getDeclaringClass(), field.getName()));
				}
			}

			value = extras.get(id);

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
