import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

public class Test{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list,"数据结构","c++","c语言");
        //实例化Stream对象
        Stream<String> stream = list.stream();
        System.out.println(stream.count());
    }
}
