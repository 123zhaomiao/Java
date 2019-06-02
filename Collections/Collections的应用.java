public class Test{
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("java");
        list.add("c");
        list.add("c++");

        list.add("数据结构");
        System.out.println("原始数据"+list);
        //1.集合翻转
        Collections.reverse(list);
        System.out.println("翻转的结果:"+list);
        //2.集合乱序 并且每次都不一样
        Collections.shuffle(list);
        System.out.println("乱序"+ list);
        //3.集合填充
        Collections.fill(list,"---");
        System.out.println("集合填充"+list);
        //4.集合编程线程安全的集合
        List<String> safeList = Collections.synchronizedList(list);
        //5.集合变成不可变集合 之后集合不能被修改
        list = Collections.unmodifiableList(list);
        list.add("数据库");
        System.out.println(Integer.reverse(1));
    }
}
