package com.example.feng.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;


//获取某个类的全部方法
public class TestReflect2 {
    private static final long serialVersionUID = -2862585049955236662L;

    public static void main(int a, String b) throws Exception {
        Class<?> clazz = ReflectTest.User.class;
//        Class<?> clazz = Class.forName("com.example.feng.reflection.ReflectClas");
//        Class<?> clazz = new TestReflect0().getClass();
        Method method[] = clazz.getMethods();
        for (int i = 0; i < method.length; ++i) {
            Class<?> returnType = method[i].getReturnType();
            Class<?> para[] = method[i].getParameterTypes();
            int temp = method[i].getModifiers();
            System.out.print(Modifier.toString(temp) + " ");
            System.out.print(returnType.getName() + "  ");
            System.out.print(method[i].getName() + " ");
            System.out.print("(");
            for (int j = 0; j < para.length; ++j) {
                System.out.print(para[j].getName() + " " + "arg" + j);
                if (j < para.length - 1) {
                    System.out.print(",");
                }
            }
            Class<?> exce[] = method[i].getExceptionTypes();
            if (exce.length > 0) {
                System.out.print(") throws ");
                for (int k = 0; k < exce.length; ++k) {
                    System.out.print(exce[k].getName() + " ");
                    if (k < exce.length - 1) {
                        System.out.print(",");
                    }
                }
            } else {
                System.out.print(")");
            }
            System.out.println();
        }
    }


}
