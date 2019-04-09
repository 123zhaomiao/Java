//饿汗式单例
class Singleton{
    // 产生了唯一的一个对象 、通过类名直接调用  、类加载时实例化
    private static final Singleton singleton = new Singleton();
    //构造方法私有化、类外部创建不了对象
    private  Singleton(){}
    //类外部只能通过对象调用普通方法,但是类外部没有对象，所以此方法为静态方法
    public static Singleton getInstance() {
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
