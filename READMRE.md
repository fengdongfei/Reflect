1反射机制是什么
    反射机制是在运行状态中，对于任意一个类，都能够知道这个类的所有属性和方法；对于任意一个对象，都能够调用它的任意一个方法和属性；这种动态获取的信息以及动态调用对象的方法的功能称为java语言的反射机制。

2反射机制能做什么
    反射机制主要提供了以下功能：
        在运行时判断任意一个对象所属的类；
        在运行时构造任意一个类的对象；
        在运行时判断任意一个类所具有的成员变量和方法；
        在运行时调用任意一个对象的方法；
        生成动态代理。

Class反射机制
    指的是可以于运行时加载,探知和使用编译期间完全未知的类.
    程序在运行状态中, 可以动态加载一个只有名称的类, 对于任意一个已经加载的类,都能够知道这个类的所有属性和方法; 对于任意一个对象,都能调用他的任意一个方法和属性;
    加载完类之后, 在堆内存中会产生一个Class类型的对象(一个类只有一个Class对象), 这个对象包含了完整的类的结构信息,而且这个Class对象就像一面镜子,透过这个镜子看到类的结构,所以被称之为:反射。
    每个类被加载进入内存之后,系统就会为该类生成一个对应的java.lang.Class对象,通过该Class对象就可以访问到JVM中的这个类.

Class对象的获取
    对象的getClass()方法;
    类的.class(最安全/性能最好)属性;
    运用Class.forName(String className)动态加载类,className需要是类的全限定名(最常用).



    首页
    所有文章
    资讯
    Web
    架构
    基础技术
    书籍
    教程
    Java小组
    工具资源

Java 反射

2016/01/24 | 分类： 教程 | 0 条评论 | 标签： 反射
分享到：
5
原文出处： 翡青
动态语言

动态语言，是指程序在运行时可以改变其结构：新的函数可以被引进，已有的函数可以被删除等在结构上的变化。比如众所周知的ECMAScript(JavaScript)便是一个动态语言。除此之外如Ruby、Python等也都属于动态语言，而C、C++等语言则不属于动态语言。(引自: 百度百科)
1
2

var execString = "alert(Math.floor(Math.random()*10));";
eval(execString);
Class反射机制

    指的是可以于运行时加载,探知和使用编译期间完全未知的类.
    程序在运行状态中, 可以动态加载一个只有名称的类, 对于任意一个已经加载的类,都能够知道这个类的所有属性和方法; 对于任意一个对象,都能调用他的任意一个方法和属性;
    加载完类之后, 在堆内存中会产生一个Class类型的对象(一个类只有一个Class对象), 这个对象包含了完整的类的结构信息,而且这个Class对象就像一面镜子,透过这个镜子看到类的结构,所以被称之为:反射。

Instances of the class Class represent classes and interfaces in a running Java application. An enum is a kind of class and an annotation is a kind of interface. Every array also belongs to a class that is reflected as a Class object that is shared by all arrays with the same element type and number of dimensions(维度). The primitive Java types (boolean, byte, char, short, int, long, float, anddouble), and the keyword void are also represented as Class objects.



    每个类被加载进入内存之后,系统就会为该类生成一个对应的java.lang.Class对象,通过该Class对象就可以访问到JVM中的这个类.

Class对象的获取

    对象的getClass()方法;
    类的.class(最安全/性能最好)属性;
    运用Class.forName(String className)动态加载类,className需要是类的全限定名(最常用).

从Class中获取信息
    Class类提供了大量的实例方法来获取该Class对象所对应的详细信息,Class类大致包含如下方法,其中每个方法都包含多个重载版本,因此我们只是做简单的介绍,详细请参考JDK文档,获取类内信息

    获取内容 	方法签名
    构造器 	Constructor<T> getConstructor(Class<?>... parameterTypes)
    包含的方法 	Method getMethod(String name, Class<?>... parameterTypes)
    包含的属性 	Field getField(String name)
    包含的Annotation 	<A extends Annotation> A getAnnotation(Class<A> annotationClass)
    内部类 	Class<?>[] getDeclaredClasses()
    外部类 	Class<?> getDeclaringClass()
    所实现的接口 	Class<?>[] getInterfaces()
    修饰符 	int getModifiers()
    所在包 	Package getPackage()
    类名 	String getName()
    简称 	String getSimpleName()

        一些判断类本身信息的方法

    判断内容 	方法签名
    注解类型? 	boolean isAnnotation()
    使用了该Annotation修饰? 	boolean isAnnotationPresent(Class<? extends Annotation> annotationClass)
    匿名类? 	boolean isAnonymousClass()
    数组? 	boolean isArray()
    枚举? 	boolean isEnum()
    原始类型? 	boolean isPrimitive()
    接口? 	boolean isInterface()
    obj是否是该Class的实例 	boolean isInstance(Object obj)

使用反射生成并操作对象:
    Method Constructor Field这些类都实现了java.lang.reflect.Member接口,程序可以通过Method对象来执行相应的方法,通过Constructor对象来调用对应的构造器创建实例,通过Filed对象直接访问和修改对象的成员变量值.

创建对象
    通过反射来生成对象的方式有两种:
    使用Class对象的newInstance()方法来创建该Class对象对应类的实例(这种方式要求该Class对象的对应类有默认构造器).
    先使用Class对象获取指定的Constructor对象, 再调用Constructor对象的newInstance()方法来创建该Class对象对应类的实例(通过这种方式可以选择指定的构造器来创建实例).

    通过第一种方式来创建对象比较常见, 像Spring这种框架都需要根据配置文件(如applicationContext.xml)信息来创建Java对象,从配置文件中读取的只是某个类的全限定名字符串,程序需要根据该字符串来创建对应的实例,就必须使用默认的构造器来反射对象.
    下面我们就模拟Spring实现一个简单的对象池, 该对象池会根据文件读取key-value对, 然后创建这些对象, 并放入Map中.

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