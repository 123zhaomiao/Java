@[toc]
# 1. 认识反射
反射指的是  <font color="#dd0000">对象的反向处理操作</font>，* 根据对象倒推类的组成*
反射的核心在于Object类的方法:
```java
//返回此Object的运行时类
public final native Class<?> getClass();
```
该方法返回的是一个Class类对象，这个Class描述的就是类。Class<?>描述各个类的组成(构造方法、普通方法、普通属性)
<font color="#dd0000">反射的世界里，看中的不是对象，而是对象身后的组成</font>
## 1.1 Class类对象的三种实例化方式
Class类是描述整个类的概念，也是整个反射的操作源头，在使用Class类的时候需要关注的依然是这个类的对象，而这个类的对象的产生模式一共有三种:

 1. 任何类的 实例化对象可以通过Object类中的getClass()方法取得Class类对象。`public final Class<?> getClass()`
 2. "类.class"直接根据某个具体的类来取得Class类的实例化对象
 3. 使用Class类提供的静态方法:`public static Class<?> forName(String className)throws ClassNotFoundException`传入类的全名称来取得Class对象。

```java
import java.util.Date;
public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        Date date = new Date();
        //1. 任何类的实例化对象 通过Object提供的getClass()取得Class对象
        System.out.println(date.getClass());
        //2.类名.class取得Class类对象
        System.out.println(Date.class);
        //3.通过Class类提供的静态方法forName()传入类的全名称取得Class对象
        System.out.println(Class.forName("java.util.Date"));
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/201903301835515.png)
取得一个类的Class对象后，可以通过反射来实例化对象

## 1.2 通过反射实例化对象
在Class类中有如下方法：
```java
public T newInstance()
              throws InstantiationException,
                     IllegalAccessException
```
```java
import java.util.Date;
public class Test{
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //Class.forName的返回值为Class<?> ?为任意字符 需要转型为Date
        Class<Date> cls = (Class<Date>) Class.forName("java.util.Date");
        Date date = cls.newInstance();
        System.out.println(date);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330185228561.png)
## 1.3 反射与工厂设计模式
之前的工厂模式:
```java
interface IFruit{
    void eat();
}
class Apple implements IFruit{
    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}
class Pear implements IFruit{
    @Override
    public void eat() {
        System.out.println("吃梨");
    }
}
class Factory{

    public static IFruit getInstance(String name){
        IFruit fruit = null;
        if("apple".equals(name)){
            fruit =  new Apple();
        }else if("pear".equals(name)){
            fruit =  new Pear();
        }
        return fruit;
    }
}
public class Test{
    public static void main(String[] args) {
        IFruit fruit = Factory.getInstance("apple");
        fruit.eat();
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330191026998.png)
此时若想要增加一个新的水果类，在工厂类中就需要多加一个else分支，多n个水果类，就需要多加n个else分支。
那我们如果用反射处理呢?
```java
interface IFruit{
    void eat();
}
class Apple implements IFruit{
    @Override
    public void eat() {
        System.out.println("吃苹果");
    }
}
class Pear implements IFruit{
    @Override
    public void eat() {
        System.out.println("吃梨");
    }
}
class Factory{

