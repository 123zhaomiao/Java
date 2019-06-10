class Person{
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
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

