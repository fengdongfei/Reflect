package com.example.feng.reflection;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 *在泛型为Integer的ArrayList中存放一个String类型的对象。
 */

public class TestReflect5 {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<Integer>();
        Method method = list.getClass().getMethod("add", Object.class);
        method.invoke(list, "Java反射机制实例。");
        System.out.println(list.get(0));
    }

}
