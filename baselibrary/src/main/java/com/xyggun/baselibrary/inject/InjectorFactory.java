package com.xyggun.baselibrary.inject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

import com.tecoming.t_base.app.BaseFragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.ViewGroup;

public class InjectorFactory {
	private static HashMap<String, ReflectObj> clazzInfo = new HashMap<String, ReflectObj>();// ��ǰ����Ϣ

	private static void checkClazzInfo(String clazzname) {
		if (clazzInfo.get(clazzname) == null) {
			ReflectObj obj = new ReflectObj();
			clazzInfo.put(clazzname, obj);
		}
	}

	private static List<Field> getFields(Class clazz) {// ��ȡ�ֶ�
		String name = clazz.getSimpleName();
		checkClazzInfo(name);
		ReflectObj obj = clazzInfo.get(name);
		List<Field> fields = obj.getFields();
		if (fields == null) {
			obj.setFields(ReflectTool.getAnnotedFields(clazz));
			clazzInfo.put(name, obj);
		}
		return obj.getFields();

	}

	/**
	 * 
	 * @Title: getMethods
	 * @Description:��ȡ����
	 * @param @param clazz
	 * @param @return
	 * @return List<Method>
	 * @throws
	 */
	private static List<Method> getMethods(Class clazz) {
		String name = clazz.getSimpleName();
		checkClazzInfo(name);
		ReflectObj obj = clazzInfo.get(name);
		List<Method> methods = obj.getMethods();
		if (methods == null) {
			obj.setMethods(ReflectTool.getAnnotedMethods(clazz));
			clazzInfo.put(name, obj);
		}
		return obj.getMethods();

	}

	/**
	 * 
	 * @Title: getMethodsWithParent
	 * @Description: ��ȡ�������������ࣩ
	 * @param @param clazz
	 * @param @return
	 * @return List<Method>
	 * @throws
	 */
	private static List<Method> getMethodsWithParent(Class clazz) {
		String name = clazz.getSimpleName();
		checkClazzInfo(name);
		ReflectObj obj = clazzInfo.get(name);
		List<Method> methods = obj.getMethodsWithParent();
		if (methods == null) {
			obj.setMethodsWithParent(ReflectTool.getAnnotedMethodsWithParent(clazz));
			clazzInfo.put(name, obj);
		}
		return obj.getMethodsWithParent();

	}

	/**
	 * 
	 * @Title: injectBeforeSetContentView
	 * @Description: SetContentView ��ʼǰע��
	 * @param @param instance
	 * @return void
	 * @throws
	 */
	public static void injectBeforeSetContentView(Activity instance) {
		/**
		 * ��ע��
		 */
		FullscreenInject.inject(instance);
		NoTitleInject.inject(instance);

		/**
		 * �ֶ�ע��
		 */
		for (Field annotatedField : getFields(instance.getClass())) {
			SystemServiceInjector.inject(instance, annotatedField);
			ExtrasInjector.inject(instance, annotatedField);
			ResourceInjector.inject(instance, annotatedField);
			PreferenceInjector.inject(instance, annotatedField);
			DaoInjector.inject(instance, annotatedField);
		}
		/**
		 * ��ͼע�� �����
		 */

		SetContentViewInject.inject(instance);
		instance = null;
	}

	/**
	 * 
	 * @Title: injectAfterSetContentView
	 * @Description: SetContentView ֮��ע��
	 * @param @param instance
	 * @return void
	 * @throws
	 */
	public static void injectAfterSetContentView(Activity instance) {
		for (Field annotatedField : getFields(instance.getClass())) {
			ViewInjector.inject(instance, annotatedField);
		}
		/**
		 * ����ע��
		 */
		for (Method annotatedMethod : getMethods(instance.getClass())) {
			OnClickInjector.injectViewOnclick(instance, annotatedMethod);
		}
		instance = null;
	}

	/**
	 * 
	 * @Title: injectViewGroup
	 * @Description: ViewGroup ������ֶ�ע��
	 * @param @param instance
	 * @return void
	 * @throws
	 */
	public static void injectViewGroup(ViewGroup instance) {
		AddViewInjector.inject(instance);
		/**
		 * �ֶ�ע��
		 */
		for (Field annotatedField : getFields(instance.getClass())) {
			ViewGroupInjector.inject(instance, annotatedField);
		}
		instance = null;
	}

	/**
	 * 
	 * @Title: injectOnStart
	 * @Description: OnStartʱע��
	 * @param @param instance
	 * @return void
	 * @throws
	 */
	public static void injectOnStart(Activity instance) {
	}

	/**
	 * 
	 * @Title: injectOnNewIntent
	 * @Description: NewIntentʱע��
	 * @param @param instance
	 * @return void
	 * @throws
	 */
	public static void injectOnNewIntent(Activity instance) {
		for (Field annotatedField : getFields(instance.getClass())) {
			ExtrasInjector.inject(instance, annotatedField);
		}
		instance = null;
	}

	/**
	 * 
	 * @Title: Destory
	 * @Description: Destoryʱע��
	 * @param @param instance
	 * @return void
	 * @throws
	 */
	public static void Destory(Activity instance) {
		Class clazz = instance.getClass();
		String name = clazz.getSimpleName();
		clazzInfo.remove(name);
	}

	/**
	 * 
	 * @Title: Destory
	 * @Description: Destoryʱע��
	 * @param @param instance
	 * @return void
	 * @throws
	 */
	public static void Destory(Fragment instance) {
		Class clazz = instance.getClass();
		String name = clazz.getSimpleName();
		clazzInfo.remove(name);
	}

	/**
	 * 
	 * @Title: onFragmentCreate
	 * @Description: onFragmentCreate ʱע��
	 * @param @param instance
	 * @param @param inflater
	 * @param @param container
	 * @return void
	 * @throws
	 */
	public static void onFragmentCreate(BaseFragment instance, LayoutInflater inflater, ViewGroup container) {
		SetContentViewInject.inject(instance, inflater, container);
		/**
		 * �ֶ�ע��
		 */
		for (Field annotatedField : getFields(instance.getClass())) {
			ViewInjector.inject(instance, annotatedField);
			ResourceInjector.inject(instance, annotatedField);
		}
		/**
		 * ����ע��
		 */
		for (Method annotatedMethod : getMethods(instance.getClass())) {
			OnClickInjector.injectViewOnclick(instance, annotatedMethod);
		}
	}
}
