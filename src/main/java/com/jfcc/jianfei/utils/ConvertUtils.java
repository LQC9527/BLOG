package com.jfcc.jianfei.utils;

import org.springframework.stereotype.Component;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

public class ConvertUtils {

//    public static <T> Map<String, String> objectToMap(T obj) {
//        Map<String, String> map = new HashMap<>();
//        for (Field field : getAllFields(obj.getClass())) {
//            field.setAccessible(true);
//            try {
//                Object value = field.get(obj);
//                if (value != null) {
//                    map.put(field.getName(), value.toString());
//                }
//            } catch (IllegalAccessException e) {
//                throw new RuntimeException("转换 Object 到 Map 失败", e);
//            }
//        }
//        return map;
//    }
//
//    public static <T> T mapToObject(Map<String, String> map, Class<T> clazz) {
//        try {
//            T obj = clazz.getDeclaredConstructor().newInstance();
//            for (Field field : getAllFields(clazz)) {
//                field.setAccessible(true);
//                if (map.containsKey(field.getName())) {
//                    Object value = convertValue(map.get(field.getName()), field.getType());
//                    field.set(obj, value);
//                }
//            }
//            return obj;
//        } catch (Exception e) {
//            throw new RuntimeException("转换 Map 到 Object 失败", e);
//        }
//    }
//
//    private static List<Field> getAllFields(Class<?> type) {
//        List<Field> fields = new ArrayList<>();
//        while (type != null && type != Object.class) {
//            fields.addAll(Arrays.asList(type.getDeclaredFields()));
//            type = type.getSuperclass();
//        }
//        return fields;
//    }

    // 将对象转成 Map<String, String>，适用于 Hash 存储
    public static Map<String, String> objectToMap(Object obj) {
        Map<String, String> map = new HashMap<>();
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
            for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
                Object value = pd.getReadMethod().invoke(obj);
                if (value != null) {
                    map.put(pd.getName(), value.toString());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("转换对象为Hash失败", e);
        }
        return map;
    }

    // 将 Hash Map 还原为 Java 对象
    public static <T> T mapToObject(Map<String, String> hash, Class<T> clazz) {
        try {
            T obj = clazz.getDeclaredConstructor().newInstance();
            for (Map.Entry<String, String> entry : hash.entrySet()) {
                PropertyDescriptor pd = new PropertyDescriptor(entry.getKey(), clazz);
                    Method setter = pd.getWriteMethod();
                if (setter != null) {
                    Class<?> paramType = pd.getPropertyType();
                    Object value = convertValue(entry.getValue(), paramType);
                    setter.invoke(obj, value);
                }
            }
            return obj;
        } catch (Exception e) {
            throw new RuntimeException("转换Hash为对象失败", e);
        }
    }

    private static Object convertValue(String value, Class<?> targetType) {
        if (targetType == String.class) return value;
        if (targetType == Integer.class || targetType == int.class) return Integer.parseInt(value);
        if (targetType == Long.class || targetType == long.class) return Long.parseLong(value);
        if (targetType == Boolean.class || targetType == boolean.class) return Boolean.parseBoolean(value);
        if (targetType == Double.class || targetType == double.class) return Double.parseDouble(value);
        // 可扩展更多类型
        return value;
    }
}
