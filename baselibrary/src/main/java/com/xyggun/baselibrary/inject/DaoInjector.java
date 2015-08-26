package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;

public class DaoInjector {

	/**
	 * 
	 * @param annotation
	 * @param instance
	 * @param field
	 */
	public static void inject(Object instance, Field field)// set
	{
		if (field.isAnnotationPresent(InjectDao.class)) {
			InjectDao annotation = field.getAnnotation(InjectDao.class);
			boolean fieldVstatus = field.isAccessible();
			Class<?> clazz = annotation.name();
			try {

				if (clazz == null && field.getAnnotation(Nullable.class) == null)
					throw new NullPointerException(String.format("Can't inject null value into %s.%s when field is not @Nullable", field.getDeclaringClass(), field.getName()));

				field.setAccessible(true);
				field.set(instance, clazz.newInstance());
				field.setAccessible(fieldVstatus);

			} catch (IllegalAccessException e) {
				throw new RuntimeException(e);

			} catch (Exception f) {
				throw new IllegalArgumentException(String.format("Can't assign %s value %s to %s field %s", clazz != null ? clazz : "(null)", clazz, field.getType(), field.getName()));
			}
		}
	}
}
