package com.wucc.island.common.util;

import com.wucc.island.common.exception.BaseException;

import java.lang.reflect.Field;



/**
 * 反射工具类
 * 
 * @author zhoujiej
 * @date 2020/06/08
 */
public class ReflectionUtils {

    public static final String PREFIX_SET = "set";

    public static final String PREFIX_GET = "get";

    public static Object getValue(Class clazz, String fieldName, Object target) {
        try {
            Field field = org.springframework.util.ReflectionUtils.findField(clazz, fieldName);
            field.setAccessible(true);
            // Method method = target.getClass().getMethod(getter);
            // method.setAccessible(true);
            // Object value = method.invoke(target);
            return field.get(target);
        } catch (Exception e) {
            throw new BaseException("获取字段值失败");
        }
    }

    public static void setValue(Class clazz, String fieldName, Object target, Object value) {
        try {
            Field field = org.springframework.util.ReflectionUtils.findField(clazz, fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BaseException("设置字段值失败");
        }
    }
}
