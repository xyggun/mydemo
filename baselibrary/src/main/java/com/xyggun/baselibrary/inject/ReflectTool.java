package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class ReflectTool {

	/**
	 * 查找对应的类型中所有被标注过的字段。
	 *
	 * @param clazz
	 * @param annotated
	 * @return
	 */
	public static List<Field> getAnnotedFields(Class<?> clazz) {
		List<Field> fields = new LinkedList<Field>();
		for (Field field : clazz.getDeclaredFields()) {
			fields.add(field);
		}
		return fields;
	}
	/**
	 * 查找对应的类型中所有被标注过的字段。本方法会进行递归查询，父类中的字段也会进行迭代。
	 *
	 * @param clazz
	 * @param annotated
	 * @return
	 */
	public static List<Field> getAnnotedFieldsWithParent(Class<?> clazz) {
		List<Field> fields = new LinkedList<Field>();
		for (Class<?> currentClass = clazz; currentClass != Object.class; currentClass = currentClass
				.getSuperclass()) {
			for (Field field : currentClass.getDeclaredFields()) {
				fields.add(field);
			}
		}
		return fields;
	}

	/**
	 * 得到所有方法
	 * 不获取 父类的方法， 为了性能
	 * @param clazz
	 * @return
	 */
	public static List<Method> getAnnotedMethods(Class<?> clazz) {
		List<Method> fields = new LinkedList<Method>();
		for (Method method : clazz.getMethods())
			fields.add(method);
		return fields;
	}

	/**
	 *
	 * @param clazz获取方法包括父类的注解方法
	 * @param annotated
	 * @return
	 */
	public static List<Method> getAnnotedMethodsWithParent(Class<?> clazz) {
		List<Method> methods = new LinkedList<Method>();
		for (Class<?> currentClass = clazz; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
			for (Method method : currentClass.getMethods()) {
				methods.add(method);
			}
		}
		return methods;
	}
}