    public static IFruit getInstance(String name){
        IFruit fruit = null;
        try {
            //1.取得Class类对象
            Class<?> cls = Class.forName(name);
            //2.通过反射实例化对象
            fruit = (IFruit) cls.newInstance();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return fruit;
    }
}
public class Test{
    public static void main(String[] args) {
        IFruit fruit = Factory.getInstance("反射.反射复习.Pear");
        fruit.eat();
    }
}
```
即使现在新创建许多的水果类，也不用修改工厂模式!
# 2. 反射与类
## 2.1 反射取得父类、父接口信息
### 2.1.1 取得类的包名称
```java
//获取此类的包
public Package  getPackage();
```
```java
package 反射.反射复习;
public class Test{
    public static void main(String[] args) {
        Class<?> cls = Test.class;
        System.out.println(cls.getPackage());
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330192640130.png)
### 2.1.2 取得父类的Class对象
```java
public native Class<? super T> getClass();
```
### 2.1.3 取得实现的父接口
```java
public Class<?>[] getInterfaces();//接口由多个所以为数组
```
```java
class Person{}
interface INews{}
interface IMessage{}
class Student extends Person implements INews,IMessage{}
public class Test{
    public static void main(String[] args) {
        Class<?> cls = Student.class;
        //1.取得类的包名称
        System.out.println(cls.getPackage());
        //2.取得父类的Class对象
        System.out.println(cls.getSuperclass());
        //3.取得实现的父接口
        Class<?>[] cls1 = cls.getInterfaces();
        for(Class<?> i:cls1){
            System.out.print(i+"   ");
        }
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330195655791.png)
## 2.2 反射调用类中构造方法
### 2.2.1 取得类中指定参数的构造方法
```java
public Constructor<T> getConstructor(Class<?>... parameterTypes)throws NoSuchMethodException, SecurityException
public Constructor<?>[] getDeclaredConstructors()throws SecurityException
```
### 2.2.2 取得类中所有的构造方法
```java
public Constructor<?>[] getConstructors() throws SecurityException
public Constructor<?>[] getDeclaredConstructors()throws SecurityException
```
```java
import java.lang.reflect.Constructor;
class Person{
    private Person(){}
    protected Person(String name){}
    public  Person(String name,int age){}
}
public class Test{
    public static void main(String[] args) {
        //1.取得反射对象
        Class<?> cls = Person.class;
        //2.取得该类中的所有构造方法
        Constructor<?>[] constructors = cls.getConstructors();
        for(Constructor constructor:constructors){
            System.out.println(constructor);
        }
        System.out.println("---------------------------------");
        Constructor<?>[] constructors1 = cls.getDeclaredConstructors();
        for(Constructor constructor:constructors1){
            System.out.println(constructor);
        }
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330201007777.png)
### 2.2.3 Class类中的newInstance和Constructor类中的newInstance比较
Class类中的newInstance:
```java
//只能调用类中的无参构造且无参构造必须是public权限
 public T newInstance()
        throws InstantiationException, IllegalAccessException
```
Constructor中的newInstance:
```java
//可以调用类中其他有参构造
 public T newInstance(Object ... initargs)
        throws InstantiationException, IllegalAccessException,
               IllegalArgumentException, InvocationTargetException
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330201612623.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_16,color_FFFFFF,t_70)
```java
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
class Person{
    private String name;
    private int age;
    private    Person(String name,int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class Test{
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //1.取得反射对象
        Class<?> cls = Person.class;
        //2.取得该类中的构造方法
        Constructor constructors = cls.getDeclaredConstructor(String.class,int.class);
        Person person = (Person)constructors.newInstance("苗",12);
        System.out.println(person);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330202321104.png)
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330202334100.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_16,color_FFFFFF,t_70)
## 2.3 反射调用类中的普通方法
### 2.3.1 取得类中指定名称的普通方法
```java
public Method getMethod(String name, Class<?>... parameterTypes)
        throws NoSuchMethodException, SecurityException 
public Method getDeclaredMethod(String name, Class<?>... parameterTypes)
        throws NoSuchMethodException, SecurityException 
```
### 2.3.2 取得类中全部普通方法
```java
 public Method[] getMethods() throws SecurityException //取得本类以及父类中所有public的方法
 public Method[] getDeclaredMethods() throws SecurityException //取得本类中全部方法包含私有方法
```
```java
import java.lang.reflect.Method;
class Person{
    private void test(){}
    protected void fun(){}
    public void ha(){}
}
class Student extends Person{
	public Student(){}
    private void test1(){}
    protected void fun1(){}
    public void ha1(){}
}
public class Test{
    public static void main(String[] args) {
        //取得类的Class对象
        Class<?> cls = Student.class;
        Method[] methods = cls.getMethods();
        for(Method method:methods){
            System.out.println(method);
        }
        System.out.println("-------------------------");
        Method[] methods1 = cls.getDeclaredMethods();
        for(Method method:methods1){
            System.out.println(method);
        }
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330204018320.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_16,color_FFFFFF,t_70)
### 2.3.3 调用类中的普通方法invoke()
```java
public Object invoke(Object obj, Object... args)
        throws IllegalAccessException, IllegalArgumentException,
           InvocationTargetException
```
```java
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Person{
    private String name;
    private int age;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
public class Test{
    public static void main(String[] args) throws Exception {
        //1.获得类的Class属性
        Class<?> cls = Person.class;
        //2.创建类的实例化对象
        Person person = (Person)cls.newInstance();
        //2.取得类的普通方法
        Method method = cls.getMethod("setName",String.class);
        Object obj = method.invoke(person,"苗");
        System.out.println(person);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330205632685.png)
## 2.4 反射调用类中属性
```java
 //取得本类以及父类中的所有public属性
 public Field[] getFields() throws SecurityException
 //取得本类中的所有方法 包括私有
 public Field[] getDeclaredFields() throws SecurityException 
```
```java
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

class Person{
    public String name;
    private int age;
}
class Student extends Person{
    private String school;
    public int grade;
}
public class Test{
    public static void main(String[] args) throws Exception {
        //1.取得Class类对象
        Class<?> cls = Student.class;
        //2.取得类中全部属性
        Field[] fields = cls.getFields();
        for(Field field:fields){
            System.out.println(field);
        }
        System.out.println("------------------------");
        //3.取得类中全部属性
        Field[] fields1 = cls.getDeclaredFields();
        for(Field field:fields1){
            System.out.println(field);
        }
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330211945197.png)
### 2.4.1 常见方法
```java
 public void set(Object obj, Object value) throws IllegalArgumentException,
IllegalAccessException  //设置属性内容
 public Object get(Object obj) throws IllegalArgumentException,
IllegalAccessException  //取得属性内容
```
```java
import java.lang.reflect.Field;
class Person{
    public String name;
}
public class Test{
    public static void main(String[] args) throws Exception{
        //1.取得class对象
        Class<?> cls = Person.class;
        //2.实例化对象
        Person person = (Person)cls.newInstance();
        //3.操作属性
        Field nameField = cls.getDeclaredField("name");
        nameField.set(person,"苗");
        System.out.println(nameField.get(person));
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190330205632685.png)
## 2.5 动态设置封装
动态设置封装:
```java
public void setAccessible(boolean flag) throws SecurityException
```
Constructor、Field均可设置封装，使得私有属性、构造方法可见
```java
import java.lang.reflect.Field;
class Person{
    private String name;
}
public class Test{
    public static void main(String[] args) throws Exception{
        //1.取得class对象
        Class<?> cls = Person.class;
        //2.实例化对象
        Person person = (Person)cls.newInstance();
        //3.操作属性
        Field nameField = cls.getDeclaredField("name");
        //动态设置封装
        nameField.setAccessible(true);
        nameField.set(person,"苗");
        System.out.println(nameField.get(person));
    }
}
```

