package com.wzm.appdemos.im.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by wangzm on 2017/8/4.
 */

public class ClassMap {
    private static Map<String, Object> classMap;

    private static void set(String key, Object value) {
        classMap.put(key, value);
    }

    private static Object get(String key) {
        return classMap.get(key);
    }

    public static Object getInstance(Class clazz) {
        if (classMap == null) {
            classMap = new ConcurrentHashMap<>();
        }
        String forName = clazz.getName();
        Object obj = get(forName);
        if (obj == null) {
            try {
                Object result = clazz.newInstance();
                set(forName, result);
                return result;
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return obj;
    }
}
