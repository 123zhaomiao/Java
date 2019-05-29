
# 1.Collections工具类
## 1.1 集合翻转reverse
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("c");
        list.add("c++");
        list.add("linux");
        System.out.println("翻转前:"+list);
        Collections.reverse(list);
        System.out.println("翻转后:"+list);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401153956709.png)
## 1.2 将所有指定的元素添加到指定的集合中 addAll
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        //相当于调用了三次add
        Collections.addAll(list,"java","c++","c");
        System.out.println(list);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401154549894.png)
## 1.3 集合乱序 shuffle
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(3);
        list.add(2);
        System.out.println("乱序前:"+list);
        Collections.shuffle(list);
        System.out.println("乱序后:"+list);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019040116165970.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3poYW9fbWlhbw==,size_16,color_FFFFFF,t_70)
<font color = red>集合乱序，每次输出都不一样</font>
## 1.4 集合填充 fill
<font color="#dd0000">用指定的元素代替指定列表的所有元素。 </font>
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(3);
        list.add(2);
        System.out.println("填充前:"+list);
        Collections.fill(list,123);
        System.out.println("填充前:"+list);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/2019040116294324.png)
## 1.5 集合编程线程安全的集合
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        //1.ArrayList线程不安全
        List<Integer> list = new ArrayList<>();
        List<Integer> safeList = 
                Collections.synchronizedList(list);
    }
}
```
## 1.6 集合排序 sort
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        //1.ArrayList线程不安全
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(3);
        System.out.println("排序前:"+list);
        Collections.sort(list);
        System.out.println("排序后:"+list);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401163706135.png)
## 1.7 将集合变为不可变集合 unmodifiableList
```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test{
    public static void main(String[] args) {
        //1.ArrayList线程不安全
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(4);
        list.add(2);
        list.add(3);
        list = Collections.unmodifiableList(list);
        list.add(5);
    }
}
```
![在这里插入图片描述](https://img-blog.csdnimg.cn/20190401163924519.png)
