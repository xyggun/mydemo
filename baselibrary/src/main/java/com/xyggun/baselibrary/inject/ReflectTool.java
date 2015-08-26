package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

public class ReflectTool {

	/**
	 * ���Ҷ�Ӧ�����������б���ע�����ֶΡ�
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
	 * ���Ҷ�Ӧ�����������б���ע�����ֶΡ�����������еݹ��ѯ�������е��ֶ�Ҳ����е�����
	 * 
	 * @param clazz
	 * @param annotated
	 * @return
	 */
	public static List<Field> getAnnotedFieldsWithParent(Class<?> clazz) {
		List<Field> fields = new LinkedList<Field>();
		for (Class<?> currentClass = clazz; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
			for (Field field : currentClass.getDeclaredFields()) {
				fields.add(field);
			}
		}
		return fields;
	}

	/**
	 * �õ����з��� ����ȡ ����ķ����� Ϊ������
	 * 
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
	 * @param clazz��ȡ�������������ע�ⷽ��
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
