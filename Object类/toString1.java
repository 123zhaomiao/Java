class Person{
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "姓名:"+this.name+" 年龄:"+this.age;
    }
}
public class Test {
    public static void main(String[] args) {
        Person per = new Person("张三",20);
        String str = "hello";
        fun(per);
        fun(str);
    }
    private static void fun(Object obj){
        System.out.println(obj.toString());
        System.out.println(obj);
    }
}
