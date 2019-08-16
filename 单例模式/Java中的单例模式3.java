
class Singleton{
    private static Singleton singleton;
    //构造方法私有化、类外部创建不了对象
    private  Singleton(){}
    public static  Singleton getInstance() {
        if(singleton == null){
            synchronized (Singleton.class){
                singleton = new Singleton();
            }
        }
        return singleton;
    }
}

public class Test {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        Singleton singleton1 = Singleton.getInstance();
        // == 比较的是singleton与singleton1的地址
        System.out.println(singleton == singleton1);
    }
}
