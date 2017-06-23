package com.example.feng.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 总结
 Class类提供了四个public方法，用于获取某个类的构造方法。

 Constructor getConstructor(Class[] params)     根据构造函数的参数，返回一个具体的具有public属性的构造函数
 Constructor getConstructors()     返回所有具有public属性的构造函数数组
 Constructor getDeclaredConstructor(Class[] params)     根据构造函数的参数，返回一个具体的构造函数（不分public和非public属性）
 Constructor getDeclaredConstructors()    返回该类中所有的构造函数数组（不分public和非public属性）

 四种获取成员方法的方法
 Method getMethod(String name, Class[] params)    根据方法名和参数，返回一个具体的具有public属性的方法
 Method[] getMethods()    返回所有具有public属性的方法数组
 Method getDeclaredMethod(String name, Class[] params)    根据方法名和参数，返回一个具体的方法（不分public和非public属性）
 Method[] getDeclaredMethods()    返回该类中的所有的方法数组（不分public和非public属性）

 四种获取成员属性的方法
 Field getField(String name)    根据变量名，返回一个具体的具有public属性的成员变量
 Field[] getFields()    返回具有public属性的成员变量的数组
 Field getDeclaredField(String name)    根据变量名，返回一个成员变量（不分public和非public属性）
 Field[] getDelcaredField()    返回所有成员变量组成的数组（不分public和非public属性）
 */

public class T1 {
    public static void main(String[] args) {
        try {

            //创建类
            Class<?> class1 = Class.forName("com.example.feng.reflection.Person");

            //无参构造函数
            Object object = class1.newInstance();

            //有参构造函数：一个参数
            Constructor<?> constructor = class1.getDeclaredConstructor(String.class);
            constructor.newInstance("1000");

            //有参构造函数：二个参数
            Constructor<?> constructor2 = class1.getDeclaredConstructor(String.class, String.class);
            constructor2.newInstance("1001", "jack");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
        try {
            //获取class的三种方法
            Class<?> class1 = Class.forName("com.example.feng.reflection.Person");
            Class<?> class2 = Person.class;
            Class<?> class3 = new Person().getClass();
//            System.out.println( class1 +"\n"+class2+"\n"+class3);

            //获取所有的公共的方法
            Method[] methods = class1.getMethods();
            for (Method method : methods) {
//                System.out.println( method );
            }

            //获取所有实现的接口：getInterfaces()
            Class<?>[] interS = class1.getInterfaces();
            for (Class<?> cla : interS) {
//                System.out.println( cla );
            }

            //获取父类：getSuperclass()
            //获取父类
            Class<?> superclass = class1.getSuperclass();
//            System.out.println( superclass );

            //获取所有的构造函数
            Constructor<?>[] constructors = class1.getConstructors();
            for (Constructor<?> constructor : constructors) {
//                System.out.println( constructor );
            }

            //获取所有的属性：getDeclaredFields();
            //取得本类的全部属性
            Field[] field = class1.getDeclaredFields();
            for (Field field2 : field) {
//                System.out.println( field2 );
            }

            //创建实例：newInstance()
            //创建实例化：相当于 new 了一个对象
            try {
                Object object = class1.newInstance();
                //向下转型
                Person person = (Person) object;
                //通过反射，获取对象实例，并且操作对象的方法
                person.setName("hello");
                person.setAge("19");
                person.setId("1");
//                System.out.println(person.getName() + "--" + person.getAge() + "--" + person.getId());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            //getDeclaredFields 和 getFields 的区别
//            getDeclaredFields()获得某个类的所有申明的字段，即包括public、private和proteced，但是不包括父类的申明字段。
//            getFields()获得某个类的所有的公共（public）的字段，包括父类。

            Field[] declaredFields = class1.getDeclaredFields();
            Field[] fields = class1.getFields();
            for (Field field1 : declaredFields) {
//                System.out.println( "de--  " +  field1  );
            }
            for (Field field2 : fields) {
//                System.out.println( "fields--  " +  field2  );
            }

            //通过反射获取对象字段属性，并且赋值,注意必须是public属性的字段，否则需要打破封装
            try {
                Object person = class1.newInstance();
                //获得name 属性
                Field idField = class1.getDeclaredField("name");
                //打破封装  实际上setAccessible是启用和禁用访问安全检查的开关,并不是为true就能访问为false就不能访问
                //由于JDK的安全检查耗时较多.所以通过setAccessible(true)的方式关闭安全检查就可以达到提升反射速度的目的
                idField.setAccessible(true);
                //给name 属性赋值
                idField.set(person, "100");
                //打印 person 的属性值
//                System.out.println( idField.get( person ));
            } catch (Exception e) {
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－－
        try {
            //创建类
            Class<?> class1 = Class.forName("com.example.feng.reflection.Util");

            //获取 nameField 属性
            Field nameField = class1.getDeclaredField("name");
            //获取 nameField 的值
            String name_ = (String) nameField.get(nameField);
            //输出值
            System.out.println(name_);


            //没有返回值，没有参数
            Method getTipMethod1 = class1.getDeclaredMethod("getTips");
            getTipMethod1.invoke(null);

            //有返回值，没有参数
            Method getTipMethod2 = class1.getDeclaredMethod("getTip");
            String result_2 = (String) getTipMethod2.invoke(null);
            System.out.println("返回值： " + result_2);

            //没有返回值，有参数
            Method getTipMethod3 = class1.getDeclaredMethod("getTip", String.class);
            String result_3 = (String) getTipMethod3.invoke(null, "第三个方法");
            System.out.println("返回值： " + result_3);

            //有返回值，有参数
            Method getTipMethod4 = class1.getDeclaredMethod("getTip", int.class);
            String result_4 = (String) getTipMethod4.invoke(null, 1);
            System.out.println("返回值： " + result_4);

            //有返回值，有参数，私有化
            Method getPeivate = class1.getDeclaredMethod("getprivate", String.class);
            //打破封装
            getPeivate.setAccessible(true);
            String result_5 = (String) getPeivate.invoke(null, "mir_feng");
            System.out.println("返回值： " + result_5);


        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
