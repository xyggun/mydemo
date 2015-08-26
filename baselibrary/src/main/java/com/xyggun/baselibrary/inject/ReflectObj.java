package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

public class ReflectObj {
	private List<Field> fields;// �����ֶ�
	private List<Method> methods;// ���з���
	private List<Method> methodsWithParent;// ���з���(��������)

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public List<Method> getMethods() {
		return methods;
	}

	public void setMethods(List<Method> methods) {
		this.methods = methods;
	}

	public List<Method> getMethodsWithParent() {
		return methodsWithParent;
	}

	public void setMethodsWithParent(List<Method> methodsWithParent) {
		this.methodsWithParent = methodsWithParent;
	}
}
