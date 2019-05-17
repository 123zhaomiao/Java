
# String类
完整类名：java.long.String 
## 1.1 String类的两种实例化
### 1.1.1 直接赋值
```java
	//直接赋值，在堆上开辟空间
	String str="hello";
	System.out.println(str);
```
### 1.1.2 通过构造方法实例化
&emsp;&emsp;String本身是个类，既然是类，那么就一定会有构造方法。
```java
	//通过构造方法实例化String对象
	String str=new String("hello");
	System.out.println(str);
```
&emsp;&emsp;<font color="#dd0000">引用数据类型均在堆上开辟空间</font><br /> &emsp;&emsp;<font color="#dd0000">只有String类可以直接赋值，其它类都得new对象才能赋值，原因是idk对String类做了优化</font><br /> 
## 1.2 字符串相等比较
### 1.2.1 基本数据类型的比较
```java
public class TestString{
	public static void main(String[] args)
	{
		int x=10;
		char y=10;
		System.out.println(x==y);//true
	}
}
```
```java
public class TestString{
		public static void main(String[] args)
		{
			int x=97;
			char y='a';
			System.out.println(x==y);//true 字符a在内存中的以97的形式储存
		}
}
```
### 1.2.1 字符串比较
```java
public class TestString{
	public static void main(String[] args)
	{
		String str1="hello";
		String str2=new String("hello");
		System.out.println(str1==str2);
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029155537273.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;==本身就是进行数值比较的，如果现在用于对象比较，那么所比较的就应该是两个对象所保存的内存地址数值，而并没有比较对象的内容,所以比较结果是false。
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029155640790.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;<font color="#dd0000">要想比较内容，则必须采用String类提供的equals方法。</font><br /> 
&emsp;&emsp;`public boolean equals(String anthorString)`
```java
public class TestString{
    public static void main(String[] args)
    {
        String str1="hello";
        String str2= new String("hello");
        System.out.println(str1.equals(str2));
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2018102915593495.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
<font color="#dd0000">**面试题：请解释String类“==”与“equals”的区别？**</font><br /> 
 - ==：进行的是数值比较，比较的是两个字符串对象的内存地址数值。
 - equals():可以进行字符串内容的比较。
## 1.3 字符串常量是String类的匿名对象
```java
public class TestString{
	public static void main(String[] args)
	{
		String str1="hello";
		String str2=new String("hello");
		System.out.println(str1.equals(str2));
		System.out.println("hello".equals(str2));
	}
}
```
&emsp;&emsp;那么在之前所出现的`String str="hello"`,本质上就是将一个匿名的String类对象设置有名字，而且匿名对象一定保存在堆内存当中。
<font color="#dd0000">**如何判断用户输入的字符串等同于特定的字符串??**</font><br /> 
- 比较方法1
将特定字符写在后面：
```java
public class TestString{
    public static void main(String[] args)
    {
        /**
        str1为用户输入的字符串
        用户极有可能没有输入
         */
        String str1=null;
        System.out.println(str1.equals("hello"));
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029162034776.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;若用户没有输入，则会出现NullPointerException（空指针异常）
 - 比较方法2
若将特定字符写在前面：
```java
public class TestString{
    public static void main(String[] args)
    {
        /**
        str1为用户输入的字符串
        用户极有可能没有输入
         */
        String str1=null;
        System.out.println("hello".equals(str1));
    }
} 
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029162253917.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;字符串常量是String的匿名对象，该对象永远不可能为空，所以即使用户输入为空，也不会造成空指针异常。
&emsp;&emsp;**所以，以后在开发过程之中，如果要判断用户输入的字符串是否等同于特定字符串，一定要将特定的字符串写在前面。**
## 1.4 String类两种实例化的区别
### 1.4.1 采用直接赋值
```java
public class TestString{
	public static void main(String[] args)
	{
		String str1 = "hello" ;
		String str2 = "hello" ;
		String str3 = "hello" ;
		System.out.println(str1 == str2); // true
		System.out.println(str1 == str3); // true
		System.out.println(str2 == str3); // true
	}
}		
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029163300425.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;之前说过String类是在堆上开辟空间，显然由上述代码可以知道str1、str2、str3只用了一块内存空间。

![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029163447892.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;**为什么没有新开辟空间呢？**
&emsp;&emsp;<font color="#dd0000">String类的设计使用了共享设计模式</font><br /> 
    在JVM底层实际上会自动维护一个对象池（字符串对象池），如果现在采用了直接赋值的模式进行String类对象实例化操作，那么该实例化对象（字符串的内容）将自动保存到这个对象池之中，如果下次继续使用直接赋值的模式声明String类对象，此时对象池之中如若有指定内容，将直接进行引用；如若没有，则开辟新的字符串对象而将其保存在对象池之中供下次使用。
所谓的对象池就是一个对象数组（目的是减小开销）。
### 1.4.2 采用构造方法
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029164443497.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;通过分析可知，如果使用String构造方法就会开辟两块堆内存空间，并且其中一块堆内存空间将会变成垃圾空间。除了这一缺点外，也会对字符串共享产生问题。

```java
public class TestString{
	public static void main(String[] args)
	{
		String str1="hello";
		String str2= new String("hello");
		System.out.println(str1==str2);
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029164751744.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;**str1!=str2,所以str2并没有保存在对象池中。**
手工入池：`public native String intern();`
```java
public class TestString{
	public static void main(String[] args)
	{
		String str1="hello";
		String str2= new String("hello").intern();
		System.out.println(str1==str2);
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029165313268.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
<font color="#dd0000">**面试题：请解释String类两种对象实例化的区别？**</font><br /> 
 - 直接赋值：只会开辟一块堆内存空间，并且该字符串对象可以自动保存在对象池中供下次使用。
 - 构造方法：会开辟两块堆内存空间，其中一块成为垃圾空间，不会自动的保存在对象池中，可以使用intern()方法手工入池。

因此，我们一般会采取第一种方式即直接赋值。
## 1.5 字符串常量不可变更
字符串一旦定义不可改变！
&emsp;&emsp;所有的语言对于字符串的底层实现，都是字符数组，数组的最大缺陷就是长度固定。在定义字符串常量时，它的内容不可改变。
```java
public class TestString{
	public static void main(String[] args)
	{
	    String str = "hello" ;
        str = str + " world" ;
        str += "!!!" ;
        System.out.println(str); 
	}
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029170232671.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
<font color="#dd0000">以上字符串的变更是**字符串对象**的变更而非字符串常量!!</font><br /> 
![在这里插入图片描述](https://img-blog.csdnimg.cn/20181029171004257.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_27,color_FFFFFF,t_70)
&emsp;&emsp;**可以发现字符串上没有发生任何变化，但是字符串对象的引用一直在改变，而且会产生大量的垃圾空间。**
&emsp;&emsp;<font color="#dd0000">所以字符串不允许改变太多!!!</font><br /> 



































































































































