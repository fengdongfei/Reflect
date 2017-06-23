package com.example.feng.reflection;

import java.lang.reflect.Field;

/**
 * 通过反射机制操作某个类的属性
 */

public class TestReflect4 {
    private static String proprety = null;

    public static void main() throws Exception {
        Class<?> clazz = Class.forName("com.example.feng.reflection.TestReflect4");
        Object obj = clazz.newInstance();
        // 可以直接对 private 的属性赋值
        Field field = clazz.getDeclaredField("proprety");
        ////使用反射机制可以打破封装性，导致了java对象的属性不安全。
        field.setAccessible(true);
        field.set(obj, "Java反射机制");
        System.out.println(field.get(obj));
        System.out.println(proprety+"--");
    }
}
