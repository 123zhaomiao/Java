import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
public class Test {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "嘻嘻", "哈哈", "呵呵");
        //方法引用
        list.forEach(System.out::print);
        System.out.println();
        //Lambda表达式
        list.forEach(s-> System.out.print(s));
        System.out.println();
        //匿名内部类
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.print(s);
            }
        });
    }
}
