package com.example.feng.reflection;

import android.util.Log;

/**
 * Created by Chexiangjia-MAC on 17/6/23.
 */

public class TestReflect0 {

    //通过一个对象获得完整的包名和类名
    public static void Main(){
        TestReflect0 testReflect = new TestReflect0();
        Log.e("RETROFIT",testReflect.getClass().getName());
    }

    //实例化Class类对象
    public static void Main1() throws ClassNotFoundException {
        Class<?> class1 = null;
        Class<?> class2 = null;
        Class<?> class3 = null;
        // 一般采用这种形式
        class1 = Class.forName("com.example.feng.reflection.TestReflect0");
        class2 = new TestReflect0().getClass();
        class3 = TestReflect0.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
    }
}
