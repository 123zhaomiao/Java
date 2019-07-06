class Person{
    private String name;
    private int age;
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    @Override
    public boolean equals(Object obj) {
        //1.如果obj为空 直接返回null
        if(obj == null){
            return false;
        }
        
        //2.如果两个对象的地址相同 直接返回true
        if(this == obj){
            return true;
        }
        //3.如果obj不是Person类的实例
        if(!(obj instanceof Person)){
            return false;
        }
        //此时需要比较两个Person对象的内容
        //obj现在还是属于Object类、向下转型转为Person类
        Person per = (Person)obj;
        return per.age == this.age &&
                per.name.equals(this.name);
    }
}

public class Test {
    public static void main(String[] args) {
        Person per = new Person("张三", 20);
        Person per1 = new Person("张三", 20);
        System.out.println(per.equals(per1));
    }
}

