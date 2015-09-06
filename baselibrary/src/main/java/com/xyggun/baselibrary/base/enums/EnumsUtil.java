package com.xyggun.baselibrary.base.enums;


import com.xyggun.baselibrary.inject.common.StringUtils;

import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 枚举工具类
 * User: longyi
 * Date: 14-1-20
 * Time: 下午5:18
 */
public class EnumsUtil {

    private static Map<Class<?>, HashMap<String, Enum<?>>> cache = new ConcurrentHashMap<Class<?>, HashMap<String, Enum<?>>>();

    private static String makeKey(String fieldName, Object fieldValue) {
        return fieldName + "-" + fieldValue;
    }

    private static Map<String, Method> getterMethods(Class<?> enumClass) {
        Method[] methods = enumClass.getMethods();
        Map<String, Method> named2Method = new HashMap<String, Method>();
        for (Method method : methods) {
            Object retType = method.getReturnType();
            if (retType == null || retType.equals(Void.class)) {
                continue;
            }
            Class<?>[] params = method.getParameterTypes();
            if (params != null && params.length > 0) {
                continue;
            }
            String methodName = method.getName();
            if (methodName.startsWith("get")) {
                String name = methodName.substring(3);
                name = StringUtils.lowercaseFirstLetter(name);
                named2Method.put(name, method);
            } else if (retType.equals(Boolean.class) || retType.equals(Boolean.TYPE)) {
                if (methodName.startsWith("is")) {
                    String name = methodName.substring(2);
                    name = StringUtils.lowercaseFirstLetter(name);
                    named2Method.put(name, method);
                }
            }
        }
        return named2Method;
    }

    private static HashMap<String, Enum<?>> toEnumMaps(Class<?> enumClass, Enum<?>[] values) {
        Map<String, Method> namedMethods = getterMethods(enumClass);
        HashMap<String, Enum<?>> fieldsEnum = new HashMap<String, Enum<?>>();
        for (Enum<?> enumObject : values) {
            for (Map.Entry<String, Method> methodEntry : namedMethods.entrySet()) {
                try {
                    String fieldName = methodEntry.getKey();
                    Method method = methodEntry.getValue();
                    Object value = method.invoke(enumObject);
                    String key = makeKey(fieldName, value);
                    fieldsEnum.put(key, enumObject);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return fieldsEnum;
    }

    /**
     * 通过枚举类的成员名称和成员值获取枚举，如果没有则返回null
     *
     * @param values
     * @param fieldName
     * @param fieldValue
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> Enum<E> findEnumByFieldValue(Enum<E>[] values, String fieldName, Object fieldValue) {
        if (values == null || values.length == 0) {
            return null;
        }
        Class<E> enumClass = values[0].getDeclaringClass();
        HashMap<String, Enum<?>> enumMap = cache.get(enumClass);
        String key = makeKey(fieldName, fieldValue);
        if (enumMap == null) {
            enumMap = toEnumMaps(enumClass, values);
            cache.put(enumClass, enumMap);
        }
        return (Enum<E>) enumMap.get(key);
    }

    /**
     * 获取成员为value对应值的枚举，如果没有则返回null
     *
     * @param values
     * @param fieldValue
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> Enum<E> findEnumByValue(Enum<E>[] values, Object fieldValue) {
        return findEnumByFieldValue(values, "value", fieldValue);
    }

}
